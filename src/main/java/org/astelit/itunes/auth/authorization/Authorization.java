package org.astelit.itunes.auth.authorization;

import java.util.Collection;

public interface Authorization {
    long getId();

    String getLogin();

    Collection<String> getRoles();

    Collection<String> getActions();

    Collection<String> getGroups();
}
