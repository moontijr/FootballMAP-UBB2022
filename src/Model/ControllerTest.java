package Model;

import controller.CoachController;
import controller.PlayerController;
import controller.SponsorController;
import controller.TeamController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    void setUp()
    {
        PlayerRepositoryMemory playerRepositoryMemory = new PlayerRepositoryMemory();
        TeamRepositoryMemory teamRepositoryMemory = new TeamRepositoryMemory();
        SponsorRepositoryMemory sponsorRepositoryMemory = new SponsorRepositoryMemory();
        CoachRepositoryMemory coachRepositoryMemory = new CoachRepositoryMemory();
        PlayerController playerController = new PlayerController(playerRepositoryMemory,teamRepositoryMemory);
        TeamController teamController= new TeamController(teamRepositoryMemory);
        CoachController coachController=new CoachController(coachRepositoryMemory,playerRepositoryMemory);
        SponsorController sponsorController=new SponsorController(sponsorRepositoryMemory,teamRepositoryMemory);
        Player player=new Player("Ciprian","Deac",34,"Germany","Goalkeeper",10000);
        Player player1=new Player("Ciprian","Petre",35,"Germany","Goalkeeper",120000);
        Player player3=new Player("Ovidiu","Bic",34,"Romania","Forward",1250000);
        Player player4=new Player("Ovidiu","Popescu",34,"Germany","Forward",1250000);
        Team team1=new Team("CFR Cluj", "CFR", "Romania", "Cluj",1907,40,2000000);
        Team team2=new Team("U Cluj", "U", "Romania", "Cluj",1919,40,8000000);
        Sponsor sponsor1=new Sponsor("NTTData","NTT",200000000);
        Sponsor sponsor2=new Sponsor("Nike","Nike",250000000);


    }

    @org.junit.jupiter.api.Test
    void sortAllPlayersByPrice() {
    }

    @Test
    void testSortAllPlayersByPrice() {
        Player player=new Player("Ciprian","Deac",34,"Germany","Goalkeeper",10000);
        Player player1=new Player("Ciprian","Petre",35,"Germany","Goalkeeper",120000);
        List<Player>players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        PlayerRepositoryMemory playerRepositoryMemory = new PlayerRepositoryMemory();
        TeamRepositoryMemory teamRepositoryMemory = new TeamRepositoryMemory();
        playerRepositoryMemory.add(player);
        playerRepositoryMemory.add(player1);
        PlayerController playerController = new PlayerController(playerRepositoryMemory,teamRepositoryMemory);
        List<Player> sortedPlayer = new ArrayList<>();
        sortedPlayer = playerController.sortAllPlayersByPrice();
        assertEquals(players,sortedPlayer);
    }

    @Test
    void sortPlayersFromSpecificTeamByPrice() {
    }

    @Test
    void printAllPlayers() {
    }

    @Test
    void sortPlayersByStatus() {
    }

    @Test
    void sortAllPlayersByAge() {
    }

    @Test
    void sortAllPlayersByPosition() {
    }

    @Test
    void sortPlayersFromSpecificTeamByPosition() {
    }

    @Test
    void sortAllPlayersByNationality() {
    }

    @Test
    void sortAllPlayersByName() {
    }

    @Test
    void printAllTeams() {
    }

    @Test
    void sortAllTeamsByBudget() {
    }

    @Test
    void sortAllTeamsByFoundationYear() {
    }

    @Test
    void searchPlayerInDataBase() {
    }

    @Test
    void seeAllOtherPlayersWithoutYourself() {
    }

    @Test
    void sortAllTeamsByCountry() {
    }

    @Test
    void isAffordable() {
    }

    @Test
    void printAllTeamsAffiliatedWithSponsor() {
    }

    @Test
    void sortTeamsByBudget() {
    }

    @Test
    void startSponsorship() {
    }

    @Test
    void endSponsorship() {
    }

    @Test
    void listYourSquadAsACoach() {
    }

    @Test
    void listAllPlayersOutsideYourTeam() {
    }

    @Test
    void sortCoachTeamByValue() {
    }

    @Test
    void sortCoachTeamByAge() {
    }

    @Test
    void addPlayer() {
    }

    @Test
    void transferPlayer() {
    }

    @Test
    void removePlayer() {
    }

    @Test
    void printAllCoaches() {
    }

    @Test
    void sortAllCoachesByAge() {
    }

    @Test
    void sortAllCoachessByNationality() {
    }

    @Test
    void sortAllCoachesByName() {
    }

    @Test
    void sortAllCoachessByPlayStyle() {
    }

    @Test
    void printAllSponsors() {
    }

    @Test
    void sortAllSponsorsByName() {
    }

    @Test
    void sortAllSponsorsByNetWorth() {
    }

    @Test
    void listAllTeamsFromASponsor() {
    }

    @Test
    void sortAllTeamsFromASponsorByMarketValue() {
    }

    @Test
    void startSponsoring() {
    }

    @Test
    void endSponsoring() {
    }
}