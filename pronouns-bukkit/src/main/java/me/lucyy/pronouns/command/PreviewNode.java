/*
 * Copyright (C) 2021 Lucy Poulton https://lucyy.me
 * This file is part of ProNouns.
 *
 * ProNouns is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ProNouns is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ProNouns.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.lucyy.pronouns.command;

import me.lucyy.pronouns.ProNouns;
import me.lucyy.pronouns.api.set.PronounSet;
import me.lucyy.squirtgun.command.argument.CommandArgument;
import me.lucyy.squirtgun.command.context.CommandContext;
import me.lucyy.squirtgun.command.node.CommandNode;
import me.lucyy.squirtgun.format.FormatProvider;
import me.lucyy.squirtgun.platform.PermissionHolder;
import me.lucyy.squirtgun.platform.SquirtgunPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PreviewNode implements CommandNode<PermissionHolder> {
	private final ProNouns pl;

	public PreviewNode(ProNouns plugin) {
		pl = plugin;
	}

	@Override
	public @NotNull String getName() {
		return "preview";
	}

	@Override
	public String getDescription() {
		return "Test out your pronoun selection!";
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public @NotNull List<CommandArgument<?>> getArguments() {
		return Collections.emptyList();
	}

	private String capitalise(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

	@Override
	public Component execute(final CommandContext<PermissionHolder> context) {
		final FormatProvider fmt = context.getFormat();
		if (!(context.getTarget() instanceof SquirtgunPlayer)) {
			return fmt.getPrefix()
					.append(fmt.formatMain("Only players can use this command."));
		}

		final SquirtgunPlayer player = (SquirtgunPlayer) context.getTarget();

		final Collection<PronounSet> sets = pl.getPronounHandler().getPronouns(player.getUuid());
		if (sets.size() == 0) {
			return fmt.getPrefix()
					.append(fmt.formatMain("You haven't set any pronouns yet!"));
		}

		final PronounSet set = sets.iterator().next();

		// yes, this is messy, but java compiles it to a stringbuilder so its all good
		String builder = player.getUsername() + " is testing " + set.getPossessiveAdjective() +
				" pronoun selection.\n" +
				"Have you seen " + player.getUsername() + "? " +
				capitalise(set.getSubjective()) + " asked me to help with " +
				set.getObjective() + " build.\n" +
				player.getUsername() + " has been spending all " + set.getPossessiveAdjective() +
				" time on this server. I hope " + set.getProgressive() + " doing okay.";
		return Component.text(builder);
	}
}