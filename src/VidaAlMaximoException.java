public class VidaAlMaximoException extends Exception {
    private int vidaActual;

    public int getVidaActual() {
        return vidaActual;
    }

    public VidaAlMaximoException(int vidaActual) {
        this.vidaActual = vidaActual;
    }
}
