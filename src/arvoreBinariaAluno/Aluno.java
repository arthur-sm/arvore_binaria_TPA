package arvoreBinariaAluno;

public class Aluno implements Comparable<Aluno> {
  private int matricula;
  private String nome;
  private String nota;

  @Override
  public int compareTo(Aluno aluno) {
    if (matricula > aluno.getMatricula()) {
      return -1;
    } else if (matricula == aluno.getMatricula()) {
      return 0;
    } else {
      return 1;
    }
  }

  public int getMatricula() {
    return matricula;
  }
  public String getNome() {
    return nome;
  }
  public String getNota() {
    return nota;
  }
  public void setMatricula(int matricula) {
    this.matricula = matricula;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setNota(String nota) {
    this.nota = nota;
  }
  public Aluno (int matricula, String nome, String nota) {
    this.matricula = matricula;
    this.nome = nome;
    this.nota = nota;
  }
  @Override
  public String toString() {
    return matricula + " " + nome + " " + nota + "\n";
  }
}
