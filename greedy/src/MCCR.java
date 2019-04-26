import java.util.HashSet;

public class MCCR {

        private static int minKey(int key[], Boolean mstSet[], int numVerts){
            int min = Integer.MAX_VALUE, min_index = -1;
            for (int v = 0; v<numVerts; v++){
                if (!mstSet[v] && key[v] < min){
                    min = key[v];
                    min_index = v;
                }
            }
            return min_index;
        }
        //reshape into a 2D array to make it easier to keep track of which nodes
        //contain which links/weights.
        private static int[][] reshape(EdgeWeightedGraph G){
            int[][] graph = new int[G.numberOfV()][G.numberOfV()];
            for (int i = 0; i < G.numberOfV(); i++) {
                HashSet<Edge> edges = (HashSet<Edge>) G.edges(i);
                for (Edge edge: edges) {
                    graph[i][edge.other(i)] = edge.weight();
                }
            }
            return graph;
        }

        public static int MCCR(EdgeWeightedGraph G) {
            int[][] graph = MCCR.reshape(G);
            //key vals used to pick min weight edge in cut
            int keys[] = new int[G.numberOfV()];

            //vertices not yet included in MST
            Boolean mstSet[] = new Boolean[G.numberOfV()];

            for (int i = 0; i < G.numberOfV(); i++) {
                keys[i] = Integer.MAX_VALUE;
                mstSet[i] = false;
            }
            keys[0] = 0;

            for (int i = 0; i < G.numberOfV()-1; i++) {
                int u = minKey(keys, mstSet, G.numberOfV());
                System.out.printf("u %d\n", u);
                //add picked vertex to MSTset
                mstSet[u] = true;

                Iterable<Edge> edges = G.edges(u);

                //update key val/parent index of adjacent vertices. Consider only
                //vertices that have not been updated
                for (int v = 0; v < G.numberOfV(); v++) {
                    if (graph[u][v]!=0 && !mstSet[v] && graph[u][v] < keys[v]){
                        keys[v] = graph[u][v];
                    }
                }
            }
            int res = 0;
            for (int i = 0; i < keys.length; i++) {
                res += keys[i];
                System.out.println(keys[i]);
            }
            System.out.println("\n");
            return res;
        }

    }

