package de.primeapi.gowebsite.controllers;

import de.primeapi.gowebsite.GoWebsiteApplication;
import de.primeapi.gowebsite.entitites.Site;
import de.primeapi.gowebsite.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lukas S. PrimeAPI
 * created on 31.07.2022
 * crated for GoWebsite
 */

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    SiteRepository siteRepository;

    @GetMapping
    public String readCookie(Model model, @CookieValue(value = "password", defaultValue = "NONE") String password, @RequestParam(required = false) Integer delete) {
        model.addAttribute("password", password);
        if(password.equals(GoWebsiteApplication.PASSWORD)){

            if(delete != null){
                siteRepository.deleteById(delete);
            }

            model.addAttribute("sites", siteRepository.findAll());
            return "settings";
        }else
            return "login";

    }

    @PostMapping
    public String login(@RequestBody MultiValueMap<String, String> formData, Model model, HttpServletResponse response){
        String password = formData.getFirst("password");
        model.addAttribute("password", password);
        Cookie cookie = new Cookie("password", password);
        cookie.setMaxAge(10 * 60); // expires in 7 days
        //add cookie to response
        response.addCookie(cookie);

        if(password.equals(GoWebsiteApplication.PASSWORD)) {
            model.addAttribute("sites", siteRepository.findAll());
            return "settings";
        }else
            return "login";

    }

    @PostMapping("/add")
    public String add(@RequestBody MultiValueMap<String, String> formData, Model model, HttpServletResponse response, @CookieValue(value = "password", defaultValue = "NONE") String password){

        if(password.equals(GoWebsiteApplication.PASSWORD)) {

            Site newSite = new Site();
            newSite.setName(formData.getFirst("name"));
            newSite.setUrl(formData.getFirst("url"));
            siteRepository.save(newSite);

            model.addAttribute("sites", siteRepository.findAll());
            return "settings";
        }else
            return "login";

    }

    /*
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }

     */

}
