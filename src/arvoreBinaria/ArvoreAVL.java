package arvoreBinaria;

public class ArvoreAVL<T extends Comparable> extends ArvoreBinaria {

    @Override
    public boolean insere(Comparable elemento) {
        No<T> novoNo = new No<T>((T) elemento);

        if (this.busca(elemento) != null)
            return false;

        if (this.getRaiz() == null)
            this.setRaiz(novoNo);
        else 
            this.setRaiz(addRecursao(this.getRaiz(), novoNo));
        
        return true;
    }

    protected No<T> addRecursao(No<T> atual, No<T> novo){
        if (novo.getElemento().compareTo(atual.getElemento()) < 0) {
            if (atual.getEsquerda() == null)
                atual.setEsquerda(novo);
            else 
                atual.setEsquerda(addRecursao(atual.getEsquerda(), novo));
        } else {
            if (atual.getDireita() == null)
                atual.setDireita(novo);
            else
                atual.setDireita(addRecursao(atual.getDireita(), novo));
        }

        if (atual.fatorBalanceamento()>1){
            if (atual.getDireita().fatorBalanceamento()>0)
                atual = this.rotacaoEsquerda(atual);
            else 
                atual = this.rotacaoDireitaEsquerda(atual);
        } else if (atual.fatorBalanceamento() < -1) {
            if (atual.getEsquerda().fatorBalanceamento()<0)
                atual = this.rotacaoDireita(atual);
            else 
                atual = this.rotacaoEsquerdaDireita(atual);
        }

        return atual;

    }

    private No<T> rotacaoEsquerda(No<T> r) {
        No<T> f = r.getDireita();
        r.setDireita(f.getEsquerda());
        f.setEsquerda(r);
        
        return f;
    }

    private No<T> rotacaoDireita(No<T> r) {
        No<T> f = r.getEsquerda();
        r.setEsquerda(f.getDireita());
        f.setDireita(r);
        return f;
    }

    private No<T> rotacaoEsquerdaDireita(No<T> r) {
        r.setEsquerda(
                rotacaoEsquerda(
                        r.getEsquerda()));
        return rotacaoDireita(r);
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
        else if (no.getDireita() == null && no.getEsquerda() != null)
            return (no.getEsquerda().getAltura() * -1);
        else if (no.getEsquerda() == null && no.getDireita() != null)
            return (no.getDireita().getAltura());
        else if (no.getEsquerda() == null && no.getDireita() == null)
            return 0;
        else
            return (no.getDireita().getAltura() - no.getEsquerda().getAltura());
    }

    private void balancaArvore(No<T> raiz) {
        raiz = this.getRaiz();
        if (fatorBalanceamento(raiz) > 1) {
            if (fatorBalanceamento(raiz.getDireita()) > 0)
                this.setRaiz(rotacaoEsquerda(raiz));
            else
                this.setRaiz(rotacaoDireitaEsquerda(raiz));
        } else if (fatorBalanceamento(raiz) < -1) {
            if (fatorBalanceamento(raiz.getEsquerda()) < 0)
                this.setRaiz(rotacaoDireita(raiz));
            else
                this.setRaiz(rotacaoEsquerdaDireita(raiz));
        }
    }
}