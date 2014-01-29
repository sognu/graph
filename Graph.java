package assignGraphTopologicalSortBreadthFirstSearch;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Chad Miller 
 * 
 * Representation of a graph.
 */
public class Graph {

	// collection of vertices -- each vertex is identified by its name (string)
	private Map<String, Vertex> vertices;
	// indicates if the graph is directed or undirected
	private boolean directed;
	// stores relevant information for each vertex after a breadth-first-search is performed
	private Map<Vertex, BFSVertexInfo> bfsInfo;


	/**
	 * Creates a graph with no vertices or edges.
	 */
	public Graph() {
		vertices = new HashMap<String, Vertex>();
		directed = true;
		bfsInfo = null;
	}


	/**
	 * Adds an edge between the vertex identified using "name1" and the vertex
	 * identifies using "name2".
	 * When the graph is directed, name1 is the start and name2 is the end.
	 */
	public void addEdge(String name1, String name2) {
		Vertex vertex1;
		// If the vertex is already in our collection, get it.
		if (vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		// If the vertex is not already in our collection, add it.
		else {
			vertex1 = new Vertex(name1);
			vertices.put(name1, vertex1);
		}

		// Do the same for other vertex.
		Vertex vertex2;
		if (vertices.containsKey(name2))
			vertex2 = vertices.get(name2);
		else {
			vertex2 = new Vertex(name2);
			vertices.put(name2, vertex2);
		}

		// adds edge name1 -> name2
		vertex1.addEdge(vertex2); 
		// checks if the graph is directed
		if (!directed) {
			vertex2.addEdge(vertex1);

		}
	}
      
	public void addVertex(String name1) {
		
		Vertex vertex1;
		if (vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		// If the vertex is not already in our collection, add it.
		else {
			vertex1 = new Vertex(name1);
			vertices.put(name1, vertex1);
	
		}
	}
		/**
	 * Sets the directed/undirected status of this graph according to the given status
	 * @param status - the given status; true for directed and false for undirected.
	 */
	public void setDirected(boolean status) {
		directed = status;
	}


	/**
	 * Checks if this graph is directed or undirected. 
	 * @return true if the graph is directed; false otherwise
	 */
	public boolean isDirected() {
		return directed;
	}


	/**
	 * Checks if this graph contains the vertex with the given name.
	 * @param name - the name of the given vertex
	 * @return true if this graph contains the specified vertex; false otherwise
	 */
	public boolean containVertex (String name) {
		return vertices.containsKey(name);
	}

	
	/**
	 * Returns all directed edges of this graph. If the graph is undirected, 
	 * returns an empty set.
	 */
	public Set<DirectedEdge> getDirectedEdges () {
		Set<DirectedEdge> result = new HashSet<DirectedEdge>();
		
		if (!directed)	// an undirected graph -- return an empty set
			return result;
		// stores all directed edges
		for (Vertex v : vertices.values()) {
			Iterator<Edge> edges = v.edges();
			while (edges.hasNext()) 
				result.add(
						new DirectedEdge(v.getName(), edges.next().getOtherVertex().getName())
						);
		}
		return result;
	}
	

	/**
	 * Finds the unweighted shortest path from the start vertex to the end vertex on this graph
	 * @param start - the name of the start vertex
	 * @param end - the name of the end vertex
	 * @return a list of names of vertices consisting of the unweighted shortest path
	 * @throws an UnsupportedOperationException if the graph is undirected or if
	 * the starting or ending vertex does not exist in the graph.
	 */
	public List<String> shortestPathUnWeighted (String start, String end) {

		if (!directed)						// undirected graph
			throw new UnsupportedOperationException("The graph is undirected!");

		if (!vertices.containsKey(start))	// start is not present
			throw new UnsupportedOperationException("The vertex " + start + 
					" is not in this graph!");

		if (!vertices.containsKey(end))		// end is not present
			throw new UnsupportedOperationException("The vertex " + end + 
					" is not in this graph!");

		// initializes the BFS info for each vertex -- no previous vertex and infinite distance
		bfsInfo = new HashMap<Vertex, BFSVertexInfo>();
		for (Vertex v : vertices.values()) {
			bfsInfo.put(v, new BFSVertexInfo(null, Double.POSITIVE_INFINITY));
		}

		// a queue used to perform BFS
		Queue<Vertex> q = new LinkedList<Vertex>();
		Vertex current = vertices.get(start);
		q.offer(current);
		// there is no previous vertex for the start vertex and its distance is 0
		bfsInfo.get(current).dist = 0.0;

		// performs BFS
		while (!q.isEmpty()) {
			current = q.poll();
			Iterator<Edge> edges = current.edges();
			// updates BFS info for vertices connected to the current vertex
			while (edges.hasNext()) {
				Vertex otherVertex = edges.next().getOtherVertex();
				BFSVertexInfo bfsvi = bfsInfo.get(otherVertex);
				if( bfsvi.prev == null && !otherVertex.getName().equals(start) ) {	
					// oterVertex is not visited and it is not the start vertex
					bfsvi.prev = current;
					bfsvi.dist = bfsvi.dist + 1;
					q.offer(otherVertex);
				}

				if (otherVertex.getName().equals(end)) {
					// the end vertex is encountered -- quit
					q.clear();
					break;
				}
			}
		}
		// gets the shortest path by tracing backward from the end vertex
		List<String> result = new LinkedList<String>();
		current = vertices.get(end);
		result.add(0, current.getName());

		while (bfsInfo.get(current).prev != null) {
			current = bfsInfo.get(current).prev;
			result.add(0, current.getName());
		}
		
		if (result.size() == 1)	// can not go from start to end
			result.clear();

		return result;
	}

	
	/**
     * @param vertexSet
     */
    public void initializeInDegree (Collection<Vertex> vertexSet)
    {
      for(Vertex v: vertexSet) {
      Iterator<Edge> edges = v.edges();

      while(edges.hasNext()) {
          Vertex otherVertex = edges.next().getOtherVertex();
          otherVertex.setInDegree(1+otherVertex.getInDegree());
      }
        
    }
    }

    /**
     * @param vertexSet
     */
    public List<String> topologicalSort (Collection<Vertex> vertexSet)
    {
        Queue<Vertex> q = new LinkedList<Vertex>();
        List<String> result = new LinkedList<String>();
        for(Vertex v: vertexSet) {
            if(v.getInDegree() == 0)
                q.offer(v);
        }

        while(!q.isEmpty()) {

            Vertex current = q.poll();
            result.add(current.getName());
            Iterator<Edge> edges = current.edges();

            while(edges.hasNext()) {

                Vertex otherVertex = edges.next().getOtherVertex();
                otherVertex.setInDegree(otherVertex.getInDegree()-1);

                if(otherVertex.getInDegree()==0)               
                    q.offer(otherVertex);
            }
        }
        
        if (result.size() < vertexSet.size())   // a cycle is detected
            throw new UnsupportedOperationException();
        
        return result;

        
    }

    public boolean isthereaPath(String start, String end) {
        
        Queue<Vertex> q = new LinkedList<Vertex>();
        Vertex current = vertices.get(start);
        q.offer(current);
        bfsInfo.get(current).dist = 0.0;
        while(!q.isEmpty())
                current = q.poll();
                Iterator<Edge> e = current.edges();
        
         while(e.hasNext()) {
             
         }
    
        return false;
        
        
        
        
    }
	public Vertex getVertex (String name) {
		return vertices.get(name);
	}


	public Collection<Vertex> getVertexSet() {
		return vertices.values();
	}


	/**
	 * Prints a linked list version of the graph.
	 */
	public void print() {
		for(Vertex v : vertices.values()) 
			System.out.println(v);
	}
}


/**
 * An inner class which represents the information associated to a vertex after BFS
 */
class BFSVertexInfo {	
	// the previous vertex of this vertex on a shortest path (depending on the starting vertex)
	protected Vertex prev;
	// the length of the shortest path between this vertex and a starting vertex
	protected Double dist;

	/**
	 * Creates the associated info for a vertex in BFS
	 * @param v - the previous vertex
	 * @param d - the distance to the start vertex
	 */
	public BFSVertexInfo (Vertex v, Double d) {
		prev = v;
		dist = d;
	}
}
