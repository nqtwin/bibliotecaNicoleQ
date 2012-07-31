package biblioteca.menu;

import biblioteca.libFunctions.Biblioteca;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */

public class RootMenuTreeNode extends MenuTreeNode {

    public RootMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        createChildren();
    }

    public void createChildren() {
        ViewMovieCatalogueMenuTreeNode movieCatalogueNode = new ViewMovieCatalogueMenuTreeNode(library);
        movieCatalogueNode.setMenuOption("View movie catalogue");
        addChildNode(movieCatalogueNode);

        ViewBookCatalogueMenuTreeNode bookCatalogueNode = new ViewBookCatalogueMenuTreeNode(library);
        bookCatalogueNode.setMenuOption("View book catalogue");
        addChildNode(bookCatalogueNode);

        SearchCatalogueMenuTreeNode searchNode = new SearchCatalogueMenuTreeNode(library);
        searchNode.setMenuOption("Search for book");
        addChildNode(searchNode);

        ReserveBookMenuTreeNode reserveNode = new ReserveBookMenuTreeNode(library);
        reserveNode.setMenuOption("Reserve book");
        addChildNode(reserveNode);

        CheckLibNumMenuTreeNode checkLibNumNode = new CheckLibNumMenuTreeNode(library);
        checkLibNumNode.setMenuOption("Check your library number");
        addChildNode(checkLibNumNode);

        addChildNode(library.getExitProgramNode());
    }
}
