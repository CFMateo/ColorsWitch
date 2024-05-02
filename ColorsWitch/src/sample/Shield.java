package sample;

/**
 * Item : Shield.
 * 
 * Rends la sorcière invisible c-a-d etanche aux collusions
 */
public class Shield extends Item {

    private boolean used = false;
    protected int color;

    public Shield(double x, double y) {
        super(x, y);
        
        this.renderer = new ImageRenderer("shield",this);//2024-04-25
        this.color = (int) (Math.random() * 4); //2024-04-25
    }

    @Override
    public void tick(double dt) {
        // Rien à faire
    }

    @Override
    public double getWidth() {
        return 48;
    }

    @Override
    public double getHeight() {
        return 48;
    }

    @Override
    public void handleCollision(Player player, Game game) {
        
        if (!used){
        //this.renderer = new ImageRenderer("empty-potion", this); // 2024-04-25 // new ImageRenderer("empty-potion", this);
        //player.randomizeColor();
        player.activateShield();    
        used = true;
        
        }
    }

    @Override
    public boolean intersects(Player player) {
               return (!used // collision player en dessous, Item au dessus 
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

