package org.codecommando.rest;

import org.codecommando.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;


    /*
     Error:
     Could not autowire. There is more than one bean of 'Coach' type.
        Beans:
        baseballCoach (BaseballCoach.java)
        cricketCoach (CricketCoach.java)
        tennisCoach (TennisCoach.java)
        trackCoach (TrackCoach.java)
     */
    // @Autowired
    // public DemoController(Coach coach) {
    //     myCoach = coach;


    @Autowired
     public DemoController(@Qualifier("cricketCoach") Coach coach) {
         myCoach = coach;
     }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
