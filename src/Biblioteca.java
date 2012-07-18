/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/16/12
 * Time: 12:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class Biblioteca {

    Catalogue libCatalogue;
    UserList userList;
    RootMenuTreeNode rootNode;
    ReturnToRootMenuTreeNode returnToRootNode;
    EndActionMenuTreeNode endActionNode;

    private void initRootNode() {
        rootNode = new RootMenuTreeNode();
        rootNode.setPreMessageCustomBeforeDefault("WELCOME TO BIBLIOTECA, the Bangalore Public Library Online Reservation System!");
    }

    private void initReturnToRootNode() {
        returnToRootNode = new ReturnToRootMenuTreeNode();
        returnToRootNode.setMenuOption("Return to main menu");
    }

    private void initEndActionNode() {
        endActionNode = new EndActionMenuTreeNode();
    }

    private void initRequiredNodes() {
        initReturnToRootNode();
        initEndActionNode();
        initRootNode();
    }

    public void startNew() {
        libCatalogue = new Catalogue("testAddBooks.txt");
        libCatalogue.updateAvailableBookQuantities("reservations.txt");
        userList = new UserList("userlist.txt");
        initRequiredNodes();
        rootNode.executeMenuAction();
    }


    public static void main(String[] args) {
        Biblioteca libSession = new Biblioteca();
        libSession.startNew();
    }


    /* ******************************************************* */

    class SearchXMenuTreeNode extends MenuTreeNode {
        MenuTreeNode postSearchNode;

        public SearchXMenuTreeNode() {
            postSearchNode = new MenuTreeNode();
        }

        public MenuTreeNode getPostSearchNode() {
            return postSearchNode;
        }

        public void createSuccessfulChildren() {
            postSearchNode.resetChildNodes();

            ReserveBookMenuTreeNode reserveBookNode = new ReserveBookMenuTreeNode();
            reserveBookNode.setMenuOption("Reserve a book");
            postSearchNode.addChildNode(reserveBookNode);

            SearchCatalogueMenuTreeNode searchNode = new SearchCatalogueMenuTreeNode();
            searchNode.setMenuOption("Search for another book");
            postSearchNode.addChildNode(searchNode);

            postSearchNode.addChildNode(returnToRootNode);
        }

        public void createFailedChildren() {
            postSearchNode.resetChildNodes();

            SearchCatalogueMenuTreeNode searchNode = new SearchCatalogueMenuTreeNode();
            searchNode.setMenuOption("Return to search");
            postSearchNode.addChildNode(searchNode);

            postSearchNode.addChildNode(returnToRootNode);
        }
    }

    class FinishReservationMenuTreeNode extends MenuTreeNode {
        User user;
        Book book;
        MenuTreeNode postReservationNode;

        public FinishReservationMenuTreeNode(User inUser, Book inBook) {
            user = inUser;
            book = inBook;
            postReservationNode = new MenuTreeNode();
            postReservationNode.setPreMessageCustomBeforeDefault("What would you like to do now?");
        }

        public void executeMenuAction() {
            user.makeReservation(book);
            System.out.println("Book successfully reserved for collection. Thank you! Enjoy the book!");
            createPostReservationChildren();
            postReservationNode.executeMenuAction();
        }

        public void createPostReservationChildren() {
            ReserveBookMenuTreeNode reserveAnotherBookNode = new ReserveBookMenuTreeNode();
            reserveAnotherBookNode.setMenuOption("Reserve another book");
            postReservationNode.addChildNode(reserveAnotherBookNode);

            postReservationNode.addChildNode(returnToRootNode);
        }
    }

    class CheckIfBookIsReservableMenuTreeNode extends SearchXMenuTreeNode {
        Book bookToReserve;

        public CheckIfBookIsReservableMenuTreeNode(Book inBookToReserve) {
            bookToReserve = inBookToReserve;
            customInstruction = "Checking if book is available...";
        }

        public void executeMenuAction() {
            displayCustomInstruction();
            if (bookToReserve.isBookReservable()) {
                ConductReservationMenuTreeNode conductReservationNode = new
                        ConductReservationMenuTreeNode(bookToReserve);
                conductReservationNode.executeMenuAction();
            }
            else {
                postSearchNode.setPreMessageCustomBeforeDefault(
                        "Sorry, but all copies of this book have already been reserved");
                createFailedChildren();
                postSearchNode.executeMenuAction();
            }
        }

        public void createFailedChildren() {
            postSearchNode.resetChildNodes();

            ReserveBookMenuTreeNode reserveAnotherBookNode = new ReserveBookMenuTreeNode();
            reserveAnotherBookNode.setMenuOption("Reserve another book");
            postSearchNode.addChildNode(reserveAnotherBookNode);

            postSearchNode.addChildNode(returnToRootNode);
        }
    }

    class ConductReservationMenuTreeNode extends SearchXMenuTreeNode {
        Book bookToReserve;
        User userConductingReserve;

        public ConductReservationMenuTreeNode(Book inBookToReserve) {
            postSearchNode = new MenuTreeNode();
            customInstruction = "Please enter your library number:";
            bookToReserve = inBookToReserve;
        }

        public void executeMenuAction() {
            displayCustomInstruction();
            UserInput userInput = new UserInput();
            String userLibNum = userInput.getUserInput();
            User userToCheck = new User(userLibNum,"");
            userConductingReserve = userList.checkForUser(userToCheck);
            if (userConductingReserve == null) {
                postSearchNode.setPreMessageCustomBeforeDefault("Sorry, user not found.");
                createFailedChildren();
            }
            else {
                userConductingReserve.displayUserInfo();
                createSuccessfulChildren();
                postSearchNode.setPreMessageCustomBeforeDefault("Is this you?");
            }
            postSearchNode.executeMenuAction();
        }

        public void createSuccessfulChildren() {
            postSearchNode.resetChildNodes();

            FinishReservationMenuTreeNode finishReservationNode =
                    new FinishReservationMenuTreeNode(userConductingReserve,bookToReserve);
            finishReservationNode.setMenuOption("Yes it is!");
            postSearchNode.addChildNode(finishReservationNode);

            ConductReservationMenuTreeNode conductReservationAgainNode =
                    new ConductReservationMenuTreeNode(bookToReserve);
            conductReservationAgainNode.setMenuOption("No it isn't, let me try again");
            postSearchNode.addChildNode(conductReservationAgainNode);

            postSearchNode.addChildNode(returnToRootNode);
        }

        public void createFailedChildren() {
            postSearchNode.resetChildNodes();

            ConductReservationMenuTreeNode conductReservationAgainNode =
                    new ConductReservationMenuTreeNode(bookToReserve);
            conductReservationAgainNode.setMenuOption("I'd like to try entering my library number again");
            postSearchNode.addChildNode(conductReservationAgainNode);

            postSearchNode.addChildNode(returnToRootNode);
        }
    }

    class ReserveByIsbnMenuTreeNode extends SearchXMenuTreeNode {
        Book bookToReserve;

        public ReserveByIsbnMenuTreeNode() {
            postSearchNode = new MenuTreeNode();
            customInstruction = "Please enter ISBN of book you wish to reserve:";
        }

        public void executeMenuAction() {
            displayCustomInstruction();
            UserInput userInput = new UserInput();
            String userIsbn = userInput.getUserInput();
            bookToReserve = libCatalogue.searchForIsbn(userIsbn);
            if (libCatalogue.displaySearchSingleBook(bookToReserve)) {
                postSearchNode.setPreMessageCustomBeforeDefault("Is this the correct book?");
                createSuccessfulChildren();
            }
            else {
                postSearchNode.setPreMessageCustomBeforeDefault("Sorry! We do not have that book yet!");
                createFailedChildren();
            }
            postSearchNode.executeMenuAction();
        }

        public void createSuccessfulChildren() {
            postSearchNode.resetChildNodes();

            CheckIfBookIsReservableMenuTreeNode checkBookReservableNode =
                    new CheckIfBookIsReservableMenuTreeNode(bookToReserve);
            checkBookReservableNode.setMenuOption("Yes, please reserve.");
            postSearchNode.addChildNode(checkBookReservableNode);

            ReserveBookMenuTreeNode reserveBookNode = new ReserveBookMenuTreeNode();
            reserveBookNode.setMenuOption("No, this is not the right book. Return to previous menu.");
            postSearchNode.addChildNode(reserveBookNode);
        }

        public void createFailedChildren() {
            postSearchNode.resetChildNodes();

            ReserveByIsbnMenuTreeNode reserveAgainNode = new ReserveByIsbnMenuTreeNode();
            reserveAgainNode.setMenuOption("Try reserving by ISBN again");
            postSearchNode.addChildNode(reserveAgainNode);

            postSearchNode.addChildNode(returnToRootNode);
        }
    }



    class SearchTitleMenuTreeNode extends SearchXMenuTreeNode {
        public SearchTitleMenuTreeNode() {
            postSearchNode = new MenuTreeNode();
            customInstruction = "Please enter search:";
        }

        public void executeMenuAction() {
            displayCustomInstruction();
            UserInput userInput = new UserInput();
            String userIn = userInput.getUserInput();
            if (libCatalogue.displaySearchResultList(libCatalogue.searchInTitle(userIn)))
                createSuccessfulChildren();
            else {
                postSearchNode.setPreMessageCustomBeforeDefault("Search did not produce any results.");
                createFailedChildren();
            }
            postSearchNode.executeMenuAction();
        }
    }

    class SearchAuthorMenuTreeNode extends SearchXMenuTreeNode {
        public SearchAuthorMenuTreeNode() {
            postSearchNode = new MenuTreeNode();
            customInstruction = "Please enter search:";
        }

        public void executeMenuAction() {
            displayCustomInstruction();
            UserInput userInput = new UserInput();
            String userIn = userInput.getUserInput();
            if (libCatalogue.displaySearchResultList(libCatalogue.searchInAuthor(userIn)))
                createSuccessfulChildren();

            else {
                postSearchNode.setPreMessageCustomBeforeDefault("Search did not produce any results.");
                createFailedChildren();
            }

            postSearchNode.executeMenuAction();
        }
    }

    class SearchTitleByLetterMenuTreeNode extends SearchXMenuTreeNode {
        public SearchTitleByLetterMenuTreeNode() {
            postSearchNode = new MenuTreeNode();
            customInstruction = "Please enter letter:";
        }

        public void executeMenuAction() {
            displayCustomInstruction();
            UserInput userInput = new UserInput();
            char searchChar = userInput.handleUserInputChar();
            if (Character.isLetter(searchChar)) {
                if (libCatalogue.displaySearchResultList(libCatalogue.searchTitleByLetter(searchChar)))
                    createSuccessfulChildren();
                else {
                    postSearchNode.setPreMessageCustomBeforeDefault("Search did not produce any results.");
                    createFailedChildren();
                }

                postSearchNode.executeMenuAction();
            }
            else {
                System.out.println("Please enter single starting letter!!");
                executeMenuAction();
            }
        }
    }

    class SearchAuthorByLetterMenuTreeNode extends SearchXMenuTreeNode {
        public SearchAuthorByLetterMenuTreeNode() {
            postSearchNode = new MenuTreeNode();
            customInstruction = "Please enter letter:";
        }

        public void executeMenuAction() {
            displayCustomInstruction();
            UserInput userInput = new UserInput();
            char searchChar = userInput.handleUserInputChar();
            if (Character.isLetter(searchChar)) {
                if (libCatalogue.displaySearchResultList(libCatalogue.searchAuthorByLetter(searchChar)))
                    createSuccessfulChildren();
                else {
                    postSearchNode.setPreMessageCustomBeforeDefault("Search did not produce any results.");
                    createFailedChildren();
                }
                postSearchNode.executeMenuAction();
            }
            else {
                System.out.println("Please enter single starting letter!!");
                executeMenuAction();
            }
        }
    }

    class CheckLibNumTreeNode extends MenuTreeNode {
        public void executeMenuAction() {
            System.out.println("Please talk to librarian. Thank you!");
            endActionNode.executeMenuAction();
        }
    }


    class ViewCatalogueByTitleMenuTreeNode extends MenuTreeNode {
        public void executeMenuAction() {
            libCatalogue.viewCatalogueByTitle();
            endActionNode.executeMenuAction();
        }
    }

    class ViewCatalogueByAuthorMenuTreeNode extends MenuTreeNode {
        public void executeMenuAction() {
            libCatalogue.viewCatalogueByAuthor();
            endActionNode.executeMenuAction();
        }
    }

    class ReturnToRootMenuTreeNode extends MenuTreeNode {
        public void executeMenuAction() {
            rootNode.executeMenuAction();
        }
    }

    class EndActionMenuTreeNode extends MenuTreeNode {
        public EndActionMenuTreeNode() {
            customInstruction = "Please hit enter to return to main menu.";
        }

        public void executeMenuAction() {
            displayCustomInstruction();
            UserInput userInput = new UserInput();
            userInput.getUserInput();
            rootNode.executeMenuAction();
        }
    }

    class ReserveBookMenuTreeNode extends MenuTreeNode {
        public ReserveBookMenuTreeNode() {
            createChildren();
        }

        public void createChildren() {
            ReserveByIsbnMenuTreeNode reserveByIsbnNode = new ReserveByIsbnMenuTreeNode();
            reserveByIsbnNode.setMenuOption("Know your book's ISBN number? Reserve here");
            addChildNode(reserveByIsbnNode);

            SearchCatalogueMenuTreeNode searchFromReserveNode = new SearchCatalogueMenuTreeNode();
            searchFromReserveNode.setMenuOption("Don't know the ISBN? Search for your book here");
            addChildNode(searchFromReserveNode);

            addChildNode(returnToRootNode);
        }
    }

    class SearchCatalogueMenuTreeNode extends MenuTreeNode {
        public SearchCatalogueMenuTreeNode() {
            createChildren();
        }

        public void createChildren() {
            SearchTitleMenuTreeNode searchTitleNode = new SearchTitleMenuTreeNode();
            searchTitleNode.setMenuOption("Search for part of title");
            addChildNode(searchTitleNode);

            SearchAuthorMenuTreeNode searchAuthorNode = new SearchAuthorMenuTreeNode();
            searchAuthorNode.setMenuOption("Search for part of author's name");
            addChildNode(searchAuthorNode);

            SearchTitleByLetterMenuTreeNode searchTitleLetterNode = new SearchTitleByLetterMenuTreeNode();
            searchTitleLetterNode.setMenuOption("Search by first letter of title");
            addChildNode(searchTitleLetterNode);

            SearchAuthorByLetterMenuTreeNode searchAuthorLetterNode = new SearchAuthorByLetterMenuTreeNode();
            searchAuthorLetterNode.setMenuOption("Search by first letter of author");
            addChildNode(searchAuthorLetterNode);

            addChildNode(returnToRootNode);
        }

    }

    class ViewCatalogueMenuTreeNode extends MenuTreeNode {
        public ViewCatalogueMenuTreeNode() {
            createChildren();
        }

        public void createChildren() {
            ViewCatalogueByTitleMenuTreeNode viewByTitleNode = new ViewCatalogueByTitleMenuTreeNode();
            viewByTitleNode.setMenuOption("View catalogue in alphabetical order by title");
            addChildNode(viewByTitleNode);

            ViewCatalogueByAuthorMenuTreeNode viewByAuthorNode = new ViewCatalogueByAuthorMenuTreeNode();
            viewByAuthorNode.setMenuOption("View catalogue in alphabetical order by author");
            addChildNode(viewByAuthorNode);

            addChildNode(returnToRootNode);
        }
    }

    class ExitProgramMenuTreeNode extends MenuTreeNode {
        public ExitProgramMenuTreeNode() {
            customInstruction = "Thank you for using Biblioteca and have a good day!";
        }

        public void executeMenuAction() {
            displayCustomInstruction();
        }
    }

    class RootMenuTreeNode extends MenuTreeNode {
        public RootMenuTreeNode() {
            createChildren();
        }

        public void createChildren() {
            ViewCatalogueMenuTreeNode catalogueNode = new ViewCatalogueMenuTreeNode();
            catalogueNode.setMenuOption("View catalogue");
            addChildNode(catalogueNode);

            SearchCatalogueMenuTreeNode searchNode = new SearchCatalogueMenuTreeNode();
            searchNode.setMenuOption("Search for book");
            addChildNode(searchNode);

            ReserveBookMenuTreeNode reserveNode = new ReserveBookMenuTreeNode();
            reserveNode.setMenuOption("Reserve book");
            addChildNode(reserveNode);

            CheckLibNumTreeNode checkLibNumNode = new CheckLibNumTreeNode();
            checkLibNumNode.setMenuOption("Check your library number");
            addChildNode(checkLibNumNode);

            ExitProgramMenuTreeNode exitProgramNode = new ExitProgramMenuTreeNode();
            exitProgramNode.setMenuOption("Quit Biblioteca");
            addChildNode(exitProgramNode);
        }
    }

}
