/*
 * @author: Cleber de Jesus Salustiano 
 * @author: Arthur Miguel
 */
package arvoreBinaria;

import java.util.ArrayList;

public class ArvoreBinaria<T extends Comparable> {
  private No<T> raiz;

  public No<T> getRaiz() {
    return raiz;
  }

  public void setRaiz(No<T> raiz) {
    this.raiz = raiz;
  }

  private No<T> comparaNoAtualComProximo(No<T> noPosicao, No<T> proximoNo, T elemento) {
    /*
     * ? Não seria interessante substituir esse parâmetro 'proximoNo' por uma
     * ? variável interna da função?
     * ? da forma como está implementado agora, causou um pouco de confusão porque,
     * ? até onde entendi, o valor passado no parâmetro é irrelevante
     */
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
        /*
         * Pensar numa forma de retornar isso.
         * Pensei em provavelmente um Boolean, para falso, caso não seja inserido.
         * ! acredito que essa seja a melhor opção
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

  private void setaValorDoNoAnterior(No<T> noAnterior, No<T> noPosicao, No<T> noPosterior) {
    if (noAnterior == null) {
      this.raiz = noPosterior;
    } else if (noAnterior != null && noAnterior.getDireita() != null
        && noAnterior.getDireita().getElemento().compareTo(noPosicao.getElemento()) == 0) {
      noAnterior.setDireita(noPosterior);
    } else {
      noAnterior.setEsquerda(noPosterior);
    }
  }

  public boolean remove(T elemento) {
    No<T> no = this.raiz;
    no = removendo(no, elemento);
    if (no == null) {
      return false;
    } else {
      return true;
    }
  }

  private No<T> removendo(No<T> no, T elemento) {
    if (no == null) {
      return null;
    } else if (no.getElemento().compareTo(elemento) > 0) {
      no.setEsquerda(removendo(no.getEsquerda(), elemento));
      return no;
    } else if (no.getElemento().compareTo(elemento) < 0) {
      no.setDireita(removendo(no.getDireita(), elemento));
      return no;
    } else if (no.getEsquerda() == null && no.getDireita() == null) {
      no = null;
      return null;
    } else if (no.getEsquerda() != null && no.getDireita() != null) {
      No<T> proximoNo = no.getEsquerda();
      while (proximoNo.getDireita() != null) {
        proximoNo = proximoNo.getDireita();
      }
      no.setElemento(proximoNo.getElemento());
      proximoNo.setElemento(elemento);
      no.setEsquerda(removendo(no.getEsquerda(), elemento));
      return no;
    } else {
      No<T> proximoNo;
      if (no.getEsquerda() != null)
        proximoNo = no.getEsquerda();
      else
        proximoNo = no.getDireita();
      return proximoNo;
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

  public ArrayList<T> caminhaEmOrdem() {
    No<T> no = this.raiz;
    ArrayList<T> emOrdem = new ArrayList<>();
    caminhandoEmOrdem(no, emOrdem);

    return emOrdem;
  }

  private void caminhandoEmOrdem(No<T> no, ArrayList<T> emOrdem) {
    if (no != null) {
      if (no.getElemento() != null)
        caminhandoEmOrdem(no.getEsquerda(), emOrdem);
      emOrdem.add(no.getElemento());
      if (no.getDireita() != null)
        caminhandoEmOrdem(no.getDireita(), emOrdem);
    }
  }

  public ArrayList<T> caminhaEmNivel() {
    No<T> no = this.raiz;
    ArrayList<T> emNivel = new ArrayList<>();
    caminhandoEmNivel(no, emNivel);

    return emNivel;
  }

  private void caminhandoEmNivel(No<T> no, ArrayList<T> emNivel) {
    if (no != null) {
      emNivel.add(no.getElemento());
      if (no.getElemento() != null)
        caminhandoEmNivel(no.getEsquerda(), emNivel);
      if (no.getDireita() != null)
        caminhandoEmNivel(no.getDireita(), emNivel);
    }
  }

  public int getQuantidadeElementos() {
    ArrayList<T> elementos = caminhaEmOrdem();
    return elementos.size();
  }

  public T getMenorElemento() {
    No<T> no = this.raiz;
    No<T> proximoNo = no;
    while (proximoNo != null) {
      no = proximoNo;
      proximoNo = proximoNo.getEsquerda();
    }
    return no.getElemento();
  }

  public T getMaiorElemento() {
    No<T> no = this.raiz;
    No<T> proximoNo = no;
    while (proximoNo != null) {
      no = proximoNo;
      proximoNo = proximoNo.getDireita();
    }
    return no.getElemento();
  }

  public ArrayList<T> getPioresCasos() {
    No<T> no = this.raiz;
    ArrayList<T> pioresCasos = new ArrayList<>();
    buscandoPiorCaso(no, 0, pioresCasos);
    return pioresCasos;
  }

  private T buscandoPiorCaso(No<T> no, int nivel, ArrayList<T> pioresCasos) {
    if (no != null) {
      if (this.getAltura() == nivel) {
        pioresCasos.add(no.getElemento());
      }
      nivel++;
      if (no.getElemento() != null)
        buscandoPiorCaso(no.getEsquerda(), nivel, pioresCasos);
      if (no.getDireita() != null)
        buscandoPiorCaso(no.getDireita(), nivel, pioresCasos);
    }
    return null;

  }

}
