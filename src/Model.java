import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model implements IModel{
    private Map<String, IEquipa> equipas;
    private List<IJogo> jogos;

    /**
     * Construtor vazio de model
     */
    public Model(){
        this.equipas = new HashMap<>();
        this.jogos = new ArrayList<>();
    }

    /**
     * Função que permite adicionar uma equipa ao modelo
     * @param e Equipa a adicionar
     */
    public void addEquipa(IEquipa e){
        this.equipas.put(e.getNome().toLowerCase(), e.clone());
    }

    /**
     * Função que permite remover uma equipa do modelo
     * @param e Equipa a remover
     */
    public void removeEquipa(IEquipa e){
        this.equipas.remove(e.getNome().toLowerCase());
    }   

    /**
     * Função que permite adicionar um jogo ao modelo
     * @param j Jogo a adicionar
     */
    public void addJogo(IJogo j){
        this.jogos.add(j);
    }

    /**
     * Função que permite remover um jogo ao modelo
     * @param j Jogo a remover
     */
    public void removeJogo(IJogo j){
        this.jogos.remove(j);
    }

    /**
     * Obter o conjunto das equipas.
     * @return O novo hashmap com as equipas.
     */
    public Map<String,IEquipa> getEquipas(){
        Map <String,IEquipa> copy = new HashMap<>();
        for (Map.Entry<String, IEquipa> equipaMap: this.equipas.entrySet()) {
            copy.put(equipaMap.getKey(), equipaMap.getValue());
        }
        return copy;
    }

    /**
     * Obter a lista dos jogos.
     * @return A lista dos jogos.
     */
    public List<IJogo> getJogos(){
        ArrayList<IJogo> l = new ArrayList<>();
        for (IJogo jogo : this.jogos) {
            l.add(jogo);
        }
        return l;
    }

}