import com.sun.jdi.request.MonitorContendedEnteredRequest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.net.*;

public class StudyToolUI implements ActionListener {
    private final JCheckBox darkTheme;
    private final JFrame mainUI, settingsUI, creationUI, selectionUI, editorUI, studyUI, cardImporterUI, responseUI;
    private final JPanel menuPanel, flashPanel, creationPanel, settingsPanel, responsePanel;
    private JPanel editorPanel;
    private final JButton cardsIconButton, settingsIconButton, newDeckButton, createDeckButton, editDeckButton, confirmEditButton, makeEditButton,
            newCardButton, mainStudyButton, confirmSelectionButton, flashcardDisplayButton, nextButton, previousButton, mainImportButton, importButton, okButton;
    private final JTextField errorMessage, supportedSites;
    private final JTextArea urlImporter, deckName, deckTerm, deckDefinition;
    private JTextArea replaceTerm, replaceDefinition;
    private final JLabel welcomeLabel, deckNameLabel, deckTermLabel, deckDefinitionLabel;
    private JComboBox deckComboBox, deckTermsComboBox, deckDefinitionsComboBox;
    private final Color dark, light, darkGrey, lightGrey;
    private final Border empty;
    private ArrayList<String> selectedTerms, selectedDefinitions;
    private int index;
    private String nameOfSet;
    private boolean isDark, side;

    public StudyToolUI() {
        isDark = false;
        side = false;
        dark = new Color(12,12,12);
        darkGrey = new Color(40,40,40);
        light = new Color(255,255,255);
        lightGrey = new Color(211, 211, 211);
        Color temp = new Color(255, 0, 0);
        empty = javax.swing.BorderFactory.createEmptyBorder();

        mainUI = createJFrame("Java Study Tool", 852, 720);
        mainUI.setVisible(true);
        settingsUI = createJFrame("Settings", 600, 600);
        cardImporterUI = createJFrame("Card Importer", 300, 300);
        creationUI = createJFrame("Deck Creator", 600, 600);
        selectionUI = createJFrame("Select Deck", 300, 90);
        editorUI = createJFrame("Edit Deck", 600, 600);
        studyUI = createJFrame("Flashcards", 600, 600);
        responseUI = createJFrame("Error", 400, 120);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        menuPanel.setVisible(true);
        menuPanel.setBackground(lightGrey);

        creationPanel = new JPanel();
        GroupLayout creationLayout = new GroupLayout(creationPanel);
        creationLayout.setAutoCreateGaps(true);
        creationLayout.setAutoCreateContainerGaps(true);
        creationPanel.setLayout(creationLayout);
        creationPanel.setVisible(false);
        creationPanel.setBackground(lightGrey);

        editorPanel = new JPanel();
        editorPanel.setBackground(lightGrey);

        flashPanel = new JPanel();
        flashPanel.setVisible(true);
        flashPanel.setBackground(light);

        settingsPanel = new JPanel();
        settingsPanel.setVisible(false);
        settingsPanel.setBackground(light);

        responsePanel = new JPanel();
        responsePanel.setBackground(light);

        darkTheme = new JCheckBox("Dark Theme");
        darkTheme.setVisible(true);
        darkTheme.addActionListener(this);
        darkTheme.setFocusPainted(false);
        darkTheme.setPreferredSize(new Dimension(100, 25));
        darkTheme.setBackground(temp);

        cardsIconButton = createIconJButton("images\\flashcards.png");
        settingsIconButton = createIconJButton("images\\gear.png");

        mainImportButton = createTextJButton("Import Flash Cards");
        mainImportButton.setBackground(lightGrey);
        mainImportButton.setOpaque(true);

        newDeckButton = createTextJButton("Create New Deck");
        newDeckButton.setBackground(lightGrey);
        newDeckButton.setOpaque(true);

        createDeckButton = createTextJButton("Finalize");
        createDeckButton.setBackground(light);
        createDeckButton.setOpaque(true);

        editDeckButton = createTextJButton("Edit Existing Deck");
        editDeckButton.setBackground(lightGrey);
        editDeckButton.setOpaque(true);

        newCardButton = createTextJButton("Create Card");
        newCardButton.setBackground(lightGrey);
        newCardButton.setOpaque(true);

        confirmEditButton = createTextJButton("Edit");
        confirmEditButton.setBackground(light);
        confirmEditButton.setOpaque(true);

        makeEditButton = createTextJButton("Make Edit");
        makeEditButton.setBackground(lightGrey);
        makeEditButton.setOpaque(true);

        mainStudyButton = createTextJButton("Study Flashcards");
        mainStudyButton.setBackground(lightGrey);
        mainStudyButton.setOpaque(true);

        confirmSelectionButton = createTextJButton("Confirm Selection");

        flashcardDisplayButton = createTextJButton("");
        flashcardDisplayButton.setBackground(lightGrey);
        flashcardDisplayButton.setOpaque(true);
        flashcardDisplayButton.addActionListener(this);

        nextButton = createTextJButton("Next");
        nextButton.addActionListener(this);
        previousButton = createTextJButton("Previous");
        previousButton.addActionListener(this);

        importButton = createTextJButton("Import Flash Cards");
        okButton = createTextJButton("OK");
        okButton.setBackground(lightGrey);
        okButton.setFocusPainted(false);

        welcomeLabel = new JLabel("Welcome");
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

        deckName = new JTextArea("Deck Name");
        deckName.setPreferredSize(new Dimension (50, 50));
        deckName.setLineWrap(true);
        deckName.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                deckName.setText("");
            }
          }
        );

        deckTerm = new JTextArea("Deck Term");
        deckTerm.setPreferredSize(new Dimension (50, 50));
        deckTerm.setLineWrap(true);
        deckTerm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                deckTerm.setText("");
            }
          }
        );

        deckDefinition = new JTextArea("Deck Definition");
        deckDefinition.setPreferredSize(new Dimension(50 , 50));
        deckDefinition.setLineWrap(true);
        deckDefinition.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                deckDefinition.setText("");
            }
          }
        );

        deckNameLabel = new JLabel("Name of Deck:");
        deckTermLabel = new JLabel("Deck Term:");
        deckDefinitionLabel = new JLabel("Deck Definition:");

        mainUI.getContentPane().add(menuPanel, BorderLayout.LINE_START);
        mainUI.getContentPane().add(flashPanel, BorderLayout.CENTER);
        menuPanel.add(cardsIconButton);
        menuPanel.add(settingsIconButton);
        flashPanel.add(welcomeLabel);
        flashPanel.add(mainStudyButton);
        flashPanel.add(newDeckButton);
        flashPanel.add(editDeckButton);
        flashPanel.add(mainImportButton);
        mainUI.revalidate();

        settingsUI.add(settingsPanel, BorderLayout.CENTER);
        settingsPanel.add(darkTheme);

        studyUI.add(flashcardDisplayButton, BorderLayout.CENTER);
        studyUI.add(previousButton, BorderLayout.WEST);
        studyUI.add(nextButton, BorderLayout.EAST);

        editorUI.add(editorPanel);

        cardImporterUI.getContentPane().add(supportedSites, BorderLayout.NORTH);
        cardImporterUI.getContentPane().add(urlImporter, BorderLayout.CENTER);
        cardImporterUI.getContentPane().add(importButton, BorderLayout.SOUTH);

        welcomeLabel.setBounds(0, 0, 100, 20);
        urlImporter.setBounds(0, 200, 50, 280);

        responseUI.getContentPane().add(responsePanel, BorderLayout.CENTER);
        responseUI.add(okButton, BorderLayout.SOUTH);
        responsePanel.add(errorMessage, BorderLayout.NORTH);
        errorMessage.setPreferredSize(new Dimension(380, 40));

        creationUI.getContentPane().add(creationPanel);
        creationLayout.setHorizontalGroup(
                creationLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(deckNameLabel)
                        .addComponent(deckName)
                        .addGroup(creationLayout.createParallelGroup(GroupLayout.Alignment.CENTER))
                            .addComponent(deckTermLabel)
                            .addComponent(deckTerm)
                            .addComponent(deckDefinitionLabel)
                            .addComponent(deckDefinition)
                        .addGroup(creationLayout.createParallelGroup(GroupLayout.Alignment.TRAILING))
                            .addComponent(createDeckButton)
        );
        creationLayout.setVerticalGroup(
                creationLayout.createSequentialGroup()
                        .addComponent(deckNameLabel)
                        .addComponent(deckName)
                        .addGroup(creationLayout.createParallelGroup(GroupLayout.Alignment.CENTER))
                            .addComponent(deckTermLabel)
                            .addComponent(deckTerm)
                            .addComponent(deckDefinitionLabel)
                            .addComponent(deckDefinition)
                        .addGroup(creationLayout.createParallelGroup(GroupLayout.Alignment.TRAILING))
                            .addComponent(createDeckButton)
        );

        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JFrame createJFrame(String name, int dimensionX, int dimensionY) {
        JFrame returnFrame = new JFrame(name);
        returnFrame.setVisible(false);
        returnFrame.setSize(dimensionX, dimensionY);
        returnFrame.setResizable(false);
        returnFrame.setLocationRelativeTo(null);
        returnFrame.setBackground(new Color(0, 255, 0));
        return returnFrame;
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
        else if (e.getSource() == cardsIconButton) {
            mainUI.setVisible(true);
            mainUI.add(menuPanel, BorderLayout.LINE_START);
            settingsUI.setVisible(false);
            settingsPanel.setVisible(false);
            mainUI.revalidate();
        }
        else if (e.getSource() == settingsIconButton) {
            settingsUI.setVisible(true);
            settingsPanel.setVisible(true);
            settingsUI.revalidate();
        }
        else if (e.getSource() == mainStudyButton) {
            ArrayList<String> allTitles = CardCreator.getAllTitles();
            deckComboBox = new JComboBox();
            for (String allTitle : allTitles) {
                deckComboBox.addItem(allTitle);
            }
            deckComboBox.setEditable(false);
            selectionUI.getContentPane().add(deckComboBox, BorderLayout.NORTH);
            selectionUI.getContentPane().add(confirmSelectionButton, BorderLayout.SOUTH);
            selectionUI.setVisible(true);
        }
        else if (e.getSource() == confirmSelectionButton) {
            index = 0;
            selectionUI.setVisible(false);
            studyUI.setVisible(true);
            nameOfSet = String.valueOf(deckComboBox.getSelectedItem());
            CardCreator cardCreator = CardCreator.getSelectedDeck(nameOfSet);
            selectedTerms = cardCreator.getTerms();
            selectedDefinitions = cardCreator.getDefinitions();
            flashcardDisplayButton.setText(selectedTerms.get(index));
        }
        else if (e.getSource() == flashcardDisplayButton) {
            flashcardDisplayButton.setText(selectedDefinitions.get(index));
        }
        else if (e.getSource() == nextButton) {
            if (index < selectedTerms.size() - 1) {
                index++;
                flashcardDisplayButton.setText(selectedTerms.get(index));
            }
        }
        else if (e.getSource() == previousButton) {
            if (index > 0) {
                index--;
                flashcardDisplayButton.setText(selectedTerms.get(index));
            }
        }
        else if (e.getSource() == newDeckButton) {
            creationUI.setVisible(true);
            creationPanel.setVisible(true);
        }
        else if (e.getSource() == createDeckButton) {
            CardCreator cardCreator = new CardCreator(deckName.getText());
            cardCreator.addNewCard(deckTerm.getText(), deckDefinition.getText());
            creationUI.setVisible(false);
        }
        else if (e.getSource() == editDeckButton) {
            ArrayList<String> allTitles = CardCreator.getAllTitles();
            deckComboBox = new JComboBox();
            for (String allTitle : allTitles) {
                deckComboBox.addItem(allTitle);
            }
            deckComboBox.setEditable(false);
            selectionUI.getContentPane().add(deckComboBox, BorderLayout.NORTH);
            selectionUI.getContentPane().add(confirmEditButton, BorderLayout.SOUTH);
            selectionUI.setVisible(true);
        }
        else if (e.getSource() == confirmEditButton) {
            selectionUI.setVisible(false);
            editorUI.setVisible(true);
            nameOfSet = String.valueOf(deckComboBox.getSelectedItem());
            CardCreator editSet = CardCreator.getSelectedDeck(nameOfSet);
            if (editSet != null) {
                ArrayList<String> terms = editSet.getTerms();
                ArrayList<String> definitions = editSet.getDefinitions();
                deckTermsComboBox = new JComboBox();
                for (String term : terms) {
                    deckTermsComboBox.addItem(term);
                }
                deckTermsComboBox.addActionListener(this);
                deckDefinitionsComboBox = new JComboBox();
                for (String definition : definitions) {
                    deckDefinitionsComboBox.addItem(definition);
                }
                deckDefinitionsComboBox.addActionListener(this);
                replaceTerm = new JTextArea();
                replaceTerm.setLineWrap(true);
                replaceDefinition = new JTextArea();
                replaceDefinition.setLineWrap(true);
                makeEditButton.addActionListener(this);
                JPanel tempCenterPanel = new JPanel();
                GridLayout editorLayout = new GridLayout(0,2);
                tempCenterPanel.setLayout(editorLayout);
                tempCenterPanel.add(deckTermsComboBox);
                tempCenterPanel.add(replaceTerm);
                tempCenterPanel.add(deckDefinitionsComboBox);
                tempCenterPanel.add(replaceDefinition);
                tempCenterPanel.add(makeEditButton);
                tempCenterPanel.add(newCardButton);
                editorPanel.setBackground(lightGrey);
                editorPanel.add(tempCenterPanel);
                editorUI.add(editorPanel);
                editorPanel.revalidate();
            }
        }
        else if (e.getSource() == deckTermsComboBox) {
            replaceTerm.setText(String.valueOf(deckTermsComboBox.getSelectedItem()));
            editorPanel.revalidate();
        }
        else if (e.getSource() == deckDefinitionsComboBox) {
            replaceDefinition.setText(String.valueOf(deckDefinitionsComboBox.getSelectedItem()));
            editorPanel.revalidate();
        }
        else if (e.getSource() == makeEditButton) {
            CardCreator editSet = CardCreator.getSelectedDeck(nameOfSet);
            String selectedTerm = String.valueOf(deckTermsComboBox.getSelectedItem());
            String newTerm = replaceTerm.getText();
            String newDefinition = replaceDefinition.getText();
            editSet.removeCard(selectedTerm, newTerm, newDefinition);
        }
        else if (e.getSource() == newCardButton) {
            CardCreator editSet = CardCreator.getSelectedDeck(nameOfSet);
            String newTerm = replaceTerm.getText();
            String newDefinition = replaceDefinition.getText();
            editSet.addNewCard(newTerm, newDefinition);
        }
        else if (e.getSource() == mainImportButton) {
            cardImporterUI.setVisible(true);
        }
        else if (e.getSource() == importButton) {
            String url = urlImporter.getText();
            if (url != null && !url.equals("")) {
                try {
                    responseUI.setTitle("Error");
                    URL webURL = new URL(url);
                    CardImporter cI = new CardImporter(webURL);
                    if (!cI.isSuccessful()) {
                        errorMessage.setText("Error: Site not supported");
                        responseUI.setVisible(true);
                    }
                    else {
                        errorMessage.setText("Imported " + urlImporter.getText());
                        responseUI.setTitle("Success");
                        responseUI.setVisible(true);
                    }
                }
                catch (MalformedURLException malURL) {
                    errorMessage.setText("Error: Improper URL (MalformedURLException)");
                    responseUI.setVisible(true);
                }
                catch (Exception otherExceptions) {
                    errorMessage.setText("Error: Something went wrong");
                    responseUI.setVisible(true);
                }
            }
        }
        else if (e.getSource() == okButton) {
            responseUI.setVisible(false);
        }
    }
}
