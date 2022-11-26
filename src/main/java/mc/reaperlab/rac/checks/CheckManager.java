package mc.reaperlab.rac.checks;

import mc.reaperlab.rac.checks.movement.fly.*;
import mc.reaperlab.rac.checks.movement.jump.*;
import mc.reaperlab.rac.checks.movement.speed.*;

import java.util.ArrayList;

public class CheckManager {

    private ArrayList<Check> Checks = new ArrayList<>();

    public CheckManager() {

        addCheck(new FlyA());
        addCheck(new FlyB());

        addCheck(new JumpA());

        addCheck(new SpeedA());
        addCheck(new SpeedB());
        addCheck(new SpeedC());


    }

    public ArrayList<Check> getChecks() {return Checks;}

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
