import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.println("========= SET UP LIBRARY =========");
       String libraryName;
       String libraryAddress;
       String libraryNamePattern = "^[0-9A-Za-z]+$"; // user can input text and number
       String libraryAddressPattern = "^[A-Za-z ]+$"; // user can input only text
       while (true) {
          System.out.print("=> Enter Library's Name: ");
          libraryName = scanner.nextLine();
          if (libraryName.matches(libraryNamePattern)) {
             break;
          } else {
             System.out.println("Invalid input. Please enter a name containing only letters.");
          }
       }
       while (true) {
          System.out.print("=> Enter Library's Address: ");
          libraryAddress = scanner.nextLine();
          if (libraryAddress.matches(libraryAddressPattern)) {
             break;
          } else {
             System.out.println("Invalid input. Please enter a name containing only letters.");
          }
       }
       Library library = new Library(libraryName, libraryAddress);
       // set up local time and current date
       LocalDateTime current =LocalDateTime.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
       String formatted =current.format(formatter);

       System.out.println("\"" + libraryName.toUpperCase() + "\" Library is already created in \"" +
             libraryAddress.toUpperCase() + "\" address successfully on " + formatted);
       int choice=0;
       do{
          System.out.println("\n========= " + libraryName.toUpperCase() + ", " + libraryAddress.toUpperCase() + " =========");
          library.displayMenu();
          System.out.print("Enter your choice: ");
          choice = scanner.nextInt();
          scanner.nextLine();
          switch (choice) {
              case 1:
                  System.out.print("Enter Book Title:");
                  String title = scanner.nextLine();
                  String authorName;
                  while (true) {
                      String authorNamePattern = "^[A-Za-z ]+$";
                      System.out.print("Enter Author Name: ");
                      authorName = scanner.nextLine();
                      if (authorName.matches(authorNamePattern)) {
                          break;
                      } else {
                          System.out.println("Invalid input. Please enter a name only text...! ");
                      }
                  }
                  System.out.print("Enter Year Active: ");
                  String authorYearActive = scanner.nextLine();
                  System.out.print("Enter Published Year: ");
                  int publishedYear = scanner.nextInt();
                  Author author = new Author(authorName, authorYearActive);
                  Book newBook = new Book(title, author, publishedYear);
                  library.addBook(newBook);
                  break;
              case 2:
                  library.showAllBooks();
                  break;

              case 3:
                  library.showAvailableBooks();
                  break;
              case 4:
                System.out.print("Enter Book ID to borrow: ");
                int borrowId = scanner.nextInt();
                library.borrowBook(borrowId);
                break;
              case 5:
                  System.out.print("Enter Book ID to return: ");
                  int returnId = scanner.nextInt();
                  library.returnBook(returnId);
                  break;
              case 6:
                  System.out.println("======Search Book via book title=======");
                  String nameToSearch=" ";
                  library.searchBook(nameToSearch);
                  break;
              case 7:
                  System.out.println(" ( ͡° ͜ʖ ͡°)=> Process the ended <=( ͡° ͜ʖ ͡°)");
          }
       }while (choice!=7);
    }

}
