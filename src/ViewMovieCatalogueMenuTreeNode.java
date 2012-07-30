/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
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

        addChildNode(library.getReturnToRootNode());
    }
}
