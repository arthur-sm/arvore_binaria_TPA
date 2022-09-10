import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import arvoreBinaria.ArvoreBinaria;
import arvoreBinaria.No;
import arvoreBinariaAluno.Aluno;

public class App {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        arvore.insere(9);
        arvore.insere(3);
        arvore.insere(4);
        arvore.insere(2);
        arvore.insere(6);
        arvore.insere(10);
        arvore.insere(9);
        arvore.insere(5);
        arvore.insere(7);
        arvore.insere(1);
        System.out.println(arvore.getRaiz());
        System.out.println("\nRemovendo elemento 3");
        arvore.remove(3);
        System.out.println(arvore.getRaiz());
        arvore.insere(3);
        arvore.remove(9);
        System.out.println("\nRemovendo elemento 9");
        System.out.println(arvore.getRaiz());
        System.out.println(arvore.getAltura());
        System.out.println(arvore.insere(10));

        ArrayList<Integer> teste = arvore.caminhaEmOrdem();
        System.out.println(teste);
        teste = arvore.caminhaEmNivel();
        System.out.println(teste);

        // Aluno cleber = new Aluno(1234, "Cleber", "20");
        // Aluno brenno = new Aluno(1233, "Brenno", "20");
        // Aluno caio = new Aluno(1235, "Caio", "20");
        // Aluno casio = new Aluno(1232, "Casio", "20");
        // arvore.insere(cleber);
        // arvore.insere(caio);
        // arvore.insere(brenno);
        // //System.out.println("Matrícula Cleber:" + cleber.getMatricula());
        // //System.out.println("Matrícula Cleber:" +
        // arvore.getRaiz().getElemento().getMatricula());
        // System.out.println("Raiz: " + arvore.getRaiz());
        // System.out.println("Casio existe na árvore: " + arvore.busca(casio));
        // arvore.insere(casio);
        // arvore.remove(brenno);
        // System.out.println("Casio existe na árvore: " + arvore.busca(casio));
        // System.out.println("Raiz: " + arvore.getRaiz());
        // System.out.println("Testando procura por matrícula:");
        // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 1234,
        // 0).getElemento());
        // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 1235,
        // 0).getElemento());
        // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 1233,
        // 0).getElemento());
        // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 1232,
        // 0).getElemento());
        // System.out.println("Procurando pela matrícula 9999");
        // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 9999,
        // 0).getElemento());
    }

    public static void Menu(ArvoreBinaria<Aluno> arvore) {
        Scanner userinput = new Scanner(System.in);
        while (true) {
            System.out.println(
                "Escolha uma opção: \n1 - Estatísticas\n2 - Busca por Matrícula\n3 - Exclusão por Matrícula\n4 - Incluir Aluno\n5 - Sair");
            int escolha = userinput.nextInt();
            if (escolha == 1) {
                //Exibir estatísticas – Deve exibir a quantidade total de elementos, a altura da árvore, o maior e o menor elemento e o pior caso de busca.
                System.out.println("Quantidade total de elementos da árvore: " + arvore.quantidadeElementos());
                System.out.println("Altura da árvore: " + arvore.getAltura());
                //maior elemento
                //menor elemento
                //pior caso
            }
            else if (escolha == 2) {
                int matricula = userinput.nextInt();
                System.out.println("Dados da matrícula cadastrada: " + encontraMatricula(arvore.getRaiz(), matricula, 0).getElemento());
            }
            else if (escolha == 5) {
                //Sair: o programa deve percorrer a árvore usando caminhamento "em ordem" e gerar um arquivo em que cada linha apresentará a matrícula, o nome e a nota de um aluno, sempre separados por ;. 
                System.out.println(arvore.caminhaEmOrdem());
                userinput.close();
                break;
            }
        }
    }

    public static No<Aluno> encontraMatricula(No<Aluno> Atual, int matricula, int percorridos) {
        No<Aluno> resultado;
        Aluno falha = new Aluno(-1, " ", " ");
        resultado = new No<Aluno>(falha);
        if (Atual.getElemento().getMatricula() == matricula) {
            resultado = Atual;
            System.out.println("Elementos percorridos: " + percorridos);
        } else {
            percorridos++;
            if ((Atual.getElemento().getMatricula() > matricula) && (Atual.getEsquerda() != null)) {
                Atual = Atual.getEsquerda();
                resultado = encontraMatricula(Atual, matricula, percorridos);
            } else if (Atual.getDireita() != null) {
                Atual = Atual.getDireita();
                resultado = encontraMatricula(Atual, matricula, percorridos);
            }
        }
        return resultado;
    }
}
