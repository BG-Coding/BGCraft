package tk.blacky704.bgcraft.tileentity;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import tk.blacky704.bgcraft.reference.Integers;

/**
 * @author Blacky
 */
public class TileEnergyHandler extends TileEnergyStorage implements IEnergyHandler
{
    private int usage;

    public TileEnergyHandler(int capacity, int usage)
    {
        this(capacity, capacity, capacity, usage);
    }

    public TileEnergyHandler(int capacity, int maxTransfer, int usage)
    {
        this(capacity, maxTransfer, maxTransfer, usage);
    }

    public TileEnergyHandler(int capacity, int maxReceive, int maxExtract, int usage)
    {
        super(capacity, maxReceive, maxExtract);
        this.usage = usage;
    }

    public void setUsage(int usage)
    {
        if(usage > this.getMaxEnergyStored())
        {
            usage = this.getMaxEnergyStored();
        }
        this.usage = usage;
    }

    public boolean hasEnergyToOperate()
    {
        return this.getEnergyStored() >= this.usage;
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
