package com.sxtanna.mc.mods;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.sxtanna.mc.PluginDeepStore;
import com.sxtanna.mc.data.DeepStore;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Map;

public final class ModuleDeepStore implements Listener
{

	@NotNull
	private final PluginDeepStore       plugin;
	@NotNull
	private final Map<Block, DeepStore> stores = Maps.newHashMap();


	public ModuleDeepStore(@NotNull final PluginDeepStore plugin)
	{
		this.plugin = plugin;
	}


	public void load()
	{

	}

	public void kill()
	{

	}


	@NotNull
	@Unmodifiable
	public Map<Block, DeepStore> getStores()
	{
		return ImmutableMap.copyOf(stores);
	}

}
