package toughasnails.temperature.modifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import toughasnails.api.temperature.Temperature;
import toughasnails.init.ModConfig;
import toughasnails.temperature.TemperatureDebugger;
import toughasnails.temperature.TemperatureDebugger.Modifier;

public class WeatherModifier extends TemperatureModifier
{
    public WeatherModifier(TemperatureDebugger debugger)
    {
        super(debugger);
    }
    
    @Override
    public Temperature modifyTarget(World world, EntityPlayer player, Temperature temperature)
    {
        int temperatureLevel = temperature.getRawValue();
        int newTemperatureLevel = temperatureLevel;
        
        BlockPos playerPos = player.getPosition();
        
        if (player.isWet())
        {
            debugger.start(Modifier.WET_TARGET, newTemperatureLevel);
            newTemperatureLevel += ModConfig.temperature.wetModifier;
            debugger.end(newTemperatureLevel);
        }
        else if (world.isRaining() && world.canSeeSky(playerPos) && world.getBiome(playerPos).getEnableSnow())
        {
            debugger.start(Modifier.SNOW_TARGET, newTemperatureLevel);
            newTemperatureLevel += ModConfig.temperature.snowModifier;
            debugger.end(newTemperatureLevel);
        }

        return new Temperature(newTemperatureLevel);
    }
}
