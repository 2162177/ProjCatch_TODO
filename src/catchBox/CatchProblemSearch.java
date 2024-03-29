package catchBox;

import agentSearch.Action;
import agentSearch.Problem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;

public class CatchProblemSearch<S extends CatchState> extends Problem<S> {
    //TODO this class might require the definition of additional methods and/or attributes

    private List<Action> actions;



    private Cell goalPosition;



    public Cell getGoalPosition() {
        return goalPosition;
    }

    public CatchProblemSearch(S initialCatchState, Cell goalPosition) {
        super(initialCatchState);
        actions = new LinkedList<Action>() {{
            add(new ActionDown());
            add(new ActionUp());
            add(new ActionRight());
            add(new ActionLeft());
        }};
        this.goalPosition = goalPosition;
    }

    @Override
    public List<S> executeActions(S state) {
        List<S> successors = new LinkedList<>();

        for (Action action : actions) {
            if (action.isValid(state)) {
                S sucessor = (S) state.clone();
                action.execute(sucessor);
                successors.add(sucessor);
            }
        }
        return successors;
    }

    public boolean isGoal(S state) {
        //TODO
         return state.getLineAgent()==goalPosition.getLine() && state.getColumnAgent()==goalPosition.getColumn();

    }
}
