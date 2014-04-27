package asyncOps;
import akka.actor.ActorRef;
import akka.actor.Props;
import asyncOps.services.AsyncOpsActor;
import play.Application;
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
        asyncOpsActor = Akka.system().actorOf(Props.create(AsyncOpsActor.class), "asyncOpsActor");

        Akka.system().scheduler().schedule(
                Duration.create(1, TimeUnit.SECONDS),
                Duration.create(1, TimeUnit.SECONDS),
                asyncOpsActor,
                "tick",
                Akka.system().dispatcher(),
                ActorRef.noSender()
        );
    }
}