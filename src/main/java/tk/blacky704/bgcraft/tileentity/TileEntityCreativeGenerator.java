package tk.blacky704.bgcraft.tileentity;

import cofh.api.energy.IEnergyProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Blacky
 */
public class TileEntityCreativeGenerator extends TileEntity implements IEnergyProvider
{
    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        return maxExtract;
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return true;
    }
}
