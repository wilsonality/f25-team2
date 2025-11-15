package com.team2.spartanslist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping({"", "/"})
    public String redirectToLanding() {
        return "redirect:/home";
    }

    
}
