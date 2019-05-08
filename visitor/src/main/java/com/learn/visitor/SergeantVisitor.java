package com.learn.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SergeantVisitor implements UnitVisitor {
    private final static Logger LOGGER = LoggerFactory.getLogger(Sergeant.class);
    @Override
    public void visitSoldier(Soldier soldier) {
        // DoNothing
    }

    @Override
    public void visitSergrant(Sergeant sergrant) {
        LOGGER.info("Hello: {}", sergrant);
    }

    @Override
    public void visitCommander(Commander commander) {
        // DoNothing
    }
}
