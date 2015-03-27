package tk.blacky704.bgcraft.tileentity;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import tk.blacky704.bgcraft.pressure.IItemPressureUpgrade;
import tk.blacky704.bgcraft.pressure.IPressureHandler;
import tk.blacky704.bgcraft.pressure.UpgradeType;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Blacky
 */
public class TileEntityVacuumPump extends TileEnergyHandler implements IPressureHandler, IInventory
{
    private double pressure = 1;
    private double maxPressure = 20;
    private double maxNegativePressure = -20;
    private boolean reversed = false;

    private final int initialMaxPressure = 20;
    private final int initialMaxNegativePressure = -20;

    private int pipeConnections = 0;
    private int maxConnections = 1;

    private ArrayList<UpgradeType> upgrades = new ArrayList<UpgradeType>();
    private int maxUpgrades = 2;

    private ItemStack[] inventory;
    private String guiName = "Vacuum Pump"; //TODO put values in reference class

    public TileEntityVacuumPump()
    {
        super(42000, 40);
        this.inventory = new ItemStack[2];
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (this.hasEnergyToOperate() && (this.pressure < this.maxPressure || this.pressure > this.maxNegativePressure))
        {
            this.pressure = !this.reversed ? this.pressure - 0.005 : this.pressure + 0.005;
            if (this.pressure >= this.maxPressure) this.pressure = this.maxPressure;
            else if (this.pressure <= this.maxNegativePressure) this.pressure = this.maxNegativePressure;
            this.modifyEnergyStored(-40);
        } else if(this.hasEnergyToOperate() && (this.pressure == this.maxPressure || this.pressure == this.maxNegativePressure))
        {
            this.modifyEnergyStored(-20);
        } else if(!this.hasEnergyToOperate())
        {
            if(!this.upgrades.contains(UpgradeType.PRESSURESTORAGE))
            {
                if (this.pressure < 1)
                {
                    this.pressure += 0.2;
                }
                if (this.pressure > 1)
                {
                    this.pressure -= 0.2;
                }
                if (this.pressure > 0.7 && this.pressure < 1.3)
                {
                    this.pressure = 1;
                }
            }
        }
        this.balancePressureWithAdjacentBlocks();
        this.pressure = Double.valueOf(new DecimalFormat("##.#####").format(this.pressure));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.pressure = nbt.getDouble("Pressure");
        this.maxPressure = nbt.getDouble("MaxPressure");
        this.maxNegativePressure = nbt.getDouble("MaxNegativePressure");
        this.reversed = nbt.getBoolean("Reversed");

        for(String upgrade : nbt.getString("Upgrades").split(", "))
        {
            this.upgrades.clear();
            if(upgrade.equals("PRESSURE"))
            {
                this.upgrades.add(UpgradeType.PRESSURE);
            }
            if(upgrade.equals("PRESSURESTORAGE"))
            {
                this.upgrades.add(UpgradeType.PRESSURESTORAGE);
            }
            if(upgrade.equals("MAXPRESSURE"))
            {
                this.upgrades.add(UpgradeType.MAXPRESSURE);
            }
        }

        this.keepPressureWithinLimits();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setDouble("Pressure", this.pressure);
        nbt.setDouble("MaxPressure", this.maxPressure);
        nbt.setDouble("MaxNegativePressure", this.maxNegativePressure);
        nbt.setBoolean("Reversed", this.reversed);
        StringBuilder upgrades = new StringBuilder();

        String prefix = "";
        for(UpgradeType upgrade : this.upgrades)
        {
            upgrades.append(prefix).append(upgrade.toString());
            prefix = ", ";
        }

        nbt.setString("Upgrades", upgrades.toString());
    }

    private void keepPressureWithinLimits()
    {
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
        if (entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            if(handler.canConnectToPressureBlocks()) handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
        if (entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            if(handler.canConnectToPressureBlocks()) handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
        if (entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            if(handler.canConnectToPressureBlocks()) handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
        if (entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            if(handler.canConnectToPressureBlocks()) handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
        if (entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            if(handler.canConnectToPressureBlocks()) handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
        entity = this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
        if (entity instanceof IPressureHandler)
        {
            IPressureHandler handler = (IPressureHandler) entity;
            if(handler.canConnectToPressureBlocks()) handler.balancePressureWith(this.pressure, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    /*~IPressureHandler*/

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
        this.pressure = Double.valueOf(new DecimalFormat("##.#####").format(pressure));
        this.keepPressureWithinLimits();
    }

    @Override
    public void modifyPressure(double addedPressure)
    {
        this.pressure += addedPressure;
        this.keepPressureWithinLimits();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return itemStack.getItem() instanceof IItemPressureUpgrade && this.inventory[slot] == null;
    }

    /*~IPressureConnection*/

    @Override
    public boolean canConnectToPressurePipe(ForgeDirection from)
    {
        return true;
    }

    @Override
    public boolean canConnectToPressurePipe()
    {
        return true;
    }

    @Override
    public boolean canConnectToPressureBlocks(ForgeDirection from)
    {
        return true;
    }

    @Override
    public boolean canConnectToPressureBlocks()
    {
        return true;
    }

    /*~IInventory*/

    @Override
    public int getSizeInventory()
    {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return this.inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.inventory[slot] != null)
        {
            ItemStack itemStack;
            if (this.inventory[slot].stackSize <= amount)
            {
                itemStack = this.inventory[slot];
                this.inventory[slot] = null;
                return itemStack;
            }
            else
            {
                this.inventory[slot].splitStack(amount);
                if (this.inventory[slot].stackSize == 0)
                {
                    this.inventory[slot] = null;
                }
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.inventory[slot] != null)
        {
            ItemStack itemStack = this.inventory[slot];
            this.inventory[slot] = null;
            return itemStack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return this.guiName;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return this.guiName != null && this.guiName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1; //TODO put value in reference class
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq((double) this.xCoord + 0.5, (double) this.yCoord + 0.5, (double) this.zCoord + 0.5) <= 64D;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }
}
