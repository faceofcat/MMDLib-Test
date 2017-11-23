package net.ndrei.mmdlibtest;

import net.ndrei.mmdlibtest.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = MMDLibTest.MODID,
        name = MMDLibTest.NAME,
        version = MMDLibTest.VERSION,
        dependencies = "required-after:forge@[14.21.0.2327,);required-after:mmdlib;",
        acceptedMinecraftVersions = "[1.12,)")
@SuppressWarnings("WeakerAccess")
public class MMDLibTest {
    @Mod.Instance
    public static MMDLibTest instance;

    /** ID of this Mod */
    public static final String MODID = "mmdlibtest";

    /** Display name of this Mod */
    static final String NAME = "MMDLib Test";

    /**
     * Version number, in Major.Minor.Patch format. The minor number is
     * increased whenever a change is made that has the potential to break
     * compatibility with other mods that depend on this one.
     */
    public static final String VERSION = "1.0.0";

    private static final String PROXY_BASE = "net.ndrei.mmdlibtest.proxy.";
    @SidedProxy(clientSide = PROXY_BASE + "ClientProxy", serverSide = PROXY_BASE + "ServerProxy")
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getFormatterLogger(com.mcmoddev.lib.MMDLib.MODID);

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init(event);
        // if we have anything else to do here, check 'proxy.allsGood' first
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        // if we have anything else to do here, check 'proxy.allsGood' first
    }
}
