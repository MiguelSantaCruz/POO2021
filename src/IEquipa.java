import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public interface IEquipa extends Serializable{
    public float calculaHabilidadeGlobal();
    public static IEquipa parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[1].split("-");
        return new Equipa(campos[0],LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    };
    public HashMap<Integer, IJogador> getplantel();
    public void removeJogador(IJogador jo);
    public void setplantel(HashMap<Integer,IJogador> plantel);
    public String getNome();
    public void insereJogador(IJogador j);
    public void setNome(String nome);
    public LocalDate getDataDeFundação();
    public void setDataDeFundação(LocalDate dataDeFundação);
    public ArrayList<IJogo> getJogosAgendados();
    public void setJogosAgendados(ArrayList<IJogo> jogosAgendados);
    public float getHabilidadeGlobal();
    public void setHabilidadeGlobal(float habilidadeGlobal);
    public IJogador getJogador(int n);
    public boolean equals(Object o);
    public IEquipa clone();
    public String toString();

}
