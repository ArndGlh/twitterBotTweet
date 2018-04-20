package com.example.springsocialdemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TwitterService {

    @Value("${twitter.consumer.key}")
    private String consumerKey;
    @Value("${twitter.consumer.secret}")
    private String consumerSecret;

    @Value("${twitter.access.token}")
    private String accessToken;

    @Value("${twitter.access.token.secret}")
    private String accessTokenSecret;

    public String postTweet(String status) {
        Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        return twitter.timelineOperations().updateStatus(status).getIdStr();
    }

    /**
     * Récupère les 20 derniers tweets de {@param screenName} puis teste si les tweets
     * de la dernière heure contienne les mots concours et retweet, si oui retweete le message.
     * @param screenName
     * @return l'identifiant du retweet tweeté, ainsi qu'un tableau contenant les 20 derniers tweets.
     */
    public String recupTweet(String screenName){
        Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        StringBuilder str = new StringBuilder();
        List<Tweet> timeline = twitter.timelineOperations().getUserTimeline(screenName);
        for (Tweet t : timeline) {
            if (!t.getCreatedAt().before(new Date(System.currentTimeMillis() - 3600000))
                    && !t.isRetweet()
                    && (t.getText().contains("concours") || t.getText().contains("Concours"))
                    && (t.getText().contains("RT") || t.getText().contains("RETWEET"))) {
                str.append("Tweet => " + twitter.timelineOperations().retweet(t.getId()));
            }
        }

        str.append("\n\n<table><thead><tr><th>Date</th><th>HasMedia</th><th>Text</th></tr></thead><tbody>");
        for (Tweet t : timeline) {
            str.append("<tr><td>");
            str.append(t.getCreatedAt());
            str.append("</td><td>");
            str.append(t.hasMedia());
            str.append("</td><td>");
            str.append(t.getText());
            str.append("</td><tr>");
        }
        str.append("</tbody></table>");
        return str.toString();
    }
}


