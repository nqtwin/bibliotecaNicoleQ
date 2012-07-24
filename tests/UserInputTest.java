import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/18/12
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInputTest {
    UserInput userInput = new UserInput();


    // NEED TO IMPLEMENT TESTS WITH SIMULATED USER INPUT
    @Test
    public void testHandleUserInput() throws Exception {
        MenuTreeNode newMenuNode = new MenuTreeNode();
        newMenuNode.addChildNode(new MenuTreeNode());
        newMenuNode.addChildNode(new MenuTreeNode());
        newMenuNode.addChildNode(new MenuTreeNode());
        //userInput.handleUserInput(newMenuNode);
    }

    @Test
    public void testHandleUserInputChar() throws Exception {

    }

    @Test
    public void testGetUserInput() throws Exception {

    }

    @Test
    public void testCheckUserInput() throws Exception {

    }

    @Test
    public void testCheckUserInputChar() throws Exception {

    }
}
