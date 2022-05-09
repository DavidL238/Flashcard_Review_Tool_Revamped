import java.io.*;
import java.net.*;
import java.util.*;

public class temp {
    public static void main(String[] args) throws Exception{
        URL webURL = new URL("https://quizlet.com/29679191/civil-war-flash-cards/");
        URLConnection urlConnect = (URLConnection)webURL.openConnection();
        InputStreamReader input = new InputStreamReader(urlConnect.getInputStream());
        BufferedReader bR = new BufferedReader(input);
        ArrayList<String> divList = new ArrayList<>();
        while (true) {
            boolean isNeeded = false;
            String line;
            if ((line = bR.readLine()) != null) {
                if (line.contains(''))
            }
        }
    }
}
