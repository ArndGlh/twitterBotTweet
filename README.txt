<html>
<ul>
<li>
1) Create an app on https://apps.twitter.com
    <p>
        Callback url empty
        Change permissions to Read, write, and directMessage if needed
        Générate access tokens
    </p>
</li>
2) Fill in src/main/resources/applications.properties
    with the informations you have on your twitter application that you created earlier

3) If you work behind a proxy
    You can change the proxy configuration in Application.class, with of without authentification,
    otherwise just comment it. For the run configuration you want to add a VM option which is :
    -Djdk.http.auth.tunneling.disabledSchemes="" it assure you since java 1.8 that your proxy is considered

4) Run Application.class, then go to localhost:8080/postTweet or parseTweets

Optionnal :
5) You can also deploy this on an Heroku-like app and call TwitterService.parseTweets() every couple hours for example
    You can also use Kaffeine (https://kaffeine.herokuapp.com/#!) to ping your Heroku app so it doesn't go to sleep.

