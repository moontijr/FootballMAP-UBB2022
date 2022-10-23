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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }

    public int getMaxSquadSize() {
        return maxSquadSize;
    }

    public void setMaxSquadSize(int maxSquadSize) {
        this.maxSquadSize = maxSquadSize;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<Player> getSquad() {
        return squad;
    }

    public void setSquad(List<Player> squad) {
        this.squad = squad;
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

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
        if(this.squad.size()>=this.maxSquadSize) {
            return false;
        }
        else if (this.budget<player.getMarketValue()) {
            System.out.println("bugetul nu ajunge");
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

    void printTeam()
    {
        System.out.println("Team - " + this.name + " | " + " Abreviation - " + this.abreviation + " | "+" Country -   " + this.country +" | " + " Town - " + this.town + " | " + " foundation year - " + this.foundationYear + " | " + " Squad Maximum Capacity - " + this.maxSquadSize + " | " + " Budget - " +this.budget + " Euro " );
    }
}

