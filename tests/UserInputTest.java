import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/18/12
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInputTest extends TestCase {
    UserInput userInput = new UserInput();


    // NEED TO IMPLEMENT TESTS WITH SIMULATED USER INPUT
    public void testHandleUserInput() throws Exception {
        MenuTreeNode newMenuNode = new MenuTreeNode();
        newMenuNode.addChildNode(new MenuTreeNode());
        newMenuNode.addChildNode(new MenuTreeNode());
        newMenuNode.addChildNode(new MenuTreeNode());
        //userInput.handleUserInput(newMenuNode);
    }

    public void testHandleUserInputChar() throws Exception {

    }

    public void testGetUserInput() throws Exception {

    }

    public void testCheckUserInput() throws Exception {

    }

    public void testCheckUserInputChar() throws Exception {

    }
}
