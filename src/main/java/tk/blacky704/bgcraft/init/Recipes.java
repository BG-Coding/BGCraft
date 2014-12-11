package tk.blacky704.bgcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

/**
 * @author Blacky
 */
public class Recipes
{
    public static void init()
    {
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.firebricks), "bb ", "bb ", 'b', new ItemStack(ModItems.firebrick));
    }
}