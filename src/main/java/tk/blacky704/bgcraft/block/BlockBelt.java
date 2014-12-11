package tk.blacky704.bgcraft.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.block.tileEntity.TileEntityBelt;
import tk.blacky704.bgcraft.reference.Names;

/**
 * @author goeiecool9999
 */
public class BlockBelt extends BlockBG implements ITileEntityProvider
{
    public BlockBelt()
    {
        super();
        this.setBlockName(Names.Blocks.BELT);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityBelt();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
