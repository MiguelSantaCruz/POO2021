
/**
 * Escreva a descrição da classe Jogador aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Jogador
{
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogo_de_cabeça;
    private int remate;
    private int capacidade_de_passe;

    /**
     * COnstrutor para objetos da classe Jogador
     */
    public Jogador()
    {
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogo_de_cabeça = 0;
        this.remate = 0;
        this.capacidade_de_passe = 0;
    }
    
    public Jogador(int velocidade, int resistencia, int destreza, int impulsao, int jogo_de_cabeça, int remate, int capacidade_de_passe){
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogo_de_cabeça = jogo_de_cabeça;
        this.remate = remate;
        this.capacidade_de_passe = capacidade_de_passe;
    
    }
    
        


}
