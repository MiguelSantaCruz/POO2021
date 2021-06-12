import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Equipa implements IEquipa{
    private HashMap<Integer, IJogador>  plantel;
    private String nome;
    private LocalDate dataDeFundação;
    private ArrayList<IJogo> jogosAgendados;
    private float habilidadeGlobal;


    /**
     * Construtor vazio de Equipa
     */
    public Equipa() {
        this.plantel = new HashMap<>();
        this.nome = "";
        this.dataDeFundação = LocalDate.now();
        this.jogosAgendados = new ArrayList<>();
        this.habilidadeGlobal = 0;
    }

    /**
     * Construtor de Equipa.
     * @param string Nome da equipa.
     * @param d Data de fundação da Equipa.
     */
    public Equipa(String string,LocalDate d) {
        this.plantel = new HashMap<>();
        this.nome = string;
        this.dataDeFundação = d;
        this.jogosAgendados = new ArrayList<>();
        this.habilidadeGlobal = 0;
    }

    /**
     * Construtor parametrizado de Equipa.
     * @param li Map do plantel de Jogadores da Equipa.
     * @param nome Nome da equipa.
     * @param c Data de Fundação da Equipa.
     * @param jogosAgendados Lista dos jogos agendados.
     */
    public Equipa(HashMap <Integer,IJogador> li,String nome, LocalDate c, ArrayList<IJogo> jogosAgendados) {
        this.plantel = li;
        this.nome = nome;
        this.dataDeFundação = c;
        this.jogosAgendados = jogosAgendados;
        this.habilidadeGlobal = calculaHabilidadeGlobal();
    }

    /**
     * Construtor por cópia da equipa.
     * @param e A equipa pela qual queremos copiar.
     */
    public Equipa(Equipa e) {
        this.nome = e.getNome();
        this.plantel = e.getplantel();
        this.dataDeFundação = e.getDataDeFundação();
        this.jogosAgendados = e.getJogosAgendados();
        this.habilidadeGlobal = e.getHabilidadeGlobal();
    }

    /**
     * Método para calcular a habilidade da equipa que está em jogo.
     * @return O valor da média da habilidade.
     */
    public float calculaHabilidadeGlobal() {       //NOTA:: TEMOS QUE CHAMAR ESTE METODO SEMPRE QUE MEXER NA EQUIPA!!!!
        double sum = 0;
        int i=0;
        for (IJogador j : this.plantel.values()) {
            if (j.getSuplente()==false) {   //ou seja, se estiver em jogo conta para o calculo da habilidade da equipa
                sum += j.getHabilidade();
                i += 1;
            }
        }
        float media = (float) (sum / i);
        return Math.round(media);
    }

    /**
     * Parser de Equipa.
     * @param input Informação da Equipa.
     * @return Construtor de Equipa com os campos devidamente preenchidos.
     */
    public static Equipa parse(String input){
        String[] campos = input.split(",");
        long tam = input.codePoints().filter(ch -> ch ==',').count();
        String[] data ;
        if (tam == 0){        
            String d  = "2000-01-01";
            data  =  d.split("-");
        }else {data  =  campos[1].split("-");}
        
        return new Equipa(campos[0],LocalDate.of(Integer.parseInt(data[0]), 
                                                 Integer.parseInt(data[1]), 
                                                 Integer.parseInt(data[2]))); 
    }


    /**
     * Inserir um novo jogador na equipa.
     * @param j O Jogador a inserir
     */
    public void insereJogador(IJogador j) {
        while(this.plantel.containsKey(j.getNumeroJogador())){
            j.setNumeroJogador(j.getNumeroJogador()+1);
        }
        this.plantel.put(j.getNumeroJogador(), j.clone());
        j.addEquipa(this);
        this.habilidadeGlobal = this.calculaHabilidadeGlobal();
    }

    /**
     * Remover um Jogador da equipa.
     * @param jo O Jogador que sai da equipa.
     */
    public void removeJogador(IJogador jo) {
        for (IJogador j : this.plantel.values()) {
            if (jo.getNumeroJogador() == j.getNumeroJogador()) {
                this.plantel.remove(jo.getNumeroJogador());
                break;
            }
        }
        this.habilidadeGlobal = this.calculaHabilidadeGlobal();
    }

    /**
     * Obter o plantel da Equipa.
     * @return O plantel.
     */
    public HashMap<Integer, IJogador> getplantel() {
        return this.plantel;
    }

    /**
     * Definir o plantel da Equipa
     * @param plantel O Map do plantel (key é o nº da camisola e value é o jogador).
     */
    public void setplantel(HashMap<Integer,IJogador> plantel) {
        this.plantel = plantel;
    }

    /**
     * Obter o nome da Equipa.
     * @return O nome da Equipa.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Definir o nome da Equipa.
     * @param nome O novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obter a data de fundação da equipa.
     * @return A data.
     */
    public LocalDate getDataDeFundação() {
        return this.dataDeFundação;
    }

    /**
     * Definir a data de Fundação da Equipa.
     * @param dataDeFundação A nova data.
     */
    public void setDataDeFundação(LocalDate dataDeFundação) {
        this.dataDeFundação = dataDeFundação;
    }

    /**
     * Obter a Lista de jogos agendados.
     * @return A lista dos jogos.
     */
    public ArrayList<IJogo> getJogosAgendados() {
        return this.jogosAgendados;
    }

    /**
     * Definir uma nova lista de jogos agendados.
     * @param jogosAgendados A nova lista de jogos.
     */
    public void setJogosAgendados(ArrayList<IJogo> jogosAgendados) {
        this.jogosAgendados = jogosAgendados;
    }

    /**
     * Obter a habilidade global da equipa.
     * @return A habilidade da equipa.
     */
    public float getHabilidadeGlobal() {
        return this.habilidadeGlobal;
    }

    /**
     * Definir a nova habilidade global da equipa.
     * @param habilidadeGlobal O novo valor da habilidade.
     */
    public void setHabilidadeGlobal(float habilidadeGlobal) {
        this.habilidadeGlobal = habilidadeGlobal;
    }

    /**
     * Obter um jogador da equipa.
     * @param n O número da Camisola do Jogador que procuramos.
     * @return O Jogador correspondente ao número passado.
     */
    public IJogador getJogador(int n) {return this.plantel.get(n);}

    /**
     * Verificar a igualdade entre uma Equipa e um Objeto.
     * @param o O objeto a verificar
     * @return Validade da igualdade.
     */
    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass()) return false;
        Equipa equipa = (Equipa) o;
        return Objects.equals(plantel, equipa.plantel) && habilidadeGlobal == equipa.habilidadeGlobal;
    }

    /**
     * Clone de uma equipa.
     * @return A equipa.
     */
    public Equipa clone() {
        return new Equipa(this);
    }

    /**
     * Passar para String a equipa e todos os seus constituíntes.
     * @return A string com toda a informação.
     */
    @Override
    public String toString() {
        return "{" +
            " plantel='" + getplantel() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataDeFundação='" + getDataDeFundação() + "'" +
            ", jogosAgendados='" + getJogosAgendados() + "'" +
            ", habilidadeGlobal='" + getHabilidadeGlobal() + "'" +
            "}";
    }
   /* public String toString(){
        String r =  "Equipa:" + nome + "\n";
        for (Jogador j : plantel){
            r += j.toString();
        }
        return r;
    }*/
   

}
