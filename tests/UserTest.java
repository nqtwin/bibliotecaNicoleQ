import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/15/12
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTest {
    User nicole;

    @Before
    public void init() {
        nicole = new User("abc1234","nicole");
    }

    @Test
    public void makeReservationReturnsOneIfSuccessful() {
        Book newBook = new Book("Les Miserables","Victor Hugo","3705");
        assertEquals(1,nicole.makeReservation(newBook));
    }

    @Test
    public void makeReservationReturnsNegOneIfUnsuccessful() {
        Book newBook = new Book("Les Miserables","Victor Hugo","3705");
        nicole.makeReservation(newBook);
        assertEquals(-1,nicole.makeReservation(newBook));
    }

    @Test
    public void equalsWorks() {
        User nicole2 = new User("ABC1234","Nicole");
        assertEquals(true,nicole.equals(nicole2));
    }

}
