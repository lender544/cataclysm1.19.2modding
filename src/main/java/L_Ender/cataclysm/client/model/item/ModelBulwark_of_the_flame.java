package L_Ender.cataclysm.client.model.item;// Made with Blockbench 3.9.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Entity;

public class ModelBulwark_of_the_flame extends AdvancedEntityModel<Entity> {
	private final AdvancedModelBox shield;
	private final AdvancedModelBox cube_r1;
	private final AdvancedModelBox cube_r2;
	private final AdvancedModelBox cube_r3;
	private final AdvancedModelBox cube_r4;
	private final AdvancedModelBox cube_r5;
	private final AdvancedModelBox cube_r6;
	private final AdvancedModelBox cube_r7;
	private final AdvancedModelBox cube_r8;
	private final AdvancedModelBox cube_r9;
	private final AdvancedModelBox cube_r10;
	private final AdvancedModelBox cube_r11;
	private final AdvancedModelBox cube_r12;
	private final AdvancedModelBox cube_r13;
	private final AdvancedModelBox handle;
	private final AdvancedModelBox cube_r14;

	public ModelBulwark_of_the_flame() {
		texWidth = 64;
		texHeight = 64;

		shield = new AdvancedModelBox(this);
		shield.setRotationPoint(-2.0F, 21.0F, 0.0F);
		shield.setTextureOffset(0, 0).addBox(-1.0F, -13.0F, -6.0F, 1.0F, 22.0F, 12.0F, -0.1F, false);

		cube_r1 = new AdvancedModelBox(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.1668F, -0.3511F, 1.2889F);
		cube_r1.setTextureOffset(36, 23).addBox(-15.0F, -11.0F, -2.0F, 1.0F, 6.0F, 3.0F, 0.5F, false);

		cube_r2 = new AdvancedModelBox(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.1668F, 0.3511F, 1.2889F);
		cube_r2.setTextureOffset(36, 23).addBox(-15.0F, -11.0F, -1.0F, 1.0F, 6.0F, 3.0F, 0.5F, false);

		cube_r3 = new AdvancedModelBox(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 1.309F);
		cube_r3.setTextureOffset(41, 0).addBox(-16.0F, -11.0F, -2.0F, 1.0F, 6.0F, 4.0F, 0.5F, false);

		cube_r4 = new AdvancedModelBox(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.0873F, -0.1745F, 0.0F);
		cube_r4.setTextureOffset(10, 10).addBox(-1.0F, -13.0F, -8.0F, 1.0F, 22.0F, 3.0F, 0.0F, false);

		cube_r5 = new AdvancedModelBox(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0873F, 0.1745F, 0.0F);
		cube_r5.setTextureOffset(10, 10).addBox(-1.0F, -13.0F, 5.0F, 1.0F, 22.0F, 3.0F, 0.0F, false);

		cube_r6 = new AdvancedModelBox(this);
		cube_r6.setRotationPoint(0.0F, -1.0F, 0.0F);
		shield.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.0214F, -0.5006F, 0.6535F);
		cube_r6.setTextureOffset(26, 12).addBox(-10.0F, -12.0F, -3.0F, 1.0F, 4.0F, 4.0F, 0.5F, false);

		cube_r7 = new AdvancedModelBox(this);
		cube_r7.setRotationPoint(0.0F, -1.0F, 0.0F);
		shield.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0214F, 0.5006F, 0.6535F);
		cube_r7.setTextureOffset(26, 12).addBox(-10.0F, -12.0F, -1.0F, 1.0F, 4.0F, 4.0F, 0.5F, false);

		cube_r8 = new AdvancedModelBox(this);
		cube_r8.setRotationPoint(0.0F, -1.0F, 0.0F);
		shield.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.6109F);
		cube_r8.setTextureOffset(24, 3).addBox(-9.0F, -12.0F, -2.0F, 1.0F, 5.0F, 4.0F, 0.75F, false);

		cube_r9 = new AdvancedModelBox(this);
		cube_r9.setRotationPoint(9.0F, -8.0F, 8.0F);
		shield.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0757F, 0.1779F, -0.0883F);
		cube_r9.setTextureOffset(36, 16).addBox(-10.0F, 13.0F, -5.0F, 1.0F, 4.0F, 3.0F, 0.35F, false);

		cube_r10 = new AdvancedModelBox(this);
		cube_r10.setRotationPoint(9.0F, -8.0F, -8.0F);
		shield.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.0757F, -0.1779F, -0.0883F);
		cube_r10.setTextureOffset(36, 16).addBox(-10.0F, 13.0F, 2.0F, 1.0F, 4.0F, 3.0F, 0.35F, false);

		cube_r11 = new AdvancedModelBox(this);
		cube_r11.setRotationPoint(0.0F, -2.0F, 0.0F);
		shield.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.0436F);
		cube_r11.setTextureOffset(26, 28).addBox(-2.0F, 10.0F, -6.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);

		cube_r12 = new AdvancedModelBox(this);
		cube_r12.setRotationPoint(0.0F, -2.0F, 0.0F);
		shield.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, -0.0436F);
		cube_r12.setTextureOffset(32, 8).addBox(-2.0F, 10.0F, 2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);

		cube_r13 = new AdvancedModelBox(this);
		cube_r13.setRotationPoint(0.0F, -2.0F, 0.0F);
		shield.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.0F, -0.0436F);
		cube_r13.setTextureOffset(0, 0).addBox(-2.0F, 9.0F, -2.0F, 1.0F, 6.0F, 4.0F, 0.35F, false);

		handle = new AdvancedModelBox(this);
		handle.setRotationPoint(8.0F, -8.0F, -8.0F);
		shield.addChild(handle);
		handle.setTextureOffset(14, 0).addBox(-8.0F, 3.5F, 7.0F, 5.0F, 5.0F, 2.0F, 0.0F, false);

		cube_r14 = new AdvancedModelBox(this);
		cube_r14.setRotationPoint(0.0F, 0.0F, 16.0F);
		handle.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, -0.7854F);
		cube_r14.setTextureOffset(42, 10).addBox(-4.0F, -9.0F, -16.0F, 5.0F, 5.0F, 2.0F, 0.0F, false);
		cube_r14.setTextureOffset(42, 10).addBox(-4.0F, -9.0F, -2.0F, 5.0F, 5.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		shield.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
		AdvancedModelBox.rotateAngleX = x;
		AdvancedModelBox.rotateAngleY = y;
		AdvancedModelBox.rotateAngleZ = z;
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(
				shield,
				cube_r2,
				cube_r3,
				cube_r4,
				cube_r5,
				cube_r6,
				cube_r7,
				cube_r8,
				cube_r9,
				cube_r10,
				cube_r11,
				cube_r12,
				cube_r13,
				cube_r14,
				handle

		);
	}

	@Override
	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(shield);
	}
}