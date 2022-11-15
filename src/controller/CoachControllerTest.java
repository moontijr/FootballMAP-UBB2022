package controller;

import Model.Coach;
import Model.Player;
import Model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoachControllerTest {

    PlayerRepositoryMemory playerRepositoryMemory = new PlayerRepositoryMemory();
    CoachRepositoryMemory coachRepositoryMemory=new CoachRepositoryMemory();
    CoachController coachController=new CoachController(coachRepositoryMemory,playerRepositoryMemory);
    Player player1=new Player("Ciprian","Deac",34,"Germany","Goalkeeper",1250000);
    Player player2=new Player("Octavian","Popescu",20,"Romania","Forward",10000000);
    Player player3=new Player("Ovidiu","Bic",34,"Romania","Forward",1250000);
    Player player4=new Player("Ovidiu","Popescu",34,"Germany","Forward",1250000);
    Team team1=new Team("CFR Cluj", "CFR", "Romania", "Cluj",1907,40,2000000);
    Team team2=new Team("U Cluj", "U", "Romania", "Cluj",1919,40,8000000);

    Coach coach1=new Coach("Vlad","Pasca",20,"Romania","Deffensive",team1);
    Coach coach2=new Coach("Tudor","Muntean",19,"Romania","Offensive",team2);

    @BeforeEach
    void setUp()
    {
        team1.addPlayerToTeam(player1);
        team1.addPlayerToTeam(player2);
        team2.addPlayerToTeam(player3);
        team2.addPlayerToTeam(player4);
        coachRepositoryMemory.add(coach1);
        coachRepositoryMemory.add(coach2);
        playerRepositoryMemory.add(player1);
        playerRepositoryMemory.add(player2);
        playerRepositoryMemory.add(player3);
        playerRepositoryMemory.add(player4);

    }

    @Test
    void listYourSquadAsACoach() {
        List<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        List<Player> players1=coachController.listYourSquadAsACoach(coach1);
        assertEquals(players,players1);
    }

    @Test
    void listAllPlayersOutsideYourTeam() {
        List<Player> players=new ArrayList<>();
        players.add(player3);
        players.add(player4);
        List<Player> players1=coachController.listAllPlayersOutsideYourTeam(coach1);
        assertEquals(players,players1);
    }

    @Test
    void sortCoachTeamByValue() {
        List<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        List<Player> players1=coachController.sortCoachTeamByValue(coach1);
        assertEquals(players,players1);
    }

    @Test
    void sortCoachTeamByAge() {
        List<Player> players=new ArrayList<>();
        players.add(player2);
        players.add(player1);
        List<Player> players1=coachController.sortCoachTeamByAge(coach1);
        assertEquals(players,players1);
    }

    @Test
    void addPlayer() {
        assertFalse(coachController.addPlayer(coach1,player1));
        assertFalse(coachController.addPlayer(coach1,player2));
        assertTrue(coachController.addPlayer(coach1,player3));
        assertTrue(coachController.addPlayer(coach1,player4));
    }

    @Test
    void transferPlayer() {
        assertFalse(coachController.transferPlayer(coach1,player3,team1));
        assertTrue(coachController.transferPlayer(coach1,player3,team2));
        assertFalse(coachController.transferPlayer(coach1,player3,team2));
    }

    @Test
    void removePlayer() {
        assertTrue(coachController.removePlayer(coach1,player1));
        assertFalse(coachController.removePlayer(coach1,player1));
        assertFalse(coachController.removePlayer(coach1,player3));
        assertTrue(coachController.removePlayer(coach2,player4));
    }

    @Test
    void printAllCoaches() {
        List<Coach> coaches=new ArrayList<>(coachRepositoryMemory.getAllCoaches());
        assertEquals(coaches,coachController.printAllCoaches());
    }

    @Test
    void sortAllCoachesByAge() {
        List <Coach> coaches=new ArrayList<>();
        coaches.add(coach2);
        coaches.add(coach1);
        assertEquals(coaches,coachController.sortAllCoachesByAge());
    }

    @Test
    void sortAllCoachessByNationality() {
        List <Coach> coaches=new ArrayList<>();
        coaches.add(coach1);
        coaches.add(coach2);
        assertEquals(coaches,coachController.sortAllCoachessByNationality("Romania"));
    }

    @Test
    void sortAllCoachesByName() {
        List <Coach> coaches=new ArrayList<>();
        coaches.add(coach2);
        coaches.add(coach1);
        assertEquals(coaches,coachController.sortAllCoachesByName());
    }

    @Test
    void sortAllCoachessByPlayStyle() {
        List <Coach> coaches=new ArrayList<>();
        coaches.add(coach1);
        assertEquals(coaches,coachController.sortAllCoachessByPlayStyle("Deffensive"));
    }
}