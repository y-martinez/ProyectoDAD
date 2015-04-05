package com.ejemplo.proyect_uno;

public class Jugador {
    String Nombre;
    int Puntaje;
    int Nro;
    Boolean Repartidor;
    String IpAddres;

    Jugador(){}
    Jugador(String Nombre,int Nro, Boolean Repartidor, String IP){
        this.Nombre=Nombre;
        this.Puntaje = 0;
        this.Repartidor = Repartidor;
        this.IpAddres = IP;

    }

    public void setNro(int Nro){this.Nro=Nro;}
    public int getNro(){return this.Nro;}

    public void setIP(String IP){this.IpAddres=IP;}
    public String getIP(){return this.IpAddres;}

    public void setPuntaje(int Puntaje){this.Puntaje=Puntaje;}
    public int getPuntaje(){return this.Puntaje;}

    public String getNombre(){return Nombre; }
}
