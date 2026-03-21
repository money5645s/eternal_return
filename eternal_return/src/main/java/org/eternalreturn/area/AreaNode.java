package org.eternalreturn.area;

import org.eternalreturn.eranimal.manager.ERAnimalManager;
import org.jetbrains.annotations.NotNull;

public class AreaNode {

    private AreaNode.State zoneState;
    private final String name;
    private ERAnimalManager manager;

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

    public ERAnimalManager getManager(){
        return manager;
    }

    //setter
    public void setZoneState(AreaNode.State state){
        this.zoneState = state;
    }

    public void setZoneAsRed(){
        setZoneState(State.Red);
        //this.manager.remove();
    }

    public void setManager(@NotNull ERAnimalManager manager){
        this.manager = manager;
    }


    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof AreaNode n){
            return n.getName().equals(name);
        }
        return false;
    }

}
