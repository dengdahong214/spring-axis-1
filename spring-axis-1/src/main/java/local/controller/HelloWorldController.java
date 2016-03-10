package local.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by plks on 2016-02-24.
 */
@Controller
public class HelloWorldController {
    @RequestMapping("/hello-world")
    @ResponseBody
    public String example1() {
        return "hello world from spring controller";
    }
}
