package edu.upc.etsetb.poo.decathlon1.casosdeuso;

import edu.upc.etsetb.poo.decathlon1.dominio.Atleta;
import edu.upc.etsetb.poo.decathlon1.dominio.Clasificacion;
import edu.upc.etsetb.poo.decathlon1.dominio.Competicion;
import edu.upc.etsetb.poo.decathlon1.dominio.MarcaEnEvento;
import edu.upc.etsetb.poo.decathlon1.iu.InterficieUsuario;
import java.util.Map;

/**
 * El controlador del programa.
 */
public class Controlador {

    /**
     * Código entero indicativo de que todo ha ido bien
     */
    public static final int RESULTADO_OK = 0;
    /**
     * Código de error indicativo de que no hay atletas inscritos
     */
    public static final int NO_ATLETAS_INSCRITOS = 1;
    /**
     * Código de error indicativo de que el número de inscripción es erróneo
     */
    public static final int NUM_INSCRIPCION_ERRONEO = 2;
    /**
     * Código de error indicativo de que el número indicativo del evento es
     * erróneo
     */
    public static final int TIPO_DE_EVENTO_ERRONEO = 3;

    /**
     * El contenedor de atletas: un mapa que mapea un atleta (valor) con su
     * número de inscripción (clave)
     */
    private final Map<Integer, Atleta> atletas;
    /**
     * La competición
     */
    private final Competicion competicion;
    /**
     * La clasificación
     */
    private Clasificacion clasificacion;
    /**
     * La interficie de usuario
     */
    private InterficieUsuario iu;

    /**
     * Método constructor de la clase. Se le pasa la información correspondiente
     * a la competición y la crea.Dependiendo del valor del argumento inicializa
     * este método puede invocar Controlador::inicializaConAtletasYMarcas
     * Iniciales() para inscribir atletas y añadir marcas y así no tener que
     * hacerlo a mano cada vez que se ejecute el programa.
     *
     * @param nombre Nombre de la competición.
     * @param fecha Fecha de la competición.
     * @param lugar Lugar donde se celebra.
     * @param iu la interficie de usuario.
     * @param inicializa indica si se quiere que el controlador invoque al
     * método Controlador::inicializaConAtletasYMarcasIniciales() para inscribir
     * atletas y añadir sus marcas, o no (en este último caso no se inscribirían
     * atletas ni se añadiría ninguna marca)
     */
    public Controlador(String nombre, String fecha,
            String lugar, InterficieUsuario iu, boolean inicializa) {
        this.iu = iu;
        this.competicion = new Competicion(nombre, fecha, lugar);
        this.atletas = new java.util.TreeMap<>();
        
        if (inicializa) {
            this.inicializaConAtletasYMarcasIniciales();
        }
    }

    /**
     * Se crea un atleta y se inscribe en la competición
     *
     * @param nombre Nobre del Atleta.
     * @param nacionalidad Nacionalidad del Atleta.
     */
    public void inscribirAtleta(String nombre, String nacionalidad) {
        int numInscripcion = competicion.obtenerSiguienteNumInscripcion();
        Atleta atleta = new Atleta(nombre, nacionalidad, numInscripcion);
        atletas.put(numInscripcion, atleta);
    }

    /**
     * Devuelve un String con los datos de la competición.
     *
     * @return Devuelve un String con los datos de la competición.
     */
    public String getInfoCompeticion() {
        return competicion.toString();
    }

    /**
     * Devuelve el número de inscritos en la competición.
     *
     * @return Devuelve el número de inscritos en la competición.
     */
    public int getNumInscritosEnCompeticion() {
        return competicion.getNumInscritos();
    }

    /**
     * Devuelve el String generado con el método Atleta::toString() con todos 
     * los datos del atleta cuyo número de inscripción es pasado como argumento 
     * si ese número de inscripción es correcto; si no lo es devuelve un 
     * mensaje de error
     *
     * @param numInscripcion Número de inscripción del atleta.
     * @return Si el número de inscripción es correcto, devuelve un String con 
     * los datos del atleta con ese número de inscripción; en caso contrario 
     * devuelve el String InterficieUsuario.NUM_INSCRIPCION_ERRONEO_STR
     */
    public String getInfoAtleta(int numInscripcion) {
        Atleta atleta = atletas.get(numInscripcion);
        if (atleta == null) {
            return InterficieUsuario.NUM_INSCRIPCION_ERRONEO_STR;
        }
        return atleta.toString();
    }

    /**
     * Añade un marca para determinado evento y atleta. Devuelve el valor 0
     * (Controlador.RESULTADO_OK) si se ha podido añadir; devuelve valores
     * distintos de cero (ver return debajo) si aún no hay ningún atleta
     * inscrito, el número de inscripción del atleta es erróneo, o el número de
     * tipo de evento es erróneo.
     *
     * @param numInscripcion Número de inscripción del atleta.
     * @param evento Evento.
     * @param marca Marca.
     * @return Devuelve:<br>
     * Controlador.RESULTADO_OK si se ha podido añadir la marca del evento al
     * atleta; <br>
     * Controlador.NO_ATLETAS_INSCRITOS si todavía no hay atletas inscritos;
     * <br>
     * Controlador.NUM_INSCRIPCION_ERRONEO si el número de inscripción es
     * erróneo (recordad que el número de inscripción va de 1 a N siendo N el
     * número de atletas inscritos); <br>
     * Controlador.TIPO_DE_EVENTO_ERRONEO si el identificador del evento (un
     * entero) no es correcto (recordad que los identificadores de evento van de
     * 1 a MarcaEnEvento.NUM_EVENTOS);
     */
    public int anyadirMarcaEnEventoDeUnAtleta(int numInscripcion, int evento, double marca) {
        if (atletas.isEmpty()) {
            return NO_ATLETAS_INSCRITOS;
        }
        Atleta atleta = atletas.get(numInscripcion);
        if (atleta == null) {
            return NUM_INSCRIPCION_ERRONEO;
        }
        if (evento < 0 || evento >= MarcaEnEvento.NUM_EVENTOS) {
            return TIPO_DE_EVENTO_ERRONEO;
        }

        atleta.anyadirMarcaEnEvento(evento, marca);
        return RESULTADO_OK;
    }

