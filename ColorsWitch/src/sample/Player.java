package sample;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Classe représentant l'entité de la personne qui joue (aka, la sorcière).
 * La sorcière est représentée par un cercle.
 */
public class Player extends Entity {

    private double radius;
    private double vy;
    private double ay;
    private int color = 1;
    private boolean isShield = false;
    private int decompteShield = 10; // Durée du bouclier en secondes
    private LocalDateTime startShieldTime;
    private LocalDateTime currentTime;
    private long secondsPrec = 0; // Stocke le nombre de secondes précédentes

    /**
     * Crée une nouvelle instance de Player avec les coordonnées spécifiées et le rayon spécifié.
     * @param x La coordonnée x de la position initiale.
     * @param y La coordonnée y de la position initiale.
     * @param r Le rayon du cercle représentant le joueur.
     */
    public Player(double x, double y, double r) {
        super(x, y);
        this.radius = r;
        this.vy = 0;
        this.ay = -400; // Accélération verticale de la gravité
        this.renderer = new PlayerRenderer(this);
    }

    /**
     * Renvoie le rayon du cercle représentant le joueur.
     * @return Le rayon du cercle.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Met à jour les attributs du joueur à chaque frame.
     * @param dt Delta-Temps en secondes.
     */
    @Override
    public void tick(double dt) {
        // Mise à jour de la vitesse
        vy += dt * ay;

        // Mise à jour de la position
        y += dt * vy;

        // Limiter la vitesse pour rester entre -300 et 300
        vy = Math.min(vy, 300);
        vy = Math.max(vy, -300);

        // Gestion du bouclier
        if (isShield) {
            currentTime = LocalDateTime.now();
            long seconds = ChronoUnit.SECONDS.between(startShieldTime, currentTime);
            if (seconds > secondsPrec) {
                decompteShield -= seconds;
                secondsPrec = seconds;
            }
        }
        if (decompteShield <= 0) {
            this.isShield = false;
        }
    }

    /**
     * Renvoie la couleur actuelle du joueur.
     * @return La couleur du joueur.
     */
    public int getColor() {
        return color;
    }

    /**
     * Active le bouclier du joueur.
     */
    public void activateShield() {
        this.isShield = true;
        this.decompteShield = 10; // Réinitialisation du compteur du bouclier
        startShieldTime = LocalDateTime.now();
    }

    /**
     * Renvoie l'état du bouclier du joueur.
     * @return true si le bouclier est actif, sinon false.
     */
    public boolean getShield() {
        return isShield;
    }

    /**
     * Fait sauter le joueur.
     */
    public void jump() {
        vy = Math.max(vy, 0);
        vy += 200; // Vitesse de saut
    }

    /**
     * Définit la coordonnée y du joueur.
     * @param y La nouvelle coordonnée y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Renvoie la largeur du joueur (le diamètre du cercle).
     * @return La largeur du joueur.
     */
    @Override
    public double getWidth() {
        return this.getRadius() * 2;
    }

    /**
     * Renvoie la hauteur du joueur (le diamètre du cercle).
     * @return La hauteur du joueur.
     */
    @Override
    public double getHeight() {
        return this.getRadius() * 2;
    }
}
