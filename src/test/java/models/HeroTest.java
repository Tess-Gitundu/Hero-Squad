package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Hero hero = setupNewHero();
        assertEquals(true, hero instanceof Hero);
    }
    @Test
    public void HeroInstantiatesWithDescription_true() throws Exception {
        Hero hero = setupNewHero();
        assertEquals("strength", hero.getSpecialPower());
    }

    public Hero setupNewHero() {
        return new Hero("WonderWoman",33,"Strength","Ego", 1, 0);
    }

}