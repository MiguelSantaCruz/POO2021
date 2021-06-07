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
    private List <Jogador> titularesCasa;   //titulares da equipa da casa   -> Nunca usar este para efetuar substituiçoes ou assim (é so para guardar os titulares)
    private List <Jogador> titularesVisitante;  //titulares da equipa Visitante -> Nunca usar este para efetuar substituiçoes ou assim (é so para guardar os titulares)
    private List <Jogador> suplentesCasa;   //suplentes da equipa da casa
    private List <Jogador> suplentesVisitante;  //Lista dos suplentes da equipa Visitante
    private List <Jogador> emJogoCasa;  //jogadores que estao a jogar -> NOTA: temos sempre que trabalahr com isto pq os titulares nao se pode mexer (serve so para guardar a equipa titular)
    private List <Jogador> emJogoFora;  //jogadores que estao a jogar -> NOTA: temos sempre que trabalahr com isto pq os titulares nao se pode mexer (serve so para guardar a equipa titular)
    //Map<Integer, Jogador> substituicoesCasa = new HashMap<>();  //key é o nº da camisola e value é o proprio jogador     -> isto é preciso??? Deixei tudo referente a isto em comentario (criei novo tostring)
    //Map<Integer, Jogador> substituicoesFora = new HashMap<>();  //key é o nº da camisola e value é o proprio jogador     -> isto é preciso??? Deixei tudo referente a isto em comentario (criei novo tostring)
    Map<Integer, Integer> entraSaiCasa = new HashMap<>();           //key é o nº da camisola que entra e value é o nº da camisola do que sai
    Map<Integer, Integer> entraSaiVisitante = new HashMap<>();      //key é o nº da camisola do que entra e value é o nº da camisola do que sai


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
        this.emJogoCasa = new ArrayList<>();
        this.emJogoFora = new ArrayList<>();
        //this.substituicoesCasa = new HashMap<>();
        //this.substituicoesFora  = new HashMap<>();
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
        setEmJogoCasa(j.getEmJogoCasa());
        setEmJogoFora(j.getEmJogoFora());
        //setSubstituicoesCasa (j.getSubstituicoesCasa ());
        //setSubstituicoesFora (j.getSubstituicoesFora());
        setEntraSaiCasa(j.getEntraSaiCasa());
        setEntraSaiVisitante(j.getEntraSaiVisitante());
    }
 

    public Jogo (String idJogo, int gc, int gf, LocalDate d, String eqCasaName, String eqForaName, List<Jogador> titCasa, List<Jogador> titVis, List<Jogador> suplCasa, List<Jogador> suplVis, List<Jogador> emJCasa, List<Jogador> emJFora, Map<Integer, Integer> entraSaiCasa, Map <Integer,Integer> entraSaiVisitante){
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
        this.setEmJogoCasa(emJCasa);
        this.setEmJogoFora(emJFora);
        //this.setSubstituicoesCasa(subsCasa);
        //this.setSubstituicoesFora(subsFora);
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


    public List<Jogador> getEmJogoCasa() {
        return this.emJogoCasa;
    }
    public void setEmJogoCasa(List<Jogador> emJogoC) {
        for (Jogador j : emJogoC) {
            this.emJogoCasa.add(j.clone());
        }
    }


    public List<Jogador> getEmJogoFora() {
        return this.emJogoFora;
    }
    public void setEmJogoFora(List<Jogador> emJogoF) {
        for (Jogador j : emJogoF) {
            this.emJogoFora.add(j.clone()); //fazer sempre o clone por causa do encapsulamento
        }
    }

    /*
            Deixei em comentario pq nao sei se será preciso depois...

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
    } */

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
                 && this.suplentesCasa.equals(jogo.suplentesCasa) && this.suplentesVisitante.equals(jogo.suplentesVisitante)
                 && this.entraSaiCasa.equals(jogo.entraSaiCasa) && this.entraSaiVisitante.equals(jogo.entraSaiVisitante);
                 //&& this.substituicoesCasa.equals(jogo.substituicoesCasa) && this.substituicoesFora.equals(jogo.substituicoesFora);
    }

    public Jogo clone () {
        return new Jogo(this);
    }


    /*
            Este toString tem as coisas do suplentesCasa e suplentesFora que eu deixei em comentario
            Deixei este em comentario pq nao sei se ainda vai ser preciso

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
 */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Jogo{");
        sb.append("idJogo='").append(idJogo).append('\'');
        sb.append(", golosCasa=").append(golosCasa);
        sb.append(", golosVisitante=").append(golosVisitante);
        sb.append(", date=").append(date);
        sb.append(", nomeEqCasa='").append(nomeEqCasa).append('\'');
        sb.append(", nomeEqVisitante='").append(nomeEqVisitante).append('\'');
        sb.append(", titularesCasa=").append(titularesCasa);
        sb.append(", titularesVisitante=").append(titularesVisitante);
        sb.append(", suplentesCasa=").append(suplentesCasa);
        sb.append(", suplentesVisitante=").append(suplentesVisitante);
        sb.append(", entraSaiCasa=").append(entraSaiCasa);
        sb.append(", entraSaiVisitante=").append(entraSaiVisitante);
        sb.append('}');
        return sb.toString();
    }


    public double calculaHabilidadeCasa () {
        double total = 0.0;
        int size = this.getEmJogoCasa().size();
        if (size != 11) System.out.println("[ERRO] A equipa da casa tem mais de 11 jogadores...");
        for (Jogador j : this.getEmJogoCasa()) {
            total += j.getHabilidade();
        }
        return total/size;
    }

    public double calculaHabilidadeVisitantes () {
        double total = 0.0;
        int size = this.getEmJogoFora().size();
        if (size != 11) System.out.println("[ERRO] A equipa visitante tem mais de 11 jogadores...");
        for (Jogador j : this.getEmJogoFora()) {
            total += j.getHabilidade();
        }
        return total/size;
    }
}
