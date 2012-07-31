package biblioteca.frontend;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * biblioteca.libFunctions.User: ThoughtWorks
 * Date: 7/24/12
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserOutput {

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayMessage(ArrayList<String> message) {
        for (String messageLine : message)
            System.out.println(messageLine);
    }
}
