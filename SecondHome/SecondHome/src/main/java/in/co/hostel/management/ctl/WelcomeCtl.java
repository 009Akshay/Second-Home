package in.co.hostel.management.ctl;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeCtl {

	@GetMapping("/welcome")
	public String display(Model model) {
		return "welcome";
	}

	@GetMapping("/aboutUs")
	public String aboutUs(Model model) {
		return "aboutUs";
	}

	@GetMapping("/contactUs")
	public String contactUs(Model model) {
		return "contactUs";
	}


}
