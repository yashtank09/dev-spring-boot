package org.codecommando.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // inject properties for: coach.name and team.name

    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/teaminfo")
    public String teamInfo() {
        return "Coach: " + coachName + ",  Team Name:  " + teamName;
    }

    // expose "/" that return "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello World";
    }

    // expose a new endpoint for "workout"

    @GetMapping("/workout")
    public String getdailyworkout() {
        return "Run a hard 5K!";
    }
}
