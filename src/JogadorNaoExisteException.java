public class JogadorNaoExisteException extends Exception{
    /**
     * Exceção para quando um jogador não existe.
     */
    public JogadorNaoExisteException () {
        super();
    }

    /**
     * Exceção que envia uma mensagem quando um jogador não existe.
     * @param msg
     */
    public JogadorNaoExisteException (String msg) {
        super(msg);
    }
}
