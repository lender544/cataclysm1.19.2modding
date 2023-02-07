package L_Ender.cataclysm.jei;

import L_Ender.cataclysm.crafting.WeaponInfusionRecipe;
import L_Ender.cataclysm.init.ModBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class WeaponInfusionRecipeCategory implements IRecipeCategory<WeaponInfusionRecipe> {
    private final IDrawable background;
    private final IDrawable icon;
    private static final ResourceLocation TEXTURE = new ResourceLocation("cataclysm", "textures/gui/infusion.png");

    public WeaponInfusionRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(TEXTURE,26, 46, 125, 18);
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.MECHANICAL_INFUSION_ANVIL.get()));
    }

    @Override
    public RecipeType<WeaponInfusionRecipe> getRecipeType() {
        return LEnderCataclysmJEIPlugin.WEAPON_INFUSION;
    }

    @Override
    public Component getTitle() {
        return ModBlocks.MECHANICAL_INFUSION_ANVIL.get().getName();
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WeaponInfusionRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
                .addIngredients(recipe.getbaseIngredient());

        builder.addSlot(RecipeIngredientRole.INPUT, 50, 1)
                .addIngredients(recipe.getAdditionIngredient());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1)
                .addItemStack(recipe.getResultItem());
    }

    @Override
    public boolean isHandled(WeaponInfusionRecipe recipe) {
        return !recipe.isSpecial();
    }
}
