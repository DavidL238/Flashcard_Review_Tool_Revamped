public class FlashcardRunner {
    public static void main(String[] args) {
        try {
            CardCreator.extractAllSaves();
            CardCreator.printAllDeckNames();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        StudyToolUI studyTool = new StudyToolUI();
    }
}
