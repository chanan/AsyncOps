package hubs;
import signalJ.services.Hub;

/**
 * Created by Chanan on 4/19/2014.
 */
public class OperationsHub extends Hub<OperationsPage> {

    @Override
    protected Class<OperationsPage> getInterface() {
        return OperationsPage.class;
    }
}