package sample;

/**
 * Obstacle simple : un carré qui change de couleur à toutes les 2 secondes.
 */
public class VerticalBar extends Square {

    private double timeSinceColorChange = 0;
    private boolean deGaucheADroite = true;
    private double largeurEcranJeu; // utilise pour gerer les rebons

    public VerticalBar(double x, double y, double longueur, double l) {
        super(x, y,longueur);
        this.largeurEcranJeu = l;
        this.renderer = new VerticalBarRenderer(this);

    }

    //@Override
    //public double getWidth() {
    //    return this.width; //getWidth(); //2024-04-25
    //}

    @Override
    public double getHeight() {
        return this.getWidth() * 4; // return getHeight(); //2024-04-25
    }

    @Override
    public void tick(double dt) {
        
        //Deplacement horizontal avec gestion des rebons sur les bords verticaux de l'ecran
        
            if (deGaucheADroite) this.setX(this.getX() + 1);
            else this.setX(this.getX() - 1);
            if (deGaucheADroite && this.getX() >= largeurEcranJeu) deGaucheADroite = false;
            if (!deGaucheADroite && this.getX() <= 0) deGaucheADroite = true;
        
    }

    public int getColor() {
        return color;
    }

    
}
