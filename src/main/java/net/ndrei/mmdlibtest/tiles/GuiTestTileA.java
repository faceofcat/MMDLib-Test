package net.ndrei.mmdlibtest.tiles;

import java.awt.Color;
import javax.annotation.Nonnull;
import com.mcmoddev.lib.container.PlayerInventory;
import com.mcmoddev.lib.container.gui.GuiContext;
import com.mcmoddev.lib.container.gui.IWidgetGui;
import com.mcmoddev.lib.container.gui.layout.CanvasLayout;
import com.mcmoddev.lib.container.parser.IWidgetGuiParser;
import com.mcmoddev.lib.container.parser.WidgetParser;
import com.mcmoddev.lib.feature.FluidTankFeature;
import com.mcmoddev.lib.feature.ItemInventoryFeature;
import com.mcmoddev.lib.feature.PlayerInventoryFeature;
import com.mcmoddev.lib.tile.MMDFeaturesTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.ndrei.mmdlibtest.MMDLibTest;

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

        this.addFeature(new PlayerInventoryFeature(PlayerInventory.QUICKBAR, 9));
    }

    private IWidgetGuiParser cachedWidgetGui = null;

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public IWidgetGui getRootWidgetGui(@Nonnull GuiContext context) {
        if (this.cachedWidgetGui == null) {
            this.cachedWidgetGui = WidgetParser.parseRootPiece(null,
                new ResourceLocation(MMDLibTest.MODID, "guitesta")
            );
        }

        IWidgetGui gui = (this.cachedWidgetGui == null) ? null : this.cachedWidgetGui.createRootWidgetGui(context);
        return (gui == null) ? new CanvasLayout() : gui;
//        return new VerticalStackLayout()
//            .addPiece(new GridLayout(9, 3)
//                .addPiece(new FeatureWrapperGui(context, this, "inputs_1"), 0, 1, 2, 2)
//                .addPiece(new FeatureWrapperGui(context, this, "inputs_2"), 3, 1, 3, 2)
//                .addPiece(new FeatureWrapperGui(context, this, "inputs_3"), 7, 1, 2, 2)
//                .addPiece(new FeatureWrapperGui(context, this, "fluid_a"), 2, 0, 1, 3)
//                .addPiece(new FeatureWrapperGui(context, this, "fluid_b"), 6, 0, 1, 3)
//            )
//            .addPiece(new FeatureWrapperGui(context, this, "player_quickbar")
//                .setPadding(Padding.top(7))
//            );
    }
}
