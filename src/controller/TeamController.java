package controller;

import Model.Sponsor;
import Model.Team;
import repository.TeamRepository;
import repository.inmemory.TeamRepositoryMemory;

import java.util.Comparator;

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
    public void sortAllTeamsByBudget()
    {
        teamRepositoryMemory.getAllTeams().sort(Comparator.comparing(Team::getBudget));
        for (Team team : teamRepositoryMemory.getAllTeams() )
        {
            team.printTeam();
        }
    }

    /**
     * sorts all the teams from our database(foundation year)
     */
    public void sortAllTeamsByFoundationYear()
    {

        teamRepositoryMemory.getAllTeams().sort(Comparator.comparing(Team::getFoundationYear));
        for (Team team : teamRepositoryMemory.getAllTeams())
        {
            team.printTeam();
        }

    }

    /**
     * prints all the teams that are affiliated with a specific sponsor
     * @param sponsor 1
     */
    public void printAllTeamsAffiliatedWithSponsor(Sponsor sponsor)
    {
        for (Team team : sponsor.sponsoredTeams)
        {
            team.printTeam();
        }
    }

    /**
     * we sort teams (that are sponsored by 1 specific sponsor ) by budget
     * @param sponsor 1
     */
    public void sortTeamsByBudget(Sponsor sponsor)
    {
        sponsor.sponsoredTeams.sort(Comparator.comparing(Team::getBudget));
        for (Team team : sponsor.sponsoredTeams)
        {
            team.printTeam();
        }

    }



}
