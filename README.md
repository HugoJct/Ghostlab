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
java main.java.client.Client USERNAME ghostlab-server.com 12345
```
```
./launch.sh
```