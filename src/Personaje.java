import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Personaje {

    private String nombre;
    private int vida;
    private int vidaMax;
    private int ataque;
    private int defensa;
    private int nivel;
    private int experiencia;

    // Podríamos declararlos como Set y Map, pero hasta que veamos interfaces no lo haremos
    private HashSet<String> habilidades;
    private HashMap<String, Integer> inventario;

    // Este es el constructor para una partida nueva. Hace un personaje a través del constructor que
    // recibe TODOS los parámetros, pero inicializa como vacíos algunos.
    public Personaje(String nombre, int vidaMax, int ataque, int defensa) {
        this(nombre, vidaMax, vidaMax, ataque, defensa, 1, 0, new HashSet<String>(), new HashMap<String, Integer>());
    }

    // Constructor para hacer un personaje a partir de los datos que tenemos guardados del mismo.
    public Personaje(String nombre, int vida, int vidaMax, int ataque, int defensa, int nivel, int experiencia,
    HashSet<String> habilidades, HashMap<String, Integer> inventario) {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMax = vidaMax;
        this.ataque = ataque;
        this.defensa = defensa;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.habilidades = habilidades;
        this.inventario = inventario;
    }

    // =========================
    // MÉTODOS BÁSICOS
    // =========================

    public void mostrarEstado() {
        // Podríamos usar String, pero introducimos StringBuffer como una manera más eficiente
        // de manejar concatenar cadenas, etc.
        StringBuffer habilidadesEnString = new StringBuffer();
        for (String habilidad:habilidades) {
            habilidadesEnString.append(habilidad).append(" - ");
        }
        // Opcionalmente, podríamos ponerlo más bonito eliminando el " - " que añadimos al final (si lo hemos añadido)

        StringBuffer inventarioEnString = new StringBuffer();

        for (String objeto: inventario.keySet()) {
            inventarioEnString.append(objeto + ": " + inventario.get(objeto) + " - ");
        }
        // Opcionalmente, podríamos ponerlo más bonito eliminando el " - " que añadimos al final (si lo hemos añadido)


        System.out.println("Personaje: " +
                "\tNombre:'" + nombre + '\'' +
                "\n\tVida=" + vida +
                "\n\tVidaMax=" + vidaMax +
                "\n\tAtaque=" + ataque +
                "\n\tDefensa=" + defensa +
                "\n\tNivel=" + nivel +
                "\n\tExperiencia=" + experiencia +
                "\n\tHabilidades=" + habilidadesEnString.toString() +
                "\n\tInventario=" + inventarioEnString.toString());
    }

    public void descansar() throws VidaAlMaximoException {
        if (vida==vidaMax) {
            throw new VidaAlMaximoException(vida);
        } else {
            vida = vidaMax;
        }
    }

    public void ganarExperiencia(int cantidad) {
        // Habría que comprobar que la experiencia es positiva, pero no se ha pedido en el enunciado.
        // Como solución, no daremos error, simplemente no sumamos si es negativa.
        experiencia += Math.max(0,cantidad);
    }

    public void atacar(Enemigo enemigo) throws PersonajeMuertoException {
        if (vida<=0) {
            throw new PersonajeMuertoException();
        } else {
            enemigo.recibirDanio(ataque);
        }
    }

    // =========================
    // HABILIDADES (SET)
    // =========================

    public void anhadirHabilidad(String habilidad) {
        System.out.println("Añadiendo la habilidad: " + habilidad);
        if (habilidades.contains(habilidad)) {
            System.out.println("No se puede añadir " + habilidad + ": ¡ya la tenía!");
        } else {
            this.habilidades.add(habilidad);
            System.out.println("Habilidad " + habilidad + " añadida.");
        }
    }

    public void mostrarHabilidades() {
        if (habilidades.isEmpty()) {
            System.out.println("No tienes habilidades");
        } else {
            System.out.println("Tienes " + habilidades.size() + " habilidades. Las habilidades son: ");
            for (String habilidad : habilidades) {
                System.out.println("- " + habilidad);
            }
        }
    }

    public boolean tieneHabilidad(String habilidad) {
        return habilidades.contains(habilidad);
    }

    // =========================
    // INVENTARIO (MAP)

    // =========================

    public void anhadirObjeto(String objeto, int cantidad) {
        System.out.println("Añadiendo " + cantidad + " de " + objeto);

        // Versión extendida (la más clara a estas alturas)
        int cantidadAConfigurar;
        if (inventario.containsKey(objeto)) {
            cantidadAConfigurar = cantidad + inventario.get(objeto);
        } else {
            cantidadAConfigurar = cantidad;
        }
        inventario.put(objeto, cantidadAConfigurar);

        // Versión simplificada a una operación ternaria
        // inventario.put(objeto, inventario.containsKey(objeto)?inventario.get(objeto) + cantidad : cantidad);

        System.out.println("Ahora de " + objeto + " hay " + inventario.get(objeto));
    }

    public void mostrarInventario() {
        System.out.println("Mostrando inventario: ");

        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío!");
        } else {
            // Versión estándar
            Set<String> todasLasClaves = inventario.keySet();
            for (String clave : todasLasClaves) {
                System.out.println("- " + clave + ": " + inventario.get(clave));
            }

            // Versión usando "Entry<key,value" - no estudiada en clase.
            // for (Map.Entry<String,Integer> claveValor : inventario.entrySet()) {
            //     System.out.println("- " + claveValor.getKey() + ": " + claveValor.getValue());
            // }
        }
    }

    public boolean tieneObjeto(String objeto) {
        return inventario.containsKey(objeto);
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public HashSet<String> getHabilidades() {
        return habilidades;
    }

    public HashMap<String, Integer> getInventario() {
        return inventario;
    }

    public void usarObjeto(String objeto) throws ObjetoNoDisponibleException {

        if (!inventario.containsKey(objeto)) {
            throw new ObjetoNoDisponibleException(objeto);
        } else {
            if (inventario.get(objeto)<=0) {
                inventario.remove(objeto);
                throw new ObjetoNoDisponibleException(objeto);
            } else {
                int cantidadTrasUsar = inventario.get(objeto)-1;
                System.out.println("Usando " + objeto + ". Quedan " + cantidadTrasUsar);
                if (cantidadTrasUsar==0) {
                    System.out.println("Eliminando el objeto del inventario.");
                    inventario.remove(objeto);
                }
            }
        }
    }
    public void recibirDanho(int cantidad) {
        vida -= Math.max(0, cantidad-defensa);
    }
}