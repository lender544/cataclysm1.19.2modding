package L_Ender.cataclysm.client.model.entity;// Made with Blockbench 4.5.1
// Exported for Minecraft version 1.14 with Mojang mappings
// Paste this class into your mod and generate all required imports


import L_Ender.cataclysm.entity.projectile.Laser_Beam_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class ModelLaser_Beam extends AdvancedEntityModel<Laser_Beam_Entity> {
	private final AdvancedModelBox root;;
	private final AdvancedModelBox one;
	private final AdvancedModelBox two;

	public ModelLaser_Beam() {
		texWidth = 64;
		texHeight = 64;


		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 21.5F, 0.0F);


		one = new AdvancedModelBox(this);
		one.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(one);
		setRotationAngle(one, 0.0F, 0.0F, -0.7854F);
		one.setTextureOffset(0, 0).addBox(0.0F, -1.5F, -6.5F, 0.0F, 3.0F, 13.0F, 0.0F, false);

		two = new AdvancedModelBox(this);
		two.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(two);
		setRotationAngle(two, 0.0F, 0.0F, 0.7854F);
		two.setTextureOffset(0, 0).addBox(0.0F, -1.5F, -6.5F, 0.0F, 3.0F, 13.0F, 0.0F, false);
	}


	public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
		AdvancedModelBox.rotateAngleX = x;
		AdvancedModelBox.rotateAngleY = y;
		AdvancedModelBox.rotateAngleZ = z;
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(root,one,two);
	}

	@Override
	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(root);
	}

	@Override
	public void setupAnim(Laser_Beam_Entity laser_beam_entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.root.rotateAngleX = headPitch * ((float)Math.PI / 180F);
	}

}