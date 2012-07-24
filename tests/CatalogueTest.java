import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/16/12
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class CatalogueTest {

    Catalogue newCatalogue = new Catalogue();

    @Test
    public void testViewCatalogueByTitle() {
        newCatalogue.viewCatalogueByTitle();
        System.out.println();
    }

    @Test
    public void testViewCatalogueByAuthor() {
        newCatalogue.viewCatalogueByAuthor();
        System.out.println();
    }

    @Test
    public void testAddBooksWithoutView() {
        try {
            File inputFile = new File("testAddBooks.txt");
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            newCatalogue.addBooks(bufferedReader);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddBooks() {
        try {
            File inputFile = new File("testAddBooks.txt");
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            newCatalogue.addBooks(bufferedReader);
            newCatalogue.viewCatalogueByTitle();
            System.out.println();
            newCatalogue.viewCatalogueByAuthor();
            System.out.println();
            Iterator itr = newCatalogue.iterator();
            while(itr.hasNext()) {
                Book tmpBook = (Book) itr.next();
                System.out.println(tmpBook.getQuantity());
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSearchForIsbn() {
        testAddBooksWithoutView();
        assertEquals(null,newCatalogue.searchForIsbn("2346"));
        assertNotNull(newCatalogue.searchForIsbn("2345"));
        System.out.println(newCatalogue.searchForIsbn("2345"));
    }

    @Test
    public void testSearchInTitle() {
        testAddBooksWithoutView();
        System.out.println(newCatalogue.searchInTitle("holmes"));
        System.out.println(newCatalogue.searchInTitle("magic garden"));
    }

    @Test
    public void testSearchInAuthor() {
        testAddBooksWithoutView();
        System.out.println(newCatalogue.searchInAuthor("arthur conan doyle"));
        System.out.println(newCatalogue.searchInAuthor("dickens"));
    }

    @Test
    public void testSearchTitleByLetter() {
        testAddBooksWithoutView();
        System.out.println(newCatalogue.searchTitleByLetter('S'));
        System.out.println(newCatalogue.searchTitleByLetter('l'));
    }

    @Test
    public void testSearchAuthorByLetter() {
        testAddBooksWithoutView();
        System.out.println(newCatalogue.searchAuthorByLetter('S'));
        System.out.println(newCatalogue.searchAuthorByLetter('l'));
    }

}
