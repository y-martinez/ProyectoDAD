package com.ejemplo.proyect_uno;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.content.Intent;
import android.view.View;


public class main extends ActionBarActivity implements View.OnClickListener{
    private Button iniciar_partida;
    private Button unirse_partida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        iniciar_partida = (Button)findViewById(R.id.btn_inicia_partida);
        unirse_partida = (Button)findViewById(R.id.btn_unirse_partida);

        iniciar_partida.setOnClickListener(this);
        unirse_partida.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v){
        Intent siguienteInterfaz = new Intent();
        if ( v == this.iniciar_partida)
            siguienteInterfaz.setClass(this,crear_partida.class);
        else
            siguienteInterfaz.setClass(this,unirse_partida.class);

        startActivity(siguienteInterfaz);
    }
}
