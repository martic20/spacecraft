package martic20.spacecraft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Botón jugar
    private ImageButton buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Referenciamos botón
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

    }

    //Al clickar sobre el imageSprite
    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, GameActivity.class));
    }
}
