package asyncOps;
import akka.actor.ActorRef;
import akka.actor.Props;
import asyncOps.services.AsyncOpsActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.Application;
import play.Logger;
import play.Play;
import play.Plugin;
import play.libs.Akka;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by Chanan on 4/19/2014.
 */
public class AsyncOpsPlugin extends Plugin {
    private final Application application;
    private ActorRef asyncOpsActor;
    private final static String intervalKey = "asyncops.interval";

    private static AsyncOpsPlugin plugin() {
        return Play.application().plugin(AsyncOpsPlugin.class);
    }

    public static ActorRef getAsyncOpsActor() {
        return plugin().asyncOpsActor;
    }

    public AsyncOpsPlugin(Application application) {
        this.application = application;
    }

    @Override
    public void onStart() {
        Logger.debug("Async Ops Starting");
        asyncOpsActor = Akka.system().actorOf(Props.create(AsyncOpsActor.class), "asyncOpsActor");

        final Config config = ConfigFactory.load();

        long interval = 10000;
        if(config.hasPath(intervalKey)) {
            interval = config.getMilliseconds(intervalKey);
        }

        Akka.system().scheduler().schedule(
                Duration.create(5, TimeUnit.SECONDS),
                Duration.create(interval, TimeUnit.MILLISECONDS),
                asyncOpsActor,
                "tick",
                Akka.system().dispatcher(),
                ActorRef.noSender()
        );
    }
}