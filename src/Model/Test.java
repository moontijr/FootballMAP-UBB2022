//import java.util.Scanner;
//
//public class Test {
//    Repository repository=new Repository();
//    Team team1=new Team("CFR Cluj", "CFR", "Romania", "Cluj",1907,40,2000000);
//        repository.registerSquad(team1);
//    Team team2=new Team("U Cluj", "U", "Romania", "Cluj",1919,40,8000000);
//        repository.registerSquad(team2);
//    Sponsor sponsor1=new Sponsor("NTTData",200000000);
//    Sponsor sponsor2=new Sponsor("Nike",250000000);
//        repository.registerSponsor(sponsor1);
//        repository.registerSponsor(sponsor2);
//    Coach coach1=new Coach("Vlad","Pasca",20,"Romania","Offensive",team1);
//        coach1.setTeam(team1);
//    Coach coach2=new Coach("Tudor","Muntean",19,"Romania","Offensive",team2);
//        coach2.setTeam(team2);
//        repository.registerCoach(coach1);
//        repository.registerCoach(coach2);
//    Player player1=new Player("Ciprian","Deac",34,"Germany","Goalkeeper",1250000);
//    Player player2=new Player("Octavian","Popescu",20,"Romania","Forward",10000000);
//    Player player3=new Player("Ovidiu","Bic",34,"Romania","Forward",1250000);
//    Player player4=new Player("Ovidiu","Popescu",34,"Germany","Forward",1250000);
//        repository.registerPlayer(player1);
//        repository.registerPlayer(player2);
//        repository.registerPlayer(player3);
//        repository.registerPlayer(player4);
////        for(Player player: repository.allPlayers)
////            System.out.println(player.getFirstName());
//        team1.addPlayerToTeam(player1);
//        team1.addPlayerToTeam(player2);
//        team2.addPlayerToTeam(player3);
//    Controller controller=new Controller(repository);
////        System.out.println(team1.getBudget());
////        System.out.println(player1.getStatus());
////        team2.transferPlayerToTeam(player1,team1);
////        System.out.println(team2.getBudget());
////        System.out.println(player1.getStatus());
////        System.out.println(team1.getBudget());
////        team2.removePlayerFromTeam(player1);
////        System.out.println(player1.getStatus());
////        team1.addPlayerToTeam(player2);
////        team2.transferPlayerToTeam(player2,team1);
////         team2.addPlayerToTeam(player2);
////         team1.transferPlayerToTeam(player2,team2);
////         team2.removePlayerFromTeam(player2);
////         System.out.println(player2.getStatus());
////         team1.printTeam();
////        System.out.println(repository.allPlayers);
////          controller.sortAllPlayersByPrice();
////         .sortPlayersFromSpecificTeamByPrice(team1);
////         player2.printPlayer();
////         controller.sortPlayersByStatus();
////         repository.sortAllPlayersByAge();
////        repository.sortAllPlayersByNationality("Romania");
////        repository.sortAllPlayersByName();
////        controller.isAffordable(player3);
////        team1.setBudget(1250);
////        controller.isAffordable(player3);
////        sponsor1.sponsorTeam(team1);
//        sponsor2.sponsorTeam(team2);
//    //        controller.sortTeamsByBudget(sponsor2);
////        controller.listYourSquadAsACoach(coach2);
////        controller.listAllPlayersOutsideYourTeam(coach2);
//    Scanner scanner=new Scanner(System.in);
//    UI ui = new UI(scanner,repository,controller);
//        ui.startMenu();
////        System.out.println("Tell us your details");
////        System.out.println("Age: ");
////        int age= scanner.nextInt();
////        scanner.nextLine();
////        System.out.println("First Name: ");
////        String firstName = scanner.nextLine();
////        System.out.println("Last Name: ");
////        String lastName = scanner.nextLine();
//
//



