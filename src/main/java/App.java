import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.HeroDao;
import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;

import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
//    static int getHerokuAssignedPort() {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        if (processBuilder.environment().get("PORT") != null) {
//            return Integer.parseInt(processBuilder.environment().get("PORT"));
//        }
//        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
//    }
    public static void main(String[] args) {
        port(1030);
//        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        String connectionString = "jdbc: postgres://tdiutyboavvkpi:92d0699e9e31a22441849675429a0d6aa648ff48b1a09251210b4db870228db1@ec2-52-4-104-184.compute-1.amazonaws.com:5432/d5rhph0u6rc5ut";
        Sql2o sql2o = new Sql2o(connectionString, "tdiutyboavvkpi", "92d0699e9e31a22441849675429a0d6aa648ff48b1a09251210b4db870228db1");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //get: show all heroes in all squads and show all squads
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes
        get("/heroes/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            heroDao.clearAllHeroes(); //change
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete an individual hero
        get("/squads/:squad_id/heroes/:hero_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("hero_id"));
            heroDao.deleteById(idOfHeroToDelete);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show new hero form
        get("/heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "heroes-form.hbs");
        }, new HandlebarsTemplateEngine());

        //hero: process new hero form
        post("/heroes", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads", allSquads);
            String name = req.queryParams("name");
            int squadId = Integer.parseInt(req.queryParams("squadId"));
            Hero newHero = new Hero("WonderWoman",33,"Strength","Ego" , 1, 0); //ignore the hardcoded categoryId
            heroDao.add(newHero);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual hero that is nested in a squad
        get("/squads/:squad_id/heroes/:hero_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params("hero_id"));
            Hero foundHero = heroDao.findById(idOfHeroToFind);
            model.put("hero", foundHero);
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a hero
        get("/heroes/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = heroDao.findById(idOfHeroToEdit); //change
            model.put("editHero", editHero);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //hero: process a form to update a hero
        post("/heroes/:id", (req, res) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            heroDao.update(idOfHeroToEdit, newName, 1); //ignore the hardcoded categoryId for now
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show all heroes in all squads and show all squads
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads", allSquads);
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //show new squad form
        get("/squads/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll(); //refresh list of links for navbar
            model.put("squads", squads);
            return new ModelAndView(model, "squad-form.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //post: process new squad form
        post("/squads", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            Squad newSquad = new Squad(23, "WonderWoman", "health");
            squadDao.add(newSquad);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params("id")); //new
            Squad foundSquad = squadDao.findById(idOfSquadToFind);
            model.put("squad", foundSquad);
            List<Hero> allHeroesBySquad = squadDao.getAllHeroesBySquad(idOfSquadToFind);
            model.put("heroes", allHeroesBySquad);
            model.put("squads", squadDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "squad-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a squad
        get("/squads/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editSquad", true);
            Squad squad = squadDao.findById(Integer.parseInt(req.params("id")));
            model.put("squad", squad);
            model.put("squads", squadDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a squad
        post("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            String newName = req.queryParams("newSquadName");
            squadDao.update(idOfSquadToEdit, newName);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a hero
        get("/heroes/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads", allSquads);
            Hero hero = heroDao.findById(Integer.parseInt(req.params("id")));
            model.put("hero", hero);
            model.put("editHero", true);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a hero
        post("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int heroToEditId = Integer.parseInt(req.params("id"));
            String newName = req.queryParams("name");
            int newSquadId = Integer.parseInt(req.queryParams("squadId"));
            heroDao.update(heroToEditId, newName, newSquadId);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all squads and all heroes
        get("/squads/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            squadDao.clearAllSquads();
            heroDao.clearAllHeroes();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes
        get("/heroes/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            heroDao.clearAllHeroes();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
