package mc.reaperlab.rac.checks.movement.fly;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.event.player.PlayerMoveEvent;

public class FlyB extends Check {

    public FlyB() {
        super("Fly", "B", CheckType.MOVEMENT);
    }

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.getUser(event.getPlayer());
        if (user.onGround()) {
            user.glideTime = 0;
            return new CheckReturn(true, "");
        }

        double dist = Math.abs(event.getFrom().getY() - event.getTo().getY());

        if (dist < 0.2 && dist != 0) {
            user.glideTime++;
            if (user.glideTime > 8)
                return new CheckReturn(false, "Gliding for too long");
        } else {
            user.glideTime = 0;
        }

        super.onMoveEvent(event);
        return new CheckReturn(true, "");
    }
}
