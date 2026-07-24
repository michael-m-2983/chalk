package md.chalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class ChalkApplication {

	@RequestMapping("/")
	public String home() {
		return "Chalk backend";
	}

	public static void main(String[] args) {
		SpringApplication.run(ChalkApplication.class, args);
	}

}
