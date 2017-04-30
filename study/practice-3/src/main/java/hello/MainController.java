package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by asxat on 2/18/17.
 */

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getAllUsers(@PathVariable("id")int id) {
        return userRepository.findOne(id);
    }


    // REST full -> CRUD

    // [PUT]:       /user       --> create new user
    // [POST]:      /user/{id}  --> updates user
    // [GET]:       /user/{id}  --> read user
    // [DELETE]:    /user/{id}  --> delete user

    // [GET]:       /user       --> get all users
    // [DELETE]     /user       --> deletes all users



}
