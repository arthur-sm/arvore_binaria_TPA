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
  public boolean insere(T elemento) {
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
        if (noPosicao.getElemento().compareTo(elemento) > 0) {
          proximoNo = noPosicao.getEsquerda();
        } else if (noPosicao.getElemento().compareTo(elemento) == 0) {
          proximoNo = null;
        } else {
          proximoNo = noPosicao.getDireita();
        }
      }
      if (noPosicao.getElemento().compareTo(elemento) != 0 && (alturaAuxiliar + 1) > this.altura)
        this.altura = alturaAuxiliar + 1;
      
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
      if (noPosicao.getElemento().compareTo(elemento) > 0) {
        proximoNo = noPosicao.getEsquerda();
      } else if (noPosicao.getElemento().compareTo(elemento) == 0) {
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
    No<T> noPosicao = this.raiz;
    No<T> proximoNo = noPosicao;
    No<T> noAnterior = null;
    boolean encontrouNo = false;
    while (!encontrouNo && proximoNo != null) {
      if (noPosicao.getElemento().compareTo(elemento) > 0) {
        proximoNo = noPosicao.getEsquerda();
      } else if (noPosicao.getElemento().compareTo(elemento) == 0) {
        encontrouNo = true;
      } else {
        proximoNo = noPosicao.getDireita();
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
      if(noAnterior != null && noAnterior.getDireita().getElemento().compareTo(noPosicao.getElemento()) == 0) {
        noAnterior.setDireita(noBuscado);
      } else if (noAnterior != null) {
        noAnterior.setEsquerda(noBuscado);
      } else {
        this.raiz = noBuscado;
      }
      noBuscado.setEsquerda(noPosicao.getEsquerda());
      noPosicao = null;
      return true;
    } else if (noPosicao.getEsquerda() != null){
      if(noAnterior != null && noAnterior.getDireita().getElemento().compareTo(noPosicao.getElemento()) == 0) {
        noAnterior.setDireita(noPosicao.getEsquerda());
      } else if (noAnterior != null) {
        noAnterior.setEsquerda(noPosicao.getEsquerda());
      }
      noPosicao = null;
      return true;
    } else {
      if(noAnterior != null && noAnterior.getDireita().getElemento().compareTo(noPosicao.getElemento()) == 0) {
        noAnterior.setDireita(null);
      } else if (noAnterior != null) {
        noAnterior.setEsquerda(null);
      }
      return true;
    }
  }

  

}
