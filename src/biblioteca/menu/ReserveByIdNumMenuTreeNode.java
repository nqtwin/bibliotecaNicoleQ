package biblioteca.menu;

import biblioteca.frontend.UserInput;
import biblioteca.libFunctions.Biblioteca;
import biblioteca.libFunctions.Book;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */

public class ReserveByIdNumMenuTreeNode extends SearchXMenuTreeNode {
    Book bookToReserve;

    public ReserveByIdNumMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        postSearchNode = new MenuTreeNode();
        customInstruction = "Please enter ID number of book you wish to reserve:";
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        userOutput.displayMessage(getCustomInstruction());
        String userIsbn = userInput.getUserInput();
        bookToReserve = (Book) library.getBookCatalogue().searchForIdNum(userIsbn);
        if (bookToReserve != null) {
            userOutput.displayMessage(catalogueRenderer.listSingleBook(bookToReserve));
            postSearchNode.setPreMessageCustomBeforeDefault("Is this the correct book?");
            createSuccessfulChildren();
        }
        else {
            postSearchNode.setPreMessageCustomBeforeDefault("Sorry! We do not have that book yet!");
            createFailedChildren();
        }
        postSearchNode.executeMenuAction(userInput);
    }

    @Override
    public void createSuccessfulChildren() {
        postSearchNode.resetChildNodes();

        CheckIfBookIsReservableMenuTreeNode checkBookReservableNode =
                new CheckIfBookIsReservableMenuTreeNode(library, bookToReserve);
        checkBookReservableNode.setMenuOption("Yes, please reserve.");
        postSearchNode.addChildNode(checkBookReservableNode);

        ReserveBookMenuTreeNode reserveBookNode = new ReserveBookMenuTreeNode(library);
        reserveBookNode.setMenuOption("No, this is not the right book. Return to previous menu.");
        postSearchNode.addChildNode(reserveBookNode);
    }

    @Override
    public void createFailedChildren() {
        postSearchNode.resetChildNodes();

        ReserveByIdNumMenuTreeNode reserveAgainNode = new ReserveByIdNumMenuTreeNode(library);
        reserveAgainNode.setMenuOption("Try reserving by ID number again");
        postSearchNode.addChildNode(reserveAgainNode);

        postSearchNode.addChildNode(library.getReturnToRootNode());
    }
}

