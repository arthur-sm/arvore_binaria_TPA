/*
 * @author: Cleber de Jesus Salustiano 
 * @author: Arthur Miguel
 */
package arvoreBinaria;

public class ArvoreBinaria<T extends Comparable> {
  private No<T> raiz;

  public No<T> getRaiz() {
    return raiz;
  }

  public void setRaiz(No<T> raiz) {
    this.raiz = raiz;
  }

  private No<T> comparaNoAtualComProximo(No<T> noPosicao, No<T> proximoNo, T elemento) {
    if (noPosicao.getElemento().compareTo(elemento) > 0) {
      proximoNo = noPosicao.getEsquerda();
    } else if (noPosicao.getElemento().compareTo(elemento) == 0) {
      proximoNo = null;
    } else {
      proximoNo = noPosicao.getDireita();
    }

    return proximoNo;
  }


  /* Insere um novo elemento (objeto) dentro da arvore */
  public boolean insere(T elemento) {
    No<T> novoNo = new No<T>(elemento);
    if (raiz == null) {
      raiz = novoNo;
      return true;
    } else {
      No<T> noPosicao = this.raiz;
      No<T> proximoNo = noPosicao;
      while (proximoNo != null) {
        noPosicao = proximoNo;
        proximoNo = comparaNoAtualComProximo(noPosicao, proximoNo, elemento);
      }
      if (noPosicao.getElemento().compareTo(elemento) > 0) {
        noPosicao.setEsquerda(novoNo);
        return true;
      } else if (noPosicao.getElemento().compareTo(elemento) < 0) {
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
    No<T> noPosicao = this.raiz;
    No<T> proximoNo = noPosicao;
    while (proximoNo != null) {
      noPosicao = proximoNo;
      proximoNo = comparaNoAtualComProximo(noPosicao, proximoNo, elemento);
    }
    if (noPosicao.getElemento().compareTo(elemento) == 0) {
      return true;
    }
    return false;
  }

  private void setaValorDoNoAnterior (No<T> noAnterior, No<T> noPosicao, No<T>noPosterior) {
    if (noAnterior == null) {
      this.raiz = noPosterior;
    } else if (noAnterior != null && noAnterior.getDireita().getElemento().compareTo(noPosicao.getElemento()) == 0) {
      noAnterior.setDireita(noPosterior);
    } else {
      noAnterior.setEsquerda(noPosterior);
    }
  }

  public boolean remove(T elemento) {
    No<T> noPosicao = this.raiz;
    No<T> proximoNo = noPosicao;
    No<T> noAnterior = null;
    boolean encontrouNo = false;
    while (!encontrouNo && proximoNo != null) {
      proximoNo = comparaNoAtualComProximo(noPosicao, proximoNo, elemento);
      if (noPosicao.getElemento().compareTo(elemento) == 0) {
        encontrouNo = true;
      }
      if (!encontrouNo) {
        noAnterior = noPosicao;
        noPosicao = proximoNo;
      }
    }
    if (!encontrouNo) {
      return false;
    } else if (noPosicao.getDireita() != null) {
      No<T> noBuscado = noPosicao.getDireita();
      proximoNo = noBuscado;
      while (proximoNo.getEsquerda() != null) {
        noBuscado = proximoNo;
        proximoNo = noBuscado.getEsquerda();
      }
      setaValorDoNoAnterior(noAnterior, noPosicao, noBuscado);
      noBuscado.setEsquerda(noPosicao.getEsquerda());
      noPosicao = null;
      return true;
    } else if (noPosicao.getEsquerda() != null){
      setaValorDoNoAnterior(noAnterior, noPosicao, noPosicao.getEsquerda());
      noPosicao = null;
      return true;
    } else {
      setaValorDoNoAnterior(noAnterior, noPosicao, null);
      return true;
    }
  }

  public int getAltura() {
    No<T> no = this.raiz;
    return altura(no);
  }

  private int altura(No<T> no) {
    if (no == null) {
      return -1;
    } else {
      int esquerda = altura(no.getEsquerda());
      int direita = altura(no.getDireita());
      if (esquerda > direita) {
        return esquerda + 1;
      } else {
        return direita + 1;
      }    
    }
  }

}
