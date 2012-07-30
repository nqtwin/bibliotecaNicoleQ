/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/16/12
 * Time: 12:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class Biblioteca {

    BookCatalogue libBookCatalogue;
    MovieCatalogue libMovieCatalogue;
    CatalogueRenderer catalogueRenderer;
    UserList userList;
    RootMenuTreeNode rootNode;
    ReturnToRootMenuTreeNode returnToRootNode;
    EndActionMenuTreeNode endActionNode;
    ExitProgramMenuTreeNode exitProgramNode;
    UserInput userInput;

    private void initRootNode() {
        rootNode = new RootMenuTreeNode(this);
        rootNode.setPreMessageCustomBeforeDefault("WELCOME TO BIBLIOTECA, the Bangalore Public Library Online Reservation System!");
    }

    private void initReturnToRootNode() {
        returnToRootNode = new ReturnToRootMenuTreeNode();
        returnToRootNode.setMenuOption("Return to main menu");
    }

    private void initEndActionNode() {
        endActionNode = new EndActionMenuTreeNode();
    }

    private void initExitProgramNode() {
        exitProgramNode = new ExitProgramMenuTreeNode();
        exitProgramNode.setMenuOption("Quit Biblioteca");
    }

    private void initRequiredNodes() {
        initReturnToRootNode();
        initEndActionNode();
        initExitProgramNode();
        initRootNode();
    }

    public ReturnToRootMenuTreeNode getReturnToRootNode() {
        return returnToRootNode;
    }

    public EndActionMenuTreeNode getEndActionNode() {
        return endActionNode;
    }

    public ExitProgramMenuTreeNode getExitProgramNode() {
        return exitProgramNode;
    }

    public BookCatalogue getBookCatalogue() {
        return libBookCatalogue;
    }

    public MovieCatalogue getMovieCatalogue() {
        return libMovieCatalogue;
    }

    public UserList getUserList() {
        return userList;
    }

    public void startNew() {
        libBookCatalogue = new BookCatalogue("testAddBooks.txt");
        libBookCatalogue.updateAvailableBookQuantities("reservations.txt");

        libMovieCatalogue = new MovieCatalogue("moviesInCatalogue.txt");

        userList = new UserList("userlist.txt");
        catalogueRenderer = new CatalogueRenderer();
        userInput = new UserInput();
        initRequiredNodes();
        rootNode.executeMenuAction(userInput);
    }


    public static void main(String[] args) {
        Biblioteca libSession = new Biblioteca();
        libSession.startNew();
    }

    class ReturnToRootMenuTreeNode extends MenuTreeNode {
        @Override
        public void executeMenuAction(UserInput userInput) {
            rootNode.executeMenuAction(userInput);
        }
    }

    class EndActionMenuTreeNode extends MenuTreeNode {
        public EndActionMenuTreeNode() {
            super();
            customInstruction = "Please hit enter to return to main menu.";
        }

        @Override
        public void executeMenuAction(UserInput userInput) {
            userOutput.displayMessage(getCustomInstruction());
            userInput.getUserInput();
            rootNode.executeMenuAction(userInput);
        }
    }
}




