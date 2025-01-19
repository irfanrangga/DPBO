/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Book;
import java.util.HashMap;
import java.util.List;
public interface IBookService {
    boolean addBook(Book book);
    boolean deleteBook(int bookId, int donaturId);
    boolean isDonaturValid(int donaturId);
    boolean verifyBook(int bookId);
    List<Book> getUnverifiedBooks();

    List<Book> listBuku(int donaturId);
    HashMap<String, Book> getAllBooksByDonatur(int donaturId);
    public Book getBookById(int bookId);

}
