package tk.blacky704.bgcraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.reference.Names;

/**
 * @author Blacky
 */
public class ItemDriedTomato extends ItemFoodBG
{
    public ItemDriedTomato(int food)
    {
        super(food, false);
        this.setUnlocalizedName(Names.Items.DRIED_TOMATO);
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        return super.onEaten(itemStack, world, player);
    }
}
