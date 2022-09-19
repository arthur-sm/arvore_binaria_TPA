/*
 * @author: Cleber de Jesus Salustiano 
 * @author: Arthur Miguel
 */
package arvoreBinaria;

import java.util.ArrayList;

public class ArvoreBinaria<T extends Comparable> {
  private No<T> raiz;
  private int altura;
  private T piorCaso;

  /**
   * Retorna a raiz da árvore
   * 
   * @return
   */
  public No<T> getRaiz() {
    return raiz;
  }

  /**
   * Permite alterar a raíz da árvore
   * 
   * @param raiz
   */
  public void setRaiz(No<T> raiz) {
    this.raiz = raiz;
  }

  /**
   * Essa função é utilizada dentro
   * das funções da classe, onde faz a comparação
   * informando se o elemento está ou não em um nó.
   * Caso esteja o próximo nó será null, caso não esteja
   * será verificado se o valor do elemento é maior ou menor
   * que o valor existente no nó.
   * 
   * @param noPosicao
   * @param elemento
   * @return
   */
  private No<T> comparaNoAtualComProximo(No<T> noPosicao, T elemento) {
    No<T> proximoNo = null;
    if (noPosicao.getElemento().compareTo(elemento) > 0) {
      proximoNo = noPosicao.getEsquerda();
    } else if (noPosicao.getElemento().compareTo(elemento) == 0) {
      proximoNo = null;
    } else {
      proximoNo = noPosicao.getDireita();
    }
    return proximoNo;
  }

