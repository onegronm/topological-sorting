import java.util.*;

// A Java program to print topological sorting of a DAG
public class Graph {
    // Adjacency list as HashMap of ArrayList<String>
    private Map<String, ArrayList<String>> adj;

    // Constructor
    public Graph(String key){
        adj = new HashMap();
        adj.put(key, new ArrayList<String>());
    }

    /**
     * Add an edge into the graph
     * @param from
     * @param to
     */
    public void addEdge(String from, String to){
        adj.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
    }

    /**
     * Performs a topological sort on the graph
     */
    public void topologicalSort(){
        // a stack to return vertices in reverse postorder
        Stack<String> stack = new Stack<String>();

        // mark all vertices as not visited
        Map<String, Boolean> visited = new HashMap();
        Iterator it = adj.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry mapElement = (Map.Entry)it.next();
            visited.putIfAbsent(mapElement.getKey().toString(), false);
        }

        // perform DFS on the graph recursively
        it = visited.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry mapElement = (Map.Entry)it.next();
            boolean isVisited = (boolean)mapElement.getValue();
            if (!isVisited)
                DFS(mapElement.getKey().toString(), visited, stack);
        }

        // Print contents of stack
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }

    /**
     * Recursive function for completing a DFS on the graph
     * @param i
     * @param visited
     * @param stack
     */
    private void DFS(String i, Map<String, Boolean> visited, Stack<String> stack){
        visited.put(i, true); // mark as visited
        // visit all the vertices adjacent to this vertex
        Iterator it = adj.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry mapElement = (Map.Entry)it.next();
            boolean isVisited = visited.get(mapElement.getKey().toString());
            if (!isVisited)
                DFS(mapElement.getKey().toString(), visited, stack);
        }

        stack.push(i);
    }

    public static void main(String args[]){

        Graph g = new Graph("TELNET");
        g.addEdge("TCPIP", "TELNET");
        g.addEdge("NETCARD", "TELNET");
        g.addEdge("NETCARD", "TCPIP");
        g.addEdge("TCPIP", "DNS");
        g.addEdge("NETCARD", "DNS");
        g.addEdge("TCPIP", "BROWSER");
        g.addEdge("HTML", "BROWSER");

        g.topologicalSort();
    }
}
