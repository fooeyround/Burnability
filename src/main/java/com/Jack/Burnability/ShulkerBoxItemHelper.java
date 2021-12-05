package com.Jack.Burnability;

import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.stream.Stream;

public class ShulkerBoxItemHelper {
    public static void EmptyShulkerBox(ItemEntity entity) {
        if (Block.getBlockFromItem(entity.getStack().getItem()) instanceof ShulkerBoxBlock) {
            CompoundTag nbttag = entity.getStack().getOrCreateTag();
            if (nbttag == null) return;

            ListTag nbtList = nbttag.getCompound("BlockEntityTag").getList("Items", 10);
            Stream<Tag> NBTStream = nbtList.stream();
            Objects.requireNonNull(CompoundTag.class);
            spawnItemContents(entity, NBTStream.map(CompoundTag.class::cast).map(ItemStack::fromTag));

        }

    }
    public static void spawnItemContents(ItemEntity itemEntity, Stream<ItemStack> contents) {
        World world = itemEntity.world;
        if (!world.isClient) {
            contents.forEach((stack) -> {
                world.spawnEntity(new ItemEntity(world, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), stack));
            });
        }
    }
}
