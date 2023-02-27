package ContactsManager;

import java.util.Scanner;

public class Input {
    private Scanner reader = new Scanner(System.in);

    /**
     * method to get user input data
     * @return returns user input as String
     */
    public String getString() {
        return this.reader.nextLine();
    }
}
