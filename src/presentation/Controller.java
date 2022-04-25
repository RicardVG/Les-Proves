package presentation;


import business.Rwfiles;

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

        String optionFaction = "null";
        while (!optionFaction.equals("I") && !optionFaction.equals("II")){
            view.pickFaction();
            optionFaction = view.askForString("Pick a faction: ");
            pickedFaction(optionFaction);
        }

    }

    private void pickedFaction(String optionFaction) {

        switch (optionFaction) {
            case "I", "II" -> Rwfiles.chooseFormat(optionFaction);
            default -> System.out.println("Enter a correct option!");
        }
    }
}
