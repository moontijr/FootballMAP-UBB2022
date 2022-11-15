package controller;

import Model.Sponsor;
import Model.Team;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    public boolean startSponsorship(Sponsor sponsor, Team team)
    {

        if(team.sponsors.contains(sponsor))
            return false;
        else {
            sponsor.sponsorTeam(team);
            return true;
        }
    }

    /**
     * we end the sponsorship between a team and a firm
     * @param sponsor 1
     * @param team 1
     */
    public boolean endSponsorship(Sponsor sponsor, Team team)
    {
        if(team.sponsors.contains(sponsor)) {
            sponsor.stopSponsorTeam(team);
            return true;
        }
        else
        {
            return false;
        }
    }
    public void printAllSponsors()
    {
        for ( Sponsor sponsor : sponsorRepositoryMemory.getAllSponsors())
            sponsor.printSponsor();
    }

    /**
     * sorts all our sponsors from our database by their name
     */
    public List <Sponsor> sortAllSponsorsByName()
    {
        List <Sponsor> sponsors=sponsorRepositoryMemory.getAllSponsors();
        sponsors.sort(Comparator.comparing(Sponsor::getName));
        return sponsors;
        /*
        for (Sponsor sponsor : sponsorRepositoryMemory.getAllSponsors()) {
            sponsor.printSponsor();
        }
         */
    }

    /**
     * sorts all our sponsors from our database by their net worth
     */
    public List<Sponsor> sortAllSponsorsByNetWorth()
    {
        List <Sponsor> sponsors=sponsorRepositoryMemory.getAllSponsors();
        sponsors.sort(Comparator.comparing(Sponsor::getNetWorth));
        return sponsors;
        /*
        sponsorRepositoryMemory.getAllSponsors().sort(Comparator.comparing(Sponsor::getNetWorth));
        for (Sponsor sponsor : sponsorRepositoryMemory.getAllSponsors()) {
            sponsor.printSponsor();
        }

         */
    }

    /**
     * gives us a list of all the teams, that the SPECIFIC sponsor is sponsoring
     * @param sponsor from many teams
     */
    //AICI AM RAMAS
    public List <Team> listAllTeamsFromASponsor(Sponsor sponsor)
    {
        List<Team> teams = new ArrayList<>(sponsor.sponsoredTeams);
        if(teams.size()>0)
            return teams;
        else
            return null;
    }

    /**
     * sorts all the teams that are sponsored by a SPECIFIC sponsor
     * @param sponsor from many teams
     */
    public List <Team> sortAllTeamsFromASponsorByMarketValue(Sponsor sponsor)
    {
        List <Team> teams=sponsor.sponsoredTeams;
        teams.sort(Comparator.comparing(Team::getBudget));
        /*
        for (Team team : sponsor.sponsoredTeams )
        {
            team.printTeam();
        }
         */
        if (teams.size()>0)
            return teams;
        else
            return null;
    }

    /**
     * starting a sponsorship between a sponsor and a team
     * @param sponsor sponsor
     * @param teamAbreviation string
     */
    public boolean startSponsoring(Sponsor sponsor,String teamAbreviation)
    {
        Team team1;
        for (Team team: teamRepositoryMemory.getAllTeams())
            if (team.getAbbreviation().contains(teamAbreviation)) {
                team1 = team;
                sponsor.sponsorTeam(team1);
                return true;
            }
        return false;

    }

    /**
     * ends a sponsorship between a sponsor and a team
     * @param sponsor sponsor
     * @param teamAbreviation string
     */
    public boolean endSponsoring(Sponsor sponsor,String teamAbreviation)
    {
        Team team1;
        for (Team team: teamRepositoryMemory.getAllTeams())
            if (team.getAbbreviation().contains(teamAbreviation)) {
                team1 = team;
                sponsor.stopSponsorTeam(team1);
                return true;
            }
        return false;
    }

}


