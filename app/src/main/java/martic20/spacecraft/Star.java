package martic20.spacecraft;

import java.util.Random;

/**
 * Created by Ximo on 17/05/2018.
 */
public class Star {
    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;



    public Star(int screenX, int screenY) {
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;
        Random generator = new Random();
        speed = generator.nextInt(10);

        //Generamos coordenada aleatoria para cada estrella
        //Siempre dentro de la pantalla
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }

    public void update(int playerSpeed) {
        //Efecto animación de la estrella
        //Disminuyendo la x, según la velocidad del jugador
        x -= playerSpeed;
        x -= speed;
        //Cuando la estrella alcanza borde
        if (x < 0) {
            //la enviamos a la derecha, dando efecto de desplazamiento infinito
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(15);
        }
    }

    public float getStarWidth() {
        //Establecemos ancho de la estrella de forma aleatoria, dando efecto de realismo
        float minX = 1.0f;
        float maxX = 4.0f;
        Random rand = new Random();
        float finalX = rand.nextFloat() * (maxX - minX) + minX;
        return finalX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}