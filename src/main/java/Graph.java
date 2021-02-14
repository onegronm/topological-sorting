import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

// A Java program to print topological sorting of a DAG
public class Graph {
    // No. of vertices
    private int N;

    // Adjacency list as ArrayList of ArrayList's
    private ArrayList<ArrayList<Integer>> adj;

    // Constructor
    public Graph(int n){
        N = n;
        adj = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < N; ++i){
            adj.add(new ArrayList<Integer>());
        }
    }

    /**
     * Add an edge into the graph
     * @param from
     * @param to
     */
    public void addEdge(int from, int to){
        adj.get(from).add(to);
    }

    /**
     * Performs a topological sort on the graph
     */
    public void topologicalSort(){
        // a stack to return vertices in reverse postorder
        Stack<Integer> stack = new Stack<Integer>();

        // mark all vertices as not visited
        boolean visited[] = new boolean[N];
        for (boolean i : visited)
            i = false;

        // perform DFS on the graph recursively
        for (int i = 0; i < N; i++){
            if (!visited[i])
                DFS(i, visited, stack);
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
    private void DFS(int i, boolean[] visited, Stack<Integer> stack){
        visited[i] = true;
        // visit all the vertices adjacent to this vertex
        Iterator<Integer> it = this.adj.get(i).iterator();
        while(it.hasNext()){
            i = it.next();
            if(!visited[i])
                DFS(i, visited, stack);
        }
        stack.push(i);
    }
}
