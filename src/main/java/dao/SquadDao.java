package dao;

import models.Squad;
import models.Hero;
import org.h2.util.Task;

import java.util.List;

public interface SquadDao {

        List<Task> getAllHeroesByCategory(int squadId);
        List<Squad> getAll();
        void add (Squad squad);
        Squad findById(int id);
        void update(int id, String name);
        void deleteById(int id);
        void clearAllCategories();
    }

