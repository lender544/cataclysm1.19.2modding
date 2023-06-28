package com.github.L_Ender.cataclysm.init;

import com.github.L_Ender.cataclysm.cataclysm;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, cataclysm.MODID);

    public static final RegistryObject<SoundEvent> GOLEMDEATH = SOUNDS.register("golemdeath",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"golemdeath")));

    public static final RegistryObject<SoundEvent> GOLEMHURT = SOUNDS.register("golemhurt",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"golemhurt")));

    public static final RegistryObject<SoundEvent> ENDERGUARDIANDEATH = SOUNDS.register("enderguardiandeath",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"enderguardiandeath")));

    public static final RegistryObject<SoundEvent> ENDERGUARDIANHURT = SOUNDS.register("enderguardianhurt",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"enderguardianhurt")));

    public static final RegistryObject<SoundEvent> GOLEMATTACK = SOUNDS.register("golemattack",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"golemattack")));

    public static final RegistryObject<SoundEvent> FLAMETHROWER = SOUNDS.register("flamethrower",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"flamethrower")));

    public static final RegistryObject<SoundEvent> HAMMERTIME = SOUNDS.register("hammertime",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"hammertime")));

    public static final RegistryObject<SoundEvent> STRONGSWING = SOUNDS.register("strongswing",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"strongswingattack")));

    public static final RegistryObject<SoundEvent> SWING = SOUNDS.register("swing",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"swingattack")));

    public static final RegistryObject<SoundEvent> MONSTROSITYSTEP = SOUNDS.register("monstrositystep",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"monstrositystep")));

    public static final RegistryObject<SoundEvent> MONSTROSITYGROWL = SOUNDS.register("monstrositygrowl",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"monstrositygrowl")));

    public static final RegistryObject<SoundEvent> MONSTROSITYDEATH = SOUNDS.register("monstrositydeath",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"monstrositydeath")));

    public static final RegistryObject<SoundEvent> MONSTROSITYAWAKEN = SOUNDS.register("monstrosityawaken",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"monstrosityawaken")));

    public static final RegistryObject<SoundEvent> MONSTROSITYHURT = SOUNDS.register("monstrosityhurt",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"monstrosityhurt")));

    public static final RegistryObject<SoundEvent> MONSTROSITYSHOOT = SOUNDS.register("monstrosityshoot",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"monstrosityshoot")));

    public static final RegistryObject<SoundEvent> MONSTROSITYLAND = SOUNDS.register("monstrosityland",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"monstrosityland")));

    public static final RegistryObject<SoundEvent> MONSTROSITY_MUSIC = SOUNDS.register("monstrosity_music",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"monstrosity_music")));

    public static final RegistryObject<SoundEvent> ENDERGUARDIAN_MUSIC = SOUNDS.register("enderguardian_music",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"enderguardian_music")));

    public static final RegistryObject<SoundEvent> IGNIS_MUSIC = SOUNDS.register("ignis_music",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"ignis_music")));

    public static final RegistryObject<SoundEvent> ENDERMAPTERA_HURT = SOUNDS.register("endermaptera_hurt",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"endermaptera_hurt")));

    public static final RegistryObject<SoundEvent> ENDERMAPTERA_AMBIENT = SOUNDS.register("endermaptera_ambient",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"endermaptera_ambient")));

    public static final RegistryObject<SoundEvent> ENDERMAPTERA_STEP = SOUNDS.register("endermaptera_step",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"endermaptera_step")));

    public static final RegistryObject<SoundEvent> ENDERMAPTERA_DEATH = SOUNDS.register("endermaptera_death",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"endermaptera_death")));

    public static final RegistryObject<SoundEvent> ENDER_GUARDIAN_FIST = SOUNDS.register("enderguardianattack",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"enderguardianattack")));

    public static final RegistryObject<SoundEvent> VOID_RUNE_RISING = SOUNDS.register("voidrunerising",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"voidrunerising")));

    public static final RegistryObject<SoundEvent> FLAME_BURST = SOUNDS.register("flame_burst",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"flame_burst")));

    public static final RegistryObject<SoundEvent> IGNIS_AMBIENT = SOUNDS.register("ignis_ambient",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"ignis_ambient")));

    public static final RegistryObject<SoundEvent> IGNIS_SHIELD_BREAK = SOUNDS.register("ignisshieldbreak",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"ignisshieldbreak")));

    public static final RegistryObject<SoundEvent> SWORD_STOMP = SOUNDS.register("sword_stomp",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"sword_stomp")));

    public static final RegistryObject<SoundEvent> IGNIS_HURT = SOUNDS.register("ignis_hurt",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"ignis_hurt")));

    public static final RegistryObject<SoundEvent> IGNIS_IMPACT = SOUNDS.register("ignis_impact",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"ignis_impact")));

    public static final RegistryObject<SoundEvent> IGNIS_ARMOR_BREAK = SOUNDS.register("ignis_armor_break",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"ignis_armor_break")));

    public static final RegistryObject<SoundEvent> IGNIS_POKE = SOUNDS.register("ignis_poke",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"ignis_poke")));

    public static final RegistryObject<SoundEvent> IGNIS_DEATH = SOUNDS.register("ignis_death",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"ignis_death")));

    public static final RegistryObject<SoundEvent> IGNIS_EARTHQUAKE = SOUNDS.register("ignis_earthquake",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"ignis_earthquake")));

    public static final RegistryObject<SoundEvent> REVENANT_BREATH = SOUNDS.register("revenant_breath",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"revenant_breath")));

    public static final RegistryObject<SoundEvent> REVENANT_HURT = SOUNDS.register("revenant_hurt",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"revenant_hurt")));

    public static final RegistryObject<SoundEvent> REVENANT_DEATH = SOUNDS.register("revenant_death",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"revenant_death")));

    public static final RegistryObject<SoundEvent> REVENANT_IDLE = SOUNDS.register("revenant_idle",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"revenant_idle")));

    public static final RegistryObject<SoundEvent> HARBINGER_LASER = SOUNDS.register("harbinger_laser",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_laser")));

    public static final RegistryObject<SoundEvent> HARBINGER_MODE_CHANGE = SOUNDS.register("harbinger_mode_change",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_mode_change")));

    public static final RegistryObject<SoundEvent> HARBINGER_PREPARE = SOUNDS.register("harbinger_prepare",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_prepare")));

    public static final RegistryObject<SoundEvent> HARBINGER_STUN = SOUNDS.register("harbinger_stun",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_stun")));

    public static final RegistryObject<SoundEvent> EMP_ACTIVATED = SOUNDS.register("emp_activated",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"emp_activated")));

    public static final RegistryObject<SoundEvent> DEATH_LASER = SOUNDS.register("death_laser",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"death_laser")));

    public static final RegistryObject<SoundEvent> HARBINGER_CHARGE_PREPARE = SOUNDS.register("harbinger_charge_prepare",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_charge_prepare")));

    public static final RegistryObject<SoundEvent> HARBINGER_CHARGE = SOUNDS.register("harbinger_charge",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_charge")));

    public static final RegistryObject<SoundEvent> HARBINGER_DEATHLASER_PREPARE = SOUNDS.register("harbinger_deathlaser_prepare",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_deathlaser_prepare")));

    public static final RegistryObject<SoundEvent> HARBINGER_MUSIC = SOUNDS.register("harbinger_music",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_music")));

    public static final RegistryObject<SoundEvent> HARBINGER_HURT = SOUNDS.register("harbinger_hurt",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_hurt")));

    public static final RegistryObject<SoundEvent> HARBINGER_IDLE = SOUNDS.register("harbinger_idle",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"harbinger_idle")));

    public static final RegistryObject<SoundEvent> ABYSS_BLAST = SOUNDS.register("abyss_blast",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"abyss_blast")));

    public static final RegistryObject<SoundEvent> ABYSS_BLAST_ONLY_CHARGE = SOUNDS.register("abyss_blast_only_charge",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"abyss_blast_only_charge")));

    public static final RegistryObject<SoundEvent> ABYSS_BLAST_ONLY_SHOOT = SOUNDS.register("abyss_blast_only_shoot",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"abyss_blast_only_shoot")));

    public static final RegistryObject<SoundEvent> LEVIATHAN_DEFEAT = SOUNDS.register("leviathan_defeat",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"leviathan_defeat")));

    public static final RegistryObject<SoundEvent> LEVIATHAN_HURT = SOUNDS.register("leviathan_hurt",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"leviathan_hurt")));

    public static final RegistryObject<SoundEvent> LEVIATHAN_IDLE = SOUNDS.register("leviathan_idle",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"leviathan_idle")));

    public static final RegistryObject<SoundEvent> LEVIATHAN_ROAR = SOUNDS.register("leviathan_roar",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"leviathan_roar")));

    public static final RegistryObject<SoundEvent> LEVIATHAN_STUN_ROAR = SOUNDS.register("leviathan_stun_roar",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"leviathan_stun_roar")));

    public static final RegistryObject<SoundEvent> LEVIATHAN_BITE = SOUNDS.register("leviathan_bite",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"leviathan_bite")));

    public static final RegistryObject<SoundEvent> LEVIATHAN_TENTACLE_STRIKE = SOUNDS.register("leviathan_tentacle_strike",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"leviathan_tentacle_strike")));

    public static final RegistryObject<SoundEvent> PORTAL_ABYSS_BLAST = SOUNDS.register("portal_abyss_blast",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"portal_abyss_blast")));

    public static final RegistryObject<SoundEvent> DEEPLING_IDLE = SOUNDS.register("deepling_idle",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"deepling_idle")));

    public static final RegistryObject<SoundEvent> DEEPLING_HURT = SOUNDS.register("deepling_hurt",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"deepling_hurt")));

    public static final RegistryObject<SoundEvent> DEEPLING_DEATH = SOUNDS.register("deepling_death",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"deepling_death")));

    public static final RegistryObject<SoundEvent> DEEPLING_SWING = SOUNDS.register("deepling_swing",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"deepling_swing")));

    public static final RegistryObject<SoundEvent> DEEPLING_LIGHT = SOUNDS.register("deepling_light",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"deepling_light")));

    public static final RegistryObject<SoundEvent> BLACK_HOLE_OPENING = SOUNDS.register("black_hole_opening",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"black_hole_opening")));

    public static final RegistryObject<SoundEvent> BLACK_HOLE_CLOSING = SOUNDS.register("black_hole_closing",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"black_hole_closing")));

    public static final RegistryObject<SoundEvent> BLACK_HOLE_LOOP = SOUNDS.register("black_hole_loop",
            () -> new SoundEvent(new ResourceLocation(cataclysm.MODID,"black_hole_loop")));



}
