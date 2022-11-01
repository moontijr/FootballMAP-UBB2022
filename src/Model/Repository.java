package Model;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;


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

    @Override
    public void deletePlayer(Player player) {
        for(Team team: allTeams)
            team.squad.remove(player);
        allPlayers.remove(player);
    }

    @Override
    public void deleteSquad(Team team)
    {
        for(Player player : team.squad)
        {
            player.setStatus("Free Agent");
        }
        for (Sponsor sponsor: allSponsors)
        {
            sponsor.sponsoredTeams.remove(team);
        }
        team.squad.clear();
        allTeams.remove(team);
    }

    @Override
    public void deleteCoach(Coach coach)
    {
        allCoaches.remove(coach);
    }

    @Override
    public void deleteSponsor(Sponsor sponsor)
    {
        for(Team team : allTeams)
            team.sponsors.remove(sponsor);
        sponsor.sponsoredTeams.clear();
        allSponsors.remove(sponsor);
    }

    @Override
    public void updatePlayer(Player player)
    {
        Scanner userInput=new Scanner(System.in);
        System.out.println("Tell us your details");
        System.out.println("First Name: ");
        String firstName = userInput.nextLine();
        player.setFirstName(firstName);
        System.out.println("Last Name: ");
        String lastName = userInput.nextLine();
        player.setLastName(lastName);
        System.out.println("Age: ");
        int age = userInput.nextInt();
        player.setAge(age);
        userInput.nextLine();
        System.out.println("Nationality: ");
        String nationality = userInput.nextLine();
        player.setNationality(nationality);
        System.out.println("Position: ");
        String position = userInput.nextLine();
        player.setPosition(position);
        System.out.println("Market value: ");
        int marketValue = userInput.nextInt();
        player.setMarketValue(marketValue);
    }


}
