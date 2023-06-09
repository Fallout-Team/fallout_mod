package net.Monsterwaill.falloutmod.data;

import net.Monsterwaill.falloutmod.FalloutMod;

public class FalloutConstants {

    public static String PIPBOY_USAGE = makeMessage("pipboy_usage");
    public static String ITEM_SHIFT = makeMessage("item_shift");
    public static String NUMBER = makeMessage("number");
    public static String PIPBOY_SCREEN = makeTitle("Pip-Boy 2500");

    public static String makeMessage(String id){
        return "message." + FalloutMod.MOD_ID + "." + id;
    }

    public static String makeTitle(String id) {
        return "screen." + FalloutMod.MOD_ID + "." + id;
    }

}
