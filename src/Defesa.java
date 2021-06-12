import java.util.ArrayList;

public class Defesa extends Jogador{
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
     * @param intersecao
     * @param drible
     */
    public Defesa (int numero,int velocidade, int resistencia,int destreza, 
    int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe,
     int intersecao, int drible,int idAtleta,String nomeAtleta,int idade,
     ArrayList<IEquipa> eq, boolean t,int p,boolean supl) {
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
              idade,t,p,supl);
        this.habilidade = calculaHabilidade();
        this.drible = drible;
        this.intersecao = intersecao;
    }

    /**
     * Parser de defesa.
     * @param input A string com as caracteristicas do Defesa.
     * @return Novo defesa com as caracteristicas passadas na string.
     */
    public static Defesa parse(String input){
        ArrayList<IEquipa> eq = new ArrayList<IEquipa>();
        String[] campos = input.split(",");
        long tam = input.codePoints().filter(ch -> ch ==',').count();
        if (tam == 8 ){
            String idade = "20";
            String idA = "0";
            String drib = "50";
            String inter = "50";
            return new Defesa(Integer.parseInt(campos[1]),
            Integer.parseInt(campos[2]),
            Integer.parseInt(campos[3]),
            Integer.parseInt(campos[4]),
            Integer.parseInt(campos[5]),
            Integer.parseInt(campos[6]),
            Integer.parseInt(campos[7]),
            Integer.parseInt(campos[8]),
            Integer.parseInt(drib),
            Integer.parseInt(inter),
            Integer.parseInt(idA), 
            campos[0],
            Integer.parseInt(idade),eq,true,1,false);
        }
        else {return new Defesa(Integer.parseInt(campos[2]),
                Integer.parseInt(campos[12]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                Integer.parseInt(campos[10]),
                Integer.parseInt(campos[11]), 
                campos[0],
                Integer.parseInt(campos[1]),eq,true,1,false);
        }
    }

    /**
     * Construtor de cópia para a classe Defesa
     * @param d
     */
    public Defesa (Defesa d) {
        super ((Jogador)d);
        this.habilidade = d.calculaHabilidade();
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
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nInterseção: ").append(intersecao);
        sb.append("\nDrible: ").append(drible);
        sb.append("\nPosição: Defesa\n");
        return sb.toString();
    }

    /**
     * Função que faz clone de um Defesa
     * @return Clone do Defesa
     */
    public Defesa clone () {
        return new Defesa(this);
    }

    /**
     * Calcula a habilidade (overall) de um Defesa baseado numa classificação por pesos para as suas habilidades
     * @return Overall do defesa
     */
    public float calculaHabilidade () {
        return Math.round(this.getVelocidade() *  0.12f +
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
