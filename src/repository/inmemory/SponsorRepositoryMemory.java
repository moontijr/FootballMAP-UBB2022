package repository.inmemory;

import Model.Player;
import Model.Sponsor;
import repository.SponsorRepository;

import java.awt.*;
import java.util.ArrayList;

public class SponsorRepositoryMemory implements SponsorRepository {

    private static SponsorRepositoryMemory single_instance = null;

    private final ArrayList<Sponsor> allSponsors = new ArrayList<>();

    public static SponsorRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new SponsorRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        SponsorRepositoryMemory.getInstance().add(new Sponsor("NTTData", "NTT", 200000000));
    }

    public static SponsorRepositoryMemory getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(SponsorRepositoryMemory single_instance) {
        SponsorRepositoryMemory.single_instance = single_instance;
    }

    public ArrayList<Sponsor> getAllSponsors() {
        return allSponsors;
    }


    @Override
    public void add(Sponsor entity) {
//        if(!this.existsSponsor(entity.getName(), entity.getAbreviation()))
        this.allSponsors.add(entity);
//        else
//            System.out.println("Sponsor already exists");
    }

    @Override
    public void remove(String s, String id2) {
//        if (findById(s,id2)!=null) {
//        findById(s, id2).disbandSponsor();
        this.allSponsors.remove(findById(s, id2));
//
//        }
//        else
//            System.out.println("Sponsor was not found!");
    }

    @Override
    public void update(String s, String id2, Sponsor newEntity) {
        //if (findById(s, id2) != null) {
        this.findById(s, id2).setName(newEntity.getName());
        this.findById(s, id2).setAbbreviation(newEntity.getAbbreviation());
        this.findById(s, id2).setNetWorth(newEntity.getNetWorth());
//        } else
//            System.out.println("Sponsor  was not found!");
    }

    @Override
    public Sponsor findById(String s, String id2) {
        for (Sponsor sponsor : this.allSponsors)
            if (sponsor.getName().equals(s) && sponsor.getAbbreviation().equals(id2))
                return sponsor;
        return null;
    }

    public boolean existsSponsor(String name, String abbreviation) {
        for (Sponsor sponsor : this.allSponsors)
            if (sponsor.getName().equals(name) && sponsor.getAbbreviation().equals(abbreviation))
                return true;
        return false;
    }

}
