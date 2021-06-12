import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser implements IParser{
    private String path;

    /**
     * Construtor vazio de um Parser.
     */
    public Parser(){
        this.path = "../POO2021/final.txt";
    }

    /**
     * Função que permite guardar o estado do modelo num ficheiro binário
     */
    public void guardaBin(String nomeFicheiro,IModel model) throws FileNotFoundException, IOException {
        FileOutputStream bf = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(bf);
        oos.writeObject(model);
        oos.flush();
        oos.close();
    }

    /**
     * Função que permite ler um ficheiro binário com um estado da aplicação 
     */
    public IModel readBin(String nomeFich) throws IOException, ClassNotFoundException{
        FileInputStream bf = new FileInputStream(nomeFich);
        ObjectInputStream ois = new ObjectInputStream(bf);
        IModel m = (IModel) ois.readObject();
        ois.close();
        return m;
    }
    /**
     * Método para fazer o parse das informações.
     * @throws LinhaIncorretaException Quando ocorre algum problema com a linha em questão.
     */
    public void parse(IModel model) throws LinhaIncorretaException {
        List<String> linhas = lerFicheiro(getPath());
        Equipa ultima = null; Jogador j = null;
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Equipa":
                    Equipa e = Equipa.parse(linhaPartida[1]);
                    model.addEquipa(e);
                    ultima = e;
                    break;
                case "Guarda-Redes":
                    j = Guarda_Redes.parse(linhaPartida[1]);
                    //jogadores.put(j.getNomeAtleta(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1]);
                    //jogadores.put(j.getNomeAtleta(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1]);
                    //jogadores.put(j.getNomeAtleta(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Lateral":
                    j = Lateral.parse(linhaPartida[1]);
                    //jogadores.put(j.getNomeAtleta(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "ST":
                    j = Avancado.parse(linhaPartida[1]);
                    //jogadores.put(j.getNomeAtleta(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1]);
                    //jogadores.put(j.getNomeAtleta(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
               case "Jogo":
                    Jogo jo = Jogo.parse(linhaPartida[1]);
                    model.addJogo(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();

            }
        }
    }

    /**
     * Método para ler as informações de um ficheiro para uma lista.
     * @param nomeFich O nome do ficheiro.
     * @return A lista das linhas lidas.
     */
    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    /**
     * Obter o path para o ficheiro com todas as informações.
     * @return O path para o ficheiro.
     */
    public String getPath(){
        return this.path;
    }
}
