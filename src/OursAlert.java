import domain.PlanningGenerator;
import domain.DayCalculator;

public class OursAlert {

    public static void main(String[] Args) {
        DayCalculator dayCalculator = new DayCalculator();
        dayCalculator.horaireCalculator();
        dayCalculator.displayHoraire();

        PlanningGenerator planningGenerator = new PlanningGenerator();
        planningGenerator.sessionGenerator();
    }
}
