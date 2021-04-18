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

import me.lucyy.common.command.Subcommand;
import me.lucyy.common.format.Platform;
import me.lucyy.pronouns.ProNouns;
import me.lucyy.pronouns.api.set.PronounSet;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class PreviewSubcommand implements Subcommand {
    private final ProNouns pl;

    public PreviewSubcommand(ProNouns plugin) {
        pl = plugin;
    }

    @Override
    public String getName() {
        return "preview";
    }

    @Override
    public String getDescription() {
        return "Test out your pronoun selection!";
    }

    @Override
    public String getUsage() {
        return "preview";
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, CommandSender target, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player");
            return true;
        }

        Player player = (Player) target;

        Collection<PronounSet> sets = pl.getPronounHandler().getPronouns(player.getUniqueId());
        if (sets.size() == 0) {
            Platform.send(sender, pl.getConfigHandler().getPrefix()
                    .append(pl.getConfigHandler().formatMain("You haven't set any pronouns yet!")));
            return true;
        }

        PronounSet set = sets.iterator().next();

        sender.sendMessage(player.getDisplayName() + " is testing " + set.getPossessiveAdjective() +
                " pronoun selection. If this sentence seems right, then " + set.getSubjective() +
                " will be pleased with " + set.getPossessiveAdjective() + " choices.");
        return true;
    }
}
