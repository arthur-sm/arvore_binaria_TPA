package arvoreBinaria;

import java.lang.reflect.Constructor;

public class No <T extends Comparable> {
  private No<T> direita;
  private No<T> esquerda;
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
  public void setDireita(No<T> direita) {
    this.direita = direita;
  }
  public void setEsquerda(No<T> esquerda) {
    this.esquerda = esquerda;
  }
  public No (T elemento) {
    this.elemento = elemento;
  }

  @Override
  public String toString() {
    return "\nElemento: " + elemento + "\nEsquerda do elemento "+ elemento + ": " + esquerda + "\nDireita do elemento "+ elemento + ": " + direita;
  }
}
