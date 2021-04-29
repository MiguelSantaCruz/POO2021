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

    public Equipa(ArrayList<Jogador> plantel, int idDaEquipa, String nome, Date dataDeFundação, ArrayList<Jogo> jogosAgendados, int habilidadeGlobal) {
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

    public int getIdDaEquipa() {
        return this.idDaEquipa;
    }

    public void setIdDaEquipa(int idDaEquipa) {
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

    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass()) return false;
        Equipa e = (Equipa) o;
        return Objects.equals(plantel, equipa.plantel) && idDaEquipa == equipa.idDaEquipa && Objects.equals(nome, equipa.nome) && Objects.equals(dataDeFundação, equipa.dataDeFundação) && Objects.equals(jogosAgendados, equipa.jogosAgendados) && habilidadeGlobal == equipa.habilidadeGlobal;
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
