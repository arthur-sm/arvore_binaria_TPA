import arvoreBinaria.ArvoreBinaria;
import arvoreBinariaAluno.Aluno;

public class App {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria<Aluno> arvore = new ArvoreBinaria<>();
        Aluno cleber = new Aluno(1234, "Cleber", "20");
        Aluno brenno = new Aluno(1233, "Brenno", "20");
        Aluno caio = new Aluno(1235, "Caio", "20");
        Aluno casio = new Aluno(1232, "Casio", "20");
        arvore.inserir(cleber);
        arvore.inserir(caio);
        arvore.inserir(brenno);
        System.out.println(arvore.busca(casio));
        arvore.inserir(casio);
        System.out.println(arvore.busca(casio));
        System.out.println(arvore.getRaiz());
    }
}
