package game.com.risk.Constants;

import android.content.Context;
import android.util.TypedValue;

import java.util.HashMap;

import game.com.risk.R;


/**
 * Created by jason on 5/7/2017.
 */

public class GameConstants {

    private static final String TAG = "GameConstants";
    public static final GameConstants GAME_CONSTANTS = new GameConstants();

    public HashMap<String, Integer> tTypes = new HashMap<String, Integer>();
    public Integer numTTypes;

    public HashMap<String, Integer> bTypes = new HashMap<String, Integer>();
    public Integer numBTypes;
    public Integer width, height;
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
        width = 1920;
        height = 928;
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
            String typeValue = c.getResources().getString(R.string.typeValue);
            String nameValue = c.getResources().getString(R.string.nameValue);
            String separators = c.getResources().getString(R.string.separators);

            String tType = tPreName + separators + typeValue + separators;
            String tNames = tPreName + separators + nameValue + separators;
            String bType = bPreName + separators + typeValue + separators;
            String bNames = bPreName + separators + nameValue + separators;

            tTypes.clear();
            bTypes.clear();
            Integer value = 0;
            String name = "";

            for(int i = 0; i < numTTypes; i++){
                value = c.getResources().getInteger(c.getResources().getIdentifier(tType + i, "integer", c.getPackageName()));
                name = c.getResources().getString(c.getResources().getIdentifier(tNames + i, "string", c.getPackageName()));
                tTypes.put(name, value);
            }

            for(int i = 0; i < numBTypes; i++){
                value = c.getResources().getInteger(c.getResources().getIdentifier(bType + i, "integer", c.getPackageName()));
                name = c.getResources().getString(c.getResources().getIdentifier(bNames + i, "string", c.getPackageName()));
                tTypes.put(name, value);
            }
        }
    }

    public float[] getTeamColor(Integer team){
        float[] color = new float[4];

        TypedValue outValue = new TypedValue();
        c.getResources().getValue(c.getResources().getIdentifier("team_" + team + "_color_r", "dimen", c.getPackageName()), outValue, true);
        float value = outValue.getFloat();

        color[0] = value;

        c.getResources().getValue(c.getResources().getIdentifier("team_" + team + "_color_g", "dimen", c.getPackageName()), outValue, true);
        value = outValue.getFloat();

        color[1] = value;

        c.getResources().getValue(c.getResources().getIdentifier("team_" + team + "_color_b", "dimen", c.getPackageName()), outValue, true);
        value = outValue.getFloat();

        color[2] = value;
        color[3] = 0.8f;

        return color;
    }

    public void updateGameDimensions(Integer width, Integer height){
        if(width != null && height != null){
            this.width = width;
            this.height = height;
        }
    }

}
