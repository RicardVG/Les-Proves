package presentation;

public class Controller {

    private final ViewComposer viewComposer;
    private final ViewConductor viewConductor;
    private final View view;

    public Controller(ViewComposer viewComposer, ViewConductor viewConductor, View view) {
        this.viewComposer = viewComposer;
        this.viewConductor = viewConductor;
        this.view = view;
    }

    public void run() {

    }
}
