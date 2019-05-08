package com.learn.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommanderVisitor implements UnitVisitor {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommanderVisitor.class);
    @Override
    public void visitSoldier(Soldier soldier) {
        // DoNothing
    }

    @Override
    public void visitSergrant(Sergeant sergrant) {
        // DoNothing
    }

    @Override
    public void visitCommander(Commander commander) {
        LOGGER.info("Good to see you {}", commander);
    }
}
