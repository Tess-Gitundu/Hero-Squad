package dao;

import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.List;

public class Sql2oSquadDao {

    public Sql2oSquadDao(Sql2o sql2o) {
    }

    Sql2o sql2o = null;

    public List<Hero> getAllHeroesByCategory(int squadId) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes WHERE squadId = :squadId")
                    .addParameter("squadId", squadId)
                    .executeAndFetch(Hero.class);
        }
    }

    public void add(Squad squad) {
    }

    public Squad findById(int id) {
        return null;
    }

    public List<Squad> getAll() {
        return null;
    }
}
