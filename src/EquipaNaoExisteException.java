public class EquipaNaoExisteException extends Exception{
    /**
     * Exceção para quando uma equipa não existe.
     */
    public EquipaNaoExisteException () {
        super ();
    }

    /**
     * Exceção para quando uma equipa não existe que envia uma mensagem.
     * @param msg
     */
    public EquipaNaoExisteException (String msg) {
        super (msg);
    }
}
