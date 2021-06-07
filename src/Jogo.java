import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogo{
    //private int idJogo;
    private int golosCasa;
    private int golosVisitante; //deixei o numero de golos de cada equipa como dois ints pq assim podemos apenas fazer golosVisitantes++ em vez de alterar uma string
    private LocalDate date;
    private String nomeEqCasa;  //nome da equipa da Casa
    private String nomeEqVisitante; //nome da equipa Visitante
    private List <Integer> titularesCasa;   //Lista de todos os jogadores da equipa da casa
    private List <Integer> titularesVisitante;  //Lista de todos os jogadores da equipa Visitante
    Map<Integer, Integer> substituicoesCasa = new HashMap<>();
    Map<Integer, Integer> substituicoesFora = new HashMap<>();


    public Jogo () {
        //this.idJogo = 0;
        this.golosCasa = 0;
        this.golosVisitante = 0;
        this.date = null;
        this.nomeEqCasa = "";
        this.nomeEqVisitante = "";
        this.titularesCasa = new ArrayList<>();
        this.titularesVisitante = new ArrayList<>();
        this.substituicoesCasa = new HashMap<>();
        this.substituicoesFora  = new HashMap<>();
    }

    public Jogo (Jogo j) {
        //this.idJogo = j.idJogo;
        this.golosCasa = j.golosCasa;
        this.golosVisitante = j.golosVisitante;
        this.date = j.date;
        this.nomeEqCasa = j.getNomeEqCasa();
        this.nomeEqVisitante = j.getNomeEqVisitante();
        setTitularesCasa(j.getTitularesCasa());
        setTitularesVisitante(j.getTitularesVisitante());
        setSubstituicoesCasa (j.getSubstituicoesCasa ());
       // setSubstituicoesFora (j.getSubstituicoesFora());
    }
 

    public Jogo (String ec, String ef, int gc, int gf, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        this.golosCasa = gc;
        this.golosVisitante = gf;
        this.date = d;
        this.nomeEqCasa = ec;
        this.nomeEqVisitante = ef;
        this.titularesCasa = new ArrayList<>(jc);
        this.titularesVisitante = new ArrayList<>(jf);
        this.substituicoesCasa = new HashMap<>(sc);
        this.substituicoesFora= new HashMap<>(sf);
    }


    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                        LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                        jc, subsC, jf, subsF);
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

    public LocalDate getTempoJogo() {
        return this.date;
    }

    public void setTempoJogo(LocalDate data) {
        this.date = data;
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

    public List<Integer> getTitularesCasa() {
        return this.titularesCasa;
    }

    public void setTitularesCasa(List<Integer> titularesCasa) {
        this.titularesCasa = new ArrayList<>();
        //for (Jogador j : titularesCasa) this.titularesCasa.add(j.clone());
    }

    public List<Integer> getTitularesVisitante() {
        return this.titularesVisitante;
    }

    public void setTitularesVisitante(List<Integer> titularesVisitante) {
        this.titularesVisitante = new ArrayList<>();
        //for (Jogador j : titularesVisitante) this.titularesVisitante.add(j.clone());
    }

    public Map<Integer, Integer> getSubstituicoesCasa() {
        return this.substituicoesCasa;
    }

    public void setSubstituicoesCasa(Map<Integer, Integer> suplentesCasa) {
        this.substituicoesCasa = new HashMap<>();
        //for (Jogador j : suplentesCasa) this.substituicoesCasa.add(j.clone());
    }

    public  Map<Integer, Integer> getSubstituicoesFora() {
        return this.substituicoesFora;
    }

    public void setSubstituicoesFora(List<Integer> suplentesVisitantes) {
        this.substituicoesFora = new HashMap<>();
        //for (Jogador j : suplentesVisitantes) this.substituicoesFora.add(j.clone());
    }


    public boolean equals (Object o) {      //verificar se esta bem definido para o Tempo de jogo
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return this.golosCasa == jogo.golosCasa && 
                this.golosVisitante == jogo.golosVisitante && this.getTempoJogo() == ((Jogo) o).getTempoJogo()
                 && this.nomeEqCasa.equals(jogo.nomeEqCasa) && this.nomeEqVisitante.equals(jogo.nomeEqVisitante) 
                 && this.titularesCasa.equals(jogo.titularesCasa) && this.titularesVisitante.equals(jogo.titularesVisitante) 
                 && this.substituicoesCasa.equals(jogo.substituicoesCasa) && this.substituicoesFora.equals(jogo.substituicoesFora);
    }

    public Jogo clone () {
        return new Jogo(this);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("Jogo{");
        //sb.append("idJogo=").append(idJogo);
        sb.append("golosCasa=");
        sb.append(golosCasa);
        sb.append(", golosVisitante=");
        sb.append(golosVisitante);
        sb.append(", tempoJogo=");
        sb.append(date);
        sb.append(", nomeEqCasa='");
        sb.append(nomeEqCasa);
        sb.append('\'');
        sb.append(", nomeEqVisitante='");
        sb.append(nomeEqVisitante).append('\'');
        sb.append(", titularesCasa=");
        sb.append(titularesCasa);
        sb.append(", titularesVisitante=");
        sb.append(titularesVisitante);
        sb.append(", suplentesCasa=");
        sb.append(substituicoesCasa);
        sb.append(", suplentesVisitantes=");
        sb.append(substituicoesFora);
        sb.append('}');
        return sb.toString();
    }
}
