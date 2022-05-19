import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class StudyToolUI implements ActionListener {
    private JCheckBox darkTheme;
    private JFrame mainUI, settingsUI, cardImporterUI;
    private JPanel menuPanel, flashPanel, settingsBody;
    private JButton flashcards, settings, importFlashCards;
    private JTextField welcome, urlImporter;
    private Color dark, light, darkGrey;
    private boolean isDark;

    public StudyToolUI() {
        isDark = false;
        dark = new Color(12,12,12);
        darkGrey = new Color(40,40,40);
        light = new Color(255,255,255);
        Color temp = new Color(255, 0, 0);
        Border empty = javax.swing.BorderFactory.createEmptyBorder();

        mainUI = new JFrame();
        mainUI.setTitle("Generic Study Tool");
        mainUI.setVisible(true);
        mainUI.setSize(852, 720);
        mainUI.setResizable(false);
        mainUI.setLocationRelativeTo(null);

        settingsUI = new JFrame("Settings");
        settingsUI.setVisible(false);
        settingsUI.setSize(600, 600);
        settingsUI.setResizable(false);
        settingsUI.setLocationRelativeTo(null);
        settingsUI.setBackground(new Color(0, 0, 255));

        cardImporterUI = new JFrame("Card Importer");
        cardImporterUI.setVisible(false);
        cardImporterUI.setSize(300, 300);
        cardImporterUI.setResizable(false);
        cardImporterUI.setLocationRelativeTo(null);
        cardImporterUI.setBackground(new Color(0, 255, 0));

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        menuPanel.setVisible(true);
        menuPanel.setBackground(new Color(255,255,255));

        flashPanel = new JPanel();
        flashPanel.setVisible(true);
        flashPanel.setBackground(new Color(0, 255, 0));

        settingsBody = new JPanel();
        settingsBody.setVisible(false);
        settingsBody.setBackground(new Color(0, 255, 255));

        darkTheme = new JCheckBox("Dark Theme");
        darkTheme.setVisible(true);
        darkTheme.addActionListener(this);
        darkTheme.setFocusPainted(false);
        darkTheme.setPreferredSize(new Dimension(100, 25));
        darkTheme.setBackground(temp);

        flashcards = createIconJButton("images\\flashcards.png");
        settings = createIconJButton("images\\gear.png");
        importFlashCards = createIconJButton("images\\flashcards.png");

        welcome = new JTextField("Welcome!");
        welcome.setPreferredSize(new Dimension(100, 20));
        welcome.setEditable(false);
        welcome.setFocusable(false);
        welcome.setFont(new Font("Times New Roman", Font.PLAIN, 24)); //better font
        welcome.setOpaque(false);
        welcome.setBorder(empty);

        urlImporter = new JTextField("Paste URL Here!");
        urlImporter.setPreferredSize(new Dimension (50, 280));
        urlImporter.addActionListener(this);

        //mainUI.getContentPane().add(backgroundPanel);
        mainUI.getContentPane().add(menuPanel, BorderLayout.LINE_START);
        mainUI.getContentPane().add(flashPanel, BorderLayout.CENTER);
        menuPanel.add(flashcards);
        menuPanel.add(settings);
        flashPanel.add(welcome);
        flashPanel.add(importFlashCards);
        mainUI.revalidate();

        settingsUI.add(settingsBody, BorderLayout.CENTER);
        settingsBody.add(darkTheme);

        cardImporterUI.getContentPane().add(urlImporter, BorderLayout.SOUTH);

        welcome.setBounds(0, 0, 100, 20);
        urlImporter.setBounds(0, 200, 50, 280);


        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JButton createIconJButton(String iconPath) {
        JButton newButton = new JButton();
        ImageIcon buttonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
        Image icon = buttonIcon.getImage();
        icon = icon.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(icon);
        newButton.setIcon(buttonIcon);
        newButton.setOpaque(false);
        newButton.setContentAreaFilled(false);
        newButton.setBorderPainted(false);
        newButton.addActionListener(this);
        return newButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == darkTheme) {
            if (!isDark) {
                darkTheme.setForeground(light);
                darkTheme.setBackground(darkGrey);
                menuPanel.setBackground(dark);
                flashPanel.setBackground(darkGrey);
                settingsBody.setBackground(darkGrey);
                mainUI.setBackground(dark);
            }
            else {
                darkTheme.setForeground(dark);
                darkTheme.setBackground(light);
                menuPanel.setBackground(light);
                flashPanel.setBackground(light);
                settingsBody.setBackground(light);
                mainUI.getContentPane().setBackground(light);
            }
            isDark = !isDark;
            // Implement saving
        }
        else if (e.getSource() == flashcards) {
            mainUI.setVisible(true);
            mainUI.add(menuPanel, BorderLayout.LINE_START);
            settingsUI.setVisible(false);
            settingsBody.setVisible(false);
            mainUI.revalidate();
        }
        else if (e.getSource() == settings) {
            settingsUI.setVisible(true);
            settingsBody.setVisible(true);
            settingsUI.revalidate();
        }
        else if (e.getSource() == importFlashCards) {
            cardImporterUI.setVisible(true);
        }
        else if (e.getSource() == urlImporter) {
            if (urlImporter.getText().contains("Paste URL Here!")) {
                urlImporter.setText("");
            }
        }
    }
}
