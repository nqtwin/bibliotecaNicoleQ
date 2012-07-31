/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/30/12
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/16/12
 * Time: 12:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class MovieCatalogue implements Catalogue {
    private ArrayList<Media> movieSet;

    public MovieCatalogue() {
        movieSet = new ArrayList<Media>();
    }

    public MovieCatalogue(String inputFilename) {
        movieSet = new ArrayList<Media>();
        try {
            File inputFile = new File(inputFilename);
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            addItems(bufferedReader);
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // returns -1 if book not present
    private int checkForItem(Movie movieToCheck) {
        return movieSet.indexOf(movieToCheck);
    }

    public void addItems(BufferedReader reader) throws IOException {
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("/");
            Movie newMovie = new Movie(tokens[0].trim(),tokens[1].trim(),tokens[2].trim(),tokens[3].trim());
            int indexBook = checkForItem(newMovie);
            if (indexBook > -1)
                movieSet.get(indexBook).addQuantity(newMovie.getQuantity());
            else
                movieSet.add(newMovie);
        }
    }

    public ArrayList<Media> getItems() {
        return movieSet;
    }

    public Iterator<Media> getIterator() {
        return movieSet.iterator();
    }

    public Media searchForIdNum(String inIdNum) {
        Movie fakeMovie = new Movie("","",inIdNum,"");
        int indexMovie = movieSet.indexOf(fakeMovie);
        if (indexMovie > -1)
            return movieSet.get(indexMovie);
        else
            return null;
    }

    public ArrayList<Media> searchInTitle(String inPartOfTitle) {
        sortCatalogueByTitle();
        ArrayList<Media> outArrMovie = new ArrayList<Media>();
        String partOfTitle = inPartOfTitle.toUpperCase();
        Iterator itr = movieSet.iterator();
        while(itr.hasNext()) {
            Movie tmpMovie = (Movie) itr.next();
            if (tmpMovie.getTitle().contains(partOfTitle))
                outArrMovie.add(tmpMovie);
        }
        if (outArrMovie.size() == 0)
            return null;
        else return outArrMovie;
    }

    public ArrayList<Media> searchInDirector(String inPartOfDirector) {
        sortCatalogueByDirector();
        ArrayList<Media> outArrMovie = new ArrayList<Media>();
        String partOfDirector = inPartOfDirector.toUpperCase();
        Iterator itr = movieSet.iterator();
        while(itr.hasNext()) {
            Movie tmpMovie = (Movie) itr.next();
            if (tmpMovie.getDirector().contains(partOfDirector))
                outArrMovie.add(tmpMovie);
        }
        if (outArrMovie.size() == 0)
            return null;
        else return outArrMovie;
    }


    public ArrayList<Media> searchTitleByLetter(char inAlphabet) {
        Character startLetter = new Character(inAlphabet);
        startLetter = Character.toUpperCase(startLetter);
        ArrayList<Media> outArrMovie = new ArrayList<Media>();
        sortCatalogueByTitle();
        Iterator itr = movieSet.iterator();
        while(itr.hasNext()) {
            Movie tmpMovie = (Movie) itr.next();
            String tmpTitle = tmpMovie.getTitle();
            String strRegex = "^[" + startLetter + "].";
            Matcher letterMatch = Pattern.compile(strRegex).matcher(tmpTitle);
            if (letterMatch.lookingAt())
                outArrMovie.add(tmpMovie);
        }
        if (outArrMovie.size() == 0)
            return null;
        else return outArrMovie;
    }

    // last name, first name issue not handled (only searches by first name for now)
    public ArrayList<Media> searchDirectorByLetter(char inAlphabet) {
        Character startLetter = new Character(inAlphabet);
        startLetter = Character.toUpperCase(startLetter);
        ArrayList<Media> outArrMovie = new ArrayList<Media>();
        sortCatalogueByDirector();
        Iterator itr = movieSet.iterator();
        while(itr.hasNext()) {
            Movie tmpMovie = (Movie) itr.next();
            String tmpDirector = tmpMovie.getDirector();
            String strRegex = "^[" + startLetter + "].";
            Matcher letterMatch = Pattern.compile(strRegex).matcher(tmpDirector);
            if (letterMatch.lookingAt())
                outArrMovie.add(tmpMovie);
        }
        if (outArrMovie.size() == 0)
            return null;
        else return outArrMovie;
    }

    public void sortCatalogueByTitle() {
        TitleCompare titleComparator = new TitleCompare();
        Collections.sort(movieSet,titleComparator);
    }

    public void sortCatalogueByDirector() {
        DirectorCompare directorComparator = new DirectorCompare();
        Collections.sort(movieSet,directorComparator);
    }

    public void sortCatalogueByRating() {
        sortCatalogueByDirector();
        RatingCompare ratingComparator = new RatingCompare();
        Collections.sort(movieSet,ratingComparator);
    }

    class TitleCompare implements Comparator<Media> {
        public int compare(Media movieOne, Media movieTwo) {
            Movie mvOne = (Movie) movieOne;
            Movie mvTwo = (Movie) movieTwo;
            return mvOne.getTitle().compareTo(mvTwo.getTitle());
        }
    }

    class DirectorCompare implements Comparator<Media> {
        public int compare(Media movieOne, Media movieTwo) {
            Movie mvOne = (Movie) movieOne;
            Movie mvTwo = (Movie) movieTwo;
            return mvOne.getDirector().compareTo(mvTwo.getDirector());
        }
    }

    class RatingCompare implements Comparator<Media> {
        public int compare(Media movieOne, Media movieTwo) {
            Movie mvOne = (Movie) movieOne;
            Movie mvTwo = (Movie) movieTwo;
            return ((Integer)mvTwo.getRating().length()).compareTo(((Integer)mvOne.getRating().length()));
        }
    }

}

