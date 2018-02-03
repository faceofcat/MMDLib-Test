package net.ndrei.mmdlibtest.items;

import java.awt.Color;
import javax.annotation.ParametersAreNonnullByDefault;
import com.mcmoddev.lib.container.IItemStackGuiProvider;
import com.mcmoddev.lib.container.PlayerInventory;
import com.mcmoddev.lib.feature.IFeatureHolder;
import com.mcmoddev.lib.feature.IItemStackFeatureHolder;
import com.mcmoddev.lib.feature.ItemInventoryFeature;
import com.mcmoddev.lib.feature.PlayerInventoryFeature;
import com.mcmoddev.lib.item.MMDItemWithGui;
import mcp.MethodsReturnNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GuiTestItemA extends MMDItemWithGui implements IItemStackGuiProvider, IItemStackFeatureHolder {
    @Override
    public void initFeatures(IFeatureHolder holder) {
        holder.addFeature(new ItemInventoryFeature("input", 9))
            .setColumns(3)
            .setOverlayColor(Color.GREEN.getRGB(), 42);
        holder.addFeature(new ItemInventoryFeature("output", 9))
            .setColumns(3)
            .setOverlayColor(Color.RED.getRGB(), 42);

        holder.addFeature(new PlayerInventoryFeature(PlayerInventory.INVENTORY, 9));
        holder.addFeature(new PlayerInventoryFeature(PlayerInventory.QUICKBAR, 9));
        holder.addFeature(new PlayerInventoryFeature(PlayerInventory.EQUIPMENT, 1));
        holder.addFeature(new PlayerInventoryFeature(PlayerInventory.OFF_HAND, 1));
    }
}
