import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

public class Jogo{
    private int golosCasa;
    private int golosVisitante; //deixei o numero de golos de cada equipa como dois ints pq assim podemos apenas fazer golosVisitantes++ em vez de alterar uma string
    private LocalDate date;
    private Equipa eqCasa;  //equipa da Casa
    private Equipa eqVisitante; //equipa Visitante
    private List <Integer> titularesCasa;   //titulares da equipa da casa   -> Nunca usar este para efetuar substituiçoes ou assim (é so para guardar os titulares)
    private List <Integer> titularesVisitante;  //titulares da equipa Visitante -> Nunca usar este para efetuar substituiçoes ou assim (é so para guardar os titulares)
    //private List <Integer> suplentesCasa;   //suplentes da equipa da casa
    //private List <Integer> suplentesVisitante;  //Lista dos suplentes da equipa Visitante
    private List <Integer> emJogoCasa;  //jogadores que estao a jogar -> NOTA: temos sempre que trabalahr com isto pq os titulares nao se pode mexer (serve so para guardar a equipa titular)
    private List <Integer> emJogoFora;  //jogadores que estao a jogar -> NOTA: temos sempre que trabalahr com isto pq os titulares nao se pode mexer (serve so para guardar a equipa titular)
    //Map<Integer, Jogador> substituicoesCasa = new HashMap<>();  //key é o nº da camisola e value é o proprio jogador     -> isto é preciso??? Deixei tudo referente a isto em comentario (criei novo tostring)
    //Map<Integer, Jogador> substituicoesFora = new HashMap<>();  //key é o nº da camisola e value é o proprio jogador     -> isto é preciso??? Deixei tudo referente a isto em comentario (criei novo tostring)
    Map<Integer, Integer> entraSaiCasa = new HashMap<>();           //key é o nº da camisola que entra e value é o nº da camisola do que sai
    Map<Integer, Integer> entraSaiVisitante = new HashMap<>();      //key é o nº da camisola do que entra e value é o nº da camisola do que sai
    private int posicaoBola;    //0- meio-campo; 1-BalizaCasa; 2-BalizaVisitantes; 3-AreaCasa; 4-AreaVisitantes; 5-CantoCasa; 6-CantoVisitantes; 7-GoloCasa; 8-GoloVisitante

    public Jogo () {
        this.golosCasa = 0;
        this.golosVisitante = 0;
        this.date = LocalDate.now();
        this.eqCasa = new Equipa();
        this.eqVisitante = new Equipa();
        this.titularesCasa = new ArrayList<>();
        this.titularesVisitante = new ArrayList<>();
        //this.suplentesCasa = new ArrayList<>();
        //this.suplentesVisitante = new ArrayList<>();
        this.emJogoCasa = new ArrayList<>();
        this.emJogoFora = new ArrayList<>();
        //this.substituicoesCasa = new HashMap<>();
        //this.substituicoesFora  = new HashMap<>();
        this.entraSaiCasa = new HashMap<>();
        this.entraSaiVisitante = new HashMap<>();
        this.posicaoBola = 0;
    }

    public Jogo (Jogo j) {
        this.golosCasa = j.golosCasa;
        this.golosVisitante = j.golosVisitante;
        this.date = j.date;
        this.eqCasa = j.getEqCasa();
        this.eqVisitante = j.getEqVisitante();
        setTitularesCasa(j.getTitularesCasa());
        setTitularesVisitante(j.getTitularesVisitante());
        //setSuplentesCasa(j.getSuplentesCasa());
        //setSuplentesVisitante(j.getSuplentesVisitante());
        setEmJogoCasa(j.getEmJogoCasa());
        setEmJogoFora(j.getEmJogoFora());
        //setSubstituicoesCasa (j.getSubstituicoesCasa ());
        //setSubstituicoesFora (j.getSubstituicoesFora());
        setEntraSaiCasa(j.getEntraSaiCasa());
        setEntraSaiVisitante(j.getEntraSaiVisitante());
        setPosicaoBola(j.getPosicaoBola());
    }
 

    public Jogo (int gc, int gf,Equipa eqCasa, Equipa eqFora, LocalDate d, 
    List<Integer> titCasa, List<Integer> titVis,List<Integer> emJCasa, List<Integer> emJFora, Map<Integer, Integer> entraSaiCasa, Map <Integer,Integer> entraSaiVisitante){
        this.golosCasa = gc;
        this.golosVisitante = gf;
        this.date = d;
        this.eqCasa = eqCasa;
        this.eqVisitante = eqFora;
        this.titularesCasa= titCasa;
        this.titularesVisitante = titVis;
        //this.suplentesCasa = suplCasa;
        //this.suplentesVisitante = suplVis;
        this.emJogoCasa = emJCasa;
        this.emJogoFora = emJFora;
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
        Equipa eC = new Equipa(campos[0],LocalDate.now());
        Equipa eV = new Equipa(campos[1],LocalDate.now());
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
        return new Jogo(Integer.parseInt(campos[2]),
                        Integer.parseInt(campos[3]),
                        eC,
                        eV, 
                        LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                        jc,
                        jf,
                        jc,
                        jf, 
                        subsC,
                        subsF);
    }


