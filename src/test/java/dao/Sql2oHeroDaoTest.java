package dao;

import models.Hero;
import org.sql2o.*;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class Sql2oHeroDaoTest {
    private Sql2oHeroDao heroDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingCourseSetsId() throws Exception {
        Hero hero = new Hero ("WonderWoman",33,"Strength","Ego" , 1, 0);
        int originalHeroId = hero.getId();
        heroDao.add(hero);
        assertNotEquals(originalHeroId, hero.getId()); //how does this work?
    }
    @Test
    public void existingHeroesCanBeFoundById() throws Exception {
        Hero hero = new Hero ("WonderWoman",33,"Strength","Ego" , 1, 0);
        heroDao.add(hero);
        Hero foundHero = heroDao.findById(hero.getId()); //retrieve
        assertEquals(hero, foundHero);
    }

}