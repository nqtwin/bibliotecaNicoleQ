import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/18/12
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserListTest {
    UserList userList;

    @Test
    public void testGenerateUserList() throws Exception {
        userList = new UserList("userlist.txt");
        userList.displayUserList();
    }

    @Test
    public void testCheckForUser() throws Exception {
        System.out.println();
        testGenerateUserList();
        assertNotNull(userList);

        User existingUser = new User("ab123","nicole");
        assertNotNull(userList.checkForUser(existingUser));
        System.out.println();
        System.out.println(userList.checkForUser(existingUser));

        User newUser = new User("abc123","nicole q");
        assertNull(userList.checkForUser(newUser));
    }
}
