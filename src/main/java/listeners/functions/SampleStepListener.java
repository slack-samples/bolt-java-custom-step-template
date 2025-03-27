package listeners.functions;

import static com.slack.api.model.block.Blocks.actions;
import static com.slack.api.model.block.Blocks.asBlocks;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.asElements;
import static com.slack.api.model.block.element.BlockElements.button;

import com.slack.api.app_backend.events.payload.EventsApiPayload;
import com.slack.api.bolt.App;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.bolt.handler.BoltEventHandler;
import com.slack.api.bolt.response.Response;
import com.slack.api.model.event.FunctionExecutedEvent;
import utils.Constants;

public class SampleStepListener implements BoltEventHandler<FunctionExecutedEvent> {

    private final App app;

    public SampleStepListener(App app) {
        this.app = app;
    }

    @Override
    public Response apply(EventsApiPayload<FunctionExecutedEvent> event, EventContext context) {
        this.app.executorService().submit(() -> {
            context.logger.info("event: {}", event);
            try {
                context.client().chatPostMessage(r -> r.channel(
                                event.getEvent().getInputs().get("user_id").asString())
                        .text(":wave: hey")
                        .blocks(asBlocks(actions(a -> a.blockId(Constants.BlockIds.WORKFLOW_STEP_BUTTON)
                                .elements(asElements(button(b -> b.actionId(Constants.ActionIds.WORKFLOW_STEP_BUTTON)
                                        .value("clicked")
                                        .text(plainText("click me!")))))))));
            } catch (Exception e) {
                context.logger.error("Failed to call chatPostMessage API (error: {})", e.getMessage(), e);
            }
        });
        return context.ack();
    }
}
