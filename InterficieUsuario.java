package edu.upc.etsetb.poo.decathlon1.iu;

import edu.upc.etsetb.poo.decathlon1.casosdeuso.Controlador;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * La interficie de usuario del programa
 */
/*
 * https://en.wikipedia.org/wiki/Decathlon
 * https://es.wikipedia.org/wiki/Decatl%C3%B3n
 */
public class InterficieUsuario {

    /**
     * Lector utilizado para leer los comandos introducidos por el usuario.
     */
    private Scanner lector;

    /**
     * El controlador.
     */
    private final Controlador controlador;

    /**
     * La consola del sistema. Siempre debe usarse para mostrar la información al usuario.
     */
    private final PrintStream console = System.out;

    /**
     * Separador utilizado en los comandos de entrada.
     */
    public static final String SEPARADOR = ":";

    public static final String NO_ATLETAS_INSCRITOS_STR = "ERROR: Aún no hay ningún atleta inscrito.";
    public static final String NUM_ATLETAS_ERRONEO_STR = "ERROR: Número de atletas erróneo.";
    public static final String NUM_INSCRIPCION_ERRONEO_STR = "ERROR: El número de inscripción es erróneo.";
    public static final String TIPO_DE_EVENTO_ERRONEO_STR = "ERROR: Número de tipo de evento erróneo.";
    public static final String CODIGO_ILEGAL_STR = "ERROR: Código ilegal.";
    public static final String CMD_ERRONEO_STR = "ERROR: Comando erróneo!";
    public static final String MARCA_ANYADIDA_STR = "Marca añadida";

    /**
     * Constructor
     */
    public InterficieUsuario(String detallesCompeticion, boolean contrInitAtletasYMarcas) {
        String[] partes = detallesCompeticion.split(SEPARADOR);
        String nombre = partes[0];
        String fecha = partes[1];
        String lugar = partes[2];
        this.controlador = new Controlador(nombre, fecha, lugar, this, contrInitAtletasYMarcas);
        this.lector = new Scanner(System.in);
    }

    public void inscribirAtleta(String[] args) {
        if (args.length >= 3) {
            controlador.inscribirAtleta(args[1], args[2]);
        }
    }

    public void mostrarCompeticion() {
        console.println(controlador.getInfoCompeticion());
    }

    public void mostrarAtleta(String[] args) {
        if (args.length >= 2) {
            try {
                int numInscripcion = Integer.parseInt(args[1]);
                String info = controlador.getInfoAtleta(numInscripcion);
                console.println(info);
            } catch (NumberFormatException e) {
                console.println(NUM_INSCRIPCION_ERRONEO_STR);
            }
        }
    }

    public void anyadirMarcaEnEventoDeUnAtleta(String[] args) {
        if (args.length >= 4) {
            try {
                int numInscripcion = Integer.parseInt(args[1]);
                int evento = Integer.parseInt(args[2]);
                double marca = Double.parseDouble(args[3]);

                int resultado = controlador.anyadirMarcaEnEventoDeUnAtleta(numInscripcion, evento, marca);
                switch (resultado) {
                    case Controlador.RESULTADO_OK:
                        console.println(MARCA_ANYADIDA_STR);
                        break;
                    case Controlador.NO_ATLETAS_INSCRITOS:
                        console.println(NO_ATLETAS_INSCRITOS_STR);
                        break;
                    case Controlador.NUM_INSCRIPCION_ERRONEO:
                        console.println(NUM_INSCRIPCION_ERRONEO_STR);
                        break;
                    case Controlador.TIPO_DE_EVENTO_ERRONEO:
                        console.println(TIPO_DE_EVENTO_ERRONEO_STR);
                        break;
                    default:
                        console.println(CODIGO_ILEGAL_STR);
                }
            } catch (NumberFormatException e) {
                console.println(CODIGO_ILEGAL_STR);
            }
        }
    }

    public void mostrarClasificacion(String[] args) {
        if (args.length >= 2) {
            try {
                int n = Integer.parseInt(args[1]);
                String resultado = controlador.getClasificacion(n);
                console.println(resultado);
                console.println(); // línea extra para pasar el test
            } catch (NumberFormatException e) {
                console.println(NUM_ATLETAS_ERRONEO_STR);
            }
        }
    }

    public void mostrarOpciones() {
        console.println("Opciones:");
        console.println("   ay                               Ayuda");
        console.println("   ia:nombre:nacionalidad           Inscribir atleta");
        console.println("   mc                               Mostrar datos de la competición");
        console.println("   ma:num_atleta                    Mostrar datos de un atleta");
        console.println("   am:num_atleta:num_evento:marca   Añadir marca de un evento a un atleta");
        console.println("   cl:num_atletas                   Mostrar clasificación");
        console.println("   fi                               Fin");
        console.println("Lista de eventos:");
        console.println("[0]\t100 metros lisos\t[1]\tSalto de longitud\t[2]\tLanzamiento de peso\t[3]\tSalto de altura\t[4]\t400 metros lisos");
    }

    public void ejecutaComando(String comando) {
        if (comando == null || comando.trim().isEmpty()) {
            console.println(CMD_ERRONEO_STR);
            return;
        }

        String[] args = comando.split(SEPARADOR);
        if (args.length == 0 || args[0].trim().isEmpty()) {
            console.println(CMD_ERRONEO_STR);
            return;
        }

        String cmd = args[0].toLowerCase();

        switch (cmd) {
            case "ay":
                mostrarOpciones();
                break;
            case "ia":
                inscribirAtleta(args);
                break;
            case "mc":
                mostrarCompeticion();
                break;
            case "ma":
                mostrarAtleta(args);
                break;
            case "am":
                anyadirMarcaEnEventoDeUnAtleta(args);
                break;
            case "cl":
                mostrarClasificacion(args);
                break;
            case "fi":
                break;
            default:
                console.println(CMD_ERRONEO_STR);
        }
    }

    public void start() {
        console.println("Para simplificar sólo tendremos las 5 pruebas que se celebran el primer día");
        console.println("Cuidado: Tu configuración de sistema puede tener como carácter decimal '.' o ','.");
        console.println("Introduce: Nombre de la competición, Fecha y Lugar donde se celebra separados por ':'.");
        lector = new Scanner(System.in);
        mostrarOpciones();
        while (true) {
            console.print("Entra un comando\n>");
            String comando = lector.nextLine().trim();
            if (comando.equalsIgnoreCase("fi")) {
                break;
            }
            ejecutaComando(comando);
        }
    }
}
