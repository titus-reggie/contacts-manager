package ContactsManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ContactsTesting {
    public static void main(String[] args) throws FileNotFoundException {

        /**
         * Empty ArrayList used to store preloaded contact data
         */
        ArrayList<String> contacts = new ArrayList<>();

        /**
         * Preloaded contact data
         */
//        contacts.add("John Adams | 916-334-7767 |");
//        contacts.add("Abigail Smith | 886-345-7625 |");
//        contacts.add("Rita Hayworth | 887-898-8833 |");
//        contacts.add("Jerry Seinfeld | 298-646-1515 |");
//        contacts.add("Larry David | 310-567-5555 |");
//        contacts.add("George Costanza | 818-545-0978 |");

        /**
         * absolute file path
         */
//        File data = new File("/Users/tituswilliams/IdeaProjects/contacts-manager/contacts.txt");

        /**
         * method called to create contacts.txt file
         */
//        createFiles(data);

        /**
         * Writes data to contacts.txt file
         */
//        writeFile("contacts.txt", contacts);



        Contacts phoneDirectory = new Contacts();

        phoneDirectory.prompt("contacts.txt");

    }

}
