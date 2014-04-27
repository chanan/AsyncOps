package hubs;

/**
 * Created by Chanan on 4/19/2014.
 */
public interface OperationsPage {
    public void operationAdded(Models.OperationStarted operationStarted);
    public void operationComplete(Models.OperationComplete operationComplete);
    public void operationStatus(Models.OperationStatus operationStatus);
}
