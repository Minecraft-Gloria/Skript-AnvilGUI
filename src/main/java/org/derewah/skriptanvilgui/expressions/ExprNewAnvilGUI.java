package org.derewah.skriptanvilgui.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.marquez.skriptvariablelink.data.VariableMap;
import me.marquez.variablelink.api.data.BetterVariableMap;
import org.bukkit.event.Event;
import org.derewah.skriptanvilgui.anvilgui.Anvil;


public class ExprNewAnvilGUI extends SimpleExpression<Anvil> {

    static {
        Skript.registerExpression(ExprNewAnvilGUI.class, Anvil.class, ExpressionType.SIMPLE,
                "[a] new anvil gui",
                "[a] new anvil gui (named|with title) %string% with [default] text %string%",
                "[a] new anvil gui with id %string% [with data %object%]"
                );
    }


    private Expression<String> exprTitle;
    private Expression<String> exprText;
    private Expression<String> exprId;
    private Expression<VariableMap> exprData;
    private boolean prebuild = false;

    public boolean init(Expression<?>[] expression, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if(matchedPattern == 1){
            prebuild = true;
            exprTitle = (Expression<String>) expression[0];
            exprText = (Expression<String>) expression[1];
        }else if(matchedPattern == 2) {
            exprId = (Expression<String>) expression[0];
            if(expression.length > 1)
                exprData = (Expression<VariableMap>) expression[1];
        }
        return true;
    }

    @Override
    protected Anvil[] get(Event event){
        Anvil anvil;
        if(exprId == null) {
            anvil = new Anvil();
            if (prebuild) {
                String text = exprText.getSingle(event);
                String title = exprTitle.getSingle(event);
                if (title != null && text != null) {
                    anvil.setTitle(title);
                    anvil.setText(text);
                }
            }
        }else {
            anvil = new Anvil(exprId.getSingle(event), exprData.getSingle(event));
        }

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
        String text = exprText.getSingle(event);
        String title = exprTitle.getSingle(event);
        return "new anvil gui "
                + (title != null ? "with title"  + title : "")
                + (text != null ? "with text " + text : "");
    }

}
