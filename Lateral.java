/**
 * Classe Jogador lateral
 * @version 1
 */
public class Lateral extends Jogador{
    private int cruzamentos;
 
    /**
     * Construtor vazio para objetos da classe Lateral
     */
    public Lateral(){
        super();
        this.cruzamentos = 0;
    }

    /**
     * Construtor para objetos da classe Lateral
     */
    public Lateral(int numero,int velocidade, int resistencia, int destreza, int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe,int cruzamentos,int idAtleta,String nomeAtleta,int idade) {
        super(numero,velocidade,
                resistencia,
                destreza,
                impulsao,
                jogo_de_cabeça,
                remate,
                capacidade_de_passe,
                idAtleta,
                nomeAtleta,
                idade);
        this.habilidade = calculaHabilidade();
        this.cruzamentos = cruzamentos;
    }

    public static Lateral parse(String input){
        String[] campos = input.split(",");
        return new Lateral(Integer.parseInt(campos[2]),
                Integer.parseInt(campos[10]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                Integer.parseInt(campos[11]),
                campos[0],
                Integer.parseInt(campos[1]));
    }
    /**
     * Construtor de cópia para objetos da classe Lateral
     */
    public Lateral(Lateral l){
        super((Jogador) l);
        this.habilidade = l.calculaHabilidade();
        this.cruzamentos = l.getCruzamentos();
    }


    /**
     * Retorna cruzamento do Lateral
     */
    public int getCruzamentos(){
        return this.cruzamentos;
    }

    /**
     * Atribui cruzamento ao Lateral
     */
    public void setCruzamentos( int cruzamentos){
        this.cruzamentos = cruzamentos;
    }
    
    /**
     * Compara um objeto com Lateral e diz se são iguais
     */
    public boolean equals(Object o){
        if (super.equals(o) == false) return false;
        Lateral l = (Lateral) o;
        return  this.getCruzamentos() == l.getCruzamentos();
    }

    /**
     * Clona o objeto Lateral
     */
    public Lateral clone(){
        return new Lateral(this);
    }

    /**
     * Converte para string o objeto Lateral
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" Cruzamentos: ");
        sb.append(getCruzamentos());
        sb.append(" Lateral\n");
        return sb.toString();
    }

    
    /**
     * Calcula a habilidade de um Lateral
     */
    public float calculaHabilidade(){
        //Desvaloriza a resistencia e valoriza o cruzamento
        return this.getVelocidade()*0.02f +
               this.getResistencia()* 0.03f + 
               this.getDestreza()*0.12f + 
               this.getImpulsao()*0.05f + 
               this.getJogoDeCabeca()*0.05f + 
               this.getRemate()*0.03f +
               this.getCapacidadeDePasse()*0.2f + 
               this.getCruzamentos()*0.5f ;
    }
}
