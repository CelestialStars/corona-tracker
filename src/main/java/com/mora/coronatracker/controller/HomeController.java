package com.mora.coronatracker.controller;

import com.mora.coronatracker.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    DataService service;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("locationStats", service.getAllStats());
        model.addAttribute("totalConfirmedCases", service.totalCases());
        return "home";
    }
}
