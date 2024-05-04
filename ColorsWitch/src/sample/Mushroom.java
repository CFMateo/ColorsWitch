package sample;

/**
 * Item : champignon.
 * 
 * Ramasser un champignon permet de gagner le niveau actuel
 */
public class Mushroom extends Item {
    private double deltaTime = 0;
    
    public Mushroom(double x, double y) {
        super(x, y);

        //this.renderer = new AnimationRenderer("mushroom_animation", 26, 20, this);//2024-04-25
        this.renderer = new AnimationRenderer("mushroom",this);
    }
    public double getDeltaTime(){
        return deltaTime;
    }
    @Override
    public void tick(double dt) {
      deltaTime += dt;
    }

    @Override
    public double getWidth() {
        return 64;
    }

    @Override
    public double getHeight() {
        return 64;
    }

    @Override
    public void handleCollision(Player player, Game game) {
        game.win();
    }

    @Override
    public boolean intersects(Player player) {
        return player.getX() < this.getX() + this.getWidth() / 2
                && player.getX() > this.getX() - this.getWidth() / 2
                && player.getY() < this.getY() + this.getHeight() / 2
                && player.getY() > this.getY() - this.getHeight() / 2;
    }
}
