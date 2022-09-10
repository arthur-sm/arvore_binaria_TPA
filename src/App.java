
/**********
   @ Autor: Arthur Miguel e Cleber de Jesus Salustiano
   @ Criado em: 07/09/2022 11:00
   @ Editado por: Arthur SM
   @ Data da edição: 10/09/22 17:23:39
   @ Descrição: Código de aplicação da Primeira etapa do trabalho prático de árvores
 **********/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import arvoreBinaria.ArvoreBinaria;
import arvoreBinaria.No;
import arvoreBinariaAluno.Aluno;

public class App {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria<Aluno> arvore = new ArvoreBinaria<>();
        try {
            FileReader arq = new FileReader("./teste/teste_10000.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String value = lerArq.readLine();
            String linha = lerArq.readLine();
            for (int i = 0; i < Integer.parseInt(value); i++) {
                String[] linhaSplit = linha.split(";");
                arvore.insere(new Aluno(Integer.parseInt(linhaSplit[0]), linhaSplit[1], linhaSplit[2]));

                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        Menu(arvore);
        /*
         * testes
         * System.out.println(arvore.getRaiz());
         * System.out.println(arvore.getAltura());
         * System.out.println(arvore.quantidadeElementos());
         * System.out.println(arvore.caminhaEmNivel());
         * System.out.println(arvore.remove(new Aluno(6062803, "Melissa Edwards",
         * "68")));
         * System.out.println(arvore.quantidadeElementos());
         * System.out.println(arvore.caminhaEmOrdem());
         * System.out.println(arvore.getMaiorElemento());
         * System.out.println(arvore.getMenorElemento());
         * 
         * System.out.println(arvore.getPioresCasos());
         * System.out.println(arvore.getAltura());
         * // arvore.insere(9);
         * // arvore.insere(3);
         * // arvore.insere(4);
         * // arvore.insere(2);
         * // arvore.insere(6);
         * // arvore.insere(10);
         * // arvore.insere(9);
         * // arvore.insere(5);
         * // arvore.insere(7);
         * // arvore.insere(1);
         * // System.out.println(arvore.getRaiz());
         * // System.out.println("\nRemovendo elemento 3");
         * // arvore.remove(3);
         * // System.out.println(arvore.getRaiz());
         * // arvore.insere(3);
         * // arvore.remove(9);
         * // System.out.println("\nRemovendo elemento 9");
         * // System.out.println(arvore.getRaiz());
         * // System.out.println(arvore.getAltura());
         * // System.out.println(arvore.insere(10));
         * 
         * // ArrayList<Integer> teste = arvore.caminhaEmOrdem();
         * // System.out.println(teste);
         * // teste = arvore.caminhaEmNivel();
         * // System.out.println(teste);
         * 
         * // Aluno cleber = new Aluno(1234, "Cleber", "20");
         * // Aluno brenno = new Aluno(1233, "Brenno", "20");
         * // Aluno caio = new Aluno(1235, "Caio", "20");
         * // Aluno casio = new Aluno(1232, "Casio", "20");
         * // arvore.insere(cleber);
         * // arvore.insere(caio);
         * // arvore.insere(brenno);
         * // //System.out.println("Matrícula Cleber:" + cleber.getMatricula());
         * // //System.out.println("Matrícula Cleber:" +
         * // arvore.getRaiz().getElemento().getMatricula());
         * // System.out.println("Raiz: " + arvore.getRaiz());
         * // System.out.println("Casio existe na árvore: " + arvore.busca(casio));
         * // arvore.insere(casio);
         * // arvore.remove(brenno);
         * // System.out.println("Casio existe na árvore: " + arvore.busca(casio));
         * // System.out.println("Raiz: " + arvore.getRaiz());
         * // System.out.println("Testando procura por matrícula:");
         * // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 1234,
         * // 0).getElemento());
         * // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 1235,
         * // 0).getElemento());
         * // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 1233,
         * // 0).getElemento());
         * // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 1232,
         * // 0).getElemento());
         * // System.out.println("Procurando pela matrícula 9999");
         * // System.out.println("\n" + encontraMatricula(arvore.getRaiz(), 9999,
         * // 0).getElemento());
         */
    }

    public static void Menu(ArvoreBinaria<Aluno> arvore) {
        Scanner userinput = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "Escolha uma opção: \n1 - Estatísticas\n2 - Busca por Matrícula\n3 - Exclusão por Matrícula\n4 - Incluir Aluno\n5 - Sair");
            int escolha = userinput.nextInt();
            if (escolha == 1) {
                // Exibir estatísticas – Deve exibir a quantidade total de elementos, a altura
                // da árvore, o maior e o menor elemento e o pior caso de busca.
                System.out.println("\nQuantidade total de elementos da árvore: " + arvore.quantidadeElementos());
                System.out.println("Altura da árvore: " + arvore.getAltura());
                /*
                 * TODO: imprimir maior e menor elementos utilizando a função imprimeAluno()
                 */
                System.out.println("   Maior elemento: " + arvore.getMaiorElemento());
                System.out.println("   Menor elemento: " + arvore.getMenorElemento());
                System.out.println("   Piores casos de busca: " + arvore.getPioresCasos() + "\n");
            } else if (escolha == 2) {
                System.out.println("   Digite o número da matrícula a ser procurada: ");
                int matricula = userinput.nextInt();
                imprimeMatricula(encontraMatricula(arvore, matricula, 0).getElemento(), matricula);
            } else if (escolha == 3) {
                System.out.println("Digite o número da matrícula a ser excluída: ");
                int matricula = userinput.nextInt();
                removeMatricula(arvore, matricula);
            } else if (escolha == 5) {
                // Sair: o programa deve percorrer a árvore usando caminhamento "em ordem" e
                // gerar um arquivo em que cada linha apresentará a matrícula, o nome e a nota
                // de um aluno, sempre separados por ;.
                System.out.println("Caminhando pela árvore em ordem: \n" + arvore.caminhaEmOrdem());
                userinput.close();
                break;
            }
        }
    }

