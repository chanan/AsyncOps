package asyncOps.services;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chanan on 4/26/2014.
 */
public class MultiStepOperation extends OperationBase {
    private List<OperationBase> operations = new ArrayList<OperationBase>();

    public MultiStepOperation(String message, String username, String group) {
        super(message, username, group);
    }

    public void addOperation(OperationBase operation) {
        operations.add(operation);
    }

    @Override
    public int getPercentComplete() {
        if(operations.size() == 0) return 100;
        float numComplete = 0;
        for(final OperationBase operation : operations) {
            if(operation.isComplete()) numComplete++;
        }
        if(numComplete == operations.size()) return 100;
        return Math.round((numComplete / operations.size()) * 100);
    }

    @Override
    public boolean doCheck() {
        boolean b = checkComplete();
        if(b) setComplete(true);
        return b;
    }

    private boolean checkComplete() {
        for(final OperationBase operation : operations) {
            if(!operation.isComplete()) return false;
        }
        return true;
    }

    public List<OperationBase> getOperations() {
        return operations;
    }
}
