import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import arvoreBinaria.ArvoreAVL;
import arvoreBinaria.ArvoreBinaria;
import arvoreBinariaAluno.Aluno;

public class Relatorio {
  public static void main(String[] args) {
    ArvoreBinaria<Aluno> arvoreAVL = new ArvoreAVL<>();
    ArvoreBinaria<Aluno> arvore = new ArvoreBinaria<>();
    try {
      FileReader arq = new FileReader("./teste/entradaAleatoria25000.txt");
      BufferedReader lerArq = new BufferedReader(arq);

      String value = lerArq.readLine();
      String linha = lerArq.readLine();
      for (int i = 0; i < Integer.parseInt(value); i++) {
        String[] linhaSplit = linha.split(";");
        arvoreAVL.insere(new Aluno(Integer.parseInt(linhaSplit[0]), linhaSplit[1], linhaSplit[2]));
        arvore.insere(new Aluno(Integer.parseInt(linhaSplit[0]), linhaSplit[1], linhaSplit[2]));

        linha = lerArq.readLine(); // lê da segunda até a última linha
      }
      arq.close();
    } catch (IOException e) {
      System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage());
    }

    System.out.println("Arvore normal: "+ arvore.getAltura());
    System.out.println("Arvore AVL: " + arvoreAVL.getAltura());
  }
}
