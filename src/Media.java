/**
 * Created with IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: 7/30/12
 * Time: 8:06 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Media {
    String title = null;
    String idNum = null;
    int quantity = 0;
    int quantFree = 0;

    public String toString();

    public boolean equals(Object bookObj);

    public void addQuantity(int addQuant);

    public String getTitle();

    public String getIdNum();

    public int getQuantity();

    public int getNumFree();

    public int getNumReserved();

}
