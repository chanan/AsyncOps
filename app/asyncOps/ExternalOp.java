package asyncOps;

import asyncOps.services.BasicOperation;

/**
 * Created by Chanan on 4/28/2014.
 */
public class ExternalOp extends BasicOperation {
    private boolean done = false;

    public ExternalOp(String message, String username, String group) {
        super(message, username, group);
    }

    @Override
    public boolean check() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
