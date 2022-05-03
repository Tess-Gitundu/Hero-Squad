package dao;

import models.Hero;
import models.Squad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

class Sql2oSquadDaoTest {

    private Sql2oSquadDao squadDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        squadDao = new Sql2oSquadDao(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingCourseSetsId() throws Exception {
        Squad squad = new Squad ( 23, "WonderWoman", "health");
        int originalSquadId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(originalSquadId, squad.getId()); //how does this work?
    }
    @Test
    public void existingHeroesCanBeFoundById() throws Exception {
        Squad squad = new Squad (23, "WonderWoman", "health");
        squadDao.add(squad);
        Squad foundSquad = squadDao.findById(squad.getId()); //retrieve
        assertEquals(squad, foundSquad);
    }

}