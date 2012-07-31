package biblioteca.menu;

import biblioteca.libFunctions.Biblioteca;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */

public class ReserveBookMenuTreeNode extends MenuTreeNode {
    public ReserveBookMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        createChildren();
    }

    public void createChildren() {
        ReserveByIdNumMenuTreeNode reserveByIdNumNode = new ReserveByIdNumMenuTreeNode(library);
        reserveByIdNumNode.setMenuOption("Know your book's ID number? Reserve here");
        addChildNode(reserveByIdNumNode);

        SearchCatalogueMenuTreeNode searchFromReserveNode = new SearchCatalogueMenuTreeNode(library);
        searchFromReserveNode.setMenuOption("Don't know the ID number? Search for your book here");
        addChildNode(searchFromReserveNode);

        addChildNode(library.getReturnToRootNode());
    }
}
