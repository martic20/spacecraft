package martic20.spacecraft;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

    /**
     * Created by Ximo on 17/05/2018.
     */
    public class Enemy {

        //bitmap que usaremos para los "enemigos"
        private Bitmap bitmap;

        //coordenadas x e y
        private int x;
        private int y;

        //velocidad del enemigo
        private int speed = 1;

        //coordenadas máximas y mínimas para que el enemigo esté dentro de la pantalla
        private int maxX;
        private int minX;

        private int maxY;
        private int minY;

        //Creamos el detector de colisión
        private Rect detectCollision;

        public Enemy(Context context, int screenX, int screenY) {
            //Primero asignamos la imágen del enemigo
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

            //initializing min and max coordinates
            maxX = screenX;
            maxY = screenY;
            minX = 0;
            minY = 0;

            //Coordenadas aleatorias donde poner al enemigo
            Random generator = new Random();
            speed = generator.nextInt(6) + 10;
            x = screenX;
            y = generator.nextInt(maxY) - bitmap.getHeight();


            //Creamos/Inicializamos un objeto colisión
            detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

        }

        public void update(int playerSpeed) {
            //Restamos la x, para que el enemigo se desplace hacia la izquierda
            x -= playerSpeed;
            x -= speed;
            //Si a alcanzado el borde
            if (x < minX - bitmap.getWidth()) {
                //Generamos altura aleatoria, y lo movemos a la derecha
                Random generator = new Random();
                speed = generator.nextInt(10) + 10;
                x = maxX;
                y = generator.nextInt(maxY) - bitmap.getHeight();
            }

            //Asiganamos coordenadas para el objeto colisión
            detectCollision.left = x;
            detectCollision.top = y;
            detectCollision.right = x + bitmap.getWidth();
            detectCollision.bottom = y + bitmap.getHeight();
        }

        //Setter para la coordenada x, para cambiarla si hay colisión
        public void setX(int x){
            this.x = x;
        }

        //El getter para obtener el objeto colisión
        public Rect getDetectCollision() {
            return detectCollision;
        }

        //getters
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
