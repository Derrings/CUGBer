package edu.cugb.jts.cugber.system.devops;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Enable users a dynamic way to manage system functional section.
 * If you want to add a new functional section, just call addFunctionalSection,
 * you must provide the url prefix like 'blog'.
 * If an functional section which url prefix is 'blog' is forbidden, it means
 * all request processing to '\blog' will be intercept by SystemInterceptor unless
 * system administrator enable the functional section.
 */
public class SystemFunctionStatusManager {
    private final Map<String, Boolean> systemFunctionStatusMapping = new HashMap<>();

    public void addFunctionalSection(String urlPrefix) {
        addFunctionalSection(urlPrefix, true);
    }

    public void addFunctionalSection(String urlPrefix, boolean status) {
        systemFunctionStatusMapping.put(urlPrefix, status);
    }

    public boolean checkUrlAccess(String url) {
        for (Map.Entry<String, Boolean> entry : systemFunctionStatusMapping.entrySet()) {
            if (url.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return true;
    }

    private boolean changeFunctionAccess(String prefix, boolean status) {
        if (systemFunctionStatusMapping.containsKey(prefix)) {
            systemFunctionStatusMapping.replace(prefix, status);
            return true;
        }
        return false;
    }
    public boolean enableFunction(String prefix) {
        return changeFunctionAccess(prefix, true);
    }
    public boolean disableFunction(String prefix) {
        return changeFunctionAccess(prefix, false);
    }
}