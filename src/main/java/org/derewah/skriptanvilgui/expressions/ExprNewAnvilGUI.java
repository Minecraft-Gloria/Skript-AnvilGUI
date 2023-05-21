package org.derewah.skriptanvilgui.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.derewah.skriptanvilgui.SkriptAnvilGUI;
import org.derewah.skriptanvilgui.anvilgui.Anvil;
import org.derewah.skriptanvilgui.events.Anvil.BridgeAnvilClick;
import org.derewah.skriptanvilgui.events.Anvil.BridgeAnvilClose;

import java.util.Arrays;
import java.util.function.BiFunction;


public class ExprNewAnvilGUI extends SimpleExpression<Anvil> {

    static {
        Skript.registerExpression(ExprNewAnvilGUI.class, Anvil.class, ExpressionType.SIMPLE, "[a] new anvil gui");
    }

    public boolean init(Expression<?>[] expression, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        return true;
    }

    @Override
    protected Anvil[] get(Event event){
        Anvil anvil = new Anvil();
        return new Anvil[]{anvil};
    }


    @Override
    public boolean isSingle(){
        return true;
    }

    @Override
    public Class<? extends Anvil> getReturnType(){
        return Anvil.class;
    }


    @Override
    public String toString(Event event, boolean debug){
        return "new anvil gui";
    }

}
