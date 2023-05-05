package com.github.L_Ender.cataclysm.client.model.entity;// Made with Blockbench 4.7.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.github.L_Ender.cataclysm.entity.The_Leviathan.Abyss_Blast_Portal_Entity;
import com.github.L_Ender.cataclysm.entity.The_Leviathan.Portal_Abyss_Blast_Entity;
import com.github.L_Ender.cataclysm.entity.The_Leviathan.The_Leviathan_Tongue_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class ModelAbyss_Blast_Portal extends AdvancedEntityModel<Abyss_Blast_Portal_Entity> {
	private final AdvancedModelBox root;
	private final AdvancedModelBox portal;

	public ModelAbyss_Blast_Portal() {
		texWidth = 128;
		texHeight = 128;

		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.setTextureOffset(0, 49).addBox(-6.0F, -25.0F, -2.0F, 9.0F, 7.0F, 4.0F, 0.0F, false);
		root.setTextureOffset(27, 49).addBox(-3.0F, -22.0F, -1.0F, 7.0F, 6.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(27, 49).addBox(-21.0F, -11.0F, -1.0F, 7.0F, 6.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(27, 49).addBox(-20.0F, 3.0F, -1.0F, 7.0F, 6.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(27, 49).addBox(5.0F, -23.0F, -1.0F, 7.0F, 6.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(0, 49).addBox(17.0F, 2.0F, -2.0F, 9.0F, 7.0F, 4.0F, 0.0F, false);
		root.setTextureOffset(0, 49).addBox(-24.0F, -5.0F, -2.0F, 9.0F, 7.0F, 4.0F, 0.0F, false);
		root.setTextureOffset(27, 49).addBox(15.0F, 6.0F, -1.0F, 7.0F, 6.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(46, 55).addBox(13.0F, -19.0F, -1.0F, 3.0F, 4.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(46, 55).addBox(16.0F, 13.0F, -1.0F, 3.0F, 4.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(46, 49).addBox(15.0F, -14.0F, -1.0F, 4.0F, 3.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(46, 49).addBox(-15.0F, -16.0F, -1.0F, 4.0F, 3.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(46, 49).addBox(-21.0F, 10.0F, -1.0F, 4.0F, 3.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(46, 49).addBox(-16.0F, 12.0F, -1.0F, 4.0F, 3.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(46, 49).addBox(20.0F, -2.0F, -1.0F, 4.0F, 3.0F, 2.0F, 0.0F, false);

		portal = new AdvancedModelBox(this);
		portal.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(portal);
		portal.setTextureOffset(0, 0).addBox(-24.0F, -24.0F, 0.0F, 48.0F, 48.0F, 0.0F, 0.0F, false);
		this.updateDefaultPose();
	}

	@Override
	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(root);
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(root, portal);
	}

	@Override
	public void setupAnim(Abyss_Blast_Portal_Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.resetToDefaultPose();
		this.root.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);

		this.root.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		this.portal.rotateAngleZ -= ageInTicks * 0.1f;
	}


	public void setRotationAngle(AdvancedModelBox modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}