package tk.blacky704.bgcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import tk.blacky704.bgcraft.handler.GuiHandler;
import tk.blacky704.bgcraft.init.ModBlocks;
import tk.blacky704.bgcraft.init.ModItems;
import tk.blacky704.bgcraft.init.ModRecipes;
import tk.blacky704.bgcraft.init.ModTileEntities;
import tk.blacky704.bgcraft.proxy.IProxy;
import tk.blacky704.bgcraft.reference.Reference;
import tk.blacky704.bgcraft.util.LogHelper;

/**
 * @author Blacky
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class BGCraft
{
    @Instance(Reference.MOD_ID)
    public static BGCraft instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        ModItems.init();
        ModItems.initOreDic();
        ModBlocks.init();
        LogHelper.info("PreInit Complete");
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        ModTileEntities.init();
        ModRecipes.init();
        LogHelper.info("Init Complete");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        LogHelper.info("PostInit Complete");
    }
}
