package application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class HelloController {

    @Autowired
    LocaleResolver localeResolver;

    @RequestMapping(value = "/home.html")
    public ModelAndView index(HttpServletRequest request) throws Exception {

        if(request.getParameter("lang") == null)
        {
            localeResolver.setLocale(request, null, Locale.US);
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("value", 5);
        return new ModelAndView("home/home", modelMap);
    }

    @RequestMapping(value = "/{lang}/home.html")
    public ModelAndView index2(HttpServletRequest request, HttpServletResponse response, @PathVariable("lang") String lang) throws Exception {

        localeResolver.setLocale(request, response, StringUtils.parseLocaleString(lang));
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("value", 5);
        return new ModelAndView("home/home", modelMap);
    }
}
