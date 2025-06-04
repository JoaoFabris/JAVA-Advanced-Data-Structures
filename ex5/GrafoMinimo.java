//Representação de Grafos: Você está desenvolvendo um sistema de roteamento para um aplicativo de navegação.
//Crie uma classe que represente um grafo em Java, utilizando lista de adjacências ou matriz de adjacências, para mapear as conexões entre diferentes locais. 

package ex5;
import java.util.*;



public class GrafoMinimo {
    Map<String, Map<String, Double>> rotas = new HashMap<>();
    
    void addRota(String de, String para, double dist) {
        rotas.putIfAbsent(de, new HashMap<>());
        rotas.putIfAbsent(para, new HashMap<>());
        rotas.get(de).put(para, dist);
        rotas.get(para).put(de, dist);
    }
    
    List<String> caminho(String inicio, String fim) {
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        Set<String> naoVisitados = new HashSet<>(rotas.keySet());
        
        for (String local : rotas.keySet()) {
            dist.put(local, Double.MAX_VALUE);
        }
        dist.put(inicio, 0.0);
        
        while (!naoVisitados.isEmpty()) {
            String atual = null;
            double menorDist = Double.MAX_VALUE;
            
            for (String local : naoVisitados) {
                if (dist.get(local) < menorDist) {
                    menorDist = dist.get(local);
                    atual = local;
                }
            }
            
            if (atual == null || atual.equals(fim)) break;
            
            naoVisitados.remove(atual);
            
            for (Map.Entry<String, Double> vizinho : rotas.get(atual).entrySet()) {
                if (naoVisitados.contains(vizinho.getKey())) {
                    double novaDist = dist.get(atual) + vizinho.getValue();
                    
                    if (novaDist < dist.get(vizinho.getKey())) {
                        dist.put(vizinho.getKey(), novaDist);
                        prev.put(vizinho.getKey(), atual);
                    }
                }
            }
        }
        
        if (!prev.containsKey(fim) && !inicio.equals(fim)) return null;
        
        List<String> path = new ArrayList<>();
        String atual = fim;
        
        while (atual != null) {
            path.add(0, atual);
            atual = prev.get(atual);
        }
        
        return path;
    }
    
    public static void main(String[] args) {
        GrafoMinimo g = new GrafoMinimo();
        
        g.addRota("A", "B", 3);
        g.addRota("A", "C", 5);
        g.addRota("B", "D", 2);
        g.addRota("C", "D", 1);
        
        List<String> path = g.caminho("A", "D");
        
        if (path != null) {
            System.out.println(String.join(" -> ", path));
        } else {
            System.out.println("Sem caminho");
        }
    }
}