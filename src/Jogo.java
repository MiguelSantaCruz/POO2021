import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogo{
    private String idJogo;
    private int golosCasa;
    private int golosVisitante; //deixei o numero de golos de cada equipa como dois ints pq assim podemos apenas fazer golosVisitantes++ em vez de alterar uma string
    private LocalDate date;
    private String nomeEqCasa;  //nome da equipa da Casa
    private String nomeEqVisitante; //nome da equipa Visitante
    private List <Jogador> titularesCasa;   //Lista de todos os jogadores da equipa da casa
    private List <Jogador> titularesVisitante;  //Lista de todos os jogadores da equipa Visitante
    private List <Jogador> suplentesCasa;   //Lista de todos os jogadores da equipa da casa
    private List <Jogador> suplentesVisitante;  //Lista de todos os jogadores da equipa Visitante
    Map<Integer, Jogador> substituicoesCasa = new HashMap<>();  //key é o nº da camisola e value é o proprio jogador
    Map<Integer, Jogador> substituicoesFora = new HashMap<>();  //key é o nº da camisola e value é o proprio jogador
    Map<Integer, Integer> entraSaiCasa = new HashMap<>();
    Map<Integer, Integer> entraSaiVisitante = new HashMap<>();


    public Jogo () {
        this.idJogo = "";
        this.golosCasa = 0;
        this.golosVisitante = 0;
        this.date = null;
        this.nomeEqCasa = "";
        this.nomeEqVisitante = "";
        this.titularesCasa = new ArrayList<>();
        this.titularesVisitante = new ArrayList<>();
        this.suplentesCasa = new ArrayList<>();
        this.suplentesVisitante = new ArrayList<>();
        this.substituicoesCasa = new HashMap<>();
        this.substituicoesFora  = new HashMap<>();
        this.entraSaiCasa = new HashMap<>();
        this.entraSaiVisitante = new HashMap<>();
    }

    public Jogo (Jogo j) {
        this.idJogo = j.idJogo;
        this.golosCasa = j.golosCasa;
        this.golosVisitante = j.golosVisitante;
        this.date = j.date;
        this.nomeEqCasa = j.getNomeEqCasa();
        this.nomeEqVisitante = j.getNomeEqVisitante();
        setTitularesCasa(j.getTitularesCasa());
        setTitularesVisitante(j.getTitularesVisitante());
        setSuplentesCasa(j.getSuplentesCasa());
        setSuplentesVisitante(j.getSuplentesVisitante());
        setSubstituicoesCasa (j.getSubstituicoesCasa ());
        setSubstituicoesFora (j.getSubstituicoesFora());
        setEntraSaiCasa(j.getEntraSaiCasa());
        setEntraSaiVisitante(j.getEntraSaiVisitante());
    }
 

    public Jogo (String idJogo, int gc, int gf, LocalDate d, String eqCasaName, String eqForaName, List<Jogador> titCasa, List<Jogador> titVis, List<Jogador> suplCasa, List<Jogador> suplVis, Map<Integer,Jogador> subsCasa, Map<Integer,Jogador> subsFora, Map<Integer, Integer> entraSaiCasa, Map <Integer,Integer> entraSaiVisitante){
        this.idJogo = idJogo;
        this.golosCasa = gc;
        this.golosVisitante = gf;
        this.date = d;
        this.nomeEqCasa = eqCasaName;
        this.nomeEqVisitante = eqForaName;
        this.setTitularesCasa(titCasa);
        this.setTitularesVisitante(titVis);
        this.setSuplentesCasa(suplCasa);
        this.setSuplentesVisitante(suplVis);
        this.setSubstituicoesCasa(subsCasa);
        this.setSubstituicoesFora(subsFora);
        this.setEntraSaiCasa(entraSaiCasa);
        this.setEntraSaiVisitante(entraSaiVisitante);
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

    public List<Jogador> getTitularesCasa() {
        return this.titularesCasa;
    }

    public void setTitularesCasa(List<Jogador> titularesCasa) {
        for (Jogador j : titularesCasa) {
            this.titularesCasa.add(j.clone());
        }
    }

    public List<Jogador> getTitularesVisitante() {
        return this.titularesVisitante;
    }

    public void setTitularesVisitante(List<Jogador> titularesVisitante) {
        for (Jogador j : titularesVisitante) {
            this.titularesVisitante.add(j.clone()); //fazer sempre o clone por causa do encapsulamento
        }
    }

    public List<Jogador> getSuplentesCasa() {
        return this.suplentesCasa;
    }

    public void setSuplentesCasa(List<Jogador> suplentesCasa) {
        for (Jogador j : suplentesCasa) {
            this.suplentesCasa.add(j.clone());
        }
    }

    public List<Jogador> getSuplentesVisitante() {
        return this.suplentesVisitante;
    }

    public void setSuplentesVisitante(List<Jogador> suplentesVisitante) {
        for (Jogador j : suplentesVisitante) {
            this.suplentesVisitante.add(j.clone()); //fazer sempre o clone por causa do encapsulamento
        }
    }

    public Map<Integer, Jogador> getSubstituicoesCasa() {
        return this.substituicoesCasa;
    }

    public void setSubstituicoesCasa(Map<Integer, Jogador> substCasa) {
        for (Jogador j : substCasa.values()) {
            this.substituicoesCasa.put(j.getNumeroJogador(), j.clone());
        }
    }

    public  Map<Integer, Jogador> getSubstituicoesFora() {
        return this.substituicoesFora;
    }

    public void setSubstituicoesFora(Map <Integer, Jogador> substFora) {
        for (Jogador j : substFora.values()) {
            this.substituicoesFora.put(j.getNumeroJogador(), j.clone());
        }
    }

    public Map<Integer, Integer> getEntraSaiCasa () {
        return this.entraSaiCasa;
    }

    public void setEntraSaiCasa (Map <Integer, Integer> entraSaiC) {
        for (Map.Entry<Integer, Integer> it : entraSaiC.entrySet()) {
            this.entraSaiCasa.put(it.getKey(), it.getValue());
        }
    }

    public Map<Integer, Integer> getEntraSaiVisitante () {
        return this.entraSaiVisitante;
    }

    public void setEntraSaiVisitante (Map <Integer, Integer> entraSaiV) {
        for (Map.Entry<Integer, Integer> it : entraSaiV.entrySet()) {
            this.entraSaiVisitante.put(it.getKey(), it.getValue());
        }
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
