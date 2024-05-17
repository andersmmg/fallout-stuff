package com.andersmmg.falloutstuff.client.renderer;

import com.andersmmg.falloutstuff.block.custom.VaultSignBlock;
import com.andersmmg.falloutstuff.block.entity.VaultSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.*;

public class VaultSignBlockEntityRenderer implements BlockEntityRenderer<VaultSignBlockEntity> {
    private final TextRenderer textRenderer;

    private static final int RENDER_DISTANCE = MathHelper.square(32);

    public VaultSignBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.textRenderer = ctx.getTextRenderer();
    }

    @Override
    public void render(VaultSignBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (!shouldRender(entity.getPos())) {
            return;
        }
        boolean isTop = entity.isTop();
        matrices.push();
        matrices.translate(0.5f, 0.5f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(getRotation(entity)));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-20));
        matrices.translate(0.0f, 0.18f, 0.49f);
        if (!isTop) {
            matrices.translate(0.0f, -0.65f, -0.24f);
        }
        float textScale = 0.013f;
        matrices.scale(textScale, textScale, textScale);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));

        Text signText = entity.getText();

        float f = (float) (-this.textRenderer.getWidth(signText) / 2);
        float g = (float) (-this.textRenderer.fontHeight / 2);

        this.textRenderer.draw(signText, f, g, 0, false, matrices.peek().getPositionMatrix(), vertexConsumers, TextRenderer.TextLayerType.POLYGON_OFFSET, 0, 5);
        matrices.pop();
    }

    static boolean shouldRender(BlockPos pos) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
        if (clientPlayerEntity != null && minecraftClient.options.getPerspective().isFirstPerson() && clientPlayerEntity.isUsingSpyglass()) {
            return true;
        } else {
            Entity entity = minecraftClient.getCameraEntity();
            return entity != null && entity.squaredDistanceTo(Vec3d.ofCenter(pos)) < (double) RENDER_DISTANCE;
        }

    }

    public int getRotation(VaultSignBlockEntity entity) {
        BlockState state = entity.getCachedState();
        Direction dir = state.get(VaultSignBlock.FACING);
        return switch (dir) {
            case NORTH -> 0;
            case SOUTH -> 180;
            case EAST -> 270;
            case WEST -> 90;
            default -> 0;
        };
    }
}
