public class TestPersonajeInventario {

    public static void main(String[] args) {
        System.out.println("=== TEST INVENTARIO ===");

        Personaje p = new Personaje("Luffy", 120, 25, 8);

        p.anhadirObjeto("Pocion", 2);
        p.anhadirObjeto("Pocion", 3);
        p.anhadirObjeto("Carne", 1);

        boolean test1 = p.tieneObjeto("Pocion");
        boolean test2 = p.tieneObjeto("Carne");
        boolean test3 = !p.tieneObjeto("Espada");

        if (test1 && test2 && test3) {
            System.out.println("Test de añadir objetos: OK");
        } else {
            System.out.println("Test de añadir objetos: FAIL");
        }

        System.out.println("Inventario después de añadir objetos:");
        p.mostrarInventario();


        try {
            p.usarObjeto("Carne");
        } catch (ObjetoNoDisponibleException e) {
            System.out.println("TEST FALLA: no se pudo usar carne.");
            return;
        }

        boolean test4 = !p.tieneObjeto("Carne");

        if (test4) {
            System.out.println("Test de eliminar objeto al llegar a 0: OK");
        } else {
            System.out.println("Test de eliminar objeto al llegar a 0: FAIL");
        }

        System.out.println("Inventario final:");
        p.mostrarInventario();
    }
}