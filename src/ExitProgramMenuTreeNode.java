/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/25/12
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */

public class ExitProgramMenuTreeNode extends MenuTreeNode {

    public ExitProgramMenuTreeNode() {
        super();
        customInstruction = "Thank you for using Biblioteca and have a good day!";
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        userOutput.displayMessage(getCustomInstruction());
    }
}
