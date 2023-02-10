package L_Ender.cataclysm.init;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.crafting.WeaponfusionRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModRecipeTypes
{
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, cataclysm.MODID);

	public static final RegistryObject<RecipeType<WeaponfusionRecipe>> WEAPON_FUSION = RECIPE_TYPES.register("weapon_fusion", () -> registerRecipeType("weapon_fusion"));


	public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
		return new RecipeType<>()
		{
			public String toString() {
				return cataclysm.MODID + ":" + identifier;
			}
		};
	}
}
