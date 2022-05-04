package dao;

import models.Hero;
import models.Squad;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

class Sql2oSquadDaoTest {

    private static Sql2oSquadDao squadDao; //these variables are now static.
    private static Sql2oHeroDao heroDao; //these variables are now static.
    private static Connection conn; //these variables are now static.

    @BeforeClass //changed to @BeforeClass (run once before running any tests in this file)
    public static void setUp() throws Exception { //changed to static
        String connectionString = "jdbc:postgresql://localhost:5432/hero_test"; // connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, null, null); // changed user and pass to null
        squadDao = new Sql2oSquadDao(sql2o);
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open(); // open connection once before this test file is run
    }
    @After // run after every test
    public void tearDown() throws Exception { //I have changed
        System.out.println("clearing database");
        squadDao.clearAllSquads(); // clear all categories after every test
        heroDao.clearAllHeroes(); // clear all tasks after every test
    }

    @AfterClass // changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception { //changed to static and shutDown
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
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