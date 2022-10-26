package L_Ender.cataclysm.util;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.init.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Cataclysm_Group extends CreativeModeTab {

    public Cataclysm_Group() {
        super(cataclysm.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return ModItems.THE_INCINERATOR.get().getDefaultInstance();
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        super.fillItemList(items);
    }
}

