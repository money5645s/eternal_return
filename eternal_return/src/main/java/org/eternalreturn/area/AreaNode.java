package org.eternalreturn.area;

import org.eternalreturn.system.SystemManager;

import java.util.List;


public class AreaNode {

    private AreaNode.State zoneState;
    private String name;

    public AreaNode(String name){
        this.zoneState = AreaNode.State.Green;
        this.name = name;
    }

    public enum State{
        Green,
        Yellow,
        Red
    }

    //getter
    public AreaNode.State getZoneState(){
        return zoneState;
    }

    public String getName(){
        return name;
    }

    //setter
    public void setZoneState(AreaNode.State state){
        this.zoneState = state;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
