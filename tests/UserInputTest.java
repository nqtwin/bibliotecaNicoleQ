import biblioteca.frontend.UserInput;
import biblioteca.menu.MenuTreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: nquah
 * Date: 7/18/12
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInputTest {
    UserInput userInput;

    @Before
    public void init() {
        userInput = new UserInput();
    }

    @Test
    public void checkUserInputShouldReturnFalseWithInvalidIntegerInput() {
        MenuTreeNode mockNode = mock(MenuTreeNode.class);
        when(mockNode.getNumChildNodes()).thenReturn(3);
        assertFalse(userInput.checkUserInput("4",mockNode));
    }

    @Test
    public void checkUserInputShouldReturnFalseWithNegativeIntegerInput() {
        MenuTreeNode mockNode = mock(MenuTreeNode.class);
        when(mockNode.getNumChildNodes()).thenReturn(3);
        assertFalse(userInput.checkUserInput("-1",mockNode));
    }

    @Test
    public void checkUserInputShouldReturnFalseWithInvalidStringInput() {
        MenuTreeNode mockNode = mock(MenuTreeNode.class);
        when(mockNode.getNumChildNodes()).thenReturn(3);
        assertFalse(userInput.checkUserInput("abc",mockNode));
    }

    @Test
    public void checkUserInputShouldReturnTrueWithValidInput() {
        MenuTreeNode mockNode = mock(MenuTreeNode.class);
        when(mockNode.getNumChildNodes()).thenReturn(3);
        assertTrue(userInput.checkUserInput("3", mockNode));
    }

    @Test
    public void handleUserInputShouldIntegerForValidInput() {
        MenuTreeNode mockNode = mock(MenuTreeNode.class);
        UserInput spyUserInput = spy(userInput);
        doReturn("3").when(spyUserInput).getUserInput();
        doReturn(true).when(spyUserInput).checkUserInput("3",mockNode);
        assertEquals(3, spyUserInput.handleUserInput(mockNode));
    }

    @Test
    public void checkUserInputCharReturnsFalseForInvalidInput(){
        assertFalse(userInput.checkUserInputChar("ab"));
    }

    @Test
    public void checkUserInputCharReturnsFalseForInvalidNonCharInput(){
        assertFalse(userInput.checkUserInputChar("0"));
    }

    @Test
    public void checkUserInputCharReturnsTrueForValidInput(){
        assertTrue(userInput.checkUserInputChar("b"));
    }

    @Test
    public void handleUserInputCharReturnsCorrectChar(){
        UserInput spyUserInput = spy(userInput);
        doReturn("z").when(spyUserInput).getUserInput();
        doReturn(true).when(spyUserInput).checkUserInputChar("z");
        assertEquals('z',spyUserInput.handleUserInputChar());
    }


}
