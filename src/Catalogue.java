import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/30/12
 * Time: 8:24 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Catalogue {
    ArrayList<Media> mediaSet = null;

    public void addItems(BufferedReader reader) throws IOException;

    public ArrayList<Media> getItems();

    public Iterator<Media> getIterator();

    public Media searchForIdNum(String inIdNum);

    public ArrayList<Media> searchInTitle(String inPartOfTitle);

    public ArrayList<Media> searchTitleByLetter(char inAlphabet);

    public void sortCatalogueByTitle();

}
