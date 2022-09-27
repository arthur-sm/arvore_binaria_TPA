package arvoreBinaria;

public class ArvoreAVL<T extends Comparable> extends ArvoreBinaria {

    @Override
    public boolean insere(Comparable elemento) {
        No<T> novoNo = new No<T>((T) elemento);
        if (this.getRaiz() == null) {
            this.setRaiz(novoNo);
            return true;
        } else {
            No<T> noPosicao = this.getRaiz();
            No<T> proximoNo = noPosicao;
            while (proximoNo != null) {
                noPosicao = proximoNo;
                proximoNo = this.comparaNoAtualComProximo(noPosicao, elemento);
            }
            if (noPosicao.getElemento().compareTo(elemento) > 0) {
                noPosicao.setEsquerda(novoNo);
                balancaArvore(this.getRaiz());
                return true;
            } else if (noPosicao.getElemento().compareTo(elemento) < 0) {
                noPosicao.setDireita(novoNo);
                balancaArvore(this.getRaiz());
                return true;
            } else {
                return false;
            }
        }
    }

    private No<T> rotacaoEsquerda(No<T> r) {
        No<T> f = r.getDireita();
        r.setDireita(f.getEsquerda());
        f.setEsquerda(r);
        /*
         * TODO: alterar o tamanho dos nós
         */
        // Retorna o nó que era o filho
        return f;
    }

    private No<T> rotacaoDireita(No<T> r) {
        No<T> f = r.getEsquerda();
        r.setEsquerda(f.getDireita());
        f.setDireita(r);
        /*
         * TODO: alterar o tamanho dos nós
         */
        return f;
    }

    private No<T> rotacaoEsquerdaDireita(No<T> r) {
        r.setEsquerda(
            rotacaoEsquerda(
                r.getDireita()));
        return rotacaoDireita(r.getDireita());
    }

    private No<T> rotacaoDireitaEsquerda(No<T> r) {
        r.setDireita(
                rotacaoDireita(
                        r.getDireita()));
        return rotacaoEsquerda(r);
    }

    private int fatorBalanceamento(No<T> no) {
        if (no == null)
            return 0;
        else if (no.getDireita() == null)
            return (no.getEsquerda().getAltura());
        else if (no.getEsquerda() == null)
            return (no.getDireita().getAltura());
        else
            return (no.getDireita().getAltura() - no.getEsquerda().getAltura());
    }

    private void balancaArvore(No<T> raiz) {
        if (fatorBalanceamento(raiz) > 1) {
            if (this.fatorBalanceamento(raiz.getDireita()) > 0)
                raiz = rotacaoEsquerda(raiz);
            else
                raiz = rotacaoDireitaEsquerda(raiz);
        } else if (fatorBalanceamento(raiz) < -1) {
            if (fatorBalanceamento(raiz.getEsquerda()) < -1)
                raiz = rotacaoDireita(raiz);
            else
                raiz = rotacaoEsquerdaDireita(raiz);
        }
    }
}