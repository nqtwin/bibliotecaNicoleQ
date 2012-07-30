import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/25/12
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */

public class SearchTitleMenuTreeNode extends SearchXMenuTreeNode {

    public SearchTitleMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        postSearchNode = new MenuTreeNode();
        customInstruction = "Please enter search:";
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        userOutput.displayMessage(getCustomInstruction());
        String userIn = userInput.getUserInput();
        ArrayList<Media> booksFound = library.getBookCatalogue().searchInTitle(userIn);
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
}