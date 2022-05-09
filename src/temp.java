import java.io.*;
import java.net.*;
import java.util.*;

public class temp {
    public static void main(String[] args) throws Exception{
        URL webURL = new URL("https://quizlet.com/29679191/civil-war-flash-cards/");
        URLConnection urlConnect = webURL.openConnection();
        urlConnect.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStreamReader input = new InputStreamReader(urlConnect.getInputStream());
        BufferedReader bR = new BufferedReader(input);
        ArrayList<String> termList = new ArrayList<>();
        ArrayList<String> definitionList = new ArrayList<>();
        int termOrDefinition = 1;
        boolean b = true;
        while (b) {
            String line;
            if ((line = bR.readLine()) != null) {
                int idxStart = line.indexOf("<span class=\"TermText notranslate lang-en\">");
                if (idxStart != -1) {
                    int idxEnd = line.indexOf("</span>");
                    line = line.substring(idxStart + 1, idxEnd);
                }
                if (termOrDefinition % 2 == 1) {
                    termList.add(line);
                }
                else {
                    definitionList.add(line);
                }
                termOrDefinition++;
            }
            else {
                b = false;
                //
            }
        }
        for (int i = 0; i < termList.size(); i++) {
            System.out.println(termList.get(i));
            System.out.println(definitionList.get(i));
        }
    }
}
