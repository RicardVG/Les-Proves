import business.EditionManager;
import business.TrialManager;
import persistance.EditionDAO;
import persistance.TrialDAO;
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
        TrialDAO trialDAO = new TrialDAO();
        TrialManager trialManager = new TrialManager(trialDAO);
        EditionManager editionManager = new EditionManager();
        Controller controller = new Controller(viewComposer,viewConductor, view, trialManager, editionManager);
        controller.run();
    }
}
