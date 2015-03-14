package tk.blacky704.bgcraft.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Blacky
 */
public class TileEntityCreativeGenerator extends TileEntity implements IEnergyHandler
{
    protected EnergyStorage storage = new EnergyStorage(100000000);

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
    {
        return maxReceive;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        return maxExtract;
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return this.storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return this.storage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return true;
    }
}
