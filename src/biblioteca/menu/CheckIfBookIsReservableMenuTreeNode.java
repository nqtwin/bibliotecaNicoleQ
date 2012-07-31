package biblioteca.menu;

import biblioteca.frontend.UserInput;
import biblioteca.libFunctions.Biblioteca;
import biblioteca.libFunctions.Book;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */

public class CheckIfBookIsReservableMenuTreeNode extends SearchXMenuTreeNode {
    Book bookToReserve;

    public CheckIfBookIsReservableMenuTreeNode(Biblioteca biblioteca, Book inBookToReserve) {
        super();
        library = biblioteca;
        bookToReserve = inBookToReserve;
        customInstruction = "Checking if book is available...";
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        userOutput.displayMessage(getCustomInstruction());
        if (bookToReserve.isBookReservable()) {
            ConductReservationMenuTreeNode conductReservationNode = new
                    ConductReservationMenuTreeNode(library, bookToReserve);
            conductReservationNode.executeMenuAction(userInput);
        }
        else {
            postSearchNode.setPreMessageCustomBeforeDefault(
                    "Sorry, but all copies of this book have already been reserved");
            createFailedChildren();
            postSearchNode.executeMenuAction(userInput);
        }
    }

    @Override
    public void createFailedChildren() {
        postSearchNode.resetChildNodes();

        ReserveBookMenuTreeNode reserveAnotherBookNode = new ReserveBookMenuTreeNode(library);
        reserveAnotherBookNode.setMenuOption("Reserve another book");
        postSearchNode.addChildNode(reserveAnotherBookNode);

        postSearchNode.addChildNode(library.getReturnToRootNode());
    }
}

