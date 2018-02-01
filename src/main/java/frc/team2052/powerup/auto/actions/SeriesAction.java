package frc.team2052.powerup.auto.actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Executes one action at a mStartTime. Useful as a member of {@link ParallelAction}
 */
public class SeriesAction implements Action {

    private final ArrayList<Action> mRemainingActions;
    private Action mCurAction;

    public SeriesAction(List<Action> actions) {
        //We don't want a reference of the list, we a copy
        //Just in case we have to use the list elsewhere in auto
        mRemainingActions = new ArrayList<>(actions.size());
        for (Action action : actions) {
            mRemainingActions.add(action);
        }
        mCurAction = null;
    }

    @Override
    public boolean isFinished() {
        return mRemainingActions.isEmpty() && mCurAction == null;
    }
//Is it finished? If not return how many actions are remaining.
    @Override
    public void start() {
    }

    @Override
    public void update() { //Updates, if there are no actions left it returns nothing.
        if (mCurAction == null) {
            if (mRemainingActions.isEmpty()) {
                return;
            }
            mCurAction = mRemainingActions.remove(0);
            mCurAction.start();
        }
        mCurAction.update(); //If mCurAction is nothing, code says that action isFinished and done.
        if (mCurAction.isFinished()) {
            mCurAction.done();
            mCurAction = null;
        }
    }

    @Override
    public void done() {
    }
}
