import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/16/12
 * Time: 12:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Catalogue {
    private ArrayList<Book> bookSet;

    public Catalogue() {
        bookSet = new ArrayList<Book>();
    }

    public Catalogue(String inputFilename) {
        bookSet = new ArrayList<Book>();
        try {
            File inputFile = new File(inputFilename);
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            addBooks(bufferedReader);
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // returns -1 if book not present
    private int checkForBook(Book bookToCheck) {
        return bookSet.indexOf(bookToCheck);
    }

    public void addBooks(BufferedReader reader) throws IOException {
        String line = null;
        while ((line = reader.readLine()) != null) {
             String[] tokens = line.split("/");
             Book newBook = new Book(tokens[0].trim(),tokens[1].trim(),tokens[2].trim(),Integer.parseInt(tokens[3].trim()));
             int indexBook = checkForBook(newBook);
             if (indexBook > -1)
                bookSet.get(indexBook).addQuantity(newBook.getQuantity());
             else
                bookSet.add(newBook);
        }
    }

    public Iterator<Book> iterator() {
        return bookSet.iterator();
    }

    // later try binary search algo for practice
    public Book searchForIsbn(String inIsbn) {
        Book fakeBook = new Book("","",inIsbn,0);
        int indexBook = bookSet.indexOf(fakeBook);
        if (indexBook > -1)
            return bookSet.get(indexBook);
        else
            return null;
    }

    public ArrayList<Book> searchInTitle(String inPartOfTitle) {
        ArrayList<Book> outArrBook = new ArrayList<Book>();
        String partOfTitle = inPartOfTitle.toUpperCase();
        Iterator itr = bookSet.iterator();
        while(itr.hasNext()) {
            Book tmpBook = (Book) itr.next();
            if (tmpBook.getTitle().contains(partOfTitle))
                outArrBook.add(tmpBook);
        }
        return outArrBook;
    }

    public ArrayList<Book> searchInAuthor(String inPartOfAuthor) {
        ArrayList<Book> outArrBook = new ArrayList<Book>();
        String partOfAuthor = inPartOfAuthor.toUpperCase();
        Iterator itr = bookSet.iterator();
        while(itr.hasNext()) {
            Book tmpBook = (Book) itr.next();
            if (tmpBook.getAuthor().contains(partOfAuthor))
                outArrBook.add(tmpBook);
        }
        return outArrBook;
    }


    public ArrayList<Book> searchTitleByLetter(char inAlphabet) {
        Character startLetter = new Character(inAlphabet);
        startLetter = Character.toUpperCase(startLetter);
        ArrayList<Book> outArrBook = new ArrayList<Book>();
        sortCatalogueByTitle();
        Iterator itr = bookSet.iterator();
        while(itr.hasNext()) {
            Book tmpBook = (Book) itr.next();
            String tmpTitle = tmpBook.getTitle();
            String strRegex = "^[" + startLetter + "].";
            Matcher letterMatch = Pattern.compile(strRegex).matcher(tmpTitle);
            if (letterMatch.lookingAt())
                outArrBook.add(tmpBook);
        }
        return outArrBook;
    }

    // last name, first name issue not handled (only searches by first name for now)
    public ArrayList<Book> searchAuthorByLetter(char inAlphabet) {
        Character startLetter = new Character(inAlphabet);
        startLetter = Character.toUpperCase(startLetter);
        ArrayList<Book> outArrBook = new ArrayList<Book>();
        sortCatalogueByAuthor();
        Iterator itr = bookSet.iterator();
        while(itr.hasNext()) {
            Book tmpBook = (Book) itr.next();
            String tmpAuthor = tmpBook.getAuthor();
            String strRegex = "^[" + startLetter + "].";
            Matcher letterMatch = Pattern.compile(strRegex).matcher(tmpAuthor);
            if (letterMatch.lookingAt())
                outArrBook.add(tmpBook);
        }
        return outArrBook;
    }

    public String getHeader() {
        String strHeader;
        strHeader = "TITLE / AUTHOR / ISBN";
        return strHeader;
    }

    private void sortCatalogueByTitle() {
        TitleCompare titleComparator = new TitleCompare();
        Collections.sort(bookSet,titleComparator);
    }

    private void sortCatalogueByAuthor() {
        AuthorCompare authorComparator = new AuthorCompare();
        Collections.sort(bookSet,authorComparator);
    }

    public void viewCatalogueByTitle() {
        System.out.println(getHeader());
        sortCatalogueByTitle();
        Iterator itr = bookSet.iterator();
        while(itr.hasNext()) {
            Book tmpBook = (Book) itr.next();
            System.out.println(tmpBook);
        }
    }

    public void viewCatalogueByAuthor() {
        System.out.println(getHeader());
        AuthorCompare authorComparator = new AuthorCompare();
        Collections.sort(bookSet,authorComparator);
        Iterator itr = bookSet.iterator();
        while(itr.hasNext()) {
            Book tmpBook = (Book) itr.next();
            System.out.println(tmpBook);
        }
    }

    public boolean displaySearchResultList(ArrayList<Book> inBooks) {
        if (inBooks.size() != 0) {
            System.out.println(getHeader());
            for(Book iBk : inBooks)
                System.out.println(iBk);
            return true;
        }
        else
            return false;
    }

    public boolean displaySearchSingleBook(Book inBook) {
        if (inBook != null) {
            System.out.println(getHeader());
            System.out.println(inBook);
            return true;
        }
        else
            return false;
    }

    // does not handle (ignores) books in reservation list that cannot be found in catalogue
    public void updateFromReservationsFile(BufferedReader reader) throws IOException, InsufficientBooksException {
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("/");
            if (tokens.length < 2) continue;
            String isbnNum = tokens[1].trim();
            Book bookFound = searchForIsbn(isbnNum);
            if (bookFound != null)
                if (bookFound.reserveBook() == -1)
                    throw new InsufficientBooksException();
        }
    }

    public void updateAvailableBookQuantities(String inFilename) {
        try {
            File inputFile = new File(inFilename);
            FileReader filereader = new FileReader(inputFile);
            BufferedReader reader = new BufferedReader(filereader);
            updateFromReservationsFile(reader);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientBooksException e) {
            System.out.println(e.getMessage());
        }
    }

    class InsufficientBooksException extends Exception {
        public String getMessage() {
            return "Error in reservations file (default: reservations.txt): " +
                    "Compare with catalogue input file to ensure number of " +
                    "reservations for each book does not exceed quantity.";
        }
    }

    class TitleCompare implements Comparator<Book> {
        public int compare(Book bookOne, Book bookTwo) {
            return bookOne.getTitle().compareTo(bookTwo.getTitle());
        }
    }

    class AuthorCompare implements Comparator<Book> {
        public int compare(Book bookOne, Book bookTwo) {
            return bookOne.getAuthor().compareTo(bookTwo.getAuthor());
        }
    }

}
