import java.util.List;

public class Repository implements RepositoryInterface{
    List<Player> allPlayers;
    List<Coach> allCoaches;
    List<Team> allTeams;
    List<Sponsor> allSponsors;
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
