import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class Repository implements RepositoryInterface{
    List<Player> allPlayers=new ArrayList<>();
    List<Coach> allCoaches=new ArrayList<>();
    List<Team> allTeams=new ArrayList<>();
    List<Sponsor> allSponsors=new ArrayList<>();
    @Override
    public void registerPlayer(Player player) {
        allPlayers.add(player);
    }

    @Override
    public void registerCoach(Coach coach) {
        allCoaches.add(coach);
    }

    @Override
    public void registerSquad(Team team) {
        allTeams.add(team);
    }

    @Override
    public void registerSponsor(Sponsor sponsor) {
        allSponsors.add(sponsor);
    }


//    /**
//     * sorts all the players from the database ( price)
//     */
//    public void sortAllPlayersByPrice ()
//    {
//        this.allPlayers.sort(Comparator.comparing(Player::getMarketValue));
//        for (Player player : this.allPlayers) {
//            System.out.println(player.getFirstName() + " " +  player.getLastName() + " " + " | "+ "Market Value : " + player.getMarketValue() + " Euro ");
//        }
//    }
//
//    /**
//     * sorts the squad list of a team (price)
//     * @param team specific one
//     */
//    public void sortPlayersFromSpecificTeamByPrice(Team team) {
//        team.squad.sort(Comparator.comparing(Player::getMarketValue));
//        for (Player player : team.squad) {
//            System.out.println(player.getFirstName() + " " + player.getLastName() + " " + " | " + "Market Value : " + player.getMarketValue() + " Euro ");
//        }
//    }
//
//    /**
//     * prints all the players from our database
//     */
//    public void printAllPlayers()
//    {
//        for (Player player : this.allPlayers) {
//            player.printPlayer();
//        }
//    }
//
//    /**
//     * prints 2 lists, one of them is with all the free agents, and the other one is with the players that currently play for a team
//     */
//    public void sortPlayersByStatus()
//    {
//        List<Player> allPlayersFreeAgents=new ArrayList<>();
//        List<Player> allPlayersNonFreeAgents=new ArrayList<>();
//        for (Player player : this.allPlayers)
//        {
//            if(player.getStatus().contains("Free Agent"))
//            {
//                allPlayersFreeAgents.add(player);
//            }
//            else
//            {
//                allPlayersNonFreeAgents.add(player);
//            }
//        }
//
//        System.out.println("These are all the Free Agents Players - ");
//        for(Player player : allPlayersFreeAgents)
//            player.printPlayer();
//
//        System.out.println("These are all the Non Free Agents Players - ");
//        for(Player player : allPlayersNonFreeAgents)
//            player.printPlayer();
//    }
//
//    /**
//     * sorts All the players from the database, by age
//     */
//    public void sortAllPlayersByAge()
//    {
//        this.allPlayers.sort(Comparator.comparing(Player::getAge));
//        for (Player player : this.allPlayers) {
//            System.out.println(player.getFirstName() + " " +  player.getLastName() + " " + " | "+ "Age : " + player.getAge() + " Year Old ");
//        }
//
//    }
//
//    /**
//     * prints a list with players with a specific nationality
//     * @param nationality of a player
//     */
//    public void sortAllPlayersByNationality(String nationality)
//    {
//        List<Player> allPlayersFromANationality=new ArrayList<>();
//        for (Player player: this.allPlayers)
//            if(player.getNationality().contains(nationality))
//                allPlayersFromANationality.add(player);
//        for (Player player: allPlayersFromANationality)
//        {
//            System.out.println(player.getFirstName() + " " +  player.getLastName() + " " + " | "+ "Nationality : " + player.getNationality() + "");
//        }
//    }
//
//    /**
//     * sorts all the players by their family Name
//     */
//    public void sortAllPlayersByName()
//    {
//        this.allPlayers.sort(Comparator.comparing(Player::getFirstName));
//        for (Player player : this.allPlayers) {
//            player.printPlayer();
//        }
//    }
//







}
