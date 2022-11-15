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
        List<Player> allPlayers = playerRepositoryMemory.getAllPlayers();
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
    public List<Player> sortPlayersFromSpecificTeamByPrice(Team team) {
        List<Player> allPlayers = new ArrayList<>(team.squad);
        allPlayers.sort(Comparator.comparing(Player::getMarketValue));
        /*
        for (Player player : team.squad) {
            player.printPlayer();
        }
         */
        return allPlayers;
    }

    /**
     * prints all the players from our database
     */
    public List <Player> printAllPlayers()
    {
        List <Player> players=playerRepositoryMemory.getAllPlayers();
        /*
        for (Player player : playerRepositoryMemory.getAllPlayers()) {
            player.printPlayer();
        }
         */
        if(players.size()>0)
            return players;
        else
            return null;
    }

    /**
     * prints 2 lists, one of them is with all the free agents, and the other one is with the players that currently play for a team
     */
    public List<Player> sortPlayersByStatus(String string)
    {
        if(string.equals("Y") || string.equals("y")) {
            List<Player> freeAgents = new ArrayList<>();
            for (Player player : playerRepositoryMemory.getAllPlayers())
                if(player.getStatus().contains("Free Agent"))
                    freeAgents.add(player);
            return freeAgents;
        }else{
            List<Player> playersWithContract = new ArrayList<>();
            for (Player player : playerRepositoryMemory.getAllPlayers())
                if(!player.getStatus().contains("Free Agent"))
                    playersWithContract.add(player);
            return playersWithContract;
        }
        //List<Player> allPlayers = new ArrayList<>();
        //List<Player> allPlayersNonFreeAgents = new ArrayList<>();
//        for (Player player : playerRepositoryMemory.getAllPlayers()) {
//            if (player.getStatus().contains(string)) {
//                allPlayers.add(player);
//            }
//        }
            /*
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
             */
//        if (allPlayers.size() > 0)
//            return allPlayers;
//        else
//            return null;

    }

    /**
     * sorts All the players from the database, by age
     */
    public List<Player> sortAllPlayersByAge()
    {
        List<Player> allPlayers = playerRepositoryMemory.getAllPlayers();
        allPlayers.sort(Comparator.comparing(Player::getAge));
        return allPlayers;
        /*
        playerRepositoryMemory.getAllPlayers().sort(Comparator.comparing(Player::getAge));
        for (Player player : playerRepositoryMemory.getAllPlayers()) {
            System.out.println(player.getFirstName() + " " +  player.getLastName() + " " + " | "+ "Age : " + player.getAge() + " Year Old ");
        }
         */

    }

    /**
     * gives us a list with all the players that have a specified position
     * @param position string
     */
    public List<Player> sortAllPlayersByPosition (String position)
    {
        List<Player> allPlayersFromAPosition= new ArrayList<>();
        for (Player player: playerRepositoryMemory.getAllPlayers()) {
            if (player.getPosition().contains(position))
                allPlayersFromAPosition.add(player);
        }
        if (allPlayersFromAPosition.size()>0)
            return allPlayersFromAPosition;
        else
            return null;
        /*
        if(allPlayersFromAPosition.size()>0) {
            for (Player player : allPlayersFromAPosition) {
                System.out.println(player.getFirstName() + " " + player.getLastName() + " " + " | " + " Position  : " + player.getPosition() + "");
            }
        }
        else
            System.out.println("No player that match the position");
         */
    }

    /**
     * gives us a list of players from a SPECIFIC TEAM, that play in a SPECIFIC POSITION
     * @param team that we want
     * @param position that we search for
     */
    public List<Player> sortPlayersFromSpecificTeamByPosition (Team team, String position)
    {
        //List<Player> allPlayersFromTeam = new ArrayList<>(team.squad);
        List<Player> allPlayersFromTeamByPosition=new ArrayList<>();
        for (Player player: team.squad)
            if(player.getPosition().contains(position))
                allPlayersFromTeamByPosition.add(player);
        /*
        if(allPlayersFromAPosition.size()>0) {
            for (Player player : allPlayersFromAPosition) {
                System.out.println(player.getFirstName() + " " + player.getLastName() + " " + " | " + " Position  : " + player.getPosition() + "");
            }
        }
        else
            System.out.println("No player that match the position in your squad");
         */
        if(allPlayersFromTeamByPosition.size()>0)
            return allPlayersFromTeamByPosition;
        else
            return null;
    }


    /**
     * prints a list with players with a specific nationality
     * @param nationality of a player
     */
    public List<Player> sortAllPlayersByNationality(String nationality) {
        List<Player> allPlayersFromANationality = new ArrayList<>();
        for (Player player : playerRepositoryMemory.getAllPlayers())
            if (player.getNationality().contains(nationality))
                allPlayersFromANationality.add(player);
        /*
        if(allPlayersFromANationality.size()>0) {
            for (Player player : allPlayersFromANationality) {
                System.out.println(player.getFirstName() + " " + player.getLastName() + " " + " | " + "Nationality : " + player.getNationality() + "");
            }
        }
        else
            System.out.println("No player from that country");
    }
         */
        if(allPlayersFromANationality.size()>0)
            return allPlayersFromANationality;
        else
            return null;
    }

    /**
     * sorts all the players by their family Name
     */
    public List<Player> sortAllPlayersByName()
    {
        List<Player> allPlayers = playerRepositoryMemory.getAllPlayers();
        allPlayers.sort(Comparator.comparing(Player::getLastName));
        return allPlayers;
        /*
        playerRepositoryMemory.getAllPlayers().sort(Comparator.comparing(Player::getFirstName));
        for (Player player : playerRepositoryMemory.getAllPlayers()) {
            player.printPlayer();
        }
         */
    }

    /**
     * prints all the players from the database, WITHOUT YOURSELF
     * @param player is yourself
     */
    public List <Player> seeAllOtherPlayersWithoutYourself(Player player)
    {
        List<Player> allPlayersWithoutYourself=new ArrayList<>();
        for (Player player1 : playerRepositoryMemory.getAllPlayers())
        {
            if(!(player1.getLastName().contains(player.getLastName())&&player.getFirstName().contains(player.getFirstName())))
                allPlayersWithoutYourself.add(player1);
        }
        /*
        for(Player player1 : allPlayersWithoutYourself)
        {
            player1.printPlayer();
        }
         */
        if(allPlayersWithoutYourself.size()>0)
            return allPlayersWithoutYourself;
        else
            return null;

    }

    /**
     * Shows the teams that can afford a specific player
     * @param player is the specific player
     */
    public List<Team> isAffordable(Player player)
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
            return allPotentiallyTeams;
        }
        else
            return null;
    }


}
