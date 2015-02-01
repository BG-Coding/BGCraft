package tk.blacky704.bgcraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.tileentity.TileEntityBelt;

/**
 * Created by Klaas on 31-1-2015.
 */
public class ItemBeltDebugger extends ItemBG
{
    public ItemBeltDebugger()
    {
        super();
        this.setUnlocalizedName(Names.Items.BELT_DEBUGGER);
    }


    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int posX, int posY, int posZ, int Side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof TileEntityBelt)
        {
            TileEntityBelt belt = (TileEntityBelt) tileEntity;
            entityPlayer.addChatMessage(new ChatComponentText(String.valueOf(belt.isLast())));
            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            double max = Math.max(Math.max(r, g), b);
            r *= max / 255;
            g *= max / 255;
            b *= max / 255;
            belt.r = r;
            belt.g = g;
            belt.b = b;
        }

        return false;
    }


}
