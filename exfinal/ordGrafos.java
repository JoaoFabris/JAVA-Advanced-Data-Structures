//Ordenação de Grafos: Por fim, implemente um algoritmo que ordene os vértices de um grafo de acordo com a topologia das conexões entre eles. 
//Essa ordenação pode ser útil em diversas aplicações, como planejamento de projetos ou organização de tarefas interdependentes.

package ex10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ordGrafos {
    Map<String, List<String>> grafo = new HashMap<>();

    void addAresta(String de, String para) {
        grafo.putIfAbsent(de, new ArrayList<>());
        grafo.putIfAbsent(para, new ArrayList<>());
        grafo.get(de).add(para);
    }

    List<String> ordenarTopologicamente() {
        Set<String> visitados = new HashSet<>();
        Stack<String> pilha = new Stack<>();

        for (String vertice : grafo.keySet()) {
            if (!visitados.contains(vertice)) {
                dfsOrdenacao(vertice, visitados, pilha);
            }
        }

        List<String> resultado = new ArrayList<>();
        while (!pilha.isEmpty()) {
            resultado.add(pilha.pop());
        }

        return resultado;
    }

    void dfsOrdenacao(String vertice, Set<String> visitados, Stack<String> pilha) {
        visitados.add(vertice);

        for (String vizinho : grafo.getOrDefault(vertice, Collections.emptyList())) {
            if (!visitados.contains(vizinho)) {
                dfsOrdenacao(vizinho, visitados, pilha);
            }
        }

        pilha.push(vertice);
    }

    boolean temCiclo() {
        Set<String> visitados = new HashSet<>();
        Set<String> emProcessamento = new HashSet<>();

        for (String vertice : grafo.keySet()) {
            if (!visitados.contains(vertice)) {
                if (temCicloDFS(vertice, visitados, emProcessamento)) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean temCicloDFS(String vertice, Set<String> visitados, Set<String> emProcessamento) {
        visitados.add(vertice);
        emProcessamento.add(vertice);

        for (String vizinho : grafo.getOrDefault(vertice, Collections.emptyList())) {
            if (!visitados.contains(vizinho)) {
                if (temCicloDFS(vizinho, visitados, emProcessamento)) {
                    return true;
                }
            } else if (emProcessamento.contains(vizinho)) {
                return true;
            }
        }

        emProcessamento.remove(vertice);
        return false;
    }

    public static void main(String[] args) {
        ordGrafos grafo = new ordGrafos();

        grafo.addAresta("Vestir Cueca", "Vestir Calça");
        grafo.addAresta("Vestir Calça", "Vestir Cinto");
        grafo.addAresta("Vestir Camiseta", "Vestir Casaco");
        grafo.addAresta("Vestir Meias", "Calçar Sapatos");
        grafo.addAresta("Vestir Calça", "Calçar Sapatos");
        grafo.addAresta("Vestir Cinto", "Vestir Casaco");

        if (grafo.temCiclo()) {
            System.out.println("O grafo contém ciclos, não é possível ordenar topologicamente");
        } else {
            List<String> ordem = grafo.ordenarTopologicamente();
            System.out.println("Ordem para realizar as tarefas:");
            for (int i = 0; i < ordem.size(); i++) {
                System.out.println((i + 1) + ". " + ordem.get(i));
            }
        }
    }
}
