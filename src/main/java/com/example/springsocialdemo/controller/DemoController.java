package com.example.springsocialdemo.controller;

import com.example.springsocialdemo.service.TwitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    private TwitterService twitterService;

    private static Logger LOOGER = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/postTweet")
    public void postTweet() throws InterruptedException {
        final String[] tweets = {
                 "The path of the righteous man is beset on all sides by the inequities of the selfish and the tyranny of evil men.",
                 "Blessed is he who, in the name of charity and good will, shepherds the weak through the valley of the darkness.",
                 "For he is truly his brother's keeper and the finder of lost children.",
                 "And I will strike down upon thee with great vengeance and furious anger",
                 "those who attempt to poison and destroy my brothers.",
                 "And you will know my name is the Lord when I lay my vengeance upon you."
        };

        for (int i = 0; i < tweets.length; i++) {
            LOOGER.info("Tweet posted : " + twitterService.postTweet(tweets[i]));
            Thread.sleep(1200000); // 40 min
        }
    }

    @GetMapping("/parseTweets")
    public String parseTweets(@RequestParam String screen_name) {
        return twitterService.parseTweets(screen_name);
    }
}
