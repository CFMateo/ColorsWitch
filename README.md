
# ColorsWitch – Jeu JavaFX

## Présentation
ColorsWitch est une réimplémentation en JavaFX du célèbre jeu mobile "Color Switch". Ce projet repose sur une architecture orientée objet modulaire, 
avec séparation claire entre la logique métier, les entités du jeu, et leur rendu visuel.


## Objectif du jeu
Le joueur contrôle une balle colorée qui doit traverser différents obstacles en mouvement. 
Il ne peut traverser que les zones qui correspondent à la couleur actuelle de la balle. Des potions changent la couleur,
d'autres objets offrent des bonus (bouclier, score...). La partie est perdue si la balle touche une mauvaise couleur.


Exemple de gameplay : 
# 1: Niveau 2 Réussi, Passage au niveau 3
![gifV](https://github.com/user-attachments/assets/32f506e2-153d-4087-b855-602ea23f17f6)


# 2: Niveau 2 Perdu
![gifL](https://github.com/user-attachments/assets/9004532d-80f5-4ba0-b2bd-c148c5f2c711)



## Objectifs techniques

- Implémenter une architecture MVC (Modèle-Vue-Contrôleur) claire.
- Gérer dynamiquement des entités (joueur, obstacles, étoiles, etc.).
- Intégrer un moteur de jeu temps réel (boucle de jeu, rendu, détection de collision, gravité).
- Offrir une expérience fluide avec JavaFX (scènes, animations, transitions).
- Modulariser le code pour faciliter l'extension du jeu (nouveaux niveaux, nouveaux obstacles...).

---

## Structure du projet
ColorsWitch/
└── src/
    └── sample/
        ├── ColorsWitch.java          # Point d'entrée de l'application (Main)
        ├── Controller.java           # Gestion des entrées clavier et événements
        ├── Game.java                 # Cœur du moteur : boucle de jeu et collisions
        ├── Player.java               # Logique et état du joueur
        ├── Entity.java               # Classe abstraite de base pour tous les objets
        ├── Level.java                # Architecture générique d'un niveau
        ├── Level1~4.java             # Définitions spécifiques des niveaux
        ├── Obstacle.java             # Classe mère pour les éléments restrictifs
        ├── Item.java                 # Super-classe pour les collectables
        ├── Potion/Shield/Mushroom.java # Implémentations des bonus
        ├── Renderers/                # Logique d'affichage JavaFX :
        │   ├── CircleRenderer.java
        │   ├── ImageRenderer.java
        │   ├── PlayerRenderer.java
        │   └── AnimationRenderer.java # Gestion des cycles d'animation
        └── Menu.java                 # Interface utilisateur et navigation
---

## Fonctionnalités principales

- **Changement de couleur aléatoire** du joueur via ColorSwitcher.
- **Détection de collision** précise entre la balle et les obstacles en rotation.
- **Collecte d’étoiles** et comptage du score.
- **Défilement vertical automatique** de la scène avec le joueur au centre.
- **Gestion des états** : menu de départ, victoire, défaite, redémarrage.

---



### Prérequis

- Java SDK 17 ou plus récent (SDK 21+ recommandé)
- JavaFX SDK 21 : [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
- IDE recommandé : IntelliJ IDEA ou Eclipse avec JavaFX configuré

### Exécution

1. Ajouter JavaFX en tant que librairie externe (voir configuration ci-dessous).
2. Lancer `ColorsWitch.java` qui contient la méthode `main()`.

**VM options à ajouter** (si nécessaire) :

```bash
--module-path /chemin/vers/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml
















