package mc.reaperlab.rac.checks;

import org.bukkit.event.player.PlayerMoveEvent;

public class Check {

    private final String name;
    private final String type;
    private final CheckType checkType;

    public Check(String name, String type, CheckType checkType) {
        this.name = name;
        this.type = type;
        this.checkType = checkType;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public CheckReturn onMoveEvent(PlayerMoveEvent event) {return CheckReturn.PASSED;}

}
