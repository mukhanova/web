package hello;

import org.springframework.data.annotation.Id;

/**
 * Created by asxat on 2/25/17.
 */
public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(

                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }


    // CRUD - DB

    // [PUT]:       /user
    // [GET]:       /user/{id}
    // [POST]:      /user/{id}
    // [DELETE]:    /user/{id}
    // [GET]:       /user
    // [DELETE]:    /user

}
