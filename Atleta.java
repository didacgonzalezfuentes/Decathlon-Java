package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 * Clase para gestionar la información de un atleta
 * 
 */
public class Atleta {

    private final int numInscripcion;
    private final String nombre;
    private final String nacionalidad;
    private int puntos;
    private final MarcaEnEvento[] marcas;

    /**
     * Método constructor de la clase
     *
     * @param nombre Nombre cel atleta
     * @param nacionalidad Nacionalidad
     * @param numInscripcion Número de inscripción.
     */
    public Atleta(String nombre, String nacionalidad, int numInscripcion) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.numInscripcion = numInscripcion;
        this.puntos = 0;
        this.marcas = new MarcaEnEvento[5];
    }

    /**
     * Método getter. Devuelve el número de inscripción.
     *
     * @return Devuelve el número de inscripción.
     */
    public int getNumInscripcion() {
        return numInscripcion;
    }

    /**
     * Método getter. Devuelve el nombre del atleta.
     *
     * @return Devuelve el nombre del atleta.
     */
    public String getNombre() {
         return nombre;
    }

    /**
     * Método getter. Devuelve la nacionalidad del atleta.
     *
     * @return Devuelve la nacionalidad del atleta.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Método getter. Devuelve la suma de los puntos obtenidos por el atleta en
     * las diferentes pruebas en las que ha participado.
     *
     * @return Devuelve la suma de los puntos obtenidos por el atleta (el valor 
     * del atributo puntos).
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Crea una nueva MarcaEnEvento, la añade en la posición evento del vector
     * marcas, calcula los puntos correspondientes a ese evento para este atleta
     * y recalcula los puntos totales del atleta.
     *
     * @param evento el evento en el que el atleta ha conseguido la marca
     * @param marca la marca conseguida por el atleta
     */
    public void anyadirMarcaEnEvento(int evento, double marca) {
        MarcaEnEvento nuevaMarca = new MarcaEnEvento(evento, marca);
        nuevaMarca.calcularPuntosEvento();
        marcas[evento] = nuevaMarca;
        calcularPuntos();
    }   

    /**
     * Recalcula los puntos totales obtendos por el atleta hasta el momento.
     */
    public void calcularPuntos() {
        puntos = 0;
        for (MarcaEnEvento marca : marcas) {
            if (marca != null) {
                puntos += marca.getPuntos();
            }
        }
    }

    /**
     * Método toString() de la clase.
     *
     * @return String con la información del atleta. A continuación se muestra
     * un ejemplo de su contenido:<br><br>
     * Número de inscripción: 1<br>
     * Nombre: Pepe Pérez<br>
     * Nacionalidad:ES<br>
     * 100 metros lisos:     marca=11.278 segundos, puntos=800<br>
     * Salto de longitud:    marca=694.0 centimetros, puntos=799<br>
     * Lanzamiento de peso:  marca=15.16 metros, puntos=800<br>
     * Salto de altura:      marca=199.0 centimetros, puntos=794<br>
     * 400 metros lisos:     marca=50.32 segundos, puntos=800<br>
     * <br>
     * Puntos totales: 3993<br>
     * 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Número de inscripción: ").append(numInscripcion).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Nacionalidad:").append(nacionalidad).append("\n");

        String[] nombresEventos = {
            "100 metros lisos",
            "Salto de longitud",
            "Lanzamiento de peso",
            "Salto de altura",
            "400 metros lisos"
        };

        for (int i = 0; i < marcas.length; i++) {
            String texto = nombresEventos[i];
            while (texto.length() < 25) {
                texto += " ";
            }
            sb.append(texto).append(": ");

            if (marcas[i] == null) {
                sb.append("sin marca\n");
            } else {
                sb.append("marca=").append(marcas[i].getMarcaTexto())
                  .append(", puntos=").append(marcas[i].getPuntos()).append("\n");
            }
        }

        sb.append("\nPuntos totales: ").append(puntos);
        return sb.toString();
    }
}