    public int getPosicaoBola () {
        return this.posicaoBola;
    }

    public void setPosicaoBola (int i) {
        this.posicaoBola = i;
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

    public Equipa getEqCasa() {
        return this.eqCasa;
    }

    public void setNomeEqCasa(Equipa eqCasa) {
        this.eqCasa = eqCasa;
    }

    public Equipa getEqVisitante() {
        return this.eqVisitante;
    }

    public void setEqVisitante(Equipa EqVisitante) {
        this.eqVisitante = EqVisitante;
    }

    public List<Integer> getTitularesCasa() {
        return this.titularesCasa;
    }
    // public void setTitularesCasa(List<Jogador> titularesCasa) {
    //     for (Jogador j : titularesCasa) {
    //         this.titularesCasa.add(j.clone());
    //     }
    // }
    public void setTitularesCasa(List<Integer> titularesCasa) {
        HashMap<Integer,Jogador> e = this.eqCasa.getplantel();
        for (Map.Entry<Integer, Jogador> entry : e.entrySet()) {
            Integer i = entry.getKey();
            Jogador j = entry.getValue();
            if (titularesCasa.contains(i)) j.setTitular(true);
            else j.setTitular(false);
        }
    }
    public List<Integer> getTitularesVisitante() {
        return this.titularesVisitante;
    }
    // public void setTitularesVisitante(List<Jogador> titularesVisitante) {
    //     for (Jogador j : titularesVisitante) {
    //         this.titularesVisitante.add(j.clone()); //fazer sempre o clone por causa do encapsulamento
    //     }
    // }

    public void setTitularesVisitante(List<Integer> titularesVisitante) {
        HashMap<Integer,Jogador> e = this.eqCasa.getplantel();
        for (Map.Entry<Integer, Jogador> entry : e.entrySet()) {
            Integer i = entry.getKey();
            Jogador j = entry.getValue();
            if (titularesVisitante.contains(i)) j.setTitular(true);
            else j.setTitular(false);
        }
    }


    public List<Integer> getEmJogoCasa() {
        return this.emJogoCasa;
    }
    public void setEmJogoCasa(List<Integer> emJogoC) {
        this.emJogoCasa= emJogoC;
    }


    public List<Integer> getEmJogoFora() {
        return this.emJogoFora;
    }
    public void setEmJogoFora(List<Integer> emJogoF) {
        this.emJogoFora = emJogoF;
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
                 && this.eqCasa.equals(jogo.eqCasa) && this.eqVisitante.equals(jogo.eqVisitante) 
                 && this.titularesCasa.equals(jogo.titularesCasa) && this.titularesVisitante.equals(jogo.titularesVisitante)
                 && this.entraSaiCasa.equals(jogo.entraSaiCasa) && this.entraSaiVisitante.equals(jogo.entraSaiVisitante)
                 && this.posicaoBola == jogo.getPosicaoBola();
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
        sb.append(", golosCasa=").append(golosCasa);
        sb.append(", golosVisitante=").append(golosVisitante);
        sb.append(", date=").append(date);
        sb.append(", nomeEqCasa='").append(eqCasa).append('\'');
        sb.append(", nomeEqVisitante='").append(eqVisitante).append('\'');
        sb.append(", titularesCasa=").append(titularesCasa);
        sb.append(", titularesVisitante=").append(titularesVisitante);
        sb.append(", entraSaiCasa=").append(entraSaiCasa);
        sb.append(", entraSaiVisitante=").append(entraSaiVisitante);
        sb.append(", PosiçãoBola=").append(posicaoBola);
        sb.append('}');
        return sb.toString();
    }

    //não é aqui é em equipa
    // public double calculaHabilidadeCasa () {
    //     double total = 0.0;
    //     int size = this.getEmJogoCasa().size();
    //     if (size != 11) System.out.println("[ERRO] A equipa da casa tem mais de 11 jogadores...");
    //     for (Jogador j : this.getEmJogoCasa()) {
    //         total += j.getHabilidade();
    //     }
    //     return total/size;
    // }

    // public double calculaHabilidadeVisitantes () {
    //     double total = 0.0;
    //     int size = this.getEmJogoFora().size();
    //     if (size != 11) System.out.println("[ERRO] A equipa visitante tem mais de 11 jogadores...");
    //     for (Jogador j : this.getEmJogoFora()) {
    //         total += j.getHabilidade();
    //     }
    //     return total/size;
    // }
}
