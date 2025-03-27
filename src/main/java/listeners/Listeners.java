package listeners;

import com.slack.api.bolt.App;
import listeners.actions.ActionListeners;
import listeners.functions.FunctionListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listeners {

    public static Logger logger = LoggerFactory.getLogger(Listeners.class);

    public static void register(App app) {
        for (ListenerProvider provider : getAllListeners()) {
            provider.register(app);
            logger.info("{} Registered", provider.getClass().getSimpleName());
        }
    }

    private static ListenerProvider[] getAllListeners() {
        return new ListenerProvider[] {new ActionListeners(), new FunctionListeners()};
    }
}
