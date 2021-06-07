import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Jogo {
    private int idJogo;
    private int golosCasa;
    private int golosVisitante; //deixei o numero de golos de cada equipa como dois ints pq assim podemos apenas fazer golosVisitantes++ em vez de alterar uma string
    private Time tempoJogo;
    private String nomeEqCasa;  //nome da equipa da Casa
    private String nomeEqVisitante; //nome da equipa Visitante
    private List <Jogador> titularesCasa;   //Lista de todos os jogadores da equipa da casa
    private List <Jogador> titularesVisitante;  //Lista de todos os jogadores da equipa Visitante
    private List <Jogador> suplentesCasa;
    private List <Jogador> suplentesVisitantes;


    public Jogo () {
        this.idJogo = 0;
        this.golosCasa = 0;
        this.golosVisitante = 0;
        this.tempoJogo = null;
        this.nomeEqCasa = "";
        this.nomeEqVisitante = "";
        this.titularesCasa = new ArrayList<>();
        this.titularesVisitante = new ArrayList<>();
        this.suplentesCasa = new ArrayList<>();
        this.suplentesVisitantes = new ArrayList<>();
    }

    public Jogo (Jogo j) {
        this.idJogo = j.idJogo;
        this.golosCasa = j.golosCasa;
        this.golosVisitante = j.golosVisitante;
        this.tempoJogo = j.tempoJogo;
        this.nomeEqCasa = j.getNomeEqCasa();
        this.nomeEqVisitante = j.getNomeEqVisitante();
        setTitularesCasa(j.getTitularesCasa());
        setTitularesVisitante(j.getTitularesVisitante());
        setSuplentesCasa(j.getSuplentesCasa());
        setSuplentesVisitantes(j.getSuplentesVisitantes());
    }

    public Jogo (int idJogo, int golosCasa, int golosVisitante, Time tempoJogo, String nomeEqCasa, String nomeEqVisitante, List <Jogador> titularesCasa, List <Jogador> titularesVisitante, List <Jogador> suplentesCasa, List <Jogador> suplentesVisitantes) {
        this.idJogo = idJogo;
        this.golosCasa = golosCasa;
        this.golosVisitante = golosVisitante;
        this.tempoJogo = tempoJogo;
        this.nomeEqCasa = nomeEqCasa;
        this.nomeEqVisitante = nomeEqVisitante;
        this.setTitularesCasa(titularesCasa);
        this.setSuplentesVisitantes(titularesVisitante);
        this.setSuplentesCasa(suplentesCasa);
        this.setSuplentesVisitantes(suplentesVisitantes);
    }

    public int getIdJogo() {
        return this.idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getGolosCasa() {
        return this.golosCasa;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public int getGolosVisitante() {
        return this.golosVisitante;
    }

    public void setGolosVisitante(int golosVisitante) {
        this.golosVisitante = golosVisitante;
    }

    public Time getTempoJogo() {
        return this.tempoJogo;
    }

    public void setTempoJogo(Time tempoJogo) {
        this.tempoJogo = tempoJogo;
    }

    public String getNomeEqCasa() {
        return this.nomeEqCasa;
    }

    public void setNomeEqCasa(String nomeEqCasa) {
        this.nomeEqCasa = nomeEqCasa;
    }

    public String getNomeEqVisitante() {
        return nomeEqVisitante;
    }

    public void setNomeEqVisitante(String nomeEqVisitante) {
        this.nomeEqVisitante = nomeEqVisitante;
    }

    public List<Jogador> getTitularesCasa() {
        return this.titularesCasa;
    }

    public void setTitularesCasa(List<Jogador> titularesCasa) {
        this.titularesCasa = new ArrayList<>();
        for (Jogador j : titularesCasa) this.titularesCasa.add(j.clone());
    }

    public List<Jogador> getTitularesVisitante() {
        return this.titularesVisitante;
    }

    public void setTitularesVisitante(List<Jogador> titularesVisitante) {
        this.titularesVisitante = new ArrayList<>();
        for (Jogador j : titularesVisitante) this.titularesVisitante.add(j.clone());
    }

    public List<Jogador> getSuplentesCasa() {
        return this.suplentesCasa;
    }

    public void setSuplentesCasa(List<Jogador> suplentesCasa) {
        this.suplentesCasa = new ArrayList<>();
        for (Jogador j : suplentesCasa) this.suplentesCasa.add(j.clone());
    }

    public List<Jogador> getSuplentesVisitantes() {
        return suplentesVisitantes;
    }

    public void setSuplentesVisitantes(List<Jogador> suplentesVisitantes) {
        this.suplentesVisitantes = new ArrayList<>();
        for (Jogador j : suplentesVisitantes) this.suplentesVisitantes.add(j.clone());
    }


    public boolean equals (Object o) {      //verificar se esta bem definido para o Tempo de jogo
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return this.idJogo == jogo.idJogo && this.golosCasa == jogo.golosCasa && this.golosVisitante == jogo.golosVisitante && this.getTempoJogo() == ((Jogo) o).getTempoJogo() && this.nomeEqCasa.equals(jogo.nomeEqCasa) && this.nomeEqVisitante.equals(jogo.nomeEqVisitante) && this.titularesCasa.equals(jogo.titularesCasa) && this.titularesVisitante.equals(jogo.titularesVisitante) && this.suplentesCasa.equals(jogo.suplentesCasa) && this.suplentesVisitantes.equals(jogo.suplentesVisitantes);
    }

    public Jogo clone () {
        return new Jogo(this);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("Jogo{");
        sb.append("idJogo=").append(idJogo);
        sb.append(", golosCasa=").append(golosCasa);
        sb.append(", golosVisitante=").append(golosVisitante);
        sb.append(", tempoJogo=").append(tempoJogo);
        sb.append(", nomeEqCasa='").append(nomeEqCasa).append('\'');
        sb.append(", nomeEqVisitante='").append(nomeEqVisitante).append('\'');
        sb.append(", titularesCasa=").append(titularesCasa);
        sb.append(", titularesVisitante=").append(titularesVisitante);
        sb.append(", suplentesCasa=").append(suplentesCasa);
        sb.append(", suplentesVisitantes=").append(suplentesVisitantes);
        sb.append('}');
        return sb.toString();
    }
}
