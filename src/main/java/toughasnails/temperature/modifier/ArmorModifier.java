package toughasnails.temperature.modifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import toughasnails.api.item.TANItems;
import toughasnails.api.temperature.Temperature;
import toughasnails.temperature.TemperatureDebugger;
import toughasnails.temperature.TemperatureDebugger.Modifier;

public class ArmorModifier extends TemperatureModifier
{
    public static final int ARMOR_RATE_MODIFIER = 25;
    public static final int JELLED_SLIME_TARGET_MODIFIER = -1;
    public static final int WOOL_TARGET_MODIFIER = 1;
    
    public ArmorModifier(TemperatureDebugger debugger)
    {
        super(debugger);
    }

    @Override
    public Temperature modifyTarget(World world, EntityPlayer player, Temperature temperature)
    {
        int temperatureLevel = temperature.getRawValue();
        int newTemperatureLevel = temperatureLevel;
        
        debugger.start(Modifier.ARMOR_TARGET, newTemperatureLevel);
        
        InventoryPlayer inventory = ((EntityPlayer)player).inventory;
        
        //Helmet
        if (inventory.armorInventory.get(3) != ItemStack.EMPTY)
        {
	        if (inventory.armorInventory.get(3).getItem() == TANItems.wool_helmet)
	        {
	        	newTemperatureLevel += WOOL_TARGET_MODIFIER;
	        }
	        if (inventory.armorInventory.get(3).getItem() == TANItems.jelled_slime_helmet)
	        {
	        	newTemperatureLevel += JELLED_SLIME_TARGET_MODIFIER;
	        }
        }
        
        //Chestplate
        if (inventory.armorInventory.get(2) != ItemStack.EMPTY)
        {
	        if (inventory.armorInventory.get(2).getItem() == TANItems.wool_chestplate)
	        {
	        	newTemperatureLevel += WOOL_TARGET_MODIFIER;
	        }
	        if (inventory.armorInventory.get(2).getItem() == TANItems.jelled_slime_chestplate)
	        {
	        	newTemperatureLevel += JELLED_SLIME_TARGET_MODIFIER;
	        }
        }
        
        //Leggings
        if (inventory.armorInventory.get(1) != ItemStack.EMPTY)
        {
	        if (inventory.armorInventory.get(1).getItem() == TANItems.wool_leggings)
	        {
	        	newTemperatureLevel += WOOL_TARGET_MODIFIER;
	        }
	        if (inventory.armorInventory.get(1).getItem() == TANItems.jelled_slime_leggings)
	        {
	        	newTemperatureLevel += JELLED_SLIME_TARGET_MODIFIER;
	        }
        }
        
        //Boots
        if (inventory.armorInventory.get(0) != ItemStack.EMPTY)
        {
	        if (inventory.armorInventory.get(0).getItem() == TANItems.wool_boots)
	        {
	        	newTemperatureLevel += WOOL_TARGET_MODIFIER;
	        }
	        if (inventory.armorInventory.get(0).getItem() == TANItems.jelled_slime_boots)
	        {
	        	newTemperatureLevel += JELLED_SLIME_TARGET_MODIFIER;
	        }
        }
        
        debugger.end(newTemperatureLevel);
        
        return new Temperature(newTemperatureLevel);
    }

}
