package com.github.cse232b.subexpr;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringF implements SubExpression {
    private final SubType subType;
    private final SubExpression f;
    private final String s;

    public StringF(SubType subType, SubExpression f, String s) {
        if (subType == null) {
            throw new NullPointerException("subType in StringF missing " + this);
        }
        if (f == null) {
            throw new NullPointerException("f in StringF missing " + this);
        }
        if (s == null) {
            throw new NullPointerException("string in StringF missing " + this);
        }
        this.subType = subType;
        assert(this.subType == SubType.RpEqualStringF);
        this.f = f;
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringF stringF = (StringF) o;
        return subType == stringF.subType && f.equals(stringF.f) && s.equals(stringF.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subType, f, s);
    }

    @Override
    public String toString() {
        return "StringFt{" +
                "subType=" + subType +
                ", f=" + f +
                ", s='" + s + '\'' +
                '}';
    }

    @Override
    public SubType getExprType() {
        return this.subType;
    }

    @Override
    public List<Node> eval(List<Node> input) throws Exception {
        List<Node> res = new ArrayList<>();
        for (Node node : input) {
            List<Node> evalResult = this.f.eval(List.of(node));
            for (Node n : evalResult) {
                if (n.getNodeValue().equals(this.s)) {
                    res.add(n);
                }
            }
        }
        return res;
    }
}