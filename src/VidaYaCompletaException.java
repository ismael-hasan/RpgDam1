public class VidaYaCompletaException extends Exception {
    private int vidaActual;

    public int getVidaActual() {
        return vidaActual;
    }

    public VidaYaCompletaException(int vidaActual) {
        this.vidaActual = vidaActual;
    }
}
