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
        //changement de taille du cercle : le minimum est un cerle de rayon <=0, le max est rayon >= largeur ecran/2
        
        if (agrandir) this.setRadius(this.getRadius() + 1);
        else this.setRadius(this.getRadius() -1);
        if (agrandir && this.getRadius() >= (largeurEcranJeu/2)) agrandir = false;
        if (!agrandir && this.getRadius() <= 0) agrandir = true;
    }

}
