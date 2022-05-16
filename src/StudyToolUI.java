import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudyToolUI implements ActionListener {
    private JCheckBox darkTheme;
    private JFrame uI;
    private JPanel darkPanel, backgroundPanel;
    private Color dark, light;
    private boolean isDark;

    public StudyToolUI() {
        isDark = false;
        dark = new Color(12,12,12);
        light = new Color(255,255,255);
        uI = new JFrame();
        uI.setTitle("Generic Study Tool");
        uI.setVisible(true);
        uI.setSize(1280, 720);
        uI.setLocationRelativeTo(null);
        darkPanel = new JPanel();
        backgroundPanel = new JPanel();
        backgroundPanel.setVisible(true);
        backgroundPanel.setSize(uI.getWidth(), uI.getHeight());
        darkTheme = new JCheckBox("Dark Theme");
        darkTheme.setVisible(true);
        darkPanel.add(darkTheme);
        darkTheme.addActionListener(this);
        darkPanel.setVisible(true);
        darkPanel.setSize(50, 50);
        darkPanel.setBounds(250, 250, 200, 250);
        uI.getContentPane().add(darkPanel);
        uI.getContentPane().add(backgroundPanel);
        uI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        darkTheme.setFocusPainted(false);
    }

    public void checkTheme() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == darkTheme) {
            if (!isDark) {
                darkTheme.setBackground(dark);
                darkTheme.setForeground(light);
                backgroundPanel.setBackground(dark);
            }
            else {
                darkTheme.setBackground(light);
                darkTheme.setForeground(dark);
                backgroundPanel.setBackground(light);
            }
            isDark = !isDark;
            // Implement saving
        }
    }
}
