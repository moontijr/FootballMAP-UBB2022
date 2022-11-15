package controller;

import Model.Player;
import Model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {

    PlayerRepositoryMemory playerRepositoryMemory = new PlayerRepositoryMemory();
    TeamRepositoryMemory teamRepositoryMemory = new TeamRepositoryMemory();
    PlayerController playerController = new PlayerController(playerRepositoryMemory,teamRepositoryMemory);
    Player player=new Player("Ciprian","Deac",34,"Romania","Goalkeeper",10000);
    Player player1=new Player("Ciprian","Petre",35,"Germany","Forward",1200000);
    Player player2=new Player("Mamadou","Thiam",29,"Nigeria","Forward",25000);

    Team team=new Team("CFR Cluj","CFR","Romania","Cluj-Napoca",1927,40,45000000);
    Team team1=new Team("Steaua Bucuresti","FCSB","Romania","Bucharest",1947,40,12500);
    @BeforeEach
      void  setUp()
    {
        playerRepositoryMemory.add(player);
        playerRepositoryMemory.add(player1);
        teamRepositoryMemory.add(team);
        team.addPlayerToTeam(player);
        team.addPlayerToTeam(player1);


    }

    @Test
    void sortAllPlayersByPrice() {
        List<Player>players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.sortAllPlayersByPrice();
        assertEquals(players,sortedPlayer);
    }

    @Test
    void sortPlayersFromSpecificTeamByPrice() {
        List<Player>players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.sortPlayersFromSpecificTeamByPrice(team);
        assertEquals(players,sortedPlayer);
        List <Team> teams = new ArrayList<>();
    }

    @Test
    void printAllPlayers() {
    }

    @Test
    void sortPlayersByStatus() {
        List <Player> players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        assertEquals(players,playerController.sortPlayersByStatus("Playing"));

    }

    @Test
    void testSortAllPlayersByPrice() {
        List<Player>players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.sortAllPlayersByPrice();
        assertEquals(players,sortedPlayer);
    }

    @Test
    void testSortPlayersFromSpecificTeamByPrice() {
        List<Player>players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.sortPlayersFromSpecificTeamByPrice(team);
        assertEquals(players,sortedPlayer);
    }

    @Test
    void testPrintAllPlayers() {
        List <Player> players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        assertEquals(players,playerController.printAllPlayers());
    }

    @Test
    void SortPlayersByStatus() {
        playerRepositoryMemory.add(player2);
        List<Player>players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer=playerController.sortPlayersByStatus("Playing");
        assertEquals(players,sortedPlayer);
        List <Player> players1=new ArrayList<>();
        players1.add(player2);
        assertEquals(players1,playerController.sortPlayersByStatus("Free"));
    }

    @Test
    void sortAllPlayersByAge() {
        List<Player>players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.sortAllPlayersByAge();
        assertEquals(players,sortedPlayer);
    }

    @Test
    void sortAllPlayersByPosition() {
        List<Player>players=new ArrayList<>();
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.sortAllPlayersByPosition("Forward");
        assertEquals(players,sortedPlayer);

    }

    @Test
    void sortPlayersFromSpecificTeamByPosition() {
        List<Player>players=new ArrayList<>();
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.sortPlayersFromSpecificTeamByPosition(team,"Forward");
        assertEquals(players,sortedPlayer);

    }

    @Test
    void sortAllPlayersByNationality() {
        List<Player>players=new ArrayList<>();
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.sortAllPlayersByNationality("Germany");
        assertEquals(players,sortedPlayer);
    }

    @Test
    void sortAllPlayersByName() {
        List<Player>players=new ArrayList<>();
        players.add(player);
        players.add(player1);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.sortAllPlayersByName();
        assertEquals(players,sortedPlayer);
    }

    @Test
    void seeAllOtherPlayersWithoutYourself() {
        List<Player>players=new ArrayList<>();
        players.add(player);
        List<Player> sortedPlayer;
        sortedPlayer = playerController.seeAllOtherPlayersWithoutYourself(player1);
        assertEquals(players,sortedPlayer);
    }

    @Test
    void isAffordable() {
        List <Team> teams=new ArrayList<>();
        teams.add(team);
        List<Team> sortedTeams;
        sortedTeams= playerController.isAffordable(player);
        assertEquals(teams,sortedTeams);
    }
}