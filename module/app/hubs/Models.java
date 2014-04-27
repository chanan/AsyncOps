package hubs;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Chanan on 4/19/2014.
 */
public class Models {
    public static class OperationComplete {
        public final UUID uuid;
        public final String message;
        public final Date startTime;
        public final Date endTime;
        public final String username;
        public final String group;

        public OperationComplete(UUID uuid, String message, Date startTime, Date endTime, String username, String group) {
            this.uuid = uuid;
            this.message = message;
            this.startTime = startTime;
            this.endTime = endTime;
            this.username = username;
            this.group = group;
        }
    }

    public static class OperationStarted {
        public final UUID uuid;
        public final String message;
        public final Date startTime;
        public final String username;
        public final String group;

        public OperationStarted(UUID uuid, String message, Date startTime, String username, String group) {
            this.uuid = uuid;
            this.message = message;
            this.startTime = startTime;
            this.username = username;
            this.group = group;
        }
    }

    public static class OperationStatus {
        public final UUID uuid;
        public final String message;
        public final Date startTime;
        public final String username;
        public final String group;
        public final int percent;

        public OperationStatus(UUID uuid, String message, Date startTime, int percent, String username, String group) {
            this.uuid = uuid;
            this.message = message;
            this.startTime = startTime;
            this.username = username;
            this.group = group;
            this.percent = percent;
        }
    }
}