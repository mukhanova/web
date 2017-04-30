package hello;

/**
 * Created by Meruyert on 29.04.17.
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String name;
    private ArrayList<String> paths = new ArrayList<String>();

    public ArrayList<String> getPaths() {
        return paths;
    }

    public void setPaths(String path) {
        this.paths.add(path);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}