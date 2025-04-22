package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class NeighborhoodLibrary {

    static Scanner scanner=new Scanner(System.in);
    private static final String FILE_NAME="library.dat";

    public static void main(String[] args) {
        Book[] library=fillLibrary();
        homeMenu(library);
        saveLibrary(library);
    }
    //home menu, lets you navigate to either book check out or book check in
    public static void homeMenu(Book[] books){
        boolean homeRunning=true;
        System.out.println("Hello! Welcome to the library.");
        while(homeRunning){
            printDivider(30);
            int userChoice=askQuestionGetInt("What would you like to do today?\n(1) Show Available Books | (2) Show Checked Out Books | (3) Exit\n");
            switch(userChoice){
                case 1:{
                    availableMenu(books);
                    break;
                }
                case 2:{
                    checkedOutMenu(books);
                    break;
                }
                case 3:{
                    System.out.println("Have a great day!");
                    homeRunning=false;
                    break;
                }
                default:{
                    System.out.println("Error, invalid input.");
                }
            }
        }
    }
    //menu for checking out books, shows available books and allows user to input the book id to check out a book
    public static void availableMenu(Book[] books){
        boolean availableRunning=true;
        while(availableRunning){
            String userChoice=askQuestionGetString("What would you like to do today?\n(C) Checkout Book | (X) Return to Home Menu\n").toUpperCase();
            switch(userChoice){
                case "C":{
                    boolean gettingBookChoice=true;
                    while(gettingBookChoice){
                        printDivider(30);
                        System.out.println("Here are the books that are available right now.\n");
                        for (Book b : books) {
                            if (!(b.isCheckOut())) {
                                System.out.printf("ID:%d | ISBN:%s | Title:%s\n", b.getId(), b.getIsbn(), b.getTitle());
                            }
                        }
                        System.out.println();
                        int bookChoice = askQuestionGetInt("Which book would you like to checkout? Respond with the book's ID or 0 to return to home.\n") - 1;
                        if (bookChoice >= 0 && bookChoice < books.length&&!books[bookChoice].isCheckOut()) {
                            books[bookChoice].checkOut(askQuestionGetString("Please enter your name: "));
                            System.out.printf("Checked out %s to %s.",books[bookChoice].getTitle(),books[bookChoice].getCheckedOutTo());
                            System.out.println("Returning to home menu.");
                            gettingBookChoice=false;
                        } else if (bookChoice == -1) {
                            System.out.println("\nReturning to home menu.");
                            gettingBookChoice=false;
                        } else if (books[bookChoice].isCheckOut()) {
                            System.out.println("That book is already checked out.");
                        } else {
                            System.out.println("Error: invalid input.");
                        }
                    }
                    availableRunning=false;
                    break;
                }
                case "X":{
                    System.out.println("Returning to home menu.");
                    availableRunning=false;
                    break;
                }
                default:{
                    System.out.println("Error, invalid input.");
                }
            }
        }
    }
    //menu for checking in books, checking which books are checked out, allows users to input the book id to return the book
    public static void checkedOutMenu(Book[] books){
        boolean checkedOutRunning=true;
        while(checkedOutRunning){
            String userChoice=askQuestionGetString("What would you like to do today?\n(C) Check In Book | (X) Return to Home Menu\n").toUpperCase();
            switch(userChoice){
                case "C":{
                    boolean gettingBookChoice=true;
                    while(gettingBookChoice){
                        printDivider(30);
                        System.out.println("Here are the books that are checked out right now.\n");
                        for (Book b : books) {
                            if (b.isCheckOut()) {
                                System.out.printf("ID:%d | ISBN:%s | Title:%s Who:%s\n", b.getId(), b.getIsbn(), b.getTitle(),b.getCheckedOutTo());
                            }
                        }
                        System.out.println();
                        int bookChoice = askQuestionGetInt("Which book would you like to check in? Respond with the book's ID or 0 to return to home.\n") - 1;
                        if (bookChoice >= 0 && bookChoice < books.length&&books[bookChoice].isCheckOut()) {
                            System.out.printf("%s checked in %s.",books[bookChoice].getCheckedOutTo(),books[bookChoice].getTitle());
                            books[bookChoice].checkIn();
                            System.out.println("\nReturning to home menu.");
                            gettingBookChoice=false;
                        } else if (bookChoice == -1) {
                            System.out.println("Returning to home menu.");
                            gettingBookChoice=false;
                        } else if (!books[bookChoice].isCheckOut()) {
                            System.out.println("That book is already checked in.");
                        } else {
                            System.out.println("Error: invalid input.");
                        }
                    }
                    checkedOutRunning=false;
                    break;
                }
                case "X":{
                    System.out.println("Returning to home menu.");
                    checkedOutRunning=false;
                    break;
                }
                default:{
                    System.out.println("Error, invalid input.");
                }
            }
        }
    }

    //method for filling library list with books, first tries loading file containing library data, if not found initializes with default library
    public static Book[] fillLibrary(){
        Book[] library;
        library=loadLibrary();
        if(library==null) {
            library = new Book[]{
                    new Book(1, "9780060935467", "To Kill a Mockingbird"),
                    new Book(2, "9780452284234", "1984"),
                    new Book(3, "9781400031702", "The Secret History"),
                    new Book(4, "9781338878929", "Harry Potter and the Sorcerer's Stone"),
                    new Book(5, "9780439023528", "The Hunger Games"),
                    new Book(6, "9781441341709", "Pride and Prejudice"),
                    new Book(7, "9780679420392", "Animal Farm"),
                    new Book(8, "9780307744432", "The Night Circus"),
                    new Book(9, "9780441013593", "Dune"),
                    new Book(10, "9780142004234", "East of Eden"),
                    new Book(11, "9780679734505", "Crime and Punishment"),
                    new Book(12, "9780553381689", "A Game of Thrones"),
                    new Book(13, "9798351145013", "The Great Gatsby"),
                    new Book(14, "9780141441146", "Jane Eyre"),
                    new Book(15, "9780060883287", "One Hundred Years of Solitude"),
                    new Book(16, "9780063081918", "American Gods"),
                    new Book(17, "9780147514011", "Little Women"),
                    new Book(18, "9783464360002", "The Perks of Being a Wallflower"),
                    new Book(19, "9781594631931", "The Kite Runner"),
                    new Book(20, "9780062060624", "The Song of Achilles")
            };
        }
        return library;
    }

    //prints n character long "-" text divider
    public static void printDivider(int n){
        StringBuilder dividerBuilder=new StringBuilder();
        for(int i=0;i<n;i++){
            dividerBuilder.append("-");
        }
        System.out.println(dividerBuilder);
    }
    //input the question you want answered, returns answer, expects string
    public static String askQuestionGetString(String q){
        System.out.print(q);
        return scanner.nextLine();
    }
    //input the question you want answered, returns answer, expects integer
    public static int askQuestionGetInt(String q){
        boolean asking=true;
        int answer=-1;
        while(asking){
            try{
                System.out.print(q);
                answer = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                printDivider(30);
                System.out.println("Error: must enter a number.");
                printDivider(30);
                continue;
            }
            asking=false;
        }
        return answer;
    }
    //saves library to given file
    private static void saveLibrary(Book[] books){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            out.writeObject(books);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //tries loading library data
    private static Book[] loadLibrary(){
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(FILE_NAME))){
            return (Book[]) in.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("No previous data found or failed to load.");
            return null;
        }
    }

}
