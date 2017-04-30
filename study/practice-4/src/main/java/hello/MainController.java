package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asxat on 2/25/17.
 */

@Controller
public class MainController {

    @Autowired
    CustomerRepository repository;

    @PutMapping(path = "/customer")
    public @ResponseBody String addCustomer(@RequestBody CustomerForm form) {
        Customer c = new Customer(form.name, form.surname);
        return repository.save(c).id;
    }

    @GetMapping(path = "/customer")
    public @ResponseBody List<Customer> getCustomers() {
        return repository.findAll();
    }

    @DeleteMapping(path = "/customer")
    public @ResponseBody String deleleCustomers() {
        repository.deleteAll();
        return "OK";
    }


}
