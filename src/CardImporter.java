import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class CardImporter {
    private URL webURL;
    private ArrayList<ArrayList<String>> flashCards;
    private String topic, author;
    private boolean success;

    public CardImporter (URL webURL) throws Exception {
        this.webURL = webURL;
        flashCards = new ArrayList<>();
        success = importCards();
    }

    public boolean importCards() throws Exception {
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
                return false;
            }
        }
        try {
            topic = findElement("<h1 class=\"UIHeading UIHeading--one\">", "</h1>");
            author = findElement("<span class=\"UserLink-username\">", "</span>");

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
                    while (word.contains("<br>")) {
                        int brIdx = word.indexOf("<br>");
                        word = word.substring(0, brIdx) + word.substring(brIdx + 4);
                    }
                    while (word.contains("&#")) {
                        int htmlIdx = word.indexOf("&#");
                        String temp = word.substring(htmlIdx, word.indexOf(";") + 1);
                    }
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
        return true;
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

    public String findElement (String openTag, String closingTag) throws Exception {
        URLConnection urlConnect = webURL.openConnection();
        urlConnect.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStreamReader input = new InputStreamReader(urlConnect.getInputStream());
        BufferedReader bR = new BufferedReader(input);
        String name;
        String found = "";
        int idx = -1;
        while ((name = bR.readLine()) != null) {
            idx = name.indexOf(openTag);
            if (idx != -1) {
                found = name;
                break;
            }
        }
        found = found.substring(idx + openTag.length());
        return found.substring(0, found.indexOf(closingTag));
    }

    public boolean isSuccessful() {
        return success;
    }

    public void saveFile() {
        try {
            File saveFlash = new File(topic + ".txt");
            FileWriter fW = new FileWriter(topic + ".txt");
            if (saveFlash.createNewFile()) {
                for (int i = 0; i < flashCards.size(); i++) {
                    String term = flashCards.get(0).get(i);
                    String def = flashCards.get(1).get(i);
                    try {
                        String response = flashCards.get(2).get(i);
                        fW.write(term + "|" + def + "|" + response);
                    }
                    catch (Exception e) {
                        fW.write(term + "|" + def);
                    }
                }
            }
            else {

            }
        }
        catch (Exception e) {

        }
    }

}
