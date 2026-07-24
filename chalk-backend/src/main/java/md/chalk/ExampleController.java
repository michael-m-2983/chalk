package md.chalk;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {
    @RequestMapping("/")
	public String home() {
		return "Chalk backend";
	}

    @RequestMapping("/length/{s}")
    public String length(@PathVariable String s) {
        return "Length is: " + s.length();
    }
}
