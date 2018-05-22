package martic20.spacecraft;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by Ximo  on 10/05/2018.
 */

public class Player {
    //Bitmap con la imágen del jugador
    private Bitmap bitmap;

    //Variables para las coordenadas.
    private int x;
    private int y;

    //Velocidad para el jugador
    private int speed = 0;
    //Variable que indicará si el jugador ha de moverse o no
    private boolean boosting;

    //Variable para simular efecto gravedad
    private final int GRAVITY = -10;

    //Variables para evitar que el jugador se salga de la pantalla
    private int maxY;
    private int minY;

    //limitamos velocidades del jugador
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;
    private Rect detectCollision;

    //constructor
    public Player(Context context, int x, int y) {
        this.x = 75;
        this.y = 50;
        speed = 1;

        //Bitmap para el recurso drawable
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        //Calculando maxY, para evitar que salga de la pantalla
        maxY = y - bitmap.getHeight();

        //La parte superior siempre será 0
        minY = 0;

        //Inicialmente no juega
        boosting = false;


        //Inicializamos objeto rect para detectar colisión
        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    //Método para actualizar coordenadas
    public void update() {
        //Si el juego esta en marcha
        if (boosting) {
            //Aumenta la velocidad
            speed += 2;
        } else {
            //Disminuye la velocidad
            speed -= 5;
        }
        //Para controlar la velocidad máxima
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        //Efecto gravedad
        y -= speed + GRAVITY;

        //Sin que se salga de la pantalla
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }

        //Coordenadas para el objeto rec
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();


    }

    //establecemos boost a true
    public void setBoosting() {
        boosting = true;
    }

    //establecemos boost a false
    public void stopBoosting() {
        boosting = false;
    }


    public Rect getDetectCollision() {
        return detectCollision;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
