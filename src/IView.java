import java.util.List;
import java.util.Map;

public interface IView {
    public void mostraMensagem(String msg);
    public void mostraMensagemSemNewLine(String msg);
    public void mostrarMenuPrincipal();
    public void mostrarMenuDeConsultaDeEquipas();
    public void mostrarMenuDeConsultaDeJogador();
    public void mostrarEquipasComOnzeOuMaisJogadores(IModel model);
    public void mostrarEquipas(IModel model);
    public void mostrarSelecaoDePosicaoJogador();
    public void showInicioJogo(IEquipa equipaCasa, IEquipa equipaVisitante);
    public void showFimJogo(IJogo j);
    public static void showEquipa(IEquipa e,boolean showPlantel,int highlight){
        System.out.print("Nome: " + truncateString(e.getNome(), 30));
        System.out.print("  Data de fundação: " + e.getDataDeFundação());
        System.out.println("            Habilidade global: " + e.calculaHabilidadeGlobal() + " " + avaliaHabilidade(e.calculaHabilidadeGlobal()));
        if(showPlantel){
            System.out.println("Plantel (" + e.getplantel().size() +"):");
            for (Map.Entry<Integer,IJogador> plantel: e.getplantel().entrySet()) {
                if(plantel.getKey() == highlight) showJogador(plantel.getValue(),true);
                else showJogador(plantel.getValue(), false);
                
            }
        }
    }

    public static void showJogador(IJogador j,boolean highlight){
        if (highlight) {
            System.out.print("\u001B[47m");
            System.out.print("\u001B[30m");
        }
        System.out.print("Nome: " + truncateString(j.getNomeAtleta(),30));
        if(j.getClass().getName().equals("Medio")) System.out.print(" [CDM]");
        else if(j.getClass().getName().equals("Lateral")) System.out.print("  [RB]");
        else if(j.getClass().getName().equals("Avancado")) System.out.print("  [ST]");
        else if(j.getClass().getName().equals("Guarda_Redes")) System.out.print("  [GK]");
        else if(j.getClass().getName().equals("Defesa")) System.out.print("  [CB]");
        else System.out.print("   [J]");
        System.out.print(" │ Número: " + truncateString(String.valueOf(j.getNumeroJogador()),3));
        System.out.print(" │ Idade " + truncateString(String.valueOf(j.getIdade()),3));
        System.out.println(" │ Habilidade geral: " + j.getHabilidade() + " " + avaliaHabilidade(j.getHabilidade()));
        System.out.print("\u001B[0m");
    }

    public void showJogos(IModel model);

    public static void showDetalhesJogador(IJogador j,IEquipa e){
        System.out.println("Equipa atual: " + e.getNome());
        System.out.println("Habilidade geral: " + j.getHabilidade() + avaliaHabilidade(j.getHabilidade()));
        System.out.println(j.toString());
    }

    public static String avaliaHabilidade(float habilidade){
        StringBuilder sb = new StringBuilder();
        if(habilidade >= 90) sb.append("[Lendário]");
        if(habilidade >= 80 && habilidade < 90) sb.append("[Muito elevado]");
        if(habilidade >= 60 && habilidade < 80) sb.append("[Elevado]");
        if(habilidade >= 40 && habilidade < 60) sb.append("[Mediano]");
        if(habilidade >= 20 && habilidade < 40) sb.append("[Baixo]");
        if(habilidade < 20) sb.append("[Muito baixo]");
        return sb.toString();
    }

    public void mostrarListaJogadoresComoMenu(List<IJogador> l);

    public static String truncateString(String s, int n) {
        return String.format("%-" + n + "s", s);  
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }


    public static void pressEnterToContinue(IInput input){
        System.out.println(" --- press enter ---");
        input.InputString();
    }
}
