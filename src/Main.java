import business.Rwfiles;
import business.TrialManager;
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
        TrialManager trialManager = new TrialManager();
        Controller controller = new Controller(viewComposer,viewConductor, view, rwfiles, trialManager);
        controller.run();
    }
}
