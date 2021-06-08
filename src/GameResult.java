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

    public void efetuarSubstituicao (Jogo jogo, Integer entra, Integer sai, Equipa nomeEquipa) throws JogadorNaoExisteException, EquipaNaoExisteException {
        if (nomeEquipa.equals(jogo.getEqCasa().getNome())) {           //saber se é na equipa da casa ou na visitante que vamos fazer a substituiçao
            if (jogo.getEmJogoCasa().contains(sai)) {                   //se o que sai estiver em jogo
                if (jogo.getEqCasa().getplantel().get(entra).getSuplente()) {       //se o que entra estiver a suplente
                    jogo.getEmJogoCasa().remove(sai);
                    jogo.getEmJogoCasa().add(entra);
                    jogo.getEqCasa().getplantel().get(entra).setSuplente(false);    //o que entra deixa de ser suplente
                    jogo.getEqCasa().getplantel().get(sai).setSuplente(true);       //o que sai passa a suplente
                    jogo.entraSaiCasa.put(entra, sai);        //Map que tem os que entram como key, e os que saem como value
                }
                else {
                    throw new JogadorNaoExisteException("[Eq. Casa] Jogador que entra não está no banco...");
                }
            }
            else {
                throw new JogadorNaoExisteException("[Eq. Casa] Jogador que sai não está em jogo...");
            }
        }

        else if (nomeEquipa.equals(jogo.getEqVisitante().getNome())) {         //caso seja na equipa Visitante que temos que fazer a substituiçao
            if (jogo.getEmJogoFora().contains(sai)) {
                if (jogo.getEqVisitante().getplantel().get(entra).getSuplente()) {     //se o que entra estiver a suplente
                    jogo.getEmJogoFora().remove(sai);
                    jogo.getEmJogoFora().add(entra);
                    jogo.getEqVisitante().getplantel().get(entra).setSuplente(false);       //o que entra passa a estar em Jogo
                    jogo.getEqVisitante().getplantel().get(sai).setSuplente(true);      //o que sai passa a suplente
                    jogo.entraSaiVisitante.put(entra, sai);
                }
                else {
                    throw new JogadorNaoExisteException("[Eq. Visitante] Jogador que entra não está no banco...");
                }
            }
            else {
                throw new JogadorNaoExisteException("[Eq. Visitante] Jogador que sai não está em jogo...");
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
