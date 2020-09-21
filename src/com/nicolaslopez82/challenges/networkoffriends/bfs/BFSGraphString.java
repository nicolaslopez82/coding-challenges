package com.nicolaslopez82.challenges.networkoffriends.bfs;

import java.util.*;

public class BFSGraphString {

    private int V; //Number of vertices
    private LinkedList<Integer> adj[]; //Adjacency list.
    private Map<String, Integer> strGraph = new HashMap<>();



    public BFSGraphString(int v) {
        V = v;
        adj = new LinkedList[V]; //Create the tiers.
        for (int i = 0; i < V; i++){
            adj[i] = new LinkedList<>(); //Create a new list for each tiers.
        }

        //Load String Conversion.
        strGraph.put("A", 0);
        strGraph.put("B", 1);
        strGraph.put("C", 2);
        strGraph.put("D", 3);
        strGraph.put("E", 4);
        strGraph.put("F", 5);
        strGraph.put("G", 6);
        strGraph.put("H", 7);
        strGraph.put("I", 8);
    }

    void addEdge(String v, String w){

        adj[strGraph.get(v)].add(strGraph.get(w));
    }

    public void BFSTraversal(String vertex){

        int s = strGraph.get(vertex);

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
            showVertex(s);

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

    private void showVertex(int s){
        if(strGraph.containsValue(s)){
            for (Map.Entry<String, Integer> map : strGraph.entrySet()) {
                if(map.getValue().equals(s)){
                    System.out.println("vertex: " + map.getKey() + " " + "(" + s + ")");
                }
            }
        }
    }

    public static void main(String[] args) {
        BFSGraphString graph = new BFSGraphString(8);

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("E", "H");

        graph.BFSTraversal("A");

    }
}
