package com.github.L_Ender.cataclysm.client.model.entity;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.github.L_Ender.cataclysm.entity.projectile.Void_Rune_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public class ModelHarpoon extends AdvancedEntityModel<Entity> {
	private final AdvancedModelBox root;

	public ModelHarpoon() {
		texWidth = 32;
		texHeight = 32;

		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		root.setTextureOffset(0, 0).addBox(-0.5F, -18.0F, -0.5F, 1.0F, 18.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(10, 12).addBox(-0.5F, -16.0F, -1.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(5, 12).addBox(-0.5F, -16.0F, 0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(10, 4).addBox(-1.5F, -16.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(10, 8).addBox(0.5F, -16.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(10, 0).addBox(1.0F, -14.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(5, 8).addBox(-0.5F, -14.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(5, 4).addBox(-2.0F, -14.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(5, 0).addBox(-0.5F, -14.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		this.updateDefaultPose();
	}

	public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.resetToDefaultPose();

	}
	@Override
	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(root);
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(root);
	}
}