package net.ndrei.mmdlibtest.tiles;

import java.awt.Color;
import com.mcmoddev.lib.container.PlayerInventory;
import com.mcmoddev.lib.feature.ItemInventoryFeature;
import com.mcmoddev.lib.inventory.PlayerInventoryFeature;
import com.mcmoddev.lib.tile.MMDFeaturesTileEntity;

public class GuiTestTileA extends MMDFeaturesTileEntity {
    @Override
    protected void initFeatures() {
        super.initFeatures();

        super.addFeature(new ItemInventoryFeature("inputs_1", 4))
            .setSlotPositions(0, 0, 2)
            .setOverlayColor(Color.RED.getRGB(), 42);
        super.addFeature(new ItemInventoryFeature("inputs_2", 6))
            .setSlotPositions(18 * 3, 0, 3)
            .setOverlayColor(Color.GREEN.getRGB(), 42);
        super.addFeature(new ItemInventoryFeature("inputs_3", 4))
            .setSlotPositions(18 * 7, 0, 2)
            .setOverlayColor(Color.BLUE.getRGB(), 42);

        super.addFeature(new PlayerInventoryFeature(PlayerInventory.QUICKBAR, 0, 18 * 2 + 8, 9));
    }
}
