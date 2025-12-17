package listeners.actions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.slack.api.bolt.App;
import com.slack.api.bolt.context.builtin.ActionContext;
import com.slack.api.bolt.request.builtin.BlockActionRequest;
import com.slack.api.methods.SlackApiException;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class CustomStepButtonActionListenerTest {

    @Test
    public void testApply() throws IOException, SlackApiException {
        // Given
        var app = new App();
        var reqMock = mock(BlockActionRequest.class);
        var ctxMock = mock(ActionContext.class);

        when(ctxMock.ack()).thenReturn(mock(com.slack.api.bolt.response.Response.class));

        // When
        var customStepButtonActionListener = new CustomStepButtonActionListener(app);
        var res = customStepButtonActionListener.apply(reqMock, ctxMock);

        // Then
        verify(ctxMock).ack();
        assertNotNull(res);
    }
}
