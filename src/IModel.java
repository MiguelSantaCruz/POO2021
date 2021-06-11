import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IModel extends Serializable{
    public void addEquipa(IEquipa e);
    public void removeEquipa(IEquipa e);
    public void addJogo(IJogo j);
    public void removeJogo(IJogo j);
    public Map<String,IEquipa> getEquipas();
    public List<IJogo> getJogos();
}
