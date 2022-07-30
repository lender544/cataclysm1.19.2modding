package L_Ender.cataclysm.client.model.entity;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import L_Ender.cataclysm.entity.Ender_Golem_Entity;
import L_Ender.cataclysm.entity.Ignited_Revenant_Entity;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelIgnited_Revenant extends AdvancedEntityModel<Ignited_Revenant_Entity> {
	private final AdvancedModelBox root;
	private final AdvancedModelBox head;
	private final AdvancedModelBox helmet;
	private final AdvancedModelBox right_horn;
	private final AdvancedModelBox left_horn;
	private final AdvancedModelBox body;
	private final AdvancedModelBox guardingring;
	private final AdvancedModelBox shield;
	private final AdvancedModelBox right_parts;
	private final AdvancedModelBox left_parts;
	private final AdvancedModelBox spike_right;
	private final AdvancedModelBox spike_left;
	private final AdvancedModelBox shield3;
	private final AdvancedModelBox right_parts3;
	private final AdvancedModelBox left_parts3;
	private final AdvancedModelBox spike_right3;
	private final AdvancedModelBox spike_left3;
	private final AdvancedModelBox shield4;
	private final AdvancedModelBox right_parts4;
	private final AdvancedModelBox left_parts4;
	private final AdvancedModelBox spike_right4;
	private final AdvancedModelBox spike_left4;
	private final AdvancedModelBox shield2;
	private final AdvancedModelBox right_parts2;
	private final AdvancedModelBox left_parts2;
	private final AdvancedModelBox spike_right2;
	private final AdvancedModelBox spike_left2;
	private final AdvancedModelBox center;
	private ModelAnimator animator;

	public ModelIgnited_Revenant() {
		texWidth = 128;
		texHeight = 128;

		root = new AdvancedModelBox(this);
		root.setPos(0.0F, 24.0F, 0.0F);


		head = new AdvancedModelBox(this);
		head.setPos(0.0F, -30.0F, 0.0F);
		root.addChild(head);
		head.setTextureOffset(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		helmet = new AdvancedModelBox(this);
		helmet.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		helmet.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.3F, false);

		right_horn = new AdvancedModelBox(this);
		right_horn.setPos(-4.0F, -7.5F, -3.5F);
		helmet.addChild(right_horn);
		setRotationAngle(right_horn, 0.4363F, 0.0F, 0.0F);
		right_horn.setTextureOffset(0, 0).addBox(-1.3F, -5.5F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		left_horn = new AdvancedModelBox(this);
		left_horn.setPos(4.3F, -7.5F, -3.5F);
		helmet.addChild(left_horn);
		setRotationAngle(left_horn, 0.4363F, 0.0F, 0.0F);
		left_horn.setTextureOffset(4, 0).addBox(0.0F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		body = new AdvancedModelBox(this);
		body.setPos(0.0F, -6.0F, 0.0F);
		root.addChild(body);


		guardingring = new AdvancedModelBox(this);
		guardingring.setPos(0.0F, -15.0F, 0.0F);
		body.addChild(guardingring);


		shield = new AdvancedModelBox(this);
		shield.setPos(0.0F, 0.0F, 0.0F);
		guardingring.addChild(shield);
		setRotationAngle(shield, -0.2182F, -0.7854F, 0.0F);
		shield.setTextureOffset(32, 0).addBox(-3.5F, -5.0F, -14.0F, 7.0F, 21.0F, 1.0F, 0.0F, false);
		shield.setTextureOffset(48, 0).addBox(-4.0F, -7.0F, -14.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		shield.setTextureOffset(44, 44).addBox(-4.0F, 13.0F, -14.25F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		shield.setTextureOffset(16, 58).addBox(-3.0F, 0.0F, -14.5F, 6.0F, 12.0F, 0.0F, 0.0F, false);

		right_parts = new AdvancedModelBox(this);
		right_parts.setPos(-3.5F, 2.0F, -12.5F);
		shield.addChild(right_parts);
		setRotationAngle(right_parts, 0.0436F, 0.0436F, -0.0873F);
		right_parts.setTextureOffset(44, 22).addBox(-4.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.0F, false);
		right_parts.setTextureOffset(57, 22).addBox(-4.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.3F, false);
		right_parts.setTextureOffset(44, 51).addBox(-4.25F, -8.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);
		right_parts.setTextureOffset(44, 51).addBox(-4.25F, 11.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);

		left_parts = new AdvancedModelBox(this);
		left_parts.setPos(3.5F, 2.0F, -12.5F);
		shield.addChild(left_parts);
		setRotationAngle(left_parts, 0.0436F, -0.0436F, 0.0873F);
		left_parts.setTextureOffset(16, 32).addBox(-1.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.0F, false);
		left_parts.setTextureOffset(69, 22).addBox(-1.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.3F, false);
		left_parts.setTextureOffset(48, 13).addBox(-2.75F, -8.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);
		left_parts.setTextureOffset(48, 7).addBox(-2.75F, 11.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);

		spike_right = new AdvancedModelBox(this);
		spike_right.setPos(4.0F, 4.5F, -14.0F);
		shield.addChild(spike_right);
		setRotationAngle(spike_right, 0.0F, -0.3491F, 0.0F);
		spike_right.setTextureOffset(68, -2).addBox(0.0F, -12.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		spike_right.setTextureOffset(68, 4).addBox(0.0F, 8.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);

		spike_left = new AdvancedModelBox(this);
		spike_left.setPos(-4.0F, 4.5F, -14.0F);
		shield.addChild(spike_left);
		setRotationAngle(spike_left, 0.0F, 0.3491F, 0.0F);
		spike_left.setTextureOffset(68, -8).addBox(0.0F, -12.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		spike_left.setTextureOffset(68, 10).addBox(0.0F, 8.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);

		shield3 = new AdvancedModelBox(this);
		shield3.setPos(0.0F, 0.0F, 0.0F);
		guardingring.addChild(shield3);
		setRotationAngle(shield3, -0.2182F, -2.3562F, 0.0F);
		shield3.setTextureOffset(32, 0).addBox(-3.5F, -5.0F, -14.0F, 7.0F, 21.0F, 1.0F, 0.0F, false);
		shield3.setTextureOffset(48, 0).addBox(-4.0F, -7.0F, -14.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		shield3.setTextureOffset(44, 44).addBox(-4.0F, 13.0F, -14.25F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		shield3.setTextureOffset(16, 58).addBox(-3.0F, 0.0F, -14.5F, 6.0F, 12.0F, 0.0F, 0.0F, false);

		right_parts3 = new AdvancedModelBox(this);
		right_parts3.setPos(-3.5F, 2.0F, -12.5F);
		shield3.addChild(right_parts3);
		setRotationAngle(right_parts3, 0.0436F, 0.0436F, -0.0873F);
		right_parts3.setTextureOffset(44, 22).addBox(-4.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.0F, false);
		right_parts3.setTextureOffset(57, 22).addBox(-4.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.3F, false);
		right_parts3.setTextureOffset(44, 51).addBox(-4.25F, -8.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);
		right_parts3.setTextureOffset(44, 51).addBox(-4.25F, 11.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);

		left_parts3 = new AdvancedModelBox(this);
		left_parts3.setPos(3.5F, 2.0F, -12.5F);
		shield3.addChild(left_parts3);
		setRotationAngle(left_parts3, 0.0436F, -0.0436F, 0.0873F);
		left_parts3.setTextureOffset(16, 32).addBox(-1.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.0F, false);
		left_parts3.setTextureOffset(69, 22).addBox(-1.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.3F, false);
		left_parts3.setTextureOffset(48, 13).addBox(-2.75F, -8.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);
		left_parts3.setTextureOffset(48, 7).addBox(-2.75F, 11.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);

		spike_right3 = new AdvancedModelBox(this);
		spike_right3.setPos(4.0F, 4.5F, -14.0F);
		shield3.addChild(spike_right3);
		setRotationAngle(spike_right3, 0.0F, -0.3491F, 0.0F);
		spike_right3.setTextureOffset(68, -2).addBox(0.0F, -12.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		spike_right3.setTextureOffset(68, 4).addBox(0.0F, 8.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);

		spike_left3 = new AdvancedModelBox(this);
		spike_left3.setPos(-4.0F, 4.5F, -14.0F);
		shield3.addChild(spike_left3);
		setRotationAngle(spike_left3, 0.0F, 0.3491F, 0.0F);
		spike_left3.setTextureOffset(68, -8).addBox(0.0F, -12.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		spike_left3.setTextureOffset(68, 10).addBox(0.0F, 8.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);

		shield4 = new AdvancedModelBox(this);
		shield4.setPos(0.0F, 0.0F, 0.0F);
		guardingring.addChild(shield4);
		setRotationAngle(shield4, -0.2182F, 2.3562F, 0.0F);
		shield4.setTextureOffset(32, 0).addBox(-3.5F, -5.0F, -14.0F, 7.0F, 21.0F, 1.0F, 0.0F, false);
		shield4.setTextureOffset(48, 0).addBox(-4.0F, -7.0F, -14.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		shield4.setTextureOffset(44, 44).addBox(-4.0F, 13.0F, -14.25F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		shield4.setTextureOffset(16, 58).addBox(-3.0F, 0.0F, -14.5F, 6.0F, 12.0F, 0.0F, 0.0F, false);

		right_parts4 = new AdvancedModelBox(this);
		right_parts4.setPos(-3.5F, 2.0F, -12.5F);
		shield4.addChild(right_parts4);
		setRotationAngle(right_parts4, 0.0436F, 0.0436F, -0.0873F);
		right_parts4.setTextureOffset(44, 22).addBox(-4.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.0F, false);
		right_parts4.setTextureOffset(57, 22).addBox(-4.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.3F, false);
		right_parts4.setTextureOffset(44, 51).addBox(-4.25F, -8.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);
		right_parts4.setTextureOffset(44, 51).addBox(-4.25F, 11.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);

		left_parts4 = new AdvancedModelBox(this);
		left_parts4.setPos(3.5F, 2.0F, -12.5F);
		shield4.addChild(left_parts4);
		setRotationAngle(left_parts4, 0.0436F, -0.0436F, 0.0873F);
		left_parts4.setTextureOffset(16, 32).addBox(-1.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.0F, false);
		left_parts4.setTextureOffset(69, 22).addBox(-1.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.3F, false);
		left_parts4.setTextureOffset(48, 13).addBox(-2.75F, -8.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);
		left_parts4.setTextureOffset(48, 7).addBox(-2.75F, 11.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);

		spike_right4 = new AdvancedModelBox(this);
		spike_right4.setPos(4.0F, 4.5F, -14.0F);
		shield4.addChild(spike_right4);
		setRotationAngle(spike_right4, 0.0F, -0.3491F, 0.0F);
		spike_right4.setTextureOffset(68, -2).addBox(0.0F, -12.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		spike_right4.setTextureOffset(68, 4).addBox(0.0F, 8.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);

		spike_left4 = new AdvancedModelBox(this);
		spike_left4.setPos(-4.0F, 4.5F, -14.0F);
		shield4.addChild(spike_left4);
		setRotationAngle(spike_left4, 0.0F, 0.3491F, 0.0F);
		spike_left4.setTextureOffset(68, -8).addBox(0.0F, -12.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		spike_left4.setTextureOffset(68, 10).addBox(0.0F, 8.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);

		shield2 = new AdvancedModelBox(this);
		shield2.setPos(0.0F, 0.0F, 0.0F);
		guardingring.addChild(shield2);
		setRotationAngle(shield2, -0.2182F, 0.7854F, 0.0F);
		shield2.setTextureOffset(32, 0).addBox(-3.5F, -5.0F, -14.0F, 7.0F, 21.0F, 1.0F, 0.0F, false);
		shield2.setTextureOffset(48, 0).addBox(-4.0F, -7.0F, -14.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		shield2.setTextureOffset(44, 44).addBox(-4.0F, 13.0F, -14.25F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		shield2.setTextureOffset(16, 58).addBox(-3.0F, 0.0F, -14.5F, 6.0F, 12.0F, 0.0F, 0.0F, false);

		right_parts2 = new AdvancedModelBox(this);
		right_parts2.setPos(-3.5F, 2.0F, -12.5F);
		shield2.addChild(right_parts2);
		setRotationAngle(right_parts2, 0.0436F, 0.0436F, -0.0873F);
		right_parts2.setTextureOffset(44, 22).addBox(-4.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.0F, false);
		right_parts2.setTextureOffset(57, 22).addBox(-4.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.3F, false);
		right_parts2.setTextureOffset(44, 51).addBox(-4.25F, -8.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);
		right_parts2.setTextureOffset(44, 51).addBox(-4.25F, 11.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);

		left_parts2 = new AdvancedModelBox(this);
		left_parts2.setPos(3.5F, 2.0F, -12.5F);
		shield2.addChild(left_parts2);
		setRotationAngle(left_parts2, 0.0436F, -0.0436F, 0.0873F);
		left_parts2.setTextureOffset(16, 32).addBox(-1.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.0F, false);
		left_parts2.setTextureOffset(69, 22).addBox(-1.0F, -7.0F, -1.0F, 5.0F, 21.0F, 1.0F, 0.3F, false);
		left_parts2.setTextureOffset(48, 13).addBox(-2.75F, -8.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);
		left_parts2.setTextureOffset(48, 7).addBox(-2.75F, 11.5F, -1.5F, 7.0F, 4.0F, 2.0F, 0.0F, false);

		spike_right2 = new AdvancedModelBox(this);
		spike_right2.setPos(4.0F, 4.5F, -14.0F);
		shield2.addChild(spike_right2);
		setRotationAngle(spike_right2, 0.0F, -0.3491F, 0.0F);
		spike_right2.setTextureOffset(68, -2).addBox(0.0F, -12.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		spike_right2.setTextureOffset(68, 4).addBox(0.0F, 8.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);

		spike_left2 = new AdvancedModelBox(this);
		spike_left2.setPos(-4.0F, 4.5F, -14.0F);
		shield2.addChild(spike_left2);
		setRotationAngle(spike_left2, 0.0F, 0.3491F, 0.0F);
		spike_left2.setTextureOffset(68, -8).addBox(0.0F, -12.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		spike_left2.setTextureOffset(68, 10).addBox(0.0F, 8.5F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F, false);

		center = new AdvancedModelBox(this);
		center.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(center);
		center.setTextureOffset(0, 32).addBox(-2.0F, -22.0F, -2.0F, 4.0F, 22.0F, 4.0F, 0.0F, false);
		center.setTextureOffset(0, 58).addBox(-2.0F, -22.0F, -2.0F, 4.0F, 22.0F, 4.0F, 0.3F, false);
		animator = ModelAnimator.create();
		this.updateDefaultPose();
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
		this.resetToDefaultPose();
	}

	@Override
	public void setupAnim(Ignited_Revenant_Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float idleSpeed = 0.1F;
		float idleDegree = 1F;
		float walkSpeed = 0.5F;
		float walkDegree = 1F;
		this.bob(root, idleSpeed, idleDegree * 3, false, ageInTicks, 1);
		this.bob(shield, idleSpeed, idleDegree, false, ageInTicks, 1);
		this.bob(shield2, idleSpeed, idleDegree, false, ageInTicks, 1);
		this.bob(shield3, idleSpeed, idleDegree, false, ageInTicks, 1);
		this.bob(shield4, idleSpeed, idleDegree, false, ageInTicks, 1);
		guardingring.rotateAngleY += ageInTicks * 0.05F;
		this.shield.rotationPointY += Mth.cos(ageInTicks * 0.1F);
		this.shield4.rotationPointY += Mth.cos(ageInTicks * 0.1F);
		this.shield2.rotationPointY -= Mth.cos(ageInTicks * 0.1F);
		this.shield3.rotationPointY -= Mth.cos(ageInTicks * 0.1F);
		float partialTick = Minecraft.getInstance().getFrameTime();
		float deactivateProgress = entityIn.prevdeactivateProgress + (entityIn.deactivateProgress - entityIn.prevdeactivateProgress) * partialTick;


	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(
				root,
				head,
				helmet,
				body,
				guardingring,
				shield,
				shield2,
				shield3,
				shield4,
				center);
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