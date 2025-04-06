package org.codecommando.rest;

import org.codecommando.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;

    private Coach spareCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach coach, @Qualifier("cricketCoach") Coach theSpareCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
         myCoach = coach;
         spareCoach = theSpareCoach;
     }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check() {
        return "Comparing beans: myCoach=spareCoach, " + (myCoach == spareCoach);
    }
}
