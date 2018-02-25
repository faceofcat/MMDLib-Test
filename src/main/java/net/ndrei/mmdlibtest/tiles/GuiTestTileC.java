package net.ndrei.mmdlibtest.tiles;

import com.mcmoddev.lib.container.PlayerInventory;
import com.mcmoddev.lib.container.gui.FeatureWrapperGui;
import com.mcmoddev.lib.container.gui.GuiContext;
import com.mcmoddev.lib.container.gui.IWidgetGui;
import com.mcmoddev.lib.container.gui.layout.GridLayout;
import com.mcmoddev.lib.container.gui.layout.VerticalStackLayout;
import com.mcmoddev.lib.container.gui.util.Padding;
import com.mcmoddev.lib.energy.ForgeEnergyStorage;
import com.mcmoddev.lib.feature.ForgeEnergyBatteryFeature;
import com.mcmoddev.lib.feature.ItemInventoryFeature;
import com.mcmoddev.lib.feature.PlayerInventoryFeature;
import com.mcmoddev.lib.tile.MMDFeaturesTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class GuiTestTileC extends MMDFeaturesTileEntity {
    private ForgeEnergyStorage battery;
    private IItemHandler inputs;
    private IItemHandler output;

    @Override
    protected void initFeatures() {
        super.initFeatures();

        this.battery = this.addFeature(new ForgeEnergyBatteryFeature("battery", 0, 50000, 120, 0))
            .getEnergyStorage();
        this.inputs = this.addFeature(new ItemInventoryFeature("inputs", 1,
            (slot, stack) -> !FurnaceRecipes.instance().getSmeltingResult(stack).isEmpty(),
            (slot, stack) -> false))
            .setColumns(1)
            .getInternalHandler();
        this.output = this.addFeature(new ItemInventoryFeature("outputs", 6,
            (slot, stack) -> false,
            (slot, stack) -> true))
            .setColumns(3)
            .getInternalHandler();

        this.addFeature(new PlayerInventoryFeature(PlayerInventory.INVENTORY, 9));
        this.addFeature(new PlayerInventoryFeature(PlayerInventory.QUICKBAR, 9));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IWidgetGui getRootWidgetGui(final GuiContext context) {
        return new VerticalStackLayout()
            .addPiece(new GridLayout(18, 6)
                .addPiece(new FeatureWrapperGui(context, this, "battery"), 0, 0, 2, 6)
                .addPiece(new FeatureWrapperGui(context, this, "inputs"), 5, 2, 2, 2)
                .addPiece(new FeatureWrapperGui(context, this, "outputs"), 10, 1, 6, 4)
            )
            .addPiece(new FeatureWrapperGui(context, this, "player_inventory")
                .setPadding(Padding.top(7))
            )
            .addPiece(new FeatureWrapperGui(context, this, "player_quickbar")
                .setPadding(Padding.top(7))
            );
    }

    @Override
    public void update() {
        super.update();

        if (this.battery.getStored() >= 100) {
            final ItemStack input = this.inputs.getStackInSlot(0);
            if (!input.isEmpty()) {
                final ItemStack output = FurnaceRecipes.instance().getSmeltingResult(input);
                if ((!output.isEmpty()) && ItemHandlerHelper.insertItemStacked(this.output, output, true).isEmpty()) {
                    this.inputs.extractItem(0, 1, false);
                    ItemHandlerHelper.insertItemStacked(this.output, output.copy(), false);
                    this.battery.take(100, true);
                }
            }
        }
    }
}
