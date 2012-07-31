package biblioteca.menu;

import biblioteca.frontend.UserInput;
import biblioteca.frontend.UserOutput;
import biblioteca.libFunctions.Biblioteca;
import biblioteca.libFunctions.Book;
import biblioteca.libFunctions.User;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */

public class ConductReservationMenuTreeNode extends SearchXMenuTreeNode {
    Book bookToReserve;
    User userConductingReserve;

    public ConductReservationMenuTreeNode(Biblioteca biblioteca, Book inBookToReserve) {
        super();
        library = biblioteca;
        postSearchNode = new MenuTreeNode();
        customInstruction = "Please enter your library number:";
        bookToReserve = inBookToReserve;
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        userOutput.displayMessage(getCustomInstruction());
        String userLibNum = userInput.getUserInput();
        User userToCheck = new User(userLibNum,"");
        userConductingReserve = library.getUserList().checkForUser(userToCheck);
        if (userConductingReserve == null) {
            postSearchNode.setPreMessageCustomBeforeDefault("Sorry, user not found.");
            createFailedChildren();
        }
        else {
            UserOutput systemDisplay = new UserOutput();
            systemDisplay.displayMessage(userConductingReserve.getUserInfo());
            createSuccessfulChildren();
            postSearchNode.setPreMessageCustomBeforeDefault("Is this you?");
        }
        postSearchNode.executeMenuAction(userInput);
    }

    @Override
    public void createSuccessfulChildren() {
        postSearchNode.resetChildNodes();

        FinishReservationMenuTreeNode finishReservationNode =
                new FinishReservationMenuTreeNode(library,userConductingReserve,bookToReserve);
        finishReservationNode.setMenuOption("Yes it is!");
        postSearchNode.addChildNode(finishReservationNode);

        ConductReservationMenuTreeNode conductReservationAgainNode =
                new ConductReservationMenuTreeNode(library,bookToReserve);
        conductReservationAgainNode.setMenuOption("No it isn't, let me try again");
        postSearchNode.addChildNode(conductReservationAgainNode);

        postSearchNode.addChildNode(library.getReturnToRootNode());
    }

    @Override
    public void createFailedChildren() {
        postSearchNode.resetChildNodes();

        ConductReservationMenuTreeNode conductReservationAgainNode =
                new ConductReservationMenuTreeNode(library,bookToReserve);
        conductReservationAgainNode.setMenuOption("I'd like to try entering my library number again");
        postSearchNode.addChildNode(conductReservationAgainNode);

        postSearchNode.addChildNode(library.getReturnToRootNode());
    }
}
