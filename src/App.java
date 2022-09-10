import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import arvoreBinaria.ArvoreBinaria;
import arvoreBinaria.No;
import arvoreBinariaAluno.Aluno;

public class App {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria<Aluno> arvore = new ArvoreBinaria<>();

        try {
            FileReader arq = new FileReader("/home/cleber/Documents/TPA/ArvoreBinária/teste/teste_100.txt");
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
        System.out.println(arvore.getRaiz());
        // System.out.println(arvore.getAltura());
        // System.out.println(arvore.quantidadeElementos());
        System.out.println(arvore.caminhaEmNivel());
        // System.out.println(arvore.remove(new Aluno(6062803, "Melissa Edwards", "68")));
        // System.out.println(arvore.quantidadeElementos());
        System.out.println(arvore.caminhaEmOrdem());
        System.out.println(arvore.menorElemento());
        // arvore.insere(9);
        // arvore.insere(3);
        // arvore.insere(4);
        // arvore.insere(2);
        // arvore.insere(6);
        // arvore.insere(10);
        // arvore.insere(9);
        // arvore.insere(5);
        // arvore.insere(7);
        // arvore.insere(1);
        // System.out.println(arvore.getRaiz());
        // System.out.println("\nRemovendo elemento 3");
        // arvore.remove(3);
        // System.out.println(arvore.getRaiz());
        // arvore.insere(3);
        // arvore.remove(9);
        // System.out.println("\nRemovendo elemento 9");
        // System.out.println(arvore.getRaiz());
        // System.out.println(arvore.getAltura());
        // System.out.println(arvore.insere(10));

        // ArrayList<Integer> teste = arvore.caminhaEmOrdem();
        // System.out.println(teste);
        // teste = arvore.caminhaEmNivel();
        // System.out.println(teste);

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
