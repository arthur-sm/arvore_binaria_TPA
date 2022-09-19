import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import arvoreBinaria.ArvoreBinaria;
import arvoreBinariaAluno.Aluno;

public class Testes {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria<Aluno> arvore = new ArvoreBinaria<>();
        long tempoInicial = System.currentTimeMillis();
        try {
            FileReader arq = new FileReader("./teste/entradaBalanceada10.txt");
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
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo do programa gasto para carregar o arquivo: " + (tempoFinal - tempoInicial) + "ms");
        System.out.println(arvore.getAltura() - 1);
        tempoInicial = System.currentTimeMillis();
        System.out.println(arvore.getPiorCaso());
        tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo do programa gasto para ir até o pior caso: " + (tempoFinal - tempoInicial) + "ms");
        // Menu(arvore);
        // System.out.println(arvore.getQuantidadeElementos());
        // System.out.println(arvore.remove(new Aluno(309, "nome", "nota")));
        // Aluno aoba = new Aluno(589, "", "nota");
        // Aluno talis = new Aluno(11971, "", "nota");
        // Aluno carlos = new Aluno(10045, "", "nota");
        // Aluno brenno = new Aluno(3463, "", "nota");
        // arvore.remove(aoba);
        // arvore.remove(talis);
        // arvore.remove(carlos);
        // arvore.remove(brenno);
        // System.out.println(arvore.getQuantidadeElementos());
        // System.out.println(arvore.getRaiz());
        // System.out.println(arvore.getRaiz());
        // System.out.println(arvore.getAltura());
        // System.out.println(arvore.caminhaEmNivel());
        // System.out.println(arvore.remove(new Aluno(6062803, "Melissa Edwards", "68")));
        // System.out.println(arvore.caminhaEmOrdem());
        // System.out.println(arvore.getMaiorElemento());
        // System.out.println(arvore.getMenorElemento());
        // System.out.println(arvore.getPioresCasos());
        // System.out.println(arvore.getAltura());
    }

    public static void Menu(ArvoreBinaria<Aluno> arvore) {
        Scanner userinput = new Scanner(System.in);
        int matricula;
        String nome, nota;
        Aluno aluno_novo = null;
        while (true) {
            System.out.println(
                    "Escolha uma opção: \n1 - Estatísticas\n2 - Busca por Matrícula\n3 - Exclusão por Matrícula\n4 - Incluir Aluno\n5 - Sair");
            int escolha = userinput.nextInt();
            if (escolha == 1) {
                estatisticas(arvore);
            } else if (escolha == 2) {
                System.out.print("Digite o número da matrícula a ser procurada: ");
                matricula = userinput.nextInt();
                imprimeMatricula(arvore, matricula);
            } else if (escolha == 3) {
                System.out.print("Digite o número da matrícula a ser excluída: ");
                matricula = userinput.nextInt();
                removeMatricula(arvore, matricula);
            } else if (escolha == 4) {
                System.out.print("Digite a matrícula: ");
                matricula = userinput.nextInt();
                System.out.print("Digite o nome: ");
                userinput.nextLine(); // por algum motivo esse input é necessário para conseguir digitar o nome e
                                      // receber o valor de acordo
                nome = userinput.nextLine();
                System.out.print("Digite a nota: ");
                nota = userinput.nextLine();
                aluno_novo = new Aluno(matricula, nome, nota);
                if (arvore.insere(aluno_novo) == true)
                    System.out.println("Novo Aluno [" + aluno_novo.toString() + "] inserido com sucesso\n");
                else
                    System.out.println(
                            "Erro ao cadastrar aluno [" + aluno_novo.toString() + "] - Matrícula já existente\n");
            } else if (escolha == 5) {
                System.out.println("\nGerando arquivo com elementos da árvor em ordem...");
                escreveArvore("arvore_ordem", "txt", arvore);
                userinput.close();
                break;
            }
        }
    }

    public static void estatisticas(ArvoreBinaria<Aluno> arvore) {
        System.out.println(" Quantidade total de elementos da árvore: " + arvore.getQuantidadeElementos());
        System.out.println(" Altura da árvore: " + arvore.getAltura());
        System.out.println(" Maior elemento: ");
        System.out.println(" Maior elemtento: [ " + arvore.getMaiorElemento().toString() + "] ");
        System.out.println(" Menor elemtento: [ " + arvore.getMenorElemento().toString() + "] ");
        System.out.println(" Piores casos de busca: " + arvore.getPiorCaso() + "\n");
    }

    public static void escreveArvore(String nomearquivo, String ext, ArvoreBinaria<Aluno> conteudo) {
        // Tempo em milisegundos divido por 60000 -> tempo em minutos
        long d = System.currentTimeMillis() / 60000;
        String arquivo = nomearquivo + "_" + d + "." + ext;
        try {
            FileWriter myWriter = new FileWriter(arquivo);
            conteudo.caminhaEmOrdem().forEach(matricula -> {
                try {
                    myWriter.write(
                            matricula.getMatricula() + ";" + matricula.getNome() + ";" + matricula.getNota() + "\n");
                } catch (IOException e) {
                    System.out.println("Erro ao escrever elemento " + matricula.toString());
                    e.printStackTrace();
                }
            });
            myWriter.close();
            System.out.println("Arquivo de árvore em ordem [" + arquivo + "] gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao escrever/gerar arquivo " + nomearquivo);
            e.printStackTrace();
        }
    }

    public static void imprimeMatricula(ArvoreBinaria<Aluno> arvore, int matricula) {
        Aluno alvo = new Aluno(matricula, "", "");
        Aluno alunoRetorno = arvore.busca(alvo);
        if (alunoRetorno == null)
            System.out.println("Matrícula [" + matricula + "] não encontrada!");
        else {
            System.out.println("   Aluno: [" + alunoRetorno.toString() + "]\n");
        }
    }

    /**
     * @param arvore      - Arvore binária onde a matrícula deve ser procurada
     * @param matricula   - inteiro que representa a matrícula a ser procurada
     * @param percorridos - inteiro, utilize 0 quando desejar que o número de
     *                    elementos percorridos até chegar à matricula desejada seja
     *                    contabilizado/impresso ou -1 para que não seja
     */

    public static void removeMatricula(ArvoreBinaria<Aluno> arvore, int matricula) {
        Aluno alvo = new Aluno(matricula, "", "");
        Aluno alunoBuscado = arvore.busca(alvo);
        if (alunoBuscado == null)
            System.out.println("Matrícula [" + matricula + "] não encontrada!\n");
        else {
            arvore.remove(alunoBuscado);
            System.out.println("   Aluno [" + alunoBuscado.toString() + "] excluído!\n");
        }
    }
}
