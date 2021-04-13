/**
 * Classe Jogador lateral
 * @version 1
 */
public class Lateral extends Jogador
{
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
    public Lateral(int velocidade, int resistencia, int destreza, int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe,int cruzamentos){
        super(velocidade,resistencia,destreza,impulsao,jogo_de_cabeça,remate,capacidade_de_passe);
        this.cruzamentos = cruzamentos;
    }

    /**
     * Construtor de cópia para objetos da classe Lateral
     */
    public Lateral(Lateral l){
        super((Jogador) l);
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
        Lateral l = new Lateral();
        l.setVelocidade(this.getVelocidade());
        l.setResistencia(this.getResistencia());
        l.setDestreza(this.getDestreza());
        l.setImpulsao(this.getImpulsao());
        l.setJogoDeCabeca(this.getJogoDeCabeca());
        l.setRemate(this.getRemate());
        l.setCapacidadeDePasse(this.getCapacidadeDePasse());  
        l.setCruzamentos(this.getCruzamentos());
        return l;
    }

    /**
     * Converte para string o objeto Lateral
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" Cruzamentos: ");
        sb.append(this.getCruzamentos());
        return sb.toString();
    }

    
    /**
     * Calcula a habilidade de um Lateral
     */
    public float calculaHabilidade(){
        //Desvaloriza a resistencia e valoriza o cruzamento
        return this.getVelocidade() + this.getResistencia()* 0.5f + this.getDestreza() 
                + this.getImpulsao() + this.getJogoDeCabeca() + this.getRemate() 
                    + this.getCapacidadeDePasse() + this.getCruzamentos() * 2;
    }
}
