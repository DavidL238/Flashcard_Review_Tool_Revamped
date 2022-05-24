import java.io.*;
import java.net.*;
import java.util.*;

public class temp {
    public static void main(String[] args) throws Exception{
        URL webURL = new URL("https://quizlet.com/2039953/french-revolution-flash-cards/");
        URLConnection urlConnect = webURL.openConnection();
        urlConnect.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStreamReader input = new InputStreamReader(urlConnect.getInputStream());
        BufferedReader bR = new BufferedReader(input);
        ArrayList<String> termList = new ArrayList<>();
        ArrayList<String> definitionList = new ArrayList<>();
        int termOrDefinition = 1;
        String line;
        while ((line = bR.readLine()) != null) {
            int idxStart = line.indexOf("<span class=\"TermText notranslate lang-en\">");
            while (idxStart != -1) {
                line = line.substring(idxStart+43);
                int idxEnd = line.indexOf("</span>");
                String word = line.substring(0,idxEnd);
                System.out.println(word);
                if (termOrDefinition % 2 == 1) {
                    termList.add(word);
                }
                else {
                    definitionList.add(word);
                }
                termOrDefinition++;
                idxStart = line.indexOf("<span class=\"TermText notranslate lang-en\">");
            }
        }
        for (int i = 0; i < termList.size(); i++) {
            System.out.println("Term: " + termList.get(i));
            System.out.println("Definition: " + definitionList.get(i));
            System.out.println(i);
        }
    }
}
