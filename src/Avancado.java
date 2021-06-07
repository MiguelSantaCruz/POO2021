public class Avancado extends Jogador {
    private int finalizacao;
    private int sprint;

    /**
     * Construtor por omissão da classe Avançado
     */
    public Avancado () {
        super ();
        this.finalizacao = 0;
        this.sprint = 0;
    }

    /**
     * Construtor parametrizado da classe Avançado
     * @param velocidade
     * @param resistencia
     * @param destreza
     * @param impulsao
     * @param jogo_de_cabeça
     * @param remate
     * @param capacidade_de_passe
     * @param finalizacao
     * @param sprint
     */
    public Avancado (int velocidade, int resistencia,int destreza, int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe, String idAtleta, String nomeAtleta, int idade,int finalizacao, int sprint) {
        super(velocidade,resistencia,destreza,impulsao,jogo_de_cabeça,remate,capacidade_de_passe,idAtleta,nomeAtleta,idade);
        this.finalizacao = finalizacao;
        this.sprint = sprint;
    }

    /**
     * Construtor de cópia da classe Avançado
     * @param a
     */
    public Avancado (Avancado a) {
        super (a);
        this.finalizacao = a.finalizacao;
        this.sprint = a.sprint;
    }

    /**
     * Função que retorna o valor da capacidade de Finalização de um Avançado
     * @return o valor associado à capacidade de finalização
     */
    public int getFinalizacao() {
        return this.finalizacao;
    }

    /**
     * Função que atribui um valor à capacidade de finalização de um Avançado
     * @param finalizacao o valor a atribuir
     */
    public void setFinalizacao(int finalizacao) {
        this.finalizacao = finalizacao;
    }

    /**
     * Função que retorna o valor da capacidade de Sprint de um Avançado
     * @return o valor associado à capacidade de Sprint
     */
    public int getSprint() {
        return this.sprint;
    }

    /**
     * Função que atribui um valor à capacidade de Sprint de um Avançado
     * @param sprint Valor a atribuir
     */
    public void setSprint(int sprint) {
        this.sprint = sprint;
    }


    /**
     * Função que compara um Objeto com um Avançado
     * @param o o objeto a comparar
     * @return Validação da comparação
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Avancado avancado = (Avancado) o;
        return this.getFinalizacao() == avancado.getFinalizacao() && this.getSprint() == avancado.getSprint();
    }


    /**
     * Função que converte para String o objeto Avançado
     * @return Valores de finalização e sprint em String
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder("Avançado{");
        sb.append("Finalização=").append(finalizacao);
        sb.append(", sprint=").append(sprint);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Função que faz clone de um Avançado
     * @return Clone do Avançado
     */
    public Avancado clone () {
        Avancado a = new Avancado();
        a.setVelocidade(this.getVelocidade());
        a.setDestreza(this.getDestreza());
        a.setResistencia(this.getResistencia());
        a.setJogoDeCabeca(this.getJogoDeCabeca());
        a.setImpulsao(this.getImpulsao());
        a.setRemate(this.getRemate());
        a.setCapacidadeDePasse(this.getCapacidadeDePasse());
        a.setFinalizacao(this.getFinalizacao());
        a.setSprint(this.getSprint());
        return a;
    }

    /**
     * Calcula a habilidade (overall) de um Avançado baseado numa classificação por pesos para as suas habilidades
     * @return Overall do Avançado
     */
    public float calculaHabilidade () {
        return (this.getVelocidade() *  0.12f +
                this.getResistencia() * 0.08f +
                this.getDestreza() * 0.07f +
                this.getImpulsao() * 0.15f +
                this.getJogoDeCabeca() * 0.10f +
                this.getRemate() * 0.15f +
                this.getCapacidadeDePasse() * 0.05f +
                this.getFinalizacao() * 0.15f +
                this.getSprint() * 0.13f);
    }
}
