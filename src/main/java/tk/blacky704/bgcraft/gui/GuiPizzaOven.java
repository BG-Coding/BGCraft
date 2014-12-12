package tk.blacky704.bgcraft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import tk.blacky704.bgcraft.block.tileEntity.TileEntityPizzaOven;
import tk.blacky704.bgcraft.container.ContainerPizzaOven;
import tk.blacky704.bgcraft.reference.Reference;

import java.util.Locale;

/**
 * @author Blacky
 */
public class GuiPizzaOven extends GuiContainer
{
    private final TileEntityPizzaOven tileEntity;
    public static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID + "/textures/gui/container/pizzaoven.png");

    public GuiPizzaOven(InventoryPlayer inventory, TileEntityPizzaOven entity)
    {
        super(new ContainerPizzaOven(inventory, entity));
        this.tileEntity = entity;
        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        //String name = this.tileEntity.hasCustomInventoryName() ? this.tileEntity.getInventoryName() : I18n.format(this.tileEntity.getInventoryName());
        //this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal(this.tileEntity.getInventoryName()), this.xSize / 2 - this.fontRendererObj.getStringWidth(this.tileEntity.getInventoryName()) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {

    }
}
