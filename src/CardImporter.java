import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class CardImporter extends CardCreator{
    private final URL WEBURL;
    private String site;
    private boolean success;

    public CardImporter (URL webURL) throws Exception {
        super();
        this.WEBURL = webURL;
        success = importCards();
    }

    public boolean importCards() throws Exception {
        URLConnection urlConnect = WEBURL.openConnection();
        urlConnect.addRequestProperty("User-Agent", "Mozilla/4.76");
        int websiteType = siteType();
        String attribute = "";
        String tag = "";
        switch (websiteType) {
            case 1 -> {
                attribute = "<span class=\"TermText notranslate lang-en\">";
                tag = "</span>";
                site = "Quizlet";
            }
            case 2 -> {
                attribute = "<font class=\"font-s\">";
                tag = "</font>";
                site = "Cram";
            }
            case -1 -> {
                return false;
            }
        }
        try {
            String topic = findElement("<h1 class=\"UIHeading UIHeading--one\">", "</h1>");
            String author = findElement("<span class=\"UserLink-username\">", "</span>");
            String fileName = topic + "_" + author + "_" + site;
            super.setNameOfSet(fileName);

            InputStreamReader input = new InputStreamReader(urlConnect.getInputStream());
            BufferedReader bR = new BufferedReader(input);
            String line;
            while ((line = bR.readLine()) != null) {
                int idxStart = line.indexOf(attribute);
                while (idxStart != -1) {
                    line = line.substring(idxStart + attribute.length());
                    int idxEnd = line.indexOf(tag);
                    String word = line.substring(0,idxEnd);
                    while (word.contains("<br>")) {
                        int brIdx = word.indexOf("<br>");
                        word = word.substring(0, brIdx) + " " + word.substring(brIdx + 4);
                    }
                    while (word.contains("&quot;")) {
                        int htmlIdx = word.indexOf("&quot;");
                        word = word.substring(0, htmlIdx) + "\"" + word.substring(htmlIdx + 6);
                    }
                    super.addNewCard(word);
                    idxStart = line.indexOf(attribute);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed. Improper Link.");
        }
        return true;
    }

    public int siteType() {
        String url = WEBURL.toString().toLowerCase();
        if (url.contains("quizlet")) {
            return 1;
        }
        else if (url.contains("cram")) {
            return 2;
        }
        return -1;
    }

    public String findElement (String openTag, String closingTag) throws Exception {
        URLConnection urlConnect = WEBURL.openConnection();
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

}
