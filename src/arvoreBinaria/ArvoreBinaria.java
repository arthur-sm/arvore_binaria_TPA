package arvoreBinaria;

public class ArvoreBinaria<T extends Comparable> {
  private No<T> raiz;
  private int altura;

  public int getAltura() {
    return altura;
  }
  public void setAltura(int altura) {
    this.altura = altura;
  }  
  public No<T> getRaiz() {
    return raiz;
  }
  public void setRaiz(No<T> raiz) {
    this.raiz = raiz;
  }

  /*Insere um novo elemento (objeto) dentro da arvore*/
  public void inserir(T elemento) {
    No<T> novoNo = new No<T>(elemento);
    if (raiz == null) {
      raiz = novoNo;
    } 
    No<T> noPosicao = buscaNo(novoNo);
    if (noPosicao.getElemento().compareTo(novoNo.getElemento()) < 0) {
      noPosicao.setEsquerda(novoNo);
    } else if (noPosicao.getElemento().compareTo(novoNo.getElemento()) > 0) {
      noPosicao.setDireita(novoNo); 
    }
  }

  /* 
   * Busca o nó que possuí elementos iguais, ou folhas.
  */
  private No<T> buscaNo(No<T> novoNo) {
    No<T> no = this.raiz;
    No<T> noAuxiliar = no;
    while (noAuxiliar != null) {
      no = noAuxiliar;
      if (no.getElemento().compareTo(novoNo.getElemento()) < 0) {
        noAuxiliar = no.getEsquerda();
      } else if (no.getElemento().compareTo(novoNo.getElemento()) == 0) {
        noAuxiliar = null;
      }else{
        noAuxiliar = no.getDireita();
      }
    }
    return no;
  }

  public boolean busca(T elemento) {
    if (raiz.getElemento().compareTo(elemento) == 0) {
      return true;
    } 
    No<T> noAuxiliar = new No<T>(elemento);
    No<T> noPosicao = buscaNo(noAuxiliar);
    if (noPosicao.getElemento().compareTo(elemento) ==0){
      return true;
    }
    return false;
  }
}
