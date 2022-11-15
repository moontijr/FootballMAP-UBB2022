package Model;

import repository.inmemory.PlayerRepositoryMemory;

import java.util.*;

public class Team {
    private String name;
    private String abbreviation;
    private String country;
    private String town;
    private int foundationYear;
    private int maxSquadSize;

    private int budget;

    private int squadValue;

    public int getSquadValue() {
        return squadValue;
    }

    public void setSquadValue(int squadValue) {
        this.squadValue = squadValue;
    }

    public List<Player> squad;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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

    public List<Sponsor> sponsors;


    public Team(String name, String abbreviation, String country, String town, int foundationYear, int maxSquadSize, int budget) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.country = country;
        this.town = town;
        this.foundationYear = foundationYear;
        this.maxSquadSize = maxSquadSize;
        this.squad = new ArrayList<>();
        this.sponsors = new ArrayList<>();
        this.budget = budget;
        this.squadValue=0;

    }

    /**
     * adds a player to a team
     *
     * @param player 1
     * @return false if the squad size is too big, and true if not
     */
    public boolean addPlayerToTeam(Player player) {
        if (this.squad.contains(player))
            return false;
        if (this.squad.size() >= this.maxSquadSize)
            return false;
        else {
            this.squad.add(player);
            player.setStatus("Playing at" + this.name);
        }
        return true;
    }

    /**
     * removes a specific player from the squad
     *
     * @param player 1
     * @return true if the player can be removed, and false if not
     */
    public boolean removePlayerFromTeam(Player player) {
        if (this.squad.remove(player)) {
            player.setStatus("Free Agent");
            return true;
        } else {
            return false;
        }
    }

    /**
     * sells a player, and automatically, the budget is getting increased, and the player is gone
     *
     * @param player    1
     * @param otherTeam 1
     */
    public void sellPlayerFromOtherTeam(Player player, Team otherTeam) {
        otherTeam.budget += player.getMarketValue();
        otherTeam.squadValue -= player.getMarketValue();
        otherTeam.squad.remove(player);
    }

    /**
     * transfers the player from "OTHER TEAM" to team , and automatically, the team that lost the player, gets the money for him, and the other one, loses the money.
     *
     * @param player    1
     * @param otherTeam 1
     * @return true if the transfer can be made, false if not
     */
    public boolean transferPlayerToTeam(Player player, Team otherTeam) //transfera un jucator de la other team, la team
    {
        if (this.squad.contains(player))
            return false;
        if (!otherTeam.squad.contains(player))
            return false;
        if (this.squad.size() >= this.maxSquadSize) {
            //System.out.println("No more places in the squad");
            return false;
        } else if (this.budget < player.getMarketValue()) {
            //System.out.println("Budget is too low");
            return false;
        } else {
            this.budget -= player.getMarketValue();
            this.squadValue += player.getMarketValue();
            this.squad.add(player);
            sellPlayerFromOtherTeam(player, otherTeam);
            player.setStatus("Playing at" + this.name);
            return true;
        }
    }

    public void disbandTeam() {
        for (Player player : this.squad)
            player.setStatus("Free Agent");
        for (Sponsor sponsor : this.sponsors)
            sponsor.stopSponsorTeam(this);
    }

    /**
     * printing format for a team
     */
    public void printTeam() {
        System.out.println("Team - " + this.name + " | " + " abbreviation - " + this.abbreviation + " | " + " Country -   " + this.country + " | " + " Town - " + this.town + " | " + " foundation year - " + this.foundationYear + " | " + " Squad Maximum Capacity - " + this.maxSquadSize + " | " + " Budget - " + this.budget + " Euro ");
    }
}

