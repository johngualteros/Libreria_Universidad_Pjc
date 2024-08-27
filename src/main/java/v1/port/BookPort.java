package v1.port;

import v1.model.Book;

import java.util.List;

public interface BookPort {
    void createBook();
    List<Book> getBooks();
    Book getBook();
    void lendBook();
    void initBooks();
}
