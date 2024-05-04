package sample;

import java.util.List;

/**
 * Contrôleur pour le jeu : fait le pont entre la vue et les modèles.
 */
public class Controller {

    private Game game;
    private int level = 2;
    private boolean pressTab = false; // Ajout de la variable pressTab

    public Controller() {
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, 4);
        
    }
    
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
    
    public List<Entity> getEntities() {
        return this.game.getEntities();
    }
    


    /**
     * Fonction appelée à chaque frame du jeu.
     * @param dt Delta-temps exprimé en secondes
     */
    public void tick(double dt) {
        if (this.game.isGameOver()) {
            if (this.game.hasWon()) {
                if (level < 4) { // Vérifie si le niveau actuel est inférieur au niveau maximal
                    level++; // Passe au niveau suivant
                    System.out.println(level);

                }
            }
            this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level); // Redémarre le niveau actuel
        } else {
            this.game.tick(dt,pressTab);
        }
    }


    public Level getCurrentLevel() {
        return this.game.getLevel();
    }

    /**
     * Fonction appelée lorsque la barre espace est enfoncée.
     */
    public void spaceTyped() {
        this.game.jump();
    }
    
    
 // Méthode pour activer ou désactiver pressTab
    public void togglePressTab() {
        pressTab = !pressTab;
    }

    // Méthode pour vérifier si pressTab est activé
    public boolean isPressTab() {
        return pressTab;
    }
    public void testMode(boolean test){
        this.game.setTest(test);
    }
}
