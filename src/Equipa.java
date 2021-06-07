import java.util.Date;
import java.util.ArrayList;

public class Equipa {
    private ArrayList<Jogador> plantel;
    private String idDaEquipa;
    private String nome;
    private Date dataDeFundação;
    private ArrayList<Jogo> jogosAgendados;
    private int habilidadeGlobal;


    public Equipa() {
        this.plantel = null;
        this.idDaEquipa = "NaN";
        this.nome = "NaN";
        this.dataDeFundação = null;
        this.jogosAgendados = null;
        this.habilidadeGlobal = 0;
    }

    public Equipa(String nome){
        this.nome = nome;
        this.plantel = null;
        this.idDaEquipa = "NaN";
        this.dataDeFundação = null;
        this.jogosAgendados = null;
        this.habilidadeGlobal = 0;
    }

    public Equipa(ArrayList<Jogador> plantel, String idDaEquipa, String nome, Date dataDeFundação, ArrayList<Jogo> jogosAgendados, int habilidadeGlobal) {
        this.plantel = plantel;
        this.idDaEquipa = idDaEquipa;
        this.nome = nome;
        this.dataDeFundação = dataDeFundação;
        this.jogosAgendados = jogosAgendados;
        this.habilidadeGlobal = habilidadeGlobal;
    }

    public ArrayList<Jogador> getplantel() {
        return this.plantel;
    }

    public void setplantel(ArrayList<Jogador> plantel) {
        this.plantel = plantel;
    }

    public String getIdDaEquipa() {
        return this.idDaEquipa;
    }

    public void setIdDaEquipa(String idDaEquipa) {
        this.idDaEquipa = idDaEquipa;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeFundação() {
        return this.dataDeFundação;
    }

    public void setDataDeFundação(Date dataDeFundação) {
        this.dataDeFundação = dataDeFundação;
    }

    public ArrayList<Jogo> getJogosAgendados() {
        return this.jogosAgendados;
    }

    public void setJogosAgendados(ArrayList<Jogo> jogosAgendados) {
        this.jogosAgendados = jogosAgendados;
    }

    public int getHabilidadeGlobal() {
        return this.habilidadeGlobal;
    }

    public void setHabilidadeGlobal(int habilidadeGlobal) {
        this.habilidadeGlobal = habilidadeGlobal;
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass()) return false;
        Equipa equipa = (Equipa) o;

        return this.jogosAgendados.equals(equipa.jogosAgendados) && idDaEquipa == equipa.idDaEquipa && nome == equipa.nome && dataDeFundação == equipa.dataDeFundação && habilidadeGlobal == equipa.habilidadeGlobal && this.plantel.equals(equipa.plantel);
    }

    @Override
    public String toString() {
        return "{" +
            " plantel='" + getplantel() + "'" +
            ", idDaEquipa='" + getIdDaEquipa() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataDeFundação='" + getDataDeFundação() + "'" +
            ", jogosAgendados='" + getJogosAgendados() + "'" +
            ", habilidadeGlobal='" + getHabilidadeGlobal() + "'" +
            "}";
    }
   

}
