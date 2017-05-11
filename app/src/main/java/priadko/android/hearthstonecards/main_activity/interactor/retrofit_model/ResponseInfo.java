package priadko.android.hearthstonecards.main_activity.interactor.retrofit_model;

import java.util.Set;

public class ResponseInfo {

    private String patch = null;
    private Set<String> classes = null;
    private Set<String> sets = null;
    private Set<String> standard = null;
    private Set<String> wild = null;
    private Set<String> types = null;
    private Set<String> factions = null;
    private Set<String> qualities = null;
    private Set<String> races = null;
    private Set<String> locales = null;

    public String getPatch() {
        return patch;
    }

    public Set<String> getClasses() {
        return classes;
    }

    public Set<String> getSets() {
        return sets;
    }

    public Set<String> getStandard() {
        return standard;
    }

    public Set<String> getWild() {
        return wild;
    }

    public Set<String> getTypes() {
        return types;
    }

    public Set<String> getFactions() {
        return factions;
    }

    public Set<String> getQualities() {
        return qualities;
    }

    public Set<String> getRaces() {
        return races;
    }

    public Set<String> getLocales() {
        return locales;
    }
}