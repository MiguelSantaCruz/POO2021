import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IJogo extends Serializable{
    public static IJogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        IEquipa eC = new Equipa(campos[0],LocalDate.now());
        IEquipa eV = new Equipa(campos[1],LocalDate.now());
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
    public int getPosicaoBola ();
    public void setPosicaoBola (int i);
    public int getGolosCasa();
    public void setGolosCasa(int golosCasa);
    public int getGolosVisitante();
    public void setGolosVisitante(int golosVisitante);
    public LocalDate getTempoJogo() ;
    public void setTempoJogo(LocalDate data);
    public IEquipa getEqCasa();
    public void setEqCasa(IEquipa eq);
    public void setNomeEqCasa(IEquipa eqCasa);
    public IEquipa getEqVisitante();
    public void setEqVisitante(IEquipa EqVisitante);
    public List<Integer> getTitularesCasa();
    public void setTitularesCasa(List<Integer> titularesCasa);
    public List<Integer> getTitularesVisitante();
    public void setTitularesVisitante(List<Integer> titularesVisitante);
    public List<Integer> getEmJogoCasa();
    public void setEmJogoCasa(List<Integer> emJogoC);
    public List<Integer> getEmJogoFora();
    public void setEmJogoFora(List<Integer> emJogoF);
    public Map<Integer, Integer> getEntraSaiCasa ();
    public void setEntraSaiCasa (Map <Integer, Integer> entraSaiC);
    public Map<Integer, Integer> getEntraSaiVisitante ();
    public void setEntraSaiVisitante (Map <Integer, Integer> entraSaiV);
    public boolean equals (Object o);
    public IJogo clone();
    public String toString();
}
