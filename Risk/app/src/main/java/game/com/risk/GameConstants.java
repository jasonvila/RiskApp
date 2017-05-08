package game.com.risk;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by jason on 5/7/2017.
 */

public class GameConstants {

    public static final GameConstants GAME_CONSTANTS = new GameConstants();

    public HashMap<String, Integer> tTypes = new HashMap<String, Integer>();
    public Integer numTTypes;

    public HashMap<String, Integer> bTypes = new HashMap<String, Integer>();
    public Integer numBTypes;
    private Context c;

    private GameConstants(){
        tTypes.put("Soldiers", 1);
        tTypes.put("Cavalry", 5);
        tTypes.put("Artillery", 10);
        bTypes.put("Factory", 0);
        bTypes.put("Cities", 1);
        bTypes.put("Military_Bases", 2);
        numTTypes = tTypes.keySet().size();
        numBTypes = bTypes.keySet().size();
    }

    public void setContext(Context c){
        this.c = c;
    }

    public void updateMappings(){
        if(c != null){
            Integer numTTypes = c.getResources().getInteger(R.integer.numTTypes);
            Integer numBTypes = c.getResources().getInteger(R.integer.numBTypes);
            String tPreName = c.getResources().getString(R.string.tPreName);
            String bPreName = c.getResources().getString(R.string.bPreName);

        }
    }


}
