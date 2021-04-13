/**
 * Classe Main
 * @version 1
 */
public class Main
{
    public static void main(String[] args) {
        
        //Teste para Jogador --------------------------------------------------------------------
        Jogador j0 = new Jogador();
        Jogador j1 = new Jogador(6,5,3,3,7,8,5);
        Jogador j2 = new Jogador(j1);
        Lateral l0 = new Lateral();
        Lateral l1 = new Lateral(6,5,3,3,7,8,5,8);
        Lateral l2 = new Lateral(l1);

        System.out.println("Jogador 0: " + j0.toString());
        System.out.println("Jogador 1: " + j1.toString());
        System.out.println("Jogador 2: " + j2.toString());
        System.out.println("Lateral 0: " + l0.toString());
        System.out.println("Lateral 1: " + l1.toString());
        System.out.println("Lateral 2: " + l2.toString());

        Jogador j3 = new Jogador();
        Lateral l3 = new Lateral();
        System.out.println("Jogador 3 (Vazio):" + j3.toString());
        System.out.println("Lateral 3 (Vazio):" + l3.toString());
        j3 = j1.clone();
        l3 = l1.clone();
        System.out.println("Jogador 3 (Clonado de Jogador 1):" + j3.toString());
        System.out.println("Jogador 1 == Jogador 2: " + j1.equals(j2));
        System.out.println("Jogador 0 == Jogador 2: " + j0.equals(j2));
        System.out.println("Jogador 1 == Jogador 3 (Clonado de Jogador 1)?" + j1.equals(j3));
        System.out.println("Lateral 3 (Clonado de Lateral 1):" + l3.toString());
        System.out.println("Lateral 1 == Lateral 2: " + l1.equals(l2));
        System.out.println("Lateral 0 == Lateral 2: " + l0.equals(l2));
        System.out.println("Lateral 1 == Lateral 3 (Clonado de Lateral 1)?" + l1.equals(l3));
        System.out.println("Habilidades (máximo 70): ------------------------------");
        System.out.println("Habilidade Jogador 0: " + j0.calculaHabilidade());
        System.out.println("Habilidade Jogador 2: " + j2.calculaHabilidade());
        System.out.println("Habilidade Jogador 3: " + j3.calculaHabilidade());
        System.out.println("Habilidade Lateral 0: " + l0.calculaHabilidade());
        System.out.println("Habilidade Lateral 2: " + l2.calculaHabilidade());
        System.out.println("Habilidade Lateral 3: " + l3.calculaHabilidade());

        //---------------------------------------------------------------------------------------
    }

}
