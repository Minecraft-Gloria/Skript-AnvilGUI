package org.derewah.skriptanvilgui.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.event.Event;
import org.derewah.skriptanvilgui.events.Anvil.BridgeAnvilClick;
import org.jetbrains.annotations.Nullable;

public class EvtOnCustomAnvilGUIClick extends SkriptEvent {

	static {
		Skript.registerEvent("custom anvil gui click", EvtOnCustomAnvilGUIClick.class, BridgeAnvilClick.class, "anvil gui click (on|of|in|at) %string%");
	}

	private String id;

	@Override
	public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
		id = ((Literal<String>)literals[0]).getSingle();
		return true;
	}

	@Override
	public boolean check(Event event) {
		if(event instanceof BridgeAnvilClick) {
			BridgeAnvilClick e = (BridgeAnvilClick)event;
			String id = e.getAnvil().getId();
			if(id == null)
				return false;
			return this.id.equalsIgnoreCase(id);
		}
		return false;
	}

	@Override
	public String toString(@Nullable Event event, boolean b) {
		return "anvil gui click on " + id;
	}
}
