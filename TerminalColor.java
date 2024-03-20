public class TerminalColor {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printBlack(String text) {
        System.out.println(ANSI_BLACK + text + ANSI_RESET);
    }

    public static void printRed(String text) {
        System.out.println(ANSI_RED + text + ANSI_RESET);
    }

    public static void printGreen(String text) {
        System.out.println(ANSI_GREEN + text + ANSI_RESET);
    }

    public static void printYellow(String text) {
        System.out.println(ANSI_YELLOW + text + ANSI_RESET);
    }

    public static void printBlue(String text) {
        System.out.println(ANSI_BLUE + text + ANSI_RESET);
    }

    public static void printPurple(String text) {
        System.out.println(ANSI_PURPLE + text + ANSI_RESET);
    }

    public static void printCyan(String text) {
        System.out.println(ANSI_CYAN + text + ANSI_RESET);
    }

    public static void printWhite(String text) {
        System.out.println(ANSI_WHITE + text + ANSI_RESET);
    }
}