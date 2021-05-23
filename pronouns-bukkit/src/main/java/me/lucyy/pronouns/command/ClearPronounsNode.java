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

import me.lucyy.pronouns.api.PronounHandler;
import me.lucyy.squirtgun.command.context.CommandContext;
import me.lucyy.squirtgun.command.node.CommandNode;
import me.lucyy.squirtgun.format.FormatProvider;
import me.lucyy.squirtgun.platform.PermissionHolder;
import me.lucyy.squirtgun.platform.SquirtgunPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class ClearPronounsNode implements CommandNode<PermissionHolder> {
	private final PronounHandler pronounHandler;

	public ClearPronounsNode(PronounHandler pronounHandler) {
		this.pronounHandler = pronounHandler;
	}

	@Override
	public @NotNull String getName() {
		return "clear";
	}

	public String getDescription() {
		return "Clears your pronouns.";
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public Component execute(CommandContext<PermissionHolder> context) {
		final FormatProvider fmt = context.getFormat();
		if (!(context.getTarget() instanceof SquirtgunPlayer)) {
			return fmt.getPrefix()
					.append(fmt.formatMain("This command can only be run by a player."));
		}


		pronounHandler.clearUserPronouns(((SquirtgunPlayer) context.getTarget()).getUuid());
		return fmt.getPrefix().append(fmt.formatMain("Cleared pronouns"));
	}
}