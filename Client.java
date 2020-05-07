import java.rmi.Naming;
import java.rmi.RemoteException;

public class Client extends java.rmi.server.UnicastRemoteObject implements Client2 {

    private static Server2 h;
    private static Client c;
    public Client() throws RemoteException {
        super();
    }

    public void menu() throws java.rmi.RemoteException {

        int option = 10, id;
        String name, rules, creator, board;
        int publication_year;

        do {
            System.out.println("1 => Insert Board game.");
            System.out.println("2 => Consult Board game.");
            System.out.println("3 => Request/Reserve Board Game.");
            System.out.println("4 => Return Board game.");
            System.out.println("5 => List all Board Games.");
            System.out.println("0 => Quit.");

            option = Ler.umInt();

            switch (option) {
                case 1:
                    System.out.println("Insert name of Board game: ");
                    name = Server.lerString();
                    System.out.println("Insert rules of Board game: ");
                    rules = Server.lerString();
                    System.out.println("Insert the name of the Board game creator: ");
                    creator = Server.lerString();
                    System.out.println("Insert the Board game publication year: ");
                    publication_year = Server.lerInt();

                    h.add((Client2) c, name, rules, creator, publication_year);

                    break;

                case 2:
                    System.out.println("Insert the name of the Board game: ");
                    board = Server.lerString();
                    h.consult((Client2) c, board);

                    break;

                case 3:
                    System.out.println("Insert the id of the Board game you want to Reserve/Request: ");
                    id = Server.lerInt();
                    h.request((Client2) c, id);

                    break;

                case 4:
                    System.out.println("Insert the id of the Board game you want to return: ");
                    id = Server.lerInt();
                    h.deliver((Client2) c, id);

                    break;

                case 5:
                    h.list((Client2) c);
                    break;


                default:
                    System.out.println("Invalid option: " + option + ".");
            }
        } while (option != 0);
    }

    public void printOnClient(String s) throws java.rmi.RemoteException{
        System.out.println("Message from server: " + s);
    }

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "file:///C:\\Users\\a35474\\Documents\\UBI\\2019-2020\\SD\\FP1x\\policy.policy");
        System.setSecurityManager(new SecurityManager());
        String s, nome;
        int op = 10, num;
        try {
            h = (Server2) Naming.lookup("Board_Games");
            Client.c = new Client();
            h.subscribe("Name of the clients machine: ", (Client2) c);

        } catch (Exception r) {
            System.out.println("Exception in client" + r.getMessage());
        }
    }
}
