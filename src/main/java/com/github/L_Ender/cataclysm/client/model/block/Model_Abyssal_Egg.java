package com.github.L_Ender.cataclysm.client.model.block;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class Model_Abyssal_Egg extends AdvancedEntityModel<Entity> {
	public final AdvancedModelBox root;
	public final AdvancedModelBox fetus;
	public Model_Abyssal_Egg() {
		texWidth = 128;
		texHeight = 128;

		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		root.setTextureOffset(0, 0).addBox(-7.0F, -17.25F, -7.0F, 14.0F, 17.0F, 14.0F, 0.0F, false);
		root.setTextureOffset(0, 32).addBox(-7.0F, -7.25F, -7.0F, 14.0F, 7.0F, 14.0F, 0.3F, false);

		fetus = new AdvancedModelBox(this);
		fetus.setRotationPoint(0.0F, -8.0F, 0.0F);
		root.addChild(fetus);
		fetus.setTextureOffset(43, 0).addBox(-6.0F, -6.0F, 0.0F, 12.0F, 12.0F, 0.0F, 0.0F, false);
		this.updateDefaultPose();
	}

	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(root);
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(root,fetus);
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