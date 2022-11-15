package repository.inmemory;

import Model.Player;
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
        //if(!this.existsTeam(entity.getName(), entity.getAbbreviation()))
        this.allTeams.add(entity);
//        else
//            System.out.println("Team already exists");
    }

    @Override
    public void remove(String s, String id2) {
        //if (findById(s,id2)!=null) {
        findById(s,id2).disbandTeam();
        this.allTeams.remove(findById(s, id2));


//        else
//            System.out.println("Team was not found!");

    }

    @Override
    public void update(String s, String s1, Team newEntity) {
        if (findById(s, s1) != null) {
            this.getTeamFromNameAndAbbreviation(s,s1).setName(newEntity.getName());
            this.getTeamFromNameAndAbbreviation(s,s1).setAbbreviation(newEntity.getAbbreviation());
            this.getTeamFromNameAndAbbreviation(s,s1).setCountry(newEntity.getCountry());
            this.getTeamFromNameAndAbbreviation(s,s1).setTown(newEntity.getTown());
            this.getTeamFromNameAndAbbreviation(s,s1).setFoundationYear(newEntity.getFoundationYear());
            this.getTeamFromNameAndAbbreviation(s,s1).setMaxSquadSize(newEntity.getMaxSquadSize());
        }
//        else
//            System.out.println("Team  was not found!");

    }

    @Override
    public Team findById(String s, String id2) {
        for(Team team: allTeams)
            if(s.equals(team.getName())&&id2.equals(team.getAbbreviation()))
                return team;
        return null;
    }

    public boolean existsTeam(String name, String abreviation)
    {
        for(Team team:this.allTeams)
            if(team.getName().equals(name)&&team.getAbbreviation().equals(abreviation))
                return true;
        return false;
    }

    public Team getTeamFromNameAndAbbreviation(String name, String abbreviation)
    {
        if(existsTeam(name,abbreviation))
        {
            for(Team team : this.allTeams)
                if(team.getName().equals(name)&&team.getAbbreviation().equals(abbreviation))
                    return team;
        }
        return null;
    }
}
