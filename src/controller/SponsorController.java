package controller;

import Model.Sponsor;
import Model.Team;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.Comparator;

public class SponsorController {
    private SponsorRepositoryMemory sponsorRepositoryMemory;
    private TeamRepositoryMemory teamRepositoryMemory;

    public SponsorController(SponsorRepositoryMemory sponsorRepositoryMemory, TeamRepositoryMemory teamRepositoryMemory) {
        this.sponsorRepositoryMemory = sponsorRepositoryMemory;
        this.teamRepositoryMemory = teamRepositoryMemory;
    }

    /**
     * we start a sponsorship between a team and a sponsor
     * @param sponsor 1
     * @param team 1
     */
    public void startSponsorship(Sponsor sponsor, Team team)
    {
        sponsor.sponsorTeam(team);
    }

    /**
     * we end the sponsorship between a team and a firm
     * @param sponsor 1
     * @param team 1
     */
    public void endSponsorship(Sponsor sponsor, Team team)
    {
        sponsor.sponsoredTeams.remove(team);
        team.sponsors.remove(sponsor);
    }
    public void printAllSponsors()
    {
        for ( Sponsor sponsor : sponsorRepositoryMemory.getAllSponsors())
            sponsor.printSponsor();
    }

    /**
     * sorts all our sponsors from our database by their name
     */
    public void sortAllSponsorsByName()
    {
        sponsorRepositoryMemory.getAllSponsors().sort(Comparator.comparing(Sponsor::getName));
        for (Sponsor sponsor : sponsorRepositoryMemory.getAllSponsors()) {
            sponsor.printSponsor();
        }
    }

    /**
     * sorts all our sponsors from our database by their net worth
     */
    public void sortAllSponsorsByNetWorth()
    {
        sponsorRepositoryMemory.getAllSponsors().sort(Comparator.comparing(Sponsor::getNetWorth));
        for (Sponsor sponsor : sponsorRepositoryMemory.getAllSponsors()) {
            sponsor.printSponsor();
        }
    }

    /**
     * gives us a list of all the teams, that the SPECIFIC sponsor is sponsoring
     * @param sponsor from many teams
     */
    public void listAllTeamsFromASponsor(Sponsor sponsor)
    {
        for (Team team : sponsor.sponsoredTeams)
        {
            team.printTeam();
        }
    }

    /**
     * sorts all the teams that are sponsored by a SPECIFIC sponsor
     * @param sponsor from many teams
     */
    public void sortAllTeamsFromASponsorByMarketValue(Sponsor sponsor)
    {
        sponsor.sponsoredTeams.sort(Comparator.comparing(Team::getBudget));
        for (Team team : sponsor.sponsoredTeams )
        {
            team.printTeam();
        }
    }

    /**
     * starting a sponsorship between a sponsor and a team
     * @param sponsor sponsor
     * @param teamAbreviation string
     */
    public void startSponsoring(Sponsor sponsor,String teamAbreviation)
    {
        Team team1;
        for (Team team: teamRepositoryMemory.getAllTeams())
            if (team.getAbreviation().contains(teamAbreviation)) {
                team1 = team;
                sponsor.sponsorTeam(team1);
                break;
            }

    }

    /**
     * ends a sponsorship between a sponsor and a team
     * @param sponsor sponsor
     * @param teamAbreviation string
     */
    public void endSponsoring(Sponsor sponsor,String teamAbreviation)
    {
        Team team1;
        for (Team team: teamRepositoryMemory.getAllTeams())
            if (team.getAbreviation().contains(teamAbreviation)) {
                team1 = team;
                sponsor.stopSponsorTeam(team1);
                break;
            }
    }

}


