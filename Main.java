public class Main
{
    public static void main(String[] args) {
        
        //Teste para Jogador --------------------------------------------------------------------
        Jogador j0 = new Jogador();
        Jogador j1 = new Jogador(6,5,3,3,7,8,5);
        Jogador j2 = new Jogador(j1);

        System.out.println("Jogador 0: " + j0.toString());
        System.out.println("Jogador 1: " + j1.toString());
        System.out.println("Jogador 2:" + j2.toString());
        Jogador j3 = new Jogador();
        System.out.println("Jogador 3 (Vazio):" + j3.toString());
        j3 = j1.clone();
        System.out.println("Jogador 3 (Clonado de Jogador 1):" + j3.toString());
        System.out.println("Jogador 1 == Jogador 2?" + j1.equals(j2));
        System.out.println("Jogador 0 == Jogador 2?" + j0.equals(j2));
        System.out.println("Jogador 1 == Jogador 3 (Clonado de Jogador 1)?" + j1.equals(j3));
        //---------------------------------------------------------------------------------------
    }

}
