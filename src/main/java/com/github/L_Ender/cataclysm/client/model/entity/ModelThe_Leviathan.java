package com.github.L_Ender.cataclysm.client.model.entity;


import com.github.L_Ender.cataclysm.entity.The_Leviathan.The_Leviathan_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class ModelThe_Leviathan extends AdvancedEntityModel<The_Leviathan_Entity> {
	private final AdvancedModelBox root;
	private final AdvancedModelBox body;
	private final AdvancedModelBox R_Spike;
	private final AdvancedModelBox L_Spike;
	private final AdvancedModelBox Main_belly;
	private final AdvancedModelBox R_Spike2;
	private final AdvancedModelBox L_Spike2;
	private final AdvancedModelBox R_mini_fin;
	private final AdvancedModelBox L_mini_fin;
	private final AdvancedModelBox Belly;
	private final AdvancedModelBox R_down_fin;
	private final AdvancedModelBox R_down_fin2;
	private final AdvancedModelBox L_down_fin;
	private final AdvancedModelBox L_down_fin2;
	private final AdvancedModelBox Tail;
	private final AdvancedModelBox R_Spike3;
	private final AdvancedModelBox L_Spike3;
	private final AdvancedModelBox Tail2;
	private final AdvancedModelBox R_Spike4;
	private final AdvancedModelBox L_Spike4;
	private final AdvancedModelBox Tail3;
	private final AdvancedModelBox UpperR_Tantacle;
	private final AdvancedModelBox UpperR_Tantacle2;
	private final AdvancedModelBox UpperR_Tantacle3;
	private final AdvancedModelBox UpperR_Tantacle4;
	private final AdvancedModelBox UpperR_Hook;
	private final AdvancedModelBox UpperR_Hook2;
	private final AdvancedModelBox UpperR_Hook3;
	private final AdvancedModelBox UpperR_Hook4;
	private final AdvancedModelBox UpperL_Tantacle;
	private final AdvancedModelBox UpperL_Tantacle2;
	private final AdvancedModelBox UpperL_Tantacle3;
	private final AdvancedModelBox UpperL_Tantacle4;
	private final AdvancedModelBox UpperL_Hook;
	private final AdvancedModelBox UpperL_Hook2;
	private final AdvancedModelBox UpperL_Hook3;
	private final AdvancedModelBox UpperL_Hook4;
	private final AdvancedModelBox LowerR_Tantacle;
	private final AdvancedModelBox LowerR_Tantacle2;
	private final AdvancedModelBox LowerR_Tantacle3;
	private final AdvancedModelBox LowerR_Tantacle4;
	private final AdvancedModelBox LowerR_Hook;
	private final AdvancedModelBox LowerR_Hook2;
	private final AdvancedModelBox LowerR_Hook3;
	private final AdvancedModelBox LowerR_Hook4;
	private final AdvancedModelBox LowerL_Tantacle;
	private final AdvancedModelBox LowerL_Tantacle2;
	private final AdvancedModelBox LowerL_Tantacle3;
	private final AdvancedModelBox LowerL_Tantacle4;
	private final AdvancedModelBox LowerL_Hook;
	private final AdvancedModelBox LowerL_Hook2;
	private final AdvancedModelBox LowerL_Hook3;
	private final AdvancedModelBox LowerL_Hook4;
	private final AdvancedModelBox Head;
	private final AdvancedModelBox Muscle;
	private final AdvancedModelBox Maw;
	private final AdvancedModelBox Skul;
	private final AdvancedModelBox R_fin;
	private final AdvancedModelBox R_fin2;
	private final AdvancedModelBox R_fin3;
	private final AdvancedModelBox L_fin;
	private final AdvancedModelBox L_fin2;
	private final AdvancedModelBox L_fin3;
	private final AdvancedModelBox four_mouths;
	private final AdvancedModelBox Mouth4;
	private final AdvancedModelBox Mouth4_e;
	private final AdvancedModelBox Mouth3;
	private final AdvancedModelBox Mouth3_e;
	private final AdvancedModelBox Mouth2;
	private final AdvancedModelBox Mouth2_e;
	private final AdvancedModelBox Mouth;
	private final AdvancedModelBox Mouth1_e;
	private ModelAnimator animator;

	public ModelThe_Leviathan() {
		texWidth = 256;
		texHeight = 256;

		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		body = new AdvancedModelBox(this);
		body.setRotationPoint(0.0F, 0.0F, -22.0F);
		root.addChild(body);
		body.setTextureOffset(67, 50).addBox(-9.0F, -24.0F, -12.0F, 18.0F, 24.0F, 18.0F, 0.0F, false);
		body.setTextureOffset(85, 168).addBox(0.0F, -33.0F, -11.0F, 0.0F, 9.0F, 14.0F, 0.0F, false);

		R_Spike = new AdvancedModelBox(this);
		R_Spike.setRotationPoint(-9.0F, -24.0F, -3.0F);
		body.addChild(R_Spike);
		setRotationAngle(R_Spike, 0.0F, 0.0F, 0.7854F);
		R_Spike.setTextureOffset(140, 138).addBox(-4.0F, 0.0F, -9.0F, 4.0F, 0.0F, 18.0F, 0.0F, true);

		L_Spike = new AdvancedModelBox(this);
		L_Spike.setRotationPoint(9.0F, -24.0F, -3.0F);
		body.addChild(L_Spike);
		setRotationAngle(L_Spike, 0.0F, 0.0F, -0.7854F);
		L_Spike.setTextureOffset(140, 138).addBox(0.0F, 0.0F, -9.0F, 4.0F, 0.0F, 18.0F, 0.0F, false);

		Main_belly = new AdvancedModelBox(this);
		Main_belly.setRotationPoint(0.0F, -14.0F, 6.0F);
		body.addChild(Main_belly);
		Main_belly.setTextureOffset(43, 0).addBox(-8.0F, -9.0F, 0.0F, 16.0F, 19.0F, 16.0F, 0.0F, false);
		Main_belly.setTextureOffset(167, 129).addBox(0.0F, -18.0F, 3.0F, 0.0F, 9.0F, 14.0F, 0.0F, false);

		R_Spike2 = new AdvancedModelBox(this);
		R_Spike2.setRotationPoint(-8.0F, -9.0F, 8.0F);
		Main_belly.addChild(R_Spike2);
		setRotationAngle(R_Spike2, 0.0F, 0.0F, -0.7418F);
		R_Spike2.setTextureOffset(155, 29).addBox(0.0F, -4.0F, -8.0F, 0.0F, 4.0F, 16.0F, 0.0F, true);

		L_Spike2 = new AdvancedModelBox(this);
		L_Spike2.setRotationPoint(8.0F, -9.0F, 8.0F);
		Main_belly.addChild(L_Spike2);
		setRotationAngle(L_Spike2, 0.0F, 0.0F, 0.7418F);
		L_Spike2.setTextureOffset(155, 29).addBox(0.0F, -4.0F, -8.0F, 0.0F, 4.0F, 16.0F, 0.0F, false);

		R_mini_fin = new AdvancedModelBox(this);
		R_mini_fin.setRotationPoint(-8.0F, 10.0F, 13.0F);
		Main_belly.addChild(R_mini_fin);
		R_mini_fin.setTextureOffset(33, 137).addBox(-8.0F, 0.0F, -8.0F, 8.0F, 0.0F, 16.0F, 0.0F, true);

		L_mini_fin = new AdvancedModelBox(this);
		L_mini_fin.setRotationPoint(8.0F, 10.0F, 13.0F);
		Main_belly.addChild(L_mini_fin);
		L_mini_fin.setTextureOffset(33, 137).addBox(0.0F, 0.0F, -8.0F, 8.0F, 0.0F, 16.0F, 0.0F, false);

		Belly = new AdvancedModelBox(this);
		Belly.setRotationPoint(0.0F, 1.0F, 16.0F);
		Main_belly.addChild(Belly);
		Belly.setTextureOffset(0, 68).addBox(-7.0F, -9.0F, 0.0F, 14.0F, 17.0F, 14.0F, 0.0F, false);
		Belly.setTextureOffset(17, 171).addBox(0.0F, -21.0F, 2.0F, 0.0F, 12.0F, 12.0F, 0.0F, false);

		R_down_fin = new AdvancedModelBox(this);
		R_down_fin.setRotationPoint(-7.0F, 8.0F, 8.0F);
		Belly.addChild(R_down_fin);
		R_down_fin.setTextureOffset(122, 75).addBox(-6.0F, -1.0F, -6.0F, 6.0F, 1.0F, 18.0F, 0.0F, false);
		R_down_fin.setTextureOffset(90, 93).addBox(-6.0F, -1.0F, 12.0F, 6.0F, 0.0F, 4.0F, 0.0F, false);

		R_down_fin2 = new AdvancedModelBox(this);
		R_down_fin2.setRotationPoint(-6.0F, -1.0F, 6.0F);
		R_down_fin.addChild(R_down_fin2);
		R_down_fin2.setTextureOffset(168, 85).addBox(-6.0F, 0.0F, -6.0F, 6.0F, 1.0F, 12.0F, 0.0F, false);
		R_down_fin2.setTextureOffset(15, 159).addBox(-6.0F, 0.0F, 6.0F, 6.0F, 0.0F, 7.0F, 0.0F, false);

		L_down_fin = new AdvancedModelBox(this);
		L_down_fin.setRotationPoint(7.0F, 8.0F, 8.0F);
		Belly.addChild(L_down_fin);
		L_down_fin.setTextureOffset(108, 0).addBox(0.0F, -1.0F, -6.0F, 6.0F, 1.0F, 18.0F, 0.0F, false);
		L_down_fin.setTextureOffset(73, 36).addBox(0.0F, -1.0F, 12.0F, 6.0F, 0.0F, 4.0F, 0.0F, false);

		L_down_fin2 = new AdvancedModelBox(this);
		L_down_fin2.setRotationPoint(6.0F, -1.0F, 6.0F);
		L_down_fin.addChild(L_down_fin2);
		L_down_fin2.setTextureOffset(164, 157).addBox(0.0F, 0.0F, -6.0F, 6.0F, 1.0F, 12.0F, 0.0F, false);
		L_down_fin2.setTextureOffset(153, 85).addBox(0.0F, 0.0F, 6.0F, 6.0F, 0.0F, 7.0F, 0.0F, false);

		Tail = new AdvancedModelBox(this);
		Tail.setRotationPoint(0.0F, -1.0F, 14.0F);
		Belly.addChild(Tail);
		Tail.setTextureOffset(84, 98).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 16.0F, 14.0F, 0.0F, false);
		Tail.setTextureOffset(56, 160).addBox(0.0F, -17.0F, 0.0F, 0.0F, 9.0F, 14.0F, 0.0F, false);
		Tail.setTextureOffset(0, 159).addBox(0.0F, 8.0F, 0.0F, 0.0F, 9.0F, 14.0F, 0.0F, false);

		R_Spike3 = new AdvancedModelBox(this);
		R_Spike3.setRotationPoint(-4.0F, -3.0F, 6.5F);
		Tail.addChild(R_Spike3);
		setRotationAngle(R_Spike3, 0.0F, 0.0F, 0.4363F);
		R_Spike3.setTextureOffset(172, 0).addBox(-4.0F, 0.0F, -6.5F, 4.0F, 0.0F, 13.0F, 0.0F, true);

		L_Spike3 = new AdvancedModelBox(this);
		L_Spike3.setRotationPoint(4.0F, -3.0F, 6.5F);
		Tail.addChild(L_Spike3);
		setRotationAngle(L_Spike3, 0.0F, 0.0F, -0.4363F);
		L_Spike3.setTextureOffset(172, 0).addBox(0.0F, 0.0F, -6.5F, 4.0F, 0.0F, 13.0F, 0.0F, false);

		Tail2 = new AdvancedModelBox(this);
		Tail2.setRotationPoint(0.0F, -3.0F, 14.0F);
		Tail.addChild(Tail2);
		Tail2.setTextureOffset(149, 157).addBox(0.0F, 5.0F, 0.0F, 0.0F, 13.0F, 14.0F, 0.0F, false);
		Tail2.setTextureOffset(112, 149).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 10.0F, 14.0F, 0.0F, false);
		Tail2.setTextureOffset(66, 112).addBox(0.0F, -9.0F, 0.0F, 0.0F, 4.0F, 8.0F, 0.0F, false);

		R_Spike4 = new AdvancedModelBox(this);
		R_Spike4.setRotationPoint(-2.0F, 0.0F, 5.0F);
		Tail2.addChild(R_Spike4);
		setRotationAngle(R_Spike4, 0.0F, 0.0F, -0.4363F);
		R_Spike4.setTextureOffset(43, 184).addBox(-3.0F, 0.0F, -5.0F, 3.0F, 0.0F, 10.0F, 0.0F, false);

		L_Spike4 = new AdvancedModelBox(this);
		L_Spike4.setRotationPoint(2.0F, 0.0F, 5.0F);
		Tail2.addChild(L_Spike4);
		setRotationAngle(L_Spike4, 0.0F, 0.0F, 0.4363F);
		L_Spike4.setTextureOffset(183, 62).addBox(0.0F, 0.0F, -5.0F, 3.0F, 0.0F, 10.0F, 0.0F, false);

		Tail3 = new AdvancedModelBox(this);
		Tail3.setRotationPoint(0.0F, -2.5F, 14.0F);
		Tail2.addChild(Tail3);
		Tail3.setTextureOffset(33, 154).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 5.0F, 14.0F, 0.0F, false);
		Tail3.setTextureOffset(0, 0).addBox(0.0F, -7.5F, 0.0F, 0.0F, 25.0F, 42.0F, 0.0F, false);

		UpperR_Tantacle = new AdvancedModelBox(this);
		UpperR_Tantacle.setRotationPoint(-8.0F, -2.0F, 6.0F);
		Main_belly.addChild(UpperR_Tantacle);
		setRotationAngle(UpperR_Tantacle, 0.0F, -0.1745F, 0.6109F);
		UpperR_Tantacle.setTextureOffset(133, 20).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		UpperR_Tantacle2 = new AdvancedModelBox(this);
		UpperR_Tantacle2.setRotationPoint(-24.0F, 0.0F, 0.0F);
		UpperR_Tantacle.addChild(UpperR_Tantacle2);
		setRotationAngle(UpperR_Tantacle2, 0.0F, -0.4363F, 0.0F);
		UpperR_Tantacle2.setTextureOffset(129, 104).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		UpperR_Tantacle3 = new AdvancedModelBox(this);
		UpperR_Tantacle3.setRotationPoint(-24.0F, 0.0F, 0.0F);
		UpperR_Tantacle2.addChild(UpperR_Tantacle3);
		setRotationAngle(UpperR_Tantacle3, 0.0F, -0.4363F, 0.0F);
		UpperR_Tantacle3.setTextureOffset(129, 104).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		UpperR_Tantacle4 = new AdvancedModelBox(this);
		UpperR_Tantacle4.setRotationPoint(-24.0F, 0.0F, 0.0F);
		UpperR_Tantacle3.addChild(UpperR_Tantacle4);
		setRotationAngle(UpperR_Tantacle4, 0.0F, -0.6109F, 0.0F);
		UpperR_Tantacle4.setTextureOffset(115, 95).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		UpperR_Hook = new AdvancedModelBox(this);
		UpperR_Hook.setRotationPoint(-24.0F, 2.0F, 0.0F);
		UpperR_Tantacle4.addChild(UpperR_Hook);
		setRotationAngle(UpperR_Hook, 0.0F, 0.0F, 0.7854F);
		UpperR_Hook.setTextureOffset(99, 129).addBox(-2.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, false);

		UpperR_Hook2 = new AdvancedModelBox(this);
		UpperR_Hook2.setRotationPoint(-24.0F, 0.0F, -2.0F);
		UpperR_Tantacle4.addChild(UpperR_Hook2);
		setRotationAngle(UpperR_Hook2, 0.0F, 0.7854F, 0.0F);
		UpperR_Hook2.setTextureOffset(189, 156).addBox(-2.0F, -1.5F, -8.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);

		UpperR_Hook3 = new AdvancedModelBox(this);
		UpperR_Hook3.setRotationPoint(-24.0F, 0.0F, 2.0F);
		UpperR_Tantacle4.addChild(UpperR_Hook3);
		setRotationAngle(UpperR_Hook3, 0.0F, -0.7854F, 0.0F);
		UpperR_Hook3.setTextureOffset(106, 189).addBox(-2.0F, -1.5F, 0.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);

		UpperR_Hook4 = new AdvancedModelBox(this);
		UpperR_Hook4.setRotationPoint(-24.0F, -2.0F, 0.0F);
		UpperR_Tantacle4.addChild(UpperR_Hook4);
		setRotationAngle(UpperR_Hook4, 0.0F, 0.0F, -0.7854F);
		UpperR_Hook4.setTextureOffset(111, 0).addBox(-2.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, false);

		UpperL_Tantacle = new AdvancedModelBox(this);
		UpperL_Tantacle.setRotationPoint(8.0F, -2.0F, 6.0F);
		Main_belly.addChild(UpperL_Tantacle);
		setRotationAngle(UpperL_Tantacle, 0.0F, 0.1745F, -0.6109F);
		UpperL_Tantacle.setTextureOffset(133, 20).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		UpperL_Tantacle2 = new AdvancedModelBox(this);
		UpperL_Tantacle2.setRotationPoint(24.0F, 0.0F, 0.0F);
		UpperL_Tantacle.addChild(UpperL_Tantacle2);
		setRotationAngle(UpperL_Tantacle2, 0.0F, 0.4363F, 0.0F);
		UpperL_Tantacle2.setTextureOffset(129, 104).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		UpperL_Tantacle3 = new AdvancedModelBox(this);
		UpperL_Tantacle3.setRotationPoint(24.0F, 0.0F, 0.0F);
		UpperL_Tantacle2.addChild(UpperL_Tantacle3);
		setRotationAngle(UpperL_Tantacle3, 0.0F, 0.4363F, 0.0F);
		UpperL_Tantacle3.setTextureOffset(129, 104).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		UpperL_Tantacle4 = new AdvancedModelBox(this);
		UpperL_Tantacle4.setRotationPoint(24.0F, 0.0F, 0.0F);
		UpperL_Tantacle3.addChild(UpperL_Tantacle4);
		setRotationAngle(UpperL_Tantacle4, 0.0F, 0.6109F, 0.0F);
		UpperL_Tantacle4.setTextureOffset(115, 95).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		UpperL_Hook = new AdvancedModelBox(this);
		UpperL_Hook.setRotationPoint(24.0F, 2.0F, 0.0F);
		UpperL_Tantacle4.addChild(UpperL_Hook);
		setRotationAngle(UpperL_Hook, 0.0F, 0.0F, -0.7854F);
		UpperL_Hook.setTextureOffset(99, 129).addBox(0.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, true);

		UpperL_Hook2 = new AdvancedModelBox(this);
		UpperL_Hook2.setRotationPoint(24.0F, 0.0F, -2.0F);
		UpperL_Tantacle4.addChild(UpperL_Hook2);
		setRotationAngle(UpperL_Hook2, 0.0F, -0.7854F, 0.0F);
		UpperL_Hook2.setTextureOffset(189, 156).addBox(0.0F, -1.5F, -8.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		UpperL_Hook3 = new AdvancedModelBox(this);
		UpperL_Hook3.setRotationPoint(24.0F, 0.0F, 2.0F);
		UpperL_Tantacle4.addChild(UpperL_Hook3);
		setRotationAngle(UpperL_Hook3, 0.0F, 0.7854F, 0.0F);
		UpperL_Hook3.setTextureOffset(106, 189).addBox(0.0F, -1.5F, 0.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		UpperL_Hook4 = new AdvancedModelBox(this);
		UpperL_Hook4.setRotationPoint(24.0F, -2.0F, 0.0F);
		UpperL_Tantacle4.addChild(UpperL_Hook4);
		setRotationAngle(UpperL_Hook4, 0.0F, 0.0F, 0.7854F);
		UpperL_Hook4.setTextureOffset(111, 0).addBox(0.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, true);

		LowerR_Tantacle = new AdvancedModelBox(this);
		LowerR_Tantacle.setRotationPoint(-8.0F, 6.0F, 6.0F);
		Main_belly.addChild(LowerR_Tantacle);
		setRotationAngle(LowerR_Tantacle, 0.0F, -0.1745F, -0.6109F);
		LowerR_Tantacle.setTextureOffset(133, 20).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		LowerR_Tantacle2 = new AdvancedModelBox(this);
		LowerR_Tantacle2.setRotationPoint(-24.0F, 0.0F, 0.0F);
		LowerR_Tantacle.addChild(LowerR_Tantacle2);
		setRotationAngle(LowerR_Tantacle2, 0.0F, -0.4363F, 0.0F);
		LowerR_Tantacle2.setTextureOffset(129, 104).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		LowerR_Tantacle3 = new AdvancedModelBox(this);
		LowerR_Tantacle3.setRotationPoint(-24.0F, 0.0F, 0.0F);
		LowerR_Tantacle2.addChild(LowerR_Tantacle3);
		setRotationAngle(LowerR_Tantacle3, 0.0F, -0.48F, 0.0F);
		LowerR_Tantacle3.setTextureOffset(129, 104).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		LowerR_Tantacle4 = new AdvancedModelBox(this);
		LowerR_Tantacle4.setRotationPoint(-24.0F, 0.0F, 0.0F);
		LowerR_Tantacle3.addChild(LowerR_Tantacle4);
		setRotationAngle(LowerR_Tantacle4, 0.0F, -0.4363F, 0.0F);
		LowerR_Tantacle4.setTextureOffset(115, 95).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		LowerR_Hook = new AdvancedModelBox(this);
		LowerR_Hook.setRotationPoint(-24.0F, 2.0F, 0.0F);
		LowerR_Tantacle4.addChild(LowerR_Hook);
		setRotationAngle(LowerR_Hook, 0.0F, 0.0F, 0.7854F);
		LowerR_Hook.setTextureOffset(92, 0).addBox(-2.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, false);

		LowerR_Hook2 = new AdvancedModelBox(this);
		LowerR_Hook2.setRotationPoint(-24.0F, 0.0F, -2.0F);
		LowerR_Tantacle4.addChild(LowerR_Hook2);
		setRotationAngle(LowerR_Hook2, 0.0F, 0.7854F, 0.0F);
		LowerR_Hook2.setTextureOffset(187, 186).addBox(-2.0F, -1.5F, -8.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);

		LowerR_Hook3 = new AdvancedModelBox(this);
		LowerR_Hook3.setRotationPoint(-24.0F, 0.0F, 2.0F);
		LowerR_Tantacle4.addChild(LowerR_Hook3);
		setRotationAngle(LowerR_Hook3, 0.0F, -0.7854F, 0.0F);
		LowerR_Hook3.setTextureOffset(43, 68).addBox(-2.0F, -1.5F, 0.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);

		LowerR_Hook4 = new AdvancedModelBox(this);
		LowerR_Hook4.setRotationPoint(-24.0F, -2.0F, 0.0F);
		LowerR_Tantacle4.addChild(LowerR_Hook4);
		setRotationAngle(LowerR_Hook4, 0.0F, 0.0F, -0.7854F);
		LowerR_Hook4.setTextureOffset(0, 68).addBox(-2.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, false);

		LowerL_Tantacle = new AdvancedModelBox(this);
		LowerL_Tantacle.setRotationPoint(8.0F, 6.0F, 6.0F);
		Main_belly.addChild(LowerL_Tantacle);
		setRotationAngle(LowerL_Tantacle, 0.0F, 0.1745F, 0.6109F);
		LowerL_Tantacle.setTextureOffset(133, 20).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		LowerL_Tantacle2 = new AdvancedModelBox(this);
		LowerL_Tantacle2.setRotationPoint(24.0F, 0.0F, 0.0F);
		LowerL_Tantacle.addChild(LowerL_Tantacle2);
		setRotationAngle(LowerL_Tantacle2, 0.0F, 0.4363F, 0.0F);
		LowerL_Tantacle2.setTextureOffset(129, 104).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		LowerL_Tantacle3 = new AdvancedModelBox(this);
		LowerL_Tantacle3.setRotationPoint(24.0F, 0.0F, 0.0F);
		LowerL_Tantacle2.addChild(LowerL_Tantacle3);
		setRotationAngle(LowerL_Tantacle3, 0.0F, 0.48F, 0.0F);
		LowerL_Tantacle3.setTextureOffset(129, 104).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		LowerL_Tantacle4 = new AdvancedModelBox(this);
		LowerL_Tantacle4.setRotationPoint(24.0F, 0.0F, 0.0F);
		LowerL_Tantacle3.addChild(LowerL_Tantacle4);
		setRotationAngle(LowerL_Tantacle4, 0.0F, 0.4363F, 0.0F);
		LowerL_Tantacle4.setTextureOffset(115, 95).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		LowerL_Hook = new AdvancedModelBox(this);
		LowerL_Hook.setRotationPoint(24.0F, 2.0F, 0.0F);
		LowerL_Tantacle4.addChild(LowerL_Hook);
		setRotationAngle(LowerL_Hook, 0.0F, 0.0F, -0.7854F);
		LowerL_Hook.setTextureOffset(92, 0).addBox(0.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, true);

		LowerL_Hook2 = new AdvancedModelBox(this);
		LowerL_Hook2.setRotationPoint(24.0F, 0.0F, -2.0F);
		LowerL_Tantacle4.addChild(LowerL_Hook2);
		setRotationAngle(LowerL_Hook2, 0.0F, -0.7854F, 0.0F);
		LowerL_Hook2.setTextureOffset(187, 186).addBox(0.0F, -1.5F, -8.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		LowerL_Hook3 = new AdvancedModelBox(this);
		LowerL_Hook3.setRotationPoint(24.0F, 0.0F, 2.0F);
		LowerL_Tantacle4.addChild(LowerL_Hook3);
		setRotationAngle(LowerL_Hook3, 0.0F, 0.7854F, 0.0F);
		LowerL_Hook3.setTextureOffset(43, 68).addBox(0.0F, -1.5F, 0.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		LowerL_Hook4 = new AdvancedModelBox(this);
		LowerL_Hook4.setRotationPoint(24.0F, -2.0F, 0.0F);
		LowerL_Tantacle4.addChild(LowerL_Hook4);
		setRotationAngle(LowerL_Hook4, 0.0F, 0.0F, 0.7854F);
		LowerL_Hook4.setTextureOffset(0, 68).addBox(0.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, true);

		Head = new AdvancedModelBox(this);
		Head.setRotationPoint(0.0F, -11.0F, -12.0F);
		body.addChild(Head);


		Muscle = new AdvancedModelBox(this);
		Muscle.setRotationPoint(0.0F, 0.0F, -2.0F);
		Head.addChild(Muscle);
		Muscle.setTextureOffset(0, 125).addBox(4.5F, -6.0F, -3.0F, 0.0F, 11.0F, 4.0F, 0.0F, false);
		Muscle.setTextureOffset(0, 100).addBox(-4.5F, -6.0F, -3.0F, 0.0F, 11.0F, 4.0F, 0.0F, false);

		Maw = new AdvancedModelBox(this);
		Maw.setRotationPoint(0.0F, 2.0F, 0.25F);
		Head.addChild(Maw);
		Maw.setTextureOffset(171, 50).addBox(-5.0F, 0.0F, -8.25F, 10.0F, 3.0F, 8.0F, 0.0F, false);
		Maw.setTextureOffset(140, 64).addBox(5.0F, -3.0F, -8.25F, 0.0F, 3.0F, 6.0F, 0.0F, false);
		Maw.setTextureOffset(0, 21).addBox(3.0F, -3.0F, -12.25F, 0.0F, 3.0F, 4.0F, 0.0F, false);
		Maw.setTextureOffset(66, 137).addBox(-3.0F, -5.0F, -12.25F, 6.0F, 5.0F, 0.0F, 0.0F, false);
		Maw.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -12.25F, 0.0F, 3.0F, 4.0F, 0.0F, false);
		Maw.setTextureOffset(5, 21).addBox(3.0F, -3.0F, -8.25F, 2.0F, 3.0F, 0.0F, 0.0F, false);
		Maw.setTextureOffset(5, 0).addBox(-5.0F, -3.0F, -8.25F, 2.0F, 3.0F, 0.0F, 0.0F, false);
		Maw.setTextureOffset(139, 0).addBox(-5.0F, -3.0F, -8.25F, 0.0F, 3.0F, 6.0F, 0.0F, false);
		Maw.setTextureOffset(33, 100).addBox(-3.0F, 0.0F, -12.25F, 6.0F, 4.0F, 4.0F, 0.0F, false);

		Skul = new AdvancedModelBox(this);
		Skul.setRotationPoint(0.0F, -1.0F, 0.0F);
		Head.addChild(Skul);
		Skul.setTextureOffset(162, 186).addBox(-4.0F, -6.0F, -13.0F, 8.0F, 7.0F, 4.0F, 0.0F, false);
		Skul.setTextureOffset(146, 113).addBox(-6.0F, -6.0F, -9.0F, 12.0F, 6.0F, 9.0F, 0.0F, false);
		Skul.setTextureOffset(82, 93).addBox(-6.0F, 0.0F, -9.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
		Skul.setTextureOffset(35, 5).addBox(-6.0F, 0.0F, -9.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		Skul.setTextureOffset(35, 0).addBox(4.0F, 0.0F, -9.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		Skul.setTextureOffset(43, 0).addBox(6.0F, 0.0F, -9.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
		Skul.setTextureOffset(115, 104).addBox(-4.0F, 1.0F, -13.0F, 8.0F, 3.0F, 0.0F, 0.0F, false);
		Skul.setTextureOffset(30, 21).addBox(4.0F, 1.0F, -13.0F, 0.0F, 4.0F, 2.0F, 0.0F, false);
		Skul.setTextureOffset(30, 0).addBox(-4.0F, 1.0F, -13.0F, 0.0F, 4.0F, 2.0F, 0.0F, false);

		R_fin = new AdvancedModelBox(this);
		R_fin.setRotationPoint(-9.0F, -4.0F, -2.0F);
		body.addChild(R_fin);
		R_fin.setTextureOffset(41, 93).addBox(-12.0F, -1.0F, -8.0F, 12.0F, 2.0F, 16.0F, 0.0F, false);
		R_fin.setTextureOffset(133, 29).addBox(-12.0F, -1.0F, 8.0F, 12.0F, 0.0F, 5.0F, 0.0F, false);

		R_fin2 = new AdvancedModelBox(this);
		R_fin2.setRotationPoint(-12.0F, 0.0F, 1.0F);
		R_fin.addChild(R_fin2);
		R_fin2.setTextureOffset(66, 129).addBox(-8.0F, 0.0F, -9.0F, 8.0F, 1.0F, 16.0F, 0.0F, false);

		R_fin3 = new AdvancedModelBox(this);
		R_fin3.setRotationPoint(-8.0F, 0.0F, 2.0F);
		R_fin2.addChild(R_fin3);
		R_fin3.setTextureOffset(0, 143).addBox(-9.0F, 0.0F, -9.0F, 9.0F, 1.0F, 14.0F, 0.0F, false);
		R_fin3.setTextureOffset(105, 138).addBox(-11.0F, 0.0F, 5.0F, 16.0F, 0.0F, 10.0F, 0.0F, false);

		L_fin = new AdvancedModelBox(this);
		L_fin.setRotationPoint(9.0F, -4.0F, -2.0F);
		body.addChild(L_fin);
		L_fin.setTextureOffset(92, 20).addBox(0.0F, -1.0F, -8.0F, 12.0F, 2.0F, 16.0F, 0.0F, false);
		L_fin.setTextureOffset(43, 36).addBox(0.0F, -1.0F, 8.0F, 12.0F, 0.0F, 5.0F, 0.0F, false);

		L_fin2 = new AdvancedModelBox(this);
		L_fin2.setRotationPoint(12.0F, 0.0F, 1.0F);
		L_fin.addChild(L_fin2);
		L_fin2.setTextureOffset(0, 125).addBox(0.0F, 0.0F, -9.0F, 8.0F, 1.0F, 16.0F, 0.0F, false);

		L_fin3 = new AdvancedModelBox(this);
		L_fin3.setRotationPoint(8.0F, 0.0F, 2.0F);
		L_fin2.addChild(L_fin3);
		L_fin3.setTextureOffset(139, 0).addBox(0.0F, 0.0F, -9.0F, 9.0F, 1.0F, 14.0F, 0.0F, false);
		L_fin3.setTextureOffset(85, 39).addBox(-5.0F, 0.0F, 5.0F, 16.0F, 0.0F, 10.0F, 0.0F, false);

		four_mouths = new AdvancedModelBox(this);
		four_mouths.setRotationPoint(0.0F, -12.0F, -13.0F);
		body.addChild(four_mouths);


		Mouth4 = new AdvancedModelBox(this);
		Mouth4.setRotationPoint(6.0F, 6.0F, 2.0F);
		four_mouths.addChild(Mouth4);
		setRotationAngle(Mouth4, 0.0436F, 0.0F, 0.0F);
		Mouth4.setTextureOffset(122, 39).addBox(-4.0F, -4.0F, -16.0F, 8.0F, 8.0F, 16.0F, 0.0F, false);
		Mouth4.setTextureOffset(114, 174).addBox(-6.0F, 4.0F, -16.0F, 2.0F, 0.0F, 14.0F, 0.0F, false);
		Mouth4.setTextureOffset(182, 139).addBox(4.0F, -6.0F, -16.0F, 0.0F, 2.0F, 14.0F, 0.0F, false);

		Mouth4_e = new AdvancedModelBox(this);
		Mouth4_e.setRotationPoint(-1.0F, -1.0F, -15.5F);
		Mouth4.addChild(Mouth4_e);
		setRotationAngle(Mouth4_e, -0.0873F, 0.0F, 0.0F);
		Mouth4_e.setTextureOffset(153, 64).addBox(-5.0F, -5.0F, -11.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);

		Mouth3 = new AdvancedModelBox(this);
		Mouth3.setRotationPoint(-6.0F, 6.0F, 2.0F);
		four_mouths.addChild(Mouth3);
		setRotationAngle(Mouth3, 0.0436F, 0.0F, 0.0F);
		Mouth3.setTextureOffset(113, 113).addBox(-4.0F, -4.0F, -16.0F, 8.0F, 8.0F, 16.0F, 0.0F, false);
		Mouth3.setTextureOffset(182, 115).addBox(-4.0F, -6.0F, -16.0F, 0.0F, 2.0F, 14.0F, 0.0F, false);
		Mouth3.setTextureOffset(172, 29).addBox(4.0F, 4.0F, -16.0F, 2.0F, 0.0F, 14.0F, 0.0F, false);

		Mouth3_e = new AdvancedModelBox(this);
		Mouth3_e.setRotationPoint(1.0F, -1.0F, -15.5F);
		Mouth3.addChild(Mouth3_e);
		setRotationAngle(Mouth3_e, -0.0873F, 0.0F, 0.0F);
		Mouth3_e.setTextureOffset(71, 147).addBox(-4.0F, -5.0F, -11.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);

		Mouth2 = new AdvancedModelBox(this);
		Mouth2.setRotationPoint(6.0F, -6.0F, 2.0F);
		four_mouths.addChild(Mouth2);
		setRotationAngle(Mouth2, -0.0436F, 0.0F, 0.0F);
		Mouth2.setTextureOffset(33, 112).addBox(-4.0F, -4.0F, -16.0F, 8.0F, 8.0F, 16.0F, 0.0F, false);
		Mouth2.setTextureOffset(164, 171).addBox(-6.0F, -4.0F, -16.0F, 2.0F, 0.0F, 14.0F, 0.0F, false);
		Mouth2.setTextureOffset(28, 182).addBox(4.0F, 4.0F, -16.0F, 0.0F, 2.0F, 14.0F, 0.0F, false);

		Mouth2_e = new AdvancedModelBox(this);
		Mouth2_e.setRotationPoint(-1.0F, 1.0F, -15.5F);
		Mouth2.addChild(Mouth2_e);
		setRotationAngle(Mouth2_e, 0.0873F, 0.0F, 0.0F);
		Mouth2_e.setTextureOffset(0, 21).addBox(-5.0F, -4.0F, -11.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);

		Mouth = new AdvancedModelBox(this);
		Mouth.setRotationPoint(-6.0F, -6.0F, 2.0F);
		four_mouths.addChild(Mouth);
		setRotationAngle(Mouth, -0.0436F, 0.0F, 0.0F);
		Mouth.setTextureOffset(0, 100).addBox(-4.0F, -4.0F, -16.0F, 8.0F, 8.0F, 16.0F, 0.0F, false);
		Mouth.setTextureOffset(92, 0).addBox(4.0F, -4.0F, -16.0F, 2.0F, 0.0F, 14.0F, 0.0F, false);
		Mouth.setTextureOffset(133, 175).addBox(-4.0F, 4.0F, -16.0F, 0.0F, 2.0F, 14.0F, 0.0F, false);

		Mouth1_e = new AdvancedModelBox(this);
		Mouth1_e.setRotationPoint(1.0F, 1.0F, -15.5F);
		Mouth.addChild(Mouth1_e);
		setRotationAngle(Mouth1_e, 0.0873F, 0.0F, 0.0F);
		Mouth1_e.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -11.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);
		animator = ModelAnimator.create();
		this.updateDefaultPose();
	}

	private static double horizontalMag(Vec3 vec) {
		return vec.x * vec.x + vec.z * vec.z;
	}

	public void animate(The_Leviathan_Entity entity, float f, float f1, float f2, float f3, float f4) {
		this.resetToDefaultPose();
		animator.update(entity);
		animator.setAnimation(The_Leviathan_Entity.ANIMATION_GRAB);
		animator.startKeyframe(25);
		animator.rotate(Maw,(float)Math.toRadians(32.5F),0, 0);
		animator.rotate(Skul,(float)Math.toRadians(-35F),0, 0);
		animator.rotate(Mouth,(float)Math.toRadians(-37.5F),(float)Math.toRadians(42.5F), 0);
		animator.rotate(Mouth2,(float)Math.toRadians(-37.5F),(float)Math.toRadians(-42.5F), 0);
		animator.rotate(Mouth3,(float)Math.toRadians(37.5F),(float)Math.toRadians(42.5F), 0);
		animator.rotate(Mouth4,(float)Math.toRadians(37.5F),(float)Math.toRadians(-42.5F), 0);
		animator.endKeyframe();
		animator.setStaticKeyframe(70);

		animator.resetKeyframe(20);


		animator.setAnimation(The_Leviathan_Entity.ANIMATION_GRAB_BITE);
		animator.startKeyframe(0);
		animator.rotate(Maw,(float)Math.toRadians(32.5F),0, 0);
		animator.rotate(Skul,(float)Math.toRadians(-35F),0, 0);
		animator.rotate(Mouth,(float)Math.toRadians(-37.5F),(float)Math.toRadians(42.5F), 0);
		animator.rotate(Mouth2,(float)Math.toRadians(-37.5F),(float)Math.toRadians(-42.5F), 0);
		animator.rotate(Mouth3,(float)Math.toRadians(37.5F),(float)Math.toRadians(42.5F), 0);
		animator.rotate(Mouth4,(float)Math.toRadians(37.5F),(float)Math.toRadians(-42.5F), 0);
		animator.endKeyframe();


		animator.resetKeyframe(3);

		animator.setStaticKeyframe(10);

	}

	@Override
	public void setupAnim(The_Leviathan_Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{Tail, Tail2, Tail3};
		float swimSpeed = 0.1F;
		float swimDegree = 0.4F;
		float finspeed = 0.1F;
		float finDegree = 0.2F;
		this.chainSwing(tailBoxes, swimSpeed * 0.4F, swimDegree * 0.45F, -1, ageInTicks , 1.0f);
		this.chainSwing(tailBoxes, swimSpeed * 4F, swimDegree * 0.6F, -1, limbSwing,limbSwingAmount);

		this.flap(R_fin, finspeed * 0.8F, finDegree, false, 0F, -0.2F, ageInTicks , 1.0f);
		this.flap(R_fin2, finspeed * 0.8F, finDegree, false, 1F, -0.2F, ageInTicks , 1.0f);
		this.flap(L_fin, finspeed * 0.8F, finDegree, true, 0F, -0.2F, ageInTicks , 1.0f);
		this.flap(L_fin2, finspeed * 0.8F, finDegree, true, 1F, -0.2F, ageInTicks , 1.0f);

		this.flap(R_fin, finspeed * 4.0F, finDegree, false, 0F, -0.2F, limbSwing,limbSwingAmount);
		this.flap(R_fin2, finspeed * 4.0F, finDegree, false, 1F, -0.2F, limbSwing,limbSwingAmount);
		this.flap(L_fin, finspeed * 4.0F, finDegree, true, 0F, -0.2F, limbSwing,limbSwingAmount);
		this.flap(L_fin2, finspeed * 4.0F, finDegree, true, 1F, -0.2F, limbSwing,limbSwingAmount);

		this.flap(R_mini_fin, finspeed * 1.2F, finDegree * 2.5F, false, 0F, -0.5F, ageInTicks , 1.0f);
		this.flap(L_mini_fin, finspeed * 1.2F, finDegree * 2.5F, true, 0F, -0.5F, ageInTicks , 1.0f);

		this.flap(R_down_fin, finspeed * 0.8F, finDegree * 1.5F, false, 0F, -0.3F, ageInTicks , 1.0f);
		this.flap(L_down_fin, finspeed * 0.8F, finDegree * 1.5F, true, 0F, -0.3F, ageInTicks , 1.0f);

		this.flap(R_mini_fin, finspeed * 4.0F, finDegree * 2.5F, false, 0F, -0.5F, limbSwing,limbSwingAmount);
		this.flap(L_mini_fin, finspeed * 4.0F, finDegree * 2.5F, true, 0F, -0.5F,limbSwing,limbSwingAmount);

		this.flap(R_down_fin, finspeed * 4.0F, finDegree * 1.5F, false, 0F, -0.3F, limbSwing,limbSwingAmount);
		this.flap(L_down_fin, finspeed * 4.0F, finDegree * 1.5F, true, 0F, -0.3F, limbSwing,limbSwingAmount);


		this.walk(Mouth, finspeed * 0.8F, finDegree * 0.15F, false, 0F, -0.03F, ageInTicks , 1.0f);
		this.walk(Mouth2, finspeed * 0.8F, finDegree * 0.15F, false, 0F, -0.03F, ageInTicks , 1.0f);
		this.walk(Mouth3, finspeed * 0.8F, finDegree * 0.15F, true, 0F, -0.03F, ageInTicks , 1.0f);
		this.walk(Mouth4, finspeed * 0.8F, finDegree * 0.15F, true, 0F, -0.03F, ageInTicks , 1.0f);

		this.walk(Maw, finspeed * 1.1F, finDegree * 0.15F, true, 0F, -0.03F, limbSwing,limbSwingAmount);
		this.walk(Skul, finspeed * 1.1F, finDegree * 0.15F, false, 0F, -0.03F, limbSwing,limbSwingAmount);


		this.flap(L_down_fin, finspeed, finDegree * 1.5F, true, 0F, -0.3F, ageInTicks , 1.0f);

		this.body.rotateAngleX += headPitch * ((float) Math.PI / 180F);
		this.body.rotateAngleY += netHeadYaw * ((float) Math.PI / 180F);

	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(
				root,
				body,
				R_Spike,
				L_Spike,
				Main_belly,
				R_Spike2,
				L_Spike2,
				R_mini_fin,
				L_mini_fin,
				Belly,
				R_down_fin,
				R_down_fin2,
				L_down_fin,
				L_down_fin2,
				Tail,
				R_Spike3,
				L_Spike3,
				Tail2,
				R_Spike4,
				L_Spike4,
				Tail3,
				UpperL_Tantacle,
				UpperL_Tantacle2,
				UpperL_Tantacle3,
				UpperL_Tantacle4,
				UpperL_Hook,
				UpperL_Hook2,
				UpperL_Hook3,
				UpperL_Hook4,
				UpperR_Tantacle,
				UpperR_Tantacle2,
				UpperR_Tantacle3,
				UpperR_Tantacle4,
				UpperR_Hook,
				UpperR_Hook2,
				UpperR_Hook3,
				UpperR_Hook4,
				LowerL_Tantacle,
				LowerL_Tantacle2,
				LowerL_Tantacle3,
				LowerL_Tantacle4,
				LowerL_Hook,
				LowerL_Hook2,
				LowerL_Hook3,
				LowerL_Hook4,
				LowerR_Tantacle,
				LowerR_Tantacle2,
				LowerR_Tantacle3,
				LowerR_Tantacle4,
				LowerR_Hook,
				LowerR_Hook2,
				LowerR_Hook3,
				LowerR_Hook4,
				Head,
				Muscle,
				Maw,
				Skul,
				R_fin,
				R_fin2,
				R_fin3,
				L_fin,
				L_fin2,
				L_fin3,
				four_mouths,
				Mouth4,
				Mouth4_e,
				Mouth3,
				Mouth3_e,
				Mouth2,
				Mouth2_e,
				Mouth,
				Mouth1_e);
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