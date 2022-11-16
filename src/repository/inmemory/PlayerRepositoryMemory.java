package repository.inmemory;

import Model.Player;
import repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepositoryMemory implements PlayerRepository {


    public List<Player> getAllPlayers() {
        return allPlayers;
    }


    private static PlayerRepositoryMemory single_instance = null;
    private final ArrayList<Player> allPlayers = new ArrayList<>();

    public static PlayerRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new PlayerRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        PlayerRepositoryMemory.getInstance().add(new Player("Tudor", "Moldovan", 26, "Spain", "Forward", 12500));
    }


    @Override
    public void add(Player entity) {
        this.allPlayers.add(entity);
    }

    @Override
    public void remove(String s, String s1) {
        if (findById(s, s1) != null)
            this.allPlayers.remove(findById(s, s1));
    }

    @Override
    public void update(String s, String s1, Player newEntity) {
        if (findById(s, s1) != null) {
            this.findById(s, s1).setFirstName(newEntity.getFirstName());
            this.findById(s, s1).setLastName(newEntity.getLastName());
            this.findById(s, s1).setAge(newEntity.getAge());
            this.findById(s, s1).setNationality(newEntity.getNationality());
            this.findById(s, s1).setPosition(newEntity.getPosition());
            this.findById(s, s1).setMarketValue(newEntity.getMarketValue());
        }
    }


    @Override
    public Player findById(String s, String s1) {
        for (Player player : allPlayers)
            if (s.equals(player.getFirstName()) && s1.equals(player.getLastName()))
                return player;
        return null;
    }

    public boolean existsPlayer(String firstName, String secondName) {
        for (Player player : this.allPlayers)
            if (player.getFirstName().equals(firstName) && player.getLastName().equals(secondName))
                return true;
        return false;
    }
}
