package L_Ender.cataclysm.init;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.tileentities.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModTileentites {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,
            cataclysm.MODID);

    public static final RegistryObject<BlockEntityType<TileEntityObsidianExplosionTrapBricks>> OBSIDIAN_EXPLOSION_TRAP_BRICKS = TILE_ENTITY_TYPES.register("obsidian_explosion_trap_bricks", () ->
            BlockEntityType.Builder.of(TileEntityObsidianExplosionTrapBricks::new, ModBlocks.OBSIDIAN_EXPLOSION_TRAP_BRICKS.get()).build(null));

    public static final RegistryObject<BlockEntityType<TileEntityEnderGuardianSpawner>> ENDER_GUARDIAN_SPAWNER = TILE_ENTITY_TYPES.register("ender_guardian_spawner", () ->
            BlockEntityType.Builder.of(TileEntityEnderGuardianSpawner::new, ModBlocks.ENDER_GUARDIAN_SPAWNER.get()).build(null));

    public static final RegistryObject<BlockEntityType<TileEntityAltarOfFire>> ALTAR_OF_FIRE = TILE_ENTITY_TYPES.register("altar_of_fire", () ->
            BlockEntityType.Builder.of(TileEntityAltarOfFire::new, ModBlocks.ALTAR_OF_FIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<TileEntityEMP>> EMP = TILE_ENTITY_TYPES.register("emp", () ->
            BlockEntityType.Builder.of(TileEntityEMP::new, ModBlocks.EMP.get()).build(null));

    public static final RegistryObject<BlockEntityType<TileEntityMechanical_infusion_Anvil>> MECHANICAL_INFUSION_ANVIL = TILE_ENTITY_TYPES.register("mechanical_infusion_anvil", () ->
            BlockEntityType.Builder.of(TileEntityMechanical_infusion_Anvil::new, ModBlocks.MECHANICAL_INFUSION_ANVIL.get()).build(null));

}
