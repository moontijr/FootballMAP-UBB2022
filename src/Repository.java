import java.util.ArrayList;

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


}
