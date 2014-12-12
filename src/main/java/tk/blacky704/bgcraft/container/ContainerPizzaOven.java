package tk.blacky704.bgcraft.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import tk.blacky704.bgcraft.block.tileEntity.TileEntityPizzaOven;


/**
 * @author Blacky
 */
public class ContainerPizzaOven extends Container
{
    private final TileEntityPizzaOven entity;
    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastCookTime;

    public ContainerPizzaOven(InventoryPlayer inventory, TileEntityPizzaOven entity)
    {
        this.entity = entity;
        this.addSlotToContainer(new Slot(entity, 0, 56, 17));
        this.addSlotToContainer(new Slot(entity, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(inventory.player, entity, 2, 116, 35));
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 94 + i * 18));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + 1 * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.entity.cookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.entity.burnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.entity.currentItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for(int i = 0; i < this.crafters.size(); i++)
        {
            ICrafting iCrafting = (ICrafting) this.crafters.get(i);
            if(this.lastCookTime != this.entity.cookTime)
            {
                iCrafting.sendProgressBarUpdate(this, 0, this.entity.cookTime);
            }
            if(this.lastBurnTime != this.entity.burnTime)
            {
                iCrafting.sendProgressBarUpdate(this, 1, this.entity.burnTime);
            }
            if(this.lastCurrentItemBurnTime != this.entity.currentItemBurnTime)
            {
                iCrafting.sendProgressBarUpdate(this, 2, this.entity.currentItemBurnTime);
            }
        }
        this.lastCookTime = this.entity.cookTime;
        this.lastBurnTime = this.entity.burnTime;
        this.lastCookTime = this.entity.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue)
    {

    }



    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
}
