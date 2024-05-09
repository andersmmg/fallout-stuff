package com.andersmmg.falloutstuff.block.entity;

import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.record.SignUpdatePacket;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class VaultSignBlockEntity extends BlockEntity {
    private Text text = Text.literal("");

    public VaultSignBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.VAULT_SIGN_BLOCK_ENTITY, blockPos, blockState);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.text = Text.Serializer.fromJson(nbt.getString("text"));
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("text", Text.Serializer.toJson(this.text));
    }

    // Method to update the text
    public void setText(Text text) {
        this.text = text;
        if (world.isClient) {
            sendUpdatePacket(); // Send packet to server
        } else {
            markDirty();
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    // Getter for the text
    public Text getText() {
        return this.text;
    }

    // Method to send update packet to clients
    private void sendUpdatePacket() {
        FalloutStuff.SIGN_UPDATE_CHANNEL.clientHandle().send(new SignUpdatePacket(pos, this.text));
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (world != null && !world.isClient) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    public void tick() {
    }
}