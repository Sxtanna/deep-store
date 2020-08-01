package com.sxtanna.mc.data;

import com.google.common.collect.Sets;
import com.sxtanna.mc.page.Page;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public final class DeepStore
{

	@NotNull
	private final Page page;
	@NotNull
	private final UUID user;

	@NotNull
	private final Set<UUID> access = Sets.newHashSet();


	public DeepStore(@NotNull final Page page, @NotNull final UUID user)
	{
		this.page = page;
		this.user = user;
	}

	@NotNull
	public Page getPage()
	{
		return page;
	}

	@NotNull
	public UUID getUser()
	{
		return user;
	}


	/**
	 * Check to see if a UUID can access this deep store
	 *
	 * @param uuid The {@link UUID} to check
	 *
	 * @return true if this {@link UUID} represents the owner, or is allowed access.
	 */
	public boolean testAccess(@NotNull final UUID uuid)
	{
		return user.equals(uuid) || access.contains(uuid);
	}

	/**
	 * Attempt to give access of this deep store to the provided {@link UUID}
	 *
	 * @param uuid The {@link UUID} to give access to
	 *
	 * @return true if they were given access, false if they already had it.
	 */
	public boolean giveAccess(@NotNull final UUID uuid)
	{
		return access.add(uuid);
	}

	/**
	 * Attempt to take access of this deep store from the provided {@link UUID}
	 *
	 * @param uuid The {@link UUID} to take access from
	 *
	 * @return true if their access was taken, false if they didn't have access.
	 */
	public boolean takeAccess(@NotNull final UUID uuid)
	{
		return access.remove(uuid);
	}

}
