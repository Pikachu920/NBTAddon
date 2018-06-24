package com.pikachu.nbt.skript;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import com.pikachu.nbt.NBTAddon;
import com.pikachu.nbt.nms.NBTApi;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprNBT extends SimplePropertyExpression<Object, String> {

	static {
		register(ExprNBT.class, String.class, "nbt", "entities/itemstacks/blocks");
	}

	@Override
	protected String getPropertyName() {
		return "nbt";
	}

	@Override
	public String convert(Object o) {
		NBTApi api = NBTAddon.getNBTApi();
		if (o instanceof ItemStack) {
			return api.getNBT((ItemStack) o);
		} else if (o instanceof Entity) {
			return api.getNBT((Entity) o);
		} else if (o instanceof Block) {
			return api.getNBT((Block) o);
		}
		return null;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

}
