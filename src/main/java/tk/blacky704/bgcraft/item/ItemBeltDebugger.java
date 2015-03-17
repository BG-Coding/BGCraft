package tk.blacky704.bgcraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
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
            entityPlayer.addChatMessage(new ChatComponentText(String.valueOf("isFirst:") + String.valueOf(belt.isFirst())));
            entityPlayer.addChatMessage(new ChatComponentText(String.valueOf("energy:") + String.valueOf(belt.getEnergyStored())));
            entityPlayer.addChatMessage(new ChatComponentText(String.valueOf("operate:") + String.valueOf(belt.hasEnergyToOperate())));
            entityPlayer.addChatMessage(new ChatComponentText(String.valueOf("isRemote:") + String.valueOf(belt.getWorldObj().isRemote)));
            entityPlayer.addChatMessage(new ChatComponentText(String.valueOf("speed:") + String.valueOf(belt.animationSpeed)));
            entityPlayer.addChatMessage(new ChatComponentText(String.valueOf("entity:") + String.valueOf(belt.toString())));
        }

        return false;
    }


}
