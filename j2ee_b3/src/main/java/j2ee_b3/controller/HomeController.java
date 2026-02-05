package j2ee_b3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import j2ee_b3.model.Student;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/")
    public String hello() {
        return "home";
    }
    
    @GetMapping("/demo")
    public String demoPage(Model model) {
        Student student = new Student(1, "Trai");
        model.addAttribute("student", student);
        model.addAttribute("message", "Welcome to the demo page!");
        return "demo";
    }

    
}
