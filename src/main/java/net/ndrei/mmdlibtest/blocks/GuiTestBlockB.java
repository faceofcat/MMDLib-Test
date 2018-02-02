package net.ndrei.mmdlibtest.blocks;

import com.mcmoddev.lib.block.MMDBlockWithTile;
import net.minecraft.block.material.Material;
import net.ndrei.mmdlibtest.tiles.GuiTestTileB;

public class GuiTestBlockB extends MMDBlockWithTile<GuiTestTileB> {
    public GuiTestBlockB() {
        super(GuiTestTileB.class, Material.ROCK);
    }
}
