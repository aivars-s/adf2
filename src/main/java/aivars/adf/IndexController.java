package aivars.adf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private static final String INDEX_TEMPLATE = "index";

    @GetMapping({"", "/"})
    public String index() {
        return INDEX_TEMPLATE;
    }

}
