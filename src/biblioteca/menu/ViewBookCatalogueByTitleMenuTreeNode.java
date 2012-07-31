package biblioteca.menu;

import biblioteca.backend.BookCatalogue;
import biblioteca.frontend.UserInput;
import biblioteca.libFunctions.Biblioteca;
import biblioteca.libFunctions.CatalogueRenderer;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewBookCatalogueByTitleMenuTreeNode extends MenuTreeNode {
    CatalogueRenderer catalogueRenderer;

    public ViewBookCatalogueByTitleMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        catalogueRenderer = new CatalogueRenderer();
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        BookCatalogue libBookCatalogue = library.getBookCatalogue();
        libBookCatalogue.sortCatalogueByTitle();
        userOutput.displayMessage(catalogueRenderer.listBooks(libBookCatalogue.getItems()));
        library.getEndActionNode().executeMenuAction(userInput);
    }
}
