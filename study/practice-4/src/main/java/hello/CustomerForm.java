package hello;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by asxat on 2/25/17.
 */
public class CustomerForm {

    @NotEmpty
    public String name;
    public String surname;

    public CustomerForm() {
    }



}
