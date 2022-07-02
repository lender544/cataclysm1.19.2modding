package L_Ender.cataclysm.init;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.tileentities.TileEntityEnderGuardianSpawner;
import L_Ender.cataclysm.tileentities.TileEntityObsidianExplosionTrapBricks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModTileentites {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,
            cataclysm.MODID);

    public static final RegistryObject<BlockEntityType<TileEntityObsidianExplosionTrapBricks>> OBSIDIAN_EXPLOSION_TRAP_BRICKS = TILE_ENTITY_TYPES.register("obsidian_explosion_trap_bricks", () ->
            BlockEntityType.Builder.of(TileEntityObsidianExplosionTrapBricks::new, ModBlocks.OBSIDIAN_EXPLOSION_TRAP_BRICKS.get()).build(null));

    public static final RegistryObject<BlockEntityType<TileEntityEnderGuardianSpawner>> ENDER_GUARDIAN_SPAWNER = TILE_ENTITY_TYPES.register("ender_guardian_spawner", () ->
            BlockEntityType.Builder.of(TileEntityEnderGuardianSpawner::new, ModBlocks.ENDER_GUARDIAN_SPAWNER.get()).build(null));

}
