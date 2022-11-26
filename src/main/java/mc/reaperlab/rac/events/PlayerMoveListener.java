package mc.reaperlab.rac.events;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerMoveEvent ev) {
        for (Check check : RAC.INSTANCE.getCheckManager().getChecksInType(CheckType.MOVEMENT)) {
            CheckReturn checkReturn = check.onMoveEvent(ev);
            if (!checkReturn.equals(CheckReturn.PASSED)) {
                ev.getPlayer().sendMessage("No");
            }
        }
    }
}
