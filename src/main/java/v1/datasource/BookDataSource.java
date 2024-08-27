package v1.datasource;

import v1.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDataSource {
    public static List<Book> books = new ArrayList<>();

    public static void add(Book book) {
        books.add(book);
    }

    public static void remove(Book book) {
        books.remove(book);
    }

    public static Book getById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public static void update(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(book.getId())) {
                books.set(i, book);
                return;
            }
        }
    }

    public static List<String> getBookTitlesFromIds(List<String> ids) {
        List<String> titles = new ArrayList<>();
        for (String id : ids) {
            Book book = getById(id);
            if (book != null) {
                titles.add(book.getTitle());
            }
        }
        return titles;
    }
}
