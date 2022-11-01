package view;

import Model.*;
import repository.TeamRepository;
import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.Scanner;

public class UI {
    private Scanner userInput;
    private PlayerRepositoryMemory playerRepositoryMemory;
    private SponsorRepositoryMemory sponsorRepositoryMemory;
    private TeamRepositoryMemory teamRepositoryMemory;
    private CoachRepositoryMemory coachRepositoryMemory;

    private Controller controller;

    public UI(Scanner scanner,PlayerRepositoryMemory playerRepositoryMemory, SponsorRepositoryMemory sponsorRepositoryMemory, TeamRepositoryMemory teamRepositoryMemory, CoachRepositoryMemory coachRepositoryMemory, Controller controller) {
        this.userInput=scanner;
        this.playerRepositoryMemory = playerRepositoryMemory;
        this.sponsorRepositoryMemory = sponsorRepositoryMemory;
        this.teamRepositoryMemory = teamRepositoryMemory;
        this.coachRepositoryMemory = coachRepositoryMemory;
        this.controller = controller;
    }

    private Scanner scanner = new Scanner(System.in);


    public void loginMenu() {
        System.out.println("""
                Welcome to our football database!
                Please enter your credentials:
                """);
        //username
        //...
        //password
        //
        //To be continued
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
                this.playerRepositoryMemory.add(newPlayer);
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
                //list all players, without the current player
                controller.seeAllOtherPlayersWithoutYourself(player);
                this.subMenuPlayer(player);
            case 2:
                //show potential teams
                controller.isAffordable(player);
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
                System.out.println("Your current team: ");
                String myTeam = this.userInput.nextLine();
                int counter = 0;
                for (Team team : teamRepositoryMemory.getAllTeams())
                    if (team.getAbreviation().contains(myTeam))
                        counter++;
                if (counter == 0) {
                    System.out.println("There is no team with such an abreviation in our database");
                    this.coachMenu();
                } else {
                    Team newTeam = null;
                    //search the team, break the loop if the team is not found
                    for (Team team : teamRepositoryMemory.getAllTeams())
                        if (team.getAbreviation().contains(myTeam))
                            newTeam = team;

                    Coach newCoach = new Coach(firstName, lastName, age, nationality, playstyle, newTeam);
                    coachRepositoryMemory.add(newCoach);
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
                3. Sort players by value
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
                controller.listYourSquadAsACoach(coach);
                this.subMenuCoach(coach);
                break;
            case 2:
                //see all other players
                controller.listAllPlayersOutsideYourTeam(coach);
                this.subMenuCoach(coach);
                break;
            case 3:
                //sort players by value
                controller.sortPlayersFromSpecificTeamByPrice(coach.getTeam());
                this.subMenuCoach(coach);
                break;
            case 4:
                //sort players by position
                System.out.println("Please choose a position");
                String inputString1 = scanner.nextLine();
                controller.sortPlayersFromSpecificTeamByPosition(coach.getTeam(), inputString1);
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
                    if (team.getAbreviation().contains(hisCurrentTeam))
                        team1 = team;
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
                coach.getTeam().removePlayerFromTeam(player3);
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
                System.out.println("Abreviation: ");
                String abreviation=this.userInput.nextLine();
                System.out.println("Net worth: ");
                int netWorth = this.userInput.nextInt();
                Sponsor newSponsor = new Sponsor(name,abreviation, netWorth);
                sponsorRepositoryMemory.add(newSponsor);
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
                //list all teams
                controller.listAllTeamsFromASponsor(sponsor);
                this.subMenuSponsor(sponsor);
                break;
            case 2:
                //sort teams by market value
                controller.sortAllTeamsFromASponsorByMarketValue(sponsor);
                this.subMenuSponsor(sponsor);
            case 3:
                //start sponsoring a team
                System.out.println("Please type the abreviation from the team you wish to sponsor");
                String inputString = scanner.nextLine();
                controller.startSponsoring(sponsor, inputString);
                this.subMenuSponsor(sponsor);
                break;
            case 4:
                //end a sponsorship
                System.out.println("Please type the abreviation from the team you wish to stop sponsoring");
                String inputString1 = scanner.nextLine();
                controller.endSponsoring(sponsor, inputString1);
                this.subMenuSponsor(sponsor);
            case 5:
                //sort sponsored teams
                controller.sortAllTeamsFromASponsorByMarketValue(sponsor);
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
                controller.printAllPlayers();
                this.playerDBMenu();
                //list all players
                break;
            case 2:
                controller.sortAllPlayersByPrice();
                this.playerDBMenu();
                //sort by value
                break;
            case 3:
                //sort by position
                System.out.println("Please choose a position");
                String inputString1 = scanner.nextLine();
                controller.sortAllPlayersByPosition(inputString1);
                this.playerDBMenu();
                break;
            case 4:
                controller.sortPlayersByStatus();
                this.playerDBMenu();
                //sort by status
                break;
            case 5:
                controller.sortAllPlayersByAge();
                this.playerDBMenu();
                //by age
                break;
            case 6:
                System.out.println("Please choose a nationality");
                String inputString = scanner.nextLine();
                controller.sortAllPlayersByNationality(inputString);
                //by nation
                this.playerDBMenu();
                break;
            case 7:
                controller.sortAllPlayersByName();
                this.playerDBMenu();
                //by name
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
                controller.printAllCoaches();
                this.coachDBMenu();
                //list all coaches
                break;
            case 2:
                this.dataBasesMenu();
                System.out.println("Please choose the playing style of the coaches you want to see");
                String inputString = scanner.nextLine();
                controller.sortAllCoachessByPlayStyle(inputString);
                //sort by playing style
                this.coachDBMenu();
                break;
            case 3:
                //sort by age
                controller.sortAllCoachesByAge();
                this.coachDBMenu();
                break;
            case 4:
                //sort by status
                this.coachDBMenu();
            case 5:
                //sort by nationality
                System.out.println("Please choose a nationality");
                String inputString1 = scanner.nextLine();
                controller.sortAllCoachessByNationality(inputString1);
                this.coachDBMenu();
                break;
            case 6:
                //sort by name
                controller.sortAllCoachesByName();
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
                controller.printAllTeams();
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
                //sort by country
                System.out.println("Please choose the country ");
                String inputString = scanner.nextLine();
                controller.sortAllTeamsByCountry(inputString);
                this.teamsDBMenu();
                break;
            case 5:
                //sort by foundation year
                controller.sortAllTeamsByFoundationYear();
                this.teamsDBMenu();
                break;
            case 6:
                //sort by budget
                controller.sortAllTeamsByBudget();
                this.teamsDBMenu();
                break;
            case 7:
                //go back
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
                controller.printAllSponsors();
                this.sponsorDBMenu();
                //list all sponsors
                break;
            case 2:
                controller.sortAllSponsorsByName();
                this.sponsorDBMenu();
                //sort by name
                break;
            case 3:
                //sort by networth
                controller.sortAllSponsorsByNetWorth();
                this.sponsorDBMenu();
                break;
            case 4:
                //teams with the same sponsor(we give a sponsor as parameter)
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
                playerRepositoryMemory.add(newPlayer);
                this.adminPlayerDBMenu();
            }
            case 2 -> {
                System.out.println("Who are you looking for?");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if(playerRepositoryMemory.existsPlayer(firstName,lastName))
                    playerRepositoryMemory.getPlayerFromFirstNameAndSecondName(firstName,lastName).printPlayer();
                else
                {
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
                if(playerRepositoryMemory.findById(firstName,lastName)!=null) {
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
                    playerRepositoryMemory.update(firstName,lastName,updatedPlayer);
                }
                    else
                {
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
                if(playerRepositoryMemory.findById(firstName,lastName)!=null) {
                    playerRepositoryMemory.remove(firstName, lastName);
                    System.out.println("Player has been removed");
                }
                else
                {
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
                    if (team.getAbreviation().contains(myTeam))
                        counter++;
                if (counter == 0) {
                    System.out.println("There is no team with such an abreviation in our database");
                    this.adminCoachDBMenu();
                } else {
                    Team newTeam = null;
                    //search the team, break the loop if the team is not found
                    for (Team team : teamRepositoryMemory.getAllTeams())
                        if (team.getAbreviation().contains(myTeam))
                            newTeam = team;

                    Coach newCoach = new Coach(firstName, lastName, age, nationality, playstyle, newTeam);
                    coachRepositoryMemory.add(newCoach);

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
                if(coachRepositoryMemory.existsCoach(firstName,lastName))
                    coachRepositoryMemory.getCoachFromFirstNameAndSecondName(firstName,lastName).printCoach();
                else
                {
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
                    if (team.getAbreviation().contains(myTeam))
                        counter++;
                if (counter == 0) {
                    System.out.println("There is no team with such an abreviation in our database");
                    this.adminCoachDBMenu();
                } else {
                    Team newTeam = null;
                    //search the team, break the loop if the team is not found
                    for (Team team : teamRepositoryMemory.getAllTeams())
                        if (team.getAbreviation().contains(myTeam))
                            newTeam = team;

                    Coach newCoach = new Coach(newFirstName, newLastName, age, nationality, playstyle, newTeam);
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
                //registerTeam(newTeam)
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
                //if the team is found, proceed to update the details
                System.out.println("Please tell us the new details of the team: ");
                System.out.println("(You don't have to change everything)");
                //sau sa formulam altfel aici?
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
                //findByID(name,abbreviation)
                //if the team is found, proceed to delete the team
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
                String abreviation=this.userInput.nextLine();
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
                String abreviation=this.userInput.nextLine();
                System.out.println("Net worth: ");
                int newNetWorth = this.userInput.nextInt();
                Sponsor updatedSponsor = new Sponsor(newName, abreviation, newNetWorth);
                //crud.update
                this.adminSponsorDBMenu();
            }
            case 4 -> {
                System.out.println("Tell us the firm's details:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String name = this.userInput.nextLine();
                System.out.println("Net worth: ");
                int netWorth = this.userInput.nextInt();
                //findByID
                //crud.remove
                this.adminSponsorDBMenu();
            }
            case 5 -> adminDBsMenu();
            case 6 -> System.exit(0);
        }
    }
}