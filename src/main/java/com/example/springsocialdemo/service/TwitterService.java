package com.example.springsocialdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterService {

    @Autowired
    private Twitter twitter;

    /**
     * Post {@param status} to twitter
     * {@param status} has to be 140 character max
     * @param status
     * @return tweet id
     */
    public String postTweet(String status) {
        return twitter.timelineOperations().updateStatus(status).getIdStr();
    }

    /**
     * Get the last 20 tweets of {@param screenName} then test whether the tweets
     * contains the words "concours" and "retweet" (French), if yes retweet the tweet.
     * Also test the word "follow", if yes follow the user.
     * @param screenName
     * @return id of retweet, and a table of the 20 last tweets
     */
    public String parseTweets(String screenName) {
        StringBuilder str = new StringBuilder();
        List<Tweet> timeline = twitter.timelineOperations().getUserTimeline(screenName);
        for (Tweet t : timeline) {
            String textTweet = t.getText().toLowerCase();
            if ((textTweet.contains("concours")) && //TODO externalize words & traduction
                    (textTweet.contains("rt") || textTweet.contains("retweet"))) {
                try {
                    long id = twitter.timelineOperations().retweet(t.getId()).getId();
                    str.append("Retweet concours => " + id);
                }catch (OperationNotPermittedException e){ // If the tweet has already been retweeted
                    e.printStackTrace();
                }
            }
            if(textTweet.contains("follow")){
                twitter.friendOperations().follow(getScreenNameToFollow(t));
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

    /**
     * Parse the {@param tweet} looking for "follow" then get the word following, minus the first
     * character, supposedly a "@"
     * @param tweet
     * @return String screen_name to follow on twitter
     */
    private String getScreenNameToFollow(Tweet tweet){
        String[] tweetTab = tweet.getText().toLowerCase().split(" ");
        String result = "";
        boolean follow = false, boucle = true;
        int i = 0, length = tweetTab.length;
        while(i<length && boucle){
            if(follow) {
                result = tweetTab[i].substring(1);
                boucle = false;
            }
            if(tweetTab[i].equals("follow")) follow = true;
            i++;
        }
        return result;
    }
}


