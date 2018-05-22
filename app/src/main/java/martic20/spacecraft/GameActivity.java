package martic20.spacecraft;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class GameActivity extends AppCompatActivity {

    //Declarem GameView, es la view que será la encargada de dibujar los sprites
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Getting - objeto Display (Área de visualización)
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();

        //Obtenemos la resolución de la pantalla
        Point size = new Point();
        display.getSize(size);

        //Inicializamos gameView
        //Le pasamos al constructor el tamaño de pantalla
        gameView = new GameView(this, size.x, size.y);

        //Establecemos el contenido de la actividad en la vista gameView
        setContentView(gameView);
    }


    //Pausa el juego
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    //OnResume
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
