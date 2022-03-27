package com.sign.languagetranslate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class SignController {

//    @GetMapping("/index")
//    public String getImage(Model model) {
////        model.addAttribute("imageLoc", imageFinder(greeting));
//        model.addAttribute("options", new char[] {'d', 'a'});
//        System.out.println("greeting");
//        return "index";
//    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        model.addAttribute("imageLoc", imageFinder(greeting.getContent()));
        model.addAttribute("series", getImagesSeries(greeting.getContentTest()));
        model.addAttribute("seriesTest", getSeries(greeting.getContentTest()));

        return "result";
    }

    private String imageFinder(String greeting) {
//        String fileLocation = "resources/static/" + greeting + ".webp";
        String fileLocation = "/img/" + greeting + ".png";
        return fileLocation;
    }

    private List<String> populateGreetings() {
        ArrayList<String> greetings = new ArrayList<>();
        greetings.add("Hello");
        greetings.add("How are you?");
        return greetings;
    }

    private String imageSeriesFinder(char letter) {
        String fileLocation = "/img/sign/" + letter + ".JPG";
        return fileLocation;
    }

    private List<String> getImagesSeries(String word) {
        List<String> imageSeries = new ArrayList<>();
        char[] letters = word.toCharArray();
        for(int i = 0; i < letters.length; i++) {
            imageSeries.add(imageSeriesFinder(letters[i]));
        }
        return imageSeries;
    }

    private Map<String, List<String>> getSeries(String word) {
        word = word + " ";
        List<String> imageSeries = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        String key = "";
        int count = 1;
        char[] letters = word.toCharArray();
        for(int i = 0; i < letters.length; i++) {
            if(letters[i] == ' ') {
                map.put(key, imageSeries);
                imageSeries = new ArrayList<>();
                count++;
                key = "";
            } else {
                key+=letters[i];
                imageSeries.add(imageSeriesFinder(letters[i]));
//                map.put(count, imageSeries);
            }
        }
        return map;
    }
}
