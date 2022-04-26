package presentation;


import business.Rwfiles;

import java.io.IOException;

public class Controller {

    private final ViewComposer viewComposer;
    private final ViewConductor viewConductor;
    private final View view;
    private final Rwfiles rwfiles;

    public Controller(ViewComposer viewComposer, ViewConductor viewConductor, View view, Rwfiles rwfiles) {
        this.viewComposer = viewComposer;
        this.viewConductor = viewConductor;
        this.view = view;
        this.rwfiles = rwfiles;
    }

    public void run() throws IOException {

        String optionFaction = "null";
        while (!optionFaction.equals("I") && !optionFaction.equals("II")){
            view.pickFaction();
            optionFaction = view.askForString("Pick a faction: ");
            pickedFaction(optionFaction);
        }

    }

    private void pickedFaction(String optionFaction) throws IOException {

        switch (optionFaction) {
            case "I", "II" -> rwfiles.chooseFormat(optionFaction);
            default -> System.out.println("Enter a correct option!");
        }
    }
}
