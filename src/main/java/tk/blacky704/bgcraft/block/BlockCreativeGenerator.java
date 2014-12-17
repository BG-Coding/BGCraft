package tk.blacky704.bgcraft.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.item.ItemBG;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.tileentity.TileEntityCreativeGenerator;

/**
 * @author Blacky
 */
public class BlockCreativeGenerator extends BlockBG implements ITileEntityProvider
{
    public BlockCreativeGenerator()
    {
        super();
        this.setBlockName(Names.Blocks.CREATIVE_GENERATOR);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityCreativeGenerator();
    }
}
