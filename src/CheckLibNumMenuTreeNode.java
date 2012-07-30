/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/25/12
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */

public class CheckLibNumMenuTreeNode extends MenuTreeNode {

    public CheckLibNumMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        userOutput.displayMessage("Please talk to librarian. Thank you!");
        library.getEndActionNode().executeMenuAction(userInput);
    }
}
