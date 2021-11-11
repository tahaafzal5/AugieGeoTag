package src;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Utility.displayMenu();
        Utility.handleUserMenuChoice(input);

        input.close();
    }
}