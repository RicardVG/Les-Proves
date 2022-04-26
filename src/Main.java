import business.Rwfiles;
import presentation.Controller;
import presentation.View;
import presentation.ViewComposer;
import presentation.ViewConductor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ViewComposer viewComposer = new ViewComposer();
        ViewConductor viewConductor = new ViewConductor();
        View view = new View();
        Rwfiles rwfiles = new Rwfiles();
        Controller controller = new Controller(viewComposer,viewConductor, view, rwfiles);
        controller.run();
    }
}
