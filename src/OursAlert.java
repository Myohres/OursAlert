import domain.PlanningGenerator;
import domain.DayCalculator;

public class OursAlert {

    public static void main(String[] Args) {
        DayCalculator dayCalculator = new DayCalculator();
        dayCalculator.hourWorkGenerator();
        dayCalculator.hourWorkDisplay();

        PlanningGenerator planningGenerator = new PlanningGenerator();
        planningGenerator.planningGenerator();
    }
}
