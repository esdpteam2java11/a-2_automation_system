package com.a2.a2_automation_system.index;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    @GetMapping("/club")
    public String getClub() {
        return "club";
    }

    @GetMapping("/trainer1")
    public String getTrainer1() {
        return "trainer1";
    }

    @GetMapping("/trainer2")
    public String getTrainer2() {
        return "trainer2";
    }

    @GetMapping("/training")
    public String getTraining() {
        return "training";
    }

    @GetMapping("/benefit")
    public String getBenefit() {
        return "benefit";
    }

}
