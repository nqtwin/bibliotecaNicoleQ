/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/30/12
 * Time: 8:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Movie implements Media {
    private String title;
    private String director;
    private String idNum;
    private String rating;
    private int quantity;
    private int quantFree;

    public Movie(String inTitle, String inDirector, String inIdNum, String inRating) {
        title = inTitle.toUpperCase();
        director = inDirector.toUpperCase();
        idNum = inIdNum.toUpperCase();
        rating = inRating;
    }

    public String toString() {
        return title + " / " + director + " / " + rating;
    }

    public boolean equals(Object movieObj) {
        Movie movieTwo = (Movie) movieObj;
        return (this.getIdNum().equals(movieTwo.getIdNum()));
    }

    public void addQuantity(int addQuant) {
        quantity += addQuant;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getIdNum() {
        return idNum;
    }

    public String getRating() {
        return rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getNumFree() {
        return quantFree;
    }

    public int getNumReserved() {
        return (quantity - quantFree);
    }

}
