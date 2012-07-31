package biblioteca.menu;

import biblioteca.libFunctions.CatalogueRenderer;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */

public class SearchXMenuTreeNode extends MenuTreeNode {
    MenuTreeNode postSearchNode;
    CatalogueRenderer catalogueRenderer;

    public SearchXMenuTreeNode() {
        super();
        postSearchNode = new MenuTreeNode();
        catalogueRenderer = new CatalogueRenderer();
    }

    public MenuTreeNode getPostSearchNode() {
        return postSearchNode;
    }

    public void createSuccessfulChildren() {
        postSearchNode.resetChildNodes();

        ReserveBookMenuTreeNode reserveBookNode = new ReserveBookMenuTreeNode(library);
        reserveBookNode.setMenuOption("Reserve a book");
        postSearchNode.addChildNode(reserveBookNode);

        SearchCatalogueMenuTreeNode searchNode = new SearchCatalogueMenuTreeNode(library);
        searchNode.setMenuOption("Search for another book");
        postSearchNode.addChildNode(searchNode);

        postSearchNode.addChildNode(library.getReturnToRootNode());
    }

    public void createFailedChildren() {
        postSearchNode.resetChildNodes();

        SearchCatalogueMenuTreeNode searchNode = new SearchCatalogueMenuTreeNode(library);
        searchNode.setMenuOption("Return to search");
        postSearchNode.addChildNode(searchNode);

        postSearchNode.addChildNode(library.getReturnToRootNode());
    }
}
