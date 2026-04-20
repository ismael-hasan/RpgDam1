public class Enemigo {

    private String nombre;
    private int vida;
    private int ataque;

    public Enemigo(String nombre, int vida, int ataque) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
    }

    public void mostrarInfo() {
        System.out.println( nombre + " tiene una vida de " + vida + " y un ataque de " + ataque);
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirDanio(int cantidad) {
        vida -= cantidad;
        vida = Math.max(0, vida);
    }

    public boolean estaVivo() {
        return vida>0;
    }

    public int getAtaque() {
        return ataque;
    }
}