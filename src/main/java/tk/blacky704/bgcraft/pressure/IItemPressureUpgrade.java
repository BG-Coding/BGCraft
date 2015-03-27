package tk.blacky704.bgcraft.pressure;

/**
 * @author Blacky
 */
public interface IItemPressureUpgrade
{
    UpgradeType getUpgradeType();

    boolean canBeUsedInPipes();

    boolean canBeUsedInBlocks();
}
