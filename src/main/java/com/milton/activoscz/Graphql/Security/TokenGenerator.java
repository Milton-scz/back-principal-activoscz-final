package com.milton.activoscz.Graphql.Security;

public interface TokenGenerator {
    String build(Object id, Object role);
}