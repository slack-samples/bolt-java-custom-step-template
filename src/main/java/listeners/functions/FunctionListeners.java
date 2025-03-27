package listeners.functions;

import com.slack.api.bolt.App;
import listeners.ListenerProvider;

public class FunctionListeners implements ListenerProvider {
    @Override
    public void register(App app) {
        app.function("sample-step", new SampleStepListener(app));
    }
}
