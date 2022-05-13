import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Locale;

public class CardImporter {
    private URL webURL;
    private ArrayList<ArrayList<String>> flashCards;

    public CardImporter (URL webURL) throws Exception {
        this.webURL = webURL;
        flashCards = new ArrayList<>();
        importCards();
    }

    public void importCards() throws Exception {
        URLConnection urlConnect = webURL.openConnection();
        urlConnect.addRequestProperty("User-Agent", "Mozilla/4.76");
        int websiteType = siteType();
        String attribute = "";
        String tag = "";
        switch (websiteType) {
            case 1 -> {
                attribute = "<span class=\"TermText notranslate lang-en\">";
                tag = "</span>";
            }
            case 2 -> {
                attribute = "<font class=\"font-s\">";
                tag = "</font>";
            }
            case -1 -> {
                System.out.println("Error: Site not supported");
                return;
            }
        }
        try {
            InputStreamReader input = new InputStreamReader(urlConnect.getInputStream());
            BufferedReader bR = new BufferedReader(input);
            ArrayList<String> termList = new ArrayList<>();
            ArrayList<String> definitionList = new ArrayList<>();
            int termOrDefinition = 1;
            String line;
            while ((line = bR.readLine()) != null) {
                int idxStart = line.indexOf(attribute);
                while (idxStart != -1) {
                    line = line.substring(idxStart + attribute.length());
                    int idxEnd = line.indexOf(tag);
                    String word = line.substring(0,idxEnd);
                    System.out.println(word);
                    if (termOrDefinition % 2 == 1) {
                        termList.add(word);
                    }
                    else {
                        definitionList.add(word);
                    }
                    termOrDefinition++;
                    idxStart = line.indexOf(attribute);
                }
            }
            flashCards.add(termList);
            flashCards.add(definitionList);
        }
        catch (Exception e) {
            System.out.println("Failed. Improper Link.");
        }
    }

    public int siteType() {
        String url = webURL.toString().toLowerCase();
        if (url.contains("quizlet")) {
            return 1;
        }
        else if (url.contains("cram")) {
            return 2;
        }
        return -1;
    }

}
