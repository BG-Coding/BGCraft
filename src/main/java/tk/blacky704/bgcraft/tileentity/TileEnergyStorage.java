package tk.blacky704.bgcraft.tileentity;

import cofh.api.energy.IEnergyStorage;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Blacky
 */
public class TileEnergyStorage extends TileEntity implements IEnergyStorage
{
    private int capacity;
    private int maxReceive;
    private int maxExtract;
    private int energy;

    public TileEnergyStorage(int capacity)
    {
        this(capacity, capacity, capacity);
    }

    public TileEnergyStorage(int capacity, int maxTransfer)
    {
        this(capacity, maxTransfer, maxTransfer);
    }

    public TileEnergyStorage(int capacity, int maxReceive, int maxExtract)
    {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    @Override
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            NBTTagCompound nbt = new NBTTagCompound();
            this.writeToNBT(nbt);
            final S35PacketUpdateTileEntity tile = new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
            int radius = 100;
            worldObj.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(xCoord - radius, yCoord - radius, zCoord - radius, xCoord + radius, yCoord + radius, zCoord + radius), new IEntitySelector()
            {
                public boolean isEntityApplicable(Entity var1)
                {
                    if (var1 instanceof EntityPlayerMP)
                    {
                        EntityPlayerMP pl = (EntityPlayerMP) var1;
                        pl.playerNetServerHandler.sendPacket(tile);
                    }
                    return false;
                }
            });
        }
    }

    public void setMaxTransfer(int maxTransfer)
    {
        setMaxReceive(maxTransfer);
        setMaxExtract(maxTransfer);
    }

    public void setMaxReceive(int maxReceive)
    {

        this.maxReceive = maxReceive;
    }

    public void setMaxExtract(int maxExtract)
    {

        this.maxExtract = maxExtract;
    }

    public int getMaxReceive()
    {

        return maxReceive;
    }

    public int getMaxExtract()
    {

        return maxExtract;
    }

    public void setEnergyStored(int energy)
    {

        this.energy = energy;

        if (this.energy > capacity)
        {
            this.energy = capacity;
        }
        else if (this.energy < 0)
        {
            this.energy = 0;
        }
    }

    public void modifyEnergyStored(int energy)
    {
        this.energy += energy;

        if (this.energy > capacity)
        {
            this.energy = capacity;
        }
        else if (this.energy < 0)
        {
            this.energy = 0;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        this.energy = nbt.getInteger("Energy");

        if (energy > capacity)
        {
            energy = capacity;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if (energy < 0)
        {
            energy = 0;
        }
        nbt.setInteger("Energy", energy);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

        if (!simulate)
        {
            energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

        if (!simulate)
        {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored()
    {
        return energy;
    }

    @Override
    public int getMaxEnergyStored()
    {
        return capacity;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }
}
