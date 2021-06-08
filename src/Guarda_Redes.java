import java.util.*;
/**
 * Classe Jogador Guarda_Redes
 * @version 1
 */
public class Guarda_Redes extends Jogador
{
    private int elasticidade;
 
    /**
     * Construtor vazio para objetos da classe Guarda_Redes
     */
    public Guarda_Redes(){
        super();
        this.elasticidade = 0;
    }

    /**
     * Construtor para objetos da classe Guarda_Redes
     */
    public Guarda_Redes(int numero,int velocidade, int resistencia, int destreza, 
    int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe,
    int elasticidade,int idAtleta,String nomeAtleta,int idade,ArrayList<Equipa> eq, boolean t, int p){
        super(numero,velocidade,
              resistencia,
              destreza,
              impulsao,
              jogo_de_cabeça,
              remate,
              capacidade_de_passe,
              eq,
              idAtleta,
              nomeAtleta,
              idade,t,p);
        this.habilidade = calculaHabilidade();
        this.elasticidade = elasticidade;
    }

    /**
     * Construtor de cópia para objetos da classe Guarda_Redes
     */
   public static Guarda_Redes parse(String input){
        ArrayList<Equipa> eq = new ArrayList<Equipa>();
        String[] campos = input.split(",");
        return new Guarda_Redes(Integer.parseInt(campos[2]),
                Integer.parseInt(campos[11]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                Integer.parseInt(campos[10]),
                campos[0],
                Integer.parseInt(campos[1]),eq,false,0);
    }
    /**
     * Construtor de cópia para objetos da classe Guarda_Redes
     */
    public Guarda_Redes(Guarda_Redes g){
        super((Jogador) g);
        this.habilidade = g.calculaHabilidade();
        this.elasticidade = g.getElasticidade();
    }


    /**
     * Retorna a elasticidade
     */
    public int getElasticidade(){
        return this.elasticidade;
    }

    /**
     * Atribui elasticidade ao guarda_redes
     */
    public void setElasticidade( int elasticidade){
        this.elasticidade = elasticidade;
    }
    
    /**
     * Compara um objeto com Guarda_Redes e diz se são iguais
     */
    public boolean equals(Object o){
        if (super.equals(o) == false) return false;
        Guarda_Redes g = (Guarda_Redes) o;
        return  this.getElasticidade() == g.getElasticidade();
    }

    /**
     * Clona o objeto Guarda_Redes
     */
    public Guarda_Redes clone(){
        return new Guarda_Redes(this);
    }

    /**
     * Converte para string o objeto Guarda_Redes
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" Elasticidade: ");
        sb.append(this.getElasticidade());
        sb.append(" Guarda-Redes\n");
        return sb.toString();
    }

    
    /**
     * Calcula a habilidade de um Guarda_Redes
     */
    public float calculaHabilidade(){
        //Desvaloriza a resistencia, velocidade,jogo de cabeça e valoriza a destreza e
        // elasticidade
        return (this.getVelocidade()*0.01f + 
                this.getResistencia()*0.02f + 
                this.getDestreza()*0.3f+ 
                this.getImpulsao()*0.2f + 
                this.getJogoDeCabeca()*0.1f + 
                this.getRemate()*0.1f+ 
                this.getCapacidadeDePasse()*0.02f + 
                this.getElasticidade() * 0.25f);
    }
}
