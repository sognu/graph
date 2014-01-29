package assignGraphTopologicalSortBreadthFirstSearch;

/**
 * An inner class which represents a directed edge from the start vertex to the end vertex
 * 
 * @author Chad Miller 
 */
class DirectedEdge {
	private String start;
	private String end;
	
	/**
	 * Creates a directed edge.
	 * @param s - the name of the start vertex
	 * @param e - the name of the end vertex
	 */
	public DirectedEdge (String s, String e) {
		start = s;
		end = e;
	}
	
	public boolean equals (DirectedEdge other) {
		return start.equals(other.start) && end.equals(other.end);
	}
	
	
	public String getStart() {
		return start;
	}
	
	public String getEnd() {
		return end;
	}
}
