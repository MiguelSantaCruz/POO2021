import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Equipa {
    private ArrayList<Jogador> plantel;
    //private String idDaEquipa;
    private String nome;
    private LocalDate dataDeFundação;
    private ArrayList<Jogo> jogosAgendados;
    private float habilidadeGlobal;


    public Equipa(String string,LocalDate d) {
        this.plantel = new ArrayList<>();
        //this.idDaEquipa = "NaN";
        this.nome = string;
        this.dataDeFundação = d;
        this.jogosAgendados = new ArrayList<>();
        this.habilidadeGlobal = 0;
    }

    public Equipa(List<Jogador> list,String nome, LocalDate c, ArrayList<Jogo> jogosAgendados) {
        this.plantel = (ArrayList<Jogador>) list;
        //this.idDaEquipa = idDaEquipa;
        this.nome = nome;
        this.dataDeFundação = c;
        this.jogosAgendados = jogosAgendados;
        this.habilidadeGlobal = calculaHabilidadeGlobal();
    }

    private float calculaHabilidadeGlobal() {
       float hab = 0;
        for(int i=0;i<plantel.size();i++){
            hab += plantel.get(i).getHabilidade();
        }
        hab = hab/plantel.size();
        return hab;
    }
    public static Equipa parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[1].split("-");
        return new Equipa(campos[0],LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }

    public void insereJogador(Jogador j) {
        plantel.add(j.clone());
        habilidadeGlobal = ((habilidadeGlobal*(plantel.size()-1)) + j.getHabilidade())/plantel.size();
    }

    public ArrayList<Jogador> getplantel() {
        return this.plantel;
    }

    public void setplantel(ArrayList<Jogador> plantel) {
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

    public void setHabilidadeGlobal(int habilidadeGlobal) {
        this.habilidadeGlobal = habilidadeGlobal;
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass()) return false;
        Equipa equipa = (Equipa) o;
        return Objects.equals(plantel, equipa.plantel) && habilidadeGlobal == equipa.habilidadeGlobal;
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
