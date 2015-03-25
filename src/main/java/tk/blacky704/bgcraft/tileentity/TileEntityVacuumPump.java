package tk.blacky704.bgcraft.tileentity;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import tk.blacky704.bgcraft.pressure.IPressureHandler;

import java.text.DecimalFormat;

/**
 * @author Blacky
 */
public class TileEntityVacuumPump extends TileEnergyHandler implements IPressureHandler
{
    private double pressure = 1;
    private double maxPressure = 20;
    private double maxNegativePressure = -20;
    private boolean reversed = false;

    public TileEntityVacuumPump()
    {
        super(42000, 40);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (this.hasEnergyToOperate() && this.pressure < this.maxPressure)
        {
//            if(this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord))
//            {
            this.pressure = !this.reversed ? this.pressure - 0.005 : this.pressure + 0.005;
            if (this.pressure >= this.maxPressure) this.pressure = this.maxPressure;
            else if (this.pressure <= this.maxNegativePressure) this.pressure = this.maxNegativePressure;
//            }
        }
        this.balancePressureWithAdjacentBlocks();
        this.pressure = Double.valueOf(new DecimalFormat("##.###").format(this.pressure));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.pressure = nbt.getDouble("Pressure");
        this.maxPressure = nbt.getDouble("MaxPressure");
        this.maxNegativePressure = nbt.getDouble("MaxNegativePressure");
        this.reversed = nbt.getBoolean("Reversed");
        if (this.pressure >= this.maxPressure) this.pressure = this.maxPressure;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setDouble("Pressure", this.pressure);
        nbt.setDouble("MaxPressure", this.maxPressure);
        nbt.setDouble("MaxNegativePressure", this.maxNegativePressure);
        nbt.setBoolean("Reversed", this.reversed);
    }

    @Override
    public double receivePressure(double pressure, double maxPressure, double maxNegativePressure)
    {
        return 0;
    }

    @Override
    public double extractPressure(double pressure, double maxPressure, double maxNegativePressure)
    {
        return 0;
    }

    @Override
    public void balancePressureWith(double pressure, int x, int y, int z)
    {
        TileEntity entity = this.worldObj.getTileEntity(x, y, z);
        if(entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            double balancedPressure = (this.pressure + handler.getPressure()) / 2;
            this.setPressure(balancedPressure);
            handler.setPressure(balancedPressure);
        }
    }

    @Override
    public double getPressure()
    {
        return this.pressure;
    }

    @Override
    public double getMaxPressure()
    {
        return this.maxPressure;
    }

    @Override
    public double getMaxNegativePressure()
    {
        return this.maxNegativePressure;
    }

    public void setMaxPressure(double maxPressure)
    {
        this.maxPressure = maxPressure;
    }

    @Override
    public void setMaxNegativePressure(double maxNegativePressure)
    {
        this.maxNegativePressure = maxNegativePressure;
    }

    @Override
    public void setPressure(double pressure)
    {
        this.pressure = Double.valueOf(new DecimalFormat("##.####").format(pressure));
        if(this.pressure >= this.maxPressure)
        {
            this.pressure = this.maxPressure;
        }
        if(this.pressure <= this.maxNegativePressure)
        {
            this.pressure = this.maxNegativePressure;
        }
    }

    @Override
    public void modifyPressure(double addedPressure)
    {
        this.pressure += addedPressure;
        if(this.pressure >= this.maxPressure)
        {
            this.pressure = this.maxPressure;
        }
        if(this.pressure <= this.maxNegativePressure)
        {
            this.pressure = this.maxNegativePressure;
        }
    }

    private void balancePressureWithAdjacentBlocks()
    {
        TileEntity entity = this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
        if(entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
        if(entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
        if(entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
        if(entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
        if(entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
        if(entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
    }
}
