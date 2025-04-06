package org.codecommando.rest;

import org.codecommando.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;

    /**
     * If we set multiple primary Coach it will raise an
     * Error: No qualifying bean of type 'org.codecommando.common.Coach' available: more than one 'primary' bean found among candidates: [baseballCoach, cricketCoach, tennisCoach, trackCoach]
     * Beans:
     *      baseballCoach (BaseballCoach.java)
     *      tennisCoach (TennisCoach.java)
     *      trackCoach (TrackCoach.java)
     *
     * @param coach
     */
    @Autowired
    public DemoController(Coach coach) {
         myCoach = coach;
     }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
