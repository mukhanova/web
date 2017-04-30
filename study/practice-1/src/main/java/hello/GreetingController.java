package hello;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asxat on 2/4/17.
 */

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam Map<String, String> params) {
        return new Greeting(counter.incrementAndGet(), String.format(template, params.get("name")));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Student register(@RequestParam Map<String, String> params) {

        Student s = new Student(
                counter.incrementAndGet(),
                params.get("name"),
                params.get("surname"),
                Integer.parseInt(params.get("age")),
                Group.getGroup()
        );

        return s;

    }

}
