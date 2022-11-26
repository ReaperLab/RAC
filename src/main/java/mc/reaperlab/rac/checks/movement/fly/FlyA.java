package mc.reaperlab.rac.checks.movement.fly;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class FlyA extends Check {

    public FlyA() {
        super("Fly", "A", CheckType.MOVEMENT);
    }

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.getUser(event.getPlayer());
        if (user.onGround()) {
            user.floatTime = 0;
            return new CheckReturn(true, "");
        }

        double dist = Math.abs(event.getFrom().getY() - event.getTo().getY());

        if (dist < 0.1 && dist > -0.1) {
            user.floatTime++;
            if (user.floatTime > 5)
                return new CheckReturn(false, "Floating for too long");
        } else {
            user.floatTime = 0;
        }

        super.onMoveEvent(event);
        return new CheckReturn(true, "");
    }
}
