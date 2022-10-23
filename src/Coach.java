import java.util.Date;

public class Coach extends Person {
    private String playStyle;

    private Team team;

    public Coach(String firstName, String lastName, int age, String nationality, String playStyle,Team currentTeam) {
        super(firstName, lastName, age,nationality);
        this.playStyle=playStyle;
        this.team=currentTeam;
    }

    public Team getTeam() {
        return team;
    }

//    public void setTeam(Team team) {
//        this.team = team;
//        this.status= " Training at " + " " + this.team.getName();
//    }

    public String getPlayStyle() {
        return playStyle;
    }

    public void setPlayStyle(String playStyle) {
        this.playStyle = playStyle;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}
