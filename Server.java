import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.rmi.Naming;
        import java.rmi.RemoteException;
        import java.util.ArrayList;

public class Server extends java.rmi.server.UnicastRemoteObject implements Server2{

    private static Client2 client;
    private static ArrayList<Board_Games> list =  new ArrayList<>(); //Array List with all board games

    public Server()  throws java.rmi.RemoteException {
        super();
    }

    //writes on server
    public void printOnServer(String s, Client2 c) throws java.rmi.RemoteException {
        System.out.println("Server : " + s);
        this.client = c;
    }

    //sends menu to client
    public void subscribe(String name, Client2 c) throws java.rmi.RemoteException {
        System.out.println("New Client arrived");
        this.client = c;
        client.menu();
    }

    //add Board games inserted by clients into the array list
    public void add(Client2 c, String nam, String rul, String creat, int pub) throws java.rmi.RemoteException {
        this.client = c;
        Board_Games b = new Board_Games(nam, rul, creat, pub);
        list.add(b);
        client.printOnClient("Board Game inserted with success!");
    }

    //list all board games to client
    public void list(Client2 c) throws RemoteException {
        int i;
        this.client = c;
        String quote = "";

        for (i = 0; i < list.size(); i++) {
            quote +=  "\n Name of Board game: " + list.get(i).getName()
                    + "\n Status: " + list.get(i).getAvailability() + "\n";
        }
        if (quote.equals("")) {
            client.printOnClient("There's no board game to list...");
        } else {
            client.printOnClient(quote);
        }
    }

    //request & reserve board games
    public synchronized void request(Client2 c, int id) throws RemoteException {

        this.client = c;
        if (list.get(id).getAvailability().equals("Reserved")) {

            client.printOnClient("The Board game is already requested & reserved.");
        }

        if (list.get(id).getAvailability().equals("Requested")) {
            if (list.get(id).getReserv() == null) {
                list.get(id).setAvailability("Reserved");
                list.get(id).setReserv(client);
                client.printOnClient("Reserved board game, since it's already requested");
            } else {
                client.printOnClient("The board game is already requested / reserved");
            }
        } else {
            list.get(id).setAvailability("Requested");
            list.get(id).setClient(client);
            client.printOnClient("Board game requested with success.");
        }
    }

    public void deliver(Client2 c, int id) throws RemoteException {
        this.client = c;

        if (list.get(id).getClient().equals(c)) {
            if (list.get(id).getReserv() == null) {
                list.get(id).setAvailability("Available");
                c.printOnClient("Board game delivered with success.");
            } else {
                list.get(id).setAvailability("Requested");
                list.get(id).setClient(list.get(id).getReserv());
                list.get(id).setReserv(null);
                c.printOnClient("Board game delivered with success.");
                list.get(id).setAvailability("Requested");
                list.get(id).getClient().printOnClient("The board game you reserved is already available " + "and requested by you!");
            }
        } else {
            client.printOnClient("The board game isn't requested by you.");
        }
    }

    public void consult(Client2 c, String Board) throws RemoteException {
        int i;
        this.client = c;
        String quote = "";
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(Board)) {
                quote += "\nID: " +  i
                        + "\nName of Board game: " + list.get(i).getName()
                        + "\nRules of Board game: " + list.get(i).getRules()
                        + "\nCreator of Board game: " + list.get(i).getCreator()
                        + "\nYear of publication: " + list.get(i).getPublication_year()
                        + "\nStatus: " + list.get(i).getAvailability();
            }
        }
        if(quote.equals("")) {
            client.printOnClient("The board game you want to consult doesn't exist.");
        } else {
            client.printOnClient(quote);
        }
    }

    public static String lerString() {
        String s = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in), 1);
            s  = in.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public static int lerInt() {
        while(true){
            try{
                return Integer.valueOf(lerString().trim()).intValue();
            }
            catch(Exception e){
                System.out.println("Not a valid int.");
            }
        }
    }

    public static void main(String[] args) throws RemoteException {
        String s;
        System.setProperty("java.security.policy", "file:///C:\\Users\\a35474\\Documents\\UBI\\2019-2020\\SD\\FP1x\\policy.policy");
        System.setSecurityManager(new SecurityManager());
        java.rmi.registry.LocateRegistry.createRegistry(1099);

        try {
            Server h = new Server();
            Naming.rebind("Board_Games", h);
            System.out.println("Server is active.");

        } catch (RemoteException r) {
            System.out.println("Exception in server" + r.getMessage());

        } catch (java.net.MalformedURLException u) {
            System.out.println("Exception in server -URL");
        }
    }
}
