import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/25/12
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */


public class SearchAuthorByLetterMenuTreeNode extends SearchXMenuTreeNode {

    public SearchAuthorByLetterMenuTreeNode(Biblioteca biblioteca) {
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
            ArrayList<Media> booksFound = library.getBookCatalogue().searchAuthorByLetter(searchChar);
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
