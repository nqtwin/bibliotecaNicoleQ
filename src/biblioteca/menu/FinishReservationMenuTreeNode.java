package biblioteca.menu;

import biblioteca.frontend.UserInput;
import biblioteca.libFunctions.Biblioteca;
import biblioteca.libFunctions.Book;
import biblioteca.libFunctions.User;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */

public class FinishReservationMenuTreeNode extends MenuTreeNode {
    User user;
    Book book;
    MenuTreeNode postReservationNode;

    public FinishReservationMenuTreeNode(Biblioteca biblioteca, User inUser, Book inBook) {
        library = biblioteca;
        user = inUser;
        book = inBook;
        postReservationNode = new MenuTreeNode();
        postReservationNode.setPreMessageCustomBeforeDefault("What would you like to do now?");
    }

    public void executeMenuAction(UserInput userInput) {
        user.makeReservation(book);
        userOutput.displayMessage("Book successfully reserved for collection. Thank you! Enjoy the book!");
        createPostReservationChildren();
        postReservationNode.executeMenuAction(userInput);
    }

    public void createPostReservationChildren() {
        ReserveBookMenuTreeNode reserveAnotherBookNode = new ReserveBookMenuTreeNode(library);
        reserveAnotherBookNode.setMenuOption("Reserve another book");
        postReservationNode.addChildNode(reserveAnotherBookNode);

        postReservationNode.addChildNode(library.getReturnToRootNode());
    }
}