    public static void imprimeAluno(Aluno aluno) {
        System.out.println("      Matrícula: " + aluno.getMatricula() + "\n      Nome: "
                + aluno.getNome() + "\n      Nota: " + aluno.getNota() + "\n");
    }

    /**
     * @param arvore      - Arvore binária onde a matrícula deve ser procurada
     * @param matricula   - inteiro que representa a matrícula a ser procurada
     * @param percorridos - inteiro, utilize 0 quando desejar que o número de
     *                    elementos percorridos até chegar à matricula desejada seja
     *                    contabilizado/impresso ou -1 para que não seja
     */
    public static No<Aluno> encontraMatricula(ArvoreBinaria<Aluno> arvore, int matricula, int percorridos) {
        No<Aluno> Atual = arvore.getRaiz();
        No<Aluno> resultado = null;
        Aluno falha = new Aluno(-1, " ", " ");
        while (resultado == null) {
            if (Atual.getElemento().getMatricula() == matricula) {
                resultado = Atual;
                if (percorridos >= 0)
                    System.out.println("   Elementos percorridos: " + percorridos);
            } else {
                if (percorridos >= 0)
                    percorridos++;
                if ((Atual.getElemento().getMatricula() > matricula) && (Atual.getEsquerda() != null))
                    Atual = Atual.getEsquerda();
                else if ((Atual.getElemento().getMatricula() < matricula) && (Atual.getDireita() != null))
                    Atual = Atual.getDireita();
                else
                    resultado = new No<Aluno>(falha);
            }
        }
        return resultado;
    }

    /**
     * @param Cadastro  - Nó que contem o aluno com as informações que devem ser
     *                  inseridas
     * @param matricula - inteiro com valor da matrícula da estrutura Aluno no Nó
     */
    public static void imprimeMatricula(Aluno Cadastro, int matricula) {
        if (Cadastro.getMatricula() == -1) {
            System.out.println("   Matricula [" + matricula + "] não encontrada\n");
        } else {
            System.out.println("   Dados da matrícula:");
            imprimeAluno(Cadastro);
        }
    }

    public static void removeMatricula(ArvoreBinaria<Aluno> arvore, int matricula) {
        No<Aluno> Alvo = encontraMatricula(arvore, matricula, -1);
        imprimeMatricula(Alvo.getElemento(), matricula);
        arvore.remove(Alvo.getElemento());
        System.out.println("   Matrícula [" + matricula + "] excluída!\n");
    }
}
