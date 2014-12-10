package tk.blacky704.bgcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tk.blacky704.bgcraft.item.ItemFirebrick;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemFirebrick firebrick = new ItemFirebrick();

    public static void init()
    {
        GameRegistry.registerItem(firebrick, Names.Items.FIRE_BRICK);
    }
}
