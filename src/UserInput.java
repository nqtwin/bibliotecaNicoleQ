import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/17/12
 * Time: 2:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserInput {

    public int handleUserInput(MenuTreeNode currentMenu) {
        String userIn = getUserInput();
        if (checkUserInput(userIn,currentMenu))
            return Integer.parseInt(userIn);
        else
            return -1;
    }

    public char handleUserInputChar() {
        String userIn = getUserInput();
        if (checkUserInputChar(userIn))
            return userIn.charAt(0);
        else
            return '0';
    }

    public String getUserInput() {
        String line = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(isr);
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return line;
    }

    public boolean checkUserInput(String userInput, MenuTreeNode currentMenu) {
        try {
            int i = Integer.parseInt(userInput);
            if ((i > currentMenu.getNumChildNodes()) || (i <= 0)) {
                return false;
            }
            else return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkUserInputChar(String userInput) {
        if (userInput.length() > 1)
            return false;
        else {
            if (Character.isLetter(userInput.charAt(0)))
                return true;
            else
                return false;
        }
    }

}
