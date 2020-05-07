import java.io.Serializable;

public class Board_Games implements Serializable {

    private String name;
    private String rules;
    private String creator;
    private int publication_year;
    private String availability;
    private Client2 client;
    private Client2 reserv;

    public Board_Games(String n, String r, String c, int p) {
        this.name = n;
        this.rules = r;
        this.creator = c;
        this.publication_year = 0;
        this.availability = "Available";
        this.client = null;
        this.reserv = null;
    }

    public String getName(){
        return name;
    }

    public String getRules(){
        return rules;
    }

    public String getCreator(){
        return creator;
    }

    public int getPublication_year(){
        return publication_year;
    }

    public String getAvailability(){
        return availability;
    }

    public void setAvailability(String a){
        this.availability = a;
    }

    public Client2 getClient(){
        return client;
    }

    public void setClient(Client2 c){
        this.client = c;
    }

    public Client2 getReserv(){
        return reserv;
    }

    public void setReserv(Client2 r){
        this.reserv = r;
    }

    public String toString(){
        return ("Board Games => " +
                "Name: \n" + name +
                "Rules: \n" + rules +
                "Criator: \n" + creator +
                "Publication year: \n" + publication_year +
                "Availability: \n" + availability +
                "Client: \n" + client +
                "Reserv: \n" + reserv);
    }
}
