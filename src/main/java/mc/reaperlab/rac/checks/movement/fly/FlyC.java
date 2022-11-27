package mc.reaperlab.rac.checks.movement.fly;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.event.player.PlayerMoveEvent;

public class FlyC extends Check {

    public FlyC() {
        super("Fly", "C", CheckType.MOVEMENT);
    }

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.getUser(event.getPlayer());
        if (user.onGround()) {
            user.upTime = 0;
            return new CheckReturn(true, "");
        }

        double dist = Math.abs(event.getFrom().getY() - event.getTo().getY());

        if (dist > 0) {
            if (user.upTime++ > 20)
                return new CheckReturn(false, "Going up for too long");
        } else {
            if (user.upTime > 1)
                user.upTime--;
        }

        super.onMoveEvent(event);
        return new CheckReturn(true, "");
    }
}
