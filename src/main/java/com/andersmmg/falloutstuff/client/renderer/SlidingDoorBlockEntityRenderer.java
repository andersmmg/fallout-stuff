package com.andersmmg.falloutstuff.client.renderer;

import com.andersmmg.falloutstuff.block.custom.SlidingDoorBlock;
import com.andersmmg.falloutstuff.block.entity.SlidingDoorBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class SlidingDoorBlockEntityRenderer implements BlockEntityRenderer<SlidingDoorBlockEntity> {
    public SlidingDoorBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(SlidingDoorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState state = entity.getCachedState();
        boolean isOpen = state.get(SlidingDoorBlock.OPEN);
        if (state.get(SlidingDoorBlock.HALF) == DoubleBlockHalf.UPPER) {
            return;
        }
        matrices.push();

        float animationProgress = entity.getAnimationProgress(tickDelta);
        float easing = (float) (0.5 * (1 - Math.cos(Math.PI * animationProgress)));
        matrices.translate(0, 1.8125f * easing, 0);

        renderBlockAsEntity(state, matrices, vertexConsumers, light, overlay);

        matrices.pop();
    }

    public void renderBlockAsEntity(BlockState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
        BakedModel bakedModel = blockRenderManager.getModel(state);
        BlockModelRenderer blockModelRenderer = new BlockModelRenderer(new BlockColors());
        blockModelRenderer.render(matrices.peek(), vertexConsumers.getBuffer(RenderLayers.getEntityBlockLayer(state, false)), state, bakedModel, 255, 255, 255, light, overlay);
    }
}
