package business;

import presentation.Controller;
import presentation.View;
import presentation.ViewComposer;

import java.util.ArrayList;

public class TrialManager {

    private View view;
    private ViewComposer viewComposer;
    private Controller controller;
    private PaperPublication paperPublication;
    private MasterStudies masterStudies;
    private DoctoralThesis doctoralThesis;
    private BudgetRequest budgetRequest;
    private final int EXIT = 0;

    public TrialManager() {
    }



    public int optionTrialManager(char optionTrial) {

        int optionTrialTypes;

        switch (optionTrial){
            case 'a':
                view.showMenuTrialTypes();
                optionTrialTypes = view.askForOption("Enter the trial's type: ");

                if (optionTrialTypes == 1){
                    System.out.println();
                    paperPublication = new PaperPublication();

                    //TODO
                    // Realment cal passar totes les tipus d'arraylists i guardarles aqui, no les necesitem despres
                    //no?
                //    trial.add(paperPublication);
                    System.out.println("");
                    System.out.println("The trial was created successfully!");
                }
                if(optionTrialTypes == 2){

                    System.out.println();
                    masterStudies = new MasterStudies();
                   // trial.add(masterStudies);
                    System.out.println();
                    System.out.println("The trial was created successfully!");
                }

                if(optionTrialTypes == 3){

                    System.out.println("");
                    doctoralThesis = new DoctoralThesis();
                   // trial.add(doctoralThesis);
                    System.out.println("");
                    System.out.println("The trial was created successfully!");
                }

                if(optionTrialTypes == 4){

                    System.out.println("");
                    budgetRequest = new BudgetRequest();
                   // trial.add(budgetRequest);
                    System.out.println("");
                    System.out.println("The trial was created successfully!");
                }

                controller.updateJsonTrial(optionTrialTypes, paperPublication, masterStudies, doctoralThesis, budgetRequest);
                viewComposer.menuTrialManager();

                break;
            case 'b':
                break;
            case 'c':
                break;
            case 'd':
                return EXIT;
        }
        return EXIT;
    }
}
