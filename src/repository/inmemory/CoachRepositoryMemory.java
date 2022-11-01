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
        CoachRepositoryMemory.getInstance().add(new Coach("Marius","Sumudica",58,"Romania", "Deffensive", new Team("Trial","TR","Romania","Medias",1999,40,28000)));
    }

    public static CoachRepositoryMemory getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(CoachRepositoryMemory single_instance) {
        CoachRepositoryMemory.single_instance = single_instance;
    }

    public ArrayList<Coach> getAllCoaches() {
        return allCoaches;
    }

    @Override
    public void add(Coach entity) {
        if(!this.existsCoach(entity.getFirstName(), entity.getLastName()))
        this.allCoaches.add(entity);
        else
        {
            System.out.println("Coach already exists");
        }

    }

    @Override
    public void remove(String s, String id2) {
        if (findById(s,id2)!=null)
            this.allCoaches.remove(findById(s,id2));
        else
            System.out.println("Coach was not found!");
    }

    @Override
    public void update(String s, String id2, Coach newEntity) {
        if (findById(s, id2) != null) {
            this.allCoaches.remove(findById(s,id2));
            this.allCoaches.add(newEntity);
        }
        else
            System.out.println("Coach  was not found!");
    }

    @Override
    public Coach findById(String s, String id2) {
        for(Coach coach: allCoaches)
            if(s.equals(coach.getFirstName())&&id2.equals(coach.getLastName()))
                return coach;
        return null;
    }

    public boolean existsCoach(String firstName, String secondName)
    {
        for(Coach coach: this.allCoaches)
            if(coach.getFirstName().equals(firstName)&&coach.getLastName().equals(secondName))
                return true;
        return false;
    }

    public Coach getCoachFromFirstNameAndSecondName(String firstName, String secondName)
    {
        if(existsCoach(firstName,secondName))
        {
            for(Coach coach : this.allCoaches)
                if(coach.getFirstName().equals(firstName)&&coach.getLastName().equals(secondName))
                    return coach;
        }
        return null;

    }

}
