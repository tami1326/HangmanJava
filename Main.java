import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {
    "+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int mistakes = 0;
        int correctLettersGuessed = 0;
        int prevoiusCorrectLettersGuessed = 0;

        String currentWord = words[pickWord()];
        System.out.println(gallows[mistakes]);
        String[]placeholder = new String[currentWord.length()];

        System.out.print("Word:\t");

        for (int i = 0; i < currentWord.length(); ++i) {
            placeholder[i] = "_";
        }
        for (int i = 0; i < placeholder.length; ++i) {
            System.out.print(placeholder[i] + " ");
        }

        System.out.print("\nMisses:\t");
        System.out.print("\nGuess:\t");

        String missedLetters = "";

        while (mistakes < 6) {
            String currentLetter = scan.nextLine();
            prevoiusCorrectLettersGuessed += checkLetter(currentLetter, currentWord, placeholder);
            if (prevoiusCorrectLettersGuessed > correctLettersGuessed) {
                correctLettersGuessed = prevoiusCorrectLettersGuessed;
            } else {
                mistakes += 1;
                missedLetters += currentLetter;
            }
            System.out.println(gallows[mistakes]);

            printPlaceholder(placeholder);

            printMissedGuesses(missedLetters);

            if (correctLettersGuessed == currentWord.length()) {
                System.out.println("\nYou win!");
                System.exit(0);
            }
            System.out.print("\nGuess:\t");
        }

        if (mistakes == 6) {
            System.out.println("\nYou lost");
        }

        scan.close();
    }

    public static int pickWord() {
        int randomWord = (int) (Math.random() * words.length + 1);
        return randomWord;
    }

    public static int checkLetter(String currentLetter, String currentWord, String[] placeholder) {
        int guesses = 0;
        for (int i = 0; i < currentWord.length(); ++i) {
            if (currentWord.charAt(i) == currentLetter.charAt(0)) {
                placeholder[i] = currentLetter;
                guesses++;
            }
        }
        return guesses;
    }

    public static void printPlaceholder(String[] placeholder) {
        System.out.print("Word:\t");
        for (int i = 0; i < placeholder.length; ++i) {
            System.out.print(placeholder[i] + " ");
        }
    }

    public static void printMissedGuesses(String missedLetters) {
        System.out.print("\nMisses:\t" + missedLetters);
    }

}
