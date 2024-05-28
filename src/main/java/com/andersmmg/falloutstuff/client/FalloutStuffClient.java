package com.andersmmg.falloutstuff.client;

import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.block.entity.ModBlockEntities;
import com.andersmmg.falloutstuff.client.renderer.SlidingDoorBlockEntityRenderer;
import com.andersmmg.falloutstuff.client.renderer.VaultSignBlockEntityRenderer;
import com.andersmmg.falloutstuff.client.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class FalloutStuffClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VAULT_BOY_STATUE, RenderLayer.getCutout());
        BlockEntityRendererFactories.register(ModBlockEntities.VAULT_SIGN_BLOCK_ENTITY, VaultSignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SLIDING_DOOR_BLOCK_ENTITY, SlidingDoorBlockEntityRenderer::new);
		ModScreenHandlers.registerScreenHandlers();
	}
}