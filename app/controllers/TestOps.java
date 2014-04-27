package controllers;
import asyncOps.AsyncOps;
import asyncOps.BasicOp;
import asyncOps.services.MultiStepOperation;
import play.mvc.Result;

/**
 * Created by Chanan on 4/20/2014.
 */
public class TestOps extends Application {
    public static Result addBasicOperation() {
        BasicOp op = new BasicOp("Some op", "Chanan", "25");
        AsyncOps.addOperation(op);
        return ok();
    }

    public static Result addMultiOperation() {
        MultiStepOperation multi = new MultiStepOperation("Steps", "Chanan", "25");
        BasicOp b1 = new BasicOp("Create instance 1", "Chanan", "25");
        BasicOp b2 = new BasicOp("Create instance 2", "Chanan", "25");
        BasicOp b3 = new BasicOp("Create instance 3", "Chanan", "25");
        multi.addOperation(b1);
        multi.addOperation(b2);
        multi.addOperation(b3);
        AsyncOps.addOperation(multi);
        return ok();
    }
}