import java.util.*;
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
    public Avancado (int numero,int velocidade, int resistencia,
    int destreza, int impulsao, int jogo_de_cabeça, 
    int remate, int capacidade_de_passe,float habilidade, 
    int finalizacao, int sprint,int idAtleta,String nomeAtleta,
    int idade,ArrayList<Equipa> eq,boolean tit,int p,boolean supl) {
        super(numero,
              velocidade,
              resistencia,
              destreza,
              impulsao,
              jogo_de_cabeça,
              remate,
              capacidade_de_passe,
              eq,
              idAtleta,
              nomeAtleta,
              idade,tit,p,supl);
        this.finalizacao = finalizacao;
        this.sprint = sprint;
        this.habilidade = calculaHabilidade();
    }


    public static Avancado parse(String input){
        ArrayList<Equipa> eq = new ArrayList<Equipa>();
        String[] campos = input.split(",");
        return new Avancado(Integer.parseInt(campos[2]),
                Integer.parseInt(campos[13]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                Integer.parseInt(campos[10]),
                Integer.parseInt(campos[11]),
                Integer.parseInt(campos[12]),
                campos[0],
                Integer.parseInt(campos[1]),eq,false,4,false);//titular = false suplente = false (???????)
    }

    /**
     * Construtor de cópia da classe Avançado
     * @param a
     */
    public Avancado (Avancado a) {
        super (a);
        this.habilidade = calculaHabilidade();
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
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" Finalização=").append(finalizacao);
        sb.append(", Sprint=").append(sprint);
        sb.append(" Avancado\n");
        return sb.toString();
    }

    /**
     * Função que faz clone de um Avançado
     * @return Clone do Avançado
     */
    public Avancado clone () {
        return new Avancado(this);
    }

    /**
     * Calcula a habilidade (overall) de um Avançado baseado numa classificação por pesos para as suas habilidades
     * @return Overall do Avançado
     */
    public float calculaHabilidade () {
        return Math.round(this.getVelocidade() *  0.12f +
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
