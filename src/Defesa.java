public class Defesa extends Jogador {
    private int intersecao;
    private int drible;

    /**
     * Construtor vazio para a classe Defesa
     */
    public Defesa () {
        super ();
        this.intersecao = 0;
        this.drible = 0;
    }

    /**
     * Construtor para objetos da classe Defesa
     * @param velocidade
     * @param resistencia
     * @param destreza
     * @param impulsao
     * @param jogo_de_cabeça
     * @param remate
     * @param capacidade_de_passe
     * @param idAtleta
     * @param nomeAtleta
     * @param iade
     * @param intersecao
     * @param drible
     */
        
    public Defesa (int velocidade, int resistencia,int destreza, int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe, String idAtleta, String nomeAtleta,int idade, int intersecao, int drible) {
        super(velocidade,resistencia,destreza,impulsao,jogo_de_cabeça,remate,capacidade_de_passe,idAtleta,nomeAtleta,idade);
        this.drible = drible;
        this.intersecao = intersecao;
    }

    /**
     * Construtor de cópia para a classe Defesa
     * @param d
     */
    public Defesa (Defesa d) {
        super (d);
        this.intersecao = d.intersecao;
        this.drible = d.drible;
    }

    /**
     * Função que retorna o valor da capacidade de Interseção de um Defesa
     * @return o valor associado à capacidade de interseção
     */
    public int getIntersecao() {
        return this.intersecao;
    }

    /**
     * Função que atribui um valor à capacidade de interseção de um Defesa
     * @param intersecao o valor a atribuir
     */
    public void setIntersecao(int intersecao) {
        this.intersecao = intersecao;
    }

    /**
     * Função que retorna o valor da capacidade de Drible de um Defesa
     * @return o valor associado à capacidade de Drible
     */
    public int getDrible() {
        return this.drible;
    }

    /**
     * Função que atribui um valor à capacidade de Drible de um Defesa
     * @param drible Valor a atribuir
     */
    public void setDrible(int drible) {
        this.drible = drible;
    }


    /**
     * Função que compara um Objeto com um Defesa
     * @param o o objeto a comparar
     * @return Validação da comparação
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Defesa defesa = (Defesa) o;
        return this.getIntersecao() == defesa.getIntersecao() && this.getDrible() == defesa.getDrible();
    }


    /**
     * Função que converte para String o objeto Defesa
     * @return Valores de interseção e drible em String
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder("Defesa{");
        sb.append("interseção=").append(intersecao);
        sb.append(", drible=").append(drible);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Função que faz clone de um Defesa
     * @return Clone do Defesa
     */
    public Defesa clone () {
        Defesa d = new Defesa();
        d.setVelocidade(this.getVelocidade());
        d.setDestreza(this.getDestreza());
        d.setResistencia(this.getResistencia());
        d.setJogoDeCabeca(this.getJogoDeCabeca());
        d.setImpulsao(this.getImpulsao());
        d.setRemate(this.getRemate());
        d.setCapacidadeDePasse(this.getCapacidadeDePasse());
        d.setIntersecao(this.getIntersecao());
        d.setDrible(this.getDrible());
        return d;
    }

    /**
     * Calcula a habilidade (overall) de um Defesa baseado numa classificação por pesos para as suas habilidades
     * @return Overall do defesa
     */
    public float calculaHabilidade () {
        return (this.getVelocidade() *  0.12f +
                this.getResistencia() * 0.05f +
                this.getDestreza() * 0.05f +
                this.getImpulsao() * 0.15f +
                this.getJogoDeCabeca() * 0.15f +
                this.getRemate() * 0.05f +
                this.getCapacidadeDePasse() * 0.15f +
                this.getIntersecao() * 0.15f +
                this.getDrible() * 0.13f);
    }
}