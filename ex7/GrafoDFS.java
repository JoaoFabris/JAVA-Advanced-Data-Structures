//DFS em Grafos: Continuando no seu aplicativo de navegação, escreva um método para realizar a Busca em Profundidade (DFS) em um grafo. 
//Esse método deve exibir todos os vértices visitados, permitindo que os usuários visualizem as possíveis rotas de maneira mais detalhada. 

package ex7;

import java.util.*;

public class GrafoDFS {
    Map<String, List<String>> grafo = new HashMap<>();

    void addRota(String de, String para) {
        grafo.putIfAbsent(de, new ArrayList<>());
        grafo.putIfAbsent(para, new ArrayList<>());
        grafo.get(de).add(para);
        grafo.get(para).add(de);
    }

    List<String> dfs(String inicio, String fim) {
        Set<String> visitados = new HashSet<>();
        Map<String, String> anterior = new HashMap<>();

        if (dfsRecursivo(inicio, fim, visitados, anterior)) {
            List<String> caminho = new ArrayList<>();
            String atual = fim;

            while (atual != null) {
                caminho.add(0, atual);
                atual = anterior.get(atual);
            }

            return caminho;
        }

        return null;
    }

    boolean dfsRecursivo(String atual, String fim, Set<String> visitados, Map<String, String> anterior) {
        visitados.add(atual);

        if (atual.equals(fim)) {
            return true;
        }

        for (String vizinho : grafo.getOrDefault(atual, Collections.emptyList())) {
            if (!visitados.contains(vizinho)) {
                anterior.put(vizinho, atual);

                if (dfsRecursivo(vizinho, fim, visitados, anterior)) {
                    return true;
                }
            }
        }

        return false;
    }

    List<String> todasRotas(String inicio) {
        Set<String> visitados = new HashSet<>();
        List<String> ordem = new ArrayList<>();

        dfsExplorar(inicio, visitados, ordem);

        return ordem;
    }

    void dfsExplorar(String atual, Set<String> visitados, List<String> ordem) {
        visitados.add(atual);
        ordem.add(atual);

        for (String vizinho : grafo.getOrDefault(atual, Collections.emptyList())) {
            if (!visitados.contains(vizinho)) {
                dfsExplorar(vizinho, visitados, ordem);
            }
        }
    }

    public static void main(String[] args) {
        GrafoDFS mapa = new GrafoDFS();

        mapa.addRota("Casa", "Mercado");
        mapa.addRota("Casa", "Escola");
        mapa.addRota("Mercado", "Farmácia");
        mapa.addRota("Escola", "Parque");
        mapa.addRota("Farmácia", "Hospital");
        mapa.addRota("Parque", "Hospital");
        mapa.addRota("Escola", "Biblioteca");

        // Encontrar um caminho específico usando DFS
        List<String> caminho = mapa.dfs("Casa", "Hospital");
        if (caminho != null) {
            System.out.println("Caminho encontrado via DFS:");
            System.out.println(String.join(" -> ", caminho));
        } else {
            System.out.println("Não existe caminho entre os pontos");
        }

        // Explorar todas as rotas possíveis a partir de um ponto
        List<String> todasRotas = mapa.todasRotas("Casa");
        System.out.println("\nTodas as rotas possíveis a partir de Casa (ordem de exploração DFS):");
        System.out.println(String.join(" -> ", todasRotas));
    }
}