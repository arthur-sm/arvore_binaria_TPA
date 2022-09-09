/*
 * @author: Cleber de Jesus Salustiano 
 * @author: Arthur Miguel
 */
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

  /* Insere um novo elemento (objeto) dentro da arvore */
  public boolean inserir(T elemento) {
    No<T> novoNo = new No<T>(elemento);
    if (raiz == null) {
      raiz = novoNo;
      return true;
    } else {
      int alturaAuxiliar = 0;
      No<T> noPosicao = this.raiz;
      No<T> proximoNo = noPosicao;
      while (proximoNo != null) {
        noPosicao = proximoNo;
        alturaAuxiliar++;
        if (noPosicao.getElemento().compareTo(novoNo.getElemento()) < 0) {
          proximoNo = noPosicao.getEsquerda();
        } else if (noPosicao.getElemento().compareTo(novoNo.getElemento()) == 0) {
          proximoNo = null;
        } else {
          proximoNo = noPosicao.getDireita();
        }
      }
      if (noPosicao.getElemento().compareTo(novoNo.getElemento()) != 0 && (alturaAuxiliar + 1) > this.altura)
        this.altura = alturaAuxiliar + 1;
      
      if (noPosicao.getElemento().compareTo(novoNo.getElemento()) < 0) {
        noPosicao.setEsquerda(novoNo);
        return true;
      } else if (noPosicao.getElemento().compareTo(novoNo.getElemento()) > 0) {
        noPosicao.setDireita(novoNo);
        return true;
      } else {
        /* Pensar numa forma de retornar isso.
         * Pensei em provavelmente um Boolean, para falso, caso não seja inserido.
         */
        // System.out.println("Esse elemento já existe na árvore");
        return false;
      }
    }
  }

  public boolean busca(T elemento) { // mudar nome para algo como 'Existe'?
    if (raiz.getElemento().compareTo(elemento) == 0) {
      return true;
    }
    No<T> novoNo = new No<T>(elemento);
    No<T> noPosicao = this.raiz;
    No<T> proximoNo = noPosicao;
    while (proximoNo != null) {
      noPosicao = proximoNo;
      if (noPosicao.getElemento().compareTo(novoNo.getElemento()) < 0) {
        proximoNo = noPosicao.getEsquerda();
      } else if (noPosicao.getElemento().compareTo(novoNo.getElemento()) == 0) {
        proximoNo = null;
      } else {
        proximoNo = noPosicao.getDireita();
      }
    }
    if (noPosicao.getElemento().compareTo(elemento) == 0) {
      return true;
    }
    return false;
  }

  public boolean remove(T elemento) {
    No<T> noSeraRemovido = new No<T>(elemento);
    if (noSeraRemovido == raiz) {
      //Fazer
    }
    return false;
  }

}
