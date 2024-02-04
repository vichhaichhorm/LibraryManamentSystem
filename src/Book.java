public class Book{
    private static int nextIdincre = 1;
    private int id;
    private String title;
    private Author author;
    private int publishedYear;
    private boolean available;
    public Book(String title, Author author, int publishedYear) {
        this.id = nextIdincre++;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.available = true;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public boolean isStatus() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
