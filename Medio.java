/**
 * Classe Jogador Medio
 * @version 1
 */
public class Medio extends Jogador
{
    private int recuperacao_bolas;
 
    /**
     * Construtor vazio para objetos da classe Medio
     */
    public Medio(){
        super();
        this.recuperacao_bolas = 0;
    }

    /**
     * Construtor para objetos da classe Medio
     */
    public Medio(int velocidade, int resistencia, int destreza, int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe,int recuperacao_bolas,int idAtleta,String nomeAtleta,int idade){
        super(velocidade,resistencia,destreza,impulsao,jogo_de_cabeça,remate,capacidade_de_passe,idAtleta,nomeAtleta,idade);
        this.recuperacao_bolas = recuperacao_bolas;
    }

    /**
     * Construtor de cópia para objetos da classe Medio
     */
    public Medio(Medio m){
        super((Jogador) m);
        this.recuperacao_bolas = m.getRecuperacao_bolas();
    }


    /**
     * Retorna recuperacao_bolas do Medio
     */
    public int getRecuperacao_bolas(){
        return this.recuperacao_bolas;
    }

    /**
     * Atribui recuperacao_bolas ao Medio
     */
    public void setRecuperacao_bolas( int recuperacao_bolas){
        this.recuperacao_bolas = recuperacao_bolas;
    }
    
    /**
     * Compara um objeto com Medio e diz se são iguais
     */
    public boolean equals(Object o){
        if (super.equals(o) == false) return false;
        Medio m = (Medio) o;
        return  this.getRecuperacao_bolas() == m.getRecuperacao_bolas();
    }

    /**
     * Clona o objeto Medio
     */
    public Medio clone(){
        Medio m= new Medio();
        m.setVelocidade(this.getVelocidade());
        m.setResistencia(this.getResistencia());
        m.setDestreza(this.getDestreza());
        m.setImpulsao(this.getImpulsao());
        m.setJogoDeCabeca(this.getJogoDeCabeca());
        m.setRemate(this.getRemate());
        m.setCapacidadeDePasse(this.getCapacidadeDePasse());  
        m.setRecuperacao_bolas(this.getRecuperacao_bolas());
        return m;
    }

    /**
     * Converte para string o objeto Medio
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" Recuperação de bolas: ");
        sb.append(this.getRecuperacao_bolas());
        return sb.toString();
    }

    
    /**
     * Calcula a habilidade de um Medio
     */
    public float calculaHabilidade(){
        //Desvaloriza a velocidade e valoriza o capcidade de passe e recuperação de bolas
        return (this.getVelocidade()*0.5f + this.getResistencia() + this.getDestreza() 
                + this.getImpulsao()*0.5f + this.getJogoDeCabeca() + this.getRemate() 
                    + this.getCapacidadeDePasse()*2 + this.getRecuperacao_bolas() * 3);
    }
}