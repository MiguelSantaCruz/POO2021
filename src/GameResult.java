import java.util.Random;

public class GameResult {
    /**
     * Método para obter a habilidade de uma equipa.
     * @param eq1 A equipa.
     * @return O overall da equipa.
     */
    public double getOverallEquipa1(IEquipa eq1) {
        return eq1.getHabilidadeGlobal();
    }


    /**
     * Efetuar uma substituição (um jogador entra e outro sai) na Equipa, num determinado jogo.
     * @param jogo  O jogo em que se faz a substituição.
     * @param entra O numero da camisola do Jogador que entra em campo.
     * @param sai O numero da camisola do Jogador que sai.
     * @param nomeEquipa O nome da Equipa em que queremos efetuar a substituição.
     * @throws JogadorNaoExisteException Exceção lançada quando o jogador que é passado como argumento não pertence à equipa.
     * @throws EquipaNaoExisteException Exceção lançada quando a equipa não corresponde à equipa da casa, nem à Visitante.
     */
    public static void efetuarSubstituicao (IJogo jogo, Integer entra, Integer sai, IEquipa nomeEquipa) throws JogadorNaoExisteException, EquipaNaoExisteException {
        if (nomeEquipa.getNome().equals(jogo.getEqCasa().getNome())) {           //saber se é na equipa da casa ou na visitante que vamos fazer a substituiçao
            if (jogo.getEmJogoCasa().contains(sai)) {                   //se o que sai estiver em jogo
                if (jogo.getEqCasa().getplantel().get(entra).getSuplente()) {       //se o que entra estiver a suplente
                    jogo.getEmJogoCasa().remove(sai);
                    jogo.getEmJogoCasa().add(entra);
                    jogo.getEqCasa().getplantel().get(entra).setSuplente(false);    //o que entra deixa de ser suplente
                    jogo.getEqCasa().getplantel().get(sai).setSuplente(true);       //o que sai passa a suplente
                    jogo.getEntraSaiCasa().put(entra, sai);        //Map que tem os que entram como key, e os que saem como value
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
                    jogo.getEntraSaiVisitante().put(entra, sai);
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



    /**
     * Calcula a próxima posição da bola com base em probabilidades, números aleatórios (fator de sorte) e na posição atual.
     * @param j O jogo em questão.
     * @return O valor correspondente ao resultado daquela jogada (Golo, canto, remate à baliza, etc...)
     */
    public static int calculaJogada (IJogo j,IView view) {
        Random r = new Random();
        int rand;
        rand = r.nextInt(100);

        double habCasa = j.getEqCasa().getHabilidadeGlobal();
        double habVis = j.getEqVisitante().getHabilidadeGlobal();
        habCasa *= 1.1;  //equipa da casa tem um boost de habilidade de 10%
        int res=0;
        int golo = 0;

        switch (j.getPosicaoBola()) {
            case 0:
                res = bolaMeioCampo(view,rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 1:
                res = bolaBaliza(view,rand,habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 2:
                res = bolaBaliza(view,rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 3:
                res = bolaArea(view,rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 4:
                res = bolaArea(view,rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 5:
                res = bolaCanto(view,rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 6:
                res = bolaCanto(view,rand, habVis, habCasa,j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 7:
                j.setGolosCasa(j.getGolosCasa()+1);
                view.mostraMensagem("\u001B[31m" + j.getEqCasa().getNome() + " " + j.getGolosCasa() + " : " + j.getGolosVisitante() + " " + j.getEqVisitante().getNome()+"\u001B[0m");
                j.setPosicaoBola(0);    
                view.mostraMensagemSemNewLine("Bola ao meio campo (posse do " + j.getEqVisitante().getNome() + ")");
                break;
            case 8:
                j.setGolosVisitante(j.getGolosVisitante()+1);
                view.mostraMensagem("\u001B[31m" + j.getEqCasa().getNome() + " " + j.getGolosCasa() + " : " + j.getGolosVisitante() + " " + j.getEqVisitante().getNome()+"\u001B[0m");
                j.setPosicaoBola(0);
                view.mostraMensagemSemNewLine("Bola ao meio campo (posse do " + j.getEqCasa().getNome() + ")");
                break;
            case 9:
                res = bolaMeioCampo(view,rand, habVis, habCasa, j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
            case 10:
                res = bolaMeioCampo(view, rand, habVis, habCasa, j.getEqCasa().getNome(),j.getEqVisitante().getNome());
                j.setPosicaoBola(res);
                break;
        }
        return golo;

    }

    /**
     * Verifica se a equipa da casa é mais forte a nível global.
     * @param habVis Habilidade da equipa Visitante.
     * @param habCasa Habilidade da equipa da Casa.
     * @return True se a equipa da casa tiver maior overall, false caso contrário.
     */
    public static boolean equipaMaisForte (double habVis, double habCasa) {
        if (habCasa > habVis) return true;
        else return false;
    }

    /**
     * Decidir o que acontece se a bola estiver no meio campo
     * @param rand Número aleatório correspondente a um fator de sorte.
     * @param habVis Habilidade da equipa Visitante.
     * @param habCasa Habilidade da equipa da Casa.
     * @return Próxima posição da bola
     */
    public static int bolaMeioCampo (IView view,int rand, double habVis, double habCasa, String equipaCasaNome, String equipaVisitanteNome) {                    //20% baliza; 35% area; 30%canto; 5% golo
        double diferencaHab = ( (habCasa>habVis) ? (habCasa-habVis) : (habVis-habCasa) );
        boolean eqCasaMaisForte = equipaMaisForte(habVis, habCasa);
        Random r = new Random();
        int random = r.nextInt(100);
        //Se a deferença entre as habilidades das equipas for 0 então a probabilidade de algo acontecer é 50%
        //Se a diferença entre as habilidades for 100 então a melhor equipa joja 100% das vezes e ganha 100% das vezes
        double probabilidade = 0.5* Math.abs(diferencaHab)+50;

        if (rand >= 0 && rand <= 20) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaVisitanteNome);
            else if(random > probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaVisitanteNome);
            else view.mostraMensagemSemNewLine("Remate à baliza do " + equipaCasaNome);
            return 1;
        }
        else if (rand > 20 && rand <= 65) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaVisitanteNome);
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaCasaNome);
            else view.mostraMensagemSemNewLine("Passe para a área do " + equipaVisitanteNome);
            return 3;
        }

        else if (rand > 65 && rand <= 95) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaVisitanteNome);
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaCasaNome);
            else view.mostraMensagemSemNewLine("Canto para o " + equipaVisitanteNome);
            return 5;
        }
        else if (rand > 95) {
            if(random < probabilidade && eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaCasaNome); 
                return 7;
            } else if (random < probabilidade && !eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaVisitanteNome);
                return 8;
            } else if(random > probabilidade && !eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaCasaNome);
                return 7;
            } else {
                view.mostraMensagemSemNewLine("Golo do " + equipaVisitanteNome);
                return 8;
            }
        }
        return 0;
    }


    /**
     * Calcula a próxima posição da bola quando é batido um canto.
     * @param rand Número aleatório correspondente a um fator de sorte.
     * @param habVis Habilidade da equipa Visitante.
     * @param habCasa Habilidade da equipa da Casa.
     * @return Próxima posição da bola.
     */
    public static int bolaCanto (IView view,int rand, double habVis, double habCasa, String equipaCasaNome, String equipaVisitanteNome) {                // 10% golo; 30% baliza; 30% area; 15% canto; 15% meio campo
        double diferencaHab = ( (habCasa>habVis) ? (habCasa-habVis) : (habVis-habCasa) );
        boolean eqCasaMaisForte = equipaMaisForte(habVis, habCasa);
        Random r = new Random();
        int random = r.nextInt(100);
        //Se a deferença entre as habilidades das equipas for 0 então a probabilidade de algo acontecer é 50%
        //Se a diferença entre as habilidades for 100 então a melhor equipa joja 100% das vezes e ganha 100% das vezes
        double probabilidade = 0.5* Math.abs(diferencaHab)+50;


        if (rand >= 0 && rand <= 30) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaVisitanteNome);
            else if(random > probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaVisitanteNome);
            else view.mostraMensagemSemNewLine("Remate à baliza do " + equipaCasaNome);
            return 1;
        }
        else if (rand > 30 && rand <= 60) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaVisitanteNome);
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaCasaNome);
            else view.mostraMensagemSemNewLine("Passe para a área do " + equipaVisitanteNome);
            return 3;
        }
        else if (rand > 60 && rand <= 75) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaVisitanteNome);
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaCasaNome);
            else view.mostraMensagemSemNewLine("Canto para o " + equipaVisitanteNome);
            return 5;
        }
        else if (rand > 75 && rand <= 95) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaCasaNome + ")");  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaVisitanteNome + ")");
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaCasaNome + ")");
            else view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaVisitanteNome + ")");
            return 9;
        }
        else if (rand > 95) {
            if(random < probabilidade && eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaCasaNome); 
                return 7;
            } else if (random < probabilidade && !eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaVisitanteNome);
                return 8;
            } else if(random > probabilidade && !eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaCasaNome);
                return 7;
            } else {
                view.mostraMensagemSemNewLine("Golo do " + equipaVisitanteNome);
                return 8;
            }
        }
        return 0;
    }



    /**
     * Calcula a próxima posição da bola quando a bola está na área
     * @param rand Número aleatório correspondente a um fator de sorte.
     * @param habVis Habilidade da equipa Visitante.
     * @param habCasa Habilidade da equipa da Casa.
     * @return Próxima posição da bola.
     */
    public static int bolaArea (IView view,int rand, double habVis, double habCasa,  String equipaCasaNome, String equipaVisitanteNome) {                // 10% golo; 30% baliza; 5% area; 25% canto; 30% meio campo
        double diferencaHab = ( (habCasa>habVis) ? (habCasa-habVis) : (habVis-habCasa) );
        boolean eqCasaMaisForte = equipaMaisForte(habVis, habCasa);
        Random r = new Random();
        int random = r.nextInt(100);
        //Se a deferença entre as habilidades das equipas for 0 então a probabilidade de algo acontecer é 50%
        //Se a diferença entre as habilidades for 100 então a melhor equipa joja 100% das vezes e ganha 100% das vezes
        double probabilidade = 0.5* Math.abs(diferencaHab)+50;

        if (rand >= 0 && rand <= 30) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaVisitanteNome);
            else if(random > probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaVisitanteNome);
            else view.mostraMensagemSemNewLine("Remate à baliza do " + equipaCasaNome);
            return 1;
        }
        else if (rand > 30 && rand <= 35) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaVisitanteNome);
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaCasaNome);
            else view.mostraMensagemSemNewLine("Passe para a área do " + equipaVisitanteNome);
            return 3;
        }
        else if (rand > 35 && rand <= 60) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaVisitanteNome);
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaCasaNome);
            else view.mostraMensagemSemNewLine("Canto para o " + equipaVisitanteNome);
            return 5;
        }
        else if (rand > 60 && rand <= 95) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaCasaNome + ")");  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaVisitanteNome + ")");
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaCasaNome + ")");
            else view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaVisitanteNome + ")");
            return 9;
        }
        else if (rand > 95) {
            if(random < probabilidade && eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaCasaNome); 
                return 7;
            } else if (random < probabilidade && !eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaVisitanteNome);
                return 8;
            } else if(random > probabilidade && !eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaCasaNome);
                return 7;
            } else {
                view.mostraMensagemSemNewLine("Golo do " + equipaVisitanteNome);
                return 8;
            }
        }
        return 0;
    }


    /**
     * Calcula a próxima posição da bola quando a bola está na Baliza (posse do guarda-redes).
     * @param rand Número aleatório correspondente a um fator de sorte.
     * @param habVis Habilidade da equipa Visitante.
     * @param habCasa Habilidade da equipa da Casa.
     * @return Próxima posição da bola.
     */
    public static int bolaBaliza (IView view,int rand, double habVis, double habCasa, String equipaCasaNome, String equipaVisitanteNome) {                // 5% golo; 10% baliza; 30% area; 25% canto; 30% meio campo
        double diferencaHab = ( (habCasa>habVis) ? (habCasa-habVis) : (habVis-habCasa) );
        boolean eqCasaMaisForte = equipaMaisForte(habVis, habCasa);
        Random r = new Random();
        int random = r.nextInt(100);

        //Se a deferença entre as habilidades das equipas for 0 então a probabilidade de algo acontecer é 50%
        //Se a diferença entre as habilidades for 100 então a melhor equipa joja 100% das vezes e ganha 100% das vezes
        double probabilidade = 0.5* Math.abs(diferencaHab)+50;

        if (rand >= 0 && rand <= 10) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaVisitanteNome);
            else if(random > probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Remate à baliza do " + equipaVisitanteNome);
            else view.mostraMensagemSemNewLine("Remate à baliza do " + equipaCasaNome);
            return 1;
        }
        else if (rand > 10 && rand <= 40) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaVisitanteNome);
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Passe para a área do " + equipaCasaNome);
            else view.mostraMensagemSemNewLine("Passe para a área do " + equipaVisitanteNome);
            return 3;
        }
        else if (rand > 40 && rand <= 65) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaCasaNome);  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaVisitanteNome);
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Canto para o " + equipaCasaNome);
            else view.mostraMensagemSemNewLine("Canto para o " + equipaVisitanteNome);
            return 5;
        }
        else if (rand > 65 && rand <= 95) {
            if(random < probabilidade && eqCasaMaisForte) view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaCasaNome + ")");  //remate à baliza significa que nao é golo e que o guarda-redes amarrou a bola
            else if (random < probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaVisitanteNome + ")");
            else if(random > probabilidade && !eqCasaMaisForte) view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaCasaNome + ")");
            else view.mostraMensagemSemNewLine("Bola para o meio campo (posse do " + equipaVisitanteNome + ")");
            return 9;
        }
        else if (rand > 95) {
            if(random < probabilidade && eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaCasaNome); 
                return 7;
            } else if (random < probabilidade && !eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaVisitanteNome);
                return 8;
            } else if(random > probabilidade && !eqCasaMaisForte) {
                view.mostraMensagemSemNewLine("Golo do " + equipaCasaNome);
                return 7;
            } else {
                view.mostraMensagemSemNewLine("Golo do " + equipaVisitanteNome);
                return 8;
            }
        }
        return 0;
    }

}
