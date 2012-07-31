package biblioteca.menu;

import biblioteca.libFunctions.Biblioteca;

/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/25/12
 * Time: 9:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewBookCatalogueMenuTreeNode extends MenuTreeNode {

    public ViewBookCatalogueMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        createChildren();
    }

    public void createChildren() {
        ViewBookCatalogueByTitleMenuTreeNode viewBookByTitleNode = new ViewBookCatalogueByTitleMenuTreeNode(library);
        viewBookByTitleNode.setMenuOption("View catalogue in alphabetical order by title");
        addChildNode(viewBookByTitleNode);

        ViewBookCatalogueByAuthorMenuTreeNode viewBookByAuthorNode = new ViewBookCatalogueByAuthorMenuTreeNode(library);
        viewBookByAuthorNode.setMenuOption("View catalogue in alphabetical order by author");
        addChildNode(viewBookByAuthorNode);

        addChildNode(library.getReturnToRootNode());
    }
}