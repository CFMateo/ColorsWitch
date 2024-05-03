package sample;

/**
 * Obstacle simple : un carré qui change de couleur à toutes les 2 secondes.
 */
public class Circle extends Obstacle {

    //private double width;
    private double radius;
    private double timeSinceColorChange = 0;
    

    public Circle(double x, double y, double radius) {
        super(x, y);
        
        this.radius = radius;
        this.renderer = new CircleRenderer(this);

        this.color = (int) (Math.random() * 4);
    }
    
    
    public double getRadius() {
        return radius;
    }
    public void setRadius(double r){
        this.radius = r;
    }
    @Override
    public double getWidth() {
        return this.radius * 2; //Diametre
    }

    @Override
    public double getHeight() {
        return this.radius * 2; // diametre
    }

    public int getColor() {
        return color;
    }
    @Override
    public void tick(double dt) {
        timeSinceColorChange += dt;

        if (timeSinceColorChange > 2) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
    }
    
    

    @Override
    public boolean intersects(Player player) {//Ici on considere l'intersection des carres circonscrits aux cerles, c'est une simplification du probleme
            //System.out.println("PlayerX : " + player.getX() + "PlayerY : " + player.getY());
            //System.out.println("CarreX  : " + this.getX() + "  CarreY  : " + this.getY());
            return (this.color != player.getColor() // collision player en dessous, Item au dessus 
                    && player.getX() < this.getX() + this.getWidth() / 2
                    && player.getX() > this.getX() - this.getWidth() / 2
                    && (player.getY() + player.getRadius() ) < this.getY() + this.getHeight() / 2
                    && (player.getY() + player.getRadius()) > this.getY() - this.getHeight() / 2)
                    || // collision player au dessus, Item en dessus
                    (this.color != player.getColor()
                    && player.getX() < this.getX() + this.getWidth() / 2
                    && player.getX() > this.getX() - this.getWidth() / 2
                    && (player.getY() - player.getRadius() ) < this.getY() + this.getHeight() / 2
                    && (player.getY() - player.getRadius()) > this.getY() - this.getHeight() / 2)
                    || // collision player à gauche, Item à droite
                    (this.color != player.getColor()
                    && (player.getX() + player.getRadius()) < this.getX() + this.getWidth() / 2
                    && (player.getX() + player.getRadius()) > this.getX() - this.getWidth() / 2
                    && (player.getY() ) < this.getY() + this.getHeight() / 2
                    && (player.getY() ) > this.getY() - this.getHeight() / 2)
                    || // collision player à droite, Item à gauche
                    (this.color != player.getColor()
                    && (player.getX() - player.getRadius()) < this.getX() + this.getWidth() / 2
                    && (player.getX() - player.getRadius()) > this.getX() - this.getWidth() / 2
                    && (player.getY() ) < this.getY() + this.getHeight() / 2
                    && (player.getY() ) > this.getY() - this.getHeight() / 2); //2024-04-26
    }
}

