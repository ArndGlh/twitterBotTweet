package com.example.springsocialdemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
public class TwitterService {

    @Value("${twitter.consumer.key}")
    String consumerKey;
    @Value("${twitter.consumer.secret}")
    String consumerSecret;

    @Value("${twitter.access.token}")
    String accessToken;

    @Value("${twitter.access.token.secret}")
    String accessTokenSecret;

    public String postTweet(String status) {
        Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        return twitter.timelineOperations().updateStatus(status).getIdStr();
    }
}


