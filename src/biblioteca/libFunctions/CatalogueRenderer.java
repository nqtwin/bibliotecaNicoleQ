package biblioteca.libFunctions;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/24/12
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class CatalogueRenderer {

    private String getBookHeader() {
        String strHeader;
        strHeader = "TITLE / AUTHOR / ID NUM";
        return strHeader;
    }

    private String getMovieHeader() {
        String strHeader;
        strHeader = "TITLE / DIRECTOR / RATING (OUT OF 5)";
        return strHeader;
    }

    public ArrayList<String> listMovies(ArrayList<Media> movieArrayList) {
        ArrayList<String> listOfMovies = new ArrayList<String>();
        if (movieArrayList.size() == 0)
            return null;
        else {
            listOfMovies.add(getMovieHeader());
            for (Media movie : movieArrayList)
                listOfMovies.add(((Movie) movie).toString());
            return listOfMovies;
        }
    }

    public ArrayList<String> listSingleMovie(Media inMovie) {
        Movie movie = (Movie) inMovie;
        ArrayList<String> listOfMovies = new ArrayList<String>();
        if (movie == null)
            return null;
        else {
            listOfMovies.add(getMovieHeader());
            listOfMovies.add(movie.toString());
            return listOfMovies;
        }
    }

    public ArrayList<String> listBooks(ArrayList<Media> bookArrayList) {
        ArrayList<String> listOfBooks = new ArrayList<String>();
        if (bookArrayList.size() == 0)
            return null;
        else {
            listOfBooks.add(getBookHeader());
            for (Media book : bookArrayList)
                listOfBooks.add(((Book)book).toString());
            return listOfBooks;
        }
    }

    public ArrayList<String> listSingleBook(Media inBook) {
        Book book = (Book) inBook;
        ArrayList<String> listOfBooks = new ArrayList<String>();
        if (book == null)
            return null;
        else {
            listOfBooks.add(getBookHeader());
            listOfBooks.add(book.toString());
            return listOfBooks;
        }
    }

}
