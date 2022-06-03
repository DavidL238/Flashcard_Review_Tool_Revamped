import java.util.ArrayList;

public class CardShuffler {
    private CardCreator cardCreator;
    private ArrayList<String> randTerms;
    private ArrayList<Integer> randIndexes;
    private int score;

    public CardShuffler(CardCreator cardCreator) {
        this.cardCreator = cardCreator;
        randTerms = new ArrayList<>();
        randIndexes = new ArrayList<>();
        score = 0;
    }

    public void shuffleDeck(int numTerms) {
        for (int i = 0; i < numTerms; i++) {
            boolean addToArray = true;
            while (true) {
                int rand = (int)(Math.random()*cardCreator.getTerms().size());
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
    }

    private void initializeRandTerms() {
        for (int indexes : randIndexes) {
            randTerms.add(cardCreator.getTerms().get(indexes));
        }
    }

    private boolean chooseAnswer() {
        ArrayList<String> answerChoices = new ArrayList<>();
        answerChoices.add(cardCreator.getDefinitions().get(randIndexes.get(0)));
    }




}
