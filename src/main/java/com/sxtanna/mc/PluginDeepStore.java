package com.sxtanna.mc;

import com.sxtanna.mc.conf.PluginDeepStoreConfig;
import com.sxtanna.mc.mods.ModuleDeepStore;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class PluginDeepStore extends JavaPlugin
{

	@NotNull
	private final ModuleDeepStore       module = new ModuleDeepStore(this);
	@NotNull
	private final PluginDeepStoreConfig config = new PluginDeepStoreConfig(this);


	@Override
	public void onLoad()
	{
		saveDefaultConfig();
	}

	@Override
	public void onEnable()
	{
		module.load();
	}

	@Override
	public void onDisable()
	{
		module.kill();
	}


	@NotNull
	public ModuleDeepStore getModule()
	{
		return module;
	}

	@NotNull
	public PluginDeepStoreConfig getDeepStoreConfig()
	{
		return config;
	}

}
