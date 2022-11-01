package controller;

import Model.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {

    @BeforeAll
    void setUp()
    {
        PlayerRepositoryMemory playerRepositoryMemory = new PlayerRepositoryMemory();
        TeamRepositoryMemory teamRepositoryMemory = new TeamRepositoryMemory();
        PlayerController playerController = new PlayerController(playerRepositoryMemory,teamRepositoryMemory);
        Player player=new Player("Ciprian","Deac",34,"Germany","Goalkeeper",10000);
        Player player1=new Player("Ciprian","Petre",35,"Germany","Goalkeeper",120000);
        playerRepositoryMemory.add(player);
        playerRepositoryMemory.add(player1);

    }

    @Test
    void sortAllPlayersByPrice() {

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
    void testSortAllPlayersByPrice() {
    }

    @Test
    void testSortPlayersFromSpecificTeamByPrice() {
    }

    @Test
    void testPrintAllPlayers() {
    }

    @Test
    void testSortPlayersByStatus() {
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
    void seeAllOtherPlayersWithoutYourself() {
    }

    @Test
    void isAffordable() {
    }
}