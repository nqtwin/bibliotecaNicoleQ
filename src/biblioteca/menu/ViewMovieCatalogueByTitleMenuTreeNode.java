package biblioteca.menu;

import biblioteca.backend.MovieCatalogue;
import biblioteca.frontend.UserInput;
import biblioteca.libFunctions.Biblioteca;
import biblioteca.libFunctions.CatalogueRenderer;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/30/12
 * Time: 9:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewMovieCatalogueByTitleMenuTreeNode extends MenuTreeNode {
    CatalogueRenderer catalogueRenderer;

    public ViewMovieCatalogueByTitleMenuTreeNode(Biblioteca biblioteca) {
        super();
        library = biblioteca;
        catalogueRenderer = new CatalogueRenderer();
    }

    @Override
    public void executeMenuAction(UserInput userInput) {
        MovieCatalogue libMovieCatalogue = library.getMovieCatalogue();
        libMovieCatalogue.sortCatalogueByTitle();
        userOutput.displayMessage(catalogueRenderer.listMovies(libMovieCatalogue.getItems()));
        library.getEndActionNode().executeMenuAction(userInput);
    }
}

