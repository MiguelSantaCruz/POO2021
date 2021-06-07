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

    public void efetuarSubstituicao (Jogo jogo, Jogador entra, Jogador sai, String nomeEquipa) throws JogadorNaoExisteException, EquipaNaoExisteException {
        if (nomeEquipa.equals(jogo.getNomeEqCasa())) {           //saber se é na equipa da casa ou na visitante que vamos fazer a substituiçao
            if (jogo.getEmJogoCasa().contains(sai)) {
                if (jogo.getSuplentesCasa().contains(entra)) {
                    jogo.getEmJogoCasa().remove(sai);
                    jogo.getEmJogoCasa().remove(sai);
                    jogo.getEmJogoCasa().add(entra);
                    jogo.getSuplentesCasa().remove(entra);
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
            if (jogo.getEmJogoFora().contains(sai)) {
                if (jogo.getSuplentesVisitante().contains(entra)) {
                    jogo.getEmJogoFora().remove(sai);
                    jogo.getEmJogoFora().remove(sai);
                    jogo.getEmJogoFora().add(entra);
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
            throw new EquipaNaoExisteException("Equipa não existe...");
        }
    }


//NOTA:: Esta função ainda está muito basica e tem que ser melhorada. Isto é so uma versao inicial
    //pensei em ver o overall da defesa de cada uma das equipas para influenciar o nº de golos
    public String calculaResultado (Jogo j, Equipa casa, Equipa visitante, int golosCasa, int golosFora) {
        double habCasa = j.calculaHabilidadeCasa();
        double habVis = j.calculaHabilidadeVisitantes();
        habCasa *= 1.08;  //equipa da casa tem um boost de habilidade de 8%
        double dif = habCasa - habVis;
        dif = dif % 1.5;  //1.5 é um numero random -> deixei 1.5 para nao ser muito grande
    }
}
