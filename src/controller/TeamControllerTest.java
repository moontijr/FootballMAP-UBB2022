package controller;

import Model.Player;
import Model.Sponsor;
import Model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamControllerTest {
    TeamRepositoryMemory teamRepositoryMemory = new TeamRepositoryMemory();
    TeamController teamController = new TeamController(teamRepositoryMemory);
    Team team=new Team("CFR Cluj","CFR","Romania","Cluj-Napoca",1927,40,45000000);
    Team team1=new Team("Steaua Bucuresti","FCSB","Romania","Bucharest",1947,40,12500);

    Sponsor sponsor=new Sponsor("Nike","Nk",250000000);


    @BeforeEach
    void  setUp()
    {
        teamRepositoryMemory.add(team);
        teamRepositoryMemory.add(team1);
        sponsor.sponsorTeam(team);
        sponsor.sponsorTeam(team1);

    }
    @Test
    void sortAllTeamsByBudget() {
        List<Team> teams=new ArrayList<>();
        teams.add(team1);
        teams.add(team);
        List<Team> sortedTeams;
        sortedTeams = teamController.sortAllTeamsByBudget();
        assertEquals(teams,sortedTeams);
    }

    @Test
    void sortAllTeamsByFoundationYear() {
        List<Team> teams=new ArrayList<>();
        teams.add(team);
        teams.add(team1);
        List<Team> sortedTeams;
        sortedTeams = teamController.sortAllTeamsByFoundationYear();
        assertEquals(teams,sortedTeams);
    }

    @Test
    void printAllTeamsAffiliatedWithSponsor() {
        List<Team> teams=new ArrayList<>();
        teams.add(team);
        teams.add(team1);
        List<Team> sortedTeams;
        sortedTeams = teamController.getAllTeamsAffiliatedWithSponsor(sponsor);
        assertEquals(teams,sortedTeams);
    }

    @Test
    void sortSponsoredTeamsByBudget() {
        List<Team> teams=new ArrayList<>();
        teams.add(team1);
        teams.add(team);
        List<Team> sortedTeams;
        sortedTeams = teamController.sortSponsoredTeamsByBudget(sponsor);
        assertEquals(teams,sortedTeams);
    }
}