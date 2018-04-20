package com.example.springsocialdemo.controller;

import com.example.springsocialdemo.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    TwitterService twitterService;

    @GetMapping("/postTweet")
    public void postTweet() throws InterruptedException {
        final String[] tweets = {
                "Le jeune Link vit paisiblement dans la forêt Kokiri",
                "Avec l’arbre Mojo jusqu’au jour où Ghoma s’infiltre dans la forêt et pénètre dans l’arbre Mojo.",
                "Celui-ci demande à Navi d’aller réveiller Link.",
                "Plus tard, avec l’aide de Mido, Link arrive à vaincre Ghoma.",
                "L’arbre Mojo lui remet l’émeraude Kokiri",
                "Et lui demande de la donner à la princesse Zelda.",
                "Cette dernière lui demande de partir en quête du rubis Goron et du saphir Zora",
                "ces pierres doivent être amenées au temple du Temps et doivent être réveillées",
                "par le chant de l’ocarina du Temps pour accéder à... ",
                "Excalibur, l’épée suprême pouvant repousser les forces des ténèbres."
        };
        for (int i=0; i<tweets.length; i++) {
            twitterService.postTweet(tweets[i]);
            Thread.sleep(2400000); // 40 min
        }
    }
}
