package net.ndrei.mmdlibtest.tiles;

import com.mcmoddev.lib.container.gui.FeatureWrapperGui;
import com.mcmoddev.lib.container.gui.GuiContext;
import com.mcmoddev.lib.container.gui.IWidgetGui;
import com.mcmoddev.lib.container.gui.layout.GridLayout;
import com.mcmoddev.lib.feature.ItemInventoryFeature;
import com.mcmoddev.lib.feature.SimpleWorkFeature;
import com.mcmoddev.lib.tile.MMDEnergyConsumerTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class GuiTestTileC extends MMDEnergyConsumerTileEntity {
    private final IItemHandler inputs;
    private final IItemHandler output;

    public GuiTestTileC() {
        super();

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

        // 5 energy per tick, 100 energy to do process = 20? ticks
        this.addFeature(new SimpleWorkFeature("work", this.battery, 5,
            () -> {
                // return amount of power needed to process current input
                final ItemStack input = this.inputs.getStackInSlot(0);
                final ItemStack output = input.isEmpty() ? input : FurnaceRecipes.instance().getSmeltingResult(input);
                return output.isEmpty() ? -1 : 100;
            },
            () -> {
                // return boolean saying if job was successfully completed
                final ItemStack input = this.inputs.getStackInSlot(0);
                final ItemStack output = input.isEmpty() ? input : FurnaceRecipes.instance().getSmeltingResult(input);
                if (!output.isEmpty() && ItemHandlerHelper.insertItemStacked(this.output, output, true).isEmpty()) {
                    this.inputs.extractItem(0, 1, false);
                    ItemHandlerHelper.insertItemStacked(this.output, output.copy(), false);
                    return true;
                }
                return false;
            }));
    }

    @Override
    protected IWidgetGui getContentWidgetGui(final GuiContext context) {
        return new GridLayout(16, 6)
            .addPiece(new FeatureWrapperGui(context, this, "inputs"), 3, 2, 2, 2)
            .addPiece(new FeatureWrapperGui(context, this, "outputs"), 8, 1, 6, 4);
    }

//    @Override
//    public void update() {
//        super.update();
//
//        if (this.battery.getStored() >= 100) {
//            final ItemStack input = this.inputs.getStackInSlot(0);
//            if (!input.isEmpty()) {
//                final ItemStack output = FurnaceRecipes.instance().getSmeltingResult(input);
//                if ((!output.isEmpty()) && ItemHandlerHelper.insertItemStacked(this.output, output, true).isEmpty()) {
//                    this.inputs.extractItem(0, 1, false);
//                    ItemHandlerHelper.insertItemStacked(this.output, output.copy(), false);
//                    this.battery.take(100, true);
//                }
//            }
//        }
//    }
}
