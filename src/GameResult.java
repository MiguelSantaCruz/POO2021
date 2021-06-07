import java.util.ArrayList;
import java.util.List;

public class GameResult {
    private double overallEquipa1;
    private double overallEquipa2;

    public double getOverallEquipa1(Equipa eq1) {
        return eq1.getHabilidadeGlobal();
    }

    public double getOverallEquipa2(Equipa eq2) {
        return eq2.getHabilidadeGlobal();
    }

    public void efetuarSubstituicao (Jogo jogo, Jogador entra, Jogador sai, String nomeEquipa) throws JogadorNaoExisteException {

        if (nomeEquipa.equals(jogo.getNomeEqCasa())) {           //saber se é na equipa da casa ou na visitante que vamos fazer a substituiçao
            List<Jogador> titulares;
            List<Jogador> suplentes;
            suplentes = jogo.getSuplentesCasa();
            titulares = jogo.getTitularesCasa();
            if (titulares.contains(sai)) {
                titulares.remove(sai);
                if (suplentes.contains(entra)) {
                    titulares.add(entra);
                    jogo.substituicoesCasa.remove(sai.getNumeroJogador());
                    jogo.entraSaiCasa.put(entra.getNumeroJogador(), sai.getNumeroJogador());        //Map que tem os que entram como key, e os que saem como value
                }
                else {
                    throw new JogadorNaoExisteException("[Eq. Casa] Jogador que entra não existe...");
                }
            }
            else {
                throw new JogadorNaoExisteException("[Eq. Casa] Jogador que sai não existe...");
            }
        }
        else if (nomeEquipa.equals(jogo.getNomeEqVisitante())) {         //caso seja na equipa Visitante que temos que fazer a substituiçao
            List<Jogador> titulares;
            List<Jogador> suplentes;
            suplentes = jogo.getSuplentesVisitante();
            titulares = jogo.getTitularesVisitante();
            if (titulares.contains(sai)) {
                titulares.remove(sai);
                if (suplentes.contains(entra)) {
                    titulares.add(entra);
                    jogo.substituicoesFora.remove(sai.getNumeroJogador());             //verificar se é (sai, entra) ou (entra, sai) -> eu deixei (entra,sai)
                    jogo.entraSaiVisitante.put(entra.getNumeroJogador(), sai.getNumeroJogador());
                }
                else {
                    throw new JogadorNaoExisteException("[Eq. Visitante] Jogador que entra não existe...");
                }
            }
            else {
                throw new JogadorNaoExisteException("[Eq. Visitante] Jogador que sai não existe...");
            }
        }
        else {
            throw new JogadorNaoExisteException();
        }
    }



}
