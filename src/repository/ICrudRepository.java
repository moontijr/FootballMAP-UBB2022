package repository;

import Model.Coach;
import Model.Player;
import Model.Sponsor;
import Model.Team;

import java.util.ArrayList;
import java.util.List;

public interface ICrudRepository<ID, E> {



//        List<Team> allTeams=new ArrayList<>();
//        List<Coach> allCoaches=new ArrayList<>();
//        List<Sponsor> allSponsors=new ArrayList<>();
        void add(E entity);


        void remove(ID id, ID id2);

        void update(ID id, ID id2, E newEntity);

        E findById(ID id, ID id2);

}

