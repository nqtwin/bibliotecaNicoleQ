import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/15/12
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private String libNum;
    private String name;
    private ArrayList<Reservation> resList;

    public User(String inLibNum, String inName) {
        libNum = inLibNum.toUpperCase();
        name = inName.toUpperCase();
        resList = new ArrayList<Reservation>();
    }

    public String getLibNum() {
        return libNum;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object userObject) {
        User inUser = (User) userObject;
        if (inUser.getLibNum().equals(libNum))
            return true;
        else return false;
    }

    public String getUserInfoHeader() {
        String userInfoHeader = "NAME / LIBRARY NUMBER";
        return userInfoHeader;
    }

    public void displayUserInfo() {
        System.out.println(getUserInfoHeader());
        System.out.println(toString());
    }

    public String toString() {
        String output = name + " / " + libNum;
        return output;
    }

    public int makeReservation(Book bookToRes) {
        if (bookToRes.reserveBook() == 1) {
            Reservation newReservation = new Reservation(bookToRes);
            resList.add(newReservation);
            newReservation.appendTransactionToFile();
            return 1;
        }
        else return -1;
    }

    // add method to removeReservation when book is returned

    private ArrayList<String> getResInfoHeader() {
        ArrayList<String> resInfo = new ArrayList<String>();
        String header = "TITLE / AUTHOR / ISBN / DUE DATE";
        resInfo.add(header);
        return resInfo;
    }

    public ArrayList<String> getResInfo() {
        ArrayList<String> resInfo = getResInfoHeader();
        for (Reservation r : resList) {
            resInfo.add(r.toString());
        }
        return resInfo;
    }

    class Reservation {
        private Book resBook;
        private String dueDate; // change to Calendar object
        private String fileToAppendTo;

        public Reservation(Book inBook) {
            resBook = inBook;
            dueDate = "01 Jan 2001";
            fileToAppendTo = "reservations.txt";
        }

        public Reservation(Book inBook, String inFilename) {
            resBook = inBook;
            dueDate = "01 Jan 2001";
            fileToAppendTo = inFilename;
        }

        public Book getResBook() {
            return resBook;
        }

        public String toString() {
            String strInfo = "";
            strInfo = resBook.getTitle() + " / " + resBook.getAuthor() + " / "
                    + resBook.getIsbn() + " / " + dueDate;
            return strInfo;
        }

        public String getTransactionInfo() {
            String output = getLibNum() + " / " + resBook.getIsbn() + " / " + dueDate;
            return output;
        }

        public void appendTransactionToFile() {
            try {
                FileWriter filewriter = new FileWriter(fileToAppendTo,true);
                PrintWriter writer = new PrintWriter(new BufferedWriter(filewriter));
                writer.println(getTransactionInfo());
                writer.flush();
                writer.close();
            }  catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
