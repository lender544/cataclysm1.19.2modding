package L_Ender.cataclysm.client.model.block;// Made with Blockbench 4.6.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports



import L_Ender.cataclysm.tileentities.TileEntityMechanical_infusion_Anvil;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class Model_Mechanical_Forge extends AdvancedEntityModel<Entity> {
	private final AdvancedModelBox root;

	public Model_Mechanical_Forge() {
		texWidth = 128;
		texHeight = 128;

		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		root.setTextureOffset(0, 19).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		root.setTextureOffset(0, 38).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
		root.setTextureOffset(49, 4).addBox(-8.0F, -15.0F, -8.0F, 1.0F, 11.0F, 15.0F, 0.0F, false);
		root.setTextureOffset(58, 38).addBox(-7.0F, -15.0F, -8.0F, 15.0F, 11.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(41, 39).addBox(7.0F, -15.0F, -7.0F, 1.0F, 11.0F, 15.0F, 0.0F, false);
		root.setTextureOffset(0, 54).addBox(-8.0F, -15.0F, 7.0F, 15.0F, 11.0F, 1.0F, 0.0F, false);
		root.setTextureOffset(0, 0).addBox(-8.0F, -12.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.3F, false);
		root.setTextureOffset(0, 66).addBox(-9.0F, -1.0F, -9.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
		root.setTextureOffset(47, 65).addBox(4.0F, -1.0F, -9.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
		root.setTextureOffset(27, 65).addBox(4.0F, -1.0F, 4.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
		root.setTextureOffset(64, 30).addBox(-9.0F, -1.0F, 4.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
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
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.resetToDefaultPose();
	}

	public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
		AdvancedModelBox.rotateAngleX = x;
		AdvancedModelBox.rotateAngleY = y;
		AdvancedModelBox.rotateAngleZ = z;
	}
}