import biblioteca.backend.MovieCatalogue;
import biblioteca.libFunctions.Movie;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/30/12
 * Time: 9:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class MovieCatalogueTest {

    MovieCatalogue newMovieCatalogue;

    public BufferedReader addMoviesSetup() throws IOException {
        newMovieCatalogue = new MovieCatalogue();

        BufferedReader mockReader = mock(BufferedReader.class);
        String strMovie1 = "Slumdog Millionaire / Danny Boyle / 903 / ****";
        String strMovie2 = "Titanic / James Cameron / 782 / *****";
        String strMovie3 = "Titanic Sank / Danny Fakename / 306 / **";
        when(mockReader.readLine()).thenReturn(strMovie1).thenReturn(strMovie2).
                thenReturn(strMovie3).thenReturn(null);
        return mockReader;
    }

    @Test
    public void addMoviesGivesCorrectBooksWhenGetMoviesIsCalled() throws IOException {
        BufferedReader mockReader = addMoviesSetup();

        ArrayList<Movie> movieArray = new ArrayList<Movie>();
        movieArray.add(new Movie("SLUMDOG MILLIONAIRE", "DANNY BOYLE", "903","****"));
        movieArray.add(new Movie("TITANIC", "JAMES CAMERON", "782","*****"));
        movieArray.add(new Movie("TITANIC SANK", "DANNY FAKENAME", "306","**"));

        newMovieCatalogue.addItems(mockReader);
        assertEquals(movieArray, newMovieCatalogue.getItems());
    }

    @Test
    public void sortCatalogueByRatingGivesCorrectOrder() throws IOException {
        BufferedReader mockReader = addMoviesSetup();

        ArrayList<Movie> movieArray = new ArrayList<Movie>();
        movieArray.add(new Movie("TITANIC", "JAMES CAMERON", "782","*****"));
        movieArray.add(new Movie("SLUMDOG MILLIONAIRE", "DANNY BOYLE", "903","****"));
        movieArray.add(new Movie("TITANIC SANK", "DANNY FAKENAME", "306","**"));

        newMovieCatalogue.addItems(mockReader);
        newMovieCatalogue.sortCatalogueByRating();
        assertEquals(movieArray, newMovieCatalogue.getItems());
    }
}
