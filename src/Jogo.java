import java.time.LocalDate;
import java.util.*;

public class Jogo implements IJogo{
    private int golosCasa;
    private int golosVisitante; 
    private LocalDate date;
    private IEquipa eqCasa;  
    private IEquipa eqVisitante; 
    private List <Integer> titularesCasa;   
    private List <Integer> titularesVisitante;  
    private List <Integer> emJogoCasa;  //jogadores que estao a jogar
    private List <Integer> emJogoFora;  //jogadores que estao a jogar 
    Map<Integer, Integer> entraSaiCasa = new HashMap<>();           //key é o nº da camisola que entra e value é o nº da camisola do que sai
    Map<Integer, Integer> entraSaiVisitante = new HashMap<>();      //key é o nº da camisola do que entra e value é o nº da camisola do que sai
    private int posicaoBola;    //0- meio-campo; 1-BalizaCasa; 2-BalizaVisitantes; 3-AreaCasa; 4-AreaVisitantes; 5-CantoCasa; 6-CantoVisitantes; 7-GoloCasa; 8-GoloVisitante

    /**
     * Construtor por omissão de Jogo.
     */
    public Jogo () {
        this.golosCasa = 0;
        this.golosVisitante = 0;
        this.date = LocalDate.now();
        this.eqCasa = new Equipa();
        this.eqVisitante = new Equipa();
        this.titularesCasa = new ArrayList<>();
        this.titularesVisitante = new ArrayList<>();
        this.emJogoCasa = new ArrayList<>();
        this.emJogoFora = new ArrayList<>();
        this.entraSaiCasa = new HashMap<>();
        this.entraSaiVisitante = new HashMap<>();
        this.posicaoBola = 0;
    }

    /**
     * Construtor por cópia de Jogo.
     * @param j O jogo pelo qual se copia.
     */
    public Jogo (Jogo j) {
        this.golosCasa = j.golosCasa;
        this.golosVisitante = j.golosVisitante;
        this.date = j.date;
        this.eqCasa = j.getEqCasa();
        this.eqVisitante = j.getEqVisitante();
        setTitularesCasa(j.getTitularesCasa());
        setTitularesVisitante(j.getTitularesVisitante());
        setEmJogoCasa(j.getEmJogoCasa());
        setEmJogoFora(j.getEmJogoFora());
        setEntraSaiCasa(j.getEntraSaiCasa());
        setEntraSaiVisitante(j.getEntraSaiVisitante());
        setPosicaoBola(j.getPosicaoBola());
    }


    /**
     * Construtor parametrizado de Jogo.
     * @param gc Golos da equipa da casa.
     * @param gf Golos da equipa Visitante.
     * @param eqCasa Nome da equipa da casa.
     * @param eqFora Nome da equipa Visitante.
     * @param d Data do Jogo.
     * @param titCasa Lista dos titulares da equipa da casa.
     * @param titVis Lista dos titulares da equipa visitante.
     * @param emJCasa Lista dos jogadores da equipa da casa que estão em jogo .
     * @param emJFora Lista dos jogadores da equipa visitante que estão em jogo.
     * @param entraSaiCasa Map das substituições ocorridas na equipa da casa.
     * @param entraSaiVisitante Map das substituições ocorridas na equipa visitante.
     */
    public Jogo (int gc, int gf,IEquipa eqCasa, IEquipa eqFora, LocalDate d, 
    List<Integer> titCasa, List<Integer> titVis,List<Integer> emJCasa, List<Integer> emJFora, Map<Integer, Integer> entraSaiCasa, Map <Integer,Integer> entraSaiVisitante){
        this.golosCasa = gc;
        this.golosVisitante = gf;
        this.date = d;
        this.eqCasa = eqCasa;
        this.eqVisitante = eqFora;
        this.titularesCasa= titCasa;
        this.titularesVisitante = titVis;
        this.emJogoCasa = emJCasa;
        this.emJogoFora = emJFora;
        this.setEntraSaiCasa(entraSaiCasa);
        this.setEntraSaiVisitante(entraSaiVisitante);
    }

    /**
     * Parser de Jogo
     * @param input String com as informações do Jogo
     * @return Novo Jogo através de um construtor parametrizado.
     */
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


    /**
     * Obter a posição da bola (meio-campo, area, baliza, golo, canto).
     * @return A posição.
     */
    public int getPosicaoBola () {
        return this.posicaoBola;
    }

    /**
     * Definir a nova posição da bola.
     * @param i A nova posição.
     */
    public void setPosicaoBola (int i) {
        this.posicaoBola = i;
    }

    /**
     * Obter o número de golos da equipa da casa.
     * @return O nº de golos.
     */
    public int getGolosCasa() {
        return this.golosCasa;
    }

    /**
     * Alterar o número de golos da equipa da casa.
     * @param golosCasa O novo número de golos.
     */
    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    /**
     * Obter o número de golos da equipa visitante.
     * @return O nº de golos.
     */
    public int getGolosVisitante() {
        return this.golosVisitante;
    }

    /**
     * Alterar o número de golos da equipa visitante.
     * @param golosVisitante O novo número de golos.
     */
    public void setGolosVisitante(int golosVisitante) {
        this.golosVisitante = golosVisitante;
    }

    /**
     * Obter o tempo de jogo decorrido.
     * @return O tempo decorrido.
     */
    public LocalDate getTempoJogo() {
        return this.date;
    }

    /**
     * Definir o tempo de jogo decorrido
     * @param data O tempo a definir.
     */
    public void setTempoJogo(LocalDate data) {
        this.date = data;
    }

