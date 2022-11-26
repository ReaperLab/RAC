package mc.reaperlab.rac.checks.movement.speed;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class SpeedB extends Check {

    public SpeedB() {
        super("Speed", "B", CheckType.MOVEMENT);
    }

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.getUser(event.getPlayer());
        if (user.onGround()) return new CheckReturn(true, "");

        double dist = event.getFrom().distance(Objects.requireNonNull(event.getTo()));
        double distY = Math.abs(event.getFrom().getY()-event.getTo().getY());

        if (dist > 0.95 && distY < 0) {
            return new CheckReturn(false, "Air Distance = " + String.valueOf(dist));
        }

        super.onMoveEvent(event);
        return new CheckReturn(true, "");
    }
}
