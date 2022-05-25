import java.io.*;
import java.net.*;
import java.util.*;

public class a {
    public static void main(String[] args) {
        try {
            CardCreator.extractAllSaves();
            CardCreator.printAllDeckNames();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        StudyToolUI a = new StudyToolUI();
    }
}
