package biblioteca.menu;

import biblioteca.libFunctions.Biblioteca;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/30/12
 * Time: 9:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewMovieCatalogueMenuTreeNode extends MenuTreeNode {

    public ViewMovieCatalogueMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        createChildren();
    }

    public void createChildren() {
        ViewMovieCatalogueByTitleMenuTreeNode viewMovieByTitleNode = new ViewMovieCatalogueByTitleMenuTreeNode(library);
        viewMovieByTitleNode.setMenuOption("View catalogue in alphabetical order by title");
        addChildNode(viewMovieByTitleNode);

        ViewMovieCatalogueByDirectorMenuTreeNode viewMovieByDirectorNode = new ViewMovieCatalogueByDirectorMenuTreeNode(library);
        viewMovieByDirectorNode.setMenuOption("View catalogue in alphabetical order by director");
        addChildNode(viewMovieByDirectorNode);

        ViewMovieCatalogueByRatingMenuTreeNode viewMovieByRatingNode = new ViewMovieCatalogueByRatingMenuTreeNode(library);
        viewMovieByRatingNode.setMenuOption("View catalogue in order by rating (best to worst)");
        addChildNode(viewMovieByRatingNode);

        addChildNode(library.getReturnToRootNode());
    }
}
