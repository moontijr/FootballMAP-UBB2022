package repository.inmemory;

import Model.Team;
import repository.TeamRepository;

import java.util.ArrayList;


public class TeamRepositoryMemory implements TeamRepository {

    private static TeamRepositoryMemory single_instance = null;
    private final ArrayList<Team> allTeams = new ArrayList<>();

    public static TeamRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new TeamRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        TeamRepositoryMemory.getInstance().add(new Team("U Cluj", "U", "Romania", "Cluj",1919,40,8000000));
    }


    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }

    @Override
    public void add(Team entity) {
        if(!this.existsTeam(entity.getName(), entity.getAbreviation()))
        this.allTeams.add(entity);
        else
            System.out.println("Team already exists");
    }

    @Override
    public void remove(String s, String id2) {
        if (findById(s,id2)!=null) {
            findById(s,id2).disbandTeam();
            this.allTeams.remove(findById(s, id2));

        }
        else
            System.out.println("Team was not found!");

    }

    @Override
    public void update(String s, String s1, Team newEntity) {
        if (findById(s, s1) != null) {
            this.allTeams.remove(findById(s,s1));
            this.allTeams.add(newEntity);
        }
        else
            System.out.println("Team  was not found!");

    }

    @Override
    public Team findById(String s, String id2) {
        for(Team team: allTeams)
            if(s.equals(team.getName())&&id2.equals(team.getAbreviation()))
                return team;
        return null;
    }

    public boolean existsTeam(String name, String abreviation)
    {
        for(Team team:this.allTeams)
            if(team.getName().equals(name)&&team.getAbreviation().equals(abreviation))
                return true;
        return false;
    }
}
