package view;

import Model.Coach;
import Model.Player;
import Model.Sponsor;
import Model.Team;
import controller.*;
import org.junit.platform.commons.function.Try;
import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.List;
import java.util.Scanner;

public class UI {
    private final Scanner userInput;
    private final PlayerRepositoryMemory playerRepositoryMemory;
    private final SponsorRepositoryMemory sponsorRepositoryMemory;
    private final TeamRepositoryMemory teamRepositoryMemory;
    private final CoachRepositoryMemory coachRepositoryMemory;

    private final PlayerController playerController;
    private final TeamController teamController;
    private final CoachController coachController;
    private final SponsorController sponsorController;

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
        int hashedpassword = password.hashCode();
        String hashedpasswordString = (String.valueOf(hashedpassword));
        String credentials = username + hashedpasswordString;
        switch (credentials) {
            case "player-985752863" -> {
                System.out.println("You are currently in Player Mode.");
                this.playerMenu();
            }
            case "coach94831770" -> {
                System.out.println("You are currently in Coach Mode.");
                this.coachMenu();
            }
            case "sponsor-1998892262" -> {
                System.out.println("You are currently in Sponsor Mode.");
                this.sponsorMenu();
            }
            case "admin92668751" -> {
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
                if (!playerController.checkString(firstName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.playerMenu();
                }
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (!playerController.checkString(lastName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.playerMenu();
                }
                System.out.println("Age: ");
                try {
                    int age = Integer.parseInt(this.userInput.nextLine());
                    System.out.println("Nationality: ");
                    String nationality = this.userInput.nextLine();
                    if (!playerController.checkString(nationality)) {
                        System.out.println("Please provide a valid nationality, without any other digits. Press Anything to go back");
                        this.userInput.nextLine();
                        this.playerMenu();
                    }
                    System.out.println("Position: ");
                    String position = this.userInput.nextLine();
                    if (!playerController.checkString(position)) {
                        System.out.println("Please provide a valid position, without any other digits. Press Anything to go back");
                        this.userInput.nextLine();
                        this.playerMenu();
                    }
                    System.out.println("Market value: ");
                    try {
                        int marketValue = Integer.parseInt(this.userInput.nextLine());
                        Player newPlayer = new Player(firstName, lastName, age, nationality, position, marketValue);
                        if (!playerRepositoryMemory.existsPlayer(firstName, lastName))
                            this.playerRepositoryMemory.add(newPlayer);
                        else System.out.println("Player is already there");
                        subMenuPlayer(newPlayer);
                    } catch (Exception e) {
                        System.out.println("Please provide an Integer at the Market Value. Press Any Button to get back");
                        this.userInput.nextLine();
                        this.playerMenu();

                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Please provide an Integer at the age. Press Any Button to get back");
                    this.userInput.nextLine();
                    this.playerMenu();

                }
            }

            case 2 -> {
                System.out.println("Great! Tell us your name:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                if (!playerController.checkString(firstName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.playerMenu();
                }
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (!playerController.checkString(lastName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.playerMenu();
                }
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
                if (allOtherPlayers == null)
                    System.out.println("There are no players");
                else
                    for (Player otherPlayer : allOtherPlayers) {
                        otherPlayer.printPlayer();
                    }
                this.subMenuPlayer(player);
            case 2:
                List<Team> potentialTeams;
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
                this.userInput.nextLine();
                this.loginMenu();
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
                if (!playerController.checkString(firstName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.playerMenu();
                }
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (!playerController.checkString(lastName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.playerMenu();
                }
                System.out.println("Age: ");
                try {
                    int age = Integer.parseInt(this.userInput.nextLine());
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
                        System.out.println("There is no team with such an abbreviation in our database");
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
                } catch (Exception e) {
                    System.out.println("Please provide an Integer at the age. Press Any Button to get back");
                    this.userInput.nextLine();
                    this.coachMenu();
                }

            }
            case 2 -> {
                System.out.println("Great! Tell us your name:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                if (!playerController.checkString(firstName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.playerMenu();
                }
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (!playerController.checkString(lastName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.playerMenu();
                }
                for (Coach coach : coachRepositoryMemory.getAllCoaches()) {
                    if (coach.getFirstName().contains(firstName) && coach.getLastName().contains(lastName))
                        subMenuCoach(coach);
                }
                System.out.println("There is no coach with that name, please try again with a different name");
                this.coachMenu();
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
                List<Player> myTeam = coachController.listYourSquadAsACoach(coach);
                if (myTeam == null)
                    System.out.println("There are no players");
                else {
                    for (Player player : myTeam) {
                        player.printPlayer();
                    }
                }
                this.subMenuCoach(coach);
                break;
            case 2:
                List<Player> otherPlayers = coachController.listAllPlayersOutsideYourTeam(coach);
                if (otherPlayers == null)
                    System.out.println("There are no players");
                else {
                    for (Player player : otherPlayers) {
                        player.printPlayer();
                    }
                }
                this.subMenuCoach(coach);
                break;
            case 3:
                List<Player> myTeamSorted = playerController.sortPlayersFromSpecificTeamByPrice(coach.getTeam());
                if (myTeamSorted == null)
                    System.out.println("There are no players");
                else {
                    for (Player player : myTeamSorted) {
                        player.printPlayer();
                    }
                }
                this.subMenuCoach(coach);
                break;
            case 4:
                this.userInput.nextLine();
                System.out.println("Please choose a position");
                String position = userInput.nextLine();
                List<Player> myTeamSpecificPosition = playerController.sortPlayersFromSpecificTeamByPosition(coach.getTeam(), position);
                if (myTeamSpecificPosition == null)
                    System.out.println("There are no players");
                else {
                    for (Player player : myTeamSpecificPosition) {
                        player.printPlayer();
                    }
                }
                this.subMenuCoach(coach);
                break;
            case 5:
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
                } else {
                    assert player1 != null;
                    if (coach.getTeam().getBudget() < player1.getMarketValue()) {
                        System.out.println("Budget is too low");
                    }
                }
                coach.getTeam().transferPlayerToTeam(player1, team1);
                this.subMenuCoach(coach);
                break;

            case 6:
                Player player2 = null;
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
                this.subMenuCoach(coach);
                break;
            case 8:
                this.userInput.nextLine();
                this.loginMenu();
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
                try {
                    int netWorth = Integer.parseInt(this.userInput.nextLine());
                    Sponsor newSponsor = new Sponsor(name, abbreviation, netWorth);
                    if (!this.sponsorRepositoryMemory.existsSponsor(name, abbreviation))
                        sponsorRepositoryMemory.add(newSponsor);
                    else
                        System.out.println("Sponsor already exists");
                    this.subMenuSponsor(newSponsor);
                } catch (Exception e) {
                    System.out.println("Please provide an Integer Value for The Net Worth of the Firm. Press any button to get back in the menu");
                    this.userInput.nextLine();
                    this.sponsorMenu();
                }
            case 2:
                System.out.println("Great! Tell us your name of the firm");
                this.userInput.nextLine();
                String nameOfTheFirm = this.userInput.nextLine();
                for (Sponsor sponsor1 : sponsorRepositoryMemory.getAllSponsors())
                    if (sponsor1.getName().contains(nameOfTheFirm))
                        subMenuSponsor(sponsor1);
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
                if (sameSponsorTeams == null)
                    System.out.println("There are no sponsors");
                else {
                    for (Team team : sameSponsorTeams)
                        team.printTeam();
                }
                this.subMenuSponsor(sponsor);
                break;
            case 2:
                List<Team> sameSponsorTeamsSortedByValue = sponsorController.sortAllTeamsFromASponsorByMarketValue(sponsor);
                if (sameSponsorTeamsSortedByValue == null)
                    System.out.println("There are no teams");
                else {
                    for (Team team : sameSponsorTeamsSortedByValue)
                        team.printTeam();
                }
                this.subMenuSponsor(sponsor);
            case 3:
                System.out.println("Please type the abbreviation from the team you wish to sponsor");
                this.userInput.nextLine();
                String inputString = userInput.nextLine();
                sponsorController.startSponsoring(sponsor, inputString);
                this.subMenuSponsor(sponsor);
                break;
            case 4:
                System.out.println("Please type the abbreviation from the team you wish to stop sponsoring");
                this.userInput.nextLine();
                String inputString1 = userInput.nextLine();
                sponsorController.endSponsoring(sponsor, inputString1);
                this.subMenuSponsor(sponsor);

            case 5:
                List<Team> sponsoredTeams = teamController.sortSponsoredTeamsByBudget(sponsor);
                if (sponsoredTeams == null)
                    System.out.println("There are no teams.");
                else {
                    for (Team team : sponsoredTeams)
                        team.printTeam();
                }
                this.subMenuSponsor(sponsor);
            case 6:
                this.userInput.nextLine();
                this.loginMenu();
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
            case 5 -> {
                this.userInput.nextLine();
                this.loginMenu();
            }

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
                if (allPlayers == null)
                    System.out.println("There are no players\n");
                else {
                    for (Player player : allPlayers) {
                        player.printPlayer();
                    }
                }
                this.playerDBMenu();
                break;
            case 2:
                List<Player> sortedPlayers = playerController.sortAllPlayersByPrice();
                if (sortedPlayers == null)
                    System.out.println("There are no players\n");
                else {
                    for (Player player : sortedPlayers) {
                        player.printPlayer();
                    }
                }
                this.playerDBMenu();
                break;
            case 3:
                System.out.println("Please choose a position");
                this.userInput.nextLine();
                String inputString1 = userInput.nextLine();
                List<Player> playersWithSelectedPosition = playerController.sortAllPlayersByPosition(inputString1);
                if (playersWithSelectedPosition == null)
                    System.out.println("There are no players\n");
                else {
                    for (Player player : playersWithSelectedPosition) {
                        player.printPlayer();
                    }
                }
                this.playerDBMenu();
                break;
            case 4:
                System.out.println("Do you want to see the free agents?\n (Y/y, else, we will list players that have a team");
                this.userInput.nextLine();
                String answer = this.userInput.nextLine();
                List<Player> statusPlayers = playerController.sortPlayersByStatus(answer);
                if (statusPlayers.size() == 0)
                    System.out.println("There are no players\n");
                else {
                    for (Player player : statusPlayers) {
                        player.printPlayer();
                    }
                }
                this.playerDBMenu();
                break;
            case 5:
                List<Player> playersByAge = playerController.sortAllPlayersByAge();
                if (playersByAge == null)
                    System.out.println("There are no players\n");
                else {
                    for (Player player : playersByAge) {
                        player.printPlayer();
                    }
                }
                this.playerDBMenu();
                break;
            case 6:
                System.out.println("Please choose a nationality");
                this.userInput.nextLine();
                String inputString = userInput.nextLine();
                List<Player> specificCountry = playerController.sortAllPlayersByNationality(inputString);
                if (specificCountry == null)
                    System.out.println("There are no players");
                else {
                    for (Player player : specificCountry) {
                        player.printPlayer();
                    }
                }
                this.playerDBMenu();
                break;
            case 7:
                List<Player> sortedByName = playerController.sortAllPlayersByName();
                if (sortedByName == null)
                    System.out.println("There are no players");
                else {
                    for (Player player : sortedByName) {
                        player.printPlayer();
                    }
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
                4. See all the coaches-teams pair
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
                if (allCoaches == null)
                    System.out.println("There are no coaches");
                else {
                    for (Coach coach : allCoaches)
                        coach.printCoach();
                }
                this.coachDBMenu();
                break;
            case 2:
                System.out.println("Please choose the playing style of the coaches you want to see");
                this.userInput.nextLine();
                String playstyle = userInput.nextLine();
                List<Coach> allCoachesSortedByPlaystyle = coachController.sortAllCoachesByPlayStyle(playstyle);
                if (allCoachesSortedByPlaystyle == null)
                    System.out.println("There are no coaches with this playstyle");
                else {
                    for (Coach coach : allCoachesSortedByPlaystyle)
                        coach.printCoach();
                }
                this.coachDBMenu();
                break;
            case 3:
                List<Coach> allCoachesSortedByAge = coachController.sortAllCoachesByAge();
                if (allCoachesSortedByAge == null)
                    System.out.println("There are no coaches");
                else {
                    for (Coach coach : allCoachesSortedByAge)
                        coach.printCoach();
                }
                this.coachDBMenu();
                break;
            case 4:
                for (Coach coach : coachRepositoryMemory.getAllCoaches()) {
                    System.out.println(coach.getFirstName() + " " + coach.getLastName() + " training the Team " + coach.getTeam().getName());
                }
                this.coachDBMenu();
                break;
            case 5:
                System.out.println("Please choose a nationality");
                this.userInput.nextLine();
                String country = userInput.nextLine();
                List<Coach> allCoachesSortedByNationality = coachController.sortAllCoachesByNationality(country);
                if (allCoachesSortedByNationality == null)
                    System.out.println("There are no coaches from this nationality");
                else {
                    for (Coach coach : allCoachesSortedByNationality)
                        coach.printCoach();
                }
                this.coachDBMenu();
                break;
            case 6:
                List<Coach> allCoachesSortedByName = coachController.sortAllCoachesByName();
                if (allCoachesSortedByName == null)
                    System.out.println("There are no coaches");
                else {
                    for (Coach coach : allCoachesSortedByName)
                        coach.printCoach();
                }
                this.coachDBMenu();
                break;
            case 7:
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
                break;
            case 2:
                teamController.sortAllTeamsByValue();
                List<Team> allTeamsSortedByValue = teamController.sortAllTeamsByValue();
                if (allTeamsSortedByValue == null)
                    System.out.println("There are no teams");
                else {
                    for (Team team : allTeamsSortedByValue)
                        team.printTeam();
                }
                this.teamsDBMenu();
                break;
            case 3:
                teamController.sortAllTeamsByName();
                List<Team> allTeamsSortedByName = teamController.sortAllTeamsByName();
                if (allTeamsSortedByName == null)
                    System.out.println("There are no teams");
                else {
                    for (Team team : allTeamsSortedByName)
                        team.printTeam();
                }
                this.teamsDBMenu();
                break;
            case 4:
                System.out.println("Please choose the country ");
                this.userInput.nextLine();
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
                if (sortedByYear == null)
                    System.out.println("There are no teams");
                else {
                    for (Team team : sortedByYear)
                        team.printTeam();
                }
                this.teamsDBMenu();
                break;
            case 6:
                List<Team> sortedByBudget = teamController.sortAllTeamsByBudget();
                if (sortedByBudget == null)
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
                break;
            case 2:
                List<Sponsor> sponsorsSortedByName = sponsorController.sortAllSponsorsByName();
                if (sponsorsSortedByName == null)
                    System.out.println("There are no sponsors");
                else {
                    for (Sponsor sponsor : sponsorsSortedByName)
                        sponsor.printSponsor();
                }
                this.sponsorDBMenu();
                break;
            case 3:
                List<Sponsor> sponsorsSortedByNetworth = sponsorController.sortAllSponsorsByNetWorth();
                if (sponsorsSortedByNetworth == null)
                    System.out.println("There are no sponsors");
                else {
                    for (Sponsor sponsor : sponsorsSortedByNetworth)
                        sponsor.printSponsor();
                }
                this.sponsorDBMenu();
                break;
            case 4:
                System.out.println("Tell us the sponsor's name:");
                this.userInput.nextLine();
                String sponsor = this.userInput.nextLine();
                System.out.println("Sponsor abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                if (sponsorRepositoryMemory.findById(sponsor, abbreviation) != null) {
                    List<Team> sameSponsorTeams = teamController.getAllTeamsAffiliatedWithSponsor(sponsorRepositoryMemory.findById(sponsor, abbreviation));
                    if (sameSponsorTeams == null)
                        System.out.println("There are no Teams");
                    else {
                        for (Team team : sameSponsorTeams)
                            team.printTeam();
                    }
                } else {
                    System.out.println("There is no such sponsor.");
                }
                this.sponsorDBMenu();
                break;
            case 5:
                System.out.println("Tell us the team's name:");
                this.userInput.nextLine();
                String team = this.userInput.nextLine();
                System.out.println("Team abbreviation: ");
                String abbreviation1 = this.userInput.nextLine();
                if (teamRepositoryMemory.findById(team, abbreviation1) != null) {
                    List<Sponsor> allSponsorsFromAteam = sponsorController.allSponsorsFromATeam(teamRepositoryMemory.findById(team, abbreviation1));
                    if (allSponsorsFromAteam.size() == 0)
                        System.out.println("There are no Sponsors");
                    else {
                        for (Sponsor sponsor1 : allSponsorsFromAteam)
                            sponsor1.printSponsor();
                    }
                } else {
                    System.out.println("There is no such team.");
                }
                this.sponsorDBMenu();
                break;
            case 6:
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
            case 5 -> {
                this.userInput.nextLine();
                this.loginMenu();
            }

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
                if (!playerController.checkString(firstName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.adminPlayerDBMenu();
                }
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (!playerController.checkString(lastName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.adminPlayerDBMenu();
                }
                System.out.println("Age: ");
                try {
                    int age = Integer.parseInt(this.userInput.nextLine());
                    System.out.println("Nationality: ");
                    String nationality = this.userInput.nextLine();
                    if (!playerController.checkString(nationality)) {
                        System.out.println("Please provide a valid nationality, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminPlayerDBMenu();
                    }
                    System.out.println("Position: ");
                    String position = this.userInput.nextLine();
                    if (!playerController.checkString(position)) {
                        System.out.println("Please provide a valid position, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminPlayerDBMenu();
                    }
                    System.out.println("Market value: ");
                    try {
                        int marketValue = this.userInput.nextInt();
                        Player newPlayer = new Player(firstName, lastName, age, nationality, position, marketValue);
                        if (!playerRepositoryMemory.existsPlayer(firstName, lastName))
                            this.playerRepositoryMemory.add(newPlayer);
                        else System.out.println("Player is already there");
                        this.adminPlayerDBMenu();
                    } catch (Exception e) {
                        System.out.println("Please provide an Integer at the Market Value. Press Any Button to get back");
                        this.userInput.nextLine();
                        this.userInput.nextLine();
                        this.adminPlayerDBMenu();
                    }
                } catch (Exception e) {
                    System.out.println("Please provide an Integer at the Age. Press Any Button to get back");
                    this.userInput.nextLine();
                    this.adminPlayerDBMenu();
                }

            }
            case 2 -> {
                System.out.println("Who are you looking for?");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (playerRepositoryMemory.existsPlayer(firstName, lastName))
                    playerRepositoryMemory.findById(firstName, lastName).printPlayer();
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
                            Press ENTER to start
                            """);
                    this.userInput.nextLine();
                    System.out.println("First Name: ");
                    String newFirstName = this.userInput.nextLine();
                    if (!playerController.checkString(newFirstName)) {
                        System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminPlayerDBMenu();
                    }
                    System.out.println("Last Name: ");
                    String newLastName = this.userInput.nextLine();
                    if (!playerController.checkString(newLastName)) {
                        System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminPlayerDBMenu();
                    }
                    System.out.println("Age: ");
                    try {
                        int newAge = Integer.parseInt(this.userInput.nextLine());
                        this.userInput.nextLine();
                        System.out.println("Nationality: ");
                        String newNationality = this.userInput.nextLine();
                        if (!playerController.checkString(newNationality)) {
                            System.out.println("Please provide a valid nationality, without any other characters. Press Anything to go back");
                            this.userInput.nextLine();
                            this.adminPlayerDBMenu();
                        }
                        System.out.println("Position: ");
                        String newPosition = this.userInput.nextLine();
                        if (!playerController.checkString(newPosition)) {
                            System.out.println("Please provide a valid position, without any other characters. Press Anything to go back");
                            this.userInput.nextLine();
                            this.adminPlayerDBMenu();
                        }
                        System.out.println("Market value: ");
                        try {
                            int newMarketValue = this.userInput.nextInt();
                            Player updatedPlayer = new Player(newFirstName, newLastName, newAge, newNationality, newPosition, newMarketValue);
                            playerRepositoryMemory.update(firstName, lastName, updatedPlayer);
                        } catch (Exception e) {
                            System.out.println("Please provide an Integer at the Market Value. Press Any Button to get back");
                            this.userInput.nextLine();
                            this.adminPlayerDBMenu();
                        }
                        int newMarketValue = Integer.parseInt(this.userInput.nextLine());
                        Player updatedPlayer = new Player(newFirstName, newLastName, newAge, newNationality, newPosition, newMarketValue);
                        playerRepositoryMemory.update(firstName, lastName, updatedPlayer);
                    } catch (Exception e) {
                        System.out.println("Please provide an Integer at the Age. Press Any Button to get back");
                        this.userInput.nextLine();
                        this.adminPlayerDBMenu();
                    }

                } else
                    System.out.println("Player does not exist");
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
                if (!playerController.checkString(firstName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.adminCoachDBMenu();
                }
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (!playerController.checkString(lastName)) {
                    System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.adminCoachDBMenu();
                }
                System.out.println("Age: ");
                try {
                    int age = Integer.parseInt(this.userInput.nextLine());
                    System.out.println("Nationality: ");
                    String nationality = this.userInput.nextLine();
                    if (!playerController.checkString(nationality)) {
                        System.out.println("Please provide a valid nationality, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminCoachDBMenu();
                    }
                    System.out.println("Playstyle: ");
                    String playstyle = this.userInput.nextLine();
                    if (!playerController.checkString(playstyle)) {
                        System.out.println("Please provide a valid playstyle, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminCoachDBMenu();
                    }
                    System.out.println("Your current team: ");
                    String myTeam = this.userInput.nextLine();
                    int counter = 0;
                    for (Team team : teamRepositoryMemory.getAllTeams())
                        if (team.getAbbreviation().contains(myTeam))
                            counter++;
                    if (counter == 0) {
                        System.out.println("There is no team with such an abbreviation in our database");
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

                    }
                    this.adminCoachDBMenu();
                } catch (Exception e) {
                    System.out.println("Please provide an Integer at the Age. Press Any Button to get back");
                    this.userInput.nextLine();
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
                    coachRepositoryMemory.findById(firstName, lastName).printCoach();
                else {
                    System.out.println("Coach does not exist");
                }
                this.adminCoachDBMenu();
            }
            case 3 -> {
                System.out.println("Tell us his name:");
                this.userInput.nextLine();
                System.out.println("First Name: ");
                String firstName = this.userInput.nextLine();
                System.out.println("Last Name: ");
                String lastName = this.userInput.nextLine();
                if (coachRepositoryMemory.findById(firstName, lastName) != null) {
                    System.out.println("Tell us the new details:");
                    this.userInput.nextLine();
                    System.out.println("First Name: ");
                    String newFirstName = this.userInput.nextLine();
                    if (!playerController.checkString(newFirstName)) {
                        System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminCoachDBMenu();
                    }
                    System.out.println("Last Name: ");
                    String newLastName = this.userInput.nextLine();
                    if (!playerController.checkString(newLastName)) {
                        System.out.println("Please provide a valid name, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminCoachDBMenu();
                    }
                    System.out.println("Age: ");
                    try {
                        int age = Integer.parseInt(this.userInput.nextLine());
                        System.out.println("Nationality: ");
                        String nationality = this.userInput.nextLine();
                        if (!playerController.checkString(nationality)) {
                            System.out.println("Please provide a valid nationality, without any other characters. Press Anything to go back");
                            this.userInput.nextLine();
                            this.adminCoachDBMenu();
                        }
                        System.out.println("Playstyle: ");
                        String playstyle = this.userInput.nextLine();
                        if (!playerController.checkString(playstyle)) {
                            System.out.println("Please provide a valid playstyle, without any other characters. Press Anything to go back");
                            this.userInput.nextLine();
                            this.adminCoachDBMenu();
                        }
                        System.out.println("Your current team: ");
                        String myTeam = this.userInput.nextLine();
                        int counter = 0;
                        for (Team team : teamRepositoryMemory.getAllTeams())
                            if (team.getAbbreviation().contains(myTeam))
                                counter++;
                        if (counter == 0) {
                            System.out.println("There is no team with such an abbreviation in our database");
                            this.adminCoachDBMenu();
                        } else {
                            Team newTeam = null;
                            for (Team team : teamRepositoryMemory.getAllTeams())
                                if (team.getAbbreviation().contains(myTeam))
                                    newTeam = team;

                            Coach newCoach = new Coach(newFirstName, newLastName, age, nationality, playstyle, newTeam);
                            coachRepositoryMemory.update(firstName, lastName, newCoach);
                        }
                    } catch (Exception e) {
                        System.out.println("Please provide an Integer at the age. Press Any Button to get back");
                        this.userInput.nextLine();
                        this.adminCoachDBMenu();
                    }


                } else
                    System.out.println("Coach was not found!");
                this.adminCoachDBMenu();
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
                6. Exit""");
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
                System.out.println("Country: ");
                String country = this.userInput.nextLine();
                if (!playerController.checkString(country)) {
                    System.out.println("Please provide a valid country, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.adminTeamsDBMenu();
                }
                System.out.println("Town: ");
                String town = this.userInput.nextLine();
                if (!playerController.checkString(town)) {
                    System.out.println("Please provide a valid town, without any other characters. Press Anything to go back");
                    this.userInput.nextLine();
                    this.adminTeamsDBMenu();
                }
                System.out.println("Foundation Year: ");
                try {
                    int foundationYear = Integer.parseInt(this.userInput.nextLine());
                    System.out.println("Squad capacity: ");
                    try {
                        int maxSquadSize = Integer.parseInt(this.userInput.nextLine());
                        System.out.println("Budget: ");
                        try {
                            int budget = Integer.parseInt(this.userInput.nextLine());
                            Team newTeam = new Team(name, abbreviation, country, town, foundationYear, maxSquadSize, budget);
                            if (this.teamRepositoryMemory.findById(newTeam.getName(), newTeam.getAbbreviation()) == null)
                                this.teamRepositoryMemory.add(newTeam);
                            else
                                System.out.println("Team already exists");
                            this.adminTeamsDBMenu();
                        } catch (Exception e) {
                            System.out.println("Please provide a valid budget. Press anything to go back to the menu");
                            this.userInput.nextLine();
                            this.adminTeamsDBMenu();
                        }

                    } catch (Exception e) {
                        System.out.println("Please provide a valid squad capacity. Press anything to go back to the menu");
                        this.userInput.nextLine();
                        this.adminTeamsDBMenu();
                    }

                } catch (Exception e) {
                    System.out.println("Please provide a valid foundation year. Press anything to go back to the menu");
                    this.userInput.nextLine();
                    this.adminTeamsDBMenu();
                }

            }
            case 2 -> {
                System.out.println("Tell us the details of the team: ");
                this.userInput.nextLine();
                System.out.println("Team name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                if (teamRepositoryMemory.findById(name, abbreviation) != null)
                    teamRepositoryMemory.findById(name, abbreviation).printTeam();
                else
                    System.out.println("This team does not exist!");
                this.adminTeamsDBMenu();
            }
            case 3 -> {
                System.out.println("Tell us the details of the team: ");
                this.userInput.nextLine();
                System.out.println("Team name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
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
                    if (!playerController.checkString(country)) {
                        System.out.println("Please provide a valid country, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminTeamsDBMenu();
                    }
                    System.out.println("Town: ");
                    String town = this.userInput.nextLine();
                    if (!playerController.checkString(town)) {
                        System.out.println("Please provide a valid town, without any other characters. Press Anything to go back");
                        this.userInput.nextLine();
                        this.adminTeamsDBMenu();
                    }
                    System.out.println("Foundation Year: ");
                    try {
                        int foundationYear = Integer.parseInt(this.userInput.nextLine());
                        System.out.println("Squad capacity: ");
                        try {
                            int maxSquadSize = Integer.parseInt(this.userInput.nextLine());
                            System.out.println("Budget: ");
                            try {
                                int budget = Integer.parseInt(this.userInput.nextLine());
                                Team updatedTeam = new Team(newName, newAbbreviation, country, town, foundationYear, maxSquadSize, budget);
                                teamRepositoryMemory.update(name, abbreviation, updatedTeam);
                            } catch (Exception e) {
                                System.out.println("Please provide a valid budget. Press anything to go back to the menu");
                                this.userInput.nextLine();
                                this.adminTeamsDBMenu();
                            }
                        } catch (Exception e) {
                            System.out.println("Please provide a valid maximum squad size. Press anything to go back to the menu");
                            this.userInput.nextLine();
                            this.adminTeamsDBMenu();
                        }

                    } catch (Exception e) {
                        System.out.println("Please provide a valid foundation year. Press anything to go back to the menu");
                        this.userInput.nextLine();
                        this.adminTeamsDBMenu();
                    }
                    int foundationYear = Integer.parseInt(this.userInput.nextLine());
                    System.out.println("Squad capacity: ");
                    int maxSquadSize = Integer.parseInt(this.userInput.nextLine());
                    System.out.println("Budget: ");
                    int budget = Integer.parseInt(this.userInput.nextLine());
                    Team updatedTeam = new Team(newName, newAbbreviation, country, town, foundationYear, maxSquadSize, budget);
                    teamRepositoryMemory.update(name, abbreviation, updatedTeam);
                } else {
                    System.out.println("Team  was not found!");
                }
                this.adminTeamsDBMenu();
            }
            case 4 -> {
                System.out.println("Tell us the details of the team: ");
                this.userInput.nextLine();
                System.out.println("Team name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                if (teamRepositoryMemory.findById(name, abbreviation) != null) {
                    teamRepositoryMemory.remove(name, abbreviation);
                    teamRepositoryMemory.findById(name, abbreviation).disbandTeam();
                } else
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
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                System.out.println("Net worth: ");
                try {
                    int netWorth = Integer.parseInt(this.userInput.nextLine());
                    Sponsor newSponsor = new Sponsor(name, abbreviation, netWorth);
                    if (!this.sponsorRepositoryMemory.existsSponsor(name, abbreviation))
                        sponsorRepositoryMemory.add(newSponsor);
                    else
                        System.out.println("Sponsor already exists");
                    this.adminSponsorDBMenu();
                } catch (Exception e) {
                    System.out.println("Please provide an Integer at the Net Worth. Press Any Button to get back");
                    this.userInput.nextLine();
                    this.adminSponsorDBMenu();
                }

            }
            case 2 -> {
                System.out.println("Tell us the firm's details:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                if (this.sponsorRepositoryMemory.existsSponsor(name, abbreviation))
                    this.sponsorRepositoryMemory.findById(name, abbreviation).printSponsor();
                else
                    System.out.println("Sponsor does not exist");
                this.adminSponsorDBMenu();
            }
            case 3 -> {
                System.out.println("Tell us the firm's details:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                if (this.sponsorRepositoryMemory.findById(name, abbreviation) != null) {
                    System.out.println("Tell us the new details of the firm:");
                    this.userInput.nextLine();
                    System.out.println("Name: ");
                    String newName = this.userInput.nextLine();
                    System.out.println("Abbreviation: ");
                    String newAbbreviation = this.userInput.nextLine();
                    System.out.println("Net worth: ");
                    try {
                        int newNetWorth = Integer.parseInt(this.userInput.nextLine());
                        Sponsor updatedSponsor = new Sponsor(newName, newAbbreviation, newNetWorth);
                        if (sponsorRepositoryMemory.findById(name, abbreviation) != null) {
                            sponsorRepositoryMemory.update(name, abbreviation, updatedSponsor);
                        }
                    } catch (Exception e) {
                        System.out.println("Please provide an Integer at the Net Worth. Press Any Button to get back");
                        this.userInput.nextLine();
                        this.adminSponsorDBMenu();
                    }
                } else
                    System.out.println("Sponsor was not found!");
                this.adminSponsorDBMenu();
            }
            case 4 -> {
                System.out.println("Tell us the firm's details:");
                this.userInput.nextLine();
                System.out.println("Name: ");
                String name = this.userInput.nextLine();
                System.out.println("Abbreviation: ");
                String abbreviation = this.userInput.nextLine();
                if (this.sponsorRepositoryMemory.findById(name, abbreviation) != null) {
                    this.sponsorRepositoryMemory.remove(name, abbreviation);
                    this.sponsorRepositoryMemory.findById(name, abbreviation).disbandSponsor();
                } else
                    System.out.println("Sponsor was not found!");
                this.adminSponsorDBMenu();
            }
            case 5 -> adminDBsMenu();
            case 6 -> System.exit(0);
        }
    }
}