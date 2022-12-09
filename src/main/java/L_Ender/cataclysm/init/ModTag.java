package L_Ender.cataclysm.init;

import L_Ender.cataclysm.cataclysm;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModTag {

    public static final TagKey<Block> NETHERITE_MONSTROSITY_IMMUNE = registerBlockTag("netherite_monstrosity_immune");

    public static final TagKey<EntityType<?>> TRAP_BLOCK_NOT_DETECTED = registerEntityTag("trap_block_not_detected");

    public static final TagKey<EntityType<?>> IGNIS_CANT_POKE = registerEntityTag("ignis_cant_poke");

    public static final TagKey<EntityType<?>> HARBINGER_NONE_TARGETS = registerEntityTag("harbinger_none_targets");

    public static final TagKey<Block> ENDER_GOLEM_CAN_DESTROY = registerBlockTag("ender_golem_can_destroy");

    public static final TagKey<Block> ENDER_GUARDIAN_CAN_DESTROY = registerBlockTag("ender_guardian_can_destroy");

    public static final TagKey<Block> ALTAR_DESTROY_IMMUNE = registerBlockTag("altar_destroy_immune");

    public static final TagKey<Block> IGNIS_CAN_DESTROY_CRACKED_BLOCK = registerBlockTag("ignis_can_destroy_cracked_block");

    public static final TagKey<Block> IGNIS_IMMUNE = registerBlockTag("ignis_immune");

    public static final TagKey<Block> HARBINGER_IMMUNE = registerBlockTag("harbinger_immune");

    public static final TagKey<Block> ENDERMAPTERA_CAN_NOT_SPAWN = registerBlockTag("endermaptera_can_not_spawn");

    public static final TagKey<Block> NETHERITE_MONSTROSITY_BREAK = registerBlockTag("netherite_monstrosity_break");

    private static TagKey<EntityType<?>> registerEntityTag(String name) {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(cataclysm.MODID, name));
    }

    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(cataclysm.MODID, name));
    }

    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(cataclysm.MODID, name));
    }

}
