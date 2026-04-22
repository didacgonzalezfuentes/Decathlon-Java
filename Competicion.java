package edu.upc.etsetb.poo.decathlon1.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar la competición.
 */
public class Competicion {

    private final String nombre;
    private final String fecha;
    private final String lugar;
    private int numInscritos = 0;
    private final List<Atleta> atletas = new ArrayList<>();
    
    

    /**
     * Método constructor de la clase. Se le pasa la información de la
     * competición (nombre, fecha y lugar de la comoetición).
     *
     * @param nombre Nombre.
     * @param fecha Fecha.
     * @param lugar Lugar.
     */
    public Competicion(String nombre, String fecha, String lugar) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    /**
     * Método getter. Devuelve el número de inscritos.
     *
     * @return Devuelve el número de inscritos.
     */
    public int getNumInscritos() {
        return numInscritos;
    }

    /**
     * La primera vez que se invoca devolverá el número 1, la segunda el 2, etc.
     *
     * @return Devuelve el siguiente número de Inscripción.
     */
    public int obtenerSiguienteNumInscripcion() {
        numInscritos++;
        return numInscritos;
    }

    /**
     * Método toString() de la clase.
     *
     * @return String con la información de la competición; a continuación 
     * se muestra un ejemplo:<br>
     * Nombre de la competición: Valencia2022
     * Fecha de celebración: 20220329
     * Número de atletas inscritos: 60
     */
    @Override
    public String toString() {
         return "Nombre de la competición: " + nombre + "\n" +
               "Fecha de celebración: " + fecha + "\n" +
               "Lugar donde se celebra: " + lugar + "\n" +
               "Número de atletas inscritos: " + numInscritos;
    }

}
