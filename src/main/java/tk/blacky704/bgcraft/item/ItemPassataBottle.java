package tk.blacky704.bgcraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.reference.Names;

/**
 * @author Blacky
 */
public class ItemPassataBottle extends ItemFoodBG
{
    public ItemPassataBottle(int food)
    {
        super(food);
        this.setUnlocalizedName(Names.Items.PASSATA_BOTTLE);
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        super.onEaten(itemStack, world, player);
        return new ItemStack(Items.glass_bottle, 1, 0);
    }
}
