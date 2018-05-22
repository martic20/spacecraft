package martic20.spacecraft;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Ximo on 18/05/2018.
 */
public class Boom {

    //Objeto bitmap
    private Bitmap bitmap;

    //variables - coordenadas
    private int x;
    private int y;

    //constructor
    public Boom(Context context) {
        //Recogemos imágen explosión
        bitmap = BitmapFactory.decodeResource
                (context.getResources(), R.drawable.boom);

        //Ponemos coordenadas fuera de la pantalla, para que la colisión
        //"desaparezca"
        x = -250;
        y = -250;
    }

    //setters para hacer visible la explosión
    // en el lugar de la colisión
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //getters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
