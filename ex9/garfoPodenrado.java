//Caminho Mais Curto em Grafo Ponderado: Ao modificar seu algoritmo BFS, você precisa encontrar o caminho mais curto entre dois pontos em um grafo ponderado,
// considerando as distâncias. Esse ajuste é crucial para garantir que os usuários recebam as rotas mais eficientes.  

package ex9;

import java.util.*;

public class garfoPodenrado {
    Map<String, Map<String, Integer>> grafo = new HashMap<>();

    void addRota(String de, String para, int dist) {
        grafo.putIfAbsent(de, new HashMap<>());
        grafo.putIfAbsent(para, new HashMap<>());
        grafo.get(de).put(para, dist);
        grafo.get(para).put(de, dist);
    }

    List<String> caminhoMaisCurto(String inicio, String fim) {
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> anterior = new HashMap<>();
        PriorityQueue<String> fila = new PriorityQueue<>(
                Comparator.comparingInt(local -> distancias.getOrDefault(local, Integer.MAX_VALUE)));

        for (String local : grafo.keySet()) {
            distancias.put(local, Integer.MAX_VALUE);
        }
        distancias.put(inicio, 0);
        fila.add(inicio);

        while (!fila.isEmpty()) {
            String atual = fila.poll();

            if (atual.equals(fim))
                break;
            if (distancias.get(atual) == Integer.MAX_VALUE)
                break;

            for (Map.Entry<String, Integer> vizinho : grafo.get(atual).entrySet()) {
                String proximo = vizinho.getKey();
                int novaDistancia = distancias.get(atual) + vizinho.getValue();

                if (novaDistancia < distancias.get(proximo)) {
                    distancias.put(proximo, novaDistancia);
                    anterior.put(proximo, atual);
                    fila.remove(proximo);
                    fila.add(proximo);
                }
            }
        }

        if (!anterior.containsKey(fim) && !inicio.equals(fim))
            return null;

        List<String> caminho = new ArrayList<>();
        String atual = fim;

        while (atual != null) {
            caminho.add(0, atual);
            atual = anterior.get(atual);
        }

        return caminho;
    }

    int calcularDistancia(List<String> caminho) {
        int total = 0;
        for (int i = 0; i < caminho.size() - 1; i++) {
            total += grafo.get(caminho.get(i)).get(caminho.get(i + 1));
        }
        return total;
    }

    public static void main(String[] args) {
        garfoPodenrado mapa = new garfoPodenrado();

        mapa.addRota("Casa", "Mercado", 5);
        mapa.addRota("Casa", "Escola", 3);
        mapa.addRota("Mercado", "Farmácia", 2);
        mapa.addRota("Escola", "Parque", 4);
        mapa.addRota("Farmácia", "Hospital", 1);
        mapa.addRota("Parque", "Hospital", 6);

        List<String> caminho = mapa.caminhoMaisCurto("Casa", "Hospital");

        if (caminho != null) {
            System.out.println(String.join(" -> ", caminho));
            System.out.println("Distância: " + mapa.calcularDistancia(caminho) + " km");
        } else {
            System.out.println("Sem caminho");
        }
    }
}