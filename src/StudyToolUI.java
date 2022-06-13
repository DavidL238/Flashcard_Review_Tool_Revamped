import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import java.net.*;

public class StudyToolUI implements ActionListener {
    private JCheckBox DARKTheme;
    private JFrame mainUI, settingsUI, creationUI, selectionUI, editorUI, studyUI, matchTermsUI, cardImporterUI, responseUI;
    private JPanel menuPanel, flashPanel, creationPanel, settingsPanel, responsePanel;
    private JPanel editorPanel;
    private JButton cardsIconButton, settingsIconButton, flashcardDisplayButton, nextButton, previousButton, newDeckButton, createDeckButton,
            mainStudyButton, confirmStudyButton, matchTermsButton, confirmDeckButton, editDeckButton,
            confirmEditButton, finalizeEditButton, newCardButton, mainImportButton, importButton, okButton;
    private JTextField errorMessage, supportedSites;
    private JTextArea urlImporter, deckName, deckTerm, deckDefinition, replaceTerm, replaceDefinition, flashcardInformation;
    private JLabel welcomeLabel, deckNameLabel, deckTermLabel, deckDefinitionLabel, flashcardIndex;
    private JComboBox deckComboBox, deckTermsComboBox, deckDefinitionsComboBox;
    private final Color DARK, LIGHT, DARK_GREY, LIGHT_GREY;
    private final Border TRANSPARENT, TEXT_BORDER;
    private ArrayList<String> selectedTerms, selectedDefinitions;
    private int index, score;
    private String nameOfSet;
    private boolean isDARK;

    public StudyToolUI() {
        isDARK = false;
        DARK = new Color(12,12,12);
        DARK_GREY = new Color(40,40,40);
        LIGHT = new Color(255,255,255);
        LIGHT_GREY = new Color(211, 211, 211);
        Color temp = new Color(255, 0, 0);
        TRANSPARENT = javax.swing.BorderFactory.createEmptyBorder();
        TEXT_BORDER = new LineBorder(DARK, 1, true);

        mainUI = createJFrame("Java Study Tool", 852, 720);
        mainUI.setVisible(true);
        settingsUI = createJFrame("Settings", 600, 600);
        cardImporterUI = createJFrame("Card Importer", 300, 300);
        creationUI = createJFrame("Deck Creator", 600, 600);
        selectionUI = createJFrame("Select Deck", 300, 90);
        editorUI = createJFrame("Edit Deck", 600, 600);
        studyUI = createJFrame("Flashcards", 600, 400);
        matchTermsUI = createJFrame("Multiple Choice", 800, 600);
        responseUI = createJFrame("Error", 400, 120);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        menuPanel.setVisible(true);
        menuPanel.setBackground(LIGHT_GREY);

        creationPanel = new JPanel();
        GroupLayout creationLayout = new GroupLayout(creationPanel);
        creationLayout.setAutoCreateGaps(true);
        creationLayout.setAutoCreateContainerGaps(true);
        creationPanel.setLayout(creationLayout);
        creationPanel.setVisible(false);
        creationPanel.setBackground(LIGHT_GREY);

        flashPanel = new JPanel();
        flashPanel.setVisible(true);
        flashPanel.setBackground(LIGHT);

        settingsPanel = new JPanel();
        settingsPanel.setVisible(false);
        settingsPanel.setBackground(LIGHT);

        responsePanel = new JPanel();
        responsePanel.setBackground(LIGHT);

        editorPanel = new JPanel();
        editorPanel.setBackground(LIGHT_GREY);

        DARKTheme = new JCheckBox("DARK Theme");
        DARKTheme.setVisible(true);
        DARKTheme.addActionListener(this);
        DARKTheme.setFocusPainted(false);
        DARKTheme.setPreferredSize(new Dimension(100, 25));
        DARKTheme.setBackground(temp);

        cardsIconButton = createIconJButton("images\\flashcards.png");
        settingsIconButton = createIconJButton("images\\gear.png");

        mainImportButton = createTextJButton("Import Flash Cards");
        mainImportButton.setBackground(LIGHT_GREY);
        mainImportButton.setOpaque(true);

        newDeckButton = createTextJButton("Create New Deck");
        newDeckButton.setBackground(LIGHT_GREY);
        newDeckButton.setOpaque(true);

        createDeckButton = createTextJButton("Finalize");
        createDeckButton.setBackground(LIGHT);
        createDeckButton.setOpaque(true);

        editDeckButton = createTextJButton("Edit Existing Deck");
        editDeckButton.setBackground(LIGHT_GREY);
        editDeckButton.setOpaque(true);

        newCardButton = createTextJButton("Create Card");
        newCardButton.setBackground(LIGHT_GREY);
        newCardButton.setOpaque(true);

        confirmEditButton = createTextJButton("Edit");
        confirmEditButton.setBackground(LIGHT);
        confirmEditButton.setOpaque(true);

        finalizeEditButton = createTextJButton("Make Edit");
        finalizeEditButton.setBackground(LIGHT_GREY);
        finalizeEditButton.setOpaque(true);

        mainStudyButton = createTextJButton("Study Flashcards");
        mainStudyButton.setBackground(LIGHT_GREY);
        mainStudyButton.setOpaque(true);

        matchTermsButton = createTextJButton("Match The Terms");
        matchTermsButton.setBackground(LIGHT_GREY);
        matchTermsButton.setOpaque(true);
        
        confirmDeckButton = createTextJButton("Confirm Selection");
        confirmStudyButton = createTextJButton("Confirm Selection");

        flashcardDisplayButton = createTextJButton("");
        flashcardDisplayButton.setBackground(LIGHT_GREY);
        flashcardDisplayButton.setOpaque(true);

        nextButton = createTextJButton("Next");
        nextButton.setPreferredSize(new Dimension(100, 400));
        previousButton = createTextJButton("Previous");
        previousButton.setPreferredSize(new Dimension(100, 400));

        importButton = createTextJButton("Import Flash Cards");
        okButton = createTextJButton("OK");
        okButton.setBackground(LIGHT_GREY);
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

        flashcardInformation = new JTextArea("");
        flashcardInformation.setLineWrap(true);
        flashcardInformation.setEditable(false);
        flashcardInformation.setFocusable(false);

        deckNameLabel = new JLabel("Name of Deck:");
        deckTermLabel = new JLabel("Deck Term:");
        deckDefinitionLabel = new JLabel("Deck Definition:");
        flashcardIndex = new JLabel("", SwingConstants.CENTER);

        mainUI.getContentPane().add(menuPanel, BorderLayout.LINE_START);
        mainUI.getContentPane().add(flashPanel, BorderLayout.CENTER);
        menuPanel.add(cardsIconButton);
        menuPanel.add(settingsIconButton);
        flashPanel.add(welcomeLabel);
        flashPanel.add(mainStudyButton);
        flashPanel.add(matchTermsButton);
        flashPanel.add(newDeckButton);
        flashPanel.add(editDeckButton);
        flashPanel.add(mainImportButton);
        mainUI.revalidate();

        settingsUI.add(settingsPanel, BorderLayout.CENTER);
        settingsPanel.add(DARKTheme);

        studyUI.add(flashcardIndex, BorderLayout.NORTH);
        studyUI.add(flashcardInformation, BorderLayout.CENTER);
        studyUI.add(flashcardDisplayButton, BorderLayout.SOUTH);
        studyUI.add(previousButton, BorderLayout.WEST);
        studyUI.add(nextButton, BorderLayout.EAST);

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
        newText.setBorder(TRANSPARENT);
        return newText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == DARKTheme) {
            if (!isDARK) {
                DARKTheme.setForeground(LIGHT);
                DARKTheme.setBackground(DARK_GREY);
                menuPanel.setBackground(DARK);
                flashPanel.setBackground(DARK_GREY);
                settingsPanel.setBackground(DARK_GREY);
                mainUI.setBackground(DARK);
            }
            else {
                DARKTheme.setForeground(DARK);
                DARKTheme.setBackground(LIGHT);
                menuPanel.setBackground(LIGHT_GREY);
                flashPanel.setBackground(LIGHT);
                settingsPanel.setBackground(LIGHT);
                mainUI.getContentPane().setBackground(LIGHT);
            }
            isDARK = !isDARK;
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
            revalidateSelectionUI();
            selectionUI.getContentPane().add(confirmStudyButton, BorderLayout.SOUTH);
            selectionUI.revalidate();
            selectionUI.repaint();
        }
        else if (e.getSource() == confirmStudyButton) {
            index = 0;
            selectionUI.setVisible(false);
            studyUI.setVisible(true);
            nameOfSet = String.valueOf(deckComboBox.getSelectedItem());
            selectionUI.remove(deckComboBox);
            System.out.println(nameOfSet);
            CardCreator cardCreator = CardCreator.getSelectedDeck(nameOfSet);
            selectedTerms = cardCreator.getTerms();
            selectedDefinitions = cardCreator.getDefinitions();
            flashcardIndex.setText("1/" + selectedTerms.size());
            flashcardInformation.setText("Term: " + selectedTerms.get(index));
            flashcardDisplayButton.setText("Click me to Flip Card");
        }
        else if (e.getSource() == matchTermsButton) {
            revalidateSelectionUI();
            selectionUI.getContentPane().add(confirmDeckButton, BorderLayout.SOUTH);
            selectionUI.revalidate();
            selectionUI.repaint();
        }
        else if (e.getSource() == confirmDeckButton) {
            selectionUI.setVisible(false);
            matchTermsUI.setVisible(true);
            String nameOfSet = (Objects.requireNonNull(deckComboBox.getSelectedItem())).toString();
            selectionUI.remove(deckComboBox);
            CardCreator cC = CardCreator.getSelectedDeck(nameOfSet);
            assert cC != null;
            CardShuffler cS = new CardShuffler(cC);
            ArrayList<String> choices = cS.getChoices();
            JTextArea term = new JTextArea();
            term.setText(cS.getTerm());
            term.setPreferredSize(new Dimension(600, 200));
            Font buttonFont = new Font("Montserrat", Font.PLAIN, 16);
            Font questionFont = new Font("Montserrat", Font.PLAIN, 32);
            term.setFont(questionFont);
            term.setEditable(false);
            term.setOpaque(false);

            JPanel topPanel = new JPanel();
            score = cS.getScore();
            JTextField scoreDisplay = createJTextField("Score: " + score, 24);
            topPanel.add(scoreDisplay, BorderLayout.EAST);

            JPanel choicePanel = new JPanel();
            JButton choiceOne = createTextJButton(choices.get(0));
            choiceOne.setFont(buttonFont);
            Dimension buttonSize = new Dimension(300, 150);
            choiceOne.setPreferredSize(buttonSize);
            choiceOne.setOpaque(true);
            choiceOne.setBackground(Color.decode("#ff3355"));
            JButton choiceTwo = createTextJButton(choices.get(1));
            choiceTwo.setFont(buttonFont);
            choiceTwo.setPreferredSize(buttonSize);
            choiceTwo.setOpaque(true);
            choiceTwo.setBackground(Color.decode("#45a3e5"));
            JButton choiceThree = createTextJButton(choices.get(2));
            choiceThree.setFont(buttonFont);
            choiceThree.setOpaque(true);
            choiceThree.setPreferredSize(buttonSize);
            choiceThree.setBackground(Color.decode("#ffc00a"));
            JButton choiceFour = createTextJButton(choices.get(3));
            choiceFour.setFont(buttonFont);
            choiceFour.setOpaque(true);
            choiceFour.setPreferredSize(buttonSize);
            choiceFour.setBackground(Color.decode("#66bf39"));
            System.out.println("1. " + choiceOne.getText() + "2. " + choiceTwo.getText() + "3. " + choiceThree.getText() + "4. " + choiceFour.getText());
            choicePanel.add(term);
            choicePanel.add(choiceOne);
            choicePanel.add(choiceTwo);
            choicePanel.add(choiceThree);
            choicePanel.add(choiceFour);
            matchTermsUI.add(topPanel, BorderLayout.NORTH);
            matchTermsUI.add(choicePanel, BorderLayout.CENTER);
            matchTermsUI.revalidate();
            matchTermsUI.repaint();
        }
        else if (e.getSource() == flashcardDisplayButton) {
            String text = flashcardInformation.getText();
            if (text.startsWith("Term")) {
                flashcardInformation.setText("Definition: " + selectedDefinitions.get(index));
            }
            else {
                flashcardInformation.setText("Term: " + selectedTerms.get(index));
            }
        }
        else if (e.getSource() == nextButton) {
            if (index < selectedTerms.size() - 1) {
                index++;
                flashcardIndex.setText((index+1) + "/" + selectedTerms.size());
                flashcardInformation.setText("Term: " + selectedTerms.get(index));
            }
        }
        else if (e.getSource() == previousButton) {
            if (index > 0) {
                index--;
                flashcardIndex.setText((index+1) + "/" + selectedTerms.size());
                flashcardInformation.setText("Term: " + selectedTerms.get(index));
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
            revalidateSelectionUI();
            selectionUI.getContentPane().add(confirmEditButton, BorderLayout.SOUTH);
            selectionUI.revalidate();
            selectionUI.repaint();
        }
        else if (e.getSource() == confirmEditButton) {
            selectionUI.setVisible(false);
            selectionUI.remove(deckComboBox);
            editorUI.setVisible(true);
            nameOfSet = String.valueOf(deckComboBox.getSelectedItem());
            CardCreator editSet = CardCreator.getSelectedDeck(nameOfSet);
            if (editSet != null) {
                selectedTerms = editSet.getTerms();
                selectedDefinitions = editSet.getDefinitions();
                revalidateTermCombo();
                revalidateDefinitionCombo();
                replaceTerm = new JTextArea();
                replaceTerm.setLineWrap(true);
                replaceTerm.setBorder(TEXT_BORDER);
                replaceDefinition = new JTextArea();
                replaceDefinition.setLineWrap(true);
                replaceDefinition.setBorder(TEXT_BORDER);
                editorPanel.removeAll();
                editorPanel.revalidate();
                editorPanel.repaint();
                GridLayout editorLayout = new GridLayout(0,2);
                editorPanel.setLayout(editorLayout);
                editorPanel.add(deckTermsComboBox);
                editorPanel.add(deckDefinitionsComboBox);
                editorPanel.add(replaceTerm);
                editorPanel.add(replaceDefinition);
                editorPanel.add(finalizeEditButton);
                editorPanel.add(newCardButton);
                editorUI.add(editorPanel);
            }
        }
        else if (e.getSource() == deckTermsComboBox) {
            index = deckTermsComboBox.getSelectedIndex();
            replaceTerm.setText(selectedTerms.get(index));
        }
        else if (e.getSource() == deckDefinitionsComboBox) {
            index = deckDefinitionsComboBox.getSelectedIndex();
            replaceDefinition.setText(selectedDefinitions.get(index));
        }
        else if (e.getSource() == finalizeEditButton) {
            CardCreator editSet = CardCreator.getSelectedDeck(nameOfSet);
            int indexOfTerm = deckTermsComboBox.getSelectedIndex();
            System.out.println("tIdx " + indexOfTerm);
            int indexOfDefinition = deckDefinitionsComboBox.getSelectedIndex();
            System.out.println("dIdx " + indexOfDefinition);
            String newTerm = replaceTerm.getText();
            String newDefinition = replaceDefinition.getText();
            if (editSet != null) {
                if (newTerm.length() > 0) {
                    editSet.replaceCard(indexOfTerm, newTerm, true);
                }
                if (newDefinition.length() > 0) {
                    editSet.replaceCard(indexOfDefinition, newDefinition, false);
                }
            }
            revalidateTermCombo();
            revalidateDefinitionCombo();
        }
        else if (e.getSource() == newCardButton) {
            CardCreator editSet = CardCreator.getSelectedDeck(nameOfSet);
            String newTerm = replaceTerm.getText();
            String newDefinition = replaceDefinition.getText();
            editSet.addNewCard(newTerm, newDefinition);
            revalidateTermCombo();
            revalidateDefinitionCombo();
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

    public void revalidateTermCombo() {
        try {
            editorPanel.remove(deckTermsComboBox);
        }
        catch (Exception e) {}
        deckTermsComboBox = new JComboBox();
        for (int i = 0; i < selectedTerms.size(); i++) {
            String temp = selectedTerms.get(i);
            if (temp.length() > 30) {
                temp = temp.substring(0, 30) + "...";
            }
            deckTermsComboBox.addItem((i+1) + ". " + temp);
        }
        for (Component component : deckTermsComboBox.getComponents())
        {
            if (component instanceof JButton) {
                deckTermsComboBox.remove(component);
            }
        }
        deckTermsComboBox.addActionListener(this);
        deckTermsComboBox.revalidate();
        deckTermsComboBox.repaint();
        editorPanel.add(deckTermsComboBox, 0);
        editorPanel.revalidate();
        editorPanel.repaint();
    }

    public void revalidateDefinitionCombo() {
        try {
            editorPanel.remove(deckDefinitionsComboBox);
        }
        catch (Exception e) {}
        deckDefinitionsComboBox = new JComboBox();
        for (int i = 0; i < selectedDefinitions.size(); i++) {
            String temp = selectedDefinitions.get(i);
            if (temp.length() > 30) {
                temp = temp.substring(0, 30) + "...";
            }
            deckDefinitionsComboBox.addItem((i+1) + ". " + temp);
        }
        for (Component component : deckDefinitionsComboBox.getComponents())
        {
            if (component instanceof JButton) {
                deckDefinitionsComboBox.remove(component);
            }
        }
        deckDefinitionsComboBox.addActionListener(this);
        deckDefinitionsComboBox.revalidate();
        deckDefinitionsComboBox.repaint();
        editorPanel.add(deckDefinitionsComboBox, 0, 1);
        editorPanel.revalidate();
        editorPanel.repaint();
    }

    public void revalidateSelectionUI() {
        try {
            selectionUI.remove(confirmStudyButton);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            selectionUI.remove(confirmEditButton);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            selectionUI.remove(confirmDeckButton);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        ArrayList<String> allTitles = CardCreator.getAllTitles();
        deckComboBox = new JComboBox();
        for (String allTitle : allTitles) {
            deckComboBox.addItem(allTitle);
        }
        deckComboBox.setEditable(false);
        selectionUI.getContentPane().add(deckComboBox, BorderLayout.NORTH);
        selectionUI.setVisible(true);
    }
}
