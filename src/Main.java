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
        Controller controller = new Controller(viewComposer,viewConductor, view);
        controller.run();
    }
}
