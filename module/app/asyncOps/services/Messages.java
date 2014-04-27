package asyncOps.services;
import java.util.UUID;

/**
 * Created by Chanan on 4/19/2014.
 */
class Messages {
    static class OperationRequest {
        public final OperationBase operation;

        OperationRequest(OperationBase operation) {
            this.operation = operation;
        }
    }

    static class OperationResponse  {
        public final UUID uuid;
        public final boolean isComplete;

        OperationResponse(UUID uuid, boolean isComplete) {
            this.uuid = uuid;
            this.isComplete = isComplete;
        }
    }
}