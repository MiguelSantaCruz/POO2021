import java.util.ArrayList;

/**
 * Classe Jogador Medio
 * @version 1
 */
public class Medio extends Jogador{
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
    public Medio(int numero,int velocidade, int resistencia, int destreza, int impulsao,
     int jogo_de_cabeça, int remate, int capacidade_de_passe,int recuperacao_bolas,
     int idAtleta,String nomeAtleta,int idade, ArrayList<IEquipa> eq,boolean t, int p,boolean supl){
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
        this.recuperacao_bolas = recuperacao_bolas;
    }

    /**
     * Parser de um Médio.
     * @param input String com as informações do novo médio.
     * @return O novo Médio com os parametros preenchidos de acordo com as informações passadas.
     */
    public static Medio parse(String input){
        ArrayList<IEquipa> eq = new ArrayList<IEquipa>();
        String[] campos = input.split(",");
        long tam = input.codePoints().filter(ch -> ch ==',').count();
        if (tam == 9 ){
            String idade = "20";
            String idA = "0";
            return new Medio( Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                Integer.parseInt(idA),
                campos[0],
                Integer.parseInt(idade),eq,true, 3,false);
        }else{return new Medio( Integer.parseInt(campos[2]),
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
                Integer.parseInt(campos[1]),eq,true, 3,false);
        }
        
    }

    /**
     * Construtor de cópia para objetos da classe Medio
     */
    public Medio(Medio m){
        super((Jogador) m);
        this.habilidade = m.calculaHabilidade();
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
        return new Medio(this);
    }

    /**
     * Converte para string o objeto Medio
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nRecuperação de bolas: ");
        sb.append(this.getRecuperacao_bolas());
        sb.append("\nPosição: Médio\n");
        return sb.toString();
    }

    
    /**
     * Calcula a habilidade de um Medio
     */
    public float calculaHabilidade(){
        //Desvaloriza a velocidade e valoriza o capcidade de passe e recuperação de bolas
        return Math.round(this.getVelocidade()*0.05f+
                this.getResistencia()*0.01f + 
                this.getDestreza()*0.02f+
                this.getImpulsao()*0.05f + 
                this.getJogoDeCabeca()*0.05f + 
                this.getRemate()*0.12f+ 
                this.getCapacidadeDePasse()*0.31f + 
                this.getRecuperacao_bolas()*0.3f);
    }
}
