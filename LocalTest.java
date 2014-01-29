package assignGraphTopologicalSortBreadthFirstSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Chad Miller
 * 
 *         Class implements tests for breadth first search and topological sort.
 *         Determines if graph is topologically sorted through inner method.
 */

public class LocalTest
{

    public static void main (String[] args)
    {

        // tests correctness of breadFirstSort where there is no path from start to end.
        System.out.println(GraphUtil.breadthFirstSearch("A7_example/dot_graphs/examplegraph7.dot", "CS 1410",
                                                        "MATH 2250"));
        // tests correctness of breadFirstSort on cyclic graph.
        System.out.println(GraphUtil.breadthFirstSearch("A7_example/dot_graphs/examplegraph.dot", "vertex 1",
                                                        "vertex 4"));
        // // tests correctness of breadFirstSort on DAG graph.
        System.out.println(GraphUtil
                .breadthFirstSearch("A7_example/dot_graphs/examplegraph7.dot", "CS 1410", "CS 4500"));

        // tests correctness of breadFirstSort on an undirected graph -- should throw exception.
        // System.out
        // .println(GraphUtil.breadthFirstSearch("A7_example/dot_graphs/undirected.dot", "CS 1410", "MATH 2250"));

        // tests correctness of topologicaSort on DAG w/o unique solution.
        System.out.println(GraphUtil.topologicalSort("A7_example/dot_graphs/examplegraph7.dot"));
        //
        // // tests correctness of topologicaSort on DAG w/o unique solution.
        System.out.println(GraphUtil.topologicalSort("A7_example/dot_graphs/examplegraph7.dot"));
        //
        // tests correctness of topologicaSort on DAG with a unique solution.
        System.out.println(GraphUtil.topologicalSort("A7_example/dot_graphs/examplegraph5.dot"));
        //
        // tests correctness of topologicaSort by method isTopologicalSorted.
        Graph g = GraphUtil.buildGraphFromDot("A7_example/dot_graphs/examplegraph7.dot");
        List<String> sorted = GraphUtil.topologicalSort("A7_example/dot_graphs/examplegraph7.dot");
        System.out.println(isTopologicalSorted(g, sorted));
}

    /**
     * Method determines if result is topologically sorted.
     * 
     * @param g
     * @param result
     * @return
     */
    public static boolean isTopologicalSorted (Graph g, List<String> result)
    {

        Set<DirectedEdge> edges = g.getDirectedEdges();
        ArrayList<String> vertices = new ArrayList<String>(result);

        for (int i = 1; i < vertices.size(); i++)
        {
            for (int j = i - 1; j >= 0; j--)
                if (edges.contains(new DirectedEdge(vertices.get(i), vertices.get(j))))
                    return false;
        }
        return true;
    }

}
