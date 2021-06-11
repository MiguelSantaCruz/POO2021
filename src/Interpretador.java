import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Interpretador {
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
                    procurarEquipa(p, input, view, model);
                    break;
                case 2:
                    procurarJogador(p, input, view, model);
                    break;
                case 3:
                    adicionaEquipa(p, input, view, model);
                    break;
                case 4:
                    adicionaJogador(p, input, view, model);
                    break;
                case 6:
                    view.mostrarEquipas(model);
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
                        i++;
                    }
                    jogar(eqCasa, eqVisitante, view, input);
                    break;
                case 7:
                    view.mostraMensagem("Insira o nome do ficheiro: ");
                    String filePath = input.InputString();
                    model = p.readBin(filePath);
                    view.mostraMensagem(
                            "Lidas: " + model.getEquipas().values().size() + " equipas de [" + filePath + "]");
                    view.mostraMensagem("--- press enter ---");
                    input.InputString();
                    break;
                case 8:
                    view.mostraMensagem("Insira o nome do ficheiro: ");
                    String filePath2 = input.InputString();
                    p.guardaBin(filePath2, model);
                    break;
                case 9:
                    input.closeScanner();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void procurarJogador(IParser p, IInput input, IView view, IModel model) {
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
                            else
                                IView.showEquipa(e, true, j.getNumeroJogador());
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
                view.mostraMensagem("-- [Erro]: pressione enter --");
                input.InputString();
                procurarJogador(p, input, view, model);
                break;
        }
        System.out.println("-- press enter --");
        input.InputString();
        return;
    }

    public static void procurarEquipa(IParser p, IInput input, IView view, IModel model) {
        view.mostrarMenuDeConsultaDeEquipas();
        int escolha = input.InputInteger();
        switch (escolha) {
            case 1:
                view.mostraMensagem("Insira um nome para procurar");
                String search = input.InputString();
                if (model.getEquipas().containsKey(search.toLowerCase())) {
                    clearScreen();
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
                view.mostraMensagem("-- [Erro]: pressione enter --");
                input.InputString();
                procurarEquipa(p, input, view, model);
                break;
        }
        view.mostraMensagem("-- pressione enter --");
        input.InputString();
        return;
    }

    public static void jogar(IEquipa equipaCasa, IEquipa equipaVisitante, IView view, IInput input) {
        IJogo j = new Jogo();
        j.setEqVisitante(equipaVisitante);
        j.setEqCasa(equipaCasa);
        j.setPosicaoBola(0);
        view.showInicioJogo(equipaCasa, equipaVisitante);
        for (int i = 0; i < 90; i++) {
            GameResult.calculaJogada(j);
            view.mostraMensagem(" (" + (i + 1) + "')");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        view.showFimJogo(j);
        view.mostraMensagem("-- press enter --");
        input.InputString();
    }

    public static void adicionaEquipa(IParser p, IInput input, IView view, IModel model) {
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

    public static void adicionaJogador(IParser p, IInput input, IView view, IModel model) {
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
                m.addEquipa(e);
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
                l.addEquipa(e);
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
                d.addEquipa(e);
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
                g.addEquipa(e);
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
                a.addEquipa(e);
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
                a1.addEquipa(e);
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

    public static ArrayList<String> leNargumentos(IParser p, Scanner s, int n, boolean isNumeric,
            ArrayList<String> text) {
        ArrayList<String> l = new ArrayList<>();
        int inputInt = 0;
        String inputStr = "";
        for (int i = 0; i < n; i++) {
            if (text != null && text.size() > i)
                System.out.print(text.get(i) + ": ");
            while (true) {
                try {
                    if (isNumeric)
                        inputInt = Integer.parseInt(s.nextLine());
                    else
                        inputStr = s.nextLine();
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("[Erro] Insira de novo: ");
                    continue;
                }
            }
            if (isNumeric)
                l.add(String.valueOf(inputInt));
            else
                l.add(inputStr);
        }
        return l;
    }
}