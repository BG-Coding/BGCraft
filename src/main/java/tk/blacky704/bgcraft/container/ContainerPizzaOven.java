package tk.blacky704.bgcraft.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import tk.blacky704.bgcraft.tileentity.TileEntityPizzaOven;


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
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
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
        if(slot == 0)
        {
            this.entity.cookTime = newValue;
        }
        if(slot == 1)
        {
            this.entity.burnTime = newValue;
        }
        if(slot == 2)
        {
            this.entity.currentItemBurnTime = newValue;
        }
    }



    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotInt)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotInt);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotInt == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotInt != 1 && slotInt != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (slotInt >= 3 && slotInt < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (slotInt >= 30 && slotInt < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
