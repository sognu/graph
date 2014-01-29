package assignGraphTopologicalSortBreadthFirstSearch;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * @author Liang Zhang and Chad Miller
 * 
 *Class represents a vertex.
 */
public class Vertex {
	// name of the vertex
	private String name;
	// collection of edges originating from this vertex
	private Set<Edge> adj; 
	// number of edges coming to this vertex
	private int inDeg;


	/**
	 * Creates a vertex with given name.
	 * @param _name - the given name for this vertex
	 */
	public Vertex(String _name) {
		this.name = _name;
		this.adj = new HashSet<Edge>();
		inDeg = 0;
	}

	
	/**
	 * Returns the name of this vertex.
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Adds an edge that originates from this vertex.
	 */
	public void addEdge(Vertex otherVertex) {
		adj.add(new Edge(otherVertex));
	}

	
	/**
	 * Returns an iterator that can be used to traverse all the edges originating from this vertex.
	 */
	public Iterator<Edge> edges() {
		return adj.iterator();
	}

	
	/**
	 * Sets the in-degree of this vertex, i.e. the number of edges pointing to this vertex.
	 * @param d - the given in-degree
	 */
	public void setInDegree(int d) {
		this.inDeg = d;
	}

	
	/**
	 * Returns the in-degree of this vertex, i.e. the number of edges pointing to this vertex.
	 */
	public int getInDegree() {
		return this.inDeg;
	}
	
	
	public boolean equals (Vertex v) {
		return this.name.equals(v.name);
	}
	
	
	/**
	 * Returns a string representation of this vertex.
	 */
	public String toString() {
		String s = "Vertex " + name + " adjacent to ";
		Iterator<Edge> itr = adj.iterator();
		while (itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}
}
