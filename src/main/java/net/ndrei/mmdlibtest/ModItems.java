package net.ndrei.mmdlibtest;

import java.util.function.Supplier;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.ndrei.mmdlibtest.items.GuiTestItemA;

@Mod.EventBusSubscriber
public enum ModItems implements Supplier<Item> {
    GUI_TEST_A(GuiTestItemA::new);

    private Supplier<Item> supplier;
    private Item instance = null;

    ModItems(Supplier<Item> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Item get() {
        return (this.instance == null) ? (this.instance = this.supplier.get()) : this.instance;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> ev) {
        for (ModItems item : ModItems.values()) {
            item.get().setRegistryName(MMDLibTest.MODID, item.name().toLowerCase());
            item.get().setCreativeTab(CreativeTabs.MISC);
            ev.getRegistry().register(item.get());
        }
    }
}
