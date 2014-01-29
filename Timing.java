package assignGraphTopologicalSortBreadthFirstSearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 *@author Liang Zhang and Chad Miller
 * 
 * Class implements timing for breadth first search and topological sort. 
 */
public class Timing {


	public static void main(String[] args) {
		timeTopologicalSort();
		timeBFS();
	}


	private static void timeTopologicalSort() {
		System.out.println("Topological Sort");
		// calculate average running time.
		int timesToLoop = 200;

		// For each problem size n . . .
		for (int n = 10000; n <= 100000; n += 10000) {

			long startTime, midpointTime, stopTime;

			//create a test graph with problem size 




			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block 
			}


			startTime = System.nanoTime();


			for (int i=0;i<(timesToLoop);i++){

				Graph g = generateRandomDAG(n);
				GraphUtil.topologicalSort(g);

			}

			midpointTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++) { 
				Graph g = generateRandomDAG(n);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}


	private static void timeBFS() {
		
		String start = "v0";
		
		System.out.println("BFS");
		// calculate average running time.
		int timesToLoop = 200;

		// For each problem size n . . .
		for (int n = 10000; n <= 100000; n += 10000) {

			String end = "v" + (n-2);
			long startTime, midpointTime, stopTime;

			//create a test graph with problem size 




			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block 
			}


			startTime = System.nanoTime();


			for (int i=0;i<(timesToLoop);i++){

				Graph g = generateRandomGraph(n);
				GraphUtil.breadthFirstSearch(g, start, end);

			}

			midpointTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++) { 
				Graph g = generateRandomGraph(n);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}


	public static Graph generateRandomGraph(int vertexCount) {

		Graph g = new Graph();

		Random rng = new Random();

		HashSet<HashMap<String, String>> edges = new HashSet<HashMap<String, String>>();

		// generate a list of vertices
		String[] vertex = new String[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			vertex[i] = "v" + i;
			g.addVertex(vertex[i]);
		}
		
		// randomly connect the vertices using 2 * |V| edges
		for (int i = 0; i < 2 * vertexCount; i++) {
			int start = rng.nextInt(vertexCount);
			int end = rng.nextInt(vertexCount);

			if (start == end) {
				i--;
				continue;
			}

			HashMap<String, String> oneEdge = new HashMap<String, String>();
			oneEdge.put(vertex[start], vertex[end]);
			edges.add( oneEdge );
		}

		for (HashMap<String, String> de : edges)
			for (String start : de.keySet())
				g.addEdge(start, de.get(start));

					return g;
	}


	public static Graph generateRandomDAG(int vertexCount) {

		Graph g = new Graph();

		Random rng = new Random();

		HashSet<HashMap<String, String>> edges = new HashSet<HashMap<String, String>>();

		// generate a list of vertices
		String[] vertex = new String[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			vertex[i] = "v" + i;

		// randomly connect the vertices using 2 * |V| edges
		for (int i = 0; i < 2 * vertexCount; i++) {
			int start = rng.nextInt(vertexCount);
			int end = rng.nextInt(vertexCount-start)+start;

			if (start == end) {
				i--;
				continue;
			}

			HashMap<String, String> oneEdge = new HashMap<String, String>();
			oneEdge.put(vertex[start], vertex[end]);
			edges.add( oneEdge );
		}

		for (HashMap<String, String> de : edges)
			for (String start : de.keySet())
				g.addEdge(start, de.get(start));

					return g;
	}


	public static void generateRandomDotFile(String filename, int vertexCount) {

		PrintWriter out = null;
		try {
			out = new PrintWriter(filename);
		} catch (IOException e) {
			System.out.println(e);
		}

		Random rng = new Random();

		// randomly construct either a graph or a digraph
		String edgeOp = "--";
		if (true) {
			out.print("di");
			edgeOp = "->";
		}
		out.println("graph G {");

		// generate a list of vertices
		String[] vertex = new String[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			vertex[i] = "v" + i;

		// randomly connect the vertices using 2 * |V| edges
		for (int i = 0; i < 2 * vertexCount; i++) {
			int start = rng.nextInt(vertexCount);
			int end = rng.nextInt(vertexCount);

			if (start == end) {
				i--;
				continue;
			}

			out.println("\t" + vertex[start] + edgeOp + vertex[end]);
		}

		out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp
				+ vertex[rng.nextInt(vertexCount)]);

		out.println("}");
		out.close();
	}


}
