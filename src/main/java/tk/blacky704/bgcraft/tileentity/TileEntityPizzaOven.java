package tk.blacky704.bgcraft.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;
import tk.blacky704.bgcraft.block.BlockPizzaOven;
import tk.blacky704.bgcraft.init.ModItems;
import tk.blacky704.bgcraft.reference.Names;

/**
 * @author Blacky
 */
public class TileEntityPizzaOven extends TileEntity implements ISidedInventory
{
    public static final int inventorySize = 3;
    private ItemStack[] inventory;
    private String guiName = Names.Containers.PIZZA_OVEN;
    public int furnaceSpeed = 100;
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
            if (this.inventory[slot].stackSize <= amount)
            {
                itemStack = this.inventory[slot];
                this.inventory[slot] = null;
                return itemStack;
            }
            else
            {
                itemStack = this.inventory[slot].splitStack(amount);
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
    }

    @Override
    public void closeInventory()
    {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        String oreDic = OreDictionary.getOreName(Item.getIdFromItem(itemStack.getItem()));
        if (slot == 0)
        {
            if (oreDic.startsWith("food") || oreDic.startsWith("crop") || oreDic.toLowerCase().contains("flour"))
            {
                return true;
            }
            Item item = itemStack.getItem();
            if (item == Items.porkchop) return true;
            if (item == Items.fish) return true;
            if (item == Items.beef) return true;
            if (item == Items.chicken) return true;
            if (item == Items.potato) return true;

            if(item == ModItems.tomato) return true;
        }
        else if (slot == 1 && isItemFuel(itemStack))
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
        if (itemStack == null)
        {
            return 0;
        }
        else
        {
            Item item = itemStack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);
                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }
                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }
            }
            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(itemStack);
        }
    }

    @Override
    public void updateEntity()
    {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if (this.burnTime > 0)
        {
            --this.burnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.burnTime != 0 || this.inventory[1] != null && this.inventory[0] != null)
            {
                if (this.burnTime == 0 && this.canSmelt())
                {
                    this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.inventory[1]);
                    if (this.burnTime > 0)
                    {
                        flag1 = true;

                        if (this.inventory[1] != null)
                        {
                            --this.inventory[1].stackSize;

                            if (this.inventory[1].stackSize == 0)
                            {
                                this.inventory[1] = inventory[1].getItem().getContainerItem(inventory[1]);
                            }
                        }
                    }
                }
                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;
                    if (this.cookTime == this.furnaceSpeed)
                    {
                        this.cookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                }
            }
            else
            {
                this.cookTime = 0;
            }

            if (flag != this.burnTime > 0)
            {
                flag1 = true;
                BlockPizzaOven.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (inventory[0] == null && isBurning())
        {
            this.cookTime = 0;
        }
        if (flag1)
        {
            this.markDirty();
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
            }
            else if (this.inventory[2].isItemEqual(itemStack))
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
        if (this.inventory[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
            if (itemstack == null) return false;
            if (this.inventory[2] == null && isItemValidForSlot(0, this.inventory[0])) return true;
            if (this.inventory[2] != null)
            {
                if (!this.inventory[2].isItemEqual(itemstack)) return false;
                int result = inventory[2].stackSize + itemstack.stackSize;
                return result <= getInventoryStackLimit() && result <= this.inventory[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
            }
        }
        return false;
    }

    public boolean isBurning()
    {
        return this.burnTime > 0;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_)
    {
        return new int[0];
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

    public int getBurnTimeRemainingScaled(int i)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = this.furnaceSpeed;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getCookProgressScaled(int i)
    {
        return this.cookTime * i / this.furnaceSpeed;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.burnTime = nbt.getShort("BurnTime");
        this.burnTime = nbt.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.inventory[1]);

        if (nbt.hasKey("CustomName", 8))
        {
            this.guiName = nbt.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setShort("BurnTime", (short) this.burnTime);
        nbt.setShort("CookTime", (short) this.burnTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            nbt.setString("CustomName", this.guiName);
        }
    }


}
