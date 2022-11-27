package mc.reaperlab.rac.checks.movement.gui;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;

public class GuiA extends Check {

    public GuiA() {super("GUI", "A", CheckType.MOVEMENT);}

    @Override
    public CheckReturn onMoveEvent(PlayerMoveEvent event) {
        User user = RAC.getUser(event.getPlayer());

        InventoryType inv = event.getPlayer().getOpenInventory().getType();

        if (!inv.equals(InventoryType.CRAFTING)) {
            user.packetsInGUI++;
            if (user.packetsInGUI > 8)
                return new CheckReturn(false, "Movement inside a GUI");
        } else {
            user.packetsInGUI = 0;
        }


        super.onMoveEvent(event);
        return new CheckReturn(true, "");
    }
}
