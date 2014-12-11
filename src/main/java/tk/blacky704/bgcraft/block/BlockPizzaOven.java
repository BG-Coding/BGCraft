package tk.blacky704.bgcraft.block;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.init.ModBlocks;

/**
 * @author Blacky
 */
public class BlockPizzaOven extends TileEntity
{
    public boolean isMultiBlockStructure(World world, int x, int y, int z)
    {
        if (checkNorth(world, x, y, z))/*North*/ return true;
        if (checkEast(world, x, y, z))/*East*/ return true;
        if (checkSouth(world, x, y, z))/*South*/ return true;
        if (checkWest(world, x, y, z))/*West*/ return true;
        return false;
    }

    private static boolean checkNorth(World world, int x, int y, int z)
    {
        if (world.getBlock(x + -2, y + -1, z) == ModBlocks.firebricks)
        {
            if (world.getBlock(x + -2, y + -1, z + -1) == ModBlocks.firebricks)
            {
                if (world.getBlock(x + -2, y + -1, z + -2) == ModBlocks.firebricks)
                {
                    if (world.getBlock(x + -2, y, z) == ModBlocks.firebricks)
                    {
                        if (world.getBlock(x + -2, y, z + -1) == ModBlocks.firebricks)
                        {
                            if (world.getBlock(x + -2, y, z + -2) == ModBlocks.firebricks)
                            {
                                if (world.getBlock(x + -2, y + 1, z) == ModBlocks.firebricks)
                                {
                                    if (world.getBlock(x + -2, y + 1, z + -1) == ModBlocks.firebricks)
                                    {
                                        if (world.getBlock(x + -2, y + 1, z + -2) == ModBlocks.firebricks)
                                        {
                                            if (world.getBlock(x + -1, y + -1, z) == ModBlocks.firebricks)
                                            {
                                                if (world.getBlock(x + -1, y + -1, z + -1) == ModBlocks.firebricks)
                                                {
                                                    if (world.getBlock(x + -1, y + -1, z + -2) == ModBlocks.firebricks)
                                                    {
                                                        if (world.getBlock(x + -1, y, z) == Block.getBlockById(0))
                                                        {
                                                            if (world.getBlock(x + -1, y, z + -1) == Block.getBlockById(0))
                                                            {
                                                                if (world.getBlock(x + -1, y, z + -2) == ModBlocks.firebricks)
                                                                {
                                                                    if (world.getBlock(x + -1, y + 1, z) == ModBlocks.firebricks)
                                                                    {
                                                                        if (world.getBlock(x + -1, y + 1, z + -1) == ModBlocks.firebricks)
                                                                        {
                                                                            if (world.getBlock(x + -1, y + 1, z + -2) == ModBlocks.firebricks)
                                                                            {
                                                                                if (world.getBlock(x, y + -1, z) == ModBlocks.firebricks)
                                                                                {
                                                                                    if (world.getBlock(x, y + -1, z + -1) == ModBlocks.firebricks)
                                                                                    {
                                                                                        if (world.getBlock(x, y + -1, z + -2) == ModBlocks.firebricks)
                                                                                        {
                                                                                            if (world.getBlock(x, y, z + -1) == Block.getBlockById(0))
                                                                                            {
                                                                                                if (world.getBlock(x, y, z + -2) == ModBlocks.firebricks)
                                                                                                {
                                                                                                    if (world.getBlock(x, y + 1, z) == ModBlocks.firebricks)
                                                                                                    {
                                                                                                        if (world.getBlock(x, y + 1, z + -1) == ModBlocks.firebricks)
                                                                                                        {
                                                                                                            if (world.getBlock(x, y + 1, z + -2) == ModBlocks.firebricks)
                                                                                                            {
                                                                                                                if (world.getBlock(x + 1, y + -1, z) == ModBlocks.firebricks)
                                                                                                                {
                                                                                                                    if (world.getBlock(x + 1, y + -1, z + -1) == ModBlocks.firebricks)
                                                                                                                    {
                                                                                                                        if (world.getBlock(x + 1, y + -1, z + -2) == ModBlocks.firebricks)
                                                                                                                        {
                                                                                                                            if (world.getBlock(x + 1, y, z) == ModBlocks.firebricks)
                                                                                                                            {
                                                                                                                                if (world.getBlock(x + 1, y, z + -1) == ModBlocks.firebricks)
                                                                                                                                {
                                                                                                                                    if (world.getBlock(x + 1, y, z + -2) == ModBlocks.firebricks)
                                                                                                                                    {
                                                                                                                                        if (world.getBlock(x + 1, y + 1, z) == ModBlocks.firebricks)
                                                                                                                                        {
                                                                                                                                            if (world.getBlock(x + 1, y + 1, z + -1) == ModBlocks.firebricks)
                                                                                                                                            {
                                                                                                                                                if (world.getBlock(x + 1, y + 1, z + -2) == ModBlocks.firebricks)
                                                                                                                                                {
                                                                                                                                                    return true;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkEast(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y + -1, z + -2) == ModBlocks.firebricks)
        {
            if (world.getBlock(x + 1, y + -1, z + -2) == ModBlocks.firebricks)
            {
                if (world.getBlock(x + 2, y + -1, z + -2) == ModBlocks.firebricks)
                {
                    if (world.getBlock(x, y, z + -2) == ModBlocks.firebricks)
                    {
                        if (world.getBlock(x + 1, y, z + -2) == ModBlocks.firebricks)
                        {
                            if (world.getBlock(x + 2, y, z + -2) == ModBlocks.firebricks)
                            {
                                if (world.getBlock(x, y + 1, z + -2) == ModBlocks.firebricks)
                                {
                                    if (world.getBlock(x + 1, y + 1, z + -2) == ModBlocks.firebricks)
                                    {
                                        if (world.getBlock(x + 2, y + 1, z + -2) == ModBlocks.firebricks)
                                        {
                                            if (world.getBlock(x, y + -1, z + -1) == ModBlocks.firebricks)
                                            {
                                                if (world.getBlock(x + 1, y + -1, z + -1) == ModBlocks.firebricks)
                                                {
                                                    if (world.getBlock(x + 2, y + -1, z + -1) == ModBlocks.firebricks)
                                                    {
                                                        if (world.getBlock(x, y, z + -1) == Block.getBlockById(0))
                                                        {
                                                            if (world.getBlock(x + 1, y, z + -1) == Block.getBlockById(0))
                                                            {
                                                                if (world.getBlock(x + 2, y, z + -1) == ModBlocks.firebricks)
                                                                {
                                                                    if (world.getBlock(x, y + 1, z + -1) == ModBlocks.firebricks)
                                                                    {
                                                                        if (world.getBlock(x + 1, y + 1, z + -1) == ModBlocks.firebricks)
                                                                        {
                                                                            if (world.getBlock(x + 2, y + 1, z + -1) == ModBlocks.firebricks)
                                                                            {
                                                                                if (world.getBlock(x, y + -1, z) == ModBlocks.firebricks)
                                                                                {
                                                                                    if (world.getBlock(x + 1, y + -1, z) == ModBlocks.firebricks)
                                                                                    {
                                                                                        if (world.getBlock(x + 2, y + -1, z) == ModBlocks.firebricks)
                                                                                        {
                                                                                            if (world.getBlock(x + 1, y, z) == Block.getBlockById(0))
                                                                                            {
                                                                                                if (world.getBlock(x + 2, y, z) == ModBlocks.firebricks)
                                                                                                {
                                                                                                    if (world.getBlock(x, y + 1, z) == ModBlocks.firebricks)
                                                                                                    {
                                                                                                        if (world.getBlock(x + 1, y + 1, z) == ModBlocks.firebricks)
                                                                                                        {
                                                                                                            if (world.getBlock(x + 2, y + 1, z) == ModBlocks.firebricks)
                                                                                                            {
                                                                                                                if (world.getBlock(x, y + -1, z + 1) == ModBlocks.firebricks)
                                                                                                                {
                                                                                                                    if (world.getBlock(x + 1, y + -1, z + 1) == ModBlocks.firebricks)
                                                                                                                    {
                                                                                                                        if (world.getBlock(x + 2, y + -1, z + 1) == ModBlocks.firebricks)
                                                                                                                        {
                                                                                                                            if (world.getBlock(x, y, z + 1) == ModBlocks.firebricks)
                                                                                                                            {
                                                                                                                                if (world.getBlock(x + 1, y, z + 1) == ModBlocks.firebricks)
                                                                                                                                {
                                                                                                                                    if (world.getBlock(x + 2, y, z + 1) == ModBlocks.firebricks)
                                                                                                                                    {
                                                                                                                                        if (world.getBlock(x, y + 1, z + 1) == ModBlocks.firebricks)
                                                                                                                                        {
                                                                                                                                            if (world.getBlock(x + 1, y + 1, z + 1) == ModBlocks.firebricks)
                                                                                                                                            {
                                                                                                                                                if (world.getBlock(x + 2, y + 1, z + 1) == ModBlocks.firebricks)
                                                                                                                                                {
                                                                                                                                                    return true;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkSouth(World world, int x, int y, int z)
    {
        if (world.getBlock(x + 2, y + -1, z) == ModBlocks.firebricks)
        {
            if (world.getBlock(x + 2, y + -1, z + 1) == ModBlocks.firebricks)
            {
                if (world.getBlock(x + 2, y + -1, z + 2) == ModBlocks.firebricks)
                {
                    if (world.getBlock(x + 2, y, z) == ModBlocks.firebricks)
                    {
                        if (world.getBlock(x + 2, y, z + 1) == ModBlocks.firebricks)
                        {
                            if (world.getBlock(x + 2, y, z + 2) == ModBlocks.firebricks)
                            {
                                if (world.getBlock(x + 2, y + 1, z) == ModBlocks.firebricks)
                                {
                                    if (world.getBlock(x + 2, y + 1, z + 1) == ModBlocks.firebricks)
                                    {
                                        if (world.getBlock(x + 2, y + 1, z + 2) == ModBlocks.firebricks)
                                        {
                                            if (world.getBlock(x + 1, y + -1, z) == ModBlocks.firebricks)
                                            {
                                                if (world.getBlock(x + 1, y + -1, z + 1) == ModBlocks.firebricks)
                                                {
                                                    if (world.getBlock(x + 1, y + -1, z + 2) == ModBlocks.firebricks)
                                                    {
                                                        if (world.getBlock(x + 1, y, z) == Block.getBlockById(0))
                                                        {
                                                            if (world.getBlock(x + 1, y, z + 1) == Block.getBlockById(0))
                                                            {
                                                                if (world.getBlock(x + 1, y, z + 2) == ModBlocks.firebricks)
                                                                {
                                                                    if (world.getBlock(x + 1, y + 1, z) == ModBlocks.firebricks)
                                                                    {
                                                                        if (world.getBlock(x + 1, y + 1, z + 1) == ModBlocks.firebricks)
                                                                        {
                                                                            if (world.getBlock(x + 1, y + 1, z + 2) == ModBlocks.firebricks)
                                                                            {
                                                                                if (world.getBlock(x, y + -1, z) == ModBlocks.firebricks)
                                                                                {
                                                                                    if (world.getBlock(x, y + -1, z + 1) == ModBlocks.firebricks)
                                                                                    {
                                                                                        if (world.getBlock(x, y + -1, z + 2) == ModBlocks.firebricks)
                                                                                        {
                                                                                            if (world.getBlock(x, y, z + 1) == Block.getBlockById(0))
                                                                                            {
                                                                                                if (world.getBlock(x, y, z + 2) == ModBlocks.firebricks)
                                                                                                {
                                                                                                    if (world.getBlock(x, y + 1, z) == ModBlocks.firebricks)
                                                                                                    {
                                                                                                        if (world.getBlock(x, y + 1, z + 1) == ModBlocks.firebricks)
                                                                                                        {
                                                                                                            if (world.getBlock(x, y + 1, z + 2) == ModBlocks.firebricks)
                                                                                                            {
                                                                                                                if (world.getBlock(x + -1, y + -1, z) == ModBlocks.firebricks)
                                                                                                                {
                                                                                                                    if (world.getBlock(x + -1, y + -1, z + 1) == ModBlocks.firebricks)
                                                                                                                    {
                                                                                                                        if (world.getBlock(x + -1, y + -1, z + 2) == ModBlocks.firebricks)
                                                                                                                        {
                                                                                                                            if (world.getBlock(x + -1, y, z) == ModBlocks.firebricks)
                                                                                                                            {
                                                                                                                                if (world.getBlock(x + -1, y, z + 1) == ModBlocks.firebricks)
                                                                                                                                {
                                                                                                                                    if (world.getBlock(x + -1, y, z + 2) == ModBlocks.firebricks)
                                                                                                                                    {
                                                                                                                                        if (world.getBlock(x + -1, y + 1, z) == ModBlocks.firebricks)
                                                                                                                                        {
                                                                                                                                            if (world.getBlock(x + -1, y + 1, z + 1) == ModBlocks.firebricks)
                                                                                                                                            {
                                                                                                                                                if (world.getBlock(x + -1, y + 1, z + 2) == ModBlocks.firebricks)
                                                                                                                                                {
                                                                                                                                                    return true;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkWest(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y + -1, z + 2) == ModBlocks.firebricks)
        {
            if (world.getBlock(x + -1, y + -1, z + 2) == ModBlocks.firebricks)
            {
                if (world.getBlock(x + -2, y + -1, z + 2) == ModBlocks.firebricks)
                {
                    if (world.getBlock(x, y, z + 2) == ModBlocks.firebricks)
                    {
                        if (world.getBlock(x + -1, y, z + 2) == ModBlocks.firebricks)
                        {
                            if (world.getBlock(x + -2, y, z + 2) == ModBlocks.firebricks)
                            {
                                if (world.getBlock(x, y + 1, z + 2) == ModBlocks.firebricks)
                                {
                                    if (world.getBlock(x + -1, y + 1, z + 2) == ModBlocks.firebricks)
                                    {
                                        if (world.getBlock(x + -2, y + 1, z + 2) == ModBlocks.firebricks)
                                        {
                                            if (world.getBlock(x, y + -1, z + 1) == ModBlocks.firebricks)
                                            {
                                                if (world.getBlock(x + -1, y + -1, z + 1) == ModBlocks.firebricks)
                                                {
                                                    if (world.getBlock(x + -2, y + -1, z + 1) == ModBlocks.firebricks)
                                                    {
                                                        if (world.getBlock(x, y, z + 1) == Block.getBlockById(0))
                                                        {
                                                            if (world.getBlock(x + -1, y, z + 1) == Block.getBlockById(0))
                                                            {
                                                                if (world.getBlock(x + -2, y, z + 1) == ModBlocks.firebricks)
                                                                {
                                                                    if (world.getBlock(x, y + 1, z + 1) == ModBlocks.firebricks)
                                                                    {
                                                                        if (world.getBlock(x + -1, y + 1, z + 1) == ModBlocks.firebricks)
                                                                        {
                                                                            if (world.getBlock(x + -2, y + 1, z + 1) == ModBlocks.firebricks)
                                                                            {
                                                                                if (world.getBlock(x, y + -1, z) == ModBlocks.firebricks)
                                                                                {
                                                                                    if (world.getBlock(x + -1, y + -1, z) == ModBlocks.firebricks)
                                                                                    {
                                                                                        if (world.getBlock(x + -2, y + -1, z) == ModBlocks.firebricks)
                                                                                        {
                                                                                            if (world.getBlock(x + -1, y, z) == Block.getBlockById(0))
                                                                                            {
                                                                                                if (world.getBlock(x + -2, y, z) == ModBlocks.firebricks)
                                                                                                {
                                                                                                    if (world.getBlock(x, y + 1, z) == ModBlocks.firebricks)
                                                                                                    {
                                                                                                        if (world.getBlock(x + -1, y + 1, z) == ModBlocks.firebricks)
                                                                                                        {
                                                                                                            if (world.getBlock(x + -2, y + 1, z) == ModBlocks.firebricks)
                                                                                                            {
                                                                                                                if (world.getBlock(x, y + -1, z + -1) == ModBlocks.firebricks)
                                                                                                                {
                                                                                                                    if (world.getBlock(x + -1, y + -1, z + -1) == ModBlocks.firebricks)
                                                                                                                    {
                                                                                                                        if (world.getBlock(x + -2, y + -1, z + -1) == ModBlocks.firebricks)
                                                                                                                        {
                                                                                                                            if (world.getBlock(x, y, z + -1) == ModBlocks.firebricks)
                                                                                                                            {
                                                                                                                                if (world.getBlock(x + -1, y, z + -1) == ModBlocks.firebricks)
                                                                                                                                {
                                                                                                                                    if (world.getBlock(x + -2, y, z + -1) == ModBlocks.firebricks)
                                                                                                                                    {
                                                                                                                                        if (world.getBlock(x, y + 1, z + -1) == ModBlocks.firebricks)
                                                                                                                                        {
                                                                                                                                            if (world.getBlock(x + -1, y + 1, z + -1) == ModBlocks.firebricks)
                                                                                                                                            {
                                                                                                                                                if (world.getBlock(x + -2, y + 1, z + -1) == ModBlocks.firebricks)
                                                                                                                                                {
                                                                                                                                                    return true;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
