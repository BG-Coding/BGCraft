package tk.blacky704.bgcraft.tileentity;

import cofh.api.energy.IEnergyHandler;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Blacky
 */
public class TileEnergyHandler extends TileEnergyStorage implements IEnergyHandler
{
    public TileEnergyHandler(int capacity)
    {
        this(capacity, capacity, capacity);
    }

    public TileEnergyHandler(int capacity, int maxTransfer)
    {
        this(capacity, maxTransfer, maxTransfer);
    }

    public TileEnergyHandler(int capacity, int maxReceive, int maxExtract)
    {
        super(capacity, maxReceive, maxExtract);
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return true;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
    {
        return this.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        return this.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return super.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return super.getMaxEnergyStored();
    }

    public boolean canConnectEnergy()
    {
        return true;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        return super.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        return super.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored()
    {
        return super.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored()
    {
        return super.getMaxEnergyStored();
    }
}
