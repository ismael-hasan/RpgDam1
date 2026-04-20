public class TestPersonajeDescanso {

    public static void main(String[] args) {
        System.out.println("=== TEST DESCANSO ===");

        Personaje p = new Personaje("Goku", 100, 30, 15);

        System.out.println("Estado inicial:");
        p.mostrarEstado();

        System.out.println("Intentando descansar con la vida al máximo...");
        try {
            p.descansar();
            System.out.println("Se permitió descansar... No se tendría que poder, ya que la vida estaba al máximo.");
        } catch (VidaYaCompletaException vame) {
            System.out.println("Vida al máximo. No se puede descansar. Test OK.");
        }
        System.out.println("Estado tras descansar:");
        p.mostrarEstado();

        System.out.println("Comprobación visual: la vida no debe superar la vida máxima.");
    }
}