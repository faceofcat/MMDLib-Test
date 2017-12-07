package net.ndrei.mmdlibtest.blocks;

import com.mcmoddev.lib.block.MMDBlockWithTile;
import net.minecraft.block.material.Material;
import net.ndrei.mmdlibtest.tiles.GuiTestTileA;

public class GuiTestBlockA extends MMDBlockWithTile<GuiTestTileA> {
    public GuiTestBlockA() {
        super(GuiTestTileA.class, Material.ROCK);
    }
}
