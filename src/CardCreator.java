import java.util.ArrayList;
import java.io.*;

public class CardCreator {
    private ArrayList<String> terms;
    private ArrayList<String> definitions;
    private static ArrayList<String> allTitles;
    private String nameOfSet;

    public CardCreator() {
        terms = new ArrayList<>();
        definitions = new ArrayList<>();
        allTitles = new ArrayList<>();
    }

    public CardCreator (String nameOfSet) {
        this.nameOfSet = nameOfSet;
        terms = new ArrayList<>();
        definitions = new ArrayList<>();
        allTitles = new ArrayList<>();
    }

    public void setNameOfSet(String name) {
        nameOfSet = name;
    }

    public void addNewCard(String word) {
        if (terms.size() <= definitions.size()) {
            terms.add(word);
        }
        else {
            definitions.add(word);
        }
        if (terms.size() > 0 && definitions.size() > 0) {
            saveFile();
        }
    }

    public boolean addNewCard(String term, String definition) {
        try {
            terms.add(term);
            definitions.add(definition);
            saveFile();
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    public boolean removeCard(String term) {
        int index = -1;
        try {
            for (int i = 0; i < terms.size(); i++) {
                if (terms.get(i).equals(term)) {
                    index = i;
                }
            }
            return removeCard(index);
        }
        catch (IndexOutOfBoundsException iE) {
            iE.printStackTrace();
            return false;
        }
    }

    public boolean removeCard(int index) {
        try {
            terms.remove(index);
            definitions.remove(index);
            saveFile();
            return true;
        }
        catch (IndexOutOfBoundsException iE) {
            iE.printStackTrace();
            return false;
        }
    }

    public void saveFile() {
        saveFileNames();
        try {
            File flashCardsFile = new File(nameOfSet + ".txt");
            flashCardsFile.createNewFile();
            PrintWriter pW = new PrintWriter(nameOfSet + ".txt");
            for (int i = 0; i < terms.size(); i++) {
                pW.print(terms.get(i) + "|");
                if (definitions.size() > i) {
                    pW.println(definitions.get(i));
                }
                else {
                    pW.println("No Definition");
                }
            }
            pW.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFileNames() {
        boolean titleAvailable = true;
        try {
            for (String title : allTitles) {
                if (title.equals(nameOfSet)) {
                    titleAvailable = false;
                    break;
                }
            }
        }
        catch (NullPointerException pointerException) {
            pointerException.printStackTrace();
        }
        if (titleAvailable) {
            allTitles.add(nameOfSet);
            try {
                File saveTitles = new File("List_of_Titles.txt");
                saveTitles.createNewFile();
                PrintWriter pW = new PrintWriter("List_of_Titles.txt");
                for (String titles : allTitles) {
                    pW.println(titles + ".txt");
                }
                pW.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
