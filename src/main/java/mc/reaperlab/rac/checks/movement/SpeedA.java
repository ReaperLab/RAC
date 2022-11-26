package mc.reaperlab.rac.checks.movement;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class SpeedA extends Check {

    public SpeedA() {
        super("Speed", "A", CheckType.MOVEMENT);
    }

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.INSTANCE.getUser(event.getPlayer());
        if (!user.onGround()) return CheckReturn.PASSED;

        double dist = event.getFrom().distance(Objects.requireNonNull(event.getTo()));

        if (dist > 0.6475837678752038) {
            return CheckReturn.FAILED;
        }

        super.onMoveEvent(event);
        return CheckReturn.PASSED;
    }
}
