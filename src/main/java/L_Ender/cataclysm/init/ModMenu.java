package L_Ender.cataclysm.init;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.inventory.WeaponInfusionMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenu {

    public static final DeferredRegister<MenuType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.MENU_TYPES, cataclysm.MODID);

    public static final RegistryObject<MenuType<WeaponInfusionMenu>> WEAPON_INFUSION = DEF_REG.register("weapon_infusion", () -> new MenuType<WeaponInfusionMenu>(WeaponInfusionMenu::new));

}
