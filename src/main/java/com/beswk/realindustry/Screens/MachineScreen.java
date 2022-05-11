package com.beswk.realindustry.Screens;

/* imports omitted */

import com.beswk.realindustry.BlockEntities.MachineBlockEntity;
import com.beswk.realindustry.Menu.MachineMenu;
import com.beswk.realindustry.util.Class.MachineComponent;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.HashMap;

public class MachineScreen extends AbstractContainerScreen<MachineMenu> {

    private final static HashMap<String, Object> guistate = MachineMenu.guistate;

    private final Level world;
    private final int x, y, z;
    private final Player entity;
    private MachineComponent machineComponent;

    public MachineScreen(MachineMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        if (text instanceof MachineComponent machineComponent) {
            this.machineComponent = machineComponent;
        }
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    private static final ResourceLocation texture = new ResourceLocation("realindustry:textures/gui/container/machine.png");

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

        RenderSystem.setShaderTexture(0, new ResourceLocation("realindustry:textures/gui/container/arrow.png"));
        this.blit(ms, this.leftPos + 85, this.topPos + 36, 0, 0, 22, 15, 22, 15);

        RenderSystem.setShaderTexture(0, new ResourceLocation("realindustry","textures/gui/container/electricity.png"));
        this.blit(ms, this.leftPos + 155, this.topPos + 3, 0, 0, 16, 80, 16, 80);

        BlockEntity entity = machineComponent.getEntity();
        if (entity instanceof MachineBlockEntity machineBlockEntity) {
            float process = machineBlockEntity.data.get(0);
            RenderSystem.setShaderTexture(0, new ResourceLocation("realindustry:textures/gui/container/arrow_complete.png"));
            this.blit(ms, this.leftPos + 85, this.topPos + 36, 0, 0, ((int) (process * 0.022)), 15, 22, 15);

            process = (float)machineBlockEntity.energyStorage.getEnergyStored()/machineBlockEntity.energyStorage.getMaxEnergyStored();
            RenderSystem.setShaderTexture(0, new ResourceLocation("realindustry","textures/gui/container/electricity_full.png"));
            this.blit(ms, this.leftPos + 155, this.topPos + 3, 0, 0, 16, (int)(process*80), 16, 80);
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