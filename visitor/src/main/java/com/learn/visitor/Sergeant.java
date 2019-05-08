package com.learn.visitor;

public class Sergeant extends Unit{
    public Sergeant(Unit... children) {
        super(children);
    }

    @Override
    public void accept(UnitVisitor visitor) {
        visitor.visitSergrant(this);
        super.accept(visitor);
    }

    @Override
    public String toString() {
        return "Sergeant";
    }
}
