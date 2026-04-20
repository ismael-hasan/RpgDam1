public class ObjetoNoDisponibleException extends Exception {
    private String objeto;

    public ObjetoNoDisponibleException(String objeto) {
        this.objeto = objeto;
    }

    public String getObjeto() {
        return objeto;
    }
}
