package view;

import Model.*;
import controller.PlayerController;
import controller.CoachController;
import controller.TeamController;
import controller.SponsorController;
import repository.TeamRepository;
import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private Scanner userInput;
    private PlayerRepositoryMemory playerRepositoryMemory;
    private SponsorRepositoryMemory sponsorRepositoryMemory;
    private TeamRepositoryMemory teamRepositoryMemory;
    private CoachRepositoryMemory coachRepositoryMemory;

    //private Controller controller;

    private PlayerController playerController;
    private TeamController teamController;
    private CoachController coachController;
    private SponsorController sponsorController;

//    public UI(Scanner scanner, PlayerRepositoryMemory playerRepositoryMemory, SponsorRepositoryMemory sponsorRepositoryMemory, TeamRepositoryMemory teamRepositoryMemory, CoachRepositoryMemory coachRepositoryMemory, Controller controller) {
//        this.userInput = scanner;
//        this.playerRepositoryMemory = playerRepositoryMemory;
//        this.sponsorRepositoryMemory = sponsorRepositoryMemory;
//        this.teamRepositoryMemory = teamRepositoryMemory;
//        this.coachRepositoryMemory = coachRepositoryMemory;
//        this.controller = controller;
//    }


    public UI(Scanner userInput, PlayerRepositoryMemory playerRepositoryMemory, SponsorRepositoryMemory sponsorRepositoryMemory, TeamRepositoryMemory teamRepositoryMemory, CoachRepositoryMemory coachRepositoryMemory, PlayerController playerController, TeamController teamController, CoachController coachController, SponsorController sponsorController) {
        this.userInput = userInput;
        this.playerRepositoryMemory = playerRepositoryMemory;
        this.sponsorRepositoryMemory = sponsorRepositoryMemory;
        this.teamRepositoryMemory = teamRepositoryMemory;
        this.coachRepositoryMemory = coachRepositoryMemory;
        this.playerController = playerController;
        this.teamController = teamController;
        this.coachController = coachController;
        this.sponsorController = sponsorController;
    }

    // private Scanner scanner = new Scanner(System.in);


    /***
     * method to be improved
     * */
    public void loginMenu() {
        System.out.println("""
                Welcome to our football database!
                Please enter your credentials:
                """);
        System.out.println("Username: ");
        String username = this.userInput.nextLine();
        System.out.println("Password: ");
        String password = this.userInput.nextLine();
        String credentials = username + password;
        switch (credentials) {
            case "player1000" -> {
                System.out.println("You are currently in Player Mode.");
                this.playerMenu();
            }
            case "coach2000" -> {
                System.out.println("You are currently in Coach Mode.");
                this.coachMenu();
            }
            case "sponsor3000" -> {
                System.out.println("You are currently in Sponsor Mode.");
                this.sponsorMenu();
            }
            case "admin1234" -> {
                System.out.println("You are currently in Admin Mode.");
                this.adminDBsMenu();
            }
            default -> {
                System.out.println("You are currently in Guest Mode.");
                this.dataBasesMenu();
            }
        }
    }

    public void startMenu() {
        System.out.println("""
                Welcome to our football database!
                Which user mode do you choose? (Please choose a number between 1 and 6)
                1.Player
                2.Coach
                3.Sponsor
                4.Guest
                5.Admin
                6.Exit
                """);


        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 7)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 6!");
                choice = userInput.nextInt();
            }
        }
        switch (choice) {
            case 1 -> {
                System.out.println("You are currently in Player Mode.");
                this.playerMenu();
            }
            case 2 -> {
                System.out.println("You are currently in Coach Mode.");
                this.coachMenu();
            }
            case 3 -> {
                System.out.println("You are currently in Sponsor Mode.");
                this.sponsorMenu();
            }
            case 4 -> {
                System.out.println("You are currently in Guest Mode.");
                this.dataBasesMenu();
            }
            case 5 -> {
                System.out.println("You are currently in Admin Mode.");
                this.adminDBsMenu();
            }
            case 6 -> System.exit(0);
        }

    }

    public void playerMenu() {
        System.out.println("""
                Do you want to register yourself in our database? Please choose a number between 1 and 2
                1. Yes
                2. I am already there
                """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 3)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 2!");
                choice = userInput.nextInt();
            }
        }
        switch (choice) {
            case 1 -> {
                System.out.println("Tell us your details");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                System.out.println("Age: ");
                int age = this.userInput.nextInt();
                this.userInput.nextLine();
                System.out.println("Nationality: ");
                String nationality = this.userInput.nextLine();
                System.out.println("Position: ");
                String position = this.userInput.nextLine();
                System.out.println("Market value: ");
                int marketValue = this.userInput.nextInt();
                Player newPlayer = new Player(firstName, lastName, age, nationality, position, marketValue);
                if (!playerRepositoryMemory.existsPlayer(firstName, lastName))
                    this.playerRepositoryMemory.add(newPlayer);
                else System.out.println("Player is already there");
                subMenuPlayer(newPlayer);
            }
            case 2 -> {
                System.out.println("Great! Tell us your name:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                //do we still need this if we have findByID in repo?
                for (Player player : playerRepositoryMemory.getAllPlayers()) {
                    if (player.getFirstName().contains(firstName) && player.getLastName().contains(lastName))
                        subMenuPlayer(player);
                }
                System.out.println("There is no player with that name. Please try again with a different name");
                this.playerMenu();
            }
        }

    }

    public void subMenuPlayer(Player player) {
        System.out.println("""
                You have the following options:
                1. See all other players
                2. See teams that could afford you
                3. Go back
                4. Exit
                """);
        int choice2 = this.userInput.nextInt();
        boolean validator2 = false;

        while (!validator2) {
            if (choice2 > 0 && choice2 < 5)
                validator2 = true;
            else {
                System.out.println("Please choose a number between 1 and 4!");
                choice2 = userInput.nextInt();
            }
        }
        switch (choice2) {
            case 1:
                List<Player> allOtherPlayers = playerController.seeAllOtherPlayersWithoutYourself(player);
                if (allOtherPlayers.size() == 0)
                    System.out.println("There are no players");
                for (Player otherPlayer : allOtherPlayers) {
                    player.printPlayer();
                }
                this.subMenuPlayer(player);
            case 2:
                //show potential teams
                List<Team> potentialTeams = new ArrayList<>();
                potentialTeams = playerController.isAffordable(player);
                if (potentialTeams == null)
                    System.out.println("There are no teams");
                else {
                    for (Team team : potentialTeams) {
                        team.printTeam();
                    }
                }

                this.subMenuPlayer(player);
            case 3:
                this.startMenu();
            case 4:
                System.exit(0);
        }

    }

    private void coachMenu() {
        System.out.println("""
                Do you want to register yourself in our database? Please choose a number between 1 and 2
                1. Yes
                2. I am already there
                """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 3)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 2!");
                choice = userInput.nextInt();
            }
        }
        switch (choice) {
            case 1 -> {
                System.out.println("Tell us your details");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                System.out.println("Age: ");
                int age = this.userInput.nextInt();
                this.userInput.nextLine();
                System.out.println("Nationality: ");
                String nationality = this.userInput.nextLine();
                System.out.println("Playstyle: ");
                String playstyle = this.userInput.nextLine();
                System.out.println("Your current team(abbreviation): ");
                String myTeam = this.userInput.nextLine();
                int counter = 0;
                for (Team team : teamRepositoryMemory.getAllTeams())
                    if (team.getAbbreviation().contains(myTeam))
                        counter++;
                if (counter == 0) {
                    System.out.println("There is no team with such an abreviation in our database");
                    this.coachMenu();
                } else {
                    Team newTeam = null;
                    for (Team team : teamRepositoryMemory.getAllTeams())
                        if (team.getAbbreviation().contains(myTeam))
                            newTeam = team;

                    Coach newCoach = new Coach(firstName, lastName, age, nationality, playstyle, newTeam);
                    if (!this.coachRepositoryMemory.existsCoach(newCoach.getFirstName(), newCoach.getLastName()))
                        coachRepositoryMemory.add(newCoach);
                    else

                        System.out.println("Coach already exists");


                    this.subMenuCoach(newCoach);
                }
            }
            case 2 -> {
                System.out.println("Great! Tell us your name:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                for (Coach coach : coachRepositoryMemory.getAllCoaches()) {
                    if (coach.getFirstName().contains(firstName) && coach.getLastName().contains(lastName))
                        subMenuCoach(coach);
                }
                System.out.println("There is no coach with that name, please try again with a different name");
                this.coachMenu();
                //search by name?
                //this.subMenuCoach(newCoach);
            }

        }

    }

    private void subMenuCoach(Coach coach) {
        System.out.println("""
                You have the following options. Please choose a number between 1 and 8:
                1. See your squad(if you coach one currently)
                2. See players outside your team
                3. Sort your players by value
                4. Sort player by position
                5. Sign a player for your team
                6. Sign a free agent player
                7. Release a player from your team
                8. Go back
                9. Exit
                """);

        int choice2 = this.userInput.nextInt();
        boolean validator2 = false;

        while (!validator2) {
            if (choice2 > 0 && choice2 <= 9)
                validator2 = true;
            else {
                System.out.println("Please choose a number between 1 and 8!");
                choice2 = userInput.nextInt();
            }
        }
        switch (choice2) {
            case 1:
                //see full squad
                List<Player> myTeam = coachController.listYourSquadAsACoach(coach);
                if (myTeam.size() == 0)
                    System.out.println("There are no players");
                for (Player player : myTeam) {
                    player.printPlayer();
                }
                this.subMenuCoach(coach);
                break;
            case 2:
                //see all other players
                List<Player> otherPlayers = coachController.listAllPlayersOutsideYourTeam(coach);
                ;
                if (otherPlayers.size() == 0)
                    System.out.println("There are no players");
                for (Player player : otherPlayers) {
                    player.printPlayer();
                }
                this.subMenuCoach(coach);
                break;
            case 3:
                List<Player> myTeamSorted = playerController.sortPlayersFromSpecificTeamByPrice(coach.getTeam());
                if (myTeamSorted.size() == 0)
                    System.out.println("There are no players");
                for (Player player : myTeamSorted) {
                    player.printPlayer();
                }
                this.subMenuCoach(coach);
                break;
            case 4:
                //sort players by position
                System.out.println("Please choose a position");
                String inputString1 = userInput.nextLine();
                List<Player> myTeamSpecificPosition = playerController.sortPlayersFromSpecificTeamByPosition(coach.getTeam(), inputString1);
                if (myTeamSpecificPosition.size() == 0)
                    System.out.println("There are no players");
                for (Player player : myTeamSpecificPosition) {
                    player.printPlayer();
                }
                this.subMenuCoach(coach);
                break;
            case 5:
                //sign a player
                Player player1 = null;
                Team team1 = null;
                System.out.println("Tell us the details of the followed player:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                System.out.println("Tell us the team that the followed player is currently playing at");
                String hisCurrentTeam = this.userInput.nextLine();
                for (Player player : playerRepositoryMemory.getAllPlayers())
                    if (player.getFirstName().contains(firstName) && player.getLastName().contains(lastName))
                        player1 = player;
                for (Team team : teamRepositoryMemory.getAllTeams())
                    if (team.getAbbreviation().contains(hisCurrentTeam))
                        team1 = team;
                if (coach.getTeam().getSquad().size() >= coach.getTeam().getMaxSquadSize()) {
                    System.out.println("No more places in the squad");
                } else if (coach.getTeam().getBudget() < player1.getMarketValue()) {
                    System.out.println("Budget is too low");
                }
                coach.getTeam().transferPlayerToTeam(player1, team1);


//                System.out.println("Age: ");
//                int age = this.userInput.nextInt();
//                System.out.println("Nationality: ");
//                String nationality = this.userInput.nextLine();
//                System.out.println("Position: ");
//                String position = this.userInput.nextLine();
//                System.out.println("Market value: ");
//                int marketValue = this.userInput.nextInt();
                //search the player
                //add to the team

                this.subMenuCoach(coach);
                break;

            case 6:
                Player player2 = null;
                Team team2 = null;
                System.out.println("Tell us the details of the followed player:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName2 = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName2 = this.userInput.nextLine();
                for (Player player : playerRepositoryMemory.getAllPlayers())
                    if (player.getFirstName().contains(firstName2) && player.getLastName().contains(lastName2))
                        player2 = player;
                coach.getTeam().addPlayerToTeam(player2);
                this.subMenuCoach(coach);


            case 7:
                System.out.println("Tell us the details of player you want to release:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName3 = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName3 = this.userInput.nextLine();

                Player player3 = null;
                for (Player player : playerRepositoryMemory.getAllPlayers())
                    if (player.getFirstName().contains(firstName3) && player.getLastName().contains(lastName3))
                        player3 = player;
                if (coach.getTeam().removePlayerFromTeam(player3)) {
                    System.out.println("Player Removed");
                } else {
                    System.out.println("Player wasn't in the squad");
                }
                ;
//                System.out.println("Age: ");
//                int age2 = this.userInput.nextInt();
//                System.out.println("Nationality: ");
//                String nationality2 = this.userInput.nextLine();
//                System.out.println("Position: ");
//                String position2 = this.userInput.nextLine();
//                System.out.println("Market value: ");
//                int marketValue2 = this.userInput.nextInt();

                //search the player
                //removes from squad
                //change player status
                this.subMenuCoach(coach);
                break;
            case 8:
                this.startMenu();
            case 9:
                System.exit(0);
        }
    }


    private void sponsorMenu() {
        System.out.println("""
                Do you want to register your firm in our database? Please choose a number between 1 and 2
                1. Yes
                2. It's already there
                """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 3)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 2!");
                choice = userInput.nextInt();
            }
        }
        switch (choice) {
            case 1:
                System.out.println("Tell us the firm's details:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                System.out.println("Net worth: ");
                int netWorth = this.userInput.nextInt();
                Sponsor newSponsor = new Sponsor(name, abbreviation, netWorth);
                if (!this.sponsorRepositoryMemory.existsSponsor(name, abbreviation))
                    sponsorRepositoryMemory.add(newSponsor);
                else
                    System.out.println("Sponsor already exists");
                this.subMenuSponsor(newSponsor);
            case 2:
                System.out.println("Great! Tell us your name of the firm");
                this.userInput.nextLine();
                String nameOfTheFirm = this.userInput.nextLine();
                for (Sponsor sponsor1 : sponsorRepositoryMemory.getAllSponsors())
                    if (sponsor1.getName().contains(nameOfTheFirm))
                        subMenuSponsor(sponsor1);
                //search sponsor
                //this.subMenuSponsor(newSponsor);
                break;
        }
    }

    private void subMenuSponsor(Sponsor sponsor) {
        System.out.println("""
                You have the following options. Please choose a number from 1 to 7:
                1. List all teams
                2. Sort teams by market value
                3. Start a sponsorship
                4. End a sponsorship
                5. Sort sponsored teams
                6. Go back
                7. Exit
                """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 8)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 7!");
                choice = userInput.nextInt();
            }
        }
        switch (choice) {
            case 1:
                List<Team> sameSponsorTeams = sponsorController.listAllTeamsFromASponsor(sponsor);
                if (sameSponsorTeams.size() == 0)
                    System.out.println("There are no sponsors");
                else {
                    for (Team team : sameSponsorTeams)
                        team.printTeam();
                }
                this.subMenuSponsor(sponsor);
                break;
            case 2:
                List<Team> sameSponsorTeamsSortedByValue = sponsorController.sortAllTeamsFromASponsorByMarketValue(sponsor);
                if (sameSponsorTeamsSortedByValue.size() == 0)
                    System.out.println("There are no sponsors");
                else {
                    for (Team team : sameSponsorTeamsSortedByValue)
                        team.printTeam();
                }
                this.subMenuSponsor(sponsor);
            case 3:
                //start sponsoring a team
                System.out.println("Please type the abreviation from the team you wish to sponsor");
                String inputString = userInput.nextLine();
                sponsorController.startSponsoring(sponsor, inputString);
                this.subMenuSponsor(sponsor);
                break;
            case 4:
                //end a sponsorship
                System.out.println("Please type the abreviation from the team you wish to stop sponsoring");
                String inputString1 = userInput.nextLine();
                sponsorController.endSponsoring(sponsor, inputString1);
                this.subMenuSponsor(sponsor);
            case 5:
                //sort sponsored teams
                List<Team> sponsoredTeams = teamController.sortSponsoredTeamsByBudget(sponsor);
                if (sponsoredTeams.size() == 0)
                    System.out.println("There are no teams.");
                else {
                    for (Team team : sponsoredTeams)
                        team.printTeam();
                }
                this.subMenuSponsor(sponsor);
            case 6:
                this.startMenu();
            case 7:
                System.exit(0);
        }

    }

    private void dataBasesMenu() {
        System.out.println("""
                Access one of the following databases(please choose a number from 1 to 4):
                1. Players
                2. Coaches
                3. Teams
                4. Sponsors
                5. Go back
                6. Exit
                 """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 7)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 6!");
                choice = userInput.nextInt();
            }
        }
        switch (choice) {
            case 1 -> {
                System.out.println("You are currently in the Players Database.");
                this.playerDBMenu();
            }
            case 2 -> {
                System.out.println("You are currently in the Coaches Database.");
                this.coachDBMenu();
            }
            case 3 -> {
                System.out.println("You are currently in the Teams Database.");
                this.teamsDBMenu();
            }
            case 4 -> {
                System.out.println("You are currently in the Sponsors Database.");
                this.sponsorDBMenu();
            }
            case 5 -> this.startMenu();

            case 6 -> System.exit(0);


        }
    }

    private void playerDBMenu() {
        System.out.println("""
                You have the following options(please choose a number from 1 to 9):
                1. List all players
                2. Sort players by value
                3. Sort players by position
                4. Sort players by status
                5. Sort players by age
                6. Sort players by nationality
                7. Sort players by name
                8. Go back
                9. Exit
                 """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 10)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 9!");
                choice = userInput.nextInt();
            }
        }

        switch (choice) {
            case 1:
                List<Player> allPlayers = playerController.printAllPlayers();
                if (allPlayers.size() == 0)
                    System.out.println("There are no players\n");
                for (Player player : allPlayers) {
                    player.printPlayer();
                }
                this.playerDBMenu();
                break;
            case 2:
                List<Player> sortedPlayers = playerController.sortAllPlayersByPrice();
                if (sortedPlayers.size() == 0)
                    System.out.println("There are no players\n");
                for (Player player : sortedPlayers) {
                    player.printPlayer();
                }
                this.playerDBMenu();
                break;
            case 3:
                //sort by position
                System.out.println("Please choose a position");
                String inputString1 = userInput.nextLine();
                List<Player> playersWithSelectedPosition = playerController.sortAllPlayersByPosition(inputString1);
                if (playersWithSelectedPosition.size() == 0)
                    System.out.println("There are no players\n");
                for (Player player : playersWithSelectedPosition) {
                    player.printPlayer();
                }
                this.playerDBMenu();
                break;
            case 4:
                System.out.println("Do you want to see the free agents?\n (Y/y, else, we will list players that have a team");
                String answer = this.userInput.nextLine();
                List<Player> statusPlayers = playerController.sortPlayersByStatus(answer);
                if (statusPlayers.size() == 0)
                    System.out.println("There are no players\n");
                for (Player player : statusPlayers) {
                    player.printPlayer();
                }
                this.playerDBMenu();
                //sort by status
                break;
            case 5:
                List<Player> playersByAge = playerController.sortAllPlayersByAge();
                if (playersByAge.size() == 0)
                    System.out.println("There are no players\n");
                for (Player player : playersByAge) {
                    player.printPlayer();
                }
                this.playerDBMenu();
                //by age
                break;
            case 6:
                System.out.println("Please choose a nationality");
                String inputString = userInput.nextLine();
                List<Player> specificCountry = playerController.sortAllPlayersByNationality(inputString);
                if (specificCountry.size() == 0)
                    System.out.println("There are no players");
                for (Player player : specificCountry) {
                    player.printPlayer();
                }
                this.playerDBMenu();
                break;
            case 7:
                List<Player> sortedByName = playerController.sortAllPlayersByName();
                if (sortedByName.size() == 0)
                    System.out.println("There are no players");
                for (Player player : sortedByName) {
                    player.printPlayer();
                }
                this.playerDBMenu();
                break;
            case 8:
                this.dataBasesMenu();
            case 9:
                System.exit(0);
        }
    }

    private void coachDBMenu() {
        System.out.println("""
                You have the following options(please choose a number from 1 to 9):
                1. List all coaches
                2. Sort coaches by playing style
                3. Sort coaches by age
                4. Sort coaches by status
                5. Sort coaches by nationality
                6. Sort coaches by name
                7. Go back
                8. Exit
                 """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 9)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 8!");
                choice = userInput.nextInt();
            }
        }

        switch (choice) {
            case 1:
                List<Coach> allCoaches = coachController.printAllCoaches();
                if (allCoaches.size() == 0)
                    System.out.println("There are no coaches");
                else {
                    for (Coach coach : allCoaches)
                        coach.printCoach();
                }
                this.coachDBMenu();
                //list all coaches
                break;
            case 2:
                this.dataBasesMenu();
                System.out.println("Please choose the playing style of the coaches you want to see");
                String playstyle = userInput.nextLine();
                List<Coach> allCoachesSortedByPlaystyle = coachController.sortAllCoachessByPlayStyle(playstyle);
                if (allCoachesSortedByPlaystyle.size() == 0)
                    System.out.println("There are no coaches");
                else {
                    for (Coach coach : allCoachesSortedByPlaystyle)
                        coach.printCoach();
                }
                //sort by playing style
                this.coachDBMenu();
                break;
            case 3:
                //sort by age
                List<Coach> allCoachesSortedByAge = coachController.sortAllCoachesByAge();
                if (allCoachesSortedByAge.size() == 0)
                    System.out.println("There are no coaches");
                else {
                    for (Coach coach : allCoachesSortedByAge)
                        coach.printCoach();
                }
                this.coachDBMenu();
                break;
            case 4:
                //sort by status
                this.coachDBMenu();
            case 5:
                //sort by nationality
                System.out.println("Please choose a nationality");
                String country = userInput.nextLine();
                List<Coach> allCoachesSortedByNationality = coachController.sortAllCoachessByNationality(country);
                if (allCoachesSortedByNationality.size() == 0)
                    System.out.println("There are no coaches");
                else {
                    for (Coach coach : allCoachesSortedByNationality)
                        coach.printCoach();
                }
                this.coachDBMenu();
                break;
            case 6:
                //sort by name
                List<Coach> allCoachesSortedByName = coachController.sortAllCoachesByName();
                if (allCoachesSortedByName.size() == 0)
                    System.out.println("There are no coaches");
                else {
                    for (Coach coach : allCoachesSortedByName)
                        coach.printCoach();
                }
                this.coachDBMenu();
                break;
            case 7:
                //
                this.dataBasesMenu();
            case 8:
                System.exit(0);
        }
    }

    private void teamsDBMenu() {
        System.out.println("""
                You have the following options. Please choose a number between 1 and 8:
                1. List all teams
                2. Sort teams by market value
                3. Sort teams by name
                4. Sort teams by country
                5. Sort teams by foundation year
                6. Sort by budget
                7. Go back
                8. Exit
                """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 9)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 8!");
                choice = userInput.nextInt();
            }
        }

        switch (choice) {
            case 1:
                teamController.printAllTeams();
                this.teamsDBMenu();
                //list all teams
                break;
            case 2:
                this.teamsDBMenu();
                //sort by market value
                break;
            case 3:
                //sort by name
                this.teamsDBMenu();
                break;
            case 4:
                System.out.println("Please choose the country ");
                String country = userInput.nextLine();
                List<Team> teamsFromCountry = teamController.sortAllTeamsByCountry(country);
                if (teamsFromCountry.size() == 0)
                    System.out.println("There are no teams from this country");
                else
                    for (Team team : teamsFromCountry)
                        team.printTeam();
                this.teamsDBMenu();
                break;
            case 5:
                List<Team> sortedByYear = teamController.sortAllTeamsByFoundationYear();
                if (sortedByYear.size() == 0)
                    System.out.println("There are no teams");
                else {
                    for (Team team : sortedByYear)
                        team.printTeam();
                }
                this.teamsDBMenu();
                break;
            case 6:
                List<Team> sortedByBudget = teamController.sortAllTeamsByBudget();
                if (sortedByBudget.size() == 0)
                    System.out.println("There are no teams");
                else {
                    for (Team team : sortedByBudget)
                        team.printTeam();
                }
                this.teamsDBMenu();
                break;
            case 7:
                this.dataBasesMenu();
            case 8:
                System.exit(0);
        }
    }

    private void sponsorDBMenu() {
        System.out.println("""
                You have the following options. Please choose a number between 1 and 7:
                1. List all sponsors
                2. Sort sponsors by name
                3. Sort sponsors by networth
                4. See all the teams with the same sponsor
                5. See all sponsors from a team
                6. Go back
                7. Exit
                """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 8)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 7!");
                choice = userInput.nextInt();
            }
        }

        switch (choice) {
            case 1:
                sponsorController.printAllSponsors();
                this.sponsorDBMenu();
                //list all sponsors
                break;
            case 2:
                List<Sponsor> sponsorsSortedByName = sponsorController.sortAllSponsorsByName();
                if (sponsorsSortedByName.size() == 0)
                    System.out.println("There are no sponsors");
                else {
                    for (Sponsor sponsor : sponsorsSortedByName)
                        sponsor.printSponsor();
                }
                this.sponsorDBMenu();
                break;
            case 3:
                List<Sponsor> sponsorsSortedByNetworth = sponsorController.sortAllSponsorsByNetWorth();
                if (sponsorsSortedByNetworth.size() == 0)
                    System.out.println("There are no sponsors");
                else {
                    for (Sponsor sponsor : sponsorsSortedByNetworth)
                        sponsor.printSponsor();
                }
                this.sponsorDBMenu();
                break;
            case 4:
                System.out.println("Tell us the sponsor's name:");
                String sponsor = this.userInput.nextLine();
                System.out.println("Sponsor abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                if (sponsorRepositoryMemory.existsSponsor(sponsor, abbreviation)) {
                    List<Team> sameSponsorTeams = teamController.getAllTeamsAffiliatedWithSponsor(sponsorRepositoryMemory.findById(sponsor, abbreviation));
                    for (Team team : sameSponsorTeams)
                        team.printTeam();
                } else {
                    System.out.println("There is no such sponsor.");
                }
                this.sponsorDBMenu();
                break;
            case 5:
                //all sponsors from a team(we give a team as a parameter)
                this.sponsorDBMenu();
                break;
            case 6:
                //go back
                this.dataBasesMenu();
            case 7:
                System.exit(0);
        }
    }


    private void adminDBsMenu() {
        System.out.println("""
                Access one of the following databases(please choose a number from 1 to 4):
                1. Players
                2. Coaches
                3. Teams
                4. Sponsors
                5. Go back
                6. Exit
                 """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 7)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 6!");
                choice = userInput.nextInt();
            }
        }
        switch (choice) {
            case 1 -> {
                System.out.println("You are currently in the Players Database.");
                this.adminPlayerDBMenu();
            }
            case 2 -> {
                System.out.println("You are currently in the Coaches Database.");
                this.adminCoachDBMenu();
            }
            case 3 -> {
                System.out.println("You are currently in the Teams Database.");
                this.adminTeamsDBMenu();
            }
            case 4 -> {
                System.out.println("You are currently in the Sponsors Database.");
                this.adminSponsorDBMenu();
            }
            case 5 -> this.startMenu();

            case 6 -> System.exit(0);


        }
    }


    private void adminPlayerDBMenu() {
        System.out.println("""
                You have the following options(please choose a number from 1 to 6):
                1. Create players
                2. Search players
                3. Update players
                4. Delete players
                5. Go back
                6. Exit
                 """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 7)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 6!");
                choice = userInput.nextInt();
            }
        }

        switch (choice) {
            case 1 -> {
                System.out.println("Tell us the details");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                System.out.println("Age: ");
                int age = this.userInput.nextInt();
                this.userInput.nextLine();
                System.out.println("Nationality: ");
                String nationality = this.userInput.nextLine();
                System.out.println("Position: ");
                String position = this.userInput.nextLine();
                System.out.println("Market value: ");
                int marketValue = this.userInput.nextInt();
                Player newPlayer = new Player(firstName, lastName, age, nationality, position, marketValue);
                if (!playerRepositoryMemory.existsPlayer(firstName, lastName))
                    this.playerRepositoryMemory.add(newPlayer);
                else System.out.println("Player is already there");
                this.adminPlayerDBMenu();
            }
            case 2 -> {
                System.out.println("Who are you looking for?");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (playerRepositoryMemory.existsPlayer(firstName, lastName))
                    playerRepositoryMemory.getPlayerFromFirstNameAndSecondName(firstName, lastName).printPlayer();
                else {
                    System.out.println("Player does not exist");
                }
                this.adminPlayerDBMenu();
            }
            case 3 -> {
                System.out.println("Who are you looking for?");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (playerRepositoryMemory.findById(firstName, lastName) != null) {
                    playerRepositoryMemory.findById(firstName, lastName).printPlayer();
                    System.out.println("""
                            Please enter the new details:
                            (You don't have to change everything)
                            """);
                    this.userInput.nextLine();
                    System.out.println("First Name: ");
                    String newFirstName = this.userInput.nextLine();
                    System.out.println("Last Name: ");
                    String newLastName = this.userInput.nextLine();
                    System.out.println("Age: ");
                    int newAge = this.userInput.nextInt();
                    this.userInput.nextLine();
                    System.out.println("Nationality: ");
                    String newNationality = this.userInput.nextLine();
                    System.out.println("Position: ");
                    String newPosition = this.userInput.nextLine();
                    System.out.println("Market value: ");
                    int newMarketValue = this.userInput.nextInt();
                    Player updatedPlayer = new Player(newFirstName, newLastName, newAge, newNationality, newPosition, newMarketValue);
                    //repo.update(firstName,lastName,updatedPlayer)
                    playerRepositoryMemory.update(firstName, lastName, updatedPlayer);
                } else {
                    System.out.println("Player does not exist");
                }
                // return true and print all details of the player
                // else move to case 1 to register it?

                this.adminPlayerDBMenu();
            }
            case 4 -> {
                System.out.println("Who are you looking for?");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (playerRepositoryMemory.findById(firstName, lastName) != null) {
                    playerRepositoryMemory.remove(firstName, lastName);
                    System.out.println("Player has been removed");
                } else {
                    System.out.println("Player does not exist");
                }
                this.adminPlayerDBMenu();
            }
            case 5 -> adminDBsMenu();
            case 6 -> System.exit(0);
        }
    }

    private void adminCoachDBMenu() {
        System.out.println("""
                You have the following options(please choose a number from 1 to 6):
                1. Create coaches
                2. Search coaches
                3. Update coaches
                4. Delete coaches
                5. Go back
                6. Exit
                 """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 7)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 6!");
                choice = userInput.nextInt();
            }
        }

        switch (choice) {
            case 1 -> {
                System.out.println("Tell us the details:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                System.out.println("Age: ");
                int age = this.userInput.nextInt();
                this.userInput.nextLine();
                System.out.println("Nationality: ");
                String nationality = this.userInput.nextLine();
                System.out.println("Playstyle: ");
                String playstyle = this.userInput.nextLine();
                System.out.println("Your current team: ");
                String myTeam = this.userInput.nextLine();
                int counter = 0;
                for (Team team : teamRepositoryMemory.getAllTeams())
                    if (team.getAbbreviation().contains(myTeam))
                        counter++;
                if (counter == 0) {
                    System.out.println("There is no team with such an abreviation in our database");
                    this.adminCoachDBMenu();
                } else {
                    Team newTeam = null;
                    //search the team, break the loop if the team is not found
                    for (Team team : teamRepositoryMemory.getAllTeams())
                        if (team.getAbbreviation().contains(myTeam))
                            newTeam = team;

                    Coach newCoach = new Coach(firstName, lastName, age, nationality, playstyle, newTeam);
                    if (!this.coachRepositoryMemory.existsCoach(newCoach.getFirstName(), newCoach.getLastName()))
                        coachRepositoryMemory.add(newCoach);
                    else
                        System.out.println("Coach already exists");

                    this.adminCoachDBMenu();
                }
            }
            case 2 -> {
                System.out.println("Tell us his name:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (coachRepositoryMemory.existsCoach(firstName, lastName))
                    coachRepositoryMemory.getCoachFromFirstNameAndSecondName(firstName, lastName).printCoach();
                else {
                    System.out.println("Coach does not exist");
                }
                //case 1 if not found?
                this.adminCoachDBMenu();
            }
            case 3 -> {
                System.out.println("Tell us his name:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                //findByID(firstName,lastName)
                System.out.println("Tell us the new details:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String newFirstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String newLastName = this.userInput.nextLine();
                System.out.println("Age: ");
                int age = this.userInput.nextInt();
                this.userInput.nextLine();
                System.out.println("Nationality: ");
                String nationality = this.userInput.nextLine();
                System.out.println("Playstyle: ");
                String playstyle = this.userInput.nextLine();
                System.out.println("Your current team: ");
                String myTeam = this.userInput.nextLine();
                int counter = 0;
                for (Team team : teamRepositoryMemory.getAllTeams())
                    if (team.getAbbreviation().contains(myTeam))
                        counter++;
                if (counter == 0) {
                    System.out.println("There is no team with such an abreviation in our database");
                    this.adminCoachDBMenu();
                } else {
                    Team newTeam = null;
                    //search the team, break the loop if the team is not found
                    for (Team team : teamRepositoryMemory.getAllTeams())
                        if (team.getAbbreviation().contains(myTeam))
                            newTeam = team;

                    Coach newCoach = new Coach(newFirstName, newLastName, age, nationality, playstyle, newTeam);
                    if (coachRepositoryMemory.findById(firstName, lastName) != null)
                        coachRepositoryMemory.update(firstName, lastName, newCoach);
                    else
                        System.out.println("Coach  was not found!");
                    //repo.update
                }
            }
            case 4 -> {
                System.out.println("Tell us his name:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (this.coachRepositoryMemory.findById(firstName, lastName) != null)
                    this.coachRepositoryMemory.remove(firstName, lastName);
                else
                    System.out.println("Coach was not found!");
                //findByID(firstName,lastName)
                //remove(firstName,lastName)
                this.adminCoachDBMenu();
            }
            case 5 -> adminDBsMenu();
            case 6 -> System.exit(0);
        }
    }

    private void adminTeamsDBMenu() {
        System.out.println("""
                You have the following options(please choose a number from 1 to 6):
                1. Create teams
                2. Search teams
                3. Update teams
                4. Delete teams 
                5. Go back
                6. Exit
                 """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 7)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 6!");
                choice = userInput.nextInt();
            }
        }
        switch (choice) {
            case 1 -> {
                System.out.println("Please tell us the details of the new team: ");
                this.userInput.nextLine();
                System.out.println("Team name: ");
                String name = this.userInput.nextLine();
                System.out.println("Team abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                //dictionarul din intelliJ mi-a scos in evidenta faptul ca abbreviation e cu 2 'b'
                //am modifcat in constructor si in UI, in rest, mie nu mi-au aparut erori
                System.out.println("Country: ");
                String country = this.userInput.nextLine();
                System.out.println("Town: ");
                String town = this.userInput.nextLine();
                System.out.println("Foundation Year: ");
                int foundationYear = this.userInput.nextInt();
                this.userInput.nextLine();
                System.out.println("Squad capacity: ");
                int maxSquadSize = this.userInput.nextInt();
                this.userInput.nextLine();
                System.out.println("Budget: ");
                int budget = this.userInput.nextInt();
                this.userInput.nextLine();
                Team newTeam = new Team(name, abbreviation, country, town, foundationYear, maxSquadSize, budget);
                if (!this.teamRepositoryMemory.existsTeam(newTeam.getName(), newTeam.getAbbreviation()))
                    this.teamRepositoryMemory.add(newTeam);
                else
                    System.out.println("Team already exists");
                this.adminTeamsDBMenu();
            }
            case 2 -> {
                System.out.println("Tell us the details of the team: ");
                this.userInput.nextLine();
                System.out.println("Team name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                //findByID(name,abbreviation)
                this.adminTeamsDBMenu();
            }
            case 3 -> {
                System.out.println("Tell us the details of the team: ");
                this.userInput.nextLine();
                System.out.println("Team name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                //findByID(name,abbreviation)
                if (teamRepositoryMemory.findById(name, abbreviation) != null) {
                    System.out.println("Please tell us the new details of the team: ");
                    System.out.println("(You don't have to change everything)");
                    this.userInput.nextLine();
                    System.out.println("Team name: ");
                    String newName = this.userInput.nextLine();
                    System.out.println("Team abbreviation: ");
                    String newAbbreviation = this.userInput.nextLine();
                    System.out.println("Country: ");
                    String country = this.userInput.nextLine();
                    System.out.println("Town: ");
                    String town = this.userInput.nextLine();
                    System.out.println("Foundation Year: ");
                    int foundationYear = this.userInput.nextInt();
                    this.userInput.nextLine();
                    System.out.println("Squad capacity: ");
                    int maxSquadSize = this.userInput.nextInt();
                    this.userInput.nextLine();
                    System.out.println("Budget: ");
                    int budget = this.userInput.nextInt();
                    this.userInput.nextLine();
                    Team updatedTeam = new Team(newName, newAbbreviation, country, town, foundationYear, maxSquadSize, budget);
                    teamRepositoryMemory.update(name, abbreviation, updatedTeam);
                } else {
                    System.out.println("Team  was not found!");
                }


                //crud.update(updatedTeam)
                this.adminTeamsDBMenu();
            }
            case 4 -> {
                System.out.println("Tell us the details of the team: ");
                this.userInput.nextLine();
                System.out.println("Team name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                if (teamRepositoryMemory.findById(name, abbreviation) != null)
                    teamRepositoryMemory.remove(name, abbreviation);
                else
                    System.out.println("Team was not found!");
                this.adminTeamsDBMenu();
            }
            case 5 -> adminDBsMenu();
            case 6 -> System.exit(0);
        }
    }

    private void adminSponsorDBMenu() {
        System.out.println("""
                You have the following options(please choose a number from 1 to 6):
                1. Create sponsors
                2. Search sponsors
                3. Update sponsors
                4. Delete sponsors
                5. Go back
                6. Exit
                 """);
        int choice = this.userInput.nextInt();
        boolean validator = false;

        while (!validator) {
            if (choice > 0 && choice < 7)
                validator = true;
            else {
                System.out.println("Please choose a number between 1 and 6!");
                choice = userInput.nextInt();
            }
        }
        switch (choice) {
            case 1 -> {
                System.out.println("Tell us the firm's details:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abreviation: ");
                String abreviation = this.userInput.nextLine();
                System.out.println("Net worth: ");
                int netWorth = this.userInput.nextInt();
                Sponsor newSponsor = new Sponsor(name, abreviation, netWorth);
                //this.repository.registerSponsor(newSponsor);
                this.adminSponsorDBMenu();
            }
            case 2 -> {
                System.out.println("Tell us the firm's details:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String name = this.userInput.nextLine();
                System.out.println("Net worth: ");
                int netWorth = this.userInput.nextInt();
                //findByID(name ca string 1, ca string 2 ce punem?
                //adaugam inca un string la sponsor de ex o tara sau?
                this.adminSponsorDBMenu();
            }
            case 3 -> {
                System.out.println("Tell us the firm's details:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String name = this.userInput.nextLine();
                System.out.println("Net worth: ");
                int netWorth = this.userInput.nextInt();
                //findByID
                System.out.println("Tell us the new details of the firm:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String newName = this.userInput.nextLine();
                System.out.println("Abreviation: ");
                String abreviation = this.userInput.nextLine();
                System.out.println("Net worth: ");
                int newNetWorth = this.userInput.nextInt();
                Sponsor updatedSponsor = new Sponsor(newName, abreviation, newNetWorth);
                if (sponsorRepositoryMemory.findById(name, abreviation) != null) {
                    sponsorRepositoryMemory.update(name, abreviation, updatedSponsor);
                } else
                    System.out.println("Sponsor  was not found!");
                this.adminSponsorDBMenu();
            }
            case 4 -> {
                System.out.println("Tell us the firm's details:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                if (this.sponsorRepositoryMemory.findById(name, abbreviation) != null)
                    this.sponsorRepositoryMemory.remove(name, abbreviation);
                else
                    System.out.println("Sponsor was not found!");
                this.adminSponsorDBMenu();
            }
            case 5 -> adminDBsMenu();
            case 6 -> System.exit(0);
        }
    }
}