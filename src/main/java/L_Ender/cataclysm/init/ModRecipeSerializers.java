package L_Ender.cataclysm.init;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.crafting.WeaponInfusionRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers
{
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, cataclysm.MODID);

	public static final RegistryObject<RecipeSerializer<?>> WEAPON_INFUSION = RECIPE_SERIALIZERS.register("weapon_infusion", WeaponInfusionRecipe.Serializer::new);

}
