/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/25/12
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */

public class ViewBookCatalogueByAuthorMenuTreeNode extends MenuTreeNode {
    CatalogueRenderer catalogueRenderer;

    public ViewBookCatalogueByAuthorMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        catalogueRenderer = new CatalogueRenderer();
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        BookCatalogue libBookCatalogue = library.getBookCatalogue();
        libBookCatalogue.sortCatalogueByAuthor();
        userOutput.displayMessage(catalogueRenderer.listBooks(libBookCatalogue.getItems()));
        library.getEndActionNode().executeMenuAction(userInput);
    }
}

