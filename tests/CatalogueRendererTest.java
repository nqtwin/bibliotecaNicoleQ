import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
* Created with IntelliJ IDEA.
* User: ThoughtWorks
* Date: 7/24/12
* Time: 4:05 PM
* To change this template use File | Settings | File Templates.
*/
public class CatalogueRendererTest {
    CatalogueRenderer catalogueRenderer;

    @Before
    public void initialize() {
        catalogueRenderer = new CatalogueRenderer();
    }

    @Test
    public void listBooksReturnsCorrectStringArray() throws Exception {
        ArrayList<Media> listOfBooks = new ArrayList<Media>();
        listOfBooks.add(new Book("Gone with the wind","Margaret Mitchell","2345",2));
        listOfBooks.add(new Book("Wuthering Heights","Emily Bronte","1326",5));

        ArrayList<String> result = new ArrayList<String>();
        result.add("TITLE / AUTHOR / ID NUM");
        result.add("GONE WITH THE WIND / MARGARET MITCHELL / 2345");
        result.add("WUTHERING HEIGHTS / EMILY BRONTE / 1326");

        assertEquals(result, catalogueRenderer.listBooks(listOfBooks));
    }

    @Test
    public void listSingleBookReturnsCorrectString() throws Exception {
        Book book = new Book("Wuthering heights","emily bronte","3456",7);

        ArrayList<String> result = new ArrayList<String>();
        result.add("TITLE / AUTHOR / ID NUM");
        result.add("WUTHERING HEIGHTS / EMILY BRONTE / 3456");

        assertEquals(result, catalogueRenderer.listSingleBook(book));
    }
}
