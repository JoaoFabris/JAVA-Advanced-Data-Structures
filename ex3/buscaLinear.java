// Busca Linear: Você está criando um aplicativo de agenda de contatos e precisa encontrar rapidamente a posição de um número específico na lista.
// Escreva um método que realize uma busca linear em um array de inteiros, retornando a posição do número buscado ou -1 caso ele não esteja presente. 

package ex3;

public class buscaLinear {

    public static int buscarNumero(long[] numeros, long numeroBuscado) {
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == numeroBuscado) {
                return i;
            }
        }

        // Se chegou até aqui, o número não foi encontrado
        return -1;
    }

    public static void main(String[] args) {
        long[] contatosTelefonicos = {
                11987654321L,
                21998765432L,
                31976543210L,
                41965432109L,
                51954321098L
        };


        long numeroPresenteNaLista = 31976543210L;
        long numeroAusenteNaLista = 11912345678L;


        int posicaoEncontrada = buscarNumero(contatosTelefonicos, numeroPresenteNaLista);
        if (posicaoEncontrada != -1) {
            System.out.println("Número " + numeroPresenteNaLista + " encontrado na posição " + posicaoEncontrada);
        } else {
            System.out.println("Número " + numeroPresenteNaLista + " não encontrado na lista");
        }

        posicaoEncontrada = buscarNumero(contatosTelefonicos, numeroAusenteNaLista);
        if (posicaoEncontrada != -1) {
            System.out.println("Número " + numeroAusenteNaLista + " encontrado na posição " + posicaoEncontrada);
        } else {
            System.out.println("Número " + numeroAusenteNaLista + " não encontrado na lista");
        }
    }
}
