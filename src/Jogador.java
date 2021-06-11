import java.util.ArrayList;

/**
 * Classe jogador
 * @version 1
 */
public class Jogador extends Atleta implements IJogador{
    
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogo_de_cabeça;
    private int remate;
    private int capacidade_de_passe;
    protected float habilidade;
    private int numeroJogador;  //numero da camisola do jogador
    private ArrayList<IEquipa> equipas;
    private int posicao;
    private boolean titular;
    private boolean suplente;
    
    

    /**
     * Construtor vazio para objetos da classe Jogador
     */
    public Jogador()
    {   super();
        this.numeroJogador = 0;
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogo_de_cabeça = 0;
        this.remate = 0;
        this.capacidade_de_passe = 0;
        this.habilidade = (float) 0.0;
        this.equipas = new ArrayList<IEquipa>();
        this.posicao = 0;
        this.titular = true;
        this.suplente = false;
    }
    
    /**
     * Construtor para objetos da classe Jogador
     */
    public Jogador(int numero,int velocidade, int resistencia, int destreza, int impulsao, 
    int jogo_de_cabeça, int remate, int capacidade_de_passe,ArrayList<IEquipa> eq,
    int idAtleta, String nomeAtleta,int idade,boolean tit, int p, boolean supl){
        super(idAtleta,
              nomeAtleta,
              idade);
        this.numeroJogador = numero;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogo_de_cabeça = jogo_de_cabeça;
        this.remate = remate;
        this.capacidade_de_passe = capacidade_de_passe;
        this.equipas = eq;
        this.posicao = p;
        this.titular = tit;
        this.suplente = supl;
    }
    
    /**
     * Construtor de cópia para objetos da classe Jogador
     */
    public Jogador(Jogador j){
        super((Atleta) j);
        this.numeroJogador = j. getNumeroJogador();
        this.velocidade = j.getVelocidade();
        this.resistencia = j.getResistencia();
        this.destreza = j.getDestreza();
        this.impulsao = j.getImpulsao();
        this.jogo_de_cabeça = j.getJogoDeCabeca();
        this.remate = j.getRemate();
        this.capacidade_de_passe = j.getCapacidadeDePasse();
        this.habilidade = j.getHabilidade();
        this.equipas = j.getEquipas();
        this.posicao = j.getPosicao();
        this.titular = j.getTitular();
        this.suplente = j.getSuplente();
    }

    /**
     * Getter que retorna se o Jogador está a suplente ou não.
     * @return Booleano correspondente a se o Jogador é suplente ou não.
     */
    public boolean getSuplente () {
        return this.suplente;
    }

    /**
     * Setter que permite alternar a variável de instância Suplente a true ou false.
     * @param t Novo estado de Suplente.
     */
    public void setSuplente (boolean t) {
        this.suplente = t;
    }

    /**
     * Saber se um jogador é titular ou não.
     * @return Booleano correspondente à titularidade do Jogador.
     */
    public boolean getTitular() {
        return this.titular;
    }

    /**
     * Permite deixar um Jogador como titular ou não.
     * @param t Booleano correspondente.
     */
    public void setTitular(boolean t) {
        this.titular = t;
    }

    /**
     * Saber a posição de um Jogador.
     * @return O inteiro correspondente à posição.
     */
    public int getPosicao() {
        return this.posicao;
    }

    /**
     * Alterar a posição do Jogador.
     * @param p O inteiro correspondente à nova posição.
     */
    public void setPosicao(int p){
        this.posicao = p;
    }

    /**
     * Obter a lista das equipas.
     * @return A lista das equipas
     */
    public ArrayList<IEquipa> getEquipas(){
        return this.equipas;
    }

    /**
     * Adicionar uma equipa à lista das equipas.
     * @param eq A equipa a adicionar
     */
    public void addEquipa(IEquipa eq) {
        this.equipas.add(eq.clone());
    }

    /**
     * Obter o número da Camisola do Jogador.
     * @return O seu número.
     */
    public int getNumeroJogador() {
        return this.numeroJogador;
    }

    /**
     * Obter a habilidade do Jogador
     * @return A sua habilidade.
     */
    public float getHabilidade() {
        return this.habilidade;
    }

    /**
     * Retorna a velocidade do Jogador
     */
    public int getVelocidade(){
        return this.velocidade;
    }

    /**
     * Retorna a resistencia do Jogador
     */
    public int getResistencia(){
        return this.resistencia;
    }

    /**
     * Retorna a destreza do Jogador
     */
    public int getDestreza(){
        return this.destreza;
    }

    /**
     * Retorna a impulsao do Jogador
     */
    public int getImpulsao(){
        return this.impulsao;
    }

    /**
     * Retorna o jogo de cabeça do Jogador
     */
    public int getJogoDeCabeca(){
        return this.jogo_de_cabeça;
    }

    /**
     * Retorna o remate do Jogador
     */
    public int getRemate(){
        return this.remate;
    }

    /**
     * Retorna a capacidade de passe do Jogador
     */
    public int getCapacidadeDePasse(){
        return this.capacidade_de_passe;
    }


    public void setNumeroJogador(int numero){
        this.numeroJogador = numero;
    }

    /**
     * Atribui velocidade ao Jogador
     */
    public void setVelocidade(int velocidade){
        this.velocidade = velocidade;
    }

    /**
     * Atribui resistencia ao Jogador
     */
    public void setResistencia(int resistencia){
        this.resistencia = resistencia;
    }

    /**
     * Atribui destreza ao Jogador
     */
    public void setDestreza(int destreza){
        this.destreza = destreza;
    }

    /**
     * Atribui impulsao ao Jogador
     */
    public void setImpulsao(int impulsao){
        this.impulsao = impulsao;
    }

    /**
     * Atribui jogo de cabeça ao Jogador
     */
    public void setJogoDeCabeca(int jogo_de_cabeça){
        this.jogo_de_cabeça = jogo_de_cabeça;
    }

    /**
     * Atribui remate ao Jogador
     */
    public void setRemate(int remate){
        this.remate = remate;
    }

    /**
     * Atribui capacidade de passe ao Jogador
     */
    public void setCapacidadeDePasse(int capacidade_de_passe){
        this.capacidade_de_passe = capacidade_de_passe;
    }

    /**
     * Alterar a habilidade do Jogador
     * @param habilidade O novo valor da Habilidade
     */
    public void setHabilidade(float habilidade){
        this.habilidade = habilidade;
    }

    /**
     * Compara um objeto com Jogador e diz se são iguais
     */
    public boolean equals(Object o){
        if(o.getClass() != this.getClass() || o == null) return false;
        Jogador j = (Jogador) o;
        return j.getNumeroJogador() == this.getNumeroJogador()&& j.getVelocidade() == this.getVelocidade() && j.getResistencia() == this.getResistencia()
                && j.getDestreza() == this.getDestreza() && j.getImpulsao() == this.getImpulsao()
                 &&j.getJogoDeCabeca() == this.getJogoDeCabeca() && j.getRemate() == this.getRemate() 
                  && j.getCapacidadeDePasse() == this.getCapacidadeDePasse()&& j.getHabilidade() == this.getHabilidade()&&
                  j.getEquipas() == this.getEquipas() && j.getSuplente() == this.getSuplente();
    }

    /**
     * Clona o objeto Jogador
     */
    public Jogador clone(){
        return new Jogador(this);
    }

    /**
     * Converte para string o objeto Jogador
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (IEquipa e : this.equipas) {
            s.append(e.getNome());
            s.append(" ");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nNúmero: ");
        sb.append(getNumeroJogador());
        sb.append("\nVelocidade: ");
        sb.append(getVelocidade());
        sb.append("\nResistencia: ");
        sb.append(getResistencia());
        sb.append("\nDestreza: ");
        sb.append(getDestreza());
        sb.append("\nImpulsão: ");
        sb.append(getImpulsao());
        sb.append("\nJogo de cabeça: ");
        sb.append(getJogoDeCabeca());
        sb.append("\nRemate: ");
        sb.append(getRemate());
        sb.append("\nCapacidade de passe: ");
        sb.append(getCapacidadeDePasse());
        sb.append("\nHabilidade: ");
        sb.append(getHabilidade());
        sb.append("\nEquipas por onde passou: ");
        sb.append(s.toString());
        sb.append("\nSuplente: ");
        sb.append(getSuplente());
        sb.append("\nTitular: ");
        sb.append(getTitular());
        return sb.toString();
    }

}
