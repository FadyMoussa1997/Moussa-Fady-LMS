
/* 
import java.io.FileOutputStream;
import java.io.File ;
import java.io.ObjectOutputStream;
import java.util.*;


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
Scanner s = new Scanner (System.in);
Scanner s1 = new Scanner (System.in);

int choice = -1 ;


File file = new File ("Library.txt") ;

ArrayList<Books> bo = new ArrayList<Books>() ;
ObjectOutputStream oos = null ;

do {

System.out.println("1.INSERT") ;
System.out.println("2.DELETE") ;
System.out.println("1.DISPLAY") ;
System.out.println("0.EXIT ") ;

System.out.println("Please Enter Your Choice") ;
choice = s.nextInt() ;

switch (choice) {

case 1:
System.out.println("Enter book ID !") ;
int id = s.nextInt() ;


System.out.println("Enter book TITLE !") ;
String title = s1.nextLine();


System.out.println("Enter book AUTHOR !") ;
String author = s1.nextLine() ;

bo.add(new Books(id , title , author)) ;

oos = new ObjectOutputStream(new FileOutputStream(file)) ;
oos.writeObject(bo);
oos.close();
break ;

case 2:
System.out.println("1.DELETE") ;

int leng = bo.size() ;
System.out.println("size is "+leng) ;
break;

case 3:
System.out.println("3.DISPLAY") ;
System.out.println(bo) ;
break;

default:
System.out.println("WRONG CHOISE !!!!") ;
break ;



}




} while(choice!=0) ;




    }
}
*/

/* 
import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);

        int choice = -1;

        File file = new File("Library.txt");
        ArrayList<Books> bo;

        // Load existing data from the file if it exists
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                bo = (ArrayList<Books>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                bo = new ArrayList<>();
            }
        } else {
            bo = new ArrayList<>();
        }

        do {
            System.out.println("1. INSERT");
            System.out.println("2. DELETE");
            System.out.println("3. DISPLAY");
            System.out.println("0. EXIT");

            System.out.println("Please Enter Your Choice");
            choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter book ID!");
                    int id = s.nextInt();

                    System.out.println("Enter book TITLE!");
                    String title = s1.nextLine();

                    System.out.println("Enter book AUTHOR!");
                    String author = s1.nextLine();

                    bo.add(new Books(id, title, author));
                    break;

                case 2:
                    // You can add code here to delete a book by ID if needed.
                    // For now, it just displays the size of the list.
                    int length = bo.size();
                    System.out.println("Size is " + length);
                    break;

                case 3:
                    System.out.println("3. DISPLAY");
                    for (Books book : bo) {
                        System.out.println(book); // Assuming Books class has a proper toString() method
                    }
                    break;

                case 0:
                    // Serialize the ArrayList and exit the program
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                        oos.writeObject(bo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("WRONG CHOICE!!!");
                    break;
            }

        } while (choice != 0);
    }
}
*/

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);

        int choice = -1;

        File file = new File("Library.csv");
        ArrayList<Books> bo;

        // Load existing data from the file if it exists
        if (file.exists()) {
            bo = loadBooksFromFile(file);
        } else {
            bo = new ArrayList<>();
        }

        do {
            System.out.println("1. INSERT");
            System.out.println("2. DELETE");
            System.out.println("3. DISPLAY");
            System.out.println("0. EXIT");

            System.out.println("Please Enter Your Choice");
            choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter book ID!");
                    int id = s.nextInt();

                    System.out.println("Enter book TITLE!");
                    String title = s1.nextLine();

                    System.out.println("Enter book AUTHOR!");
                    String author = s1.nextLine();

                    bo.add(new Books(id, title, author));
                    saveBooksToFile(bo, file);
                    break;

                    case 2:
    System.out.println("Enter book ID to delete:");
    int idToDelete = s.nextInt();

    // Find and remove the book with the specified ID
    boolean removed = false;
    Iterator<Books> iterator = bo.iterator();
    while (iterator.hasNext()) {
        Books book = iterator.next();
        if (book.id == idToDelete) { // Access the id field directly
            iterator.remove();
            removed = true;
            break; // Exit the loop after the first match is removed
        }
    }

    if (removed) {
        System.out.println("Book with ID " + idToDelete + " has been deleted.");
    } else {
        System.out.println("Book with ID " + idToDelete + " not found.");
    } saveBooksToFile(bo, file);
    break;

                

                case 3:
                    System.out.println("3. DISPLAY");
                    displayBooks(bo);
                    break;

                case 0:
                saveBooksToFile(bo, file);
                System.out.println("Goodbye! Data has been saved to the Library file.");
                System.exit(0);
                break;

                default:
                    System.out.println("WRONG CHOICE!!!");
                    break;
            }

            System.out.println("-------------------------------") ;

        } while (choice != 0);
    }

    // Load books from a CSV file
    private static ArrayList<Books> loadBooksFromFile(File file) {
        ArrayList<Books> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    books.add(new Books(id, title, author));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Save books to a CSV file
   // Save books to a CSV file
private static void saveBooksToFile(ArrayList<Books> books, File file) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
        for (Books book : books) {
            writer.println(book.id + "," + book.title + "," + book.author);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    // Display books
    private static void displayBooks(ArrayList<Books> books) {
        for (Books book : books) {
            System.out.println(book);
        }
    }
}
