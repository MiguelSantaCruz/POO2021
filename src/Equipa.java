import java.time.LocalDate;
import java.util.*;

public class Equipa {
    private HashMap<Integer, Jogador>  plantel;
    private String nome;
    private LocalDate dataDeFundação;
    private ArrayList<Jogo> jogosAgendados;
    private float habilidadeGlobal;


    public Equipa() {
        this.plantel = new HashMap<>();
        this.nome = "";
        this.dataDeFundação = LocalDate.now();
        this.jogosAgendados = new ArrayList<>();
        this.habilidadeGlobal = 0;
    }

    public Equipa(String string,LocalDate d) {
        this.plantel = new HashMap<>();
        this.nome = string;
        this.dataDeFundação = d;
        this.jogosAgendados = new ArrayList<>();
        this.habilidadeGlobal = 0;
    }

    public Equipa(HashMap <Integer,Jogador> li,String nome, LocalDate c, ArrayList<Jogo> jogosAgendados) {
        this.plantel = li;
        this.nome = nome;
        this.dataDeFundação = c;
        this.jogosAgendados = jogosAgendados;
        this.habilidadeGlobal = calculaHabilidadeGlobal();
    }
    public Equipa(Equipa e) {
        this.nome = e.getNome();
        this.plantel = e.getplantel();
        this.dataDeFundação = e.getDataDeFundação();
        this.jogosAgendados = e.getJogosAgendados();
        this.habilidadeGlobal = e.getHabilidadeGlobal();
    }
    
    private float calculaHabilidadeGlobal() {       //NOTA:: TEMOS QUE CHAMAR ESTE METODO SEMPRE QUE MEXER NA EQUIPA!!!!
        double sum = 0;
        int i=0;
        for (Jogador j : this.plantel.values()) {
            if (j.getSuplente()==false) {   //ou seja, se estiver em jogo conta para o calculo da habilidade da equipa
                sum += j.getHabilidade();

                i += 1;
            }
        }
        float media = (float) (sum / i);
        return media;
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[1].split("-");
        return new Equipa(campos[0],LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }


    public void insereJogador(Jogador j) {
        this.plantel.put(j.getNumeroJogador(), j.clone());
        j.addEquipa(this);
        calculaHabilidadeGlobal();
    }
    public void removeJogador(Jogador jo) {
        for (Jogador j : this.plantel.values()) {
            if (jo.getNumeroJogador() == j.getNumeroJogador()) {
                this.plantel.remove(jo.getNumeroJogador());
                break;
            }
        }
    }
   
    public HashMap<Integer, Jogador> getplantel() {
        return this.plantel;
    }

    public void setplantel(HashMap<Integer,Jogador> plantel) {
        this.plantel = plantel;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeFundação() {
        return this.dataDeFundação;
    }

    public void setDataDeFundação(LocalDate dataDeFundação) {
        this.dataDeFundação = dataDeFundação;
    }

    public ArrayList<Jogo> getJogosAgendados() {
        return this.jogosAgendados;
    }

    public void setJogosAgendados(ArrayList<Jogo> jogosAgendados) {
        this.jogosAgendados = jogosAgendados;
    }

    public float getHabilidadeGlobal() {
        return this.habilidadeGlobal;
    }

    public void setHabilidadeGlobal(float habilidadeGlobal) {
        this.habilidadeGlobal = habilidadeGlobal;
    }
    public Jogador getJogador(int n) {return this.plantel.get(n);}
    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass()) return false;
        Equipa equipa = (Equipa) o;
        return Objects.equals(plantel, equipa.plantel) && habilidadeGlobal == equipa.habilidadeGlobal;
    }

    public Equipa clone() {
        return new Equipa(this);
    }

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
