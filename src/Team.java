import java.util.*;
public class Team {
    private String name;
    private String abreviation;
    private String country;
    private String town;
    private int foundationYear;
    private int maxSquadSize;

    private int budget;

    List <Player> squad;

    List <Sponsor> sponsors;


    public Team(String name, String abreviation, String country, String town, int foundationYear, int maxSquadSize,int budget) {
        this.name = name;
        this.abreviation = abreviation;
        this.country = country;
        this.town = town;
        this.foundationYear = foundationYear;
        this.maxSquadSize = maxSquadSize;
        this.squad=new ArrayList<>();
        this.sponsors=new ArrayList<>();
        this.budget=budget;

    }

    boolean addPlayerToTeam (Player player)
    {
        if (this.squad.size()>=this.maxSquadSize)
            return false;
        else {
            this.squad.add(player);
            player.setStatus("Playing at" + this.name);
        }
        return true;
    }

    boolean removePlayerFromTeam(Player player)
    {
        if(this.squad.remove(player)) {
            System.out.println("Player Removed");
            player.setStatus("Free Agent");
            return true;
        }
        else {
            System.out.println("Player wasn't in the squad");
            return false;
        }
    }

    void sellPlayerFromOtherTeam(Player player, Team otherTeam)
    {
        otherTeam.budget+=player.getMarketValue();
        otherTeam.squad.remove(player);
    }

    boolean transferPlayerToTeam(Player player, Team otherTeam) //transfera un jucator de la other team, la team
    {
        if(this.squad.size()>=this.maxSquadSize)
            return false;
        else if (this.budget<player.getMarketValue()) {
            return false;
        }
        else {
            this.budget-= player.getMarketValue();
            this.squad.add(player);
            sellPlayerFromOtherTeam(player, otherTeam);
            player.setStatus("Playing at" + this.name);
            return true;
        }
    }
}

