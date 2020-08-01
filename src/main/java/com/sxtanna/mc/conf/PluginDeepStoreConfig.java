package com.sxtanna.mc.conf;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class PluginDeepStoreConfig
{

	@NotNull
	private final Plugin plugin;


	public PluginDeepStoreConfig(@NotNull final Plugin plugin)
	{
		this.plugin = plugin;
	}


	@NotNull
	public Optional<String> getMenuLayout(final int pageCount)
	{
		return Optional.ofNullable(plugin.getConfig().getString("menu.layout." + pageCount));
	}

}
