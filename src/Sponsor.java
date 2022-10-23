import java.util.*;
public class Sponsor {
    private String name;
    private int netWorth;

    List <Team> sponsoredTeams=new ArrayList<>();

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

}
