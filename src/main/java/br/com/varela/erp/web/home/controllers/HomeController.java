package br.com.varela.erp.web.home.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    
    @GetMapping
    public String index(){
        return "home/index";
    }

    @GetMapping("/format-data")
    public ModelAndView formatData() {
        var model = Map.of(
            "locale", Locale.of("en-us"),
            "date", LocalDate.of(2025, 3, 11),
            "datetime", LocalDateTime.of(2025,3,17,8,0,0),
            "currency", 3513.51,
            "percent", 50.0/100
        );
        return new ModelAndView("format-data", model);
    }
}
