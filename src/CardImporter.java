import java.io.*;
import java.net.*;

public class CardImporter {
    private URL webURL;
    private boolean s;
    private String[][] flashCards;

    public CardImporter(String a) {
        s = true;
        try {
            webURL = new URL(a);
        }
        catch(MalformedURLException e) {
            s = false;
        }

    }

    public void importCards() {

    }

    public void out() throws IOException {
        BufferedReader bR = new BufferedReader (new InputStreamReader(webURL.openStream()));
        String output ;
        while ((output = bR.readLine()) != null) {
            System.out.println(output);
        }
        bR.close();
    }

    public String getURL() {
        return webURL.toExternalForm();
    }

    public boolean isSuccessful() {
        return s;
    }
}
