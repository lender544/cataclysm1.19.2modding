package L_Ender.cataclysm.jei;

import L_Ender.cataclysm.crafting.WeaponInfusionRecipe;
import L_Ender.cataclysm.init.ModRecipeTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeManager;


import java.util.List;

public class CMRecipes
{
	private final RecipeManager recipeManager;

	public CMRecipes() {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;

		if (level != null) {
			this.recipeManager = level.getRecipeManager();
		} else {
			throw new NullPointerException("minecraft world must not be null.");
		}
	}

	public List<WeaponInfusionRecipe> getWeaponInfusionRecipes() {
		return recipeManager.getAllRecipesFor(ModRecipeTypes.WEAPON_INFUSION.get()).stream().toList();
	}

}
