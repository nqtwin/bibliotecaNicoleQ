package biblioteca.menu;

import biblioteca.libFunctions.Biblioteca;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/25/12
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */

public class SearchCatalogueMenuTreeNode extends MenuTreeNode {

    public SearchCatalogueMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        createChildren();
    }

    public void createChildren() {
        SearchTitleMenuTreeNode searchTitleNode = new SearchTitleMenuTreeNode(library);
        searchTitleNode.setMenuOption("Search for part of title");
        addChildNode(searchTitleNode);

        SearchAuthorMenuTreeNode searchAuthorNode = new SearchAuthorMenuTreeNode(library);
        searchAuthorNode.setMenuOption("Search for part of author's name");
        addChildNode(searchAuthorNode);

        SearchTitleByLetterMenuTreeNode searchTitleLetterNode = new SearchTitleByLetterMenuTreeNode(library);
        searchTitleLetterNode.setMenuOption("Search by first letter of title");
        addChildNode(searchTitleLetterNode);

        SearchAuthorByLetterMenuTreeNode searchAuthorLetterNode = new SearchAuthorByLetterMenuTreeNode(library);
        searchAuthorLetterNode.setMenuOption("Search by first letter of author");
        addChildNode(searchAuthorLetterNode);

        addChildNode(library.getReturnToRootNode());
    }

}
