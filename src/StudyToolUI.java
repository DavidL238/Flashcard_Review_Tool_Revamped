import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.net.*;

public class StudyToolUI implements ActionListener {
    private final JCheckBox darkTheme;
    private final JFrame mainUI, settingsUI, cardImporterUI, errorUI;
    private final JPanel menuPanel, flashPanel, creationPanel, settingsPanel, errorPanel;
    private final JButton flashcards, settings, createNewDeck, importFlashCards, importButton, okButton;
    private final JTextField welcome, errorMessage, supportedSites;
    private final JTextArea urlImporter;
    private final Color dark, light, darkGrey, lightGrey;
    private final Border empty;
    private CardImporter cI;
    private boolean isDark;

    public StudyToolUI() {
        isDark = false;
        dark = new Color(12,12,12);
        darkGrey = new Color(40,40,40);
        light = new Color(255,255,255);
        lightGrey = new Color(211, 211, 211);
        Color temp = new Color(255, 0, 0);
        empty = javax.swing.BorderFactory.createEmptyBorder();

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

        errorUI = new JFrame("Error");
        errorUI.setVisible(false);
        errorUI.setSize(new Dimension(400, 120));
        errorUI.setLocationRelativeTo(null);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        menuPanel.setVisible(true);
        menuPanel.setBackground(new Color(255,255,255));

        creationPanel = new JPanel();


        flashPanel = new JPanel();
        flashPanel.setVisible(true);
        flashPanel.setBackground(new Color(0, 255, 0));

        settingsPanel = new JPanel();
        settingsPanel.setVisible(false);
        settingsPanel.setBackground(new Color(0, 255, 255));

        errorPanel = new JPanel();
        errorPanel.setBackground(light);

        darkTheme = new JCheckBox("Dark Theme");
        darkTheme.setVisible(true);
        darkTheme.addActionListener(this);
        darkTheme.setFocusPainted(false);
        darkTheme.setPreferredSize(new Dimension(100, 25));
        darkTheme.setBackground(temp);

        flashcards = createIconJButton("images\\flashcards.png");
        settings = createIconJButton("images\\gear.png");

        importFlashCards = createTextJButton("Import Flash Cards");
        importFlashCards.setBackground(lightGrey);
        importFlashCards.setOpaque(true);

        createNewDeck = createTextJButton("Create New Deck");
        createNewDeck.setBackground(lightGrey);
        createNewDeck.setOpaque(true);

        importButton = createTextJButton("Import Flash Cards");
        okButton = createTextJButton("OK");
        okButton.setBackground(lightGrey);
        okButton.setFocusPainted(false);

        welcome = createJTextField("Welcome!", 24);
        errorMessage = createJTextField("", 12);
        supportedSites = createJTextField("Supported Sites: Quizlet & Cram", 12);


        urlImporter = new JTextArea("Paste URL Here!");
        urlImporter.setPreferredSize(new Dimension (50, 90));
        urlImporter.setLineWrap(true);
        urlImporter.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    urlImporter.setText("");
                }
            }
        );

        //mainUI.getContentPane().add(backgroundPanel);
        mainUI.getContentPane().add(menuPanel, BorderLayout.LINE_START);
        mainUI.getContentPane().add(flashPanel, BorderLayout.CENTER);
        menuPanel.add(flashcards);
        menuPanel.add(settings);
        flashPanel.add(welcome);
        flashPanel.add(createNewDeck);
        flashPanel.add(importFlashCards);
        mainUI.revalidate();

        settingsUI.add(settingsPanel, BorderLayout.CENTER);
        settingsPanel.add(darkTheme);

        cardImporterUI.getContentPane().add(supportedSites, BorderLayout.NORTH);
        cardImporterUI.getContentPane().add(urlImporter, BorderLayout.CENTER);
        cardImporterUI.getContentPane().add(importButton, BorderLayout.SOUTH);

        welcome.setBounds(0, 0, 100, 20);
        urlImporter.setBounds(0, 200, 50, 280);

        errorUI.getContentPane().add(errorPanel, BorderLayout.CENTER);
        errorUI.add(okButton, BorderLayout.SOUTH);
        errorPanel.add(errorMessage, BorderLayout.NORTH);
        errorMessage.setPreferredSize(new Dimension(380, 40));

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

    public JButton createTextJButton(String text) {
        JButton newButton = new JButton(text);
        newButton.setOpaque(false);
        newButton.setContentAreaFilled(false);
        newButton.setBorderPainted(false);
        newButton.addActionListener(this);
        newButton.setFocusPainted(false);
        return newButton;
    }

    public JTextField createJTextField(String text, int fontSize) {
        JTextField newText = new JTextField(text);
        newText.setPreferredSize(new Dimension(100, 20));
        newText.setEditable(false);
        newText.setFocusable(false);
        newText.setFont(new Font("Tahoma", Font.PLAIN, fontSize)); //better font
        newText.setOpaque(false);
        newText.setBorder(empty);
        return newText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == darkTheme) {
            if (!isDark) {
                darkTheme.setForeground(light);
                darkTheme.setBackground(darkGrey);
                menuPanel.setBackground(dark);
                flashPanel.setBackground(darkGrey);
                settingsPanel.setBackground(darkGrey);
                mainUI.setBackground(dark);
            }
            else {
                darkTheme.setForeground(dark);
                darkTheme.setBackground(light);
                menuPanel.setBackground(lightGrey);
                flashPanel.setBackground(light);
                settingsPanel.setBackground(light);
                mainUI.getContentPane().setBackground(light);
            }
            isDark = !isDark;
            // Implement saving
        }
        else if (e.getSource() == flashcards) {
            mainUI.setVisible(true);
            mainUI.add(menuPanel, BorderLayout.LINE_START);
            settingsUI.setVisible(false);
            settingsPanel.setVisible(false);
            mainUI.revalidate();
        }
        else if (e.getSource() == settings) {
            settingsUI.setVisible(true);
            settingsPanel.setVisible(true);
            settingsUI.revalidate();
        }
        else if (e.getSource() == createNewDeck) {
            
        }
        else if (e.getSource() == importFlashCards) {
            cardImporterUI.setVisible(true);
        }
        else if (e.getSource() == importButton) {
            String url = urlImporter.getText();
            if (url != null && !url.equals("")) {
                try {
                    errorUI.setTitle("Error");
                    URL webURL = new URL(url);
                    cI = new CardImporter(webURL);
                    if (!cI.isSuccessful()) {
                        errorMessage.setText("Error: Site not supported");
                        errorUI.setVisible(true);
                    }
                    else {
                        errorMessage.setText("Imported " + urlImporter.getText());
                        errorUI.setTitle("Success");
                        errorUI.setVisible(true);
                    }
                }
                catch (MalformedURLException malURL) {
                    errorMessage.setText("Error: Improper URL (MalformedURLException)");
                    errorUI.setVisible(true);
                }
                catch (Exception otherExceptions) {
                    errorMessage.setText("Error: Something went wrong");
                    errorUI.setVisible(true);
                }
            }
        }
        else if (e.getSource() == okButton) {
            errorUI.setVisible(false);
        }
    }
}
