import org.junit.Before;
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
 * Date: 7/18/12
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserListTest {
    UserList userList;

    @Before
    public void init() {
        userList = new UserList();
    }

    @Test
    public void generateUserListGivesCorrectResultWhenGetUserListIsCalled() throws IOException {
        init();
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenReturn("a123 / nicole").thenReturn("b456 / quah").thenReturn(null);
        userList.generateUserList(mockReader);

        ArrayList<User> arrResult = new ArrayList<User>();
        arrResult.add(new User("A123","NICOLE"));
        arrResult.add(new User("B456","QUAH"));

        assertEquals(arrResult,userList.getUserList());
    }

    @Test
    public void checkForUserReturnsUserIfSuccessful() throws Exception {
        init();
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenReturn("a123 / nicole").thenReturn("b456 / quah").thenReturn(null);
        userList.generateUserList(mockReader);


        User existingUser = new User("a123","nicole q");
        assertNotNull(userList.checkForUser(existingUser));
    }

    @Test
    public void checkForUserReturnsNullIfUnsuccessful() throws Exception {
        init();
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenReturn("a123 / nicole").thenReturn("b456 / quah").thenReturn(null);
        userList.generateUserList(mockReader);


        User existingUser = new User("a1234","nicole");
        assertNull(userList.checkForUser(existingUser));
    }
}
