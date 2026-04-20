import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class GestorFicheros {

    // Para transformar de Objeto a String y viceversa, vamos a serializar el objeto a un String con el formato de que en cada línea tenemos el
    // contenido de un campo.
    //   - Para el set de habilidades, guardaremos la lista separada por comas.
    //   - Para el mapa de inventario, guardaremos una lista clave:valor separada por comas.
    // Además, guardaremos como constantes en qué línea del fichero guardado mantenemos cada campo.
    //
    // NO estamos manejando caracteres especiales (como , o \n); si alguien mete un \n en el contenido de cualquiera
    // de los atributos de la clase Personaje, esto dejaría de funcionar.
    // Una forma de solucionar lo anterior sería NO permitir caracteres especiales en los atributos,
    // o escaparlos en esta clase.

    private final static int LINEA_NOMBRE = 0;
    private final static int LINEA_ATAQUE = 1;
    private final static int LINEA_DEFENSA = 2;
    private final static int LINEA_EXPERIENCIA = 3;
    private final static int LINEA_NIVEL = 4;
    private final static int LINEA_VIDA = 5;
    private final static int LINEA_VIDA_MAX = 6;
    private final static int LINEA_HABILIDADES = 7;
    private final static int LINEA_INVENTARIO = 8;

    // Decidimos usar un directorio temporal. Esto nos dará /tmp en Linux, C:\tmp en Windows. En MAC, asigno directamente /tmp, ya que esta variable da un directorio temporal diferente.
    private final static String GUARDADO_DIRECTORIO_JUEGO =
            // System.getProperty("java.io.tmpdir");
            "/tmp";
    private final static String GUARDADO_NOMBRE_ARCHIVO = "jugador.txt";

    private static String personajeAString(Personaje personaje) {

        StringBuilder resultado = new StringBuilder();
        resultado.append(personaje.getNombre()).append("\n");
        resultado.append(personaje.getAtaque()).append("\n");
        resultado.append(personaje.getDefensa()).append("\n");
        resultado.append(personaje.getExperiencia()).append("\n");
        resultado.append(personaje.getNivel()).append("\n");
        resultado.append(personaje.getVida()).append("\n");
        resultado.append(personaje.getVidaMax()).append("\n");

        for (String habilidad: personaje.getHabilidades()) {
            resultado.append(habilidad).append(",");
        }
        resultado.append("\n");

        for (String clave:personaje.getInventario().keySet()) {
            resultado.append(clave).append(":").append(personaje.getInventario().get(clave)).append(",");
        }
        resultado.append("\n");
        return resultado.toString();
    }

    private static Personaje stringAPersonaje(String input) {
        Personaje resultado;
        String[] lineas = input.split("\n");
        String nombre = lineas[LINEA_NOMBRE];
        int ataque = Integer.parseInt(lineas[LINEA_ATAQUE]);
        int defensa = Integer.parseInt(lineas[LINEA_DEFENSA]);
        int vida = Integer.parseInt(lineas[LINEA_VIDA]);
        int vidaMax = Integer.parseInt(lineas[LINEA_VIDA_MAX]);
        int experiencia = Integer.parseInt(lineas[LINEA_EXPERIENCIA]);
        int nivel = Integer.parseInt(lineas[LINEA_NIVEL]);

        // Ahora, construimos las habilidades
        HashSet<String> habilidades = new HashSet<String>();
        // si alguna línea estaba vacía, no la devuelve...
        if (lineas.length>LINEA_HABILIDADES) {
            String[] habilidadesRaw = lineas[LINEA_HABILIDADES].split(",");
            for (String habilidad : habilidadesRaw) {
                // Como dejamos una coma al final al serializar, tendremos al menos una habilidad vacía
                if (habilidad.length() > 0) {
                    habilidades.add(habilidad);
                }
            }
        }
        HashMap<String, Integer> inventario = new HashMap<>();
        if (lineas.length>LINEA_INVENTARIO) {
            // Finalmente, construimos el mapa de inventario:
            String[] paresClaveValor = lineas[LINEA_INVENTARIO].split(",");
            for (String par : paresClaveValor) {
                if (!par.isEmpty()) {
                    String[] parDividido = par.split(":");
                    inventario.put(parDividido[0], Integer.parseInt(parDividido[1]));
                }
            }
        }

        resultado = new Personaje(nombre, vida, vidaMax, ataque, defensa, nivel, experiencia, habilidades, inventario);
        return resultado;
    }

    public static void guardarPersonaje(Personaje personaje) throws IOException {
        String aGuardar = personajeAString(personaje);
        BufferedWriter writer = new BufferedWriter(new FileWriter(GUARDADO_DIRECTORIO_JUEGO + File.separator + GUARDADO_NOMBRE_ARCHIVO));
        writer.write(aGuardar);
        writer.close();
    }

    public static Personaje cargarPersonaje() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(GUARDADO_DIRECTORIO_JUEGO + File.separator + GUARDADO_NOMBRE_ARCHIVO));
        StringBuffer sb = new StringBuffer();
        String linea = reader.readLine();
        while (linea!=null) {
            sb.append(linea).append("\n");
            linea = reader.readLine();
        }
        reader.close();
        Personaje resultado = stringAPersonaje(sb.toString());
        return resultado;
    }
}

