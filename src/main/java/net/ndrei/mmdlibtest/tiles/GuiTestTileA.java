package net.ndrei.mmdlibtest.tiles;

import java.awt.Color;
import javax.annotation.Nonnull;
import com.mcmoddev.lib.container.PlayerInventory;
import com.mcmoddev.lib.container.gui.FeatureWrapperGui;
import com.mcmoddev.lib.container.gui.GuiContext;
import com.mcmoddev.lib.container.gui.IWidgetGui;
import com.mcmoddev.lib.container.gui.layout.GridLayout;
import com.mcmoddev.lib.container.gui.layout.VerticalStackLayout;
import com.mcmoddev.lib.container.gui.util.Padding;
import com.mcmoddev.lib.feature.FluidTankFeature;
import com.mcmoddev.lib.feature.ForgeEnergyBatteryFeature;
import com.mcmoddev.lib.feature.ItemInventoryFeature;
import com.mcmoddev.lib.feature.PlayerInventoryFeature;
import com.mcmoddev.lib.tile.MMDFeaturesTileEntity;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiTestTileA extends MMDFeaturesTileEntity {
    public GuiTestTileA() {
        super();
    }

    @Override
    protected void initFeatures() {
        super.initFeatures();

        this.addFeature(new ItemInventoryFeature("inputs_1", 4,
            (slot, stack) -> !stack.isEmpty() && (stack.getItem() == Items.APPLE),
            (slot, stack) -> true))
            .setColumns(2)
            .setOverlayColor(Color.RED.getRGB(), 42);
        this.addFeature(new ItemInventoryFeature("inputs_2", 6,
            (slot, stack) -> !stack.isEmpty() && (stack.getItem() == Items.BREAD),
            (slot, stack) -> false))
            .setColumns(3)
            .setOverlayColor(Color.GREEN.getRGB(), 42);
        this.addFeature(new ItemInventoryFeature("inputs_3", 4,
            (slot, stack) -> !stack.isEmpty() && (stack.getItem() == Items.BEEF),
            (slot, stack) -> true))
            .setColumns(2)
            .setOverlayColor(Color.BLUE.getRGB(), 42);

        this.addFeature(new FluidTankFeature("fluid_a", 5000,
            fluid -> (fluid != null) && (fluid.getFluid() == FluidRegistry.WATER),
            fluid -> true));
        this.addFeature(new FluidTankFeature("fluid_b", 5000,
            fluid -> true,
            fluid -> false));

        this.addFeature(new PlayerInventoryFeature(PlayerInventory.INVENTORY, 9));
        this.addFeature(new PlayerInventoryFeature(PlayerInventory.QUICKBAR, 9));

        this.addFeature(new ForgeEnergyBatteryFeature("battery", 50000));
    }

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public IWidgetGui getRootWidgetGui(@Nonnull GuiContext context) {
        return new VerticalStackLayout()
            .addPiece(new GridLayout(10, 3)
                .addPiece(new FeatureWrapperGui(context, this, "battery"), 0, 0, 1, 3)
                .addPiece(new FeatureWrapperGui(context, this, "inputs_1"), 1, 1, 2, 2)
                .addPiece(new FeatureWrapperGui(context, this, "inputs_2"), 4, 1, 3, 2)
                .addPiece(new FeatureWrapperGui(context, this, "inputs_3"), 8, 1, 2, 2)
                .addPiece(new FeatureWrapperGui(context, this, "fluid_a"), 3, 0, 1, 3)
                .addPiece(new FeatureWrapperGui(context, this, "fluid_b"), 7, 0, 1, 3)
            )
            .addPiece(new FeatureWrapperGui(context, this, "player_inventory")
                .setPadding(Padding.top(7))
            )
            .addPiece(new FeatureWrapperGui(context, this, "player_quickbar")
                .setPadding(Padding.top(7))
            );
    }
}
