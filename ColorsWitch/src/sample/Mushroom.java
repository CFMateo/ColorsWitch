package sample;

/**
 * Représente un item de type champignon.
 * 
 * Ramasser un champignon permet de gagner le niveau actuel.
 */
public class Mushroom extends Item {
    private double deltaTime = 0;
    
    /**
     * Crée un nouveau champignon à la position spécifiée.
     *
     * @param x la coordonnée x de la position du champignon
     * @param y la coordonnée y de la position du champignon
     */
    public Mushroom(double x, double y) {
        super(x, y);

        // Initialise le rendu de l'animation du champignon
        this.renderer = new AnimationRenderer("mushroom", this);
    }
    
    /**
     * Retourne le temps écoulé depuis l'apparition du champignon.
     *
     * @return le temps écoulé en secondes
     */
    public double getDeltaTime() {
        return deltaTime;
    }
    
    /**
     * Met à jour le champignon à chaque frame.
     *
     * @param dt le delta-temps en secondes
     */
    @Override
    public void tick(double dt) {
        deltaTime += dt;
    }

    /**
     * Retourne la largeur du champignon.
     *
     * @return la largeur du champignon en pixels
     */
    @Override
    public double getWidth() {
        return 64;
    }

    /**
     * Retourne la hauteur du champignon.
     *
     * @return la hauteur du champignon en pixels
     */
    @Override
    public double getHeight() {
        return 64;
    }

    /**
     * Gère la collision entre le joueur et le champignon.
     * 
     * Lorsque le joueur entre en collision avec le champignon, le niveau actuel est gagné.
     *
     * @param player le joueur entrant en collision avec le champignon
     * @param game le jeu en cours
     */
    @Override
    public void handleCollision(Player player, Game game) {
        game.win();
    }

    /**
     * Vérifie si le joueur entre en collision avec le champignon.
     *
     * @param player le joueur à vérifier
     * @return true si le joueur entre en collision avec le champignon, sinon false
     */
    @Override
    public boolean intersects(Player player) {
        return player.getX() < this.getX() + this.getWidth() / 2
                && player.getX() > this.getX() - this.getWidth() / 2
                && player.getY() < this.getY() + this.getHeight() / 2
                && player.getY() > this.getY() - this.getHeight() / 2;
    }
}
