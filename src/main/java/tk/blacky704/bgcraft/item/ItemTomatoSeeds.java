package tk.blacky704.bgcraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import tk.blacky704.bgcraft.block.BlockTomatoPlant;
import tk.blacky704.bgcraft.reference.Names;

/**
 * @author Blacky
 */
public class ItemTomatoSeeds extends ItemBG implements IPlantable
{
    private Block block;
    private Block soilBlock;

    public ItemTomatoSeeds(Block block, Block soilBlock)
    {
        super();
        this.setUnlocalizedName(Names.Items.TOMATO_SEEDS);
        this.block = block;
        this.soilBlock = soilBlock;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_7_ != 1)
        {
            return false;
        }
        else if (player.canPlayerEdit(x, y, z, p_77648_7_, itemStack) && player.canPlayerEdit(x, y + 1, z, p_77648_7_, itemStack))
        {
            if (world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z))
            {
                world.setBlock(x, y + 1, z, this.block);
                --itemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Crop;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return world.getBlock(x, y, z) instanceof BlockTomatoPlant ? world.getBlock(x, y ,z) : null;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
}
