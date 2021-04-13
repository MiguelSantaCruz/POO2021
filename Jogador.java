/**
 * Classe jogador
 * @version 1
 */
public class Jogador
{
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogo_de_cabeça;
    private int remate;
    private int capacidade_de_passe;

    /**
     * Construtor vazio para objetos da classe Jogador
     */
    public Jogador()
    {
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogo_de_cabeça = 0;
        this.remate = 0;
        this.capacidade_de_passe = 0;
    }
    
    /**
     * Construtor para objetos da classe Jogador
     */
    public Jogador(int velocidade, int resistencia, int destreza, int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe){
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogo_de_cabeça = jogo_de_cabeça;
        this.remate = remate;
        this.capacidade_de_passe = capacidade_de_passe;
    }
    
    /**
     * Construtor de cópia para objetos da classe Jogador
     */
    public Jogador(Jogador j){
        this.velocidade = j.getVelocidade();
        this.resistencia = j.getResistencia();
        this.destreza = j.getDestreza();
        this.impulsao = j.getImpulsao();
        this.jogo_de_cabeça = j.getJogoDeCabeca();
        this.remate = j.getRemate();
        this.capacidade_de_passe = j.getCapacidadeDePasse();
    }

    /**
     * Retorna a velocidade do Jogador
     */
    int getVelocidade(){
        return this.velocidade;
    }

    /**
     * Retorna a resistencia do Jogador
     */
    int getResistencia(){
        return this.resistencia;
    }

    /**
     * Retorna a destreza do Jogador
     */
    int getDestreza(){
        return this.destreza;
    }

    /**
     * Retorna a impulsao do Jogador
     */
    int getImpulsao(){
        return this.impulsao;
    }

    /**
     * Retorna o jogo de cabeça do Jogador
     */
    int getJogoDeCabeca(){
        return this.jogo_de_cabeça;
    }

    /**
     * Retorna o remate do Jogador
     */
    int getRemate(){
        return this.remate;
    }

    /**
     * Retorna a capacidade de passe do Jogador
     */
    int getCapacidadeDePasse(){
        return this.capacidade_de_passe;
    }

    /**
     * Atribui velocidade ao Jogador
     */
    void setVelocidade(int velocidade){
        this.velocidade = velocidade;
    }

    /**
     * Atribui resistencia ao Jogador
     */
    void setResistencia(int resistencia){
        this.resistencia = resistencia;
    }

    /**
     * Atribui destreza ao Jogador
     */
    void setDestreza(int destreza){
        this.destreza = destreza;
    }

    /**
     * Atribui impulsao ao Jogador
     */
    void setImpulsao(int impulsao){
        this.impulsao = impulsao;
    }

    /**
     * Atribui jogo de cabeça ao Jogador
     */
    void setJogoDeCabeca(int jogo_de_cabeça){
        this.jogo_de_cabeça = jogo_de_cabeça;
    }

    /**
     * Atribui remate ao Jogador
     */
    void setRemate(int remate){
        this.remate = remate;
    }

    /**
     * Atribui capacidade de passe ao Jogador
     */
    void setCapacidadeDePasse(int capacidade_de_passe){
        this.capacidade_de_passe = capacidade_de_passe;
    }

    /**
     * Compara um objeto com Jogador e diz se são iguais
     */
    public boolean equals(Object o){
        if(o.getClass() != this.getClass() || o == null) return false;
        Jogador j = (Jogador) o;
        return j.getVelocidade() == this.getVelocidade() && j.getResistencia() == this.getResistencia()
                && j.getDestreza() == this.getDestreza() && j.getImpulsao() == this.getImpulsao()
                 &&j.getJogoDeCabeca() == this.getJogoDeCabeca() && j.getRemate() == this.getRemate() 
                  && j.getCapacidadeDePasse() == this.getCapacidadeDePasse();
    }

    /**
     * Clona o objeto Jogador
     */
    public Jogador clone(){
        Jogador j = new Jogador();
        j.velocidade = this.getVelocidade();
        j.resistencia = this.getResistencia();
        j.destreza = this.getDestreza();
        j.impulsao = this.getImpulsao();
        j.jogo_de_cabeça = this.getJogoDeCabeca();
        j.remate = this.getRemate();
        j.capacidade_de_passe = this.getCapacidadeDePasse();
        return j;
    }

    /**
     * Converte para string o objeto Jogador
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Velocidade: ");
        sb.append(getVelocidade());
        sb.append(" Resistencia: ");
        sb.append(getResistencia());
        sb.append(" Destreza: ");
        sb.append(getDestreza());
        sb.append(" Impulsão: ");
        sb.append(getImpulsao());
        sb.append(" Jogo de cabeça: ");
        sb.append(getJogoDeCabeca());
        sb.append(" Remate: ");
        sb.append(getRemate());
        sb.append(" Capacidade de passe: ");
        sb.append(getCapacidadeDePasse());
        return sb.toString();
    }
    
    /**
     * Calcula a habilidade de um Jogador
     */
    public float calculaHabilidade(){
        //Considera todos os parametros importantes
        return this.velocidade + this.resistencia + this.destreza 
                + this.impulsao + this.jogo_de_cabeça + this.remate 
                  + this.capacidade_de_passe;
    }
    


}
