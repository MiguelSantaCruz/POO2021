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
    public Guarda_Redes(int velocidade, int resistencia, int destreza, int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe,int elasticidade){
        super(velocidade,resistencia,destreza,impulsao,jogo_de_cabeça,remate,capacidade_de_passe);
        this.elasticidade = elasticidade;
    }

    /**
     * Construtor de cópia para objetos da classe Guarda_Redes
     */
    public Guarda_Redes(Guarda_Redes g){
        super((Jogador) g);
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
        Guarda_Redes g = new Guarda_Redes();
        g.setVelocidade(this.getVelocidade());
        g.setResistencia(this.getResistencia());
        g.setDestreza(this.getDestreza());
        g.setImpulsao(this.getImpulsao());
        g.setJogoDeCabeca(this.getJogoDeCabeca());
        g.setRemate(this.getRemate());
        g.setCapacidadeDePasse(this.getCapacidadeDePasse());  
        g.setElasticidade(this.getElasticidade());
        return g;
    }

    /**
     * Converte para string o objeto Guarda_Redes
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" Elasticidade: ");
        sb.append(this.getElasticidade());
        return sb.toString();
    }

    
    /**
     * Calcula a habilidade de um Guarda_Redes
     */
    public float calculaHabilidade(){
        //Desvaloriza a resistencia, velocidade,jogo de cabeça e valoriza a destreza e
        // elasticidade
        return (this.getVelocidade() + this.getResistencia()*0.5f + this.getDestreza()*2 
                + this.getImpulsao() + this.getJogoDeCabeca()*0.5f + this.getRemate() 
                    + this.getCapacidadeDePasse() + this.getElasticidade() * 2);
    }
}