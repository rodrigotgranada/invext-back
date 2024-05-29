package com.invext.distribuidor.model;

public class Agent {
    private String id;
    private String name;
    private String team;
    private int currentLoad;

    public Agent(String id, String name, String team) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.currentLoad = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

}
