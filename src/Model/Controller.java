package Model;

import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.SponsorRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Controller {
    private PlayerRepositoryMemory playerRepositoryMemory;
    private CoachRepositoryMemory coachRepositoryMemory;
    private TeamRepositoryMemory teamRepositoryMemory;
    private SponsorRepositoryMemory sponsorRepositoryMemory;

    public Controller(PlayerRepositoryMemory playerRepositoryMemory, CoachRepositoryMemory coachRepositoryMemory, TeamRepositoryMemory teamRepositoryMemory, SponsorRepositoryMemory sponsorRepositoryMemory) {
        this.playerRepositoryMemory = playerRepositoryMemory;
        this.coachRepositoryMemory = coachRepositoryMemory;
        this.teamRepositoryMemory = teamRepositoryMemory;
        this.sponsorRepositoryMemory = sponsorRepositoryMemory;
    }

    /**
     * sorts all the players from the database ( price)
     */
    public void sortAllPlayersByPrice ()
    {
        playerRepositoryMemory.getAllPlayers().sort(Comparator.comparing(Player::getMarketValue));
        for (Player player : playerRepositoryMemory.getAllPlayers()) {
            player.printPlayer();
        }
    }

    /**
     * sorts the squad list of a team (price)
     * @param team specific one
     */
    public void sortPlayersFromSpecificTeamByPrice(Team team) {
        team.squad.sort(Comparator.comparing(Player::getMarketValue));
        for (Player player : team.squad) {
            player.printPlayer();
        }
    }

    /**
     * prints all the players from our database
     */
    public void printAllPlayers()
    {
        for (Player player : playerRepositoryMemory.getAllPlayers()) {
            player.printPlayer();
        }
    }

    /**
     * prints 2 lists, one of them is with all the free agents, and the other one is with the players that currently play for a team
     */
    public void sortPlayersByStatus()
    {
        List<Player> allPlayersFreeAgents=new ArrayList<>();
        List<Player> allPlayersNonFreeAgents=new ArrayList<>();
        for (Player player : playerRepositoryMemory.getAllPlayers())
        {
            if(player.getStatus().contains("Free Agent"))
            {
                allPlayersFreeAgents.add(player);
            }
            else
            {
                allPlayersNonFreeAgents.add(player);
            }
        }

        System.out.println("These are all the Free Agents Players - ");
        for(Player player : allPlayersFreeAgents)
            player.printPlayer();

        System.out.println("These are all the Non Free Agents Players - ");
        for(Player player : allPlayersNonFreeAgents)
            player.printPlayer();
    }

    /**
     * sorts All the players from the database, by age
     */
    public void sortAllPlayersByAge()
    {
        playerRepositoryMemory.getAllPlayers().sort(Comparator.comparing(Player::getAge));
        for (Player player : playerRepositoryMemory.getAllPlayers()) {
            System.out.println(player.getFirstName() + " " +  player.getLastName() + " " + " | "+ "Age : " + player.getAge() + " Year Old ");
        }

    }

    /**
     * gives us a list with all the players that have a specified position
     * @param position string
     */
    public void sortAllPlayersByPosition (String position)
    {
        List<Player> allPlayersFromAPosition=new ArrayList<>();
        for (Player player: playerRepositoryMemory.getAllPlayers())
            if(player.getPosition().contains(position))
                allPlayersFromAPosition.add(player);
        if(allPlayersFromAPosition.size()>0) {
            for (Player player : allPlayersFromAPosition) {
                System.out.println(player.getFirstName() + " " + player.getLastName() + " " + " | " + " Position  : " + player.getPosition() + "");
            }
        }
        else
            System.out.println("No player that match the position");
    }

    /**
     * gives us a list of players from a SPECIFIC TEAM, that play in a SPECIFIC POSITION
     * @param team that we want
     * @param position that we search for
     */
    public void sortPlayersFromSpecificTeamByPosition (Team team, String position)
    {
        List<Player> allPlayersFromAPosition=new ArrayList<>();
        for (Player player: team.squad)
            if(player.getPosition().contains(position))
                allPlayersFromAPosition.add(player);
        if(allPlayersFromAPosition.size()>0) {
            for (Player player : allPlayersFromAPosition) {
                System.out.println(player.getFirstName() + " " + player.getLastName() + " " + " | " + " Position  : " + player.getPosition() + "");
            }
        }
        else
            System.out.println("No player that match the position in your squad");
    }


    /**
     * prints a list with players with a specific nationality
     * @param nationality of a player
     */
    public void sortAllPlayersByNationality(String nationality)
    {
        List<Player> allPlayersFromANationality=new ArrayList<>();
        for (Player player: playerRepositoryMemory.getAllPlayers())
            if(player.getNationality().contains(nationality))
                allPlayersFromANationality.add(player);
        if(allPlayersFromANationality.size()>0) {
            for (Player player : allPlayersFromANationality) {
                System.out.println(player.getFirstName() + " " + player.getLastName() + " " + " | " + "Nationality : " + player.getNationality() + "");
            }
        }
        else
            System.out.println("No player from that country");
    }

    /**
     * sorts all the players by their family Name
     */
    public void sortAllPlayersByName()
    {
        playerRepositoryMemory.getAllPlayers().sort(Comparator.comparing(Player::getFirstName));
        for (Player player : playerRepositoryMemory.getAllPlayers()) {
            player.printPlayer();
        }
    }

    /**
     * prints all the Teams from our database
     */
    public void printAllTeams()
    {
        for (Team team : teamRepositoryMemory.getAllTeams())
            team.printTeam();
    }

    /**
     * sorts all our teams from the database (budget)
     */
    public void sortAllTeamsByBudget()
    {
        teamRepositoryMemory.getAllTeams().sort(Comparator.comparing(Team::getBudget));
        for (Team team : teamRepositoryMemory.getAllTeams() )
        {
            team.printTeam();
        }
    }

    /**
     * sorts all the teams from our database(foundation year)
     */
    public void sortAllTeamsByFoundationYear()
    {

        teamRepositoryMemory.getAllTeams().sort(Comparator.comparing(Team::getFoundationYear));
        for (Team team : teamRepositoryMemory.getAllTeams())
        {
            team.printTeam();
        }

    }

    /**
     * searches a specific player in our database
     * @param name1 first name
     * @param name2 last name
     * @return true if the player can be found, false if not
     */

    public boolean searchPlayerInDataBase(String name1, String name2)
    {
        for(Player player:playerRepositoryMemory.getAllPlayers())
        {
            if(player.getFirstName().contains(name1) && player.getLastName().contains(name2))
                return true;
        }
        return false;
    }


    /**
     * prints all the players from the database, WITHOUT YOURSELF
     * @param player is yourself
     */
    public void seeAllOtherPlayersWithoutYourself(Player player)
    {
        List<Player> allPlayersWithoutYourself=new ArrayList<>();
        for (Player player1 : playerRepositoryMemory.getAllPlayers())
        {
            if(!(player1.getLastName().contains(player.getLastName())&&player1.getFirstName().contains(player.getFirstName())))
                allPlayersWithoutYourself.add(player1);
        }
        for(Player player1 : allPlayersWithoutYourself)
        {
            player1.printPlayer();
        }

    }

    /**
     * sorts all the teams by country (gives us a list back of all the teams that are from 1 specified country)
     * @param country (typed from keyboard)
     */
    public void sortAllTeamsByCountry(String country)
    {
        List<Team> allTeamsFromACountry=new ArrayList<>();
        for (Team team: teamRepositoryMemory.getAllTeams())
            if(team.getCountry().contains(country))
                allTeamsFromACountry.add(team);
        if(allTeamsFromACountry.size()>0) {
            for (Team team : allTeamsFromACountry) {
                team.printTeam();
            }
        }
        else
        {
            System.out.println("No Team from that specific Country");
        }
    }

    /**
     * Shows the teams that can afford a specific player
     * @param player is the specific player
     */
    public void isAffordable(Player player)
    {
        List<Team> allPotentiallyTeams=new ArrayList<>();
        for(Team team : teamRepositoryMemory.getAllTeams())
        {
            if(team.getBudget()>=player.getMarketValue()&&team.getMaxSquadSize()>team.squad.size())
            {
                allPotentiallyTeams.add(team);
            }
        }
        if(allPotentiallyTeams.size()>0)
        {
            for (Team team : allPotentiallyTeams)
            {
               System.out.println("Team : " + team.getName());
            }
        }
        else
            System.out.println("No Teams match your profile");
    }

    /**
     * prints all the teams that are affiliated with a specific sponsor
     * @param sponsor 1
     */
    public void printAllTeamsAffiliatedWithSponsor(Sponsor sponsor)
    {
        for (Team team : sponsor.sponsoredTeams)
        {
            team.printTeam();
        }
    }

    /**
     * we sort teams (that are sponsored by 1 specific sponsor ) by budget
     * @param sponsor 1
     */
    public void sortTeamsByBudget(Sponsor sponsor)
    {
        sponsor.sponsoredTeams.sort(Comparator.comparing(Team::getBudget));
        for (Team team : sponsor.sponsoredTeams)
        {
            team.printTeam();
        }

    }

    /**
     * we start a sponsorship between a team and a sponsor
     * @param sponsor 1
     * @param team 1
     */
    public void startSponsorship(Sponsor sponsor, Team team)
    {
        sponsor.sponsorTeam(team);
    }

    /**
     * we end the sponsorship between a team and a firm
     * @param sponsor 1
     * @param team 1
     */
    public void endSponsorship(Sponsor sponsor, Team team)
    {
        sponsor.sponsoredTeams.remove(team);
        team.sponsors.remove(sponsor);
    }

    /**
     * lists all the players from the squad that a coach trains
     * @param coach 1
     */
    public void listYourSquadAsACoach(Coach coach)
    {
        List<Player> allPlayers=new ArrayList<>();
        for(Player player : playerRepositoryMemory.getAllPlayers())
        {
            if (player.getStatus().contains(coach.getTeam().getName()))
            {
                allPlayers.add(player);
            }
        }
        for(Player player : allPlayers)
        {
            player.printPlayer();
        }

    }

    /**
     * lists all the players without the ones from the squad that the coach trains
     * @param coach 1
     */
    public void listAllPlayersOutsideYourTeam(Coach coach)
    {
        List<Player> allPlayers=new ArrayList<>();
        for(Player player : playerRepositoryMemory.getAllPlayers())
        {
            if (!(player.getStatus().contains(coach.getTeam().getName())))
            {
                allPlayers.add(player);
            }
        }
        for(Player player : allPlayers)
        {
            player.printPlayer();
        }
    }

    /**
     * sorts the team that a coach trains (by the players value)
     * @param coach 1
     */
    public void sortCoachTeamByValue(Coach coach)
    {
        List<Player> allPlayers=new ArrayList<>();
        for(Player player : playerRepositoryMemory.getAllPlayers())
        {
            if (player.getStatus().contains(coach.getTeam().getName()))
            {
                allPlayers.add(player);
            }
        }
        allPlayers.sort(Comparator.comparing(Player::getMarketValue));

        for(Player player : allPlayers)
        {
            player.printPlayer();
        }
    }

    /**
     * sorts the team that a coaches train., by age
     * @param coach 1
     */
    public void sortCoachTeamByAge(Coach coach)
    {
        List<Player> allPlayers=new ArrayList<>();
        for(Player player : playerRepositoryMemory.getAllPlayers())
        {
            if (player.getStatus().contains(coach.getTeam().getName()))
            {
                allPlayers.add(player);
            }
        }
        allPlayers.sort(Comparator.comparing(Player::getAge));

        for(Player player : allPlayers)
        {
            player.printPlayer();
        }

    }

    /**
     * adds a player to the list (team)
     * @param coach 1
     * @param player 1
     */
    public void addPlayer(Coach coach,Player player)
    {
        coach.getTeam().addPlayerToTeam(player);
    }

    /**
     * transfers players
     * @param coach 1
     * @param player 1
     * @param team 1
     */
    public void TransferPlayer(Coach coach,Player player,Team team)
    {
        coach.getTeam().transferPlayerToTeam(player,team);
    }



    /**
     * removes player ( makes him free agent )
     * @param coach 1
     * @param player 1
     */
    public void removePlayer(Coach coach,Player player)
    {
        Team team=coach.getTeam();
        team.removePlayerFromTeam(player);
    }

    /**
     * prints all the coaches from our database
     */
    public void printAllCoaches()
    {
        for(Coach coach : coachRepositoryMemory.getAllCoaches())
        {
            coach.printCoach();
        }
    }

    /**
     * sorts all our coaches from our database
     */
    public void sortAllCoachesByAge()
    {
        coachRepositoryMemory.getAllCoaches().sort(Comparator.comparing(Coach::getAge));
        for (Coach coach : coachRepositoryMemory.getAllCoaches()) {
            coach.printCoach();
        }

    }

    /**
     * gives us a list of Coaches with a SPECIFIC NATIONALITY
     * @param nationality that we search for
     */
    public void sortAllCoachessByNationality(String nationality)
    {
        List<Coach> allCoachesFromANationality=new ArrayList<>();
        for (Coach coach: coachRepositoryMemory.getAllCoaches())
            if(coach.getNationality().contains(nationality))
                allCoachesFromANationality.add(coach);
        if(allCoachesFromANationality.size()>0) {
            for (Coach coach : allCoachesFromANationality) {
                coach.printCoach();
            }
        }
        else {
            System.out.println("No coach from that country");
        }
    }

    /**
     * sorts all our coaches from the database by their name
     */
    public void sortAllCoachesByName()
    {
        coachRepositoryMemory.getAllCoaches().sort(Comparator.comparing(Coach::getFirstName));
        for (Coach coach : coachRepositoryMemory.getAllCoaches()) {
            coach.printCoach();
        }
    }

    /**
     * gives us a list of all our coaches by a SPECIFIC playstyle
     * @param playStyle that we search for
     */
    public void sortAllCoachessByPlayStyle(String playStyle)
    {
        List<Coach> allCoachesWithAPlaystyle=new ArrayList<>();
        for (Coach coach: coachRepositoryMemory.getAllCoaches())
            if(coach.getPlayStyle().contains(playStyle))
                allCoachesWithAPlaystyle.add(coach);
        if(allCoachesWithAPlaystyle.size()>0) {
            for (Coach coach : allCoachesWithAPlaystyle) {
                coach.printCoach();
            }
        }
        else {
            System.out.println("No coach with that playstyle");
        }
    }

    /**
     * prints all the sponsors from our database
     */
    public void printAllSponsors()
    {
        for ( Sponsor sponsor : sponsorRepositoryMemory.getAllSponsors())
            sponsor.printSponsor();
    }

    /**
     * sorts all our sponsors from our database by their name
     */
    public void sortAllSponsorsByName()
    {
        sponsorRepositoryMemory.getAllSponsors().sort(Comparator.comparing(Sponsor::getName));
        for (Sponsor sponsor : sponsorRepositoryMemory.getAllSponsors()) {
            sponsor.printSponsor();
        }
    }

    /**
     * sorts all our sponsors from our database by their net worth
     */
    public void sortAllSponsorsByNetWorth()
    {
        sponsorRepositoryMemory.getAllSponsors().sort(Comparator.comparing(Sponsor::getNetWorth));
        for (Sponsor sponsor : sponsorRepositoryMemory.getAllSponsors()) {
            sponsor.printSponsor();
        }
    }

    /**
     * gives us a list of all the teams, that the SPECIFIC sponsor is sponsoring
     * @param sponsor from many teams
     */
    public void listAllTeamsFromASponsor(Sponsor sponsor)
    {
        for (Team team : sponsor.sponsoredTeams)
        {
            team.printTeam();
        }
    }

    /**
     * sorts all the teams that are sponsored by a SPECIFIC sponsor
     * @param sponsor from many teams
     */
    public void sortAllTeamsFromASponsorByMarketValue(Sponsor sponsor)
    {
        sponsor.sponsoredTeams.sort(Comparator.comparing(Team::getBudget));
        for (Team team : sponsor.sponsoredTeams )
        {
            team.printTeam();
        }
    }

    /**
     * starting a sponsorship between a sponsor and a team
     * @param sponsor sponsor
     * @param teamAbreviation string
     */
    public void startSponsoring(Sponsor sponsor,String teamAbreviation)
    {
        Team team1;
        for (Team team: teamRepositoryMemory.getAllTeams())
            if (team.getAbreviation().contains(teamAbreviation)) {
                team1 = team;
                sponsor.sponsorTeam(team1);
                break;
            }

    }

    /**
     * ends a sponsorship between a sponsor and a team
     * @param sponsor sponsor
     * @param teamAbreviation string
     */
    public void endSponsoring(Sponsor sponsor,String teamAbreviation)
    {
        Team team1;
        for (Team team: teamRepositoryMemory.getAllTeams())
            if (team.getAbreviation().contains(teamAbreviation)) {
                team1 = team;
                sponsor.stopSponsorTeam(team1);
                break;
            }
    }

}

// THIS CONTROLLER WILL BE MOVED INTO SPECIFIC CONTROLLERS FOR EACH PLAYER/GUEST/SPONSOR/TEAM


