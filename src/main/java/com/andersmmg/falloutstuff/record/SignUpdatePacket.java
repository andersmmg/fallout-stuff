package com.andersmmg.falloutstuff.record;

import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public record SignUpdatePacket(BlockPos pos, Text text) {
}