package Model;

interface RepositoryInterface {
    void registerPlayer(Player player);
    void registerSquad(Team team);
    void registerCoach(Coach coach);
    void registerSponsor(Sponsor sponsor);

    void deletePlayer(Player player);

    void deleteSquad(Team team);

    void deleteCoach(Coach coach);

    void deleteSponsor(Sponsor sponsor);

    void updatePlayer(Player player);


}
