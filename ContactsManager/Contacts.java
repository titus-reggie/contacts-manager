package ContactsManager;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contacts {


    /**
     * method prompts user and calls all methods necessary for given functionality
     * @param fileName name of file
     */
    public static void prompt(String fileName){
        Input reader = new Input();

        System.out.println("");
        System.out.println("""
                1. View contacts.
                2. Add a new contact.
                3. Search a contact by name.
                4. Delete an existing contact.
                5. Exit.
                Enter an option (1, 2, 3, 4 or 5)""");
            String result = reader.getString();
        if(result.equals("1")){
            displayAllContacts(fileName);
           prompt(fileName);
        } else if (result.equals("2")) {
            System.out.println("Enter FIRST and LAST name:");
            String name = reader.getString();
            ArrayList<String> result2 = addContactsArrayList(fileName);
            for(int i = 0; i < result2.size();  i++){
                if(result2.get(i).contains(name)) {
                    System.out.println("The name you have entered is already a Contact would you still like to continue? [Y/N]");
                    String yesNo = reader.getString();
                    if (yesNo.equalsIgnoreCase("y")) {
                        deleteFromArrayList(fileName, name);
                        System.out.println("Enter phone number: ");
                        String number = reader.getString();
                        addContactToFile(fileName, name, number);
                        prompt(fileName);
                    } else if (yesNo.equalsIgnoreCase("n")) {
                        prompt(fileName);
                    }
                }
            }
            System.out.println("Enter phone number: ");
            String number = reader.getString();
            addContactToFile(fileName, name, number);
            prompt(fileName);
        } else if(result.equals("3")) {
            System.out.println("Enter the name of contact: ");
            String name = reader.getString();
            System.out.println("");
            searchContacts(fileName, name);
            prompt(fileName);
        } else if(result.equals("4")) {
            System.out.println("Enter the FIRST and LAST name of contact: ");
            String name = reader.getString();
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


    /**
     * method to add contact to file
     * @param fileName name of file
     * @param name first and last name to add to file
     * @param number phone number to add to file
     */
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


    /**
     * method to display all contacts in file
     * @param fileName name of file
     */
    public static void displayAllContacts(String fileName){
        int width = longestString(fileName) - 13;
        int nameWidth = width - 4;
        String string = "";
        String headingName = "Name";
        String headingNumber = " Phone Number";
        String strHeading = headingName + String.format("%"+nameWidth+"s", "|") + headingNumber + " |";
        String strBreak = "-".repeat(longestString(fileName) + 2);
        System.out.println(strHeading);
        System.out.println(strBreak);
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                string = data;
                String[] parts = string.split("\\|");
                String part1 = parts[0];
                String part2 = parts[1];
                if (part1.length() < longestString(fileName)) {
                    int difference = width - part1.length();
                    String str = part1 + String.format("%" + difference + "s", "|") + part2 + " |" + "\n";
                    System.out.printf(str);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * method to find the longest string of contact information
     * @param fileName name of file
     * @return returns the longest strength length as integer
     */
    public static int longestString(String fileName){
        List<String> strings = new ArrayList<>();
        String string = "";
        int maxResult = 0;

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                strings.add(data);
                int max = strings.stream().map(String::length).max(Integer::compareTo).get();
                maxResult = max;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return maxResult + 2;
    }

    /**
     * method adds contact to file
     * @param fileName name of file
     * @return contact information returned in Arraylist
     */
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

    /**
     * method to delete contact from file
     * @param fileName name of file
     * @param search contact name to delete
     */
    public static void deleteFromArrayList(String fileName, String search){
        ArrayList<String> result = addContactsArrayList(fileName);
        for(int i = 0; i < result.size();  i++){
            if(result.get(i).contains(search)){
                result.remove(i);
                writeFile(fileName, result);
            }
        }
    }

    /**
     * method that searches for contact
     * @param fileName name of file
     * @param search contact to search
     */
    public static void searchContacts(String fileName, String search){
        ArrayList<String> result = addContactsArrayList(fileName);
        for(int i = 0; i < result.size();  i++){
            if(result.get(i).contains(search)){
                System.out.println(result.get(i) + " |");
            }

        }
    }


    /**
     * method creates .txt file
     * @param data file path
     */
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


    /**
     * method writes to file
     * @param fileName name of file
     * @param data Arraylist of data to write to file
     */
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

}
