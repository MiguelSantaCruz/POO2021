import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public interface IParser extends Serializable{
    public void guardaBin(String nomeFicheiro, IModel model)throws FileNotFoundException, IOException ;
    public IModel readBin(String nomeFich) throws IOException, ClassNotFoundException;
    public void parse(IModel model) throws LinhaIncorretaException;
    public static List<String> lerFicheiro(String nomeFich){
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
    public String getPath();
}
