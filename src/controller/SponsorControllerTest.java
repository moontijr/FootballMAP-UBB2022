package controller;

import Model.Sponsor;
import Model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SponsorControllerTest {
    TeamRepositoryMemory teamRepositoryMemory=new TeamRepositoryMemory();
    SponsorRepositoryMemory sponsorRepositoryMemory=new SponsorRepositoryMemory();
    SponsorController sponsorController=new SponsorController(sponsorRepositoryMemory,teamRepositoryMemory);
    Team team=new Team("CFR Cluj","CFR","Romania","Cluj-Napoca",1927,40,45000000);
    Team team1=new Team("Steaua Bucuresti","FCSB","Romania","Bucharest",1947,40,12500);

    Sponsor sponsor=new Sponsor("Mike","Nk",250000000);
    Sponsor sponsor1=new Sponsor("NTT Data","NTT",25000);

    @BeforeEach
    void setUp()
    {

        teamRepositoryMemory.add(team);
        teamRepositoryMemory.add(team1);
        sponsorRepositoryMemory.add(sponsor);
        sponsorRepositoryMemory.add(sponsor1);
    }

    @Test
    void startSponsorship() {
        assertTrue(sponsorController.startSponsorship(sponsor, team));
        assertTrue(sponsorController.startSponsorship(sponsor, team1));
        assertFalse(sponsorController.startSponsorship(sponsor, team1));
    }

    @Test
    void endSponsorship() {
        sponsorController.startSponsorship(sponsor,team1);
        assertTrue(sponsorController.endSponsorship(sponsor, team1));
        assertFalse(sponsorController.endSponsorship(sponsor1,team1));
    }

    @Test
    void sortAllSponsorsByName() {
        List<Sponsor> sponsors=new ArrayList<>();
        sponsors.add(sponsor);
        sponsors.add(sponsor1);
        List <Sponsor> listFromController=sponsorController.sortAllSponsorsByName();
        assertEquals(sponsors,listFromController);
    }

    @Test
    void sortAllSponsorsByNetWorth() {
        List<Sponsor> sponsors=new ArrayList<>();
        sponsors.add(sponsor1);
        sponsors.add(sponsor);
        List <Sponsor> listFromController=sponsorController.sortAllSponsorsByNetWorth();
        assertEquals(sponsors,listFromController);
    }

    @Test
    void listAllTeamsFromASponsor() {
        sponsorController.startSponsorship(sponsor,team1);
        sponsorController.startSponsorship(sponsor,team);
        List <Team> teams=sponsorController.listAllTeamsFromASponsor(sponsor);
        List<Team> teams1=new ArrayList<>();
        teams1.add(team1);
        teams1.add(team);
        assertEquals(teams1,teams);

    }

    @Test
    void sortAllTeamsFromASponsorByMarketValue() {
        sponsorController.startSponsorship(sponsor,team1);
        sponsorController.startSponsorship(sponsor,team);
        List <Team> teams=sponsorController.sortAllTeamsFromASponsorByMarketValue(sponsor);
        List<Team> teams1=new ArrayList<>();
        teams1.add(team1);
        teams1.add(team);
        assertEquals(teams1,teams);
    }

    @Test
    void startSponsoring() {
        assertTrue(sponsorController.startSponsoring(sponsor,"CFR"));
        assertFalse(sponsorController.startSponsoring(sponsor,"FCSBB"));
        assertTrue(sponsorController.startSponsoring(sponsor,"CFR"));
    }

    @Test
    void endSponsoring() { //not sure aici
        assertTrue(sponsorController.endSponsoring(sponsor,"CFR"));
        assertFalse(sponsorController.endSponsoring(sponsor,"FCSBB"));
        assertTrue(sponsorController.endSponsoring(sponsor,"CFR"));

    }
}