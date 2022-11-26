package mc.reaperlab.rac.checks.movement.jump;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

import java.util.Objects;

public class JumpA extends Check {

    public JumpA() {
        super("Jump", "A", CheckType.MOVEMENT);
    }

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.getUser(event.getPlayer());

        if (user.onGround() && user.getPlayer().getVelocity().getY() > 0 ) {
            if (user.getPlayer().getVelocity().getY() > 0.42) {
                return new CheckReturn(false, "Highjump");
            }
        }

        super.onMoveEvent(event);
        return new CheckReturn(true, "");
    }
}
