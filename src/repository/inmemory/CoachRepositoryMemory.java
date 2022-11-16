package repository.inmemory;

import Model.Coach;
import Model.Team;
import repository.CoachRepository;

import java.util.ArrayList;

public class CoachRepositoryMemory implements CoachRepository {
    private static CoachRepositoryMemory single_instance = null;

    private final ArrayList<Coach> allCoaches = new ArrayList<>();

    public static CoachRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new CoachRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        CoachRepositoryMemory.getInstance().add(new Coach("Marius", "Sumudica", 58, "Romania", "Defensive", new Team("Trial", "TR", "Romania", "Medias", 1999, 40, 28000)));
    }


    public ArrayList<Coach> getAllCoaches() {
        return allCoaches;
    }

    @Override
    public void add(Coach entity) {
        this.allCoaches.add(entity);
    }

    @Override
    public void remove(String s, String id2) {
        this.allCoaches.remove(findById(s, id2));
    }

    @Override
    public void update(String s, String id2, Coach newEntity) {
        this.findById(s, id2).setFirstName(newEntity.getFirstName());
        this.findById(s, id2).setLastName(newEntity.getLastName());
        this.findById(s, id2).setAge(newEntity.getAge());
        this.findById(s, id2).setNationality(newEntity.getNationality());
        this.findById(s, id2).setTeam(newEntity.getTeam());
        this.findById(s, id2).setPlayStyle(newEntity.getPlayStyle());
    }

    @Override
    public Coach findById(String s, String id2) {
        for (Coach coach : allCoaches)
            if (s.equals(coach.getFirstName()) && id2.equals(coach.getLastName()))
                return coach;
        return null;
    }

    public boolean existsCoach(String firstName, String secondName) {
        for (Coach coach : this.allCoaches)
            if (coach.getFirstName().equals(firstName) && coach.getLastName().equals(secondName))
                return true;
        return false;
    }


}
