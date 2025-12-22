package listeners.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.slack.api.app_backend.events.payload.EventsApiPayload;
import com.slack.api.bolt.App;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.bolt.response.Response;
import com.slack.api.model.event.FunctionExecutedEvent;
import org.junit.jupiter.api.Test;

public class SampleStepListenerTest {

    @Test
    public void testApply() {
        // Given
        var plMock = (EventsApiPayload<FunctionExecutedEvent>) mock(EventsApiPayload.class);
        var ctxMock = mock(EventContext.class);

        var responseMock = mock(Response.class);
        when(responseMock.getStatusCode()).thenReturn(200);
        when(ctxMock.ack()).thenReturn(responseMock);

        // When
        var app = new App();
        var sampleStepListener = new SampleStepListener(app);
        var res = sampleStepListener.apply(plMock, ctxMock);

        // Then
        verify(ctxMock).ack();
        assertEquals(res.getStatusCode(), 200);
    }
}
