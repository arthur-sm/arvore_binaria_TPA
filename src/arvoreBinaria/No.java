package arvoreBinaria;

import java.lang.reflect.Constructor;

public class No<T extends Comparable> {
  private No<T> direita;
  private No<T> esquerda;
  private int altura;
  private T elemento;

  public T getElemento() {
    return elemento;
  }

  public void setElemento(T elemento) {
    this.elemento = elemento;
  }

  public No<T> getDireita() {
    return direita;
  }

  public No<T> getEsquerda() {
    return esquerda;
  }

  public int getAltura() {
    return obterAltura(this);
  }

  private int obterAltura(No<T> r) {
    if (r == null) {
      return -1;
    } else {
      int hd = obterAltura(r.direita);
      int he = obterAltura(r.esquerda);
      if (hd > he) {
        return hd + 1;
      } else {
        return he + 1;
      }
    }
  }

  public int fatorBalanceamento(){
    return obterAltura(this.direita) - obterAltura(this.esquerda);
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }

  public void setDireita(No<T> direita) {
    this.direita = direita;
  }

  public void setEsquerda(No<T> esquerda) {
    this.esquerda = esquerda;
  }

  public No(T elemento) {
    this.elemento = elemento;
  }

  // @Override
  // public String toString() {
  // return "\nElemento: " + elemento + "\nEsquerda do elemento "+ elemento + ": "
  // + esquerda + "\nDireita do elemento "+ elemento + ": " + direita;
  // }
}
