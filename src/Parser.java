import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    public static void parse() throws LinhaIncorretaException {
        List<String> linhas = lerFicheiro("final.txt");
        Map<String, Equipa> equipas = new HashMap<>(); //nome, equipa
        //Map<String, Jogador> jogadores = new HashMap<>(); //numero, jogador
        List<Jogo> jogos = new ArrayList<>();
        Equipa ultima = null; Jogador j = null;
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Equipa":
                    Equipa e = Equipa.parse(linhaPartida[1]);
                    equipas.put(e.getNome(), e);
                    ultima = e;
                    break;
                case "GK":
                    j = Guarda_Redes.parse(linhaPartida[1]);
                    //jogadores.put(j.getNomeAtleta(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "CB":
                    j = Defesa.parse(linhaPartida[1]);
                    //jogadores.put(j.getNomeAtleta(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "CDM":
                    j = Medio.parse(linhaPartida[1]);
                    //jogadores.put(j.getNomeAtleta(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "RB":
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
               case "Jogo":
                    Jogo jo = Jogo.parse(linhaPartida[1]);
                    jogos.add(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();

            }
        }
        // if ( equipas.containsKey("Boavista FC") )
        //      System.out.println("Valor da Chave"+
        //       " = "+equipas.get("Boavista FC"));
        for (Equipa e: equipas.values()){
            System.out.println(e.toString());
        }
        for (Jogo jog: jogos){
            System.out.println(jog.toString());
        }


    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }


}
