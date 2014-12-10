package tk.blacky704.bgcraft;

import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import tk.blacky704.bgcraft.init.ModItems;
import tk.blacky704.bgcraft.proxy.IProxy;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class BGCraft
{
    @Instance(Reference.MOD_ID)
    private static BGCraft instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
    }
}
