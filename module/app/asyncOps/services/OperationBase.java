package asyncOps.services;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Chanan on 4/19/2014.
 */
public abstract class OperationBase {
    public final UUID uuid = UUID.randomUUID();
    public final String message;
    public final Date startTime = new Date();
    private Date endTime;
    private boolean isComplete;
    public final String username;
    public final String group;

    public OperationBase(String message, String username, String group) {
        this.message = message;
        this.username = username;
        this.group = group;
        this.isComplete = false;
    }

    void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
        if(isComplete && endTime == null) endTime = new Date();
    }

    public abstract int getPercentComplete();

    public abstract boolean doCheck();

    public boolean isComplete() {
        return isComplete;
    }

    public Date getEndTime() {
        return endTime;
    }
}