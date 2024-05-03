package sample;

/**
 * Obstacle simple : un carré qui change de couleur à toutes les 2 secondes.
 */
public class RotatingCircle extends Circle {

    //private double width;
    
    private double rayonRotation; // utilise pour gerer les rebons
    private double timeSinceColorChange = 0;
    private double teta = Math.PI / 2; //angle de rotation de a 2 Pi
    private double screenHeight;
    private double screenWidth;

    public RotatingCircle(double x, double y, double radius, double rayonRotation, double screenHeight, double screenWidth) {
        super(x, y,radius);
        this.rayonRotation = rayonRotation;

        //this.renderer = new RotatingCircleRenderer(this);

        this.color = (int) (Math.random() * 4);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }
    
    @Override
    public void tick(double dt) {
        timeSinceColorChange += dt;

        if (timeSinceColorChange > 2) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
        
        // Ajustement de la vitesse de rotation
        double vitesseRotation = 1; // Modifier cette valeur selon la vitesse souhaitée
        teta += vitesseRotation * dt;

        if (teta > (Math.PI * 2)) teta = 0; // remise à zéro pour éviter un débordement
        this.setX((this.screenWidth / 2) + (Math.cos(teta) * rayonRotation));
        this.setY((this.screenHeight / 2) + (Math.sin(teta) * rayonRotation) + 75);
    }
}

