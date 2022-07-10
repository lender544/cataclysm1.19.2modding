package L_Ender.cataclysm.client.event;

import L_Ender.cataclysm.config.CMConfig;
import L_Ender.cataclysm.entity.Ignis_Entity;
import L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import L_Ender.cataclysm.init.ModEffect;
import L_Ender.cataclysm.init.ModItems;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientEvent {

    @SubscribeEvent
    public void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        Player player = Minecraft.getInstance().player;
        float delta = Minecraft.getInstance().getFrameTime();
        float ticksExistedDelta = player.tickCount + delta;
        if (CMConfig.ScreenShake) {
            if (player != null) {
                float shakeAmplitude = 0;
                for (ScreenShake_Entity ScreenShake : player.level.getEntitiesOfClass(ScreenShake_Entity.class, player.getBoundingBox().inflate(20, 20, 20))) {
                    if (ScreenShake.distanceTo(player) < ScreenShake.getRadius()) {
                        shakeAmplitude += ScreenShake.getShakeAmount(player, delta);
                    }
                }
                if (shakeAmplitude > 1.0f) shakeAmplitude = 1.0f;
                event.setPitch((float) (event.getPitch() + shakeAmplitude * Math.cos(ticksExistedDelta * 3 + 2) * 25));
                event.setYaw((float) (event.getYaw() + shakeAmplitude * Math.cos(ticksExistedDelta * 5 + 1) * 25));
                event.setRoll((float) (event.getRoll() + shakeAmplitude * Math.cos(ticksExistedDelta * 4) * 25));
            }

            if (Minecraft.getInstance().player.getEffect(ModEffect.EFFECTSTUN.get()) != null && !Minecraft.getInstance().isPaused()) {
                MobEffectInstance effectinstance1 = Minecraft.getInstance().player.getEffect(ModEffect.EFFECTSTUN.get());
                float shakeAmplitude = (float) ((1 + effectinstance1.getAmplifier()) * 0.01);
                event.setPitch((float) (event.getPitch() + shakeAmplitude * Math.cos(ticksExistedDelta * 3 + 2) * 25));
                event.setYaw((float) (event.getYaw() + shakeAmplitude * Math.cos(ticksExistedDelta * 5 + 1) * 25));
                event.setRoll((float) (event.getRoll() + shakeAmplitude * Math.cos(ticksExistedDelta * 4) * 25));
            }
        }

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onFogDensity(ViewportEvent.RenderFog event) {
        FogType fogType = event.getCamera().getFluidInCamera();
        ItemStack itemstack = Minecraft.getInstance().player.getInventory().getArmor(3);
        if (itemstack.is(ModItems.IGNITIUM_HELMET.get()) && fogType == FogType.LAVA) {
            RenderSystem.setShaderFogStart(-8.0F);
            RenderSystem.setShaderFogEnd(50.0F);
        }

    }

    @SubscribeEvent
    public void onRenderHUD(RenderGuiOverlayEvent.Pre event) {
        Player player = Minecraft.getInstance().player;
        if (player != null && player.isPassenger()) {
            if (player.getVehicle() instanceof Ignis_Entity) {
                if (event.getOverlay().id().equals(VanillaGuiOverlay.HELMET.id())) {
                    Minecraft.getInstance().gui.setOverlayMessage(Component.translatable("you_cant_escape"), false);
                }
                if (event.getOverlay().id().equals(VanillaGuiOverlay.MOUNT_HEALTH.id())) {
                    event.setCanceled(true);
                }
            }
        }
    }

}
