package catchBox;

import agentSearch.Heuristic;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class HeuristicCatch extends Heuristic<CatchProblemSearch, CatchState> {

    @Override
    public double compute(CatchState state) {
        //TODO
        return state.computeDistances(problem.getGoalPosition());
    }

    @Override
    public String toString() {
        //TODO
        return"";
    }
}