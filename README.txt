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