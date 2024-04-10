package sample;

public class Level1 extends Level {

    public Level1(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Square obstacle1 = new Square(x, 0.75 * screenHeight, 40);
        Square obstacle2 = new Square(x, 1.2 * screenHeight, 60);
        Square obstacle3 = new Square(x, 1.75 * screenHeight, 60);
        Square obstacle4 = new Square(x, 3 * screenHeight, 200);
        VerticalBar obstacle5 = new VerticalBar(x, 3.6 * screenHeight, 100, 40, 2.6 * screenWidth);
        GrowingCircle obstacle6 = new GrowingCircle(x,2.3*screenHeight, 40 );
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);

        // Création des items
        Potion potion1 = new Potion(x, 1.5 * screenHeight);
        Potion potion2 = new Potion(x, 2.6 * screenHeight);

        items.add(potion1);
        items.add(potion2);
        // changer la position du champignon dans Level1
        victoryMushroom = new Mushroom(screenWidth / 2, 3.5 * screenHeight);
        victoryMushroom = new Mushroom(screenWidth /2, 4 * screenHeight);
        victoryMushroom = new Mushroom(screenWidth /2, 5* screenHeight);
    }
}
