package com.conduent.hcesdk.entities;

public class HCERules {

    private String tagName;
    private Rule rule;

    private HCERules() {

    }

    public HCERules(String tagName, Rule rule) {
        this.tagName = tagName;
        this.rule = rule;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
