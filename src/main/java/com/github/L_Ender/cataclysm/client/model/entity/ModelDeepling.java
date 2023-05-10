package com.github.L_Ender.cataclysm.client.model.entity;// Made with Blockbench 4.7.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.github.L_Ender.cataclysm.entity.Deepling_Entity;
import com.github.L_Ender.cataclysm.entity.Ender_Golem_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Entity;

public class ModelDeepling extends AdvancedEntityModel<Deepling_Entity> {
	public final AdvancedModelBox root;
	public final AdvancedModelBox left_leg;
	public final AdvancedModelBox right_leg;
	public final AdvancedModelBox body;
	public final AdvancedModelBox headwear;
	public final AdvancedModelBox head;
	public final AdvancedModelBox head2;
	public final AdvancedModelBox r_fin;
	public final AdvancedModelBox l_fin;
	public final AdvancedModelBox right_arm;
	public final AdvancedModelBox left_arm;
	private ModelAnimator animator;

	public ModelDeepling() {
		texWidth = 64;
		texHeight = 64;


		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		left_leg = new AdvancedModelBox(this);
		left_leg.setRotationPoint(2.0F, -20.0F, 0.0F);
		root.addChild(left_leg);
		left_leg.setTextureOffset(40, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 20.0F, 2.0F, 0.0F, false);

		right_leg = new AdvancedModelBox(this);
		right_leg.setRotationPoint(-2.0F, -20.0F, 0.0F);
		root.addChild(right_leg);
		right_leg.setTextureOffset(44, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 20.0F, 2.0F, 0.0F, false);

		body = new AdvancedModelBox(this);
		body.setRotationPoint(0.0F, -20.0F, 0.0F);
		root.addChild(body);
		body.setTextureOffset(0, 32).addBox(-5.0F, -11.0F, -2.0F, 10.0F, 11.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(52, 29).addBox(0.0F, -11.0F, 2.0F, 0.0F, 11.0F, 4.0F, 0.0F, false);

		head = new AdvancedModelBox(this);
		head.setRotationPoint(0.0F, -11.0F, 0.0F);
		body.addChild(head);


		head2 = new AdvancedModelBox(this);
		head2.setRotationPoint(0.0F, -3.0F, 0.0F);
		head.addChild(head2);
		head2.setTextureOffset(0, 16).addBox(-6.0F, -4.0F, -4.0F, 12.0F, 8.0F, 8.0F, 0.0F, false);

		r_fin = new AdvancedModelBox(this);
		r_fin.setRotationPoint(-6.0F, 0.0F, 0.0F);
		head2.addChild(r_fin);
		r_fin.setTextureOffset(44, 44).addBox(-6.0F, -7.0F, 0.0F, 6.0F, 8.0F, 0.0F, 0.0F, false);

		l_fin = new AdvancedModelBox(this);
		l_fin.setRotationPoint(6.0F, 0.0F, 0.0F);
		head2.addChild(l_fin);
		l_fin.setTextureOffset(0, 47).addBox(0.0F, -7.0F, 0.0F, 6.0F, 8.0F, 0.0F, 0.0F, false);

		headwear = new AdvancedModelBox(this);
		headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(headwear);
		headwear.setTextureOffset(0, 0).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 8.0F, 8.0F, -0.5F, false);

		right_arm = new AdvancedModelBox(this);
		right_arm.setRotationPoint(-6.0F, -10.0F, 0.0F);
		body.addChild(right_arm);
		right_arm.setTextureOffset(36, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
		right_arm.setTextureOffset(12, 47).addBox(-4.0F, 1.0F, 0.0F, 3.0F, 12.0F, 0.0F, 0.0F, false);

		left_arm = new AdvancedModelBox(this);
		left_arm.setRotationPoint(6.0F, -10.0F, 0.0F);
		body.addChild(left_arm);
		left_arm.setTextureOffset(28, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
		left_arm.setTextureOffset(18, 47).addBox(1.0F, 1.0F, 0.0F, 3.0F, 12.0F, 0.0F, 0.0F, false);
		animator = ModelAnimator.create();
		this.updateDefaultPose();
	}

	@Override
	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(root);
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(root, left_leg,right_leg,body,headwear,head,head2,r_fin,l_fin,right_arm,left_arm);
	}

	public void animate(Deepling_Entity entity, float f, float f1, float f2, float f3, float f4) {
		this.resetToDefaultPose();
		animator.update(entity);
		animator.setAnimation(Deepling_Entity.TRIDENT_THROW);
		animator.startKeyframe(10);
		animator.rotate(left_arm,(float)Math.toRadians(-55F), (float)Math.toRadians(-10F), (float)Math.toRadians(-7.5F));
		animator.rotate(right_arm,(float)Math.toRadians(-200F), (float)Math.toRadians(15F), (float)Math.toRadians(-35f));
		animator.rotate(head,(float)Math.toRadians(-7.5F), (float)Math.toRadians(-10.5F), (float)Math.toRadians(-7.5F));
		animator.rotate(body,(float)Math.toRadians(-10F), (float)Math.toRadians(15F), (float)Math.toRadians(7.5F));
		animator.endKeyframe();

		animator.startKeyframe(2);
		animator.rotate(left_arm,(float)Math.toRadians(-7.5F), (float)Math.toRadians(-12.5F), (float)Math.toRadians(-22.5F));
		animator.rotate(right_arm,(float)Math.toRadians(-55.5F), (float)Math.toRadians(-5F), (float)Math.toRadians(-50f));
		animator.rotate(head,(float)Math.toRadians(5F), (float)Math.toRadians(-10F), (float)Math.toRadians(-5F));
		animator.rotate(body,(float)Math.toRadians(22.5F), (float)Math.toRadians(-30F), (float)Math.toRadians(7.5F));
		animator.endKeyframe();

		animator.resetKeyframe(8);
	}

	@Override
	public void setupAnim(Deepling_Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float walkSpeed = 0.75F;
		float walkDegree = 0.5F;
		this.faceTarget(netHeadYaw, headPitch, 1, head);
		this.walk(left_leg, walkSpeed, walkDegree * 1.2F, true, 0F, 0F, limbSwing, limbSwingAmount);
		this.walk(right_leg, walkSpeed, walkDegree * 1.2F, false, 0F, 0F, limbSwing, limbSwingAmount);
		this.walk(left_arm, walkSpeed, walkDegree * 1.2F, false, 0F, 0F, limbSwing, limbSwingAmount);
		this.walk(right_arm, walkSpeed, walkDegree * 1.2F, true, 0F, 0F, limbSwing, limbSwingAmount);

		this.walk(left_arm, 0.05f, walkDegree * 0.05F, false, 0F, 0F, ageInTicks, 1.0f);
		this.walk(right_arm, 0.05f, walkDegree * 0.05F, true, 0F, 0F, ageInTicks, 1.0f);

		this.flap(left_arm, 0.05f, walkDegree * 0.05F, false, 0F, 0, ageInTicks, 1.0f);
		this.flap(right_arm, 0.05f, walkDegree * 0.05F, true, 0F, 0F, ageInTicks, 1.0f);
	}

	public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
		AdvancedModelBox.rotateAngleX = x;
		AdvancedModelBox.rotateAngleY = y;
		AdvancedModelBox.rotateAngleZ = z;
	}
}
