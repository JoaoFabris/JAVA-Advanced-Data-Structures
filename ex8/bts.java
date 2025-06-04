//Mínimo de uma BST: Imagine que você está analisando uma árvore binária de busca para identificar o produto mais barato de uma lista.
// Escreva um método que encontre o menor valor em uma árvore binária de busca, ajudando a determinar a melhor oferta disponível. 

package ex8;

class bts {
    class No {
        int valor;
        No esquerda, direita;

        No(int valor) {
            this.valor = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }

    No raiz;

    bts() {
        raiz = null;
    }

    void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    No inserirRecursivo(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserirRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserirRecursivo(no.direita, valor);
        }

        return no;
    }

    int encontrarMinimo() {
        if (raiz == null) {
            throw new IllegalStateException("Árvore vazia");
        }

        No atual = raiz;


        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }

        return atual.valor;
    }

   
    int encontrarMinimoRecursivo() {
        if (raiz == null) {
            throw new IllegalStateException("Árvore vazia");
        }

        return encontrarMinimoRecursivo(raiz);
    }

    int encontrarMinimoRecursivo(No no) {

        if (no.esquerda == null) {
            return no.valor;
        }

        return encontrarMinimoRecursivo(no.esquerda);
    }

    public static void main(String[] args) {
        bts arvore = new bts();


        arvore.inserir(29);
        arvore.inserir(15);
        arvore.inserir(45);
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(37);
        arvore.inserir(89);
        arvore.inserir(7);


        System.out.println("Preço do produto mais barato: R$" + arvore.encontrarMinimo());


        System.out.println("Preço do produto mais barato (método recursivo): R$" + arvore.encontrarMinimoRecursivo());
    }
}