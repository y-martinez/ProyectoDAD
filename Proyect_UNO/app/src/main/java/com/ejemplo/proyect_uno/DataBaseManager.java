package com.ejemplo.proyect_uno;

public class DataBaseManager {



    /* TABLA PARTIDA
    *  create table partida(
    *    id interger primary key autoincrement,
    *    sentido text not null
    *  );
    *
    */
    public static final String NOMBRE_TABLA_PARTIDA = "Partida";

    public static final String PARTIDA_CN_ID = "id";
    public static final String PARTIDA_CN_SENTIDO = "sentido";

    public static final String CREATE_TABLE_PARTIDA = "create table " + NOMBRE_TABLA_PARTIDA + " (" +
            PARTIDA_CN_ID + " integer primary key autoincrement,"+
            PARTIDA_CN_SENTIDO +" text not null);";

    /* TABLA JUGADOR
    * create table jugador(
    *   nombre varchar(100) primary key,
    *   puntaje int not null,
    *   nro int autoincrement,
    *   repartidor boolean not null,
    *   id_partida int
    *   foreign key (id_partida) references Partida(id)
    * );
    * */

    public static final String NOMBRE_TABLA_JUDADOR = "Jugador";

    public static final String JUDADOR_CN_NRO = "nro";
    public static final String JUDADOR_CN_NOMBRE = "nombre";
    public static final String JUDADOR_CN_REPARTIDOR = "repartidor";
    public static final String JUDADOR_CN_PUNTAJE = "puntaje";
    public static final String JUDADOR_CN_PARTIDA = "id_partida";

    public static final String CREATE_TABLE_JUGADOR = "create table " + NOMBRE_TABLA_JUDADOR + " (" +
            JUDADOR_CN_NOMBRE + " text primary key,"+
            JUDADOR_CN_PUNTAJE + " integer not null,"+
            JUDADOR_CN_NRO + " integer,"+
            JUDADOR_CN_REPARTIDOR + " integer not null,"+
            JUDADOR_CN_PARTIDA + " integer,"+
            " foreign key ("+JUDADOR_CN_PARTIDA+") references "+NOMBRE_TABLA_PARTIDA+" ("+PARTIDA_CN_ID+"));";

    /* TABLA CARTA
    * create table carta(
    *   id integer primary key
    *   especial integer not null,
    *   color text,
    *   nro int,
    *   tipo_especial text,
    *   mazo integer not null,
    *   primera integer not null
    *   nombre_jugador text,
    *   id_partida integer
    * );
    * */
    public static final String NOMBRE_TABLA_CARTA = "Carta";

    public static final String CARTA_CN_ID = "id";
    public static final String CARTA_CN_ESPECIAL = "especial";
    public static final String CARTA_CN_COLOR = "color";
    public static final String CARTA_CN_NRO = "nro";
    public static final String CARTA_CN_TIPO_ESPECIAL = "tipo_especial";
    public static final String CARTA_CN_MAZO = "mazo";
    public static final String CARTA_CN_PRIMERA = "primera";
    public static final String CARTA_CN_NOMBRE_JUGADOR = "nombre_jugador";
    public static final String CARTA_CN_PARTIDA = "id_partida";

    public static final String CREATE_TABLE_CARTA = "create table " + NOMBRE_TABLA_CARTA + " (" +
            CARTA_CN_ID + " integer primary key,"+
            CARTA_CN_ESPECIAL + " integer not null,"+
            CARTA_CN_COLOR + " text,"+
            CARTA_CN_NRO + " integer,"+
            CARTA_CN_TIPO_ESPECIAL + " text,"+
            CARTA_CN_MAZO + " integer not null,"+
            CARTA_CN_PRIMERA + " integer not null,"+
            " foreign key ("+CARTA_CN_PARTIDA+") references "+NOMBRE_TABLA_PARTIDA+" ("+PARTIDA_CN_ID+"),"+
            " foreign key ("+CARTA_CN_NOMBRE_JUGADOR+") references "+NOMBRE_TABLA_JUDADOR+" ("+JUDADOR_CN_NOMBRE+"));";
 }
