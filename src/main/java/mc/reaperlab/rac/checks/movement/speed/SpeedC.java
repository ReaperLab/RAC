package mc.reaperlab.rac.checks.movement.speed;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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

        if (motionXZ > 0.63 + getMovementMod(user.getPlayer()))
            return new CheckReturn(false, "Modified Motion");

        super.onMoveEvent(event);
        return new CheckReturn(true, "");
    }

    public float getMovementMod(Player player) {
        float returnVal = 0;
        if (player.hasPotionEffect(PotionEffectType.SPEED))
            returnVal += 0.15f;
        if (player.isBlocking())
            returnVal -= 0.25f;
        if (player.getLocation().subtract(0,0.25,0).getBlock().getType() == Material.SLIME_BLOCK)
            returnVal -= 0.05f;
        return returnVal;
    }
}