    /**
     * Obter a Equipa da casa.
     * @return A Equipa da casa.
     */
    public IEquipa getEqCasa() {
        return this.eqCasa;
    }

    /**
     * Definir o nome da equipa da casa.
     * @param eq O nome da equipa.
     */
    public void setEqCasa(IEquipa eq) {
        this.eqCasa = eq.clone();
    }

    /**
     * Alterar o nome da equipa da casa.
     * @param eqCasa O novo nome.
     */
    public void setNomeEqCasa(IEquipa eqCasa) {
        this.eqCasa = eqCasa;
    }

    /**
     * Obter a Equipa visitante.
     * @return A Equipa Visitante.
     */
    public IEquipa getEqVisitante() {
        return this.eqVisitante;
    }

    /**
     * Definir a Equipa visitante.
     * @param EqVisitante A equipa visitante.
     */
    public void setEqVisitante(IEquipa EqVisitante) {
        this.eqVisitante = EqVisitante;
    }

    /**
     * Obter a lista de titulares da equipa da casa.
     * @return A lista com os titulares da equipa da casa.
     */
    public List<Integer> getTitularesCasa() {
        return this.titularesCasa;
    }
    // public void setTitularesCasa(List<Jogador> titularesCasa) {
    //     for (Jogador j : titularesCasa) {
    //         this.titularesCasa.add(j.clone());
    //     }
    // }

    /**
     * Definir os titulares da equipa da casa.
     * @param titularesCasa A lista dos titulares da casa
     */
    public void setTitularesCasa(List<Integer> titularesCasa) {
        HashMap<Integer,IJogador> e = this.eqCasa.getplantel();
        for (Map.Entry<Integer, IJogador> entry : e.entrySet()) {
            Integer i = entry.getKey();
            IJogador j = entry.getValue();
            if (titularesCasa.contains(i)) j.setTitular(true);
            else j.setTitular(false);
        }
    }

    /**
     * Obter a lista dos números da camisola do titulares da equipa da casa.
     * @return
     */
    public List<Integer> getTitularesVisitante() {
        return this.titularesVisitante;
    }

    /**
     * Definir a lista dos titulares da equipa visitante.
     * @param titularesVisitante Os jogadores da equipa visitante.
     */
    public void setTitularesVisitante(List<Integer> titularesVisitante) {
        HashMap<Integer,IJogador> e = this.eqCasa.getplantel();
        for (Map.Entry<Integer, IJogador> entry : e.entrySet()) {
            Integer i = entry.getKey();
            IJogador j = entry.getValue();
            if (titularesVisitante.contains(i)) j.setTitular(true);
            else j.setTitular(false);
        }
    }


    /**
     * Obter a lista dos jogadores da equipa da casa que estão em jogo
     * @return A lista dos jogadores em jogo.
     */
    public List<Integer> getEmJogoCasa() {
        return this.emJogoCasa;
    }
    public void setEmJogoCasa(List<Integer> emJogoC) {
        this.emJogoCasa= emJogoC;
    }

    /**
     * Obter o map das substituições da equipa da casa.
     * @return As substituições.
     */
    public Map<Integer,Integer> getEntraSaiCasa(){
        Map<Integer,Integer> m = new HashMap<>();
        for (Map.Entry<Integer,Integer> entry : this.entraSaiCasa.entrySet()){
            m.put(entry.getKey(), entry.getValue());
        }
        return m;
    }


    /**
     * Obter a lista dos Jogadores em jogo da equipa visitante.
     * @return A lista dos jogadores.
     */
    public List<Integer> getEmJogoFora() {
        return this.emJogoFora;
    }

    /**
     * Definir a lista dos jogadores em jogo da equipa visitante.
     * @param emJogoF A lista dos jogadores em jogo.
     */
    public void setEmJogoFora(List<Integer> emJogoF) {
        this.emJogoFora = emJogoF;
    }

    /**
     * Definir o map das substituições da equipa da casa.
     * @param entraSaiC Novo map das substituições.
     */
    public void setEntraSaiCasa (Map <Integer, Integer> entraSaiC) {
        for (Map.Entry<Integer, Integer> it : entraSaiC.entrySet()) {
            this.entraSaiCasa.put(it.getKey(), it.getValue());
        }
    }

    /**
     * Obter o map das substituições da equipa visitante
     * @return As substituições.
     */
    public Map<Integer,Integer> getEntraSaiVisitante(){
        Map<Integer,Integer> m = new HashMap<>();
        for (Map.Entry<Integer,Integer> entry : this.entraSaiVisitante.entrySet()){
            m.put(entry.getKey(), entry.getValue());
        }
        return m;
    }

    /**
     * Definir o map das substituições da equipa visitante.
     * @param entraSaiV Novo map das substituições.
     */
    public void setEntraSaiVisitante (Map <Integer, Integer> entraSaiV) {
        for (Map.Entry<Integer, Integer> it : entraSaiV.entrySet()) {
            this.entraSaiVisitante.put(it.getKey(), it.getValue());
        }
    }

    /**
     * Verificar se um objeto é igual ao jogo.
     * @param o O objeto em questão.
     * @return A validade da igualdade.
     */
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
    }

    /**
     * Clone de um Jogo.
     * @return O novo jogo.
     */
    public Jogo clone () {
        return new Jogo(this);
    }

    /**
     * Converter a informação de um Jogo para String.
     * @return A string com toda a informação.
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
}
