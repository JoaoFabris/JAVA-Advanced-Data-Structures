//1 - Implementação da Árvore Binária: Imagine que você está desenvolvendo um sistema de gerenciamento de bibliotecas.
// Crie uma classe em Java para representar uma árvore binária, onde cada nó armazenará informações sobre um livro. 
// Implemente métodos que permitam inserir novos livros e percorrer a árvore em pré-ordem, pós-ordem e em ordem para listar todos os livros disponíveis. 

package ex1;
public class BibliotecaSimples {
    // Classe interna para representar um livro, DEFINIDA com título, autor e código.
    static class Livro {
        String titulo;
        String autor;
        int codigo;
        // Construtor para inicializar os atributos do livro
        Livro(String titulo, String autor, int codigo) {
            this.titulo = titulo;
            this.autor = autor;
            this.codigo = codigo;
        }
        
        @Override // Esse método toString() serve para definir como um objeto da sua classe será convertido em String, geralmente para exibição. representar o objeto como texto.
        public String toString() {
            return "Livro: " + titulo + " - " + autor + " (Código: " + codigo + ")";
        }
    }
    
    // Classe interna para representar um nó da árvore
    static class No {
        Livro livro;
        No esquerda, direita;
        
        No(Livro livro) {
            this.livro = livro;
            this.esquerda = this.direita = null;
        }
    }
    
    // Raiz da árvore, tem q ser nulo inicialmente
    private static No raiz = null;
    
    // Método para inserir um livro na árvore 
    public static void inserir(Livro livro) {
        raiz = inserirRecursivo(raiz, livro);
    }
    
    private static No inserirRecursivo(No no, Livro livro) {
        if (no == null) {
            return new No(livro);
        }
        
        if (livro.codigo < no.livro.codigo) {
            no.esquerda = inserirRecursivo(no.esquerda, livro);
        } else if (livro.codigo > no.livro.codigo) {
            no.direita = inserirRecursivo(no.direita, livro);
        }
        
        return no;
    }
    
    // Método para percorrer em ordem (crescente por código)
    public static void percorrerEmOrdem() {
        System.out.println("\n--- Livros em Ordem ---");
        percorrerEmOrdemRecursivo(raiz);
    }
    
    private static void percorrerEmOrdemRecursivo(No no) {
        if (no != null) {
            percorrerEmOrdemRecursivo(no.esquerda);
            System.out.println(no.livro);
            percorrerEmOrdemRecursivo(no.direita);
        }
    }
    
    // Método para percorrer em pré-ordem (raiz, esquerda, direita)
    public static void percorrerPreOrdem() {
        System.out.println("\n--- Livros em Pré-Ordem ---");
        percorrerPreOrdemRecursivo(raiz);
    }
    
    private static void percorrerPreOrdemRecursivo(No no) {
        if (no != null) {
            System.out.println(no.livro);
            percorrerPreOrdemRecursivo(no.esquerda);
            percorrerPreOrdemRecursivo(no.direita);
        }
    }
    
    // Método para percorrer em pós-ordem (esquerda, direita, raiz)
    public static void percorrerPosOrdem() {
        System.out.println("\n--- Livros em Pós-Ordem ---");
        percorrerPosOrdemRecursivo(raiz);
    }
    
    private static void percorrerPosOrdemRecursivo(No no) {
        if (no != null) {
            percorrerPosOrdemRecursivo(no.esquerda);
            percorrerPosOrdemRecursivo(no.direita);
            System.out.println(no.livro);
        }
    }
    
    // Método principal para testar a implementação
    public static void main(String[] args) {
        // Inserindo alguns livros
        inserir(new Livro("Dom Casmurro", "Machado de Assis", 100));
        inserir(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 50));
        inserir(new Livro("Harry Potter", "J.K. Rowling", 150));
        inserir(new Livro("1984", "George Orwell", 75));
        inserir(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 25));
        
        // Percorrendo a árvore nos três modos
        percorrerEmOrdem();
        percorrerPreOrdem();
        percorrerPosOrdem();
    }
}