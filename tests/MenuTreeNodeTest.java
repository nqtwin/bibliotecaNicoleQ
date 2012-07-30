import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/18/12
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuTreeNodeTest {
    MenuTreeNode node;

    @Before
    public void init() {
        node = new MenuTreeNode();
    }

    @Test
    public void resetChildNodesShouldReturnZeroToGetNumChildNodes() throws Exception {
        node.addChildNode(new MenuTreeNode());
        node.resetChildNodes();
        assertEquals(0,node.getNumChildNodes());
    }

    @Test
    public void addChildNodeShouldIncrementNumChildNodes() throws Exception {
        MenuTreeNode childNode = new MenuTreeNode();
        childNode.setCustomInstruction("TEST ADD CHILD NODE");
        node.addChildNode(childNode);
        assertEquals(1,node.getNumChildNodes());
    }

    @Test
    public void getNumChildNodesReturnsOneAfterAddingChild() throws Exception {
        node.addChildNode(new MenuTreeNode());
        assertEquals(1, node.getNumChildNodes());
    }

    @Test
    public void setChildNodesGivesCorrectNumChildNodes() throws Exception {
        ArrayList<MenuTreeNode> childNodes = new ArrayList<MenuTreeNode>();
        childNodes.add(new MenuTreeNode());
        childNodes.add(new MenuTreeNode());
        node.setChildNodes(childNodes);
        assertEquals(2,node.getNumChildNodes());
    }

    @Test
    public void getMenuOptionsGivesCorrectStringArray() throws Exception {
        MenuTreeNode childOne = new MenuTreeNode();
        childOne.setMenuOption("TEST ONE");
        node.addChildNode(childOne);
        MenuTreeNode childTwo = new MenuTreeNode();
        childTwo.setMenuOption("TEST TWO");
        node.addChildNode(childTwo);
        ArrayList<String> result = new ArrayList<String>();
        result.add("Please select one of the following menu options:");
        result.add("1: TEST ONE");
        result.add("2: TEST TWO");
        assertEquals(result,node.getFullMenuOptions());
    }

    @Test
    public void executeMenuActionExecutesCorrectChildActionWithValidUserInput() throws Exception {
        MenuTreeNode mockChildOne = mock(MenuTreeNode.class);
        when(mockChildOne.getMenuOption()).thenReturn("TEST MENU OPTION ONE");
        node.addChildNode(mockChildOne);

        MenuTreeNode mockChildTwo = mock(MenuTreeNode.class);
        when(mockChildTwo.getMenuOption()).thenReturn("TEST MENU OPTION TWO");
        node.addChildNode(mockChildTwo);

        UserInput mockInput = mock(UserInput.class);
        when(mockInput.handleUserInput(node)).thenReturn(1);
        node.executeMenuAction(mockInput);
        verify(mockChildOne).executeMenuAction(mockInput);
    }

    @Test
    public void executeMenuActionReexecutesWithInvalidUserInput() throws Exception {
        MenuTreeNode spyNode = spy(node);

        MenuTreeNode mockChildOne = mock(MenuTreeNode.class);
        when(mockChildOne.getMenuOption()).thenReturn("TEST MENU OPTION ONE");
        spyNode.addChildNode(mockChildOne);

        MenuTreeNode mockChildTwo = mock(MenuTreeNode.class);
        when(mockChildTwo.getMenuOption()).thenReturn("TEST MENU OPTION TWO");
        spyNode.addChildNode(mockChildTwo);

        UserInput mockInput = mock(UserInput.class);
        when(mockInput.handleUserInput(spyNode)).thenReturn(-1).thenReturn(1);
        spyNode.executeMenuAction(mockInput);

        verify(spyNode,times(2)).executeMenuAction(mockInput);
    }

}
