package edu.upc.etsetb.poo.decathlon1.dominio;

import java.util.LinkedList;

/**
 * Clase para gestionar la clasificación; solo mostrará, ordenados, un número 
 * determinado de atletas (que puede ser diferente al número de participantes) pasado 
 * como argumento al constructor.
 */
public class Clasificacion {

    private final int numAtletas;
    private final LinkedList<Atleta> atletas;

    /**
     * Método constructor de la clase. Se le pasa el número de atletas que
     * tendrá la clasificación y crea la lista , de momento vacia, de atletas.
     *
     * @param numAtletas El número de atletas que se incluirán en el String 
     * que devolverá el método toString().
     */
    public Clasificacion(int numAtletas) {
        this.numAtletas = numAtletas;
        this.atletas = new LinkedList<>();
    }

    /**
     * Añade ordenadamente el atleta a a la clasificación (de acuerdo a los
     * puntos totales dek atleta) y, si el número de atletas en la
     * clasificación ya supera el guardado en el atributo numAtletas, elimina
     * el atleta con menos puntos. De este modo, si se crea una clasificación y
     * se van añadiendo todos los atletas utilizando este método, al final
     * quedarán los numAtletas con mejor puntuación ordenados por dicha
     * puntuación.
     *
     * @param a El atleta añadir a la clasificación
     */
    public void anyadirAClasificacion(Atleta a) {
         int i = 0;
        while (i < atletas.size() && a.getPuntos() < atletas.get(i).getPuntos()) {
            i++;
        }
        atletas.add(i, a);

        // Si se pasa del límite, se elimina el último
        if (atletas.size() > numAtletas) {
            atletas.removeLast();
        }
    }

    /**
     * Método toString() de la clase.
     *
     * @return Devuelve la clasificación con, para cada atleta, número de
     * clasificación, nacionalidad y nombre; a continuación se muestra un 
     * ejemplo si el valor del atributo numAtletas es 5:<br>
     * 1 ALG Larbi Bourrada<br>
     * 2 KAZ Dmitriy Karpov<br>
     * 3 FRA Kevin Mayer<br>
     * 4 USA Ashton Eaton<br>
     * 5 AUS Ashley Moloney<br>
     */
    @Override
    public String toString() {
         StringBuilder sb = new StringBuilder();
        int pos = 1;
        for (Atleta atleta : atletas) {
            sb.append(pos).append(" ")
              .append(atleta.getNacionalidad()).append(" ")
              .append(atleta.getNombre()).append("\n");
            pos++;
        }
        return sb.toString().trim();
    }

}
