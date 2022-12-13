package com.shchoholiev.recipes.api.controllers;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/health")
public class HealthController {

    @GetMapping("check")
    public Object check(){
        return new Object(){ public String status = "running"; };
    }
}
