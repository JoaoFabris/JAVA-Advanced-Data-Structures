//Busca Binária: Imagine que você está desenvolvendo um sistema de pesquisa de dados em uma lista de números ordenados. 
//Implemente um algoritmo de busca binária que encontre a posição de um elemento específico em um array ordenado de inteiros,
// retornando sua posição ou -1 se não for encontrado. 

package ex4;

public class buscaBinaria {

    public static int BuscaBinaria(int[] array, int elementoBuscado) {
        int inicio = 0;
        int fim = array.length - 1;

        while (inicio <= fim) {

            int meio = inicio + (fim - inicio) / 2;

            if (array[meio] == elementoBuscado) {
                return meio;
            }

            if (array[meio] > elementoBuscado) {
                fim = meio - 1;
            }

            else {
                inicio = meio + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] numerosOrdenados = { 2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91 };

        int elementoPresente = 23;
        int elementoAusente = 10;

        int posicao = BuscaBinaria(numerosOrdenados, elementoPresente);
        if (posicao != -1) {
            System.out.println("Elemento " + elementoPresente + " encontrado na posição " + posicao);
        } else {
            System.out.println("Elemento " + elementoPresente + " não encontrado no array");
        }

        posicao = BuscaBinaria(numerosOrdenados, elementoAusente);
        if (posicao != -1) {
            System.out.println("Elemento " + elementoAusente + " encontrado na posição " + posicao);
        } else {
            System.out.println("Elemento " + elementoAusente + " não encontrado no array");
        }
    }
}