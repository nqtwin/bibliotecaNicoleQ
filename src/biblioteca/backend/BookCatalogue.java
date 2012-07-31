package biblioteca.backend;

import biblioteca.libFunctions.Book;
import biblioteca.libFunctions.Media;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: nquah
 * Date: 7/16/12
 * Time: 12:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class BookCatalogue implements Catalogue {
    private ArrayList<Media> bookSet;

    public BookCatalogue() {
        bookSet = new ArrayList<Media>();
    }

    public BookCatalogue(String inputFilename) {
        bookSet = new ArrayList<Media>();
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
    private int checkForItem(Book bookToCheck) {
        return bookSet.indexOf(bookToCheck);
    }

    public void addItems(BufferedReader reader) throws IOException {
        String line = null;
        while ((line = reader.readLine()) != null) {
             String[] tokens = line.split("/");
             Book newBook = new Book(tokens[0].trim(),tokens[1].trim(),tokens[2].trim(),Integer.parseInt(tokens[3].trim()));
             int indexBook = checkForItem(newBook);
             if (indexBook > -1)
                bookSet.get(indexBook).addQuantity(newBook.getQuantity());
             else
                bookSet.add(newBook);
        }
    }

    public ArrayList<Media> getItems() {
        return bookSet;
    }

    public Iterator<Media> getIterator() {
        return bookSet.iterator();
    }

    // later try binary search algo for practice
    public Media searchForIdNum(String inIdNum) {
        Book fakeBook = new Book("","",inIdNum,0);
        int indexBook = bookSet.indexOf(fakeBook);
        if (indexBook > -1)
            return bookSet.get(indexBook);
        else
            return null;
    }

    public ArrayList<Media> searchInTitle(String inPartOfTitle) {
        sortCatalogueByTitle();
        ArrayList<Media> outArrBook = new ArrayList<Media>();
        String partOfTitle = inPartOfTitle.toUpperCase();
        Iterator itr = bookSet.iterator();
        while(itr.hasNext()) {
            Book tmpBook = (Book) itr.next();
            if (tmpBook.getTitle().contains(partOfTitle))
                outArrBook.add(tmpBook);
        }
        if (outArrBook.size() == 0)
            return null;
        else return outArrBook;
    }

    public ArrayList<Media> searchInAuthor(String inPartOfAuthor) {
        sortCatalogueByAuthor();
        ArrayList<Media> outArrBook = new ArrayList<Media>();
        String partOfAuthor = inPartOfAuthor.toUpperCase();
        Iterator itr = bookSet.iterator();
        while(itr.hasNext()) {
            Book tmpBook = (Book) itr.next();
            if (tmpBook.getAuthor().contains(partOfAuthor))
                outArrBook.add(tmpBook);
        }
        if (outArrBook.size() == 0)
            return null;
        else return outArrBook;
    }


    public ArrayList<Media> searchTitleByLetter(char inAlphabet) {
        Character startLetter = new Character(inAlphabet);
        startLetter = Character.toUpperCase(startLetter);
        ArrayList<Media> outArrBook = new ArrayList<Media>();
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
        if (outArrBook.size() == 0)
            return null;
        else return outArrBook;
    }

    // last name, first name issue not handled (only searches by first name for now)
    public ArrayList<Media> searchAuthorByLetter(char inAlphabet) {
        Character startLetter = new Character(inAlphabet);
        startLetter = Character.toUpperCase(startLetter);
        ArrayList<Media> outArrBook = new ArrayList<Media>();
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
        if (outArrBook.size() == 0)
            return null;
        else return outArrBook;
    }

    public void sortCatalogueByTitle() {
        TitleCompare titleComparator = new TitleCompare();
        Collections.sort(bookSet,titleComparator);
    }

    public void sortCatalogueByAuthor() {
        AuthorCompare authorComparator = new AuthorCompare();
        Collections.sort(bookSet,authorComparator);
    }

    // does not handle (ignores) books in reservation list that cannot be found in catalogue
    public void updateFromReservationsFile(BufferedReader reader) throws IOException, InsufficientBooksException {
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("/");
            if (tokens.length < 2) continue;
            String isbnNum = tokens[1].trim();
            Book bookFound = (Book) searchForIdNum(isbnNum);
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

    public class InsufficientBooksException extends Exception {
        public String getMessage() {
            return "Error in reservations file (default: reservations.txt): " +
                    "Compare with catalogue input file to ensure number of " +
                    "reservations for each book does not exceed quantity.";
        }
    }

    class TitleCompare implements Comparator<Media> {
        public int compare(Media bookOne, Media bookTwo) {
            Book bkOne = (Book) bookOne;
            Book bkTwo = (Book) bookTwo;
            return bkOne.getTitle().compareTo(bkTwo.getTitle());
        }
    }

    class AuthorCompare implements Comparator<Media> {
        public int compare(Media bookOne, Media bookTwo) {
            Book bkOne = (Book) bookOne;
            Book bkTwo = (Book) bookTwo;
            return bkOne.getAuthor().compareTo(bkTwo.getAuthor());
        }
    }

}
