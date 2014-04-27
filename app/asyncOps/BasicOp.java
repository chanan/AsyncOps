package asyncOps;
import asyncOps.services.BasicOperation;

import java.util.Random;

/**
 * Created by Chanan on 4/20/2014.
 */
public class BasicOp extends BasicOperation {
    private Random rnd = new Random();

    public BasicOp(String message, String username, String group) {
        super(message, username, group);
    }

    @Override
    public boolean check() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rnd.nextBoolean();
    }
}