1) Créer une app sur https://apps.twitter.com
    Callback url vide
    Modifier les permission en Read, write, and directMessage si besoin
    Générer les Tokens d'accès

2) Modifier src/main/resources/applications.properties
    Y ajouter les informations de l'application Twitter créée plus tôt (app.secret, app.id, etc)

3) Si vous travaillez avec un proxy pour pouvez modifier les informations dans la classe Application.
    Pour la configuration de run, ajoutez également l'option de VM suivante afin que le proxy soit pris en compte
    (a compter de Java 1.8) : -Djdk.http.auth.tunneling.disabledSchemes=""

4) Lancer la classe Application, et allez sur localhost:8080/postTweet pour lancer les tweets.

Optionnal :
5) Mettre en place une application Heroku pour appeller TwitterService.recupTweet() toute les heures (afin de parser
    les tweets et les retweeter s'ils contiennent les mots concours && retweet) Notes : on peut aussi utiliser
    Kaffeine (https://kaffeine.herokuapp.com/#!) pour pinger l'application Heroku pour qu'elle ne se mette pas en veille.