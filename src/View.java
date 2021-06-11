public class View implements IView{
    public View(){

    }

    public void mostrarMenuPrincipal(){
            clearScreen();
            Menu menu = new Menu();
            menu.setTitulo("Football Manager ⚽");
            menu.adicionaOpcao("Consultar equipas 👥");
            menu.adicionaOpcao("Consultar jogadores ⛹️‍");
            menu.adicionaOpcao("Adicionar equipa ➕👥");
            menu.adicionaOpcao("Adicionar jogador ➕⛹️");
            menu.adicionaOpcao("Transferir jogador ➡️ ⛹️");
            menu.adicionaOpcao("Jogar 🎮‍");
            menu.adicionaOpcao("Carrega estado 🖥️");
            menu.adicionaOpcao("Guarda estado 💾");
            menu.adicionaOpcao("Sair ❌");
            menu.show(true);
    }

    public void mostrarMenuDeConsultaDeJogador(){
        clearScreen();
        Menu menu = new Menu();
        menu.adicionaOpcao("Procurar jogador nos planteis 📕");
        menu.adicionaOpcao("Ver detalhes do jogador 📊");
        menu.adicionaOpcao("Voltar");
        menu.setTitulo("Consultar jogadores ⚽");
        menu.show(true);
    }
    
    
    public void mostrarMenuDeConsultaDeEquipas(){
        clearScreen();
        Menu menu = new Menu();
        menu.adicionaOpcao("Procurar por nome 📕");
        menu.adicionaOpcao("Consultar lista 📋");
        menu.adicionaOpcao("Voltar");
        menu.setTitulo("Consultar equipas ⚽");
        menu.show(true);
    }

    public void mostrarEquipas(IModel model){
        clearScreen();
        Menu menuEquipas = new Menu();
        menuEquipas.setTitulo("Selecione uma equipa");
            for (IEquipa e : model.getEquipas().values()) {
                menuEquipas.adicionaOpcao(e.getNome());
            }
        menuEquipas.show(true);
    }

    public void mostrarListaDeJogadores(){
        Menu menu = new Menu();
        menu.setTitulo("Selecione a posição");
        menu.adicionaOpcao("Medio");
        menu.adicionaOpcao("Lateral");
        menu.adicionaOpcao("Defesa");
        menu.adicionaOpcao("Guarda-Redes");
        menu.adicionaOpcao("Avançado");
        menu.show(true);
    }

    public void showInicioJogo(IEquipa equipaCasa, IEquipa equipaVisitante){
        clearScreen();
        System.out.println("\u001B[36mJogo entre " + equipaCasa.getNome() + " e " + equipaVisitante.getNome() + " [Ainda não dá para selecionar]\u001B[0m");
        System.out.println("-----------------------------------------");
        System.out.println("Bola ao meio campo");
    }

    public void showFimJogo(IJogo j){
        System.out.println("\u001B[32mResultado final: " + j.getEqCasa().getNome() + " " + j.getGolosCasa() + " : " + j.getGolosVisitante() + " " + j.getEqVisitante().getNome()+"\u001B[0m");
    }

    public void mostraMensagem(String msg){
        System.out.println(msg);
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 
}

