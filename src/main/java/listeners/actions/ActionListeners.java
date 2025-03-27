package listeners.actions;

import com.slack.api.bolt.App;
import listeners.ListenerProvider;
import utils.Constants;

public class ActionListeners implements ListenerProvider {
    @Override
    public void register(App app) {
        app.blockAction(Constants.ActionIds.WORKFLOW_STEP_BUTTON, new CustomStepButtonActionListener(app));
    }
}
