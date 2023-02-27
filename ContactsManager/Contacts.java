package ContactsManager;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Contacts {


    public static void prompt(String fileName){
        Scanner reader = new Scanner(System.in);
        System.out.println("");
        System.out.println("""
                1. View contacts.
                2. Add a new contact.
                3. Search a contact by name.
                4. Delete an existing contact.
                5. Exit.
                Enter an option (1, 2, 3, 4 or 5):
                """);
            String result = reader.nextLine();
        if(result.equals("1")){
            displayAllContacts(fileName);
           prompt(fileName);
        } else if (result.equals("2")) {
            System.out.println("Enter FIRST and LAST name:");
            String name = reader.nextLine();
            System.out.println("Enter phone number: ");
            String number = reader.nextLine();
            addContactToFile(fileName, name, number);
            prompt(fileName);
        } else if(result.equals("3")) {
            System.out.println("Enter the name of contact: ");
            String name = reader.nextLine();
            System.out.println("");
            searchContacts(fileName, name);
            prompt(fileName);
        } else if(result.equals("4")) {
            System.out.println("Enter the FIRST and LAST name of contact: ");
            String name = reader.nextLine();
            System.out.println("");
            deleteFromArrayList(fileName, name);
            prompt(fileName);
        } else if(result.equals("5")) {
            System.out.println("Exiting...");
        } else {
            System.out.println("Please enter a valid choice");
            prompt(fileName);
        }

    }


    public static void addContactToFile(String fileName, String name, String number) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.printf("%s | %s%n", name, number);
            pw.flush();
            pw.close();
        }
        catch (IOException ex){
            System.out.println("Error - cannot write to file: " + fileName);
        }
    }



    public static void displayAllContacts(String fileName) {
        System.out.printf("""
                
                Name  |  Phone number
                ------------------
                """);
        try {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        while(reader.hasNextLine()) {
            String data = reader.nextLine();
            System.out.println(data);
        }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static ArrayList<String> addContactsArrayList(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                list.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;

    }

    public static void deleteFromArrayList(String fileName, String search){
        ArrayList<String> result = addContactsArrayList(fileName);
        for(int i = 0; i < result.size();  i++){
            if(result.get(i).contains(search)){
                result.remove(i);
                writeFile(fileName, result);
            }
        }
    }

    public static void searchContacts(String fileName, String search){
        ArrayList<String> result = addContactsArrayList(fileName);
        for(int i = 0; i < result.size();  i++){
            if(result.get(i).contains(search)){
                System.out.println(result.get(i));
            }

        }
    }

    // Create contacts .txt file
    public static void createFiles(File data){
        boolean result;

        try
        {
            result = data.createNewFile();
            if(result) {
                System.out.println("file created: " + data.getCanonicalPath());
            } else {
                System.out.println("file already exists at location: " + data.getCanonicalPath());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    // Write to contacts file
    public static void writeFile(String fileName, ArrayList<String> data) {
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);
            for(int i = 0; i < data.size(); i++){
                pw.println(data.get(i));
            }
            pw.close();
        }
        catch (IOException ex){
            System.out.println("Error - cannot write to file: " + fileName);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Creating  contact information and empty arraylist to store contact info.
        ArrayList<String> contacts = new ArrayList<>();

        contacts.add("John Adams: 916-334-7767");
        contacts.add("Abigail Smith: 886-345-7625");
        contacts.add("Rita Hayworth: 887-898-8833");
        contacts.add("Jerry Seinfeld: 298-646-1515");
        contacts.add("Larry David: 310-567-5555");
        contacts.add("George Costanza: 818-545-0978");

        // New file path
        File data = new File("/Users/tituswilliams/IdeaProjects/contacts-manager/contacts.txt");
//        File data = new File("/Users/tituswilliams/IdeaProjects/codeup-java-exercises/contacts.txt");
//
//        createFiles(data);

        /**
         * Writes data to contacts.txt file
         */
//        writeFile("contacts.txt", contacts);

        Contacts phoneDirectory = new Contacts();

//        phoneDirectory.prompt("contacts.txt");

    }
}
