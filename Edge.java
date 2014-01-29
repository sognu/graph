package assignGraphTopologicalSortBreadthFirstSearch;

/**
 * Represents an edge originate from a vertex.
 * 
 * @author Chad Miller
 */
public class Edge {

	// the other vertex this edge points to
	private Vertex other;  
	// the weight of this edge
	private Double weight;

	
	/**
	 * Creates an edge that pointing to the given vertex.with default weight 1.
	 * @param _other - the other vertex this egde pointing to
	 */
	public Edge(Vertex _other) {
		this.other = _other;
		weight = 1.0;
	}
	
	
	/**
	 * Creates an edge that pointing to the given vertex.with given weight.
	 * @param _other - the other vertex this egde pointing to
	 * @param w - the given weight of this edge
	 */
	public Edge(Vertex _other, double w) {
		this.other = _other;
		weight = w;
	}

	
	/**
	 * Returns the other vertex this edge pointing to.
	 */
	public Vertex getOtherVertex() {
		return other;
	}
	
	
	/**
	 * Sets the weight of this edge by the given value.
	 * @param w - the given weight
	 */
	public void setWeight (Double w) {
		weight = w;
	}
	
	/**
	 * Returns the weight of this edge.
	 */
	public Double getWeight() {
		return weight;
	}

	
	
	public boolean equals (Edge e) {
		return this.other.equals(e.other);
	}
	
	/**
	 * Returns a string representation of this edge.
	 */
	public String toString() {
		return other.getName();
	}
}
