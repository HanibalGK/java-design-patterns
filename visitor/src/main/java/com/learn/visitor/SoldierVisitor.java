package com.learn.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoldierVisitor implements UnitVisitor{
    private final static Logger LOGGER = LoggerFactory.getLogger(SoldierVisitor.class);
    @Override
    public void visitSoldier(Soldier soldier) {
        LOGGER.info("Greetings {}", soldier);
    }

    @Override
    public void visitSergrant(Sergeant sergrant) {
        // DoNothing
    }

    @Override
    public void visitCommander(Commander commander) {
        // DoNothing
    }
}
