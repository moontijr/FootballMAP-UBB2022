package controller;

import Model.Coach;
import Model.Player;
import Model.Team;
import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class  CoachController {
    private CoachRepositoryMemory coachRepositoryMemory;
    private PlayerRepositoryMemory playerRepositoryMemory;


    public CoachController(CoachRepositoryMemory coachRepositoryMemory, PlayerRepositoryMemory playerRepositoryMemory) {
        this.coachRepositoryMemory = coachRepositoryMemory;
        this.playerRepositoryMemory = playerRepositoryMemory;
    }

    /**
     * lists all the players from the squad that a coach trains
     */
    public List<Player> listYourSquadAsACoach(Coach coach)
    {
        List<Player> allPlayers=new ArrayList<>();
        for(Player player : playerRepositoryMemory.getAllPlayers())
        {
            if (player.getStatus().contains(coach.getTeam().getName()))
            {
                allPlayers.add(player);
            }
        }
        /*
        for(Player player : allPlayers)
        {
            player.printPlayer();
        }
         */
        if(allPlayers.size()>0)
            return allPlayers;
        else
            return null;

    }

    /**
     * lists all the players without the ones from the squad that the coach trains
     * @param coach 1
     */
    public List<Player> listAllPlayersOutsideYourTeam(Coach coach)
    {
        List<Player> allPlayers=new ArrayList<>();
        for(Player player : playerRepositoryMemory.getAllPlayers())
        {
            if (!(player.getStatus().contains(coach.getTeam().getName())))
            {
                allPlayers.add(player);
            }
        }

        if(allPlayers.size()>0)
            return allPlayers;
        else
            return null;
        /*
        for(Player player : allPlayers)
        {
            player.printPlayer();
        }
         */
    }

    /**
     * sorts the team that a coach trains (by the players value)
     * @param coach 1
     */
    public List <Player> sortCoachTeamByValue(Coach coach)
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

        if(allPlayers.size()>0)
            return allPlayers;
        else
            return null;
        /*
        for(Player player : allPlayers)
        {
            player.printPlayer();
        }
         */
    }

    /**
     * sorts the team that a coaches train., by age
     * @param coach 1
     */
    public List <Player> sortCoachTeamByAge(Coach coach)
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

        if(allPlayers.size()>0)
            return allPlayers;
        else
            return null;
        /*
        for(Player player : allPlayers)
        {
            player.printPlayer();
        }
         */

    }

    /**
     * adds a player to the list (team)
     * @param coach 1
     * @param player 1
     */
    public boolean addPlayer(Coach coach,Player player)
    {
        return coach.getTeam().addPlayerToTeam(player);

    }

    /**
     * transfers players
     * @param coach 1
     * @param player 1
     * @param team 1
     */
    public boolean transferPlayer(Coach coach, Player player, Team team)
    {
        return coach.getTeam().transferPlayerToTeam(player,team);
    }



    /**
     * removes player ( makes him free agent )
     * @param coach 1
     * @param player 1
     */
    public boolean removePlayer(Coach coach,Player player)
    {
        Team team=coach.getTeam();
        return team.removePlayerFromTeam(player);
    }

    /**
     * prints all the coaches from our database
     */
    public List<Coach> printAllCoaches()
    {
        List<Coach> coaches = new ArrayList<>(coachRepositoryMemory.getAllCoaches());
        /*
        for(Coach coach : coachRepositoryMemory.getAllCoaches())
        {
            coach.printCoach();
        }
         */
        if(coaches.size()>0)
            return coaches;
        else
            return null;
    }

    /**
     * sorts all our coaches from our database
     */
    public List <Coach> sortAllCoachesByAge()
    {
        List<Coach> coaches = new ArrayList<>(coachRepositoryMemory.getAllCoaches());
        coaches.sort(Comparator.comparing(Coach::getAge));
        /*
        for (Coach coach : coachRepositoryMemory.getAllCoaches()) {
            coach.printCoach();
        }
        */
        if (coaches.size()>0)
            return coaches;
        else
            return null;

    }

    /**
     * gives us a list of Coaches with a SPECIFIC NATIONALITY
     * @param nationality that we search for
     */
    public List <Coach> sortAllCoachessByNationality(String nationality)
    {
        List<Coach> allCoachesFromANationality=new ArrayList<>();
        for (Coach coach: coachRepositoryMemory.getAllCoaches())
            if(coach.getNationality().contains(nationality))
                allCoachesFromANationality.add(coach);
        /*
        if(allCoachesFromANationality.size()>0) {
            for (Coach coach : allCoachesFromANationality) {
                coach.printCoach();
            }
        }
        else {
            System.out.println("No coach from that country");

        }
         */
        if(allCoachesFromANationality.size()>0)
            return allCoachesFromANationality;
        else
            return null;
    }

    /**
     * sorts all our coaches from the database by their name
     */
    public List <Coach> sortAllCoachesByName()
    {
        List<Coach> coaches = new ArrayList<>(coachRepositoryMemory.getAllCoaches());
        coaches.sort(Comparator.comparing(Coach::getFirstName));
        /*
        for (Coach coach : coachRepositoryMemory.getAllCoaches()) {
            coach.printCoach();
        }
         */
        if(coaches.size()>0)
            return coaches;
        else
            return null;
    }

    /**
     * gives us a list of all our coaches by a SPECIFIC playstyle
     * @param playStyle that we search for
     */
    public List <Coach> sortAllCoachessByPlayStyle(String playStyle)
    {
        List<Coach> allCoachesWithAPlaystyle=new ArrayList<>();
        for (Coach coach: coachRepositoryMemory.getAllCoaches())
            if(coach.getPlayStyle().contains(playStyle))
                allCoachesWithAPlaystyle.add(coach);
        /*
        if(allCoachesWithAPlaystyle.size()>0) {
            for (Coach coach : allCoachesWithAPlaystyle) {
                coach.printCoach();
            }
        }
        else {
            System.out.println("No coach with that playstyle");
        }
         */
        if(allCoachesWithAPlaystyle.size()>0)
            return allCoachesWithAPlaystyle;
        else
            return null;
    }

}
