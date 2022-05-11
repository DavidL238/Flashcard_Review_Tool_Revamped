import java.util.Scanner;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(System.in);
        String url = s.nextLine();
        try {
            URL webURL = new URL (url);
            CardImporter cImporter = new CardImporter(webURL);
        }
        catch (Exception e) {
            System.out.println("Error: Improper Link - no protocol");
        }
    }
}
