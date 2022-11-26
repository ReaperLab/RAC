package mc.reaperlab.rac.checks;

import mc.reaperlab.rac.checks.movement.SpeedA;

import java.util.ArrayList;

public class CheckManager {

    private ArrayList<Check> Checks = new ArrayList<>();

    public CheckManager() {
        addCheck(new SpeedA());
    }

    public ArrayList<Check> getChecksInType(CheckType type) {
        ArrayList<Check> checksInType = new ArrayList<>();
        for (Check checks : Checks) {
            if (checks.getCheckType().equals(type)) {
                checksInType.add(checks);
            }
        }
        return checksInType;
    }

    public void addCheck(Check check) {
        Checks.add(check);
    }

}
