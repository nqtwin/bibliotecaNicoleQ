import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/18/12
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuTreeNodeTest extends TestCase {
    MenuTreeNode node = new MenuTreeNode();

    public void testSetPreMessageCustomBeforeDefault() throws Exception {
        node.setPreMessageCustomBeforeDefault("THIS MESSAGE COMES BEFORE THE PLEASE SELECT MENU OPTIONS MESSAGE");
        node.displayPreMessage();
    }

    public void testSetPreMessageCustom() throws Exception {
        node.setPreMessageCustom("THIS IS A NEW STANDALONE CUSTOM PREMESSAGE");
        node.displayPreMessage();
    }

    public void testGetPreMessage() throws Exception {
        node.setPreMessageCustomBeforeDefault("TEST GET PRE MESSAGE");
        System.out.println(node.getPreMessage());
    }

    public void testSetCustomInstruction() throws Exception {
        node.setCustomInstruction("TEST SET CUSTOM INSTRUCTION");
        node.displayCustomInstruction();
    }

    public void testSetMenuOption() throws Exception {
        node.setMenuOption("TEST SET MENU OPTION");
        node.displayMenuOptions();
    }

    public void testGetMenuOption() throws Exception {
        node.setMenuOption("TEST GET MENU OPTION");
        System.out.println(node.getMenuOption());
    }

    public void testSetParentNode() throws Exception {
        MenuTreeNode parentNode = new MenuTreeNode();
        assertNull(node.getParentNode());
        node.setParentNode(parentNode);
        assertNotNull((node.getParentNode()));
    }

    public void testResetChildNodes() throws Exception {
        assertEquals(0,node.getNumChildNodes());
        node.addChildNode(new MenuTreeNode());
        assertEquals(1,node.getNumChildNodes());
        node.resetChildNodes();
        assertEquals(0,node.getNumChildNodes());
    }

    public void testAddChildNode() throws Exception {
        assertEquals(0,node.getNumChildNodes());
        MenuTreeNode childNode = new MenuTreeNode();
        childNode.setCustomInstruction("TEST ADD CHILD NODE");
        node.addChildNode(childNode);
        node.getChildNodes().get(0).displayCustomInstruction();
    }

    public void testGetNumChildNodes() throws Exception {
        node.addChildNode(new MenuTreeNode());
        assertEquals(1, node.getNumChildNodes());
    }

    public void testSetChildNodes() throws Exception {
        ArrayList<MenuTreeNode> childNodes = new ArrayList<MenuTreeNode>();
        childNodes.add(new MenuTreeNode());
        childNodes.add(new MenuTreeNode());
        node.setChildNodes(childNodes);
        assertEquals(2,node.getNumChildNodes());
    }

    public void testDisplayMenuOptions() throws Exception {
        MenuTreeNode childOne = new MenuTreeNode();
        childOne.setMenuOption("TEST DISPLAY MENU OPTIONS ONE");
        node.addChildNode(childOne);
        MenuTreeNode childTwo = new MenuTreeNode();
        childTwo.setMenuOption("TEST DISPLAY MENU OPTIONS TWO");
        node.addChildNode(childTwo);
        node.displayMenuOptions();
    }

    public void testExecuteMenuAction() throws Exception {

    }
}