    /**
     * Devuelve un String con la clasificación de los 'numAtletas' mejores
     * atletas si todo es correcto, pero devuelve un mensaje notificando un
     * error que comienza con "ERROR: " (ver return debajo) si: aún no hay
     * ningún atleta inscrito, el número de atletas que se quiere que aparezca
     * en la clasificación es erróneo (menor que 1 o mayor que el número de
     * atletas compitiendo)
     *
     * @param numAtletas Número de los 'n' mejores atletas que queremos que
     * aparecerán en la clasificación.
     * @return Devuelve<br>
     * Un String con la clasificación de los 'numAtletas' mejores atletas<br>
     * El String InterficieUsuario.NO_ATLETAS_INSCRITOS_STR si no se ha inscrito
     * ningún atleta<br>
     * El String InterficieUsuario.NUM_ATLETAS_ERRONEO_STR si el número de inscripción
     * pasado no es correcto
     */
    public String getClasificacion(int numAtletas) {
         if (atletas.isEmpty()) {
            return InterficieUsuario.NO_ATLETAS_INSCRITOS_STR;
        }
        if (numAtletas < 1 || numAtletas > atletas.size()) {
            return InterficieUsuario.NUM_ATLETAS_ERRONEO_STR;
        }

        clasificacion = new Clasificacion(numAtletas);
        for (Atleta atleta : atletas.values()) {
            clasificacion.anyadirAClasificacion(atleta);
        }
        return clasificacion.toString();
    }

    /**
     * Este método se os da hecho y no tenéis que modificarlo: inicializa el
     * Controlador con 5 atletas y todas sus marcas; el corrector automático
     * utiliza este método para realizar sus correcciones; NO LO TOQUÉIS.
     * Echadle un vistazo para ver cómo se inscriben atletas y se añaden sus
     * marcas A continuación se muestra un trozo del código que contiene este
     * método:<br>
     * inscribirAtleta("Kevin Mayer", "FRA");<br>
     * anyadirMarcaEnEventoDeUnAtleta(1, 0, 11.278);<br>
     * anyadirMarcaEnEventoDeUnAtleta(i, 1, 694);<br>
     * anyadirMarcaEnEventoDeUnAtleta(i, 2, 15.16);<br>
     * anyadirMarcaEnEventoDeUnAtleta(i, 3, 199);<br>
     * anyadirMarcaEnEventoDeUnAtleta(i, 4, 50.32);<br>
     *
     */
    public void inicializaConAtletasYMarcasIniciales() {
        // Codigo para poblar atletas y marcas
        // Se abre el periodo de inscripciones
        inscribirAtleta("Kevin Mayer", "FRA");      // 800 pts por prueba
        inscribirAtleta("Larbi Bourrada", "ALG");   // 1000 pts por prueba
        inscribirAtleta("Dimitriy Karpov", "KAZ");   // 900 pts por prueba
        inscribirAtleta("Ashton Eaton", "USA");     // 700 pts por prueba
        inscribirAtleta("Ashley Moloney", "AUS");   // se lesionó y no compitió :-(
        int i = 1;
        // 800 pts
        anyadirMarcaEnEventoDeUnAtleta(i, 0, 11.278);
        anyadirMarcaEnEventoDeUnAtleta(i, 1, 694);
        anyadirMarcaEnEventoDeUnAtleta(i, 2, 15.16);
        anyadirMarcaEnEventoDeUnAtleta(i, 3, 199);
        anyadirMarcaEnEventoDeUnAtleta(i, 4, 50.32);
        i++;
        // 1000 pts
        anyadirMarcaEnEventoDeUnAtleta(i, 0, 10.395);
        anyadirMarcaEnEventoDeUnAtleta(i, 1, 776);
        anyadirMarcaEnEventoDeUnAtleta(i, 2, 18.40);
        anyadirMarcaEnEventoDeUnAtleta(i, 3, 221);
        anyadirMarcaEnEventoDeUnAtleta(i, 4, 46.17);
        i++;
        // 900 pts
        anyadirMarcaEnEventoDeUnAtleta(i, 0, 10.827);
        anyadirMarcaEnEventoDeUnAtleta(i, 1, 736);
        anyadirMarcaEnEventoDeUnAtleta(i, 2, 16.79);
        anyadirMarcaEnEventoDeUnAtleta(i, 3, 210);
        anyadirMarcaEnEventoDeUnAtleta(i, 4, 48.19);
        i++;
        // 700 pts
        anyadirMarcaEnEventoDeUnAtleta(i, 0, 11.756);
        anyadirMarcaEnEventoDeUnAtleta(i, 1, 651);
        anyadirMarcaEnEventoDeUnAtleta(i, 2, 13.53);
        anyadirMarcaEnEventoDeUnAtleta(i, 3, 188);
        anyadirMarcaEnEventoDeUnAtleta(i, 4, 52.58);
        // Ashley Moloney se lesionó y no competirá :-/

    }

}
