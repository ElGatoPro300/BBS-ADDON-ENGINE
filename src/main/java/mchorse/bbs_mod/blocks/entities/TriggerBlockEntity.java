package mchorse.bbs_mod.blocks.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class TriggerBlockEntity extends BlockEntity
{
    public TriggerBlockEntity(BlockPos pos, BlockState state)
    {
        super(null, pos, state);
    }

    public Box getRegionBox()
    {
        return null;
    }

    public Box getRegionBoxRelative()
    {
        return null;
    }

    public Box getRegionBox(double x, double y, double z)
    {
        return null;
    }
}
