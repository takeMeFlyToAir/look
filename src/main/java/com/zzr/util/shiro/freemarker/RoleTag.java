package com.zzr.util.shiro.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 */
public abstract class RoleTag extends SecureTag {
    String getName(Map<String, Object> params) {
        return getParam(params, "name");
    }

    @Override
    public void render(Environment env, Map<String, Object> params, TemplateDirectiveBody body) throws IOException, TemplateException {
        boolean show = showTagBody(getName(params));
        if (show) {
            renderBody(env, body);
        }
    }

    protected abstract boolean showTagBody(String roleName);
}