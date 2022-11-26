package mc.reaperlab.rac.checks;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.checkerframework.checker.units.qual.C;

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

    public CheckReturn onAttackEvent(EntityDamageByEntityEvent event) {return new CheckReturn(true, "");}
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {return new CheckReturn(true, "");}
    public CheckReturn onVelocityEvent(PlayerVelocityEvent ev) {return new CheckReturn(true, "");}
    public CheckReturn onUpdateEvent(PlayerEvent event) {return new CheckReturn(true, "");}

}
