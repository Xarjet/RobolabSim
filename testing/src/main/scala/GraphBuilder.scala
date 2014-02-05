import org.jgrapht.graph.DefaultEdge
import org.jgrapht.UndirectedGraph

trait GraphBuilder[N, E <: DefaultEdge]
{
  /** The known Maze contains all explicit & implicit information, which are known by the solution.
    * It possibly contains nodes, which wasn't visited --> important for evaluating the shortest path back home.
    */
  def constructKnownMaze: UndirectedGraph[N, E]

  /** The path is the representation of the actually driven course */
  def constructPath: UndirectedGraph[N, E]

  /** If you combine these 2 Graphs to one, it will result in the path Graph
    * So these two, are subgraphs of the actual driven path:
    * explorationPhasePath: is the path until all tokens was found
    * driveHomePhasePath: is the Path back home, after the last token was found (empty if there are no tokens in the maze)
    * @see [[GraphBuilder]].constructDriveHomePhaseGraph()
    */
  def constructExplorationPhaseGraph: UndirectedGraph[N, E]

  /** If you combine these 2 Graphs to one, it will result in the path Graph
    * So these two, are subgraphs of the actual driven path:
    * explorationPhasePath: is the path until all tokens was found
    * driveHomePhasePath: is the Path back home, after the last token was found (empty if there are no tokens in the maze)
    * @see [[GraphBuilder]].constructExplorationPhaseGraph()
    */
  def constructDriveHomePhaseGraph: UndirectedGraph[N, E]

  def getLastTokenNode: N

  def setTokenCount(tokenCount: Int): GraphBuilder[N, E]

  def makeMultiGraph: GraphBuilder[N, E]

  def addStartPoint: GraphBuilder[N, E]
}
