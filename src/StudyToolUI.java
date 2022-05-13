import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StudyToolUI implements ActionListener {
    public static void main(String[] args) {
        JFrame uI = new JFrame();
        uI.setTitle("Generic Study Tool");
        uI.setVisible(true);
        uI.setSize(1280, 720);
        uI.setResizable(false);
        uI.setLocationRelativeTo(null);
        uI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JCheckBox darkCheck = new JCheckBox("Dark Mode");
        uI.add(darkCheck);
        darkCheck.setVisible(true);
        darkCheck.setActionCommand("disable");
        darkCheck.addActionListener(e -> uI.getContentPane().setBackground(new Color(12,12,12)));
    }

    public void checkTheme() {}
}
