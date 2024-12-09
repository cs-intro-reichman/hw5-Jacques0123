public class Scrabble {

    static final String WORDS_FILE = "dictionary.txt";
    static final int[] SCRABBLE_LETTER_VALUES = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
        1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    static int HAND_SIZE = 10;
    static int MAX_NUMBER_OF_WORDS = 100000;
    static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];
    static int NUM_OF_WORDS;

    public static void init() {
        In in = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
        while (!in.isEmpty()) {
            DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
        }
        System.out.println(NUM_OF_WORDS + " words loaded.");
    }

    public static boolean isWordInDictionary(String word) {
        for (int i = 0; i < NUM_OF_WORDS; i++) {
            if (MyString.subsetOf(word, DICTIONARY[i]) && MyString.subsetOf(DICTIONARY[i], word)) {
                return true;
            }
        }
        return false;
    }

    public static int wordScore(String word) {
        if (word == null || word.isEmpty()) return 0;

        int score = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            score += SCRABBLE_LETTER_VALUES[c - 'a'];
        }

        score *= word.length();

        if (word.length() == HAND_SIZE) {
            score += 50;
        }

        if (word.contains("runi")) {
            score += 1000;
        }

        return score;
    }

    public static String createHand() {
        StringBuilder hand = new StringBuilder();

        for (int i = 0; i < HAND_SIZE - 2; i++) {
            hand.append(MyString.randomStringOfLetters(1));
        }

        hand.append('a');
        hand.append('e');

        char[] handArray = hand.toString().toCharArray();
        for (int i = handArray.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            char temp = handArray[i];
            handArray[i] = handArray[j];
            handArray[j] = temp;
        }

        return new String(handArray);
    }

    public static void playHand(String hand) {
        int score = 0;
        In in = new In();

        while (!hand.isEmpty()) {
            System.out.println("Current Hand: " + MyString.spacedString(hand));
            System.out.println("Enter a word, or '.' to finish playing this hand:");
            String input = in.readString();

            if (input.equals(".")) break;

            if (isWordInDictionary(input) && MyString.subsetOf(input, hand)) {
                int wordScore = wordScore(input);
                score += wordScore;
                System.out.println(input + " earned " + wordScore + " points. Total score: " + score + " points.");
                hand = MyString.remove(hand, input);
            } else {
                System.out.println("Invalid word. Try again.");
            }
        }

        System.out.println("End of hand. Total score: " + score + " points.");
    }

    public static void playGame() {
        init();
        In in = new In();

        while (true) {
            System.out.println("Enter n to deal a new hand, or e to end the game:");
            String input = in.readString();

            if (input.equals("n")) {
                String hand = createHand();
                playHand(hand);
            } else if (input.equals("e")) {
                System.out.println("Thank you for playing!");
                break;
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        testScrabbleScore();
        playGame();
    }

    public static void testScrabbleScore() {
        System.out.println("'cat' -> " + wordScore("cat") + " (expected: 15)");
        System.out.println("'dog' -> " + wordScore("dog") + " (expected: 15)");
        System.out.println("'quiz' -> " + wordScore("quiz") + " (expected: 88)");
        System.out.println("'friendship' -> " + wordScore("friendship") + " (expected: 240)");
        System.out.println("'running' -> " + wordScore("running") + " (expected: 1056)");
    }
}
