package L_Ender.cataclysm.client.model.entity;

import L_Ender.cataclysm.entity.projectile.Lava_Bomb_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ModelLava_Bomb extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;


    public ModelLava_Bomb() {
        texWidth = 64;
        texHeight = 64;

        root = new AdvancedModelBox(this);
        root.setRotationPoint(0.0F, 4F, 0.0F);



        root.setTextureOffset(0, 0).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        Lava_Bomb_Entity lava = (Lava_Bomb_Entity) entity;
        float delta = ageInTicks - entity.tickCount;
        Vec3 prevV = new Vec3(lava.prevDeltaMovementX, lava.prevDeltaMovementY, lava.prevDeltaMovementZ);
        Vec3 dv = prevV.add(lava.getDeltaMovement().subtract(prevV).scale(delta));
        double d = Math.sqrt(dv.x * dv.x + dv.y * dv.y + dv.z * dv.z);
        if (d != 0) {
            double a = dv.y / d;
            a = Math.max(-10, Math.min(1, a));
            float pitch = -(float) Math.asin(a);
            root.rotateAngleX = pitch + (float)Math.PI / 2f;

        }
    }




    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }
}