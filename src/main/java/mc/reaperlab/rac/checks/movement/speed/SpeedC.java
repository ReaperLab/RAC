package mc.reaperlab.rac.checks.movement.speed;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class SpeedC extends Check {

    public SpeedC() {
        super("Speed", "C", CheckType.MOVEMENT);
    }

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.getUser(event.getPlayer());
        Location to = event.getTo();
        Location from = event.getFrom();

        float motionXZ = (float) Math.hypot(to.getX() - from.getX(), to.getZ() - from.getZ());

        if (motionXZ > 0.6 + getMovementMod(user.getPlayer()))
            return new CheckReturn(false, "Modified Motion");

        super.onMoveEvent(event);
        return new CheckReturn(true, "");
    }

    public float getMovementMod(Player player) {
        if (player.hasPotionEffect(PotionEffectType.SPEED))
            return 0.15f;
        return 0f;
    }
}
