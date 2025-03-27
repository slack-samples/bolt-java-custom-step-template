package listeners.actions;

import com.slack.api.bolt.App;
import com.slack.api.bolt.context.builtin.ActionContext;
import com.slack.api.bolt.handler.builtin.BlockActionHandler;
import com.slack.api.bolt.request.builtin.BlockActionRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomStepButtonActionListener implements BlockActionHandler {
    private final App app;

    public CustomStepButtonActionListener(App app) {
        this.app = app;
    }

    @Override
    public Response apply(BlockActionRequest req, ActionContext ctx) throws IOException, SlackApiException {
        this.app.executorService().submit(() -> {
            Map<String, Object> outputs = new HashMap<>();
            outputs.put(
                    "user_id",
                    req.getPayload()
                            .getFunctionData()
                            .getInputs()
                            .get("user_id")
                            .asString());
            try {
                ctx.complete(outputs);
                ctx.client().chatUpdate(r -> r.channel(
                                req.getPayload().getContainer().getChannelId())
                        .ts(req.getPayload().getContainer().getMessageTs())
                        .text("Thank you :heart_hands: for clicking a button!"));
            } catch (Exception e) {
                ctx.logger.error("Failed to call complete() or views.update API (error: {})", e.getMessage(), e);
            }
        });
        return ctx.ack();
    }
}
