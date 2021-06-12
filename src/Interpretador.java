import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Interpretador {
    /**
     * Mostrar o menu principal, pedir input e chamar as respetivas funções
     */
    public static void run() throws LinhaIncorretaException, ClassNotFoundException, IOException {
        IView view = new View();
        IInput input = new Input();
        IModel model = new Model();
        IParser p = new Parser();
        p.parse(model);
        while (true) {
            view.mostrarMenuPrincipal();
            int escolha = input.InputInteger();
            switch (escolha) {
                case 1:
                    procurarEquipa(input, view, model);
                    break;
                case 2:
                    procurarJogador(input, view, model);
                    break;
                case 3:
                    view.showJogos(model);
                    IView.pressEnterToContinue(input);
                    break;
                case 4:
                    adicionaEquipa(input, view, model);
                    break;
                case 5:
                    adicionaJogador(input, view, model);
                    break;
                case 6:
                    transfereJogador(input,view,model);
                    break;
                case 7:
                    view.mostrarEquipasComOnzeOuMaisJogadores(model);
                    view.mostraMensagem("Insira a primeira equipa");
                    int equipa1 = input.InputInteger();
                    view.mostraMensagem("Insira a segunda equipa");
                    int equipa2 = input.InputInteger();
                    while (equipa1 == equipa2) {
                        view.mostraMensagem("Insira equipas diferentes");
                        equipa2 = input.InputInteger();
                    }
                    int i = 1;
                    IEquipa eqCasa = new Equipa();
                    IEquipa eqVisitante = new Equipa();
                    for (IEquipa equipa : model.getEquipas().values()) {
                        if (i == equipa1)
                            eqCasa = equipa;
                        if (i == equipa2)
                            eqVisitante = equipa;
                        if(equipa.getplantel().size() >= 11) i++;
                    }
                    jogar(eqCasa, eqVisitante, view, input,model);
                    break;
                case 8:
                    view.mostraMensagem("Insira o nome do ficheiro: ");
                    String filePath = input.InputString();
                    model = p.readBin(filePath);
                    view.mostraMensagem(
                            "Lidas " + model.getEquipas().values().size() + " equipas de [" + filePath + "]");
                    view.mostraMensagem(
                            "Lidos " + model.getJogos().size() + " jogos de [" + filePath + "]");
                    IView.pressEnterToContinue(input);
                    break;
                case 9:
                    view.mostraMensagem("Insira o nome do ficheiro: ");
                    String filePath2 = input.InputString();
                    p.guardaBin(filePath2, model);
                    view.mostraMensagem("Guardado em " + filePath2);
                    IView.pressEnterToContinue(input);
                    break;
                case 10:
                    removeJogador(input,view,model);
                    break;
                case 11:
                    view.mostrarEquipas(model);
                    int escolha2 = -1;
                    while (escolha2 < 1 || escolha2 > model.getEquipas().values().size()) {
                        escolha2 = input.InputInteger();
                    }
                    int index = 1;
                    for (IEquipa equipa : model.getEquipas().values()) {
                        if(index == escolha2){
                            view.mostraMensagem("Removida a equipa " + equipa.getNome());
                            model.removeEquipa(equipa);
                            break;
                        }
                        index++;
                    }
                    IView.pressEnterToContinue(input);
                    break;
                case 12:
                    input.closeScanner();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Função que pede ao utilizador o nome de um jogador e uma equipa e efetua a transferencia
     * @param input Scanner aberto no momento encapsulado pela classe Input
     * @param view  View inicializada no momento
     * @param model Modelo carregado em memória no momento
     */
    public static void transfereJogador(IInput input, IView view, IModel model){
        view.mostraMensagem("Insira o nome do jogador para procurar:");
        String search;
        search = input.InputString();
        boolean playerExists = false;
        IJogador jogador = new Jogador();
        for (IEquipa e : model.getEquipas().values()) {
            if (playerExists)
            break;
            for (IJogador j : e.getplantel().values()) {
                if (j.getNomeAtleta().toLowerCase().equals(search.toLowerCase())) {
                    jogador = j;
                    playerExists = true;
                }
            }
        }
        if(!playerExists){
            view.mostraMensagem("--- Não encontrado ---");
            IView.pressEnterToContinue(input);
            return;
        } else {
           view.mostrarEquipas(model);
           int escolha = input.InputInteger();
           while (escolha < 1 || escolha > model.getEquipas().values().size()) {
                escolha = input.InputInteger();
           }
           model.getEquipas().get(jogador.getEquipas().get(jogador.getEquipas().size()-1).getNome().toLowerCase()).removeJogador(jogador);
           int i = 1;
           for (IEquipa eq : model.getEquipas().values()) {
                if (i == escolha) {
                    eq.insereJogador(jogador);
                    break;
                }
                i++;
           }           
        }
    }

    /**
     * Função que procura um determinado jogador
     * @param input Scanner aberto no momento encapsulado pela classe Input
     * @param view  View inicializada no momento
     * @param model Modelo carregado em memória no momento
     */
    public static void procurarJogador(IInput input, IView view, IModel model) {
        view.mostrarMenuDeConsultaDeJogador();
        boolean detalhesJogador = false;
        int escolha = input.InputInteger();
        if (escolha == 2) {
            escolha = 1;
            detalhesJogador = true;
        }
        switch (escolha) {
            case 1:
                view.mostraMensagem("Insira um nome para procurar:");
                String search = input.InputString();
                boolean playerExists = false;
                for (IEquipa e : model.getEquipas().values()) {
                    if (playerExists)
                        break;
                    for (IJogador j : e.getplantel().values()) {
                        if (j.getNomeAtleta().toLowerCase().equals(search.toLowerCase())) {
                            playerExists = true;
                            if (detalhesJogador)
                                IView.showDetalhesJogador(j, e);
                            else{
                                IView.clearScreen();
                                IView.showEquipa(e, true, j.getNumeroJogador());
                            }
                            break;
                        }
                    }
                }
                if (!playerExists)
                    view.mostraMensagem("Não encontrado");
                break;
            case 3:
                return;
            default:
                view.mostraMensagem("-- [Erro] --");
                IView.pressEnterToContinue(input);
                procurarJogador(input, view, model);
                break;
        }
        IView.pressEnterToContinue(input);
        return;
    }

    /**
     * Função que procura uma determinada equipa
     * @param input Scanner aberto no momento encapsulado pela classe Input
     * @param view  View inicializada no momento
     * @param model Modelo carregado em memória no momento
     */
    public static void procurarEquipa(IInput input, IView view, IModel model) {
        view.mostrarMenuDeConsultaDeEquipas();
        int escolha = input.InputInteger();
        switch (escolha) {
            case 1:
                view.mostraMensagem("Insira um nome para procurar");
                String search = input.InputString();
                if (model.getEquipas().containsKey(search.toLowerCase())) {
                    IView.clearScreen();
                    IView.showEquipa(model.getEquipas().get(search.toLowerCase()), true, -1);
                } else
                    view.mostraMensagem("Não encontrado");
                break;
            case 2:
                for (IEquipa e : model.getEquipas().values()) {
                    IView.showEquipa(e, false, -1);
                }
                break;
            case 3:
                return;
            default:
                IView.pressEnterToContinue(input);
                procurarEquipa(input, view, model);
                break;
        }
        IView.pressEnterToContinue(input);
        return;
    }

    /**
     * Função que inicializa um jogo dadas duas equipas
     * @param equipaCasa Equipa que joga em casa
     * @param equipaVisitante Equipa que joga fora de casa
     * @param input Scanner aberto no momento encapsulado pela classe Input
     * @param view  View inicializada no momento
     * @param model Modelo carregado em memória no momento
     */
    public static void jogar(IEquipa equipaCasa, IEquipa equipaVisitante, IView view, IInput input,IModel model) {
        IJogo j = new Jogo();
        j.setTempoJogo(LocalDate.now());
        j.setEqVisitante(equipaVisitante);
        j.setEqCasa(equipaCasa);
        j.setPosicaoBola(0);
        view.showInicioJogo(equipaCasa, equipaVisitante);
        for (int i = 0; i < 90; i++) {
            GameResult.calculaJogada(j,view);
            view.mostraMensagem(" (" + (i + 1) + "')");
            try {
                if((i+1) == 45){
                    view.mostraMensagem("\u001B[35m ------------------ Intervalo -------------------\u001B[0m");
                    Thread.sleep(1000);
                }
                else Thread.sleep(400);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        model.addJogo(j);
        view.showFimJogo(j);
        IView.pressEnterToContinue(input);
    }

    /**
     * Função que permite adicionar uma equipa ao modelo
     * @param input Scanner aberto no momento encapsulado pela classe Input
     * @param view  View inicializada no momento
     * @param model Modelo carregado em memória no momento
     */
    public static void adicionaEquipa(IInput input, IView view, IModel model) {
        IEquipa e = new Equipa();
        e.setplantel(new HashMap<Integer, IJogador>());
        e.setJogosAgendados(new ArrayList<IJogo>());
        view.mostraMensagem("Insira o nome");
        String nome = input.InputString();
        view.mostraMensagem("Insira a data de fundação (aaaa-mm-dd)");
        String dataDeFundacao = input.InputString();
        e.setNome(nome);
        e.setDataDeFundação(LocalDate.parse(dataDeFundacao));
        model.addEquipa(e);
    }


    /**
     * Função que permite adicionar um jogador ao modelo
     * @param input Scanner aberto no momento encapsulado pela classe Input
     * @param view  View inicializada no momento
     * @param model Modelo carregado em memória no momento
     */
    public static void adicionaJogador(IInput input, IView view, IModel model) {
        view.mostraMensagem("Insira o nome");
        String nome = input.InputString();
        view.mostraMensagem("Insira a idade");
        Integer idade = input.InputInteger();
        view.mostraMensagem("Insira o número");
        Integer numero = input.InputInteger();
        view.mostrarEquipas(model);
        view.mostraMensagem("Insira a equipa");
        int numEquipas = input.InputInteger();
        int i = 1;
        IEquipa e = new Equipa();
        for (IEquipa equipa : model.getEquipas().values()) {
            if (i == numEquipas) {
                e = equipa;
                break;
            }
            i++;
        }
        view.mostrarSelecaoDePosicaoJogador();
        int escolha = input.InputInteger();
        switch (escolha) {
            case 1:
                Medio m = new Medio();
                m.setRecuperacao_bolas((int) (Math.random() * 100));
                m.setHabilidade(m.calculaHabilidade());
                m.setNomeAtleta(nome);
                m.setIdade(idade);
                m.setNumeroJogador(numero);
                m.setCapacidadeDePasse((int) (Math.random() * 100));
                m.setDestreza((int) (Math.random() * 100));
                m.setImpulsao((int) (Math.random() * 100));
                m.setJogoDeCabeca((int) (Math.random() * 100));
                m.setRemate((int) (Math.random() * 100));
                m.setResistencia((int) (Math.random() * 100));
                m.setVelocidade((int) (Math.random() * 100));
                m.setTitular(true);
                m.setSuplente(false);
                model.getEquipas().get(e.getNome().toLowerCase()).insereJogador(m);
                break;
            case 2:
                Lateral l = new Lateral();
                l.setCruzamentos((int) (Math.random() * 100));
                l.setHabilidade(l.calculaHabilidade());
                l.setNomeAtleta(nome);
                l.setIdade(idade);
                l.setNumeroJogador(numero);
                l.setCapacidadeDePasse((int) (Math.random() * 100));
                l.setDestreza((int) (Math.random() * 100));
                l.setImpulsao((int) (Math.random() * 100));
                l.setJogoDeCabeca((int) (Math.random() * 100));
                l.setRemate((int) (Math.random() * 100));
                l.setResistencia((int) (Math.random() * 100));
                l.setVelocidade((int) (Math.random() * 100));
                l.setTitular(true);
                l.setSuplente(false);
                model.getEquipas().get(e.getNome().toLowerCase()).insereJogador(l);
                break;
            case 3:
                Defesa d = new Defesa();
                d.setIntersecao((int) (Math.random() * 100));
                d.setDrible((int) (Math.random() * 100));
                d.setHabilidade(d.calculaHabilidade());
                d.setNomeAtleta(nome);
                d.setIdade(idade);
                d.setNumeroJogador(numero);
                d.setCapacidadeDePasse((int) (Math.random() * 100));
                d.setDestreza((int) (Math.random() * 100));
                d.setImpulsao((int) (Math.random() * 100));
                d.setJogoDeCabeca((int) (Math.random() * 100));
                d.setRemate((int) (Math.random() * 100));
                d.setResistencia((int) (Math.random() * 100));
                d.setVelocidade((int) (Math.random() * 100));
                d.setTitular(true);
                d.setSuplente(false);
                model.getEquipas().get(e.getNome().toLowerCase()).insereJogador(d);
                break;
            case 4:
                Guarda_Redes g = new Guarda_Redes();
                g.setElasticidade((int) (Math.random() * 100));
                g.setHabilidade(g.calculaHabilidade());
                g.setNomeAtleta(nome);
                g.setIdade(idade);
                g.setNumeroJogador(numero);
                g.setCapacidadeDePasse((int) (Math.random() * 100));
                g.setDestreza((int) (Math.random() * 100));
                g.setImpulsao((int) (Math.random() * 100));
                g.setJogoDeCabeca((int) (Math.random() * 100));
                g.setRemate((int) (Math.random() * 100));
                g.setResistencia((int) (Math.random() * 100));
                g.setVelocidade((int) (Math.random() * 100));
                g.setTitular(true);
                g.setSuplente(false);
                model.getEquipas().get(e.getNome().toLowerCase()).insereJogador(g);
                break;
            case 5:
                Avancado a = new Avancado();
                a.setFinalizacao((int) (Math.random() * 100));
                a.setSprint((int) (Math.random() * 100));
                a.setHabilidade(a.calculaHabilidade());
                a.setNomeAtleta(nome);
                a.setIdade(idade);
                a.setNumeroJogador(numero);
                a.setCapacidadeDePasse((int) (Math.random() * 100));
                a.setDestreza((int) (Math.random() * 100));
                a.setImpulsao((int) (Math.random() * 100));
                a.setJogoDeCabeca((int) (Math.random() * 100));
                a.setRemate((int) (Math.random() * 100));
                a.setResistencia((int) (Math.random() * 100));
                a.setVelocidade((int) (Math.random() * 100));
                a.setTitular(true);
                a.setSuplente(false);
                model.getEquipas().get(e.getNome().toLowerCase()).insereJogador(a);
                break;
            default:
                Avancado a1 = new Avancado();
                a1.setFinalizacao((int) (Math.random() * 100));
                a1.setSprint((int) (Math.random() * 100));
                a1.setHabilidade(a1.calculaHabilidade());
                a1.setNomeAtleta(nome);
                a1.setIdade(idade);
                a1.setNumeroJogador(numero);
                a1.setCapacidadeDePasse((int) (Math.random() * 100));
                a1.setDestreza((int) (Math.random() * 100));
                a1.setImpulsao((int) (Math.random() * 100));
                a1.setJogoDeCabeca((int) (Math.random() * 100));
                a1.setRemate((int) (Math.random() * 100));
                a1.setResistencia((int) (Math.random() * 100));
                a1.setVelocidade((int) (Math.random() * 100));
                a1.setTitular(true);
                a1.setSuplente(false);
                model.getEquipas().get(e.getNome().toLowerCase()).insereJogador(a1);
                break;
        }
    }

    /**
     * Função que permite remover um jogador ao modelo
     * @param input Scanner aberto no momento encapsulado pela classe Input
     * @param view  View inicializada no momento
     * @param model Modelo carregado em memória no momento
     */
    public static void removeJogador(IInput input,IView view,IModel model){
        view.mostraMensagem("Insira um nome:");
        String name = input.InputString();
        List<IJogador> l = new ArrayList<>();
        for (IEquipa e : model.getEquipas().values()) {
            for (IJogador j : e.getplantel().values()) {
                if (j.getNomeAtleta().toLowerCase().equals(name.toLowerCase())) {
                    l.add(j);
                }
            }
        }
        //Remove o jogador da equipa atual
        if(l.size() == 1) {
            IEquipa e = l.get(0).getEquipas().get(l.get(0).getEquipas().size()-1);
            model.getEquipas().get(e.getNome().toLowerCase()).removeJogador(l.get(0));
            view.mostraMensagem("Removido " + l.get(0).getNomeAtleta() + " da equipa " +e.getNome());
        }   
        else if(l.size() > 1) {
            view.mostrarListaJogadoresComoMenu(l);
            int escolha = -1;
            while(escolha < 1 || escolha > l.size()){
                escolha = input.InputInteger();
            }
            IEquipa e = l.get(escolha-1).getEquipas().get(l.get(escolha-1).getEquipas().size()-1);
            model.getEquipas().get(e.getNome().toLowerCase()).removeJogador(l.get(escolha));
            view.mostraMensagem("Removido " + l.get(escolha-1).getNomeAtleta() + " da equipa " + e.getNome());
            
        }
    }
}