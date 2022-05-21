# GHOSTLAB

## Compiler le projet

### Serveur

Lancer la commande `make` depuis le répertoire `src/main/c/server` 

### Client

Lancer le script `compile.sh` contenu dans le répertoire `src` depuis ce même répertoire

## Executer le projet

### Serveur

Exécuter le fichier `server` généré lors de la compilation avec en argument le port désiré.


*Exemple*

Pour lancer le serveur en écoute sur le port 12345:

```
./server 12345
```


### Client 

Exécuter le script `launch.sh` contenu dans le répertoire `src` depuis ce même répertoire.

OU

Exécuter la commande suivante: `java main.java.client.Client` depuis le répertoire `src`.

Les arguments suivants sont optionnels et peuvent par la suite être renseignés dans l'interface graphique:

- IP: L'IP du serveur auquel se connecter

- Port: Le port du serveur a utiliser

- Pseudo: Le pseudo a utiliser dans une partie. Ce pseudo doit faire exactement 8 caractères


*Exemple*

```
java main.java.client.Client ghostlab-server.com 12345 USERNAME
```
```
./launch.sh
```

## Comment utiliser le client 

Au lancement, si l'utilisateur n'a pas précisé d'informations de connexion, il est invité à entrer les informations du serveur.

Une fois connecté, le volet `Game Manager` devient accessible et permet de:

- créer une partie
- rejoindre une partie
- lister les parties existantes et les informations relatives à celles-ci
- quitter la partie dans laquelle se trouve le client
- démarrer la partie

Une fois la partie lancée, le volet `game` permet de se déplacer dans la labyrinthe en ayant un retour visuel de ce labyrinthe.

Si le joueur rencontre un mur lors de ses déplacements, celui-ci est automatiquement ajouté sur la carte du labyrinthe.

Lorsque les fantômes se déplacent, seul le dernier fantôme à s'être déplacé est affiché sur la carte.

## Architecture du serveur 


Le serveur attend en boucle des connexions et délègue le traitement du protocole à un thread.
Au cours des interactions client/serveur, plusieurs threads se succèdent dans le traitement des requêtes du client: chaque thread représentant une phase de la durée de vie d'une partie.
Les différentes phases sont les suivantes:

1. le client vient de se connecter
2. le client a créé ou rejoint une partie
3. la partie a été lancée
4. la partie est terminée 

Chaque partie possède:

- son propre verrou pour empêcher les modifications concurrentes sur la liste des joueurs.
- son propre socket UDP utilisé pour multi-caster et envoyer les messages privés

L'IP de multi-diffusion est unique au serveur, seul le port change pour chaque partie créée.

Les labyrinthes sont récupérés depuis des fichiers respectant les règles suivantes:

- Une première ligne contenant deux entiers `i` et `j,` `i  représentant la largeur et `j` la hauteur
- `j` lignes de chacune `i` colonnes composées de `1` ou de `0`, `1` représentant un mur et `0` un chemin
- Une ligne vide





