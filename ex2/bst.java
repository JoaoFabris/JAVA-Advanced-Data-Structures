package ex2;

//Validação de BST: Suponha que você tenha uma árvore que armazena a hierarquia de categorias de produtos em um e-commerce. 
//Escreva um método que verifique se essa árvore binária é uma árvore binária de busca (BST),
// garantindo que os produtos estejam organizados corretamente para facilitar a busca e navegação. 

public class bst {
    static class Categoria {
        String nome;
        int codigo;

        public Categoria(String nome, int codigo) {
            this.nome = nome;
            this.codigo = codigo;
        }

        @Override
        public String toString() {
            return nome + " (Código: " + codigo + ")";
        }
    }

    static class No {
        Categoria categoria;
        No esquerda, direita;

        public No(Categoria categoria) {
            this.categoria = categoria;
            this.esquerda = this.direita = null;
        }
    }

    public static boolean ehBST(No raiz) {
        return verificarBST(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean verificarBST(No no, int min, int max) {

        if (no == null) {
            return true;
        }

        if (no.categoria.codigo <= min || no.categoria.codigo >= max) {
            return false;
        }

        return verificarBST(no.esquerda, min, no.categoria.codigo) &&
                verificarBST(no.direita, no.categoria.codigo, max);
    }

    public static No inserir(No raiz, Categoria categoria) {
        if (raiz == null) {
            return new No(categoria);
        }

        if (categoria.codigo < raiz.categoria.codigo) {
            raiz.esquerda = inserir(raiz.esquerda, categoria);
        } else if (categoria.codigo > raiz.categoria.codigo) {
            raiz.direita = inserir(raiz.direita, categoria);
        }

        return raiz;
    }

    public static No criarArvoreBSTInvalida() {
        No raiz = new No(new Categoria("Eletrônicos", 50));
        raiz.esquerda = new No(new Categoria("Smartphones", 30));
        raiz.direita = new No(new Categoria("Computadores", 70));

        raiz.direita.esquerda = new No(new Categoria("Tablets", 40));

        return raiz;
    }

    public static void main(String[] args) {

        No raizValida = null;
        raizValida = inserir(raizValida, new Categoria("Eletrônicos", 50));
        raizValida = inserir(raizValida, new Categoria("Smartphones", 30));
        raizValida = inserir(raizValida, new Categoria("Acessórios", 20));
        raizValida = inserir(raizValida, new Categoria("Fones de Ouvido", 25));
        raizValida = inserir(raizValida, new Categoria("Computadores", 70));
        raizValida = inserir(raizValida, new Categoria("Notebooks", 60));
        raizValida = inserir(raizValida, new Categoria("Desktops", 80));

        // Verificando se a árvore válida é uma BST
        System.out.println("Árvore 1 é uma BST válida? " + ehBST(raizValida));

        // Criando uma árvore BST inválida
        No raizInvalida = criarArvoreBSTInvalida();

        // Verificando se a árvore inválida é uma BST
        System.out.println("Árvore 2 é uma BST válida? " + ehBST(raizInvalida));
    }
}