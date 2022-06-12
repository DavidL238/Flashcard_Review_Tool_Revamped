import java.util.ArrayList;

public class CardShuffler {
    private CardCreator cardCreator;
    private ArrayList<String> randTerms;
    private ArrayList<Integer> randIndexes;
    private ArrayList<ArrayList<String>> answerChoices;
    private final int sizeOfDeck;
    private int score;

    public CardShuffler(CardCreator cardCreator) {
        this.cardCreator = cardCreator;
        randTerms = new ArrayList<>();
        randIndexes = new ArrayList<>();
        answerChoices = new ArrayList<>();
        score = 0;
        sizeOfDeck = cardCreator.getTerms().size();
    }

    public void shuffleDeck() {
        for (int i = 0; i < sizeOfDeck; i++) {
            boolean addToArray = true;
            while (true) {
                int rand = (int)(Math.random()*sizeOfDeck);
                for (int indexes : randIndexes) {
                    if (indexes == rand) {
                        addToArray = false;
                        break;
                    }
                }
                if (addToArray) {
                    randIndexes.add(rand);
                    break;
                }
            }
        }
        initializeRandTerms();
        initializeAnswers();
    }

    private void initializeRandTerms() {
        for (int indexes : randIndexes) {
            randTerms.add(cardCreator.getTerms().get(indexes));
        }
    }

    private void initializeAnswers() {
        for (int i = 0; i < randIndexes.size(); i++) {
            ArrayList<String> choices = new ArrayList<>();
            ArrayList<Integer> usedIndexes = new ArrayList<>();
            int indexOfFirstTerm = randIndexes.get(0);
            usedIndexes.add(indexOfFirstTerm);
            for (int p = 0; p < 3; p++) {
                while (true) {
                    int rand = (int) (Math.random() * sizeOfDeck);
                    boolean answerUsed = false;
                    for (int indexes : usedIndexes) {
                        if (indexes == rand) {
                            answerUsed = true;
                            break;
                        }
                    }
                    if (!answerUsed) {
                        int placement = (int) (Math.random() * 2);
                        usedIndexes.add(rand, placement);
                        break;
                    }
                }
            }
            for (int idx : usedIndexes) {
                choices.add(cardCreator.getDefinitions().get(idx));
            }
            answerChoices.add(choices);
        }
    }

    public String getTerm() {
        return randTerms.get(0);
    }

    public ArrayList<String> getChoices() {
        return answerChoices.get(0);
    }

    public boolean selectAnswer(String choice) {
        answerChoices.remove(0);
        randTerms.remove(0);
        int index = randIndexes.remove(0);
        if (cardCreator.getDefinitions().get(index).equals(choice)) {
            score += 100;
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }
}
