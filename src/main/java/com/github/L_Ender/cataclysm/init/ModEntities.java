package com.github.L_Ender.cataclysm.init;


import com.github.L_Ender.cataclysm.cataclysm;
import com.github.L_Ender.cataclysm.entity.*;
import com.github.L_Ender.cataclysm.entity.BossMonsters.*;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan.*;
import com.github.L_Ender.cataclysm.entity.Deepling.*;
import com.github.L_Ender.cataclysm.entity.Pet.The_Baby_Leviathan_Entity;
import com.github.L_Ender.cataclysm.entity.effect.*;
import com.github.L_Ender.cataclysm.entity.projectile.Ashen_Breath_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.*;

import com.google.common.base.Predicates;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = cataclysm.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, cataclysm.MODID);


    public static final RegistryObject<EntityType<Ender_Golem_Entity>> ENDER_GOLEM = ENTITY_TYPE.register("ender_golem", () -> EntityType.Builder.of(Ender_Golem_Entity::new, MobCategory.MONSTER)
            .sized(2.5F, 3.5F)
            .fireImmune()
            .build(cataclysm.MODID + ":ender_golem"));

    public static final RegistryObject<EntityType<Ender_Guardian_Entity>> ENDER_GUARDIAN = ENTITY_TYPE.register("ender_guardian", () -> EntityType.Builder.of(Ender_Guardian_Entity::new, MobCategory.MONSTER)
            .sized(2.5F, 3.8F)
            .fireImmune()
            .clientTrackingRange(10)
            .setShouldReceiveVelocityUpdates(true)
            .build(cataclysm.MODID + ":ender_guardian"));

    public static final RegistryObject<EntityType<Netherite_Monstrosity_Entity>> NETHERITE_MONSTROSITY = ENTITY_TYPE.register("netherite_monstrosity", () -> EntityType.Builder.of(Netherite_Monstrosity_Entity::new, MobCategory.MONSTER)
            .sized(3.0f, 5.75f)
            .fireImmune()
            .clientTrackingRange(4)
            .build(cataclysm.MODID + ":netherite_monstrosity"));

    public static final RegistryObject<EntityType<Lava_Bomb_Entity>> LAVA_BOMB = ENTITY_TYPE.register("lava_bomb", () -> EntityType.Builder.<Lava_Bomb_Entity>of(Lava_Bomb_Entity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .fireImmune()
            .setShouldReceiveVelocityUpdates(true)
            .setUpdateInterval(20)
            .build(cataclysm.MODID + ":lava_bomb"));

    public static final RegistryObject<EntityType<Nameless_Sorcerer_Entity>> NAMELESS_SORCERER = ENTITY_TYPE.register("nameless_sorcerer", () -> EntityType.Builder.of(Nameless_Sorcerer_Entity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F)
            .build(cataclysm.MODID + ":nameless_sorcerer"));

    public static final RegistryObject<EntityType<Ignis_Entity>> IGNIS = ENTITY_TYPE.register("ignis", () -> EntityType.Builder.of(Ignis_Entity::new, MobCategory.MONSTER)
            .sized(2.25F, 3.5F)
            .fireImmune()
            .clientTrackingRange(10)
            .setShouldReceiveVelocityUpdates(true)
            .build(cataclysm.MODID + ":ignis"));

    public static final RegistryObject<EntityType<Ender_Guardian_Bullet_Entity>> ENDER_GUARDIAN_BULLET = ENTITY_TYPE.register("ender_guardian_bullet", () -> EntityType.Builder.<Ender_Guardian_Bullet_Entity>of(Ender_Guardian_Bullet_Entity::new, MobCategory.MISC)
            .sized(0.3125f, 0.3125f)
            .setUpdateInterval(1)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .build(cataclysm.MODID + ":ender_guardian_bullet"));

    public static final RegistryObject<EntityType<Void_Rune_Entity>> VOID_RUNE = ENTITY_TYPE.register("void_rune", () -> EntityType.Builder.<Void_Rune_Entity>of(Void_Rune_Entity::new, MobCategory.MISC)
            .sized(0.6F, 1.95F)
            .clientTrackingRange(6)
            .updateInterval(2)
            .fireImmune()
            .build(cataclysm.MODID + ":void_rune"));

    public static final RegistryObject<EntityType<Abyss_Mine_Entity>> ABYSS_MINE = ENTITY_TYPE.register("abyss_mine", () -> EntityType.Builder.<Abyss_Mine_Entity>of(Abyss_Mine_Entity::new, MobCategory.MISC)
            .sized(1.0F, 1.0F)
            .clientTrackingRange(6)
            .updateInterval(2)
            .fireImmune()
            .build(cataclysm.MODID + ":abyss_mine"));


    public static final RegistryObject<EntityType<Endermaptera_Entity>> ENDERMAPTERA = ENTITY_TYPE.register("endermaptera", () -> EntityType.Builder.of(Endermaptera_Entity::new, MobCategory.MONSTER)
            .sized(1.2F, 0.8f)
            .fireImmune()
            .build(cataclysm.MODID + ":endermaptera"));

    public static final RegistryObject<EntityType<Deepling_Entity>> DEEPLING = ENTITY_TYPE.register("deepling", () -> EntityType.Builder.<Deepling_Entity>of(Deepling_Entity::new, MobCategory.MONSTER)
            .sized(0.6F, 2.3f)
            .clientTrackingRange(8)
            .build(cataclysm.MODID + ":deepling"));

    public static final RegistryObject<EntityType<Deepling_Brute_Entity>> DEEPLING_BRUTE = ENTITY_TYPE.register("deepling_brute", () -> EntityType.Builder.<Deepling_Brute_Entity>of(Deepling_Brute_Entity::new, MobCategory.MONSTER)
            .sized(0.7F, 2.6f)
            .fireImmune()
            .clientTrackingRange(8)
            .build(cataclysm.MODID + ":deepling_brute"));

    public static final RegistryObject<EntityType<Deepling_Angler_Entity>> DEEPLING_ANGLER = ENTITY_TYPE.register("deepling_angler", () -> EntityType.Builder.of(Deepling_Angler_Entity::new, MobCategory.MONSTER)
            .sized(0.65F, 2.45f)
            .clientTrackingRange(8)
            .build(cataclysm.MODID + ":deepling_angler"));

    public static final RegistryObject<EntityType<Deepling_Priest_Entity>> DEEPLING_PRIEST = ENTITY_TYPE.register("deepling_priest", () -> EntityType.Builder.of(Deepling_Priest_Entity::new, MobCategory.MONSTER)
            .sized(0.6F, 2.3f)
            .clientTrackingRange(8)
            .build(cataclysm.MODID + ":deepling_priest"));

    public static final RegistryObject<EntityType<Lionfish_Entity>> LIONFISH = ENTITY_TYPE.register("lionfish", () -> EntityType.Builder.of(Lionfish_Entity::new, MobCategory.MONSTER)
            .sized(0.6F, 0.55f)
            .clientTrackingRange(6)
            .build(cataclysm.MODID + ":lionfish"));

    public static final RegistryObject<EntityType<Coralssus_Entity>> CORALSSUS = ENTITY_TYPE.register("coralssus", () -> EntityType.Builder.of(Coralssus_Entity::new, MobCategory.MONSTER)
            .sized(2.5F, 2.7F)
            .clientTrackingRange(10)
            .build(cataclysm.MODID + ":coralssus"));

    public static final RegistryObject<EntityType<Ignited_Revenant_Entity>> IGNITED_REVENANT = ENTITY_TYPE.register("ignited_revenant", () -> EntityType.Builder.of(Ignited_Revenant_Entity::new, MobCategory.MONSTER)
            .sized(1.6F, 2.8f)
            .fireImmune()
            .build(cataclysm.MODID + ":ignited_revenant"));

    public static final RegistryObject<EntityType<The_Harbinger_Entity>> THE_HARBINGER = ENTITY_TYPE.register("the_harbinger", () -> EntityType.Builder.of(The_Harbinger_Entity::new, MobCategory.MONSTER)
            .sized(1.6F, 3.75F)
            .fireImmune()
            .immuneTo(Blocks.WITHER_ROSE)
            .clientTrackingRange(10)
            .build(cataclysm.MODID + ":the_harbinger"));

    public static final RegistryObject<EntityType<The_Leviathan_Entity>> THE_LEVIATHAN = ENTITY_TYPE.register("the_leviathan", () -> EntityType.Builder.of(The_Leviathan_Entity::new, MobCategory.MONSTER)
            .sized(4.5F, 3F)
            .fireImmune()
            .clientTrackingRange(8)
            .setShouldReceiveVelocityUpdates(true)
            .build(cataclysm.MODID + ":the_leviathan"));

    public static final RegistryObject<EntityType<The_Baby_Leviathan_Entity>> THE_BABY_LEVIATHAN = ENTITY_TYPE.register("the_baby_leviathan", () -> EntityType.Builder.of(The_Baby_Leviathan_Entity::new, MobCategory.CREATURE)
            .sized(0.75F, 0.42F)
            .clientTrackingRange(10)
            .fireImmune()
            .build(cataclysm.MODID + ":the_baby_leviathan"));

    public static final RegistryObject<EntityType<Void_Scatter_Arrow_Entity>> VOID_SCATTER_ARROW = ENTITY_TYPE.register("void_scatter_arrow", () -> EntityType.Builder.<Void_Scatter_Arrow_Entity>of(Void_Scatter_Arrow_Entity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .setCustomClientFactory(Void_Scatter_Arrow_Entity::new)
            .updateInterval(20)
            .clientTrackingRange(4)
            .build(cataclysm.MODID + ":void_scatter_arrow"));

    public static final RegistryObject<EntityType<Void_Shard_Entity>> VOID_SHARD = ENTITY_TYPE.register("void_shard", () -> EntityType.Builder.<Void_Shard_Entity>of(Void_Shard_Entity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .setCustomClientFactory(Void_Shard_Entity::new)
            .updateInterval(20)
            .clientTrackingRange(4)
            .build(cataclysm.MODID + ":void_shard"));

    public static final RegistryObject<EntityType<Blazing_Bone_Entity>> BLAZING_BONE = ENTITY_TYPE.register("blazing_bone", () -> EntityType.Builder.<Blazing_Bone_Entity>of(Blazing_Bone_Entity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .updateInterval(20)
            .clientTrackingRange(4)
            .build(cataclysm.MODID + ":blazing_bone"));

    public static final RegistryObject<EntityType<Lionfish_Spike_Entity>> LIONFISH_SPIKE = ENTITY_TYPE.register("lionfish_spike", () -> EntityType.Builder.<Lionfish_Spike_Entity>of(Lionfish_Spike_Entity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .updateInterval(20)
            .clientTrackingRange(4)
            .build(cataclysm.MODID + ":lionfish_spike"));

    public static final RegistryObject<EntityType<ScreenShake_Entity>> SCREEN_SHAKE = ENTITY_TYPE.register("screen_shake", () -> EntityType.Builder.<ScreenShake_Entity>of(ScreenShake_Entity::new, MobCategory.MISC)
            .noSummon()
            .sized(1.0f, 1.0f)
            .setUpdateInterval(Integer.MAX_VALUE)
            .build(cataclysm.MODID + ":screen_shake"));

    public static final RegistryObject<EntityType<Cm_Falling_Block_Entity>> CM_FALLING_BLOCK = ENTITY_TYPE.register("cm_falling_block", () -> EntityType.Builder.<Cm_Falling_Block_Entity>of(Cm_Falling_Block_Entity::new, MobCategory.MISC)
            .sized(0.98F, 0.98F)
            .clientTrackingRange(10)
            .updateInterval(20)
            .build(cataclysm.MODID + ":cm_falling_block"));

    public static final RegistryObject<EntityType<Ignis_Fireball_Entity>> IGNIS_FIREBALL = ENTITY_TYPE.register("ignis_fireball", () -> EntityType.Builder.<Ignis_Fireball_Entity>of(Ignis_Fireball_Entity::new, MobCategory.MISC)
            .sized(0.6F, 0.6F)
            .setUpdateInterval(1)
            .setTrackingRange(20)
            .setShouldReceiveVelocityUpdates(true)
            .build(cataclysm.MODID + ":ignis_fireball"));

    public static final RegistryObject<EntityType<Ignis_Abyss_Fireball_Entity>> IGNIS_ABYSS_FIREBALL = ENTITY_TYPE.register("ignis_abyss_fireball", () -> EntityType.Builder.<Ignis_Abyss_Fireball_Entity>of(Ignis_Abyss_Fireball_Entity::new, MobCategory.MISC)
            .sized(0.6F, 0.6F)
            .setUpdateInterval(1)
            .setTrackingRange(20)
            .setShouldReceiveVelocityUpdates(true)
            .build(cataclysm.MODID + ":ignis_abyss_fireball"));

    public static final RegistryObject<EntityType<Wither_Smoke_Effect_Entity>> WITHER_SMOKE_EFFECT = ENTITY_TYPE.register("wither_smoke_effect", () -> EntityType.Builder.<Wither_Smoke_Effect_Entity>of(Wither_Smoke_Effect_Entity::new, MobCategory.MISC)
            .sized(6.0F, 0.5F)
            .fireImmune()
            .clientTrackingRange(10).updateInterval(Integer.MAX_VALUE)
            .build(cataclysm.MODID + ":wither_smoke_effect"));

    public static final RegistryObject<EntityType<Flame_Strike_Entity>> FLAME_STRIKE = ENTITY_TYPE.register("flame_strike", () -> EntityType.Builder.<Flame_Strike_Entity>of(Flame_Strike_Entity::new, MobCategory.MISC)
            .sized(6.0F, 0.5F)
            .fireImmune()
            .clientTrackingRange(10).updateInterval(Integer.MAX_VALUE)
            .build(cataclysm.MODID + ":flame_strike"));

    public static final RegistryObject<EntityType<Ashen_Breath_Entity>> ASHEN_BREATH = ENTITY_TYPE.register("ashen_breath", () -> EntityType.Builder.<Ashen_Breath_Entity>of(Ashen_Breath_Entity::new, MobCategory.MISC)
            .sized(0.0f, 0.0f)
            .fireImmune()
            .setUpdateInterval(1)
            .build(cataclysm.MODID + ":ashen_breath"));

    public static final RegistryObject<EntityType<Wall_Watcher_Entity>> WALL_WATCHER = ENTITY_TYPE.register("wall_watcher", () -> EntityType.Builder.<Wall_Watcher_Entity>of(Wall_Watcher_Entity::new, MobCategory.MISC)
            .sized(0.0F, 0.0F)
            .noSummon()
            .fireImmune()
            .build(cataclysm.MODID + ":wall_watcher"));

    public static final RegistryObject<EntityType<Death_Laser_Beam_Entity>> DEATH_LASER_BEAM = ENTITY_TYPE.register("death_laser_beam", () -> EntityType.Builder.<Death_Laser_Beam_Entity>of(Death_Laser_Beam_Entity::new, MobCategory.MISC)
            .sized(0.1F, 0.1F)
            .fireImmune()
            .build(cataclysm.MODID + ":death_laser_beam"));

    public static final RegistryObject<EntityType<Abyss_Blast_Entity>> ABYSS_BLAST = ENTITY_TYPE.register("abyss_blast", () -> EntityType.Builder.<Abyss_Blast_Entity>of(Abyss_Blast_Entity::new, MobCategory.MISC)
            .sized(0.1F, 0.1F)
            .fireImmune()
            .build(cataclysm.MODID + ":abyss_blast"));

    public static final RegistryObject<EntityType<Mini_Abyss_Blast_Entity>> MINI_ABYSS_BLAST = ENTITY_TYPE.register("mini_abyss_blast", () -> EntityType.Builder.<Mini_Abyss_Blast_Entity>of(Mini_Abyss_Blast_Entity::new, MobCategory.MISC)
            .sized(0.1F, 0.1F)
            .fireImmune()
            .build(cataclysm.MODID + ":mini_abyss_blast"));

    public static final RegistryObject<EntityType<Portal_Abyss_Blast_Entity>> PORTAL_ABYSS_BLAST = ENTITY_TYPE.register("portal_abyss_blast", () -> EntityType.Builder.<Portal_Abyss_Blast_Entity>of(Portal_Abyss_Blast_Entity::new, MobCategory.MISC)
            .sized(0.1F, 0.1F)
            .fireImmune()
            .build(cataclysm.MODID + ":portal_abyss_blast"));


    public static final RegistryObject<EntityType<Laser_Beam_Entity>> LASER_BEAM = ENTITY_TYPE.register("laser_beam", () -> EntityType.Builder.<Laser_Beam_Entity>of(Laser_Beam_Entity::new, MobCategory.MISC)
            .sized(0.3125F, 0.3125F)
            .fireImmune()
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(cataclysm.MODID + ":laser_beam"));

    public static final RegistryObject<EntityType<Wither_Missile_Entity>> WITHER_MISSILE = ENTITY_TYPE.register("wither_missile", () -> EntityType.Builder.<Wither_Missile_Entity>of(Wither_Missile_Entity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(4)
            .updateInterval(10)
            .setShouldReceiveVelocityUpdates(true)
            .build(cataclysm.MODID + ":wither_missile"));

    public static final RegistryObject<EntityType<Wither_Homing_Missile_Entity>> WITHER_HOMING_MISSILE = ENTITY_TYPE.register("wither_homing_missile", () -> EntityType.Builder.<Wither_Homing_Missile_Entity>of(Wither_Homing_Missile_Entity::new, MobCategory.MISC)
            .sized(0.25F, 0.25F)
            .setUpdateInterval(1)
            .setTrackingRange(20)
            .setShouldReceiveVelocityUpdates(true)
            .build(cataclysm.MODID + ":wither_homing_missile"));

    public static final RegistryObject<EntityType<Wither_Howitzer_Entity>> WITHER_HOWITZER = ENTITY_TYPE.register("wither_howitzer", () -> EntityType.Builder.<Wither_Howitzer_Entity>of(Wither_Howitzer_Entity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .fireImmune()
            .setShouldReceiveVelocityUpdates(true)
            .setUpdateInterval(20)
            .build(cataclysm.MODID + ":wither_howitzer"));

    public static final RegistryObject<EntityType<Abyss_Orb_Entity>> ABYSS_ORB = ENTITY_TYPE.register("abyss_orb", () -> EntityType.Builder.<Abyss_Orb_Entity>of(Abyss_Orb_Entity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .fireImmune()
            .setUpdateInterval(1)
            .setTrackingRange(20)
            .setShouldReceiveVelocityUpdates(true)
            .build(cataclysm.MODID + ":abyss_orb"));

    public static final RegistryObject<EntityType<Void_Howitzer_Entity>> VOID_HOWITZER = ENTITY_TYPE.register("void_howitzer", () -> EntityType.Builder.<Void_Howitzer_Entity>of(Void_Howitzer_Entity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .fireImmune()
            .setShouldReceiveVelocityUpdates(true)
            .setUpdateInterval(20)
            .build(cataclysm.MODID + ":void_howitzer"));

    public static final RegistryObject<EntityType<Eye_Of_Dungeon_Entity>> EYE_OF_DUNGEON = ENTITY_TYPE.register("eye_of_dungeon", () -> EntityType.Builder.<Eye_Of_Dungeon_Entity>of(Eye_Of_Dungeon_Entity::new, MobCategory.MISC)
            .sized(0.25F, 0.25F)
            .clientTrackingRange(4)
            .updateInterval(4)
            .build(cataclysm.MODID + ":eye_of_dungeon"));

    public static final RegistryObject<EntityType<Void_Vortex_Entity>> VOID_VORTEX = ENTITY_TYPE.register("void_vortex", () -> EntityType.Builder.<Void_Vortex_Entity>of(Void_Vortex_Entity::new, MobCategory.MISC)
            .sized(2.5F, 0.5F)
            .fireImmune()
            .clientTrackingRange(10).
            updateInterval(Integer.MAX_VALUE)
            .build(cataclysm.MODID + ":void_vortex"));

    public static final RegistryObject<EntityType<The_Leviathan_Tongue_Entity>> THE_LEVIATHAN_TONGUE = ENTITY_TYPE.register("the_leviathan_tongue", () -> EntityType.Builder.<The_Leviathan_Tongue_Entity>of(The_Leviathan_Tongue_Entity::new, MobCategory.MISC)
            .sized(0.1F, 0.1F)
            .build(cataclysm.MODID + ":the_leviathan_tongue"));

    public static final RegistryObject<EntityType<Tidal_Tentacle_Entity>> TIDAL_TENTACLE = ENTITY_TYPE.register("tidal_tentacle", () -> EntityType.Builder.<Tidal_Tentacle_Entity>of(Tidal_Tentacle_Entity::new, MobCategory.MISC)
            .sized(0.1F, 0.1F)
            .build(cataclysm.MODID + ":tidal_tentacle"));

    public static final RegistryObject<EntityType<ThrownCoral_Bardiche_Entity>> CORAL_BARDICHE = ENTITY_TYPE.register("coral_bardiche", () -> EntityType.Builder.<ThrownCoral_Bardiche_Entity>of(ThrownCoral_Bardiche_Entity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(cataclysm.MODID + ":coral_bardiche"));

    public static final RegistryObject<EntityType<Tidal_Hook_Entity>> TIDAL_HOOK = ENTITY_TYPE.register("tidal_hook", () -> EntityType.Builder.<Tidal_Hook_Entity>of(Tidal_Hook_Entity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .build(cataclysm.MODID + ":tidal_hook"));

    public static final RegistryObject<EntityType<Abyss_Portal_Entity>> ABYSS_PORTAL = ENTITY_TYPE.register("abyss_portal", () -> EntityType.Builder.<Abyss_Portal_Entity>of(Abyss_Portal_Entity::new, MobCategory.MISC)
            .fireImmune()
            .sized(3F, 0.15f)
            .setCustomClientFactory(Abyss_Portal_Entity::new)
            .build(cataclysm.MODID + ":abyss_portal"));


    public static final RegistryObject<EntityType<Abyss_Blast_Portal_Entity>> ABYSS_BLAST_PORTAL = ENTITY_TYPE.register("abyss_blast_portal", () -> EntityType.Builder.<Abyss_Blast_Portal_Entity>of(Abyss_Blast_Portal_Entity::new, MobCategory.MISC)
            .sized(4.0f, 0.5f)
            .fireImmune()
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(cataclysm.MODID + ":abyss_blast_portal"));

    public static final RegistryObject<EntityType<ThrownCoral_Spear_Entity>> CORAL_SPEAR = ENTITY_TYPE.register("coral_spear", () -> EntityType.Builder.<ThrownCoral_Spear_Entity>of(ThrownCoral_Spear_Entity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(cataclysm.MODID + ":coral_spear"));

    public static final RegistryObject<EntityType<Dimensional_Rift_Entity>> DIMENSIONAL_RIFT = ENTITY_TYPE.register("dimensional_rift", () -> EntityType.Builder.<Dimensional_Rift_Entity>of(Dimensional_Rift_Entity::new, MobCategory.MISC)
            .sized(2.0F, 2.0F)
            .fireImmune()
            .clientTrackingRange(10).
            updateInterval(Integer.MAX_VALUE)
            .build(cataclysm.MODID + ":dimensional_rift"));

    public static final RegistryObject<EntityType<Amethyst_Crab_Entity>> AMETHYST_CRAB = ENTITY_TYPE.register("amethyst_crab", () -> EntityType.Builder.of(Amethyst_Crab_Entity::new, MobCategory.MONSTER)
            .sized(2.5F, 3.5F)
            .build(cataclysm.MODID + ":amethyst_crab"));

    public static final RegistryObject<EntityType<EarthQuake_Entity>> EARTHQUAKE = ENTITY_TYPE.register("earthquake", () -> EntityType.Builder.<EarthQuake_Entity>of(EarthQuake_Entity::new, MobCategory.MISC).
            setShouldReceiveVelocityUpdates(true)
            .setTrackingRange(20)
            .setUpdateInterval(1)
            .sized(0.5f, 0.5f)
            .build(cataclysm.MODID + ":earthquake"));

    public static final RegistryObject<EntityType<Amethyst_Cluster_Projectile_Entity>> AMETHYST_CLUSTER_PROJECTILE = ENTITY_TYPE.register("amethyst_cluster_projectile", () -> EntityType.Builder.<Amethyst_Cluster_Projectile_Entity>of(Amethyst_Cluster_Projectile_Entity::new, MobCategory.MISC)
            .sized(0.5f, 0.0f)
            .fireImmune()
            .setShouldReceiveVelocityUpdates(true)
            .setUpdateInterval(20)
            .build(cataclysm.MODID + ":amethyst_cluster_projectile"));


    public static Predicate<LivingEntity> buildPredicateFromTag(TagKey<EntityType<?>> entityTag){
        if(entityTag == null){
            return Predicates.alwaysFalse();
        }else{
            return (com.google.common.base.Predicate<LivingEntity>) e -> e.isAlive() && e.getType().is(entityTag);
        }
    }

    public static boolean rollSpawn(int rolls, RandomSource random, MobSpawnType reason){
        if(reason == MobSpawnType.SPAWNER){
            return true;
        }else{
            return rolls <= 0 || random.nextInt(rolls) == 0;
        }
    }

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event) {
        SpawnPlacements.register(ENDERMAPTERA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Endermaptera_Entity::canSpawn);
        SpawnPlacements.register(DEEPLING_ANGLER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Deepling_Angler_Entity::candeeplingSpawn);
        SpawnPlacements.register(DEEPLING.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, Deepling_Entity::candeeplingSpawn);
        SpawnPlacements.register(DEEPLING_BRUTE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Deepling_Brute_Entity::candeeplingSpawn);
        event.put(ENDER_GOLEM.get(), Ender_Golem_Entity.ender_golem().build());
        event.put(NETHERITE_MONSTROSITY.get(), Netherite_Monstrosity_Entity.netherite_monstrosity().build());
        event.put(NAMELESS_SORCERER.get(), Nameless_Sorcerer_Entity.nameless_sorcerer().build());
        event.put(IGNIS.get(), Ignis_Entity.ignis().build());
        event.put(ENDER_GUARDIAN.get(), Ender_Guardian_Entity.ender_guardian().build());
        event.put(ENDERMAPTERA.get(), Endermaptera_Entity.endermaptera().build());
        event.put(IGNITED_REVENANT.get(), Ignited_Revenant_Entity.ignited_revenant().build());
        event.put(THE_HARBINGER.get(), The_Harbinger_Entity.harbinger().build());
        event.put(THE_LEVIATHAN.get(), The_Leviathan_Entity.leviathan().build());
        event.put(THE_BABY_LEVIATHAN.get(), The_Baby_Leviathan_Entity.babyleviathan().build());
        event.put(DEEPLING.get(), Deepling_Entity.deepling().build());
        event.put(DEEPLING_BRUTE.get(), Deepling_Brute_Entity.deeplingbrute().build());
        event.put(DEEPLING_ANGLER.get(), Deepling_Angler_Entity.deepling().build());
        event.put(DEEPLING_PRIEST.get(), Deepling_Priest_Entity.deeplingpriest().build());
        event.put(CORALSSUS.get(), Coralssus_Entity.coralssus().build());
        event.put(LIONFISH.get(), Lionfish_Entity.lionfish().build());
        event.put(AMETHYST_CRAB.get(), Amethyst_Crab_Entity.amethyst_crab().build());
    }
}

