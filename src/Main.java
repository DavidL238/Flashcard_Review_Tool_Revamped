import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(System.in);
        String url = s.nextLine();
        CardImporter cI = new CardImporter(url);
        System.out.println(cI.getURL());
        cI.out();
    }
}
