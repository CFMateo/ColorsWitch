 package sample;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Game {

    private Level level;
    private Player player;
    private boolean test = false;
    private double screenWidth, screenHeight;
    private boolean gameOver = false;
    private boolean hasWon = false;
    private String message = ""; // Ajout d'un attribut pour stocker le message à afficher
    private boolean displayMessage = false; // Booléen pour indiquer si le message doit être affiché
    private long displayMessageStartTime; // Temps de début d'affichage du message
    private long displayMessageDuration = 3000; // Durée d'affichage du message en millisecondes (3 secondes)


    /**
     * Crée une nouvelle instance de Game avec le niveau spécifié.
     * @param screenWidth La largeur de l'écran.
     * @param screenHeight La hauteur de l'écran.
     * @param levelNumber Le numéro du niveau.
     */
    public Game(double screenWidth, double screenHeight, int levelNumber) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        player = new Player(screenWidth / 2, 200, 15);
        switch (levelNumber) {
            case 1:
                level = new Level1(screenWidth, screenHeight);
                break;
            case 2:
                level = new Level2(screenWidth, screenHeight);
                break;
            case 3:
                level = new Level3(screenWidth, screenHeight);
                break;
            case 4:
                level = new Level4(screenWidth, screenHeight);
                break;
            default:
                throw new IllegalArgumentException("Niveau inconnu");
        }
    }

    /**
     * Active ou désactive le mode test.
     * @param test true pour activer le mode test, false pour le désactiver.
     */
    public void setTest(boolean test) {
        this.test = test;
    }

    /**
     * Met à jour l'état du jeu à chaque frame.
     * @param dt Delta-temps (en secondes).
     * @param pressTab Indique si la touche TAB est enfoncée.
     */
    public void tick(double dt, boolean pressTab) {
        level.tick(dt); 

        // Mettre à jour la position du joueur
        player.tick(dt);

        // Gérer le scrolling vertical du niveau par rapport au joueur
        if (player.getY() - player.getRadius() < level.getScroll()) {
            player.setY(level.getScroll() + player.getRadius());
        } else if (player.getY() - level.getScroll() > screenHeight / 2) {
            level.incrementScroll(player.getY() - level.getScroll() - screenHeight / 2);
        }

        // Mettre à jour la position des obstacles et gérer les collisions avec le joueur
        for (LevelElement element : level.getEntities()) {
            element.tick(dt);
            if (element.intersects(player)) {
                // Vérifier si pressTab est vrai pour éviter de perdre lors d'une collision
                if (!pressTab) {
                    element.handleCollision(player, this);
                }
            }
        }

        // Si le joueur perd
        if (gameOver && !hasWon) {
            message = "Vous avez perdu !"; // Définir le message de perte
            setDisplayMessage(true); // Définir displayMessage sur true pour afficher le message
        }

        // Si le joueur gagne
        if (hasWon) {
            message = "Vous avez gagné !"; // Définir le message de gain
            setDisplayMessage(true); // Définir displayMessage sur true pour afficher le message
        }
    }

    
    // Méthode pour activer l'affichage du message et enregistrer le temps de début
    public void setDisplayMessage(boolean displayMessage) {
        this.displayMessage = displayMessage;
        if (displayMessage) {
            displayMessageStartTime = System.currentTimeMillis();
        }
    }
    
    // Méthode pour vérifier si le message doit être affiché en fonction du temps écoulé
    public boolean shouldDisplayMessage() {
        if (displayMessage) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - displayMessageStartTime;
            return elapsedTime < displayMessageDuration;
        }
        return false;
    }
    
    // Méthode pour dessiner le message sur le canvas
    public void drawMessage(GraphicsContext context) {
        context.setFill(Color.WHITE);
        context.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        context.fillText(message, 100, 100); // Position du texte
    }

    /**
     * Renvoie la liste des entités à afficher à l'écran.
     * @return La liste des entités.
     */
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>();
        entities.addAll(level.getEntities());
        entities.add(player);
        return entities;
    }

    /**
     * Renvoie le niveau actuel du jeu.
     * @return Le niveau actuel.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Déclenche le saut du joueur.
     */
    public void jump() {
        player.jump();
    }

    /**
     * Affiche le message de défaite.
     */
    public void loose() {
        System.out.println("You loose... Too bad !");
        this.gameOver = true;
    }

    /**
     * Affiche le message de victoire.
     */
    public void win() {
        System.out.println("You win !");
        this.hasWon = true;
        this.gameOver = true;
    }

    /**
     * Indique si la partie est gagnée.
     * @return true si la partie est gagnée, sinon false.
     */
    public boolean hasWon() {
        return hasWon;
    }

    /**
     * Indique si la partie est terminée.
     * @return true si la partie est terminée, sinon false.
     */
    public boolean isGameOver() {
        return gameOver;
    }
    
}