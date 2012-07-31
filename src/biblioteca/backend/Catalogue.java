package biblioteca.backend;

import biblioteca.libFunctions.Media;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
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
