package asyncOps;
import akka.actor.ActorRef;
import asyncOps.services.BasicOperation;
import asyncOps.services.OperationBase;

import java.util.UUID;

/**
 * Created by Chanan on 4/19/2014.
 */
public class AsyncOps {
    public static UUID addOperation(OperationBase operation) {
        AsyncOpsPlugin.getAsyncOpsActor().tell(operation, ActorRef.noSender());
        return operation.uuid;
    }
}