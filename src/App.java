
/**********
   @ Autor: Arthur Miguel e Cleber de Jesus Salustiano
   @ Criado em: 07/09/2022 11:00
   @ Editado por: Arthur SM
   @ Data da edição: 26/09/22 08:59:16
   @ Descrição: Código de aplicação da Primeira etapa do trabalho prático de árvores
 **********/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import arvoreBinaria.ArvoreAVL;
import arvoreBinaria.ArvoreBinaria;
import arvoreBinaria.No;
import arvoreBinariaAluno.Aluno;

public class App {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria<Aluno> arvore = new ArvoreAVL<>();
        try {
            FileReader arq = new FileReader("./teste/entradaBalanceada200000.txt");
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

    public static void imprimeMatricula(ArvoreBinaria<Aluno> arvore, int matricula) {
        Aluno alunoRetorno = encontraMatricula(arvore, matricula, 0).getElemento();
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
