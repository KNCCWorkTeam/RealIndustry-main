
package com.beswk.realindustry.Screens;

import com.beswk.realindustry.BlockEntities.GeneratorBlockEntity;
import com.beswk.realindustry.Menu.GeneratorMenu;
import com.beswk.realindustry.util.Class.GeneratorComponent;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.HashMap;

public abstract class GeneratorScreen extends AbstractContainerScreen<GeneratorMenu> {
	private final static HashMap<String, Object> guistate = GeneratorMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private GeneratorComponent generatorComponent;

	public GeneratorScreen(GeneratorMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		if (text instanceof GeneratorComponent generatorComponent) {
			this.generatorComponent = generatorComponent;
		}
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("realindustry","textures/gui/container/generator.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("realindustry","textures/gui/container/furnace_burn.png"));
		blit(ms, this.leftPos + 80, this.topPos + 26, 0, 0, 13, 13, 13, 13);

		BlockEntity entity = generatorComponent.getEntity();

		if (entity instanceof GeneratorBlockEntity generatorBlockEntity) {
			int process = generatorBlockEntity.data.get(0);
			RenderSystem.setShaderTexture(0, new ResourceLocation("realindustry", "textures/gui/container/furnace.png"));
			System.out.println(this.topPos + 26 - (int) (process * 0.26));
			blit(ms, this.leftPos + 80, this.topPos + 26, 0, 0, 13, 13-(int) (process * 0.26), 13, 13);
		}

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
	}
}
