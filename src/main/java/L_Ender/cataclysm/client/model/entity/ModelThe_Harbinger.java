package L_Ender.cataclysm.client.model.entity;

import L_Ender.cataclysm.entity.The_Harbinger_Entity;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelThe_Harbinger extends AdvancedEntityModel<The_Harbinger_Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox main_head;
    private final AdvancedModelBox head;
    private final AdvancedModelBox headgear;
    private final AdvancedModelBox eyebrows;
    private final AdvancedModelBox jaw;
    private final AdvancedModelBox righthead;
    private final AdvancedModelBox rightlaser;
    private final AdvancedModelBox rightlaser2;
    private final AdvancedModelBox right_guard;
    private final AdvancedModelBox right_upper_guard;
    private final AdvancedModelBox right_lower_guard;
    private final AdvancedModelBox lefthead;
    private final AdvancedModelBox left_side_guard;
    private final AdvancedModelBox left_upper_guard;
    private final AdvancedModelBox left_lower_guard;
    private final AdvancedModelBox right_laser;
    private final AdvancedModelBox right_laser2;
    private final AdvancedModelBox body;
    public final AdvancedModelBox nether_star;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tailbone;
    private final AdvancedModelBox jetpack;
    private final AdvancedModelBox rightside;
    private final AdvancedModelBox rightedge1;
    private final AdvancedModelBox rightedge2;
    private final AdvancedModelBox rightedge3;
    private final AdvancedModelBox leftside;
    private final AdvancedModelBox leftedge1;
    private final AdvancedModelBox leftedge2;
    private final AdvancedModelBox leftedge3;
    private ModelAnimator animator;

    public ModelThe_Harbinger() {
        texWidth = 128;
        texHeight = 128;

        root = new AdvancedModelBox(this);
        root.setRotationPoint(0.0F, 0.0F, 0.0F);


        main_head = new AdvancedModelBox(this);
        main_head.setRotationPoint(0.0F, 1.75F, 2.0F);
        root.addChild(main_head);
        setRotationAngle(main_head, 0.2182F, 0.0F, 0.0F);


        head = new AdvancedModelBox(this);
        head.setRotationPoint(0.0F, -1.0F, 0.0F);
        main_head.addChild(head);
        head.setTextureOffset(0, 42).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        headgear = new AdvancedModelBox(this);
        headgear.setRotationPoint(-0.5F, -5.0F, 1.0F);
        head.addChild(headgear);
        headgear.setTextureOffset(30, 11).addBox(-3.5F, 0.0F, -8.0F, 8.0F, 5.0F, 9.0F, 0.2F, false);
        headgear.setTextureOffset(51, 7).addBox(-1.5F, 2.0F, -8.0F, 4.0F, 2.0F, 1.0F, 0.15F, false);
        headgear.setTextureOffset(76, 73).addBox(-5.75F, -0.25F, -6.0F, 2.0F, 6.0F, 5.0F, 0.0F, false);
        headgear.setTextureOffset(76, 73).addBox(4.75F, -0.25F, -6.0F, 2.0F, 6.0F, 5.0F, 0.0F, true);

        eyebrows = new AdvancedModelBox(this);
        eyebrows.setRotationPoint(-0.5F, 3.0F, -2.0F);
        head.addChild(eyebrows);
        eyebrows.setTextureOffset(39, 5).addBox(-2.5F, -7.5F, -4.25F, 6.0F, 2.0F, 0.0F, 0.0F, false);

        jaw = new AdvancedModelBox(this);
        jaw.setRotationPoint(0.0F, 0.25F, 0.0F);
        main_head.addChild(jaw);
        jaw.setTextureOffset(58, 18).addBox(-4.0F, 1.0F, -6.0F, 8.0F, 0.0F, 8.0F, 0.0F, false);
        jaw.setTextureOffset(33, 26).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 8.0F, 8.0F, -0.01F, false);

        righthead = new AdvancedModelBox(this, "righthead");
        righthead.setRotationPoint(-10.0F, 4.0F, 1.0F);
        root.addChild(righthead);
        setRotationAngle(righthead, 0.3491F, 0.1309F, 0.0F);
        righthead.setTextureOffset(51, 73).addBox(-4.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

        rightlaser = new AdvancedModelBox(this);
        rightlaser.setRotationPoint(-1.0F, -1.0F, 1.5F);
        righthead.addChild(rightlaser);
        rightlaser.setTextureOffset(77, 50).addBox(-2.0F, -2.0F, -2.5F, 4.0F, 4.0F, 3.0F, 0.0F, false);

        rightlaser2 = new AdvancedModelBox(this);
        rightlaser2.setRotationPoint(0.0F, 0.0F, -0.5F);
        rightlaser.addChild(rightlaser2);
        rightlaser2.setTextureOffset(25, 43).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        rightlaser2.setTextureOffset(50, 56).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 2.0F, 0.3F, false);

        right_guard = new AdvancedModelBox(this);
        right_guard.setRotationPoint(8.0F, 20.0F, 2.25F);
        righthead.addChild(right_guard);


        right_upper_guard = new AdvancedModelBox(this);
        right_upper_guard.setRotationPoint(-9.0F, -22.0F, -7.0F);
        right_guard.addChild(right_upper_guard);
        right_upper_guard.setTextureOffset(33, 43).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 4.0F, 8.0F, 0.0F, false);

        right_lower_guard = new AdvancedModelBox(this);
        right_lower_guard.setRotationPoint(-9.0F, -20.75F, -6.0F);
        right_guard.addChild(right_lower_guard);
        right_lower_guard.setTextureOffset(0, 60).addBox(-3.0F, -0.25F, -1.0F, 6.0F, 3.0F, 8.0F, 0.1F, false);

        lefthead = new AdvancedModelBox(this, "lefthead");
        lefthead.setRotationPoint(10.0F, 4.0F, 1.0F);
        root.addChild(lefthead);
        setRotationAngle(lefthead, 0.3491F, -0.1309F, 0.0F);
        lefthead.setTextureOffset(51, 73).addBox(-2.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.0F, true);

        left_side_guard = new AdvancedModelBox(this);
        left_side_guard.setRotationPoint(1.0F, -1.0F, -0.75F);
        lefthead.addChild(left_side_guard);


        left_upper_guard = new AdvancedModelBox(this);
        left_upper_guard.setRotationPoint(0.0F, -1.0F, -4.0F);
        left_side_guard.addChild(left_upper_guard);
        left_upper_guard.setTextureOffset(33, 43).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 4.0F, 8.0F, 0.0F, true);

        left_lower_guard = new AdvancedModelBox(this);
        left_lower_guard.setRotationPoint(0.0F, 0.25F, -3.0F);
        left_side_guard.addChild(left_lower_guard);
        left_lower_guard.setTextureOffset(0, 60).addBox(-3.0F, -0.25F, -1.0F, 6.0F, 3.0F, 8.0F, 0.1F, true);

        right_laser = new AdvancedModelBox(this);
        right_laser.setRotationPoint(1.0F, -1.0F, 1.5F);
        lefthead.addChild(right_laser);
        right_laser.setTextureOffset(77, 50).addBox(-2.0F, -2.0F, -2.5F, 4.0F, 4.0F, 3.0F, 0.0F, false);

        right_laser2 = new AdvancedModelBox(this);
        right_laser2.setRotationPoint(0.0F, 0.0F, -2.5F);
        right_laser.addChild(right_laser2);
        right_laser2.setTextureOffset(25, 43).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        right_laser2.setTextureOffset(50, 56).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.3F, false);

        body = new AdvancedModelBox(this);
        body.setRotationPoint(0.0F, 4.0F, 2.0F);
        root.addChild(body);
        setRotationAngle(body, 0.3491F, 0.0F, 0.0F);
        body.setTextureOffset(32, 0).addBox(-9.0F, -0.4434F, -1.1553F, 18.0F, 2.0F, 2.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-6.0F, 1.0566F, -7.1553F, 12.0F, 12.0F, 7.0F, 0.0F, false);
        body.setTextureOffset(17, 78).addBox(-1.0F, 0.5566F, -1.1553F, 2.0F, 13.0F, 2.0F, 0.0F, false);
        body.setTextureOffset(24, 68).addBox(-4.0F, 2.5566F, -5.6553F, 8.0F, 6.0F, 5.0F, 0.0F, false);
        body.setTextureOffset(26, 80).addBox(-1.0F, 0.5F, 0.75F, 2.0F, 4.0F, 4.0F, 0.0F, false);

        nether_star = new AdvancedModelBox(this);
        nether_star.setRotationPoint(-0.25F, 3.0F, -2.75F);
        body.addChild(nether_star);

        tail = new AdvancedModelBox(this);
        tail.setRotationPoint(0.0F, 13.5566F, -0.1553F);
        body.addChild(tail);
        setRotationAngle(tail, 0.5236F, 0.0F, 0.0F);
        tail.setTextureOffset(81, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
        tail.setTextureOffset(60, 37).addBox(-4.0F, -2.0F, -6.0F, 8.0F, 6.0F, 6.0F, 0.0F, false);

        tailbone = new AdvancedModelBox(this);
        tailbone.setRotationPoint(0.0F, 6.0F, 0.0F);
        tail.addChild(tailbone);
        setRotationAngle(tailbone, 0.5672F, 0.0F, 0.0F);
        tailbone.setTextureOffset(39, 80).addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 4.0F, 0.0F, false);

        jetpack = new AdvancedModelBox(this);
        jetpack.setRotationPoint(0.0F, 0.0F, 2.5F);
        body.addChild(jetpack);
        setRotationAngle(jetpack, 0.2618F, 0.0F, 0.0F);


        rightside = new AdvancedModelBox(this);
        rightside.setRotationPoint(0.0F, 4.0F, 1.0F);
        jetpack.addChild(rightside);
        setRotationAngle(rightside, -0.0436F, -0.9163F, 0.1745F);
        rightside.setTextureOffset(0, 20).addBox(-3.8071F, -11.8715F, 8.293F, 8.0F, 13.0F, 8.0F, 0.0F, false);
        rightside.setTextureOffset(66, 27).addBox(-2.8071F, 1.1285F, 9.293F, 6.0F, 3.0F, 6.0F, 0.0F, false);
        rightside.setTextureOffset(25, 56).addBox(-3.8071F, -2.1215F, 8.293F, 8.0F, 3.0F, 8.0F, 0.5F, false);
        rightside.setTextureOffset(56, 5).addBox(-3.8071F, -6.1215F, 8.293F, 8.0F, 3.0F, 8.0F, 0.5F, false);
        rightside.setTextureOffset(0, 72).addBox(0.1929F, -4.8715F, 0.293F, 0.0F, 8.0F, 8.0F, 0.0F, false);

        rightedge1 = new AdvancedModelBox(this);
        rightedge1.setRotationPoint(-1.25F, 3.0F, 13.25F);
        rightside.addChild(rightedge1);
        setRotationAngle(rightedge1, 0.0F, 1.5708F, 0.0F);
        rightedge1.setTextureOffset(58, 56).addBox(1.0F, -5.0F, 1.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        rightedge2 = new AdvancedModelBox(this);
        rightedge2.setRotationPoint(-1.25F, 3.0F, 11.25F);
        rightside.addChild(rightedge2);
        rightedge2.setTextureOffset(58, 56).addBox(1.0F, -5.0F, 1.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        rightedge3 = new AdvancedModelBox(this);
        rightedge3.setRotationPoint(0.75F, 3.0F, 11.25F);
        rightside.addChild(rightedge3);
        setRotationAngle(rightedge3, 0.0F, -1.5708F, 0.0F);
        rightedge3.setTextureOffset(58, 56).addBox(1.0F, -5.0F, 1.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        leftside = new AdvancedModelBox(this);
        leftside.setRotationPoint(0.0F, 4.0F, 1.0F);
        jetpack.addChild(leftside);
        setRotationAngle(leftside, -0.0436F, 0.9163F, -0.1745F);
        leftside.setTextureOffset(0, 20).addBox(-4.1929F, -11.8715F, 8.293F, 8.0F, 13.0F, 8.0F, 0.0F, true);
        leftside.setTextureOffset(66, 27).addBox(-3.1929F, 1.1285F, 9.293F, 6.0F, 3.0F, 6.0F, 0.0F, true);
        leftside.setTextureOffset(25, 56).addBox(-4.1929F, -2.1215F, 8.293F, 8.0F, 3.0F, 8.0F, 0.5F, true);
        leftside.setTextureOffset(56, 5).addBox(-4.1929F, -6.1215F, 8.293F, 8.0F, 3.0F, 8.0F, 0.5F, true);
        leftside.setTextureOffset(0, 72).addBox(-0.1929F, -4.8715F, 0.293F, 0.0F, 8.0F, 8.0F, 0.0F, true);

        leftedge1 = new AdvancedModelBox(this);
        leftedge1.setRotationPoint(-1.25F, 3.0F, 11.25F);
        leftside.addChild(leftedge1);
        leftedge1.setTextureOffset(58, 56).addBox(1.0F, -5.0F, 1.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        leftedge2 = new AdvancedModelBox(this);
        leftedge2.setRotationPoint(0.75F, 3.0F, 11.25F);
        leftside.addChild(leftedge2);
        setRotationAngle(leftedge2, 0.0F, -1.5708F, 0.0F);
        leftedge2.setTextureOffset(58, 56).addBox(1.0F, -5.0F, 1.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        leftedge3 = new AdvancedModelBox(this);
        leftedge3.setRotationPoint(0.75F, 3.0F, 13.25F);
        leftside.addChild(leftedge3);
        setRotationAngle(leftedge3, 0.0F, 3.1416F, 0.0F);
        leftedge3.setTextureOffset(58, 56).addBox(1.0F, -5.0F, 1.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        animator = ModelAnimator.create();
        this.updateDefaultPose();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        animator.update(entity);
    }

    @Override
    public void setupAnim(The_Harbinger_Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float idleSpeed = 0.1F;
        float idleDegree = 0.1F;
        this.walk(body, idleSpeed * 0.75F, idleDegree * 0.5F, true, 0, -0.05F, ageInTicks, 1);
        this.walk(tail, idleSpeed * 0.75F, idleDegree * 0.35F, true, 1, -0.05F, ageInTicks, 1);
        this.main_head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.main_head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
    }

    public void prepareMobModel(The_Harbinger_Entity entityIn, float p_104096_, float p_104097_, float p_104098_) {
        setupHeadRotation(entityIn, this.righthead, 0);
        setupHeadRotation(entityIn, this.lefthead, 1);
    }

    private static void setupHeadRotation(The_Harbinger_Entity entityIn, AdvancedModelBox p_171073_, int p_171074_) {
        p_171073_.rotateAngleY = (entityIn.getHeadYRot(p_171074_) - entityIn.yBodyRot) * ((float)Math.PI / 180F);
        p_171073_.rotateAngleX = entityIn.getHeadXRot(p_171074_) * ((float)Math.PI / 180F);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(
                root,
                main_head,
                headgear,
                eyebrows,
                jaw,
                righthead,
                rightlaser,
                rightlaser2,
                right_guard,
                right_upper_guard,
                right_lower_guard,
                lefthead,
                left_side_guard,
                left_upper_guard,
                left_lower_guard,
                right_laser,
                right_laser2,
                body,
                tail,
                nether_star,
                tailbone,
                jetpack,
                rightside,
                rightedge1,
                rightedge2,
                rightedge3,
                leftside,
                leftedge1,
                leftedge2,
                leftedge3);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }
}
