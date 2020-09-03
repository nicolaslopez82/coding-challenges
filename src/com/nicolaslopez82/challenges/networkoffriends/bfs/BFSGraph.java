package com.nicolaslopez82.challenges.networkoffriends.bfs;

import java.util.Iterator;
import java.util.LinkedList;

public class BFSGraph {

    private int V; //Number of vertices
    private LinkedList<Integer> adj[]; //Adjacency list.

    public BFSGraph(int v) {
        V = v;
        adj = new LinkedList[V]; //Create the tiers.
        for (int i = 0; i < V; i++){
            adj[i] = new LinkedList<>(); //Create a new list for each tiers.
        }
    }

    void addEdge(int v, int w){
        adj[v].add(w);
    }

    public void BFSTraversal(int s){

        // Mark all the vertices as not visited(By default is false).
        boolean visited[] = new boolean[V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0){

            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s + " ");

            //Get all adjacent vertices of the dequeued vertex "s".
            Iterator<Integer> iterator = adj[s].iterator();

            while (iterator.hasNext()){

                int n = iterator.next();

                //Check if the adjacent hasn't been visited.
                if(!visited[n]){
                    //Mark the adjacent visited as true.
                    visited[n] = true;
                    //Enqueue the adjacent that hasn't been visited yet.
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFSGraph graph = new BFSGraph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

        graph.BFSTraversal(2);
    }
}
