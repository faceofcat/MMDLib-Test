package net.ndrei.mmdlibtest.items;

import java.util.List;
import com.google.common.collect.Lists;
import com.mcmoddev.lib.container.IPlayerInventoryProvider;
import com.mcmoddev.lib.container.MMDContainer;
import com.mcmoddev.lib.container.PlayerInventory;
import com.mcmoddev.lib.container.PlayerInventoryInfo;
import com.mcmoddev.lib.item.MMDItemWithGui;

public class GuiTestItemA extends MMDItemWithGui implements IPlayerInventoryProvider {
    @Override
    public List<PlayerInventoryInfo> getPlayerSlots(MMDContainer container) {
        List<PlayerInventoryInfo> list = Lists.newArrayList();

        list.add(new PlayerInventoryInfo(PlayerInventory.INVENTORY, 0, 18, 9));
        list.add(new PlayerInventoryInfo(PlayerInventory.QUICKBAR, 0, 18 * 4 + 4, 9));
        list.add(new PlayerInventoryInfo(PlayerInventory.EQUIPMENT, 18 * 9 + 4, 0, 1));
        list.add(new PlayerInventoryInfo(PlayerInventory.OFF_HAND, 18 * 9 + 4, 18 * 4 + 4, 1));

        return list;
    }
}
