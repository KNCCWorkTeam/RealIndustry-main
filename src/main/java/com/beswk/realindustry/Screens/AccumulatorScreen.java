package com.beswk.realindustry.Screens;

import com.beswk.realindustry.BlockEntities.IAccumulatorBlockEntity;
import com.beswk.realindustry.BlockEntities.IGeneratorBlockEntity;
import com.beswk.realindustry.Menu.AccumulatorMenu;
import com.beswk.realindustry.Menu.MachineMenu;
import com.beswk.realindustry.util.Class.MachineComponent;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class AccumulatorScreen extends AbstractContainerScreen<AccumulatorMenu> {
    private final static HashMap<String, Object> guistate = AccumulatorMenu.guistate;

    private final Level world;
    private final int x, y, z;
    private final Player entity;
    private MachineComponent machineComponent;

    public AccumulatorScreen(AccumulatorMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        if (text instanceof MachineComponent machineComponent) {
            this.machineComponent = machineComponent;
        }
        System.out.println(machineComponent.getEntity());
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    private static final ResourceLocation texture = new ResourceLocation("realindustry:textures/gui/container/accumulator.png");

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
        this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

        RenderSystem.setShaderTexture(0, new ResourceLocation("realindustry:textures/gui/container/electricity.png"));
        this.blit(ms, this.leftPos + 79, this.topPos + 41, 0, 0, 16, 80, 16, 80);

        if (machineComponent.getEntity() instanceof IAccumulatorBlockEntity iAccumulatorBlock) {
            float process = (float) iAccumulatorBlock.energyStorage.getEnergyStored() / iAccumulatorBlock.energyStorage.getMaxEnergyStored();
            RenderSystem.setShaderTexture(0, new ResourceLocation("realindustry:textures/gui/container/electricity_full.png"));
            this.blit(ms, this.leftPos + 79, this.topPos + 41, 0, 0, 16, (int) (process * 80), 16, 80);
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
        if (machineComponent.getEntity() instanceof IAccumulatorBlockEntity iAccumulatorBlock) {
            this.font.draw(poseStack, iAccumulatorBlock.energyStorage.getEnergyStored()+"/"+iAccumulatorBlock.energyStorage.getMaxEnergyStored(), 60, 25, -12829636);
        }
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
