package L_Ender.cataclysm.jei;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.crafting.WeaponInfusionRecipe;
import L_Ender.cataclysm.init.ModBlocks;
import L_Ender.cataclysm.init.ModMenu;
import L_Ender.cataclysm.init.ModRecipeTypes;
import L_Ender.cataclysm.inventory.WeaponInfusionMenu;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class LEnderCataclysmJEIPlugin implements IModPlugin {
    public static final ResourceLocation MOD = new ResourceLocation(cataclysm.MODID, "jei_plugin");

    public static final RecipeType<WeaponInfusionRecipe> WEAPON_INFUSION = RecipeType.create(cataclysm.MODID, "weapon_infusion", WeaponInfusionRecipe.class);


    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new WeaponInfusionRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    public ResourceLocation getPluginUid() {
        return MOD;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        CMRecipes modRecipes = new CMRecipes();
        registration.addRecipes(WEAPON_INFUSION, modRecipes.getWeaponInfusionRecipes());
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(WeaponInfusionMenu.class, ModMenu.WEAPON_INFUSION.get(), WEAPON_INFUSION, 0, 6, 9, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MECHANICAL_INFUSION_ANVIL.get()), WEAPON_INFUSION);

    }
}
