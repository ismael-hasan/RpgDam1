public class TestPersonajeUsarObjeto {

    public static void main(String[] args) {
        System.out.println("=== TEST USAR OBJETO ===");

        Personaje p = new Personaje("Ichigo", 100, 18, 12);

        p.anhadirObjeto("Pocion", 2);

        System.out.println("Inventario inicial:");
        p.mostrarInventario();

        try {
            p.usarObjeto("Pocion");
        } catch (ObjetoNoDisponibleException e) {
            System.out.println("TEST FALLA: no se ha podido usar Pocion");
            return;
        }

        System.out.println("Inventario tras usar una poción:");
        p.mostrarInventario();

        boolean test1 = p.tieneObjeto("Pocion");

        try {
            p.usarObjeto("Pocion");
        } catch (ObjetoNoDisponibleException e) {
            System.out.println("TEST FALLA: no se ha podido usar Pocion");
            return;
        }

        boolean test2 = !p.tieneObjeto("Pocion");

        if (test1 && test2) {
            System.out.println("Test de usar objeto: OK");
        } else {
            System.out.println("Test de usar objeto: FAIL");
        }
    }
}