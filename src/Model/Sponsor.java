package Model;

import java.util.*;
public class Sponsor {
    private String name;
    private int netWorth;

    private String abreviation;

    public List <Team> sponsoredTeams=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(int netWorth) {
        this.netWorth = netWorth;
    }

    public List<Team> getSponsoredTeams() {
        return sponsoredTeams;
    }

    public void setSponsoredTeams(List<Team> sponsoredTeams) {
        this.sponsoredTeams = sponsoredTeams;
    }

    public Sponsor(String name,String abreviation, int netWorth) {
        this.name = name;
        this.netWorth = netWorth;
        this.abreviation=abreviation;
    }

    public void sponsorTeam(Team team)
    {
        this.sponsoredTeams.add(team);
        Sponsor sponsor=new Sponsor(this.name, this.abreviation,this.netWorth);
        team.sponsors.add(sponsor);
    }

    public void stopSponsorTeam(Team team)
    {
        Sponsor sponsor=new Sponsor(this.name, this.abreviation,this.netWorth);
        this.sponsoredTeams.remove(team);
        team.sponsors.remove(sponsor);
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public void disbandSponsor()
    {
        for(Team team: this.sponsoredTeams)
            stopSponsorTeam(team);
    }

    public void printSponsor()
    {
        System.out.println("Name of The Sponsor : " + this.name + " | " + " Net Worth : " + this.netWorth + " Euro ");
    }

}
