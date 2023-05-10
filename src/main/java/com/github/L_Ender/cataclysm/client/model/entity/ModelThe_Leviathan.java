package com.github.L_Ender.cataclysm.client.model.entity;


import com.github.L_Ender.cataclysm.entity.The_Leviathan.The_Leviathan_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
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
		body.setRotationPoint(0.0F, -13.0F, 0.0F);
		root.addChild(body);
		body.setTextureOffset(67, 50).addBox(-9.0F, -11.0F, -34.0F, 18.0F, 24.0F, 18.0F, 0.0F, false);
		body.setTextureOffset(44, 168).addBox(0.0F, -20.0F, -33.0F, 0.0F, 9.0F, 14.0F, 0.0F, false);

		R_Spike = new AdvancedModelBox(this);
		R_Spike.setRotationPoint(-9.0F, -11.0F, -25.0F);
		body.addChild(R_Spike);
		setRotationAngle(R_Spike, 0.0F, 0.0F, 0.7854F);
		R_Spike.setTextureOffset(0, 135).addBox(-4.0F, 0.0F, -9.0F, 4.0F, 0.0F, 18.0F, 0.0F, true);

		L_Spike = new AdvancedModelBox(this);
		L_Spike.setRotationPoint(9.0F, -11.0F, -25.0F);
		body.addChild(L_Spike);
		setRotationAngle(L_Spike, 0.0F, 0.0F, -0.7854F);
		L_Spike.setTextureOffset(0, 135).addBox(0.0F, 0.0F, -9.0F, 4.0F, 0.0F, 18.0F, 0.0F, false);

		Main_belly = new AdvancedModelBox(this);
		Main_belly.setRotationPoint(0.0F, -1.0F, -16.0F);
		body.addChild(Main_belly);
		Main_belly.setTextureOffset(43, 0).addBox(-8.0F, -9.0F, 0.0F, 16.0F, 19.0F, 16.0F, 0.0F, false);
		Main_belly.setTextureOffset(15, 168).addBox(0.0F, -18.0F, 3.0F, 0.0F, 9.0F, 14.0F, 0.0F, false);

		R_Spike2 = new AdvancedModelBox(this);
		R_Spike2.setRotationPoint(-8.0F, -9.0F, 8.0F);
		Main_belly.addChild(R_Spike2);
		setRotationAngle(R_Spike2, 0.0F, 0.0F, -0.7418F);
		R_Spike2.setTextureOffset(164, 91).addBox(0.0F, -4.0F, -8.0F, 0.0F, 4.0F, 16.0F, 0.0F, true);

		L_Spike2 = new AdvancedModelBox(this);
		L_Spike2.setRotationPoint(8.0F, -9.0F, 8.0F);
		Main_belly.addChild(L_Spike2);
		setRotationAngle(L_Spike2, 0.0F, 0.0F, 0.7418F);
		L_Spike2.setTextureOffset(164, 91).addBox(0.0F, -4.0F, -8.0F, 0.0F, 4.0F, 16.0F, 0.0F, false);

		R_mini_fin = new AdvancedModelBox(this);
		R_mini_fin.setRotationPoint(-8.0F, 10.0F, 13.0F);
		Main_belly.addChild(R_mini_fin);
		R_mini_fin.setTextureOffset(64, 137).addBox(-8.0F, 0.0F, -8.0F, 8.0F, 0.0F, 16.0F, 0.0F, true);

		L_mini_fin = new AdvancedModelBox(this);
		L_mini_fin.setRotationPoint(8.0F, 10.0F, 13.0F);
		Main_belly.addChild(L_mini_fin);
		L_mini_fin.setTextureOffset(64, 137).addBox(0.0F, 0.0F, -8.0F, 8.0F, 0.0F, 16.0F, 0.0F, false);

		Belly = new AdvancedModelBox(this);
		Belly.setRotationPoint(0.0F, 1.0F, 16.0F);
		Main_belly.addChild(Belly);
		Belly.setTextureOffset(0, 68).addBox(-7.0F, -9.0F, 0.0F, 14.0F, 17.0F, 14.0F, 0.0F, false);
		Belly.setTextureOffset(175, 118).addBox(0.0F, -21.0F, 2.0F, 0.0F, 12.0F, 12.0F, 0.0F, false);

		R_down_fin = new AdvancedModelBox(this);
		R_down_fin.setRotationPoint(-7.0F, 8.0F, 8.0F);
		Belly.addChild(R_down_fin);
		R_down_fin.setTextureOffset(31, 126).addBox(-6.0F, -1.0F, -6.0F, 6.0F, 1.0F, 18.0F, 0.0F, false);
		R_down_fin.setTextureOffset(133, 29).addBox(-6.0F, -1.0F, 12.0F, 6.0F, 0.0F, 4.0F, 0.0F, false);

		R_down_fin2 = new AdvancedModelBox(this);
		R_down_fin2.setRotationPoint(-6.0F, -1.0F, 6.0F);
		R_down_fin.addChild(R_down_fin2);
		R_down_fin2.setTextureOffset(155, 169).addBox(-6.0F, 0.0F, -6.0F, 6.0F, 1.0F, 12.0F, 0.0F, false);
		R_down_fin2.setTextureOffset(54, 154).addBox(-6.0F, 0.0F, 6.0F, 6.0F, 0.0F, 7.0F, 0.0F, false);

		L_down_fin = new AdvancedModelBox(this);
		L_down_fin.setRotationPoint(7.0F, 8.0F, 8.0F);
		Belly.addChild(L_down_fin);
		L_down_fin.setTextureOffset(108, 0).addBox(0.0F, -1.0F, -6.0F, 6.0F, 1.0F, 18.0F, 0.0F, false);
		L_down_fin.setTextureOffset(73, 36).addBox(0.0F, -1.0F, 12.0F, 6.0F, 0.0F, 4.0F, 0.0F, false);

		L_down_fin2 = new AdvancedModelBox(this);
		L_down_fin2.setRotationPoint(6.0F, -1.0F, 6.0F);
		L_down_fin.addChild(L_down_fin2);
		L_down_fin2.setTextureOffset(0, 27).addBox(0.0F, 0.0F, -6.0F, 6.0F, 1.0F, 12.0F, 0.0F, false);
		L_down_fin2.setTextureOffset(15, 154).addBox(0.0F, 0.0F, 6.0F, 6.0F, 0.0F, 7.0F, 0.0F, false);

		Tail = new AdvancedModelBox(this);
		Tail.setRotationPoint(0.0F, -0.9F, 14.0F);
		Belly.addChild(Tail);
		Tail.setTextureOffset(41, 93).addBox(-4.0F, -8.0F, -2.0F, 8.0F, 16.0F, 16.0F, 0.0F, false);
		Tail.setTextureOffset(126, 167).addBox(0.0F, -17.0F, 0.0F, 0.0F, 9.0F, 14.0F, 0.0F, false);
		Tail.setTextureOffset(97, 167).addBox(0.0F, 8.0F, 0.0F, 0.0F, 9.0F, 14.0F, 0.0F, false);

		R_Spike3 = new AdvancedModelBox(this);
		R_Spike3.setRotationPoint(-4.0F, -3.0F, 6.5F);
		Tail.addChild(R_Spike3);
		setRotationAngle(R_Spike3, 0.0F, 0.0F, 0.4363F);
		R_Spike3.setTextureOffset(176, 143).addBox(-4.0F, 0.0F, -6.5F, 4.0F, 0.0F, 13.0F, 0.0F, true);

		L_Spike3 = new AdvancedModelBox(this);
		L_Spike3.setRotationPoint(4.0F, -3.0F, 6.5F);
		Tail.addChild(L_Spike3);
		setRotationAngle(L_Spike3, 0.0F, 0.0F, -0.4363F);
		L_Spike3.setTextureOffset(176, 143).addBox(0.0F, 0.0F, -6.5F, 4.0F, 0.0F, 13.0F, 0.0F, false);

		Tail2 = new AdvancedModelBox(this);
		Tail2.setRotationPoint(0.0F, -2.9F, 14.0F);
		Tail.addChild(Tail2);
		Tail2.setTextureOffset(0, 154).addBox(0.0F, 4.9F, 0.0F, 0.0F, 13.0F, 14.0F, 0.0F, false);
		Tail2.setTextureOffset(0, 0).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 10.0F, 16.0F, 0.0F, false);
		Tail2.setTextureOffset(25, 0).addBox(0.0F, -9.0F, 0.0F, 0.0F, 4.0F, 8.0F, 0.0F, false);

		R_Spike4 = new AdvancedModelBox(this);
		R_Spike4.setRotationPoint(-2.0F, 0.0F, 5.0F);
		Tail2.addChild(R_Spike4);
		setRotationAngle(R_Spike4, 0.0F, 0.0F, -0.4363F);
		R_Spike4.setTextureOffset(30, 168).addBox(-3.0F, 0.0F, -5.0F, 3.0F, 0.0F, 10.0F, 0.0F, false);

		L_Spike4 = new AdvancedModelBox(this);
		L_Spike4.setRotationPoint(2.0F, 0.0F, 5.0F);
		Tail2.addChild(L_Spike4);
		setRotationAngle(L_Spike4, 0.0F, 0.0F, 0.4363F);
		L_Spike4.setTextureOffset(112, 167).addBox(0.0F, 0.0F, -5.0F, 3.0F, 0.0F, 10.0F, 0.0F, false);

		Tail3 = new AdvancedModelBox(this);
		Tail3.setRotationPoint(0.0F, -2.5F, 14.0F);
		Tail2.addChild(Tail3);
		Tail3.setTextureOffset(29, 146).addBox(-2.0F, -2.5F, -2.0F, 4.0F, 5.0F, 16.0F, -0.01F, false);
		Tail3.setTextureOffset(0, 0).addBox(0.0F, -7.6F, 0.0F, 0.0F, 25.0F, 42.0F, 0.0F, false);

		UpperR_Tantacle = new AdvancedModelBox(this);
		UpperR_Tantacle.setRotationPoint(-8.0F, -2.0F, 6.0F);
		Main_belly.addChild(UpperR_Tantacle);
		setRotationAngle(UpperR_Tantacle, 0.0F, -0.1745F, 0.6109F);
		UpperR_Tantacle.setTextureOffset(139, 0).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		UpperR_Tantacle2 = new AdvancedModelBox(this);
		UpperR_Tantacle2.setRotationPoint(-24.0F, 0.0F, 0.0F);
		UpperR_Tantacle.addChild(UpperR_Tantacle2);
		setRotationAngle(UpperR_Tantacle2, 0.0F, -0.4363F, 0.0F);
		UpperR_Tantacle2.setTextureOffset(133, 20).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		UpperR_Tantacle3 = new AdvancedModelBox(this);
		UpperR_Tantacle3.setRotationPoint(-24.0F, 0.0F, 0.0F);
		UpperR_Tantacle2.addChild(UpperR_Tantacle3);
		setRotationAngle(UpperR_Tantacle3, 0.0F, -0.4363F, 0.0F);
		UpperR_Tantacle3.setTextureOffset(133, 20).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		UpperR_Tantacle4 = new AdvancedModelBox(this);
		UpperR_Tantacle4.setRotationPoint(-24.0F, 0.0F, 0.0F);
		UpperR_Tantacle3.addChild(UpperR_Tantacle4);
		setRotationAngle(UpperR_Tantacle4, 0.0F, -0.6109F, 0.0F);
		UpperR_Tantacle4.setTextureOffset(0, 100).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		UpperR_Hook = new AdvancedModelBox(this);
		UpperR_Hook.setRotationPoint(-24.0F, 2.0F, 0.0F);
		UpperR_Tantacle4.addChild(UpperR_Hook);
		setRotationAngle(UpperR_Hook, 0.0F, 0.0F, 0.7854F);
		UpperR_Hook.setTextureOffset(111, 0).addBox(-2.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, false);

		UpperR_Hook2 = new AdvancedModelBox(this);
		UpperR_Hook2.setRotationPoint(-24.0F, 0.0F, -2.0F);
		UpperR_Tantacle4.addChild(UpperR_Hook2);
		setRotationAngle(UpperR_Hook2, 0.0F, 0.7854F, 0.0F);
		UpperR_Hook2.setTextureOffset(184, 157).addBox(-2.0F, -1.5F, -8.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);

		UpperR_Hook3 = new AdvancedModelBox(this);
		UpperR_Hook3.setRotationPoint(-24.0F, 0.0F, 2.0F);
		UpperR_Tantacle4.addChild(UpperR_Hook3);
		setRotationAngle(UpperR_Hook3, 0.0F, -0.7854F, 0.0F);
		UpperR_Hook3.setTextureOffset(156, 183).addBox(-2.0F, -1.5F, 0.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);

		UpperR_Hook4 = new AdvancedModelBox(this);
		UpperR_Hook4.setRotationPoint(-24.0F, -2.0F, 0.0F);
		UpperR_Tantacle4.addChild(UpperR_Hook4);
		setRotationAngle(UpperR_Hook4, 0.0F, 0.0F, -0.7854F);
		UpperR_Hook4.setTextureOffset(92, 0).addBox(-2.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, false);

		UpperL_Tantacle = new AdvancedModelBox(this);
		UpperL_Tantacle.setRotationPoint(8.0F, -2.0F, 6.0F);
		Main_belly.addChild(UpperL_Tantacle);
		setRotationAngle(UpperL_Tantacle, 0.0F, 0.1745F, -0.6109F);
		UpperL_Tantacle.setTextureOffset(139, 0).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		UpperL_Tantacle2 = new AdvancedModelBox(this);
		UpperL_Tantacle2.setRotationPoint(24.0F, 0.0F, 0.0F);
		UpperL_Tantacle.addChild(UpperL_Tantacle2);
		setRotationAngle(UpperL_Tantacle2, 0.0F, 0.4363F, 0.0F);
		UpperL_Tantacle2.setTextureOffset(133, 20).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		UpperL_Tantacle3 = new AdvancedModelBox(this);
		UpperL_Tantacle3.setRotationPoint(24.0F, 0.0F, 0.0F);
		UpperL_Tantacle2.addChild(UpperL_Tantacle3);
		setRotationAngle(UpperL_Tantacle3, 0.0F, 0.4363F, 0.0F);
		UpperL_Tantacle3.setTextureOffset(133, 20).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		UpperL_Tantacle4 = new AdvancedModelBox(this);
		UpperL_Tantacle4.setRotationPoint(24.0F, 0.0F, 0.0F);
		UpperL_Tantacle3.addChild(UpperL_Tantacle4);
		setRotationAngle(UpperL_Tantacle4, 0.0F, 0.6109F, 0.0F);
		UpperL_Tantacle4.setTextureOffset(0, 100).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		UpperL_Hook = new AdvancedModelBox(this);
		UpperL_Hook.setRotationPoint(24.0F, 2.0F, 0.0F);
		UpperL_Tantacle4.addChild(UpperL_Hook);
		setRotationAngle(UpperL_Hook, 0.0F, 0.0F, -0.7854F);
		UpperL_Hook.setTextureOffset(111, 0).addBox(0.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, true);

		UpperL_Hook2 = new AdvancedModelBox(this);
		UpperL_Hook2.setRotationPoint(24.0F, 0.0F, -2.0F);
		UpperL_Tantacle4.addChild(UpperL_Hook2);
		setRotationAngle(UpperL_Hook2, 0.0F, -0.7854F, 0.0F);
		UpperL_Hook2.setTextureOffset(184, 157).addBox(0.0F, -1.5F, -8.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		UpperL_Hook3 = new AdvancedModelBox(this);
		UpperL_Hook3.setRotationPoint(24.0F, 0.0F, 2.0F);
		UpperL_Tantacle4.addChild(UpperL_Hook3);
		setRotationAngle(UpperL_Hook3, 0.0F, 0.7854F, 0.0F);
		UpperL_Hook3.setTextureOffset(156, 183).addBox(0.0F, -1.5F, 0.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		UpperL_Hook4 = new AdvancedModelBox(this);
		UpperL_Hook4.setRotationPoint(24.0F, -2.0F, 0.0F);
		UpperL_Tantacle4.addChild(UpperL_Hook4);
		setRotationAngle(UpperL_Hook4, 0.0F, 0.0F, 0.7854F);
		UpperL_Hook4.setTextureOffset(92, 0).addBox(0.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, true);

		LowerR_Tantacle = new AdvancedModelBox(this);
		LowerR_Tantacle.setRotationPoint(-8.0F, 6.0F, 6.0F);
		Main_belly.addChild(LowerR_Tantacle);
		setRotationAngle(LowerR_Tantacle, 0.0F, -0.1745F, -0.6109F);
		LowerR_Tantacle.setTextureOffset(139, 0).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		LowerR_Tantacle2 = new AdvancedModelBox(this);
		LowerR_Tantacle2.setRotationPoint(-24.0F, 0.0F, 0.0F);
		LowerR_Tantacle.addChild(LowerR_Tantacle2);
		setRotationAngle(LowerR_Tantacle2, 0.0F, -0.4363F, 0.0F);
		LowerR_Tantacle2.setTextureOffset(133, 20).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		LowerR_Tantacle3 = new AdvancedModelBox(this);
		LowerR_Tantacle3.setRotationPoint(-24.0F, 0.0F, 0.0F);
		LowerR_Tantacle2.addChild(LowerR_Tantacle3);
		setRotationAngle(LowerR_Tantacle3, 0.0F, -0.48F, 0.0F);
		LowerR_Tantacle3.setTextureOffset(133, 20).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		LowerR_Tantacle4 = new AdvancedModelBox(this);
		LowerR_Tantacle4.setRotationPoint(-24.0F, 0.0F, 0.0F);
		LowerR_Tantacle3.addChild(LowerR_Tantacle4);
		setRotationAngle(LowerR_Tantacle4, 0.0F, -0.4363F, 0.0F);
		LowerR_Tantacle4.setTextureOffset(0, 100).addBox(-24.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, false);

		LowerR_Hook = new AdvancedModelBox(this);
		LowerR_Hook.setRotationPoint(-24.0F, 2.0F, 0.0F);
		LowerR_Tantacle4.addChild(LowerR_Hook);
		setRotationAngle(LowerR_Hook, 0.0F, 0.0F, 0.7854F);
		LowerR_Hook.setTextureOffset(0, 68).addBox(-2.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, false);

		LowerR_Hook2 = new AdvancedModelBox(this);
		LowerR_Hook2.setRotationPoint(-24.0F, 0.0F, -2.0F);
		LowerR_Tantacle4.addChild(LowerR_Hook2);
		setRotationAngle(LowerR_Hook2, 0.0F, 0.7854F, 0.0F);
		LowerR_Hook2.setTextureOffset(183, 62).addBox(-2.0F, -1.5F, -8.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);

		LowerR_Hook3 = new AdvancedModelBox(this);
		LowerR_Hook3.setRotationPoint(-24.0F, 0.0F, 2.0F);
		LowerR_Tantacle4.addChild(LowerR_Hook3);
		setRotationAngle(LowerR_Hook3, 0.0F, -0.7854F, 0.0F);
		LowerR_Hook3.setTextureOffset(43, 68).addBox(-2.0F, -1.5F, 0.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);

		LowerR_Hook4 = new AdvancedModelBox(this);
		LowerR_Hook4.setRotationPoint(-24.0F, -2.0F, 0.0F);
		LowerR_Tantacle4.addChild(LowerR_Hook4);
		setRotationAngle(LowerR_Hook4, 0.0F, 0.0F, -0.7854F);
		LowerR_Hook4.setTextureOffset(0, 27).addBox(-2.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, false);

		LowerL_Tantacle = new AdvancedModelBox(this);
		LowerL_Tantacle.setRotationPoint(8.0F, 6.0F, 6.0F);
		Main_belly.addChild(LowerL_Tantacle);
		setRotationAngle(LowerL_Tantacle, 0.0F, 0.1745F, 0.6109F);
		LowerL_Tantacle.setTextureOffset(139, 0).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		LowerL_Tantacle2 = new AdvancedModelBox(this);
		LowerL_Tantacle2.setRotationPoint(24.0F, 0.0F, 0.0F);
		LowerL_Tantacle.addChild(LowerL_Tantacle2);
		setRotationAngle(LowerL_Tantacle2, 0.0F, 0.4363F, 0.0F);
		LowerL_Tantacle2.setTextureOffset(133, 20).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		LowerL_Tantacle3 = new AdvancedModelBox(this);
		LowerL_Tantacle3.setRotationPoint(24.0F, 0.0F, 0.0F);
		LowerL_Tantacle2.addChild(LowerL_Tantacle3);
		setRotationAngle(LowerL_Tantacle3, 0.0F, 0.48F, 0.0F);
		LowerL_Tantacle3.setTextureOffset(133, 20).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		LowerL_Tantacle4 = new AdvancedModelBox(this);
		LowerL_Tantacle4.setRotationPoint(24.0F, 0.0F, 0.0F);
		LowerL_Tantacle3.addChild(LowerL_Tantacle4);
		setRotationAngle(LowerL_Tantacle4, 0.0F, 0.4363F, 0.0F);
		LowerL_Tantacle4.setTextureOffset(0, 100).addBox(0.0F, -2.0F, -2.0F, 24.0F, 4.0F, 4.0F, 0.0F, true);

		LowerL_Hook = new AdvancedModelBox(this);
		LowerL_Hook.setRotationPoint(24.0F, 2.0F, 0.0F);
		LowerL_Tantacle4.addChild(LowerL_Hook);
		setRotationAngle(LowerL_Hook, 0.0F, 0.0F, -0.7854F);
		LowerL_Hook.setTextureOffset(0, 68).addBox(0.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, true);

		LowerL_Hook2 = new AdvancedModelBox(this);
		LowerL_Hook2.setRotationPoint(24.0F, 0.0F, -2.0F);
		LowerL_Tantacle4.addChild(LowerL_Hook2);
		setRotationAngle(LowerL_Hook2, 0.0F, -0.7854F, 0.0F);
		LowerL_Hook2.setTextureOffset(183, 62).addBox(0.0F, -1.5F, -8.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		LowerL_Hook3 = new AdvancedModelBox(this);
		LowerL_Hook3.setRotationPoint(24.0F, 0.0F, 2.0F);
		LowerL_Tantacle4.addChild(LowerL_Hook3);
		setRotationAngle(LowerL_Hook3, 0.0F, 0.7854F, 0.0F);
		LowerL_Hook3.setTextureOffset(43, 68).addBox(0.0F, -1.5F, 0.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		LowerL_Hook4 = new AdvancedModelBox(this);
		LowerL_Hook4.setRotationPoint(24.0F, -2.0F, 0.0F);
		LowerL_Tantacle4.addChild(LowerL_Hook4);
		setRotationAngle(LowerL_Hook4, 0.0F, 0.0F, 0.7854F);
		LowerL_Hook4.setTextureOffset(0, 27).addBox(0.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F, true);

		Head = new AdvancedModelBox(this);
		Head.setRotationPoint(0.0F, 2.0F, -34.0F);
		body.addChild(Head);


		Muscle = new AdvancedModelBox(this);
		Muscle.setRotationPoint(0.0F, 0.0F, -2.0F);
		Head.addChild(Muscle);
		Muscle.setTextureOffset(43, 0).addBox(-4.5F, -6.0F, -3.0F, 0.0F, 11.0F, 4.0F, 0.0F, false);
		Muscle.setTextureOffset(43, 0).addBox(4.5F, -6.0F, -2.0F, 0.0F, 11.0F, 4.0F, 0.0F, true);

		Maw = new AdvancedModelBox(this);
		Maw.setRotationPoint(0.0F, 2.0F, 0.25F);
		Head.addChild(Maw);
		Maw.setTextureOffset(171, 50).addBox(-5.0F, 0.0F, -8.25F, 10.0F, 3.0F, 8.0F, 0.0F, false);
		Maw.setTextureOffset(131, 95).addBox(5.0F, -3.0F, -8.25F, 0.0F, 3.0F, 6.0F, 0.0F, false);
		Maw.setTextureOffset(57, 80).addBox(3.0F, -3.0F, -12.25F, 0.0F, 3.0F, 4.0F, 0.0F, false);
		Maw.setTextureOffset(107, 120).addBox(-3.0F, -5.0F, -12.25F, 6.0F, 5.0F, 0.0F, 0.0F, false);
		Maw.setTextureOffset(56, 68).addBox(-3.0F, -3.0F, -12.25F, 0.0F, 3.0F, 4.0F, 0.0F, false);
		Maw.setTextureOffset(25, 0).addBox(3.0F, -3.0F, -8.25F, 2.0F, 3.0F, 0.0F, 0.0F, false);
		Maw.setTextureOffset(10, 11).addBox(-5.0F, -3.0F, -8.25F, 2.0F, 3.0F, 0.0F, 0.0F, false);
		Maw.setTextureOffset(62, 131).addBox(-5.0F, -3.0F, -8.25F, 0.0F, 3.0F, 6.0F, 0.0F, false);
		Maw.setTextureOffset(27, 135).addBox(-3.0F, 0.0F, -12.25F, 6.0F, 4.0F, 4.0F, 0.0F, false);

		Skul = new AdvancedModelBox(this);
		Skul.setRotationPoint(0.0F, -1.0F, 0.0F);
		Head.addChild(Skul);
		Skul.setTextureOffset(141, 169).addBox(-4.0F, -6.0F, -13.0F, 8.0F, 7.0F, 4.0F, 0.0F, false);
		Skul.setTextureOffset(164, 75).addBox(-6.0F, -6.0F, -9.0F, 12.0F, 6.0F, 9.0F, 0.0F, false);
		Skul.setTextureOffset(25, 27).addBox(-6.0F, 0.0F, -9.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
		Skul.setTextureOffset(5, 11).addBox(-6.0F, 0.0F, -9.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		Skul.setTextureOffset(0, 11).addBox(4.0F, 0.0F, -9.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		Skul.setTextureOffset(0, 0).addBox(6.0F, 0.0F, -9.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
		Skul.setTextureOffset(122, 64).addBox(-4.0F, 1.0F, -13.0F, 8.0F, 3.0F, 0.0F, 0.0F, false);
		Skul.setTextureOffset(8, 0).addBox(4.0F, 1.0F, -13.0F, 0.0F, 4.0F, 2.0F, 0.0F, false);
		Skul.setTextureOffset(0, 0).addBox(-4.0F, 1.0F, -13.0F, 0.0F, 4.0F, 2.0F, 0.0F, false);

		R_fin = new AdvancedModelBox(this);
		R_fin.setRotationPoint(-9.0F, 9.0F, -24.0F);
		body.addChild(R_fin);
		R_fin.setTextureOffset(90, 93).addBox(-12.0F, -1.0F, -8.0F, 12.0F, 2.0F, 16.0F, 0.0F, false);
		R_fin.setTextureOffset(107, 114).addBox(-12.0F, -1.0F, 8.0F, 12.0F, 0.0F, 5.0F, 0.0F, false);

		R_fin2 = new AdvancedModelBox(this);
		R_fin2.setRotationPoint(-12.0F, 0.0F, 1.0F);
		R_fin.addChild(R_fin2);
		R_fin2.setTextureOffset(131, 96).addBox(-8.0F, 0.0F, -9.0F, 8.0F, 1.0F, 16.0F, 0.0F, false);

		R_fin3 = new AdvancedModelBox(this);
		R_fin3.setRotationPoint(-8.0F, 0.0F, 2.0F);
		R_fin2.addChild(R_fin3);
		R_fin3.setTextureOffset(142, 132).addBox(-9.0F, 0.0F, -9.0F, 9.0F, 1.0F, 14.0F, 0.0F, false);
		R_fin3.setTextureOffset(140, 64).addBox(-11.0F, 0.0F, 5.0F, 16.0F, 0.0F, 10.0F, 0.0F, false);

		L_fin = new AdvancedModelBox(this);
		L_fin.setRotationPoint(9.0F, 9.0F, -24.0F);
		body.addChild(L_fin);
		L_fin.setTextureOffset(92, 20).addBox(0.0F, -1.0F, -8.0F, 12.0F, 2.0F, 16.0F, 0.0F, false);
		L_fin.setTextureOffset(43, 36).addBox(0.0F, -1.0F, 8.0F, 12.0F, 0.0F, 5.0F, 0.0F, false);

		L_fin2 = new AdvancedModelBox(this);
		L_fin2.setRotationPoint(12.0F, 0.0F, 1.0F);
		L_fin.addChild(L_fin2);
		L_fin2.setTextureOffset(131, 77).addBox(0.0F, 0.0F, -9.0F, 8.0F, 1.0F, 16.0F, 0.0F, false);

		L_fin3 = new AdvancedModelBox(this);
		L_fin3.setRotationPoint(8.0F, 0.0F, 2.0F);
		L_fin2.addChild(L_fin3);
		L_fin3.setTextureOffset(140, 114).addBox(0.0F, 0.0F, -9.0F, 9.0F, 1.0F, 14.0F, 0.0F, false);
		L_fin3.setTextureOffset(85, 39).addBox(-5.0F, 0.0F, 5.0F, 16.0F, 0.0F, 10.0F, 0.0F, false);

		four_mouths = new AdvancedModelBox(this);
		four_mouths.setRotationPoint(0.0F, 1.0F, -35.0F);
		body.addChild(four_mouths);


		Mouth4 = new AdvancedModelBox(this);
		Mouth4.setRotationPoint(6.0F, 6.0F, 2.0F);
		four_mouths.addChild(Mouth4);
		setRotationAngle(Mouth4, 0.0436F, 0.0F, 0.0F);
		Mouth4.setTextureOffset(122, 39).addBox(-4.0F, -4.0F, -16.0F, 8.0F, 8.0F, 16.0F, 0.0F, false);
		Mouth4.setTextureOffset(181, 91).addBox(-6.0F, 4.0F, -16.0F, 2.0F, 0.0F, 14.0F, 0.0F, false);
		Mouth4.setTextureOffset(170, 184).addBox(4.0F, -6.0F, -16.0F, 0.0F, 2.0F, 14.0F, 0.0F, false);

		Mouth4_e = new AdvancedModelBox(this);
		Mouth4_e.setRotationPoint(-1.0F, -1.0F, -15.5F);
		Mouth4.addChild(Mouth4_e);
		setRotationAngle(Mouth4_e, -0.0873F, 0.0F, 0.0F);
		Mouth4_e.setTextureOffset(70, 156).addBox(-5.0F, -5.0F, -11.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);

		Mouth3 = new AdvancedModelBox(this);
		Mouth3.setRotationPoint(-6.0F, 6.0F, 2.0F);
		four_mouths.addChild(Mouth3);
		setRotationAngle(Mouth3, 0.0436F, 0.0F, 0.0F);
		Mouth3.setTextureOffset(107, 121).addBox(-4.0F, -4.0F, -16.0F, 8.0F, 8.0F, 16.0F, 0.0F, false);
		Mouth3.setTextureOffset(141, 183).addBox(-4.0F, -6.0F, -16.0F, 0.0F, 2.0F, 14.0F, 0.0F, false);
		Mouth3.setTextureOffset(178, 169).addBox(4.0F, 4.0F, -16.0F, 2.0F, 0.0F, 14.0F, 0.0F, false);

		Mouth3_e = new AdvancedModelBox(this);
		Mouth3_e.setRotationPoint(1.0F, -1.0F, -15.5F);
		Mouth3.addChild(Mouth3_e);
		setRotationAngle(Mouth3_e, -0.0873F, 0.0F, 0.0F);
		Mouth3_e.setTextureOffset(155, 29).addBox(-4.0F, -5.0F, -11.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);

		Mouth2 = new AdvancedModelBox(this);
		Mouth2.setRotationPoint(6.0F, -6.0F, 2.0F);
		four_mouths.addChild(Mouth2);
		setRotationAngle(Mouth2, -0.0436F, 0.0F, 0.0F);
		Mouth2.setTextureOffset(74, 112).addBox(-4.0F, -4.0F, -16.0F, 8.0F, 8.0F, 16.0F, 0.0F, false);
		Mouth2.setTextureOffset(73, 177).addBox(-6.0F, -4.0F, -16.0F, 2.0F, 0.0F, 14.0F, 0.0F, false);
		Mouth2.setTextureOffset(182, 0).addBox(4.0F, 4.0F, -16.0F, 0.0F, 2.0F, 14.0F, 0.0F, false);

		Mouth2_e = new AdvancedModelBox(this);
		Mouth2_e.setRotationPoint(-1.0F, 1.0F, -15.5F);
		Mouth2.addChild(Mouth2_e);
		setRotationAngle(Mouth2_e, 0.0873F, 0.0F, 0.0F);
		Mouth2_e.setTextureOffset(143, 148).addBox(-5.0F, -4.0F, -11.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);

		Mouth = new AdvancedModelBox(this);
		Mouth.setRotationPoint(-6.0F, -6.0F, 2.0F);
		four_mouths.addChild(Mouth);
		setRotationAngle(Mouth, -0.0436F, 0.0F, 0.0F);
		Mouth.setTextureOffset(0, 110).addBox(-4.0F, -4.0F, -16.0F, 8.0F, 8.0F, 16.0F, 0.0F, false);
		Mouth.setTextureOffset(92, 0).addBox(4.0F, -4.0F, -16.0F, 2.0F, 0.0F, 14.0F, 0.0F, false);
		Mouth.setTextureOffset(0, 182).addBox(-4.0F, 4.0F, -16.0F, 0.0F, 2.0F, 14.0F, 0.0F, false);

		Mouth1_e = new AdvancedModelBox(this);
		Mouth1_e.setRotationPoint(1.0F, 1.0F, -15.5F);
		Mouth.addChild(Mouth1_e);
		setRotationAngle(Mouth1_e, 0.0873F, 0.0F, 0.0F);
		Mouth1_e.setTextureOffset(102, 146).addBox(-4.0F, -4.0F, -11.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);
		animator = ModelAnimator.create();
		this.updateDefaultPose();
	}

	private static double horizontalMag(Vec3 vec) {
		return vec.x * vec.x + vec.z * vec.z;
	}

	public void animate(The_Leviathan_Entity entity, float f, float f1, float f2, float f3, float f4) {
		this.resetToDefaultPose();
		animator.update(entity);
		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_GRAB);
		animator.startKeyframe(25);
		animator.rotate(Maw,(float)Math.toRadians(32.5F),0, 0);
		animator.rotate(Skul,(float)Math.toRadians(-35F),0, 0);
		animator.rotate(Mouth,(float)Math.toRadians(-37.5F),(float)Math.toRadians(42.5F), 0);
		animator.rotate(Mouth2,(float)Math.toRadians(-37.5F),(float)Math.toRadians(-42.5F), 0);
		animator.rotate(Mouth3,(float)Math.toRadians(37.5F),(float)Math.toRadians(42.5F), 0);
		animator.rotate(Mouth4,(float)Math.toRadians(37.5F),(float)Math.toRadians(-42.5F), 0);
		animator.endKeyframe();
		animator.setStaticKeyframe(40);

		animator.resetKeyframe(15);


		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_GRAB_BITE);
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



		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_ABYSS_BLAST);
		animator.startKeyframe(20);
		animator.rotate(Maw,(float)Math.toRadians(20F),0, 0);
		animator.rotate(Skul,(float)Math.toRadians(-20F),0, 0);
		animator.rotate(Mouth,(float)Math.toRadians(-15F),(float)Math.toRadians(7.5F), 0);
		animator.rotate(Mouth2,(float)Math.toRadians(-15F),(float)Math.toRadians(-7.5F), 0);
		animator.rotate(Mouth3,(float)Math.toRadians(2.5F),(float)Math.toRadians(5F), 0);
		animator.rotate(Mouth4,(float)Math.toRadians(2.5F),(float)Math.toRadians(-5F), 0);
		animator.rotate(Tail,(float)Math.toRadians(15F),0, 0);
		animator.rotate(Tail2,(float)Math.toRadians(12.5F),0, 0);
		animator.rotate(Tail3,(float)Math.toRadians(12.5F),0, 0);
		animator.endKeyframe();

		animator.setStaticKeyframe(10);

		animator.startKeyframe(25);
		animator.rotate(Tail,(float)Math.toRadians(17.5F),0, 0);
		animator.rotate(Tail2,(float)Math.toRadians(15F),0, 0);
		animator.rotate(Tail3,(float)Math.toRadians(15F),0, 0);
		animator.endKeyframe();

		animator.setStaticKeyframe(26);

		animator.startKeyframe(3);
		animator.rotate(Maw,(float)Math.toRadians(37.5F),0, 0);
		animator.rotate(Skul,(float)Math.toRadians(-40F),0, 0);
		animator.rotate(Mouth,(float)Math.toRadians(-37.5F),(float)Math.toRadians(40F), 0);
		animator.rotate(Mouth2,(float)Math.toRadians(-37.5F),(float)Math.toRadians(-40F), 0);
		animator.rotate(Mouth3,(float)Math.toRadians(37.5F),(float)Math.toRadians(40F), 0);
		animator.rotate(Mouth4,(float)Math.toRadians(37.5F),(float)Math.toRadians(-40F), 0);
		animator.rotate(Tail,(float)Math.toRadians(25F),0, (float)Math.toRadians(5F));
		animator.rotate(Tail2,(float)Math.toRadians(27.5F),0, (float)Math.toRadians(-2.5F));
		animator.rotate(Tail3,(float)Math.toRadians(50F),0, (float)Math.toRadians(-2.5F));
		animator.endKeyframe();

		animator.setStaticKeyframe(80);

		animator.resetKeyframe(20);

		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_RUSH);
		animator.startKeyframe(25);
		animator.rotate(UpperR_Tantacle,0,(float)Math.toRadians(20F), 0);
		animator.rotate(UpperR_Tantacle2,0,(float)Math.toRadians(50F), 0);
		animator.rotate(UpperR_Tantacle3,0,(float)Math.toRadians(50F), 0);
		animator.rotate(UpperR_Tantacle4,0,(float)Math.toRadians(70F), 0);

		animator.rotate(UpperL_Tantacle,0,(float)Math.toRadians(-20F), 0);
		animator.rotate(UpperL_Tantacle2,0,(float)Math.toRadians(-50F), 0);
		animator.rotate(UpperL_Tantacle3,0,(float)Math.toRadians(-50F), 0);
		animator.rotate(UpperL_Tantacle4,0,(float)Math.toRadians(-70F), 0);

		animator.rotate(LowerR_Tantacle,0,(float)Math.toRadians(20F), 0);
		animator.rotate(LowerR_Tantacle2,0,(float)Math.toRadians(50F), 0);
		animator.rotate(LowerR_Tantacle3,0,(float)Math.toRadians(52.5F), 0);
		animator.rotate(LowerR_Tantacle4,0,(float)Math.toRadians(55F), 0);

		animator.rotate(LowerL_Tantacle,0,(float)Math.toRadians(-20F), 0);
		animator.rotate(LowerL_Tantacle2,0,(float)Math.toRadians(-50F), 0);
		animator.rotate(LowerL_Tantacle3,0,(float)Math.toRadians(-52.5F), 0);
		animator.rotate(LowerL_Tantacle4,0,(float)Math.toRadians(-55F), 0);

		animator.rotate(Mouth,(float)Math.toRadians(-2.5F),0,0);
		animator.rotate(Mouth2,(float)Math.toRadians(-2.5F),0,0);
		animator.rotate(Mouth3,(float)Math.toRadians(2.5F),0,0);
		animator.rotate(Mouth4,(float)Math.toRadians(2.5F),0,0);

		animator.endKeyframe();

		animator.setStaticKeyframe(10);

		animator.startKeyframe(3);
		animator.rotate(UpperR_Tantacle,0,(float)Math.toRadians(37.5F), 0);
		animator.rotate(UpperR_Tantacle2,0,(float)Math.toRadians(45F), 0);
		animator.rotate(UpperR_Tantacle3,0,(float)Math.toRadians(50F), 0);
		animator.rotate(UpperR_Tantacle4,0,(float)Math.toRadians(40F), 0);
		animator.rotate(UpperR_Hook,0,0, (float)Math.toRadians(25F));
		animator.rotate(UpperR_Hook2,0,(float)Math.toRadians(25F), 0);
		animator.rotate(UpperR_Hook3,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(UpperR_Hook4,0,0, (float)Math.toRadians(-25F));

		animator.rotate(UpperL_Tantacle,0,(float)Math.toRadians(-37.5F), 0);
		animator.rotate(UpperL_Tantacle2,0,(float)Math.toRadians(-45F), 0);
		animator.rotate(UpperL_Tantacle3,0,(float)Math.toRadians(-50F), 0);
		animator.rotate(UpperL_Tantacle4,0,(float)Math.toRadians(-40F), 0);
		animator.rotate(UpperL_Hook,0,0, (float)Math.toRadians(-25F));
		animator.rotate(UpperL_Hook2,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(UpperL_Hook3,0,(float)Math.toRadians(25F), 0);
		animator.rotate(UpperL_Hook4,0,0, (float)Math.toRadians(25F));

		animator.rotate(LowerR_Tantacle,0,(float)Math.toRadians(37.5F), 0);
		animator.rotate(LowerR_Tantacle2,0,(float)Math.toRadians(45F), 0);
		animator.rotate(LowerR_Tantacle3,0,(float)Math.toRadians(52.5F), 0);
		animator.rotate(LowerR_Tantacle4,0,(float)Math.toRadians(30F), 0);
		animator.rotate(LowerR_Hook,0,0, (float)Math.toRadians(25F));
		animator.rotate(LowerR_Hook2,0,(float)Math.toRadians(25F), 0);
		animator.rotate(LowerR_Hook3,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(LowerR_Hook4,0,0, (float)Math.toRadians(-25F));

		animator.rotate(LowerL_Tantacle,0,(float)Math.toRadians(-37.5F), 0);
		animator.rotate(LowerL_Tantacle2,0,(float)Math.toRadians(-45F), 0);
		animator.rotate(LowerL_Tantacle3,0,(float)Math.toRadians(-52.5F), 0);
		animator.rotate(LowerL_Tantacle4,0,(float)Math.toRadians(-30F), 0);
		animator.rotate(LowerL_Hook,0,0, (float)Math.toRadians(-25F));
		animator.rotate(LowerL_Hook2,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(LowerL_Hook3,0,(float)Math.toRadians(25F), 0);
		animator.rotate(LowerL_Hook4,0,0, (float)Math.toRadians(25F));

		animator.rotate(Maw,(float)Math.toRadians(15F),0, 0);
		animator.rotate(Skul,(float)Math.toRadians(-30F),0, 0);
		animator.rotate(Mouth,(float)Math.toRadians(-27.5F),(float)Math.toRadians(2.5F), 0);
		animator.rotate(Mouth2,(float)Math.toRadians(-27.5F),(float)Math.toRadians(-2.5F), 0);
		animator.rotate(Mouth3,(float)Math.toRadians(10F),(float)Math.toRadians(2.5F), 0);
		animator.rotate(Mouth4,(float)Math.toRadians(10F),(float)Math.toRadians(-2.5F), 0);

		animator.endKeyframe();

		animator.setStaticKeyframe(16);

		animator.startKeyframe(3);
		animator.rotate(UpperR_Tantacle,0,(float)Math.toRadians(55F), 0);
		animator.rotate(UpperR_Tantacle2,0,(float)Math.toRadians(45F), 0);
		animator.rotate(UpperR_Tantacle3,0,(float)Math.toRadians(50F), 0);
		animator.rotate(UpperR_Tantacle4,0,(float)Math.toRadians(40F), 0);
		animator.rotate(UpperR_Hook,0,0, (float)Math.toRadians(25F));
		animator.rotate(UpperR_Hook2,0,(float)Math.toRadians(25F), 0);
		animator.rotate(UpperR_Hook3,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(UpperR_Hook4,0,0, (float)Math.toRadians(-25F));

		animator.rotate(UpperL_Tantacle,0,(float)Math.toRadians(-55F), 0);
		animator.rotate(UpperL_Tantacle2,0,(float)Math.toRadians(-45F), 0);
		animator.rotate(UpperL_Tantacle3,0,(float)Math.toRadians(-50F), 0);
		animator.rotate(UpperL_Tantacle4,0,(float)Math.toRadians(-40F), 0);
		animator.rotate(UpperL_Hook,0,0, (float)Math.toRadians(-25F));
		animator.rotate(UpperL_Hook2,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(UpperL_Hook3,0,(float)Math.toRadians(25F), 0);
		animator.rotate(UpperL_Hook4,0,0, (float)Math.toRadians(25F));

		animator.rotate(LowerR_Tantacle,0,(float)Math.toRadians(55F), 0);
		animator.rotate(LowerR_Tantacle2,0,(float)Math.toRadians(45F), 0);
		animator.rotate(LowerR_Tantacle3,0,(float)Math.toRadians(52.5F), 0);
		animator.rotate(LowerR_Tantacle4,0,(float)Math.toRadians(30F), 0);
		animator.rotate(LowerR_Hook,0,0, (float)Math.toRadians(25F));
		animator.rotate(LowerR_Hook2,0,(float)Math.toRadians(25F), 0);
		animator.rotate(LowerR_Hook3,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(LowerR_Hook4,0,0, (float)Math.toRadians(-25F));

		animator.rotate(LowerL_Tantacle,0,(float)Math.toRadians(-55F), 0);
		animator.rotate(LowerL_Tantacle2,0,(float)Math.toRadians(-45F), 0);
		animator.rotate(LowerL_Tantacle3,0,(float)Math.toRadians(-52.5F), 0);
		animator.rotate(LowerL_Tantacle4,0,(float)Math.toRadians(-30F), 0);
		animator.rotate(LowerL_Hook,0,0, (float)Math.toRadians(-25F));
		animator.rotate(LowerL_Hook2,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(LowerL_Hook3,0,(float)Math.toRadians(25F), 0);
		animator.rotate(LowerL_Hook4,0,0, (float)Math.toRadians(25F));

		animator.rotate(Maw,(float)Math.toRadians(20F),0, 0);
		animator.rotate(Skul,(float)Math.toRadians(-35F),0, 0);
		animator.rotate(Mouth,(float)Math.toRadians(-37.5F),(float)Math.toRadians(7.5F), 0);
		animator.rotate(Mouth2,(float)Math.toRadians(-37.5F),(float)Math.toRadians(-7.5F), 0);
		animator.rotate(Mouth3,(float)Math.toRadians(37.5F),(float)Math.toRadians(7.5F), 0);
		animator.rotate(Mouth4,(float)Math.toRadians(37.5F),(float)Math.toRadians(-7.5F), 0);

		animator.endKeyframe();

		animator.setStaticKeyframe(80);

		animator.resetKeyframe(20);


		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_ABYSS_BLAST_PORTAL);
		animator.setStaticKeyframe(60);


		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_TENTACLE_STRIKE_UPPER_R);
		animator.startKeyframe(23);
		animator.rotate(UpperR_Tantacle,0,(float)Math.toRadians(35F), 0);
		animator.rotate(UpperR_Tantacle2,0,(float)Math.toRadians(5F), 0);
		animator.rotate(UpperR_Tantacle3,0,(float)Math.toRadians(5F), 0);
		animator.rotate(UpperR_Tantacle4,0,(float)Math.toRadians(10F), 0);
		animator.endKeyframe();


		animator.startKeyframe(1);
		animator.rotate(UpperR_Tantacle,0,(float)Math.toRadians(-55F), 0);
		animator.rotate(UpperR_Tantacle2,0,(float)Math.toRadians(5F), 0);
		animator.rotate(UpperR_Tantacle3,0,(float)Math.toRadians(-2.5F), 0);
		animator.rotate(UpperR_Tantacle4,0,(float)Math.toRadians(2.5F), 0);

		animator.rotate(UpperR_Hook,0,0, (float)Math.toRadians(22.5F));
		animator.rotate(UpperR_Hook2,0,(float)Math.toRadians(22.5F), 0);
		animator.rotate(UpperR_Hook3,0,(float)Math.toRadians(-22.5F), 0);
		animator.rotate(UpperR_Hook4,0,0, (float)Math.toRadians(-22.5F));

		animator.endKeyframe();

		animator.setStaticKeyframe(10);

		animator.resetKeyframe(10);


		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_TENTACLE_STRIKE_LOWER_R);
		animator.startKeyframe(27);
		animator.rotate(LowerR_Tantacle,0,(float)Math.toRadians(35F), 0);
		animator.rotate(LowerR_Tantacle2,0,(float)Math.toRadians(5F), 0);
		animator.rotate(LowerR_Tantacle3,0,(float)Math.toRadians(5F), 0);
		animator.rotate(LowerR_Tantacle4,0,(float)Math.toRadians(10F), 0);
		animator.endKeyframe();


		animator.startKeyframe(1);
		animator.rotate(LowerR_Tantacle,0,(float)Math.toRadians(-55F), 0);
		animator.rotate(LowerR_Tantacle2,0,(float)Math.toRadians(5F), 0);
		animator.rotate(LowerR_Tantacle3,0,(float)Math.toRadians(-2.5F), 0);
		animator.rotate(LowerR_Tantacle4,0,(float)Math.toRadians(2.5F), 0);

		animator.rotate(LowerR_Hook,0,0, (float)Math.toRadians(22.5F));
		animator.rotate(LowerR_Hook2,0,(float)Math.toRadians(22.5F), 0);
		animator.rotate(LowerR_Hook3,0,(float)Math.toRadians(-22.5F), 0);
		animator.rotate(LowerR_Hook4,0,0, (float)Math.toRadians(-22.5F));

		animator.endKeyframe();

		animator.setStaticKeyframe(6);

		animator.resetKeyframe(10);


		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_TENTACLE_STRIKE_UPPER_L);
		animator.startKeyframe(25);
		animator.rotate(UpperL_Tantacle,0,(float)Math.toRadians(-35F), 0);
		animator.rotate(UpperL_Tantacle2,0,(float)Math.toRadians(-5F), 0);
		animator.rotate(UpperL_Tantacle3,0,(float)Math.toRadians(-5F), 0);
		animator.rotate(UpperL_Tantacle4,0,(float)Math.toRadians(-10F), 0);
		animator.endKeyframe();


		animator.startKeyframe(1);
		animator.rotate(UpperL_Tantacle,0,(float)Math.toRadians(55F), 0);
		animator.rotate(UpperL_Tantacle2,0,(float)Math.toRadians(5F), 0);
		animator.rotate(UpperL_Tantacle3,0,(float)Math.toRadians(-2.5F), 0);
		animator.rotate(UpperL_Tantacle4,0,(float)Math.toRadians(-2.5F), 0);

		animator.rotate(UpperL_Hook,0,0, (float)Math.toRadians(-22.5F));
		animator.rotate(UpperL_Hook2,0,(float)Math.toRadians(-22.5F), 0);
		animator.rotate(UpperL_Hook3,0,(float)Math.toRadians(22.5F), 0);
		animator.rotate(UpperL_Hook4,0,0, (float)Math.toRadians(22.5F));

		animator.endKeyframe();

		animator.setStaticKeyframe(8);

		animator.resetKeyframe(10);

		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_TENTACLE_STRIKE_LOWER_L);
		animator.startKeyframe(20);
		animator.rotate(LowerL_Tantacle,0,(float)Math.toRadians(-35F), 0);
		animator.rotate(LowerL_Tantacle2,0,(float)Math.toRadians(-5F), 0);
		animator.rotate(LowerL_Tantacle3,0,(float)Math.toRadians(-5F), 0);
		animator.rotate(LowerL_Tantacle4,0,(float)Math.toRadians(-10F), 0);
		animator.endKeyframe();


		animator.startKeyframe(1);
		animator.rotate(LowerL_Tantacle,0,(float)Math.toRadians(55F), 0);
		animator.rotate(LowerL_Tantacle2,0,(float)Math.toRadians(5F), 0);
		animator.rotate(LowerL_Tantacle3,0,(float)Math.toRadians(-2.5F), 0);
		animator.rotate(LowerL_Tantacle4,0,(float)Math.toRadians(-2.5F), 0);

		animator.rotate(LowerL_Hook,0,0, (float)Math.toRadians(-22.5F));
		animator.rotate(LowerL_Hook2,0,(float)Math.toRadians(-22.5F), 0);
		animator.rotate(LowerL_Hook3,0,(float)Math.toRadians(22.5F), 0);
		animator.rotate(LowerL_Hook4,0,0, (float)Math.toRadians(22.5F));

		animator.endKeyframe();

		animator.setStaticKeyframe(13);

		animator.resetKeyframe(10);


		animator.setAnimation(The_Leviathan_Entity.LEVIATHAN_STUN);
		animator.startKeyframe(0);
		animator.rotate(UpperR_Tantacle,0,(float)Math.toRadians(37.5F), 0);
		animator.rotate(UpperR_Tantacle2,0,(float)Math.toRadians(45F), 0);
		animator.rotate(UpperR_Tantacle3,0,(float)Math.toRadians(50F), 0);
		animator.rotate(UpperR_Tantacle4,0,(float)Math.toRadians(40F), 0);
		animator.rotate(UpperR_Hook,0,0, (float)Math.toRadians(25F));
		animator.rotate(UpperR_Hook2,0,(float)Math.toRadians(25F), 0);
		animator.rotate(UpperR_Hook3,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(UpperR_Hook4,0,0, (float)Math.toRadians(-25F));
		animator.rotate(UpperL_Tantacle,0,(float)Math.toRadians(-37.5F), 0);
		animator.rotate(UpperL_Tantacle2,0,(float)Math.toRadians(-45F), 0);
		animator.rotate(UpperL_Tantacle3,0,(float)Math.toRadians(-50F), 0);
		animator.rotate(UpperL_Tantacle4,0,(float)Math.toRadians(-40F), 0);
		animator.rotate(UpperL_Hook,0,0, (float)Math.toRadians(-25F));
		animator.rotate(UpperL_Hook2,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(UpperL_Hook3,0,(float)Math.toRadians(25F), 0);
		animator.rotate(UpperL_Hook4,0,0, (float)Math.toRadians(25F));
		animator.rotate(LowerR_Tantacle,0,(float)Math.toRadians(37.5F), 0);
		animator.rotate(LowerR_Tantacle2,0,(float)Math.toRadians(45F), 0);
		animator.rotate(LowerR_Tantacle3,0,(float)Math.toRadians(52.5F), 0);
		animator.rotate(LowerR_Tantacle4,0,(float)Math.toRadians(30F), 0);
		animator.rotate(LowerR_Hook,0,0, (float)Math.toRadians(25F));
		animator.rotate(LowerR_Hook2,0,(float)Math.toRadians(25F), 0);
		animator.rotate(LowerR_Hook3,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(LowerR_Hook4,0,0, (float)Math.toRadians(-25F));
		animator.rotate(LowerL_Tantacle,0,(float)Math.toRadians(-37.5F), 0);
		animator.rotate(LowerL_Tantacle2,0,(float)Math.toRadians(-45F), 0);
		animator.rotate(LowerL_Tantacle3,0,(float)Math.toRadians(-52.5F), 0);
		animator.rotate(LowerL_Tantacle4,0,(float)Math.toRadians(-30F), 0);
		animator.rotate(LowerL_Hook,0,0, (float)Math.toRadians(-25F));
		animator.rotate(LowerL_Hook2,0,(float)Math.toRadians(-25F), 0);
		animator.rotate(LowerL_Hook3,0,(float)Math.toRadians(25F), 0);
		animator.rotate(LowerL_Hook4,0,0, (float)Math.toRadians(25F));
		animator.rotate(Maw,(float)Math.toRadians(15F),0, 0);
		animator.rotate(Skul,(float)Math.toRadians(-30F),0, 0);
		animator.rotate(Mouth,(float)Math.toRadians(-27.5F),(float)Math.toRadians(2.5F), 0);
		animator.rotate(Mouth2,(float)Math.toRadians(-27.5F),(float)Math.toRadians(-2.5F), 0);
		animator.rotate(Mouth3,(float)Math.toRadians(10F),(float)Math.toRadians(2.5F), 0);
		animator.rotate(Mouth4,(float)Math.toRadians(10F),(float)Math.toRadians(-2.5F), 0);
		animator.endKeyframe();

		animator.setStaticKeyframe(5);

		animator.startKeyframe(8);
		animator.rotate(four_mouths,(float)Math.toRadians(15F),0, 0);
		animator.endKeyframe();
		animator.setStaticKeyframe(15);

		animator.resetKeyframe(15);
		animator.setStaticKeyframe(10);

		animator.startKeyframe(2);
		animator.rotate(Mouth,(float)Math.toRadians(-37.5F),(float)Math.toRadians(37.5f), 0);
		animator.rotate(Mouth2,(float)Math.toRadians(-37.5F),(float)Math.toRadians(-37.5f), 0);
		animator.rotate(Mouth3,(float)Math.toRadians(37.5F),(float)Math.toRadians(37.5f), 0);
		animator.rotate(Mouth4,(float)Math.toRadians(37.5F),(float)Math.toRadians(-37.5f), 0);
		animator.rotate(Skul,(float)Math.toRadians(-40F),0, 0);
		animator.rotate(Maw,(float)Math.toRadians(30F),0, 0);

		animator.endKeyframe();

		animator.setStaticKeyframe(20);


		animator.resetKeyframe(15);

	}

	@Override
	public void setupAnim(The_Leviathan_Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{Tail, Tail2, Tail3};

		float swimSpeed = 0.1F;
		float swimDegree = 0.4F;
		float finspeed = 0.1F;
		float finDegree = 0.2F;
		boolean swimAnimate = entityIn.isInWater();
		float partialTick = Minecraft.getInstance().getFrameTime();
		float NoswimProgress = entityIn.prevNoSwimProgress + (entityIn.NoSwimProgress - entityIn.prevNoSwimProgress) * partialTick;

		progressRotationPrev(R_fin,NoswimProgress,0, 0, (float)Math.toRadians(-15F), 10f);
		progressRotationPrev(L_fin,NoswimProgress,0, 0, (float)Math.toRadians(15F), 10f);
		progressRotationPrev(R_fin2,NoswimProgress,0, 0, (float)Math.toRadians(15F), 10f);
		progressRotationPrev(L_fin2,NoswimProgress,0, 0, (float)Math.toRadians(-15F), 10f);
		progressRotationPrev(R_down_fin,NoswimProgress,0, 0, (float)Math.toRadians(-50F), 10f);
		progressRotationPrev(L_down_fin,NoswimProgress,0, 0, (float)Math.toRadians(50F), 10f);
		progressRotationPrev(R_down_fin2,NoswimProgress,0, 0, (float)Math.toRadians(50F), 10f);
		progressRotationPrev(L_down_fin2,NoswimProgress,0, 0, (float)Math.toRadians(-50F), 10f);
		progressRotationPrev(R_mini_fin,NoswimProgress,0, 0, (float)Math.toRadians(-32.5F), 10f);
		progressRotationPrev(L_mini_fin,NoswimProgress,0, 0, (float)Math.toRadians(32.5F), 10f);
		progressRotationPrev(LowerL_Tantacle,NoswimProgress,0, 0, (float)Math.toRadians(-32.5F), 10f);
		progressRotationPrev(LowerR_Tantacle,NoswimProgress,0, 0, (float)Math.toRadians(32.5F), 10f);

		this.swing(UpperR_Tantacle, swimSpeed * 0.4F, 0.1F, false, 0F, -0.1F, ageInTicks, 1.0f);
		//this.swing(UpperR_Tantacle2, swimSpeed * 0.4F, 0.05F, false, 1F, -0.05F, ageInTicks, 1.0f);
		this.swing(UpperR_Tantacle3, swimSpeed * 0.4F, 0.1F, false, 2F, -0.1F, ageInTicks, 1.0f);


		this.flap(UpperR_Hook, swimSpeed * 0.2F, 0.35F, true, 0F, -0.35F, ageInTicks, 1.0f);
		this.swing(UpperR_Hook2, swimSpeed * 0.2F, 0.35F, true, 0F, -0.35F, ageInTicks, 1.0f);
		this.swing(UpperR_Hook3, swimSpeed * 0.2F, 0.35F, false, 0F, -0.35F, ageInTicks, 1.0f);
		this.flap(UpperR_Hook4, swimSpeed * 0.2F, 0.35F, false, 0F, -0.35F, ageInTicks, 1.0f);
		this.flap(UpperR_Hook, swimSpeed * 1.3F, 0.35F, true, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.swing(UpperR_Hook2, swimSpeed * 1.3F, 0.35F, true, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.swing(UpperR_Hook3, swimSpeed * 1.3F, 0.35F, false, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.flap(UpperR_Hook4, swimSpeed * 1.3F, 0.35F, false, 0F, -0.35F, limbSwing, limbSwingAmount);

		this.swing(UpperL_Tantacle, swimSpeed * 0.4F, 0.1F, true, 0F, -0.1F, ageInTicks, 1.0f);
		//this.swing(UpperL_Tantacle2, swimSpeed * 0.4F, 0.05F, true, 1F, -0.05F, ageInTicks, 1.0f);
		this.swing(UpperL_Tantacle3, swimSpeed * 0.4F, 0.1F, true, 2f, -0.1F, ageInTicks, 1.0f);


		this.flap(UpperL_Hook, swimSpeed * 0.2F, 0.35F, false, 0F, -0.35F, ageInTicks, 1.0f);
		this.swing(UpperL_Hook2, swimSpeed * 0.2F, 0.35F, false, 0F, -0.35F, ageInTicks, 1.0f);
		this.swing(UpperL_Hook3, swimSpeed * 0.2F, 0.35F, true, 0F, -0.35F, ageInTicks, 1.0f);
		this.flap(UpperL_Hook4, swimSpeed * 0.2F, 0.35F, true, 0F, -0.35F, ageInTicks, 1.0f);
		this.flap(UpperL_Hook, swimSpeed * 1.3F, 0.35F, false, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.swing(UpperL_Hook2, swimSpeed * 1.3F, 0.35F, false, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.swing(UpperL_Hook3, swimSpeed * 1.3F, 0.35F, true, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.flap(UpperL_Hook4, swimSpeed * 1.3F, 0.35F, true, 0F, -0.35F, limbSwing, limbSwingAmount);

		this.swing(LowerR_Tantacle, swimSpeed * 0.4F, 0.1F, false, 0F, -0.1F, ageInTicks, 1.0f);
	//	this.swing(LowerR_Tantacle2, swimSpeed * 0.4F, 0.05F, false, 1f, -0.05F, ageInTicks, 1.0f);
		this.swing(LowerR_Tantacle3, swimSpeed * 0.4F, 0.1F, false, 2f, -0.1F, ageInTicks, 1.0f);


		this.flap(LowerR_Hook, swimSpeed * 0.2F, 0.35F, true, 0F, -0.35F, ageInTicks, 1.0f);
		this.swing(LowerR_Hook2, swimSpeed * 0.2F, 0.35F, true, 0F, -0.35F, ageInTicks, 1.0f);
		this.swing(LowerR_Hook3, swimSpeed * 0.2F, 0.35F, false, 0F, -0.35F, ageInTicks, 1.0f);
		this.flap(LowerR_Hook4, swimSpeed * 0.2F, 0.35F, false, 0F, -0.35F, ageInTicks, 1.0f);
		this.flap(LowerR_Hook, swimSpeed * 1.3F, 0.35F, true, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.swing(LowerR_Hook2, swimSpeed * 1.3F, 0.35F, true, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.swing(LowerR_Hook3, swimSpeed * 1.3F, 0.35F, false, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.flap(LowerR_Hook4, swimSpeed * 1.3F, 0.35F, false, 0F, -0.35F, limbSwing, limbSwingAmount);

		this.swing(LowerL_Tantacle, swimSpeed * 0.4F, 0.1F, true, 0F, -0.1F, ageInTicks, 1.0f);
	//	this.swing(LowerL_Tantacle2, swimSpeed * 0.4F, 0.05F, true, 1f, -0.05F, ageInTicks, 1.0f);
		this.swing(LowerL_Tantacle3, swimSpeed * 0.4F, 0.1F, true, 2f, -0.1F, ageInTicks, 1.0f);

		this.flap(LowerL_Hook, swimSpeed * 0.2F, 0.35F, false, 0F, -0.35F, ageInTicks, 1.0f);
		this.swing(LowerL_Hook2, swimSpeed * 0.2F, 0.35F, false, 0F, -0.35F, ageInTicks, 1.0f);
		this.swing(LowerL_Hook3, swimSpeed * 0.2F, 0.35F, true, 0F, -0.35F, ageInTicks, 1.0f);
		this.flap(LowerL_Hook4, swimSpeed * 0.2F, 0.35F, true, 0F, -0.35F, ageInTicks, 1.0f);
		this.flap(LowerL_Hook, swimSpeed * 1.3F, 0.35F, false, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.swing(LowerL_Hook2, swimSpeed * 1.3F, 0.35F, false, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.swing(LowerL_Hook3, swimSpeed * 1.3F, 0.35F, true, 0F, -0.35F, limbSwing, limbSwingAmount);
		this.flap(LowerL_Hook4, swimSpeed * 1.3F, 0.35F, true, 0F, -0.35F, limbSwing, limbSwingAmount);

		this.chainSwing(tailBoxes, swimSpeed * 0.4F, swimDegree * 0.45F, -1, ageInTicks , 1.0f);
		this.chainSwing(tailBoxes, swimSpeed * 4F, swimDegree * 0.6F, -1, limbSwing,limbSwingAmount);
		if(swimAnimate) {
			this.flap(R_fin, finspeed * 0.8F, finDegree, false, 0F, -0.2F, ageInTicks, 1.0f);
			this.flap(R_fin2, finspeed * 0.8F, finDegree, false, 1F, -0.2F, ageInTicks, 1.0f);
			this.flap(L_fin, finspeed * 0.8F, finDegree, true, 0F, -0.2F, ageInTicks, 1.0f);
			this.flap(L_fin2, finspeed * 0.8F, finDegree, true, 1F, -0.2F, ageInTicks, 1.0f);

			this.flap(R_fin, finspeed * 4.0F, finDegree, false, 0F, -0.2F, limbSwing, limbSwingAmount);
			this.flap(R_fin2, finspeed * 4.0F, finDegree, false, 1F, -0.2F, limbSwing, limbSwingAmount);
			this.flap(L_fin, finspeed * 4.0F, finDegree, true, 0F, -0.2F, limbSwing, limbSwingAmount);
			this.flap(L_fin2, finspeed * 4.0F, finDegree, true, 1F, -0.2F, limbSwing, limbSwingAmount);

			this.flap(R_mini_fin, finspeed * 1.2F, finDegree * 2.5F, false, 0F, -0.5F, ageInTicks, 1.0f);
			this.flap(L_mini_fin, finspeed * 1.2F, finDegree * 2.5F, true, 0F, -0.5F, ageInTicks, 1.0f);

			this.flap(R_down_fin, finspeed * 0.8F, finDegree * 1.5F, false, 0F, -0.3F, ageInTicks, 1.0f);
			this.flap(L_down_fin, finspeed * 0.8F, finDegree * 1.5F, true, 0F, -0.3F, ageInTicks, 1.0f);

			this.flap(R_mini_fin, finspeed * 4.0F, finDegree * 2.5F, false, 0F, -0.5F, limbSwing, limbSwingAmount);
			this.flap(L_mini_fin, finspeed * 4.0F, finDegree * 2.5F, true, 0F, -0.5F, limbSwing, limbSwingAmount);

			this.flap(R_down_fin, finspeed * 4.0F, finDegree * 1.5F, false, 0F, -0.3F, limbSwing, limbSwingAmount);
			this.flap(L_down_fin, finspeed * 4.0F, finDegree * 1.5F, true, 0F, -0.3F, limbSwing, limbSwingAmount);
			this.flap(L_down_fin, finspeed, finDegree * 1.5F, true, 0F, -0.3F, ageInTicks, 1.0f);
		}else{
			this.swing(R_fin, finspeed * 20.0F, finDegree * 1.5f, false, 0F, -0.1F, limbSwing, limbSwingAmount);
			this.swing(L_fin, finspeed * 20.0F, finDegree * 1.5f, true, 0F, -0.1F, limbSwing, limbSwingAmount);
		}
		this.walk(Mouth, finspeed * 0.8F, finDegree * 0.15F, false, 0F, -0.03F, ageInTicks, 1.0f);
		this.walk(Mouth2, finspeed * 0.8F, finDegree * 0.15F, false, 0F, -0.03F, ageInTicks, 1.0f);
		this.walk(Mouth3, finspeed * 0.8F, finDegree * 0.15F, true, 0F, -0.03F, ageInTicks, 1.0f);
		this.walk(Mouth4, finspeed * 0.8F, finDegree * 0.15F, true, 0F, -0.03F, ageInTicks, 1.0f);

		this.walk(Maw, finspeed * 1.1F, finDegree * 0.15F, true, 0F, -0.03F, limbSwing, limbSwingAmount);
		this.walk(Skul, finspeed * 1.1F, finDegree * 0.15F, false, 0F, -0.03F, limbSwing, limbSwingAmount);


		this.body.rotateAngleX += headPitch * ((float) Math.PI / 180F);
		this.body.rotateAngleY += netHeadYaw * ((float) Math.PI / 180F);

		if (horizontalMag(entityIn.getDeltaMovement()) > 1.0E-7D) {
			//this.body.rotateAngleX += -0.05F + -0.05F * Mth.cos(ageInTicks * 0.3F);
		}

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