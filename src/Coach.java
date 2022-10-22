import java.util.Date;

public class Coach extends Person {
    private String playStyle;
    private String status;
    public Coach(String firstName, String lastName, int age, String nationality, String playStyle, String status) {
        super(firstName, lastName, age,nationality);
        this.playStyle=playStyle;
        this.status="Free Agent";
    }

    public String getPlayStyle() {
        return playStyle;
    }

    public void setPlayStyle(String playStyle) {
        this.playStyle = playStyle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
