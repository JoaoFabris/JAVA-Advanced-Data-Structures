//BFS em Grafos: No contexto do seu aplicativo de navegação, implemente o algoritmo de Busca em Largura (BFS) 
//para encontrar o caminho mais curto entre dois pontos em um grafo não ponderado, ajudando os usuários a escolherem a melhor rota. 

package ex6;

import java.util.*;

public class GrafoBFS {
    Map<String, List<String>> grafo = new HashMap<>();

    void addRota(String de, String para) {
        grafo.putIfAbsent(de, new ArrayList<>());
        grafo.putIfAbsent(para, new ArrayList<>());
        grafo.get(de).add(para);
        grafo.get(para).add(de);
    }

    List<String> bfs(String inicio, String fim) {
        if (inicio.equals(fim)) {
            return Collections.singletonList(inicio);
        }

        Queue<String> fila = new LinkedList<>();
        Set<String> visitados = new HashSet<>();
        Map<String, String> anterior = new HashMap<>();

        fila.add(inicio);
        visitados.add(inicio);

        while (!fila.isEmpty()) {
            String atual = fila.poll();

            for (String vizinho : grafo.getOrDefault(atual, Collections.emptyList())) {
                if (!visitados.contains(vizinho)) {
                    fila.add(vizinho);
                    visitados.add(vizinho);
                    anterior.put(vizinho, atual);

                    if (vizinho.equals(fim)) {
                        List<String> caminho = new ArrayList<>();
                        String no = fim;

                        while (no != null) {
                            caminho.add(0, no);
                            no = anterior.get(no);
                        }

                        return caminho;
                    }
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        GrafoBFS mapa = new GrafoBFS();

        mapa.addRota("Casa", "Mercado");
        mapa.addRota("Casa", "Escola");
        mapa.addRota("Mercado", "Farmácia");
        mapa.addRota("Escola", "Parque");
        mapa.addRota("Farmácia", "Hospital");
        mapa.addRota("Parque", "Hospital");
        mapa.addRota("Escola", "Biblioteca");

        List<String> caminho = mapa.bfs("Casa", "Hospital");

        if (caminho != null) {
            System.out.println("Caminho mais curto (em número de paradas):");
            System.out.println(String.join(" -> ", caminho));
        } else {
            System.out.println("Não existe caminho entre os pontos");
        }
    }
}