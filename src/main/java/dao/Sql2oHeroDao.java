package dao;

import models.Hero;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHeroDao implements HeroDao{

    private final Sql2o sql2o;
    public Sql2oHeroDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public void add(Hero hero) {
        String sql = "INSERT INTO hero (name, age, specialPower, weakness, squadId) VALUES (:name, :age, :specialPower, :weakness, squadId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(hero)
                    .executeUpdate() //run it all
                    .getKey();
            hero.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public List<Hero> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM hero") //raw sql
                    .executeAndFetch(Hero.class); //fetch a list
        }
    }

    @Override
    public Hero findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM hero WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Hero.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, String newName, int newSquadId) {
        String sql = "UPDATE hero SET (name, squadId) = (:name, :squadId) WHERE id=:id"; //raw sql
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("squadId", newSquadId)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from hero WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllHeroes() {
        String sql = "DELETE from hero";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
