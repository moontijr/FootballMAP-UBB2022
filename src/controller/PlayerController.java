package controller;

import Model.Player;
import Model.Team;
import repository.inmemory.PlayerRepositoryMemory;
import repository.inmemory.TeamRepositoryMemory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerController {
    private PlayerRepositoryMemory playerRepositoryMemory;
    private TeamRepositoryMemory teamRepositoryMemory;

    public PlayerController(PlayerRepositoryMemory playerRepositoryMemory, TeamRepositoryMemory teamRepositoryMemory) {
        this.playerRepositoryMemory = playerRepositoryMemory;
        this.teamRepositoryMemory = teamRepositoryMemory;
    }

    /**
     * sorts all the players from the database ( price)
     */
    public List<Player> sortAllPlayersByPrice ()
    {
        List<Player> allPlayers = (ArrayList<Player>) playerRepositoryMemory.getAllPlayers();
        allPlayers.sort(Comparator.comparing(Player::getMarketValue));
        return allPlayers;
        //playerRepositoryMemory.getAllPlayers().sort(Comparator.comparing(Player::getMarketValue));
//        for (Player player : playerRepositoryMemory.getAllPlayers()) {
//            player.printPlayer();
//        }
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


}
