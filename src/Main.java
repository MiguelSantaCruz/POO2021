/**
 * Classe Main
 * @version 1
 */
public class Main
{
    /**
     * Método para correr as funcionalidades do Projeto.
     * @param args Argumentos da main.
     * @throws Exception Lançar exceções caso seja necessário.
     */
    public static void main(String[] args) throws Exception{
        Interpretador.run();
       
        //Interpretador.run();
        //Avancado j1 = new Avancado (1,1,5,3,3,7,8,7,0,123,3,3,"Sérgio",20);
        //System.out.println("Atleta: " + j1.toString());
        //Teste para Jogador --------------------------------------------------------------------
        //Atleta a = new Atleta(1478,"Pedro",26);
        //a.gravaOb("ass.txt");
        //Jogador j1 = new Jogador(6,5,3,3,7,8,5,100,8147,"Júlio",26);
        //
        //Avancado j1 = new Avancado (6,5,3,3,7,8,7,0,123,3,3,"Sérgio",20);
        //Lateral j = new Lateral(6,5,3,3,7,8,5,8,1234,"Sérgio",20);
        //Guarda_Redes j1 = new Guarda_Redes(6,5,3,3,7,8,5,8,1234,"Paulinho",20);
        //j1.gravaOb("ass.txt");
        //Atleta b = j1.lerObjeto("ass.txt");
	    
        /*Jogador j0 = new Jogador();

        Jogador j2 = new Jogador(j1);
        Lateral l0 = new Lateral();
        Lateral l1 = new Lateral(6,5,3,3,7,8,5,8,1234,"Sérgio",20);
        Lateral l2 = new Lateral(l1);
        Guarda_Redes g0 = new Guarda_Redes();
        Guarda_Redes g1 = new Guarda_Redes(1,2,3,4,5,6,7,8,2234,"Paulo",31);
        Guarda_Redes g2 = new Guarda_Redes(g1);
        Medio m0 = new Medio();
        Medio m1 = new Medio(6,5,3,3,7,8,5,8,5578,"Rui",18);
        Medio m2 = new Medio(m1);

        System.out.println("Atleta: " + a.toString());
        System.out.println("Jogador 0: " + j0.toString());
        System.out.println("Jogador 1: " + j1.toString());
        System.out.println("Jogador 2: " + j2.toString());
        System.out.println("Lateral 0: " + l0.toString());
        System.out.println("Lateral 1: " + l1.toString());
        System.out.println("Lateral 2: " + l2.toString());
        System.out.println("Guarda_Redes 0: " + g0.toString());
        System.out.println("Guarda_Redes 1: " + g1.toString());
        System.out.println("Guarda_Redes 2: " + g2.toString());
        System.out.println("Médio 0: " + m0.toString());
        System.out.println("Médio 1: " + m1.toString());
        System.out.println("Médio 2: " + m2.toString());

        Jogador j3 = new Jogador();
        Lateral l3 = new Lateral();
        Guarda_Redes g3 = new Guarda_Redes();
        Medio m3 = new Medio();
        System.out.println("Jogador 3 (Vazio):" + j3.toString());
        System.out.println("Lateral 3 (Vazio):" + l3.toString());
        System.out.println("Guarda_Redes 3 (Vazio):" + g3.toString());
        System.out.println("Médio 3 (Vazio):" + m3.toString());
        j3 = j1.clone();
        l3 = l1.clone();
        g3 = g1.clone();
        m3 = m1.clone();
        System.out.println("Jogador 3 (Clonado de Jogador 1):" + j3.toString());
        System.out.println("Jogador 1 == Jogador 2: " + j1.equals(j2));
        System.out.println("Jogador 0 == Jogador 2: " + j0.equals(j2));
        System.out.println("Jogador 1 == Jogador 3 (Clonado de Jogador 1)?" + j1.equals(j3));
        
        System.out.println("Lateral 3 (Clonado de Lateral 1):" + l3.toString());
        System.out.println("Lateral 1 == Lateral 2: " + l1.equals(l2));
        System.out.println("Lateral 0 == Lateral 2: " + l0.equals(l2));
        System.out.println("Lateral 1 == Lateral 3 (Clonado de Lateral 1)?" + l1.equals(l3));
        
        System.out.println("Guarda_Redes 3 (Clonado de Guarda_Redes 1):" + g3.toString());
        System.out.println("Guarda_Redes 1 == Guarda_Redes 2: " + g1.equals(g2));
        System.out.println("Guarda_Redes 0 == Guarda_Redes 2: " + g0.equals(g2));
        System.out.println("Guarda_Redes 1 == Guarda_Redes 3 (Clonado de Guarda_Redes 1)?" + g1.equals(g3));

        System.out.println("Médio 3 (Clonado de Medio 1):" + m3.toString());
        System.out.println("Médio 1 == Medio 2: " + m1.equals(j2));
        System.out.println("Médio 0 == Medio 2: " + m0.equals(j2));
        System.out.println("Médio 1 == Medio 3 (Clonado de Médio 1)?" + m1.equals(m3));
        
        System.out.println("Habilidades (máximo 70): ------------------------------");
        System.out.println("Habilidade Jogador 0: " + j0.calculaHabilidade());
        System.out.println("Habilidade Jogador 2: " + j2.calculaHabilidade());
        System.out.println("Habilidade Jogador 3: " + j3.calculaHabilidade());
        System.out.println("Habilidade Lateral 0: " + l0.calculaHabilidade());
        System.out.println("Habilidade Lateral 2: " + l2.calculaHabilidade());
        System.out.println("Habilidade Lateral 3: " + l3.calculaHabilidade());
        System.out.println("Habilidade Guarda_Redes 0: " + g0.calculaHabilidade());
        System.out.println("Habilidade Guarda_Redes 2: " + g2.calculaHabilidade());
        System.out.println("Habilidade Guarda_Redes 3: " + g3.calculaHabilidade());
        System.out.println("Habilidade Médio 0: " + m0.calculaHabilidade());
        System.out.println("Habilidade Médio 2: " + m2.calculaHabilidade());
        System.out.println("Habilidade Médio 3: " + m3.calculaHabilidade());
        //---------------------------------------------------------------------------------------*/
    }

}
