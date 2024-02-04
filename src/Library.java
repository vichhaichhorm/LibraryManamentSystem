import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Library{
private String name;
    private String address;
    private List<Book> books;
    public Library(String name, String address) {
        this.name = name;
        this.address = address;
        this.books = new ArrayList<>();
    }
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }
    public void showAllBooks(){
        CellStyle numberStyle=new CellStyle(CellStyle.HorizontalAlign.center);
        Table listTable =new Table(5, BorderStyle.UNICODE_BOX,ShownBorders.ALL);
        System.out.println("=============Show all Books in the Library=============");
        listTable.setColumnWidth(1,20,25);
        listTable.setColumnWidth(2,20,25);
        listTable.setColumnWidth(3,20,25);
        listTable.setColumnWidth(4,20,25);
        listTable.addCell("ID" ,numberStyle);
        listTable.addCell("Title" ,numberStyle);
        listTable.addCell("Author" ,numberStyle);
        listTable.addCell("Published Year" ,numberStyle);
        listTable.addCell("Status" ,numberStyle);
        for (Book book : books) {
            listTable.addCell(String.valueOf(book.getId()), numberStyle);
            listTable.addCell(book.getTitle(), numberStyle);
            listTable.addCell(book.getAuthor().getName(), numberStyle);
            listTable.addCell(String.valueOf(book.getPublishedYear()), numberStyle);
            listTable.addCell(book.isStatus() ? "Available" : "Unavailable", numberStyle);
        }
        System.out.println(listTable.render());
    }
    public void showAvailableBooks(){
        CellStyle availableStyle=new CellStyle(CellStyle.HorizontalAlign.center);
        Table listTable =new Table(5, BorderStyle.UNICODE_BOX,ShownBorders.ALL);
        System.out.println("=============Show only available Books in the Library=============");
        listTable.setColumnWidth(1,20,25);
        listTable.setColumnWidth(2,20,25);
        listTable.setColumnWidth(3,20,25);
        listTable.setColumnWidth(4,20,25);

        listTable.addCell("ID" ,availableStyle);
        listTable.addCell("Title" ,availableStyle);
        listTable.addCell("Author" ,availableStyle);
        listTable.addCell("Published Year" , availableStyle);
        listTable.addCell("Status" ,availableStyle);
        for (Book book : books) {
            if (book.isStatus()) {
                listTable.addCell(String.valueOf(book.getId()), availableStyle);
                listTable.addCell(book.getTitle(), availableStyle);
                listTable.addCell(book.getAuthor().getName(), availableStyle);
                listTable.addCell(String.valueOf(book.getPublishedYear()), availableStyle);
                listTable.addCell("Available", availableStyle);
            }
            else {
                System.out.println("Not add book te!");
            }
        }
        System.out.println(listTable.render());
    }
    private void showUnavailableBooks() {
        CellStyle unavailableStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table listTable = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        System.out.println("=============Show only unavailable Books in the Library=============");
        listTable.setColumnWidth(1, 20, 25);
        listTable.setColumnWidth(2, 20, 25);
        listTable.setColumnWidth(3, 20, 25);
        listTable.setColumnWidth(4, 20, 25);
        listTable.addCell("ID" ,unavailableStyle);
        listTable.addCell("Title" ,unavailableStyle);
        listTable.addCell("Author" ,unavailableStyle);
        listTable.addCell("Published Year" , unavailableStyle);
        listTable.addCell("Status" ,unavailableStyle);
        for (Book book : books) {
            if (!book.isStatus()) {
                listTable.addCell(String.valueOf(book.getId()), unavailableStyle);
                listTable.addCell(book.getTitle(), unavailableStyle);
                listTable.addCell(book.getAuthor().getName(), unavailableStyle);
                listTable.addCell(String.valueOf(book.getPublishedYear()), unavailableStyle);
                listTable.addCell("Unavailable", unavailableStyle);
            }
        }
        System.out.println(listTable.render());
    }
    public void borrowBook(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId && book.isStatus()) {
                book.setAvailable(false);
                showUnavailableBooks();
                System.out.println("Book with ID (" + bookId + ") borrowed successfully!");
                return;
            }
        }
        System.out.println("Book not found for borrowing.");
    }

    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId && !book.isStatus()) {
                book.setAvailable(true);
                System.out.println("Book with ID (" + bookId + ") returned successfully!");
                return;
            }
        }
        System.out.println("Book not found to returned.");
    }
    public void displayMenu(){
        CellStyle cellStyle=new CellStyle(CellStyle.HorizontalAlign.left);
        Table listTable =new Table(8, BorderStyle.UNICODE_BOX,ShownBorders.ALL);

        listTable.setColumnWidth(1,10,25);
        listTable.setColumnWidth(2,10,25);
        listTable.setColumnWidth(3,10,25);
        listTable.setColumnWidth(4,10,25);
        listTable.setColumnWidth(5,10,25);
        listTable.setColumnWidth(6,10,25);
        listTable.setColumnWidth(7,10,25);

        listTable.addCell("1- Add Book",cellStyle);
        listTable.addCell("2- Show All Books",cellStyle);
        listTable.addCell("3- Show Available Books",cellStyle);
        listTable.addCell("4- Borrow Book",cellStyle);
        listTable.addCell("5- Return",cellStyle);
        listTable.addCell("6- Search",cellStyle);
        listTable.addCell("7- Remove",cellStyle);
        listTable.addCell("8- Exit",cellStyle);
        System.out.println(listTable.render());
    }

    public void searchBook(String nameToSearch) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book title to search: ");
        nameToSearch = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(nameToSearch)) {
                // Display information about the matching book
                System.out.println("Book found:");
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor().getName());
                System.out.println("Published Year: " + book.getPublishedYear());
                System.out.println("Status: " + (book.isStatus() ? "Available" : "Unavailable"));
                return; // Stop searching after finding the first match
            }
        }
        System.out.println("Book with title '" + nameToSearch + "' not found.");
    }
}
