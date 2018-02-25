package net.ndrei.mmdlibtest;

import java.util.function.Supplier;
import com.mcmoddev.lib.block.MMDBlockWithTile;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.ndrei.mmdlibtest.blocks.GuiTestBlockA;
import net.ndrei.mmdlibtest.blocks.GuiTestBlockB;
import net.ndrei.mmdlibtest.blocks.GuiTestBlockC;

@Mod.EventBusSubscriber
public enum ModBlocks implements Supplier<Block> {
    GUI_TEST_A(GuiTestBlockA::new),
    GUI_TEST_B(GuiTestBlockB::new),
    GUI_TEST_C(GuiTestBlockC::new);

    private final Supplier<Block> supplier;
    private Block instance = null;

    ModBlocks(final Supplier<Block> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Block get() {
        return (this.instance == null) ? (this.instance = this.supplier.get()) : this.instance;
    }

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> ev) {
        for (final ModBlocks block : ModBlocks.values()) {
            block.get().setRegistryName(MMDLibTest.MODID, block.name().toLowerCase());
            block.get().setCreativeTab(CreativeTabs.MISC);
            ev.getRegistry().register(block.get());
            if (block.get() instanceof MMDBlockWithTile) {
                ((MMDBlockWithTile)block.get()).registerTile();
            }
        }
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> ev) {
        for (final ModBlocks block : ModBlocks.values()) {
            final ResourceLocation name = block.get().getRegistryName();
            if (name != null) {
                final ItemBlock ib = new ItemBlock(block.get());
                ib.setRegistryName(name.getResourceDomain(), "block_" + name.getResourcePath());
                ib.setCreativeTab(CreativeTabs.MISC);
                ev.getRegistry().register(ib);
            }
        }
    }
}
