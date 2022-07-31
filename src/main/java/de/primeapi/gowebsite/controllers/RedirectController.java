package de.primeapi.gowebsite.controllers;

import de.primeapi.gowebsite.entitites.Site;
import de.primeapi.gowebsite.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lukas S. PrimeAPI
 * created on 31.07.2022
 * crated for GoWebsite
 */
@Controller
public class RedirectController {

    @Autowired
    SiteRepository siteRepository;

    @RequestMapping(value = "/**", method = {RequestMethod.GET})
    public String redirect(HttpServletResponse httpServletResponse, HttpServletRequest request) {

        Site site = siteRepository.findByName(request.getServletPath());
        if(site != null){
            httpServletResponse.setHeader("Location", site.getUrl());
            httpServletResponse.setStatus(302);
        }

        return "redirect";
    }


}
