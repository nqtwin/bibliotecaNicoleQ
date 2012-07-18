import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/18/12
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserList {
    private ArrayList<User> userList;

    public UserList(String inputFilename) {
        userList = new ArrayList<User>();
        try {
            File inputFile = new File(inputFilename);
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            generateUserList(bufferedReader);
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateUserList(BufferedReader reader) throws IOException {
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("/");
            User newUser = new User(tokens[0].trim(),tokens[1].trim());
            userList.add(newUser);
        }
    }

    public void displayUserList() {
        for (User iUser : userList)
            System.out.println(iUser);
    }

    public User checkForUser(User inUser) {
        if (userList.indexOf(inUser) == -1)
            return null;
        else return userList.get(userList.indexOf(inUser));
    }

}
