import java.util.Date;

public class Player extends Person {
    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String position;

    private String status; // free agent, or is playing at a team

    private int marketValue;

    public int getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(int marketValue) {
        this.marketValue = marketValue;
    }

    public Player(String firstName, String lastName, int age, String nationality, String position, int marketValue) {
        super(firstName,lastName,age,nationality);
        this.position=position;
        this.status= "Free Agent";
        this.marketValue=marketValue;
    }

    public String getPosition() {
        return position;
    }


}
