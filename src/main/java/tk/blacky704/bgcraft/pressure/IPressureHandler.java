package tk.blacky704.bgcraft.pressure;

/**
 * @author Blacky
 */
public interface IPressureHandler extends IPressureConnection
{
    double receivePressure(double pressure, double maxPressure, double maxNegativePressure);

    double extractPressure(double pressure, double maxPressure, double maxNegativePressure);

    void balancePressureWith(double pressure, int x, int y, int z);

    double getPressure();

    double getMaxPressure();

    double getMaxNegativePressure();

    void setMaxPressure(double maxPressure);

    void setMaxNegativePressure(double maxNegativePressure);

    void setPressure(double pressure);

    void modifyPressure(double addedPressure);
}
