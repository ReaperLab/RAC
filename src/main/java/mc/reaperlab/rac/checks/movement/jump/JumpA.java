package mc.reaperlab.rac.checks.movement.jump;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class JumpA extends Check {

    public JumpA() {
        super("Jump", "A", CheckType.MOVEMENT);
    }

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.getUser(event.getPlayer());

        float motionY = (float) (event.getTo().getY() - event.getFrom().getY());

        if (user.onGround() && motionY > 0.44 + getMovementMod(user.getPlayer())) {
            return new CheckReturn(false, "High jump");
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
