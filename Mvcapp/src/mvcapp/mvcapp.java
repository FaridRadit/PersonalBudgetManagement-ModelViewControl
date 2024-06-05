package mvcapp;

public class mvcapp {
    public static void main(String[] args) {
        ModelContact model = new ModelContact();
        ViewContact view = new ViewContact();
        ControllerContact controller = new ControllerContact(model, view);
    }
}
