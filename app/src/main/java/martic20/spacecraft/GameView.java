package martic20.spacecraft;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {
    //Variable booleana que nos indica si estamos jugando o no
    volatile boolean playing;

    //Hilo del juego
    private Thread gameThread = null;
    //Referenciamos/añadimos el jugador
    private Player player;
    //Objetos para dibujar la pantalla
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;



    //Creamos un array de enemigos
    private Enemy[] enemies;
    private Life[] lifes;

    //Añadimos 3 enemigos.
    private int enemyCount = 3;
    private int lifeCount =1;


    //Creamos array de estrellas para darle más vistosidad
    private ArrayList<Star> stars = new
            ArrayList<Star>();


    //Objeto bomba, en caso de colisión
    private Boom boom;

    public GameView(Context context, int x, int y) {

        super(context);

        //Inicializamos objeto jugador.
        //Pasamos al constructor de player, las medidas de la pantalla.
        player = new Player(context, x, y);

        //Inicializamos pantalla (objetos gráficos)
        surfaceHolder = getHolder();
        paint = new Paint();

        //Por ejemplo, 100 estrellas.
        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            Star s  = new Star(x, y);
            stars.add(s);
        }


        //Rellenamos el array de enemigos
        enemies = new Enemy[enemyCount];
        for(int i=0; i<enemyCount; i++){
            enemies[i] = new Enemy(context, x, y);
        }

        lifes = new Life[lifeCount];
        for(int i=0; i<lifeCount; i++){
            lifes[i] = new Life(context, x, y);
        }

        //Creamos el objeto explosión.
        boom = new Boom(context);


    }

    @Override
    public void run() {
        while (playing) {
            //Para actualizar el juego
            update();

            //Para dibujar el juego
            draw();

            //Para controlar el juego
            control();
        }
    }

    private void update() {
        //Actualiza posición del jugador
        player.update();

        //Explosión no visible (fuera de pantalla)
        boom.setX(-250);
        boom.setY(-250);
        //Actualizamos las estrellas, a la velocidad del jugador
        for (Star s : stars) {
            s.update(player.getSpeed());
        }

        //Actualizamos la velocidad de los enemigos a la del jugador
        for(int i=0; i<enemyCount; i++){
            enemies[i].update(player.getSpeed());


            //Si ocurre colisión
            if (Rect.intersects(player.getDetectCollision(), enemies[i].getDetectCollision())) {
                //Movemos objeto explosión a la posición del jugador
                boom.setX(enemies[i].getX());
                boom.setY(enemies[i].getY());


                //desplazamos al enemigo fuera de pantalla
                enemies[i].setX(-200);
            }

        }

        for(int i=0; i<lifeCount; i++){
            lifes[i].update(player.getSpeed());
        }

    }

    private void draw() {
        //Revisamos si la pantalla es válida
        if (surfaceHolder.getSurface().isValid()) {
            //Bloqueamos el canvas
            canvas = surfaceHolder.lockCanvas();
            //Le damos un color al canvas
            canvas.drawColor(Color.BLACK);

            //color para las estrellas
            paint.setColor(Color.WHITE);

            //dibujando las estrellas
            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }

            //Dibujando el jugador
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);

            //Dibujando enemigos
            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }

            for (int i = 0; i < lifeCount; i++) {
                canvas.drawBitmap(
                        lifes[i].getBitmap(),
                        lifes[i].getX(),
                        lifes[i].getY(),
                        paint
                );
            }

            //Dibujando objeto bomba
            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint
            );

            //Desbloqueo del canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        //Si el juego se pausa, ponemos la variable playing a false
        playing = false;
        try {
            //paramos el hilo del juego
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        //OnResume, empieza el juego
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                //el usuario toca la pantalla
                //Paramos el boosting (el objeto subirá)
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                //Cuando el usuario suelte la pantalla
                //el player bajará
                player.setBoosting();
                break;
        }
        return true;
    }


}
