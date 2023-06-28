package com.github.L_Ender.cataclysm.client.model.block;// Made with Blockbench 4.4.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class Model_Altar_of_Amethyst extends AdvancedEntityModel<Entity> {
	private final AdvancedModelBox root;
	private final AdvancedModelBox crystal;


	public Model_Altar_of_Amethyst() {
		texWidth = 128;
		texHeight = 128;

		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		root.setTextureOffset(48, 16).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		root.setTextureOffset(0, 32).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		root.setTextureOffset(0, 51).addBox(-6.0F, -13.0F, -6.0F, 12.0F, 10.0F, 12.0F, 0.0F, false);
		root.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.3F, false);

		crystal = new AdvancedModelBox(this);
		crystal.setRotationPoint(-2.0F, 0.0F, 2.0F);
		root.addChild(crystal);
		crystal.setTextureOffset(0, 0).addBox(-7.0F, -24.0F, 3.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);
		crystal.setTextureOffset(0, 39).addBox(-8.0F, -19.0F, 2.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		crystal.setTextureOffset(0, 32).addBox(8.0F, -19.0F, -11.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		this.updateDefaultPose();
	}

	@Override
	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(root);
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(root, crystal);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.resetToDefaultPose();
	}

	public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
		AdvancedModelBox.rotateAngleX = x;
		AdvancedModelBox.rotateAngleY = y;
		AdvancedModelBox.rotateAngleZ = z;
	}
}