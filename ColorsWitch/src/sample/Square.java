package sample;

/**
 * Obstacle simple : un carré qui change de couleur à toutes les 2 secondes.
 */
public class Square extends Obstacle {

    private double width;
    private double timeSinceColorChange = 0;

    public Square(double x, double y, double longueur) {
        super(x, y);

        this.width = longueur;
        this.renderer = new SquareRenderer(this);

        this.color = (int) (Math.random() * 4);
    }

    @Override
    public double getWidth() {
        return this.width; //getWidth(); //2024-04-25
    }

    @Override
    public double getHeight() {
        return this.width; // return getHeight(); //2024-04-25
    }

    @Override
    public void tick(double dt) {
        timeSinceColorChange += dt;

        if (timeSinceColorChange > 3) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean intersects(Player player) {
            //System.out.println("PlayerX : " + player.getX() + "PlayerY : " + player.getY());
            //System.out.println("CarreX  : " + this.getX() + "  CarreY  : " + this.getY());
            return  (!player.getShield() &&
                     (
                    (this.color != player.getColor() // collision player en dessous, Item au dessus 
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
                    && (player.getY() ) > this.getY() - this.getHeight() / 2)
                    )); //2024-04-26
    }
}
