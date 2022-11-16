import Model.*;
import controller.CoachController;
import controller.PlayerController;
import controller.SponsorController;
import controller.TeamController;
import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;
import view.UI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Repository repository=new Repository();
//        Controller controller=new Controller(repository);
        Team team1=new Team("CFR Cluj", "CFR", "Romania", "Cluj",1907,40,2000000);
//        repository.registerSquad(team1);
        Team team2=new Team("U Cluj", "U", "Romania", "Cluj",1919,40,8000000);
//        repository.registerSquad(team2);
        Team team3=new Team ("Gaz Metan Medias", "GMM", "Romania", "Medias", 1977,35,20000);
//        repository.registerSquad(team3);
        Sponsor sponsor1=new Sponsor("NTTData","NTT",200000000);
        Sponsor sponsor2=new Sponsor("Nike","Nk",250000000);
//        repository.registerSponsor(sponsor1);
//        repository.registerSponsor(sponsor2);
        Coach coach1=new Coach("Vlad","Pasca",20,"Romania","Offensive",team1);
        coach1.setTeam(team1);
        Coach coach2=new Coach("Tudor","Muntean",19,"Romania","Offensive",team2);
        coach2.setTeam(team2);
//        repository.registerCoach(coach1);
//        repository.registerCoach(coach2);
        Player player1=new Player("Ciprian","Deac",34,"Germany","Goalkeeper",1250000);
        Player player2=new Player("Octavian","Popescu",20,"Romania","Forward",10000000);
        Player player3=new Player("Ovidiu","Bic",34,"Romania","Forward",1250000);
        Player player4=new Player("Ovidiu","Popescu",34,"Germany","Forward",1250000);
//        repository.registerPlayer(player1);
//        repository.registerPlayer(player2);
//        repository.registerPlayer(player3);
//        repository.registerPlayer(player4);
//        Controller controller=new Controller(repository);
//        Scanner scanner=new Scanner(System.in);
 //       UI ui = new UI(scanner,repository,controller);
       // team2.addPlayerToTeam(player2);
//        System.out.println(team2.squad);
//        System.out.println(player2.getStatus());
        //System.out.println(repository.allTeams);
        //repository.deleteSquad(team2);
        //System.out.println(team2.squad);
        //System.out.println(player2.getStatus());
        //System.out.println(repository.allTeams);
//        team1.addPlayerToTeam(player2);
//        System.out.println(repository.allPlayers);
//        System.out.println(team1.squad);
//        repository.deletePlayer(player2);
//        System.out.println(team1.squad);
//        System.out.println(player2.getFirstName());
//        System.out.println(repository.allPlayers);
//        repository.updatePlayer(player1);
        //for(Player player:repository.allPlayers)
          //  player.printPlayer();
//        PlayerRepositoryMemory playerRepositoryMemory=new PlayerRepositoryMemory();
//        playerRepositoryMemory.add(player1);
//        TeamRepositoryMemory teamRepositoryMemory=new TeamRepositoryMemory();
//        teamRepositoryMemory.add(team1);
//        team1.addPlayerToTeam(player1);
//        playerRepositoryMemory.add(player3);
//        for(Player player: playerRepositoryMemory.getAllPlayers())
//            player.printPlayer();
//        for (Team team: teamRepositoryMemory.getAllTeams())
//            team.printTeam();
        //for (Player player : team1.squad)
          //  player.printPlayer();
//        teamRepositoryMemory.remove("CFR Cluj","CFR");
//        for(Player player: playerRepositoryMemory.getAllPlayers())
//            player.printPlayer();
        //for(Player player: playerRepositoryMemory.getAllPlayers())
          //  player.printPlayer();

        //playerRepositoryMemory.update("12",player1);
        //ui.startMenu();
        PlayerRepositoryMemory playerRepositoryMemory=new PlayerRepositoryMemory();
        CoachRepositoryMemory coachRepositoryMemory=new CoachRepositoryMemory();
        TeamRepositoryMemory teamRepositoryMemory=new TeamRepositoryMemory();
        SponsorRepositoryMemory sponsorRepositoryMemory=new SponsorRepositoryMemory();
//        System.out.println(playerRepositoryMemory.getAllPlayers());
        playerRepositoryMemory.add(player1);
//        System.out.println(playerRepositoryMemory.getAllPlayers());
//        playerRepositoryMemory.add(player1);
//        for(Player player : playerRepositoryMemory.getAllPlayers())
//            player.printPlayer();
        teamRepositoryMemory.add(team1);
        teamRepositoryMemory.add(team2);
//        for(Team team: teamRepositoryMemory.getAllTeams())
//            team.printTeam();
//        sponsorRepository.add(sponsor1);
//        sponsorRepository.add(sponsor2);
        sponsor2.sponsorTeam(team1);
        sponsor1.sponsorTeam(team1);
//        for(Team team: teamRepositoryMemory.getAllTeams())
//            System.out.println(team.sponsors);
        //ontroller controller=new Controller(playerRepositoryMemory,coachRepositoryMemory,teamRepositoryMemory,sponsorRepositoryMemory);

        Scanner scanner=new Scanner(System.in);
        //UI ui = new UI(scanner,playerRepositoryMemory,sponsorRepositoryMemory,teamRepositoryMemory,coachRepositoryMemory,controller);
        //ui.loginMenu();
        PlayerController playerController = new PlayerController(playerRepositoryMemory,teamRepositoryMemory);
        UI ui = new UI(scanner,playerRepositoryMemory,sponsorRepositoryMemory,teamRepositoryMemory,
                coachRepositoryMemory,playerController,new TeamController(teamRepositoryMemory),
                new CoachController(coachRepositoryMemory,playerRepositoryMemory),
                new SponsorController(sponsorRepositoryMemory,teamRepositoryMemory));
        ui.startMenu();
    }
}