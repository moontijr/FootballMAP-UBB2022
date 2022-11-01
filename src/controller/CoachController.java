package controller;

import Model.Coach;
import Model.Player;
import Model.Team;
import repository.inmemory.CoachRepositoryMemory;
import repository.inmemory.PlayerRepositoryMemory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoachController {
    private CoachRepositoryMemory coachRepositoryMemory;
    private PlayerRepositoryMemory playerRepositoryMemory;


    public CoachController(CoachRepositoryMemory coachRepositoryMemory, PlayerRepositoryMemory playerRepositoryMemory) {
        this.coachRepositoryMemory = coachRepositoryMemory;
        this.playerRepositoryMemory = playerRepositoryMemory;
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
    public void TransferPlayer(Coach coach, Player player, Team team)
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

}
