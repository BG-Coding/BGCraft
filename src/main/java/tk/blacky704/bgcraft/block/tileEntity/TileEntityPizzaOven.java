package tk.blacky704.bgcraft.block.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;
import tk.blacky704.bgcraft.block.BlockPizzaOven;

/**
 * @author Blacky
 */
public class TileEntityPizzaOven extends TileEntity implements ISidedInventory
{
    public static final int inventorySize = 3;
    private ItemStack[] inventory;
    private String guiName;
    public int burnTimeMultiplier = 2;
    public int furnaceSpeed = 150;
    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;

    public TileEntityPizzaOven()
    {
        inventory = new ItemStack[inventorySize];
    }

    public void setGuiDisplayName(String name)
    {
        this.guiName = name;
    }

    @Override
    public int getSizeInventory()
    {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.inventory[slot] != null)
        {
            ItemStack itemStack;
            if(this.inventory[slot].stackSize <= amount)
            {
                itemStack = this.inventory[slot];
                this.inventory[slot] = null;
                return itemStack;
            } else
            {
                itemStack = this.inventory[slot].splitStack(amount);
                if(this.inventory[slot].stackSize == 0)
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
        if(this.inventory[slot] != null)
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
        if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
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
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq((double) this.xCoord + 0.5, (double) this.yCoord + 0.5, (double) this.zCoord + 0.5) <= 64D;
    }

    @Override
    public void openInventory()
    {
        //TODO
    }

    @Override
    public void closeInventory()
    {
        //TODO
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        String oreDic = OreDictionary.getOreName(Item.getIdFromItem(itemStack.getItem()));
        if (slot == 0)
        {
            if (oreDic.startsWith("food") || oreDic.startsWith("crop"))
            {
                return true;
            }
        } else if (slot == 1 && isItemFuel(itemStack))
        {
            return true;
        }
        return false;
    }

    private boolean isItemFuel(ItemStack itemStack)
    {
        return getItemBurnTime(itemStack) > 0;
    }

    private int getItemBurnTime(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            Item item = itemStack.getItem();
            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);
                String name = OreDictionary.getOreName(Block.getIdFromBlock(block));
                if (name.equals("treeWood")) return 600 * burnTimeMultiplier;
                if (name.equals("plankWood")) return 300 * burnTimeMultiplier;
                if (name.equals("slabWood")) return 150 * burnTimeMultiplier;
                if (name.equals("blockCoal")) return 1600 * 9 * burnTimeMultiplier;
            } else
            {
                String name = OreDictionary.getOreName(Item.getIdFromItem(item));
                if (name.equals("stickWood")) return 100 * burnTimeMultiplier;
                if (name.equals("treeSapling")) return 100 * burnTimeMultiplier;
                if (name.equals(OreDictionary.getOreName(Item.getIdFromItem(Items.coal))))
                    return 1600 * burnTimeMultiplier;
            }
        }
        return 0;
    }

    public void updateEntity()
    {
        boolean isBurning = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning())
        {
            this.burnTime--;
        }
        if (!this.worldObj.isRemote)
        {
            if (!this.isBurning() && this.canSmelt())
            {
                this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.inventory[1]);
                if (this.isBurning())
                {
                    flag1 = true;
                    if (this.inventory[1] != null)
                    {
                        this.inventory[1].stackSize--;
                        if (this.inventory[1].stackSize == 0)
                        {
                            this.inventory[1] = this.inventory[1].getItem().getContainerItem(this.inventory[1]);
                        }
                    }
                }
                if (this.isBurning() && this.canSmelt())
                {
                    this.cookTime++;
                    if (this.cookTime == this.furnaceSpeed)
                    {
                        this.cookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                } else
                {
                    this.cookTime = 0;
                }
                if (isBurning != this.isBurning())
                {
                    flag1 = true;
                    BlockPizzaOven.updateBlockState(this.isBurning(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                }
            }
            if (flag1)
            {
                this.markDirty();
            }
        }
    }

    private void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
            if (this.inventory[2] == null)
            {
                this.inventory[2] = itemStack.copy();
            } else if (this.inventory[2].isItemEqual(itemStack))
            {
                this.inventory[2].stackSize += itemStack.stackSize;
            }
            this.inventory[0].stackSize--;
            if (this.inventory[0].stackSize <= 0)
            {
                this.inventory[0] = null;
            }
        }
    }

    private boolean canSmelt()
    {
        if (this.inventory[0] != null)
        {
            ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
            if (itemStack == null) return false;
            if (this.inventory[2] == null) return true;
            if (!this.inventory[2].isItemEqual(itemStack)) return false;
            int result = this.inventory[2].stackSize + itemStack.stackSize;
            return result <= getInventoryStackLimit() && result <= itemStack.getMaxStackSize();
        }
        return false;
    }

    private boolean isBurning()
    {
        return this.burnTime > 0;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_)
    {
        //Dunt want nuh automatic stuffz
        return null;
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int p_102007_3_)
    {
        //Dunt want nuh automatic stuffz
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int p_102008_3_)
    {
        //Dunt want nuh automatic stuffz
        return false;
    }
}
