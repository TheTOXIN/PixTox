package com.toxin.pixtox;

public class Stats {

    public int HP;

    public float hungry;
    public float water;
    public float social;
    public float sleep;

    public Stats() {
        this.HP = 100;
        this.hungry = 1;
        this.water = 1;
        this.social = 1;
        this.sleep = 1;
    }

    @Override
    public String toString() {
        return "Stats{" +
            "HP=" + HP +
            ", hungry=" + hungry +
            ", water=" + water +
            ", social=" + social +
            ", sleep=" + sleep +
            '}';
    }

}
