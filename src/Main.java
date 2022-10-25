import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Repository repository=new Repository();
        Team team1=new Team("CFR Cluj", "CFR", "Romania", "Cluj",1907,40,2000000);
        repository.registerSquad(team1);
        Team team2=new Team("U Cluj", "U", "Romania", "Cluj",1919,40,8000000);
        repository.registerSquad(team2);
        Team team3=new Team ("Gaz Metan Medias", "GMM", "Romania", "Medias", 1977,35,20000);
        repository.registerSquad(team3);
        Sponsor sponsor1=new Sponsor("NTTData",200000000);
        Sponsor sponsor2=new Sponsor("Nike",250000000);
        repository.registerSponsor(sponsor1);
        repository.registerSponsor(sponsor2);
        Coach coach1=new Coach("Vlad","Pasca",20,"Romania","Offensive",team1);
        coach1.setTeam(team1);
        Coach coach2=new Coach("Tudor","Muntean",19,"Romania","Offensive",team2);
        coach2.setTeam(team2);
        repository.registerCoach(coach1);
        repository.registerCoach(coach2);
        Player player1=new Player("Ciprian","Deac",34,"Germany","Goalkeeper",1250000);
        Player player2=new Player("Octavian","Popescu",20,"Romania","Forward",10000000);
        Player player3=new Player("Ovidiu","Bic",34,"Romania","Forward",1250000);
        Player player4=new Player("Ovidiu","Popescu",34,"Germany","Forward",1250000);
        repository.registerPlayer(player1);
        repository.registerPlayer(player2);
        repository.registerPlayer(player3);
        repository.registerPlayer(player4);
        Controller controller=new Controller(repository);
        Scanner scanner=new Scanner(System.in);
        UI ui = new UI(scanner,repository,controller);
        ui.startMenu();

    }
}