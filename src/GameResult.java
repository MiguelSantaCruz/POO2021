import java.util.Random;

public class GameResult {
    private double overallEquipa1;
    private double overallEquipa2;

    public double getOverallEquipa1(Equipa eq1) {
        return eq1.getHabilidadeGlobal();
    }

    public double getOverallEquipa2(Equipa eq2) {
        return eq2.getHabilidadeGlobal();
    }

    public static void efetuarSubstituicao (Jogo jogo, Integer entra, Integer sai, Equipa nomeEquipa) throws JogadorNaoExisteException, EquipaNaoExisteException {
        if (nomeEquipa.getNome().equals(jogo.getEqCasa().getNome())) {           //saber se é na equipa da casa ou na visitante que vamos fazer a substituiçao
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

        else if (nomeEquipa.getNome().equals(jogo.getEqVisitante().getNome())) {         //caso seja na equipa Visitante que temos que fazer a substituiçao
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



    public static int calculaJogada (Jogo j) {
        Random r = new Random();
        int rand;
        rand = r.nextInt(100);

        double habCasa = j.getEqCasa().getHabilidadeGlobal();
        double habVis = j.getEqVisitante().getHabilidadeGlobal();
        habCasa *= 1.1;  //equipa da casa tem um boost de habilidade de 10%
        double dif = habCasa - habVis;
        int res=0;
        int golo = 0;

        switch (j.getPosicaoBola()) {
            case 0:
                res = bolaMeioCampo(rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 1:
                res = bolaBaliza(rand,habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 2:
                res = bolaBaliza(rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 3:
                res = bolaArea(rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 4:
                res = bolaArea(rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 5:
                res = bolaCanto(rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 6:
                res = bolaCanto(rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 7:
                j.setGolosCasa(j.getGolosCasa()+1);
                System.out.println("\u001B[31m" + j.getEqCasa().getNome() + " " + j.getGolosCasa() + " : " + j.getGolosVisitante() + " " + j.getEqVisitante().getNome()+"\u001B[0m");
                j.setPosicaoBola(0);
                System.out.print("Bola ao meio campo (posse do " + j.getEqVisitante().getNome() + ")");
                break;
            case 8:
                j.setGolosVisitante(j.getGolosVisitante()+1);
                System.out.println("\u001B[31m" + j.getEqCasa().getNome() + " " + j.getGolosCasa() + " : " + j.getGolosVisitante() + " " + j.getEqVisitante().getNome()+"\u001B[0m");
                j.setPosicaoBola(0);
                System.out.print("Bola ao meio campo (posse do " + j.getEqCasa().getNome() + ")");
                break;
            case 9:
                res = bolaMeioCampo(rand, habVis, habCasa, j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 10:
                res = bolaMeioCampo(rand, habVis, habCasa, j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
        }
        return golo;

    }

    public static boolean equipaMaisForte (double habVis, double habCasa) {
        double diferencaHab;
        boolean eqCasaMaisForte;
        if (habCasa > habVis) {
            return true;
        }
        else {
            return false;
        }
    }

    //decidir o que acontece se a bola estiver no meio campo
    public static int bolaMeioCampo (int rand, double habVis, double habCasa, String equipaCasaNome, String equipaVisitanteNome) {                    //20% baliza; 35% area; 30%canto; 5% golo
        double diferencaHab = ( (habCasa>habVis) ? (habCasa-habVis) : (habVis-habCasa) );
        boolean eqCasaMaisForte = equipaMaisForte(habVis, habCasa);


        if (rand >= 0 && rand <= 20 && eqCasaMaisForte) {
            System.out.print("Remate à baliza do " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            return 1;
        }
        else if (rand >= 0 && rand <= 20 && (!eqCasaMaisForte)) {
            System.out.print("Remate à baliza do " + equipaVisitanteNome);
            return 2;
        }

        else if (rand > 20 && rand <= 65 && eqCasaMaisForte) {
            System.out.print("Passe para a área do " + equipaCasaNome);
            return 3;
        }
        else if (rand > 20 && rand <= 65 && !eqCasaMaisForte) {
            System.out.print("Passe para a área do " + equipaVisitanteNome);
            return 4;
        }

        else if (rand > 65 && rand <= 95 && eqCasaMaisForte) {
            System.out.print("Canto para o " + equipaCasaNome);
            return 5;
        }
        else if (rand > 65 && rand <= 95 && !eqCasaMaisForte) {
            System.out.print("Canto para o " + equipaVisitanteNome);
            return 6;
        }

        else if (rand > 95 && eqCasaMaisForte) {
            System.out.print("Golo do " + equipaCasaNome);
            return 7;
        }
        else if (rand > 95 && !eqCasaMaisForte) {
            System.out.print("Golo do " + equipaVisitanteNome);
            return 8;
        }
        return 0;
    }


    public static int bolaCanto (int rand, double habVis, double habCasa, String equipaCasaNome, String equipaVisitanteNome) {                // 10% golo; 30% baliza; 30% area; 15% canto; 15% meio campo
        double diferencaHab = ( (habCasa>habVis) ? (habCasa-habVis) : (habVis-habCasa) );
        boolean eqCasaMaisForte = equipaMaisForte(habVis, habCasa);


        if (rand >= 0 && rand <= 30 && eqCasaMaisForte) {
            System.out.print("Remate à baliza por parte do " + equipaCasaNome);
            return 1;
        }
        else if (rand >= 0 && rand <= 30 && (!eqCasaMaisForte)) {
            System.out.print("Remate à baliza por parte do "+ equipaVisitanteNome);
            return 2;
        }

        else if (rand > 30 && rand <= 60 && eqCasaMaisForte) {
            System.out.print("Passe para a área (posse do " + equipaCasaNome +")");
            return 3;
        }
        else if (rand > 30 && rand <= 60 && !eqCasaMaisForte) {
            System.out.print("Passe para a área (posse do " + equipaVisitanteNome +")");
            return 4;
        }

        else if (rand > 60 && rand <= 75 && eqCasaMaisForte) {
            System.out.print("Canto para o " + equipaCasaNome);
            return 5;
        }
        else if (rand > 60 && rand <= 75 && !eqCasaMaisForte) {
            System.out.print("Canto para o " + equipaVisitanteNome);
            return 6;
        }
        else if (rand > 75 && rand <= 90 && eqCasaMaisForte) {
            System.out.print("Bola para o meio campo (posse do " + equipaCasaNome + ")");
            return 9;
        }
        else if (rand > 75 && rand <= 90 && !eqCasaMaisForte) {
            System.out.print("Bola para o meio campo (posse do " + equipaVisitanteNome + ")");
            return 10;
        }

        else if (rand > 90 && eqCasaMaisForte) {
            System.out.print("Golo do " + equipaCasaNome);
            return 7;
        }
        else if (rand > 90 && !eqCasaMaisForte) {
            System.out.print("Golo do " + equipaVisitanteNome);
            return 8;
        }
        return 0;
    }



    public static int bolaArea (int rand, double habVis, double habCasa,  String equipaCasaNome, String equipaVisitanteNome) {                // 10% golo; 30% baliza; 5% area; 25% canto; 30% meio campo
        double diferencaHab = ( (habCasa>habVis) ? (habCasa-habVis) : (habVis-habCasa) );
        boolean eqCasaMaisForte = equipaMaisForte(habVis, habCasa);


        if (rand >= 0 && rand <= 30 && eqCasaMaisForte) {
            System.out.print("Remate à baliza por parte do " + equipaCasaNome);
            return 1;
        }
        else if (rand >= 0 && rand <= 30 && (!eqCasaMaisForte)) {
            System.out.print("Remate à baliza por parte do " + equipaVisitanteNome);
            return 2;
        }

        else if (rand > 30 && rand <= 35 && eqCasaMaisForte) {
            System.out.print("Passe para a área do " + equipaVisitanteNome + "(posse do " + equipaCasaNome +")");
            return 3;
        }
        else if (rand > 30 && rand <= 35 && !eqCasaMaisForte) {
            System.out.print("Passe para a área do " + equipaCasaNome + "(posse do " + equipaVisitanteNome +")");
            return 4;
        }

        else if (rand > 35 && rand <= 60 && eqCasaMaisForte) {
            System.out.print("Canto para o " + equipaCasaNome);
            return 5;
        }
        else if (rand > 35 && rand <= 60 && !eqCasaMaisForte) {
            System.out.print("Canto para o " + equipaVisitanteNome);
            return 6;
        }
        else if (rand > 60 && rand <= 90 && eqCasaMaisForte) {
            System.out.print("Bola para o meio campo (posse do " + equipaCasaNome +")");
            return 9;
        }
        else if (rand > 60 && rand <= 90 && !eqCasaMaisForte) {
            System.out.print("Bola para o meio campo (posse do " + equipaVisitanteNome +")");
            return 10;
        }

        else if (rand > 90 && eqCasaMaisForte) {
            System.out.print("Golo " + equipaCasaNome);
            return 7;
        }
        else if (rand > 90 && !eqCasaMaisForte) {
            System.out.print("Golo " + equipaVisitanteNome);
            return 8;
        }
        return 0;
    }


    public static int bolaBaliza (int rand, double habVis, double habCasa, String equipaCasaNome, String equipaVisitanteNome) {                // 5% golo; 10% baliza; 30% area; 25% canto; 30% meio campo
        double diferencaHab = ( (habCasa>habVis) ? (habCasa-habVis) : (habVis-habCasa) );
        boolean eqCasaMaisForte = equipaMaisForte(habVis, habCasa);

        if (rand >= 0 && rand <= 10 && eqCasaMaisForte) {
            System.out.print("Remate à baliza por parte do " + equipaCasaNome);
            return 1;
        }
        else if (rand >= 0 && rand <= 10 && (!eqCasaMaisForte)) {
            System.out.print("Remate à baliza por parte do " + equipaVisitanteNome);
            return 2;
        }
        else if (rand > 10 && rand <= 40 && eqCasaMaisForte) {
            System.out.print("Passe para a área" + "(posse do " + equipaCasaNome +")");
            return 3;
        }
        else if (rand > 10 && rand <= 40 && !eqCasaMaisForte) {
            System.out.print("Passe para a área" + "(posse do " + equipaVisitanteNome +")");
            return 4;
        }
        else if (rand > 40 && rand <= 65 && eqCasaMaisForte) {
            System.out.print("Canto para o " + equipaCasaNome);
            return 5;
        }
        else if (rand > 40 && rand <= 65 && !eqCasaMaisForte) {
            System.out.print("Canto para o " + equipaVisitanteNome);
            return 6;
        }
        else if (rand > 65 && rand <= 95 && eqCasaMaisForte) {
            System.out.print("Bola para o meio campo (posse do " + equipaCasaNome +")");
            return 9;
        }
        else if (rand > 65 && rand <= 95 && !eqCasaMaisForte) {
            System.out.print("Bola para o meio campo (posse do " + equipaVisitanteNome +")");
            return 10;
        }
        else if (rand > 95 && eqCasaMaisForte) {
            System.out.print("Golo do " + equipaCasaNome);
            return 7;
        }
        else if (rand > 95 && !eqCasaMaisForte) {
            System.out.print("Golo do " + equipaVisitanteNome);
            return 8;
        }
        return 0;
    }

}
