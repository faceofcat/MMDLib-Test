package net.ndrei.mmdlibtest.tiles;

import java.awt.Color;
import javax.annotation.Nonnull;
import com.mcmoddev.lib.feature.FluidTankFeature;
import com.mcmoddev.lib.feature.ItemInventoryFeature;
import com.mcmoddev.lib.feature.PlayerInventoryFeature;
import com.mcmoddev.lib.gui.GuiContext;
import com.mcmoddev.lib.gui.IGuiPiece;
import com.mcmoddev.lib.gui.Padding;
import com.mcmoddev.lib.gui.PlayerInventory;
import com.mcmoddev.lib.gui.layout.FeatureGuiPiece;
import com.mcmoddev.lib.gui.layout.GridLayout;
import com.mcmoddev.lib.gui.layout.VerticalStackLayout;
import com.mcmoddev.lib.tile.MMDFeaturesTileEntity;

public class GuiTestTileA extends MMDFeaturesTileEntity {
    @Override
    protected void initFeatures() {
        super.initFeatures();

        this.addFeature(new ItemInventoryFeature("inputs_1", 4))
            .setColumns(2)
            .setOverlayColor(Color.RED.getRGB(), 42);
        this.addFeature(new ItemInventoryFeature("inputs_2", 6))
            .setColumns(3)
            .setOverlayColor(Color.GREEN.getRGB(), 42);
        this.addFeature(new ItemInventoryFeature("inputs_3", 4))
            .setColumns(2)
            .setOverlayColor(Color.BLUE.getRGB(), 42);

        this.addFeature(new FluidTankFeature("fluid_a", 5000, null, null));
        this.addFeature(new FluidTankFeature("fluid_b", 5000, null, null));

        this.addFeature(new PlayerInventoryFeature(PlayerInventory.QUICKBAR, 0, 18 * 2 + 8, 9));
    }

    @Override
    @Nonnull
    public IGuiPiece getRootPiece(@Nonnull GuiContext context) {
        return new VerticalStackLayout()
            .addPiece(new GridLayout(9, 3)
                .addPiece(new FeatureGuiPiece(context, this, "inputs_1"), 0, 1, 2, 2)
                .addPiece(new FeatureGuiPiece(context, this, "inputs_2"), 3, 1, 3, 2)
                .addPiece(new FeatureGuiPiece(context, this, "inputs_3"), 7, 1, 2, 2)
                .addPiece(new FeatureGuiPiece(context, this, "fluid_a"), 2, 0, 1, 3)
                .addPiece(new FeatureGuiPiece(context, this, "fluid_b"), 6, 0, 1, 3)
            )
            .addPiece(new FeatureGuiPiece(context, this, "player_quickbar")
                .setPadding(Padding.top(7))
            );
    }
}
