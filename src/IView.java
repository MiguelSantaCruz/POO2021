import java.util.Map;

public interface IView {
    public void mostraMensagem(String msg);
    public void mostrarMenuPrincipal();
    public void mostrarMenuDeConsultaDeEquipas();
    public void mostrarMenuDeConsultaDeJogador();
    public void mostrarEquipas(IModel model);
    public void mostrarListaDeJogadores();
    public void showInicioJogo(IEquipa equipaCasa, IEquipa equipaVisitante);
    public void showFimJogo(IJogo j);
    public static void showEquipa(IEquipa e,boolean showPlantel,int highlight){
        System.out.print("Nome: " + truncateString(e.getNome(), 30));
        System.out.print("  Data de fundação: " + e.getDataDeFundação());
        System.out.println("            Habilidade global: " + e.getHabilidadeGlobal() + avaliaHabilidade(e.getHabilidadeGlobal()));
        if(showPlantel){
            System.out.println("Plantel:");
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
        System.out.print(" │ Número: " + truncateString(String.valueOf(j.getIdAtleta()),3));
        System.out.print(" │ Idade " + truncateString(String.valueOf(j.getIdade()),3));
        System.out.println(" │ Habilidade geral: " + j.getHabilidade() + avaliaHabilidade(j.getHabilidade()));
        System.out.print("\u001B[0m");
    }

    public static void showDetalhesJogador(IJogador j,IEquipa e){
        System.out.println("Equipa: " + e.getNome());
        System.out.println("Nome: " + (j.getNomeAtleta()));
        System.out.println("Número: " + j.getIdAtleta());
        System.out.println("Idade " + j.getIdade());
        System.out.println("Habilidade geral: " + j.getHabilidade() + avaliaHabilidade(j.getHabilidade()));
        System.out.println("Posição: " + j.getClass().getName());
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

    public static String truncateString(String s, int n) {
        return String.format("%-" + n + "s", s);  
    }
}
