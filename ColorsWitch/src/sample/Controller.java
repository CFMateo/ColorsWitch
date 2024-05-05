package sample;

import java.util.List;

public class Controller {

    private Game game;
    private int level = 2;
    private boolean pressTab = false;
    private boolean gameEnded = false; // Variable pour suivre si le jeu est terminé
    private long endTime = 0; // Variable pour stocker le temps écoulé depuis la fin du jeu

    /**
     * Constructeur de la classe Controller.
     * Initialise le jeu avec un niveau par défaut.
     */
    public Controller() {
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, 4);
    }
    
    /**
     * Sélectionne le niveau spécifié par l'utilisateur.
     * @param level Le nom du niveau sélectionné.
     */
    public void setLevelSelected(String level) {
        if (level.equals("Level 1")) {
            this.level = 1;
        } else if (level.equals("Level 2")) {
            this.level = 2;
        } else if (level.equals("Level 3")) {
            this.level = 3;
        } else if (level.equals("Level 4")) {
            this.level = 4;
        }
        // Met à jour le jeu avec le nouveau niveau sélectionné
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, this.level);
    }
    
    /**
     * Renvoie la liste des entités présentes dans le jeu.
     * @return La liste des entités.
     */
    public List<Entity> getEntities() {
        return this.game.getEntities();
    }

    /**
     * Effectue une itération du jeu.
     * Met à jour le jeu et vérifie s'il est terminé.
     * @param dt Le delta-temps exprimé en secondes.
     */
    public void tick(double dt) {
        if (gameEnded) {
            // Vérifier si le temps écoulé depuis la fin du jeu est supérieur à 3 secondes
            if (System.currentTimeMillis() - endTime > 1000) {
                if (game.hasWon()) {
                    if (level < 4) {
                        level++;
                    }
                }
                // Recréer le jeu après une attente
                game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
                gameEnded = false; // Réinitialiser la variable gameEnded
            }
        } else {
            game.tick(dt, pressTab);
            if (game.isGameOver() || game.hasWon()) {
                gameEnded = true; // Indiquer que le jeu est terminé
                endTime = System.currentTimeMillis(); // Enregistrer le temps actuel
            }
        }
    }
    


    /**
     * Renvoie le niveau actuel du jeu.
     * @return Le niveau actuel.
     */
    public Level getCurrentLevel() {
        return this.game.getLevel();
    }

    /**
     * Gère l'événement de pression de la touche espace.
     * Déclenche le saut du joueur dans le jeu.
     */
    public void spaceTyped() {
        this.game.jump();
    }
    
    /**
     * Active ou désactive le mode pressTab.
     * Le mode pressTab permet de basculer entre le mode normal et le mode test.
     */
    public void togglePressTab() {
        pressTab = !pressTab;
    }

    /**
     * Vérifie si le mode pressTab est activé.
     * @return true si le mode pressTab est activé, sinon false.
     */
    public boolean isPressTab() {
        return pressTab;
    }
    
    /**
     * Active ou désactive le mode test du jeu.
     * @param test true pour activer le mode test, false pour le désactiver.
     */
    public void testMode(boolean test) {
        this.game.setTest(test);
    }
    public Game getGame() {
        return game;
    }
}
