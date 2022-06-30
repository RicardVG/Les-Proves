import business.Competition;
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
        View view = new View();
        ViewComposer viewComposer = new ViewComposer(view);
        ViewConductor viewConductor = new ViewConductor();
        TrialDAO trialDAO = new TrialDAO();
        EditionDAO editionDAO = new EditionDAO();
        TrialManager trialManager = new TrialManager(trialDAO);
        EditionManager editionManager = new EditionManager(editionDAO);
        Competition competition = new Competition();
        Controller controller = new Controller(viewComposer,viewConductor, view, trialManager, editionManager,competition);
        controller.run();
    }
}
