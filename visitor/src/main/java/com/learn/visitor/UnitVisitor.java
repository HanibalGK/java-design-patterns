package com.learn.visitor;

public interface UnitVisitor {
    void visitSoldier(Soldier soldier);

    void visitSergrant(Sergeant sergrant);

    void visitCommander(Commander commander);
}
