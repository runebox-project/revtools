package io.runebox.revtools.mapper.type;

public enum MatchableKind {
	CLASS, FIELD, METHOD, METHOD_ARG, METHOD_VAR;

	public static final MatchableKind[] VALUES = values();
}
