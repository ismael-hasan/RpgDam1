import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Juego {

    private final static int ENEMIGO_VIDA_MAXIMA = 120;
    private final static int ENEMIGO_VIDA_MINIMA = 80;
    private final static int ENEMIGO_ATAQUE_MAXIMO = 20;
    private final static int ENEMIGO_ATAQUE_MINIMO = 5;


    private final static String[] nombreEnemigos = {
            "Aiden", "Nyx", "Valeria", "Kael", "Riven",
            "Elara", "Dante", "Selene", "Orion", "Vesper",
            "Lucian", "Freya", "Cassian", "Lyra", "Draven",
            "Aria", "Magnus", "Zara", "Ezra", "Nova",
            "Rowan", "Seraphina", "Axel", "Iris", "Theron"
    };

    private final static String[] aliasEnemigos = {
            "the Fallen", "the Unbroken", "the Silent Blade", "the Last Shadow", "the Stormbringer",
            "the Iron Fist", "the Void Walker", "the Night Reaper", "the Flame Warden", "the Soul Hunter",
            "the Crimson Vow", "the Black Revenant", "the Thunderlord", "the Blood Oath", "the Phantom Strike",
            "the Abyss Caller", "the Dread Knight", "the Frostborn", "the Warborn", "the Doombringer",
            "the Eclipse", "the Relentless", "the Vindicator", "the Shadowborn", "the Riftbreaker"
    };

    private Personaje jugador;

    // Podríamos declararlo como "List", pero hasta ver interfaces no lo hacemos.
    private ArrayList<Enemigo> enemigos;

    private Scanner sc;

    public Juego() {
        this.jugador = new Personaje("Jugador", 100, 20, 10);
        this.sc = new Scanner(System.in);
        this.enemigos = new ArrayList<Enemigo>();
    }

    public void iniciar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    jugador.mostrarEstado();
                    break;
                case 2:
                    buscarEnemigo();
                    break;
                case 3:
                    try {
                        jugador.descansar();
                    } catch (VidaAlMaximoException vame) {
                        System.out.println("No se puede descansar. La vida está al máximo: " + vame.getVidaActual());
                    }
                    break;
                case 4:
                    jugador.mostrarInventario();
                    break;
                case 5:
                    jugador.mostrarHabilidades();
                    break;
                case 6:
                    mostrarEnemigosEncontrados();
                    break;
                case 7:
                    guardarPersonaje();
                    break;
                case 8:
                    cargarPersonaje();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("===== MENU =====");
        System.out.println("1. Ver estado");
        System.out.println("2. Buscar enemigo");
        System.out.println("3. Descansar");
        System.out.println("4. Ver inventario");
        System.out.println("5. Ver habilidades");
        System.out.println("6. Ver enemigos encontrados");
        System.out.println("7. Guardar personaje");
        System.out.println("8. Cargar personaje");
        System.out.println("0. Salir");
        System.out.print("Elige opción: ");
    }

    private void buscarEnemigo() {

        // Para crear un enemigo aleatorio, vamos a usar 2 arrays de nombre y alias (constantes) para generar nombres aleatorios
        // Además, vamos a usar límites superiores e inferiores para el ataque y la vida, para que también sean aleatorios..
        Random aleatorio = new Random();

        String nombre = nombreEnemigos[aleatorio.nextInt(nombreEnemigos.length)] + " " +
                aliasEnemigos[aleatorio.nextInt(aliasEnemigos.length)];
        Enemigo enemigo = new Enemigo(nombre,
                aleatorio.nextInt(ENEMIGO_VIDA_MINIMA, ENEMIGO_VIDA_MAXIMA+1),
                aleatorio.nextInt(ENEMIGO_ATAQUE_MINIMO, ENEMIGO_ATAQUE_MAXIMO+1)
                );
        enemigos.add(enemigo);
        System.out.println("Has encontrado a un enemigo:");
        enemigo.mostrarInfo();

        combatir(enemigo);
    }

    private void mostrarEnemigosEncontrados() {

        System.out.println("Hasta ahora has encontrado " + enemigos.size());
        System.out.println("Te has encontrado con: ");
        for (int i = 0; i<enemigos.size(); i++) {
            System.out.println((i+1) + " - " + enemigos.get(i).getNombre()) ;
        }
    }

    private void guardarPersonaje() {
        try {
            GestorFicheros.guardarPersonaje(jugador);
        } catch (IOException e) {
            System.out.println("No se ha podido guardar el personaje.");
            e.printStackTrace();
        }
    }

    private void cargarPersonaje() {
        try {
            jugador = GestorFicheros.cargarPersonaje();
        } catch (IOException e) {
            System.out.println("No se ha podido cargar el personaje.");
            e.printStackTrace();
        }
    }

    private boolean combatir(Enemigo enemigo) {
        System.out.println("Comienza la pelea");

        boolean turnoJugador = true;
        boolean finalizado = false;

        while (!finalizado) {
            if (turnoJugador) {
                enemigo.recibirDanio(jugador.getAtaque());
                if (!enemigo.estaVivo()) {
                    finalizado = true;
                    System.out.println("El jugador ha ganado! Le queda de vida: " + jugador.getVida());
                }
            } else {
                jugador.recibirDanho(enemigo.getAtaque());
                if (jugador.getVida()<=0) {
                    System.out.println("El jugador ha perdido!");
                    finalizado = true;
                }
            }
            turnoJugador = !turnoJugador;
        }


        // TODO semana 3:
        // combate simple:
        // jugador y enemigo se atacan alternativamente hasta que uno muere.
        // Devuelve "true" si el jugador gana, "false" si el jugador muere.
        // El jugador gana una XP equivalente a los puntos de vida del enemigo.
        return true;
    }
}