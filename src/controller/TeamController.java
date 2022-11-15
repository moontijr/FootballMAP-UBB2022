package controller;

import Model.Player;
import Model.Sponsor;
import Model.Team;
import repository.TeamRepository;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeamController {
    private TeamRepositoryMemory teamRepositoryMemory;

    public TeamController(TeamRepositoryMemory teamRepositoryMemory) {
        this.teamRepositoryMemory = teamRepositoryMemory;
    }

    /**
     * prints all the Teams from our database
     */
    public void printAllTeams()
    {
        for (Team team : teamRepositoryMemory.getAllTeams())
            team.printTeam();
    }

    /**
     * sorts all our teams from the database (budget)
     */
    public List<Team> sortAllTeamsByBudget()
    {
        List<Team> allTeams = teamRepositoryMemory.getAllTeams();
        allTeams.sort(Comparator.comparing(Team::getBudget));
        return allTeams;
        /*
        teamRepositoryMemory.getAllTeams().sort(Comparator.comparing(Team::getBudget));
        for (Team team : teamRepositoryMemory.getAllTeams() )
        {
            team.printTeam();
        }
         */
    }

    /**
     * sorts all the teams from our database(foundation year)
     */
    public List <Team> sortAllTeamsByFoundationYear()
    {
        List<Team> allTeams = teamRepositoryMemory.getAllTeams();
        allTeams.sort(Comparator.comparing(Team::getFoundationYear));
        return allTeams;
        /*

        teamRepositoryMemory.getAllTeams().sort(Comparator.comparing(Team::getFoundationYear));
        for (Team team : teamRepositoryMemory.getAllTeams())
        {
            team.printTeam();
        }
         */

    }

    /**
     * prints all the teams that are affiliated with a specific sponsor
     * @param sponsor 1
     */
    public List<Team> getAllTeamsAffiliatedWithSponsor(Sponsor sponsor)
    {
        List<Team> allTeams = new ArrayList<>(sponsor.sponsoredTeams);
        if(allTeams.size()>0)
            return allTeams;
        else
            return null;
    }

    /**
     * we sort teams (that are sponsored by 1 specific sponsor ) by budget
     * @param sponsor 1
     */
    public List<Team> sortSponsoredTeamsByBudget(Sponsor sponsor)
    {
        List<Team> allTeams = new ArrayList<>(sponsor.sponsoredTeams);
        allTeams.sort(Comparator.comparing(Team::getBudget));
        if(allTeams.size()>0)
            return allTeams;
        else
            return null;
        /*
        for (Team team : sponsor.sponsoredTeams)
        {
            team.printTeam();
        }

         */

    }

    public List<Team> sortAllTeamsByCountry(String country)
    {
        List<Team> allTeamsFromACountry=new ArrayList<>();
        for (Team team: teamRepositoryMemory.getAllTeams())
            if(team.getCountry().contains(country))
                allTeamsFromACountry.add(team);
        return allTeamsFromACountry;
//        if(allTeamsFromACountry.size()>0) {
//            for (Team team : allTeamsFromACountry) {
//                team.printTeam();
//            }
//        }
//        else
//        {
//            System.out.println("No Team from that specific Country");
//        }
    }


}
