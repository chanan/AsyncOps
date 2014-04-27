package controllers;
import play.mvc.*;

public class Application extends Controller {

    public static Result index()  {
        return ok(views.html.main.render());
    }

    public static Result viewOps() {
        return ok(views.html.viewOps.render());
    }

    public static Result testOps() {
        return ok(views.html.testOps.render());
    }
}