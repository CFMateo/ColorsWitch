package sample;

public class Level3 extends Level {

    public Level3(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        
        Square obstacle2 = new Square(x, 1.3 * screenHeight, 60);
        Square obstacle3 = new Square(x, 1.75 * screenHeight, 60);
        Square obstacle4 = new Square(x, 3 * screenHeight, 200);
        //VerticalBar obstacle5 = new VerticalBar(x, 3.6 * screenHeight, 100, 40, 2.6 * screenWidth); //2024-04-25
        //GrowingCircle obstacle6 = new GrowingCircle(x,2.3*screenHeight, 40 ); //2024-04-25
      
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        //obstacles.add(obstacle5); //2024-04-25
        //obstacles.add(obstacle6); //2024-04-25

        //Vertical Bars
        
        VerticalBar vertical1 = new VerticalBar(x, 2.2 * screenHeight, 20,screenWidth); // 20 est la largeur, la longeur est par defaut largeur * 4
        VerticalBar vertical2 = new VerticalBar(x, 2 * screenHeight, 30,screenWidth);
        VerticalBar vertical3 = new VerticalBar(x, 2.6* screenHeight, 30,screenWidth);

        obstacles.add(vertical1);
        obstacles.add(vertical2);
        obstacles.add(vertical3);

        //Growing circles
        
        GrowingCircle growingC1 = new GrowingCircle(x, 0.75 * screenHeight, 20,screenWidth); // 20 est la largeur, la longeur est par defaut largeur * 4
        //GrowingCircle growingC2 = new GrowingCircle(x, 2.2 * screenHeight, 40,screenWidth);

        obstacles.add(growingC1);
        //obstacles.add(growingC2);

        //Rotating circles
        
        //RotatingCircle rotatingC1 = new RotatingCircle(x, 1 * screenHeight, 20,40,screenHeight,screenWidth); // 20 est la largeur, la longeur est par defaut largeur * 4
        //RotatingCircle rotatingC2 = new RotatingCircle(x, 4.2 * screenHeight, 40,80,screenHeight,screenWidth);

        //obstacles.add(rotatingC1);
        //obstacles.add(rotatingC2);

        // Création des items
        
        //Potion potion1 = new Potion(x, 1.5 * screenHeight); //2024-04-26
        //Potion potion2 = new Potion(x, 2.6 * screenHeight); //2024-04-26

        Potion potion1 = new Potion(screenWidth / 2, 1.5 * screenHeight);
        Potion potion2 = new Potion(screenWidth / 2, 2.6 * screenHeight);

        items.add(potion1);
        items.add(potion2);
        //Shield 

        Shield shield1 = new Shield(screenWidth / 2, 2 * screenHeight);
        
        items.add(shield1);
        
        
        // changer la position du champignon dans Level1
        //victoryMushroom = new Mushroom(screenWidth / 2, 3.5 * screenHeight);
        victoryMushroom = new Mushroom(screenWidth /2, 3.5 * screenHeight);
        //victoryMushroom = new Mushroom(screenWidth /2, 5* screenHeight);
    }
}

