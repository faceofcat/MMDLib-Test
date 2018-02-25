package net.ndrei.mmdlibtest.blocks;

import com.mcmoddev.lib.block.MMDBlockWithTile;
import net.minecraft.block.material.Material;
import net.ndrei.mmdlibtest.tiles.GuiTestTileC;

public class GuiTestBlockC extends MMDBlockWithTile<GuiTestTileC> {
    public GuiTestBlockC() {
        super(GuiTestTileC.class, Material.ROCK);
    }
}
