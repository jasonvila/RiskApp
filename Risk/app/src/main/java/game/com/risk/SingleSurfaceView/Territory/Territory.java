package game.com.risk.SingleSurfaceView.Territory;

import java.util.HashMap;
import java.util.Set;

import game.com.risk.SingleSurfaceView.Constants.GameConstants;
import game.com.risk.SingleSurfaceView.Territory.Shape.TerritoryShape;
import game.com.risk.SingleSurfaceView.Territory.Shape.TerritoryShapeCircle;
import game.com.risk.SingleSurfaceView.Territory.Shape.TerritoryShapeCircleHighlight;

/**
 * Created by jason on 5/7/2017.
 */

public class Territory {

    private static final String TAG = "Territory";

    private TerritoryShape s;
    private TerritoryShape h;
    private HashMap<Integer, Long> tMilitary = new HashMap<Integer, Long>();
    private HashMap<Integer, Long> tBuildings = new HashMap<Integer, Long>();
    private Integer tTypes;
    private Boolean isCapital;
    private Boolean isSelected;
    private Integer team;

    public Territory(float[] args)throws Exception {

        team = 0;
        isCapital = false;
        s = new TerritoryShapeCircle(args);
        h = new TerritoryShapeCircleHighlight(args);
        tTypes = GameConstants.GAME_CONSTANTS.numTTypes;
        isSelected = false;

        for(int i = 0; i < tTypes; i++){
            tMilitary.put(i, 0L);
        }
    }

    public TerritoryShape getTerritoryShape(){
        return s;
    }

    public void draw(float[] m){
        s.draw(m);
        if(isSelected){
            h.draw(m);
        }
    }

    public void setCapital(Boolean isCapital){
        this.isCapital = isCapital;
    }

    public void changeTeams(Integer team){
        this.team = team;
        Set<Integer> m = tMilitary.keySet();
        for(Integer t: m){
            tMilitary.put(t, 0L);
        }

        m = tBuildings.keySet();
        for(Integer t: m){
            tMilitary.put(t, 0L);
        }

        s.setColor(GameConstants.GAME_CONSTANTS.getTeamColor(team));
    }

    public Boolean setIsSelected(){
        if(isSelected){
            isSelected = false;
        } else{
            isSelected = true;
        }
        return isSelected;
    }

    public Boolean getIsSelected(){
        return isSelected;
    }

    public void addTroopType(Integer tType, Long numTroops){
        tMilitary.put(tType, tMilitary.get(tType) + numTroops);
    }

    public void addBuildingType(Integer bType, Long numBuildings){
        tBuildings.put(bType, tBuildings.get(bType) + numBuildings);
    }

    public void moveShape(float x, float y){

    }
}
