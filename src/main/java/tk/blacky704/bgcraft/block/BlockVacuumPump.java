package tk.blacky704.bgcraft.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.block.BlockBG;
import tk.blacky704.bgcraft.init.ModBlocks;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.tileentity.TileEntityVacuumPump;

/**
 * @author Blacky
 */
public class BlockVacuumPump extends BlockBG implements ITileEntityProvider
{
    private final boolean isActive;

    public BlockVacuumPump(boolean isActive)
    {
        super();
        this.isActive = isActive;
        this.setBlockName(this.isActive ? Names.Blocks.VACUUM_PUMP_ACTIVE : Names.Blocks.VACUUM_PUMP_IDLE);
    }

    public void updateBlockState(boolean active, World world, int x, int y, int z)
    {
        int i = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
//        keepInventory = true;
        if (active)
        {
            world.setBlock(x, y, z, ModBlocks.vacuumPumpActive);
        }
        else
            world.setBlock(x, y, z, ModBlocks.vacuumPumpIdle);
//        keepInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, i, 2);
        if (tileEntity != null)
        {
            tileEntity.validate();
            world.setTileEntity(x, y, z, tileEntity);
        }
    }

//    @Override
//    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float hitX, float hitY, float hitZ)
//    {
//        return true;
//    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityVacuumPump();
    }
}
