import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/16/12
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class BookCatalogueTest {

    BookCatalogue newBookCatalogue;
    
    public BufferedReader addBooksSetup() throws IOException {
        newBookCatalogue = new BookCatalogue();

        BufferedReader mockReader = mock(BufferedReader.class);
        String strBook1 = "The Art of War / Sun Tzu / 3425 / 2";
        String strBook2 = "Oliver Twist / Charles Dickens / 3482 / 3";
        String strBook3 = "The Art of Faking / Charles Fakename / 3213 / 1";
        when(mockReader.readLine()).thenReturn(strBook1).thenReturn(strBook2).
                thenReturn(strBook3).thenReturn(null);
        return mockReader;
    }

    @Test
    public void addBooksGivesCorrectBooksWhenGetBooksIsCalled() throws IOException {
        BufferedReader mockReader = addBooksSetup();

        ArrayList<Book> bookArray = new ArrayList<Book>();
        bookArray.add(new Book("THE ART OF WAR","SUN TZU","3425"));
        bookArray.add(new Book("OLIVER TWIST","CHARLES DICKENS","3482"));
        bookArray.add(new Book("THE ART OF FAKING","CHARLES FAKENAME","3213"));

        newBookCatalogue.addItems(mockReader);
        assertEquals(bookArray, newBookCatalogue.getItems());
    }

    @Test
    public void addBooksGivesCorrectBookTitles() throws IOException {
        BufferedReader mockReader = addBooksSetup();

        ArrayList<Book> bookArray = new ArrayList<Book>();
        bookArray.add(new Book("THE ART OF WAR","SUN TZU","3425"));
        bookArray.add(new Book("OLIVER TWIST","CHARLES DICKENS","3482"));

        newBookCatalogue.addItems(mockReader);
        assertEquals(bookArray.get(1).getTitle(), ((Book)newBookCatalogue.getItems().get(1)).getTitle());
    }

    @Test
    public void addBooksGivesCorrectBookAuthors() throws IOException {
        BufferedReader mockReader = addBooksSetup();

        ArrayList<Book> bookArray = new ArrayList<Book>();
        bookArray.add(new Book("THE ART OF WAR","SUN TZU","3425"));
        bookArray.add(new Book("OLIVER TWIST","CHARLES DICKENS","3482"));

        newBookCatalogue.addItems(mockReader);
        assertEquals(bookArray.get(1).getAuthor(), ((Book)newBookCatalogue.getItems().get(1)).getAuthor());
    }

    @Test
    public void addBooksGivesCorrectBookQuantity() throws IOException {
        BufferedReader mockReader = addBooksSetup();

        ArrayList<Book> bookArray = new ArrayList<Book>();
        bookArray.add(new Book("THE ART OF WAR","SUN TZU","3425",2));
        bookArray.add(new Book("OLIVER TWIST","CHARLES DICKENS","3482",3));

        newBookCatalogue.addItems(mockReader);
        assertEquals(bookArray.get(1).getQuantity(), newBookCatalogue.getItems().get(1).getQuantity());
    }

    @Test
    public void searchForIdNumReturnsCorrectBook() throws IOException {
        BufferedReader mockReader = addBooksSetup();
        newBookCatalogue.addItems(mockReader);
        String bookOne = "OLIVER TWIST / CHARLES DICKENS / 3482";
        assertEquals(bookOne, newBookCatalogue.searchForIdNum("3482").toString());
    }

    @Test
    public void searchInTitleReturnsCorrectBook() throws IOException {
        BufferedReader mockReader = addBooksSetup();
        newBookCatalogue.addItems(mockReader);

        ArrayList<Book> booksFound = new ArrayList<Book>();
        booksFound.add(new Book("the art of faking","charles fakename","3213"));
        booksFound.add(new Book("the art of war","sun tzu","3425"));

        assertEquals(booksFound.toString(), newBookCatalogue.searchInTitle("art").toString());
    }

    @Test
    public void searchInTitleReturnsNullIfUnsuccessful() throws IOException {
        BufferedReader mockReader = addBooksSetup();
        newBookCatalogue.addItems(mockReader);

        ArrayList<Book> booksFound = new ArrayList<Book>();
        booksFound.add(new Book("oliver twist","charles dickens","3482"));
        booksFound.add(new Book("the art of faking","charles fakename","3213"));

        assertNull(newBookCatalogue.searchInTitle("blah"));
    }

    @Test
    public void searchInAuthorReturnsCorrectBook() throws IOException {
        BufferedReader mockReader = addBooksSetup();
        newBookCatalogue.addItems(mockReader);

        ArrayList<Book> booksFound = new ArrayList<Book>();
        booksFound.add(new Book("oliver twist","charles dickens","3482"));
        booksFound.add(new Book("the art of faking","charles fakename","3213"));

        assertEquals(booksFound.toString(), newBookCatalogue.searchInAuthor("charles").toString());
    }

    @Test
    public void searchInAuthorReturnsNullIfUnsuccessful() throws IOException {
        BufferedReader mockReader = addBooksSetup();
        newBookCatalogue.addItems(mockReader);

        ArrayList<Book> booksFound = new ArrayList<Book>();
        booksFound.add(new Book("oliver twist","charles dickens","3482"));
        booksFound.add(new Book("the art of faking","charles fakename","3213"));

        assertNull(newBookCatalogue.searchInAuthor("blah"));
    }

    @Test
    public void searchTitleByLetterReturnsCorrectBook() throws IOException {
        BufferedReader mockReader = addBooksSetup();
        newBookCatalogue.addItems(mockReader);

        ArrayList<Book> booksFound = new ArrayList<Book>();
        booksFound.add(new Book("the art of faking","charles fakename","3213"));
        booksFound.add(new Book("the art of war","sun tzu","3425"));

        assertEquals(booksFound.toString(), newBookCatalogue.searchTitleByLetter('t').toString());
    }

    @Test
    public void searchTitleByLetterReturnsNullIfUnsuccessful() throws IOException {
        BufferedReader mockReader = addBooksSetup();
        newBookCatalogue.addItems(mockReader);

        ArrayList<Book> booksFound = new ArrayList<Book>();
        booksFound.add(new Book("oliver twist","charles dickens","3482"));
        booksFound.add(new Book("the art of faking","charles fakename","3213"));

        assertNull(newBookCatalogue.searchTitleByLetter('a'));
    }

    @Test
    public void searchAuthorByLetterReturnsCorrectBook() throws IOException {
        BufferedReader mockReader = addBooksSetup();
        newBookCatalogue.addItems(mockReader);

        ArrayList<Book> booksFound = new ArrayList<Book>();
        booksFound.add(new Book("oliver twist","charles dickens","3482"));
        booksFound.add(new Book("the art of faking","charles fakename","3213"));

        assertEquals(booksFound.toString(), newBookCatalogue.searchAuthorByLetter('c').toString());
    }

    @Test
    public void searchAuthorByLetterReturnsNullIfUnsuccessful() throws IOException {
        BufferedReader mockReader = addBooksSetup();
        newBookCatalogue.addItems(mockReader);

        ArrayList<Book> booksFound = new ArrayList<Book>();
        booksFound.add(new Book("oliver twist","charles dickens","3482"));
        booksFound.add(new Book("the art of faking","charles fakename","3213"));

        assertNull(newBookCatalogue.searchAuthorByLetter('a'));
    }
    
    @Test 
    public void updateFromReservationsFileUpdatesCorrectly() throws IOException, BookCatalogue.InsufficientBooksException {
        BufferedReader mockBooksReader = addBooksSetup();
        newBookCatalogue.addItems(mockBooksReader);

        BufferedReader mockReservationsReader = mock(BufferedReader.class);
        when(mockReservationsReader.readLine()).thenReturn("MC245 / 3482 / 01 Jan 2001").thenReturn(null);
        newBookCatalogue.updateFromReservationsFile(mockReservationsReader);
        assertEquals(2, newBookCatalogue.searchForIdNum("3482").getNumFree());
    }

}
