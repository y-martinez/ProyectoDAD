package com.ejemplo.proyect_uno;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class crear_partida extends Activity implements View.OnClickListener{

    private Button btn_iniciar_partida;
    private Button btn_iniciar_busqueda;
    private String Mensaje;
    private String Repartidor;
    private ServerSocket serverSocket = null;
    private ArrayList<Jugador> Jugadores;
    private ListView listaVista;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_partida);

        Repartidor = ((TextView)findViewById(R.id.nameRepartidor)).getText().toString();
        btn_iniciar_partida = (Button)findViewById(R.id.btn_iniciar_partida);
        btn_iniciar_busqueda = (Button)findViewById(R.id.btn_iniciar_busqueda);
        listaVista = (ListView)findViewById(R.id.listaJugadoresVista);
        Jugadores = new ArrayList<Jugador>();

        Jugadores.add(new Jugador(Repartidor,0,true,getIpAddr()));
        actualizarLista();

        btn_iniciar_partida.setOnClickListener(this);
        btn_iniciar_busqueda.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crear_partida, menu);
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

    public String getIpAddr() {
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();

        String ipString = String.format(
                "%d.%d.%d.%d",
                (ip & 0xff),
                (ip >> 8 & 0xff),
                (ip >> 16 & 0xff),
                (ip >> 24 & 0xff));

        return ipString;
    }

    public void actualizarLista(){

        listaVista.setAdapter(new Lista_adaptador(this, R.layout.jugador, Jugadores){
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView NombreJugador = (TextView) view.findViewById(R.id.NombreJugador);
                NombreJugador.setText(((Jugador) entrada).getNombre());

                TextView IP_Jugador = (TextView) view.findViewById(R.id.IP_Jugador);
                IP_Jugador.setText(((Jugador) entrada).getIP());
            }
        });
    }

    public boolean unicoJugador(String IP_Client){
        for(int i = 0 ; i < Jugadores.size() ; ++i){
            if(Jugadores.get(i).IpAddres.compareTo(IP_Client) == 0)
                return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onClick(View v) {

        if(v == btn_iniciar_busqueda){
            Thread socketServerThread = new Thread(new SocketServerThread());
            socketServerThread.start();
        }else{
            if(Jugadores.size()>=2){
                Intent siguienteInterfaz =  new Intent();
            //siguienteInterfaz.setClass(this,tablero.class);
    //        startActivity(siguienteIn

            onDestroy();
            }else{
                
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private class SocketServerThread extends Thread {

        static final int SocketServerPORT = 5000;
        int id_jugador = 0;
        Socket cliente = null;

        @Override
        @SuppressWarnings("deprecation")
        public void run() {
            try {
                serverSocket = new ServerSocket(SocketServerPORT);
                crear_partida.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                          System.out.println("Esperando clientes...");
                    }
                });

                while (id_jugador < 7) {
                    Socket cliente = serverSocket.accept();

                    DataInputStream sEntrada = new DataInputStream( cliente.getInputStream() );
                    PrintStream sSalida = new PrintStream( cliente.getOutputStream() );

                    Mensaje = sEntrada.readLine();
                    String ipAddrClient = cliente.getInetAddress().toString().replaceFirst("/","");

                    if(!unicoJugador(ipAddrClient))
                        continue;

                    id_jugador++;
                    Jugador usuario = new Jugador(Mensaje,id_jugador,false,ipAddrClient);
                    Jugadores.add(usuario);

                    crear_partida.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            System.out.println("Informacion del Cliente\n" + Mensaje);
                            actualizarLista();
                        }
                    });

                    SocketServerReplyThread socketServerReplyThread = new SocketServerReplyThread(cliente, id_jugador);
                    socketServerReplyThread.run();

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    private class SocketServerReplyThread extends Thread {

        private Socket hostThreadSocket;
        int cnt;

        SocketServerReplyThread(Socket socket, int c) {
            hostThreadSocket = socket;
            cnt = c;
        }

        @Override
        public void run() {
            OutputStream outputStream;
            String msgReply = "Hola android tu eres el jugador #" + cnt;

            try {
                outputStream = hostThreadSocket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                printStream.print(msgReply);
                printStream.close();

                Mensaje += "envia: " + msgReply + "\n";

                crear_partida.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println("Mensaje del cliente 1\n" + Mensaje);
                    }
                });

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Ocurrio un error " + e.toString() + "\n");
            }

            crear_partida.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Mensaje del cliente 2\n" + Mensaje);
                }
            });
        }

    }


}
