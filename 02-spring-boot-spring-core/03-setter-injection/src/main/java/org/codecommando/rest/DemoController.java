package org.codecommando.rest;

import org.codecommando.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;

    // Setter method injection
    @Autowired
    public void setCoacbh(Coach coach) {
        myCoach = coach;
    }

    // We can give any name for setter method
    @Autowired
    public void doSomeStuff(Coach coach) {
        myCoach = coach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
