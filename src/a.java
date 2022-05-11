import java.io.*;
import java.net.*;
import java.util.*;

public class a {
    public static void main(String[] args) throws Exception{
        String a = "<span class=\"TermText notranslate lang-en\">";
        int c = 0;
        for (int i = 0; i < a.length(); i++) {
            a = a.substring(0, a.length()-1);
            c++;
        }
        System.out.println(c);
    }
}
