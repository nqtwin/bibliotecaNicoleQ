package biblioteca.menu;

import biblioteca.frontend.UserInput;
import biblioteca.libFunctions.Biblioteca;
import biblioteca.libFunctions.Media;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */

public class SearchTitleByLetterMenuTreeNode extends SearchXMenuTreeNode {

    public SearchTitleByLetterMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        postSearchNode = new MenuTreeNode();
        customInstruction = "Please enter letter:";
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        userOutput.displayMessage(getCustomInstruction());
        char searchChar = userInput.handleUserInputChar();
        if (Character.isLetter(searchChar)) {
            ArrayList<Media> booksFound = library.getBookCatalogue().searchTitleByLetter(searchChar);
            if (booksFound != null) {
                userOutput.displayMessage(catalogueRenderer.listBooks(booksFound));
                createSuccessfulChildren();
            }
            else {
                postSearchNode.setPreMessageCustomBeforeDefault("Search did not produce any results.");
                createFailedChildren();
            }

            postSearchNode.executeMenuAction(userInput);
        }
        else {
            userOutput.displayMessage("Please enter single starting letter!!");
            executeMenuAction(userInput);
        }
    }
}