  /**
   * Essa função insere um novo
   * elemento (objeto) dentro da arvore
   * 
   * @param elemento
   * @return
   */
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
        proximoNo = comparaNoAtualComProximo(noPosicao, elemento);
      }
      if (noPosicao.getElemento().compareTo(elemento) > 0) {
        noPosicao.setEsquerda(novoNo);
        return true;
      } else if (noPosicao.getElemento().compareTo(elemento) < 0) {
        noPosicao.setDireita(novoNo);
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Essa função busca por um elemento na árvore
   * retornando o objeto se existir e null se o elemento
   * não existir na árvore.
   * 
   * @param elemento
   * @return
   */
  public T busca(T elemento) { // mudar nome para algo como 'Existe'?
    if (raiz.getElemento().compareTo(elemento) == 0) {
      return raiz.getElemento();
    }
    No<T> noPosicao = this.raiz;
    No<T> proximoNo = noPosicao;
    while (proximoNo != null) {
      noPosicao = proximoNo;
      proximoNo = comparaNoAtualComProximo(noPosicao, elemento);
    }
    if (noPosicao.getElemento().compareTo(elemento) == 0) {
      return noPosicao.getElemento();
    }
    return null;
  }

  /**
   * Essa função remove um elemento
   * dentro da árvore retornando true
   * se ele foi excluído. Se ele não existir
   * na árvore ou não tiver sido excluído, retorna false.
   * 
   * @param elemento
   * @return
   */
  public boolean remove(T elemento) {
    if (busca(elemento) == null) { //verifica se o elemento existe na árvore
      return false;
    } else {
      No<T> no = this.raiz;
      no = removendo(no, elemento);
      if (busca(elemento) == null) { //verifica se o elemento foi excluído
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Essa função é utilizada função remover
   * para ser feito uma recursão, onde busca pelo elemento
   * e ao excluir reorganiza a árvore, de forma a manter todos os
   * elementos.
   * 
   * @param no
   * @param elemento
   * @return No<T>
   */
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

  /**
   * Retorna a altura da arvore
   * 
   * @return
   */
  public int getAltura() {
    No<T> no = this.raiz;
    this.altura = altura(no);
    return this.altura;
  }

  /**
   * Essa função é utilizada
   * de forma recursiva para fazer o calculo da
   * raíz da árvore. Utilizada pela função getAltura.
   * 
   * @param no
   * @return
   */
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

  /**
   * Essa função retorna um ArrayList,
   * em ordem crescente, dos elementos existentes
   * dentro da árvore
   * 
   * @return
   */
  public ArrayList<T> caminhaEmOrdem() {
    No<T> no = this.raiz;
    ArrayList<T> emOrdem = new ArrayList<>();
    caminhandoEmOrdem(no, emOrdem);

    return emOrdem;
  }

  /**
   * Essa função é utilizada pela função
   * caminhaEmOrdem, onde essa faz o ArrayList
   * de forma recursiva.
   * 
   * @param no
   * @param emOrdem
   */
  private void caminhandoEmOrdem(No<T> no, ArrayList<T> emOrdem) {
    if (no != null) {
      if (no.getElemento() != null)
        caminhandoEmOrdem(no.getEsquerda(), emOrdem);
      emOrdem.add(no.getElemento());
      if (no.getDireita() != null)
        caminhandoEmOrdem(no.getDireita(), emOrdem);
    }
  }

  /**
   * Essa função retorna um ArrayList
   * dos elementos da árvore em ordem dos níveis
   * existentes dentro da árvore.
   * 
   * @return
   */
  public ArrayList<T> caminhaEmNivel() {
    No<T> no = this.raiz;
    ArrayList<T> emNivel = new ArrayList<>();
    caminhandoEmNivel(no, emNivel);

    return emNivel;
  }

  /**
   * Essa função é utilizada pela função
   * caminhaEmNivel, onde essa faz o ArrayList
   * de forma recursiva.
   * 
   * @param no
   * @param emNivel
   */
  private void caminhandoEmNivel(No<T> no, ArrayList<T> emNivel) {
    if (no != null) {
      emNivel.add(no.getElemento());
      if (no.getElemento() != null)
        caminhandoEmNivel(no.getEsquerda(), emNivel);
      if (no.getDireita() != null)
        caminhandoEmNivel(no.getDireita(), emNivel);
    }
  }

  /**
   * Essa função retorna a quantidade de elementos
   * existentes na arvore.
   * 
   * @return
   */
  public int getQuantidadeElementos() {
    ArrayList<T> elementos = caminhaEmOrdem();
    return elementos.size();
  }

  /**
   * Essa função retorna o menor elemento
   * da árvore.
   * 
   * @return
   */
  public T getMenorElemento() {
    No<T> no = this.raiz;
    No<T> proximoNo = no;
    while (proximoNo != null) {
      no = proximoNo;
      proximoNo = proximoNo.getEsquerda();
    }
    return no.getElemento();
  }

  /**
   * Essa função retorna o maior elemento
   * da árvore
   * 
   * @return
   */
  public T getMaiorElemento() {
    No<T> no = this.raiz;
    No<T> proximoNo = no;
    while (proximoNo != null) {
      no = proximoNo;
      proximoNo = proximoNo.getDireita();
    }
    return no.getElemento();
  }

   /**
   * Essa função um ArrayList contendo
   * os piores casos de busca dentro da árvore
   * 
   * @return
   */
  public T getPiorCaso() {
    No<T> no = this.raiz;
    getAltura();
    this.piorCaso = null;
    buscandoPiorCaso(no, 0, this.piorCaso);
    return this.piorCaso;
  }

  /**
   * Essa função busca pelo primeiro pior caso
   * existente dentro da árvore, ou seja,
   * o primeiro elemento que possui o nível
   * ígual a altura da árvore.
   * Utilizado pela função pioresCasos()
   * 
   * @param no
   * @param nivel
   * @param pioresCasos
   * @return
   */
  private T buscandoPiorCaso(No<T> no, int nivel, T piorCaso) {
    if (no != null) {
      if (this.altura == nivel && piorCaso == null) {
        piorCaso = no.getElemento();
        this.piorCaso = no.getElemento();
      }
      nivel++;
      if (no.getElemento() != null)
        buscandoPiorCaso(no.getEsquerda(), nivel, piorCaso);
      if (no.getDireita() != null)
        buscandoPiorCaso(no.getDireita(), nivel, piorCaso);
    }
    return null;

  }

  
  /**
   * Uma versão alternativa do getPiorCaso
   * que retorna todos os piores casos de busca
   * 
   * */
  public ArrayList<T> getPioresCasos() {
    No<T> no = this.raiz;
    ArrayList<T> pioresCasos = new ArrayList<>();
    getAltura();
    buscandoPioresCasos(no, 0, pioresCasos);
    return pioresCasos;
  }

  /**
   * Uma versão alternativa do buscandoPiorCaso
   * que retorna todos os piores casos de busca
   * 
   * @param no
   * @param nivel
   * @param pioresCasos
   * @return
   */
  private T buscandoPioresCasos(No<T> no, int nivel, ArrayList<T> pioresCasos) {
    if (no != null) {
      if (this.altura == nivel) {
        pioresCasos.add(no.getElemento());
      }
      nivel++;
      if (no.getElemento() != null)
        buscandoPioresCasos(no.getEsquerda(), nivel, pioresCasos);
      if (no.getDireita() != null)
        buscandoPioresCasos(no.getDireita(), nivel, pioresCasos);
    }
    return null;

  }

}
