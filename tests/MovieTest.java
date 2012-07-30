import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/30/12
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class MovieTest {
    Movie movie;

    @Before
    public void init() {
        movie = new Movie("The Lord of the Rings","Peter Jackson","1234567890","*****");
    }

    @Test
    public void testEquals() {
        Movie movie2 = new Movie("LotR","peter jackson","1234567890","***");
        assertEquals(true, movie.equals(movie2));
    }
}
