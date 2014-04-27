package asyncOps.services;

/**
 * Created by Chanan on 4/19/2014.
 */
public abstract class BasicOperation extends OperationBase {
    public BasicOperation(String message, String username, String group) {
        super(message, username, group);
    }

    @Override
    public boolean doCheck() {
        if(isComplete()) return true;
        boolean b = check();
        if(b) setComplete(true);
        return b;
    }

    @Override
    public int getPercentComplete() {
        return isComplete() ? 100 : 0;
    }

    public abstract boolean check();
}