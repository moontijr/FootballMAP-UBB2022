import java.util.*;
public class Sponsor {
    private String name;
    private String netWorth;

    List <Team> sponsoredTeams;

    public Sponsor(String name, String netWorth) {
        this.name = name;
        this.netWorth = netWorth;
    }

    public void sponsorTeam(Team team)
    {
        this.sponsoredTeams.add(team);
        Sponsor sponsor=new Sponsor(this.name,this.netWorth);
        team.sponsors.add(sponsor);
    }
}
