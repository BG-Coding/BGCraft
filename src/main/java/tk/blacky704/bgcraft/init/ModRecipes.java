package tk.blacky704.bgcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModRecipes
{
    public static void init()
    {
        //ShapedRecipes
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.firebricks), "bb ", "bb ", 'b', new ItemStack(ModItems.firebrick));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.pizzaOvenIdle), "bbb", "b b", "bbb", 'b', new ItemStack(ModBlocks.firebricks));
        //ShapedOreRecipes

        //ShapelessRecipes
        //ShapelessOreRecipes

        //Smelting
        GameRegistry.addSmelting(new ItemStack(Items.brick), new ItemStack(ModItems.firebrick), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModItems.tomato), new ItemStack(ModItems.driedTomato), 0.2f);
    }
}
