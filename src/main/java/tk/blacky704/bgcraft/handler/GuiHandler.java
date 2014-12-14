package tk.blacky704.bgcraft.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.container.ContainerPizzaOven;
import tk.blacky704.bgcraft.gui.GuiPizzaOven;
import tk.blacky704.bgcraft.reference.Integers;
import tk.blacky704.bgcraft.tileentity.TileEntityPizzaOven;

/**
 * @author Blacky
 */
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (entity != null)
        {
            switch (ID)
            {
                case Integers.GuiIds.PIZZA_OVEN:
                    if (entity instanceof TileEntityPizzaOven)
                    {
                        return new ContainerPizzaOven(player.inventory, (TileEntityPizzaOven) entity);
                    }
                    return null;
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (entity != null)
        {
            switch (ID)
            {
                case Integers.GuiIds.PIZZA_OVEN:
                    if (entity instanceof TileEntityPizzaOven)
                    {
                        return new GuiPizzaOven(player.inventory, (TileEntityPizzaOven) entity);
                    }
                    return null;
            }
        }
        return null;
    }
}
