	package sample;

/**
 * Obstacle simple : un carré qui change de couleur à toutes les 2 secondes.
 */
public class GrowingCircle extends Circle {

    //private double width;
    
    private double largeurEcranJeu; // utilise pour gerer les rebons
    private boolean agrandir = true;
    private double timeSinceColorChange = 0;

    public GrowingCircle(double x, double y, double radius, double screenWidth) {
        super(x, y,radius);
        this.largeurEcranJeu = screenWidth;

        //this.renderer = new GrowingCircleRenderer(this);

        this.color = (int) (Math.random() * 4);
    }
    
    @Override
    public void tick(double dt) {
        timeSinceColorChange += dt;

        if (timeSinceColorChange > 2) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
        
        // Ajustement de la vitesse d'agrandissement
        double vitesseAgrandissement = 32; // Modifier cette valeur selon la vitesse souhaitée
        if (agrandir) {
            this.setRadius(this.getRadius() + vitesseAgrandissement * dt);
            if (this.getRadius() >= (largeurEcranJeu / 2)) agrandir = false;
        } else {
            this.setRadius(this.getRadius() - vitesseAgrandissement * dt);
            if (this.getRadius() <= 0) agrandir = true;
        }
    }
}