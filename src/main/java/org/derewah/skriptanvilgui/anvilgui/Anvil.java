package org.derewah.skriptanvilgui.anvilgui;

import lombok.Getter;
import me.marquez.variablelink.api.data.BetterVariableMap;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.derewah.skriptanvilgui.SkriptAnvilGUI;
import org.derewah.skriptanvilgui.events.Anvil.BridgeAnvilClick;
import org.derewah.skriptanvilgui.events.Anvil.BridgeAnvilClose;

import java.util.Arrays;

public class Anvil {


    private final AnvilGUI.Builder builder;
    private String title;
    private String text;

    private ItemStack itemLeft;
    private ItemStack itemRight;

    private ItemStack itemOutput;

    @Getter
    private String id;
    @Getter
    private BetterVariableMap data;

    public Anvil() {
        this(null, null);
    }

    public Anvil(String id, BetterVariableMap data) {
        this.id = id;
        this.data = data;
        this.builder = new AnvilGUI.Builder();
        this.text = "Input Text";
        this.title = "Anvil Title";
        builder.text(this.text);
        builder.title(this.title);
        builder.plugin(SkriptAnvilGUI.getInstance());
        builder.onClick((slot, stateSnapshot) -> {
            return Arrays.asList(AnvilGUI.ResponseAction.run(() ->
                    Bukkit.getPluginManager().callEvent(new BridgeAnvilClick(slot, stateSnapshot, this))));
        });
        builder.onClose(stateSnapshot -> {
            Bukkit.getPluginManager().callEvent(new BridgeAnvilClose(stateSnapshot, this));});
    }

    public void setText(String text){
        this.text = text;
        this.builder.text(text);
    }

    public String getText(){
        return this.text;
    }

    public void setTitle(String title){
        this.title = title;
        this.builder.title(title);
    }

    public String getTitle(){
        return this.title;
    }

    public void setItemLeft(ItemStack item){
        this.itemLeft = item;
        this.builder.itemLeft(item);
    }

    public ItemStack getItemLeft(){
        return this.itemLeft;
    }

    public void setItemRight(ItemStack item){
        this.itemRight = item;
        this.builder.itemRight(item);
    }

    public ItemStack getItemRight(){
        return this.itemRight;
    }

    public void setItemOutput(ItemStack item){
        this.itemOutput = item;
        this.builder.itemOutput(item);
    }

    public ItemStack getItemOutput(){
        return this.itemOutput;
    }

    public AnvilGUI.Builder getBuilder(){
        return this.builder;
    }

    public void openAnvil(Player player){
        Bukkit.getScheduler().runTaskLater(SkriptAnvilGUI.getInstance(), () -> getBuilder().open(player), 1);
    }


    @Override
    public String toString() {
        return "anvil gui "
                + (getText() != null ? "with text " + getText() : "")
                + (getTitle() != null ? "with title"  + getTitle() : "");
    }
}
