import java.util.*;
public class Sponsor {
    private String name;
    private int netWorth;

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

    public Sponsor(String name, int netWorth) {
        this.name = name;
        this.netWorth = netWorth;
    }

    public void sponsorTeam(Team team)
    {
        this.sponsoredTeams.add(team);
        Sponsor sponsor=new Sponsor(this.name,this.netWorth);
        team.sponsors.add(sponsor);
    }

    public void stopSponsorTeam(Team team)
    {
        Sponsor sponsor=new Sponsor(this.name,this.netWorth);
        this.sponsoredTeams.remove(team);
        team.sponsors.remove(sponsor);
    }

    public void printSponsor()
    {
        System.out.println("Name of The Sponsor : " + this.name + " | " + " Net Worth : " + this.netWorth + " Euro ");
    }

}
