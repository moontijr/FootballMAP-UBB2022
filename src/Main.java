import controller.*;
import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;
import view.UI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Team team1=new Team("CFR Cluj", "CFR", "Romania", "Cluj",1907,40,2000000);
//        Team team2=new Team("U Cluj", "U", "Romania", "Cluj",1919,40,8000000);
//        Team team3=new Team ("Gaz Metan Medias", "GMM", "Romania", "Medias", 1977,35,20000);
//        Sponsor sponsor1=new Sponsor("NTTData","NTT",200000000);
//        Sponsor sponsor2=new Sponsor("Nike","Nk",250000000);
//        Coach coach1=new Coach("Vlad","Pasca",20,"Romania","Offensive",team1);
//        coach1.setTeam(team1);
//        Coach coach2=new Coach("Tudor","Muntean",19,"Romania","Offensive",team2);
//        coach2.setTeam(team2);
//        Player player1=new Player("Ciprian","Deac",34,"Germany","Goalkeeper",1250000);
//        Player player2=new Player("Octavian","Popescu",20,"Romania","Forward",10000000);
//        Player player3=new Player("Ovidiu","Bic",34,"Romania","Forward",1250000);
//        Player player4=new Player("Ovidiu","Popescu",34,"Germany","Forward",1250000);

        PlayerRepositoryMemory playerRepositoryMemory = PlayerRepositoryMemory.getInstance();
        CoachRepositoryMemory coachRepositoryMemory = CoachRepositoryMemory.getInstance();
        TeamRepositoryMemory teamRepositoryMemory = TeamRepositoryMemory.getInstance();
        SponsorRepositoryMemory sponsorRepositoryMemory = SponsorRepositoryMemory.getInstance();
        Scanner scanner = new Scanner(System.in);
        PlayerController playerController = new PlayerController(playerRepositoryMemory, teamRepositoryMemory);
        UI ui = new UI(scanner, playerRepositoryMemory, sponsorRepositoryMemory, teamRepositoryMemory,
                coachRepositoryMemory, playerController, new TeamController(teamRepositoryMemory),
                new CoachController(coachRepositoryMemory, playerRepositoryMemory),
                new SponsorController(sponsorRepositoryMemory, teamRepositoryMemory));
        ui.loginMenu();
    }
}