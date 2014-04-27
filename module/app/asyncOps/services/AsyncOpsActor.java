package asyncOps.services;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import hubs.Models;
import hubs.OperationsHub;
import hubs.OperationsPage;
import play.Logger;
import signalJ.GlobalHost;
import signalJ.services.HubContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Chanan on 4/19/2014.
 */
public class AsyncOpsActor extends UntypedActor {
    private final Map<UUID, OperationBase> operations = new HashMap<UUID, OperationBase>();
    private final HubContext<OperationsPage> hub;
    private final Random rnd = new Random();

    public AsyncOpsActor()  {
        HubContext<OperationsPage> hub = null;
        try {
            hub = GlobalHost.getHub(OperationsHub.class);
        } catch (Exception e) {
            Logger.error("Cannot get hub class!", e);
        }
        this.hub = hub;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof OperationBase) {
            final OperationBase operation = (OperationBase) message;
            addOperation(operation);
            if(message instanceof MultiStepOperation) {
                final MultiStepOperation multi = (MultiStepOperation) message;
                for(final OperationBase op : multi.getOperations()) {
                    addOperation(op);
                }
            }
        }
        if(message instanceof String) {
            startCheck();
        }
        if(message instanceof Messages.OperationResponse) {
            final Messages.OperationResponse response = (Messages.OperationResponse) message;
            final OperationBase operation = operations.get(response.uuid);
            if(operation.isComplete()) {
                hub.clients().group(operation.group).operationComplete(new Models.OperationComplete(operation.uuid, operation.message, operation.startTime, operation.getEndTime(), operation.username, operation.group));
                hub.clients().group("admin").operationComplete(new Models.OperationComplete(operation.uuid, operation.message, operation.startTime, operation.getEndTime(), operation.username, operation.group));
            }
        }
    }

    private void addOperation(OperationBase operation) {
        operations.put(operation.uuid, operation);
        hub.clients().group(operation.group).operationAdded(new Models.OperationStarted(operation.uuid, operation.message, operation.startTime, operation.username, operation.group));
        hub.clients().group("admin").operationAdded(new Models.OperationStarted(operation.uuid, operation.message, operation.startTime, operation.username, operation.group));
    }

    private void startCheck() {
        for(OperationBase operation : operations.values()) {
            if(operation.isComplete()) continue;
            if(operation instanceof MultiStepOperation) {
                int percent = operation.getPercentComplete();
                hub.clients().group(operation.group).operationStatus(new Models.OperationStatus(operation.uuid, operation.message, operation.startTime, percent, operation.username, operation.group));
                hub.clients().group("admin").operationStatus(new Models.OperationStatus(operation.uuid, operation.message, operation.startTime, percent, operation.username, operation.group));
            }
            final Props props = Props.create(Worker.class);
            if(getContext().getChild(operation.uuid.toString()) == null) {
                final ActorRef worker = getContext().actorOf(props, operation.uuid.toString());
                final Messages.OperationRequest request = new Messages.OperationRequest(operation);
                worker.tell(request, getSelf());
            }
        }
    }

    static class Worker extends UntypedActor {
        @Override
        public void onReceive(Object message) throws Exception {
            final Messages.OperationRequest request = (Messages.OperationRequest) message;
            final Messages.OperationResponse response = new Messages.OperationResponse(request.operation.uuid, request.operation.doCheck());
            getSender().tell(response, getSelf());
            getContext().stop(getSelf());
        }
    }
}