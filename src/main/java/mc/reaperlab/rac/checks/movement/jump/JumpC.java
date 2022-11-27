package mc.reaperlab.rac.checks.movement.jump;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class JumpC extends Check {

    public JumpC() {
        super("Jump", "C", CheckType.MOVEMENT);
    }

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.getUser(event.getPlayer());

        double dist = Math.abs(event.getFrom().getY() - event.getTo().getY());

        if (!user.onGround() && dist > 1.1 + getMovementMod(user.getPlayer())) {
            return new CheckReturn(false, "Up Distance = " + dist);
        }

        super.onMoveEvent(event);
        return new CheckReturn(true, "");
    }

    public float getMovementMod(Player player) {
        if (player.hasPotionEffect(PotionEffectType.JUMP))
            return 0.15f;
        return 0f;
    }
}
