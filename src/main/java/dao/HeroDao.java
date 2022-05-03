package dao;

import models.Hero;
import java.util.List;

public interface HeroDao {

    static void add(Hero hero) {

    }



    List <Hero> getAll();

    Hero findById(int id);
    void update(int id, String content, int squadId);

    void deleteById(int id);
    void clearAllHeroes();
}
