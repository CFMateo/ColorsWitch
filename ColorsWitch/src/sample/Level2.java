package sample;

public class Level2 extends Level {

    public Level2(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Square obstacle1 = new Square(screenWidth / 2, screenHeight * 2, screenHeight * 1.9);
        Square obstacle2 = new Square(x, 2.25 * screenHeight, 30);
        RotatingCircle obstacle3 = new RotatingCircle(x, 3.2 * screenHeight, 15, 4.8, 0.06);
        RotatingCircle obstacle4 = new RotatingCircle(x, 3.4 * screenHeight, 15, 3.8, 0.06);
        VerticalBar obstacle5 = new VerticalBar(x, 1.75 * screenHeight, 30, 80, 2.6 * screenWidth);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);

        // Création des items
        Potion potion1 = new Potion(x, 2.75 * screenHeight);
        Potion potion2 = new Potion(x, 2.6 * screenHeight);

        items.add(potion1);
        items.add(potion2);
        // changer la position du champignon dans Level2

        victoryMushroom = new Mushroom(screenWidth / 2, 100 * screenHeight);
    }
}
