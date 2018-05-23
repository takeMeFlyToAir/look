package com.zzr.util.shiro.freemarker;

/**
 */
public class HasRoleTag extends RoleTag {
    @Override
    protected boolean showTagBody(String roleName) {
        return getSubject() != null && getSubject().hasRole(roleName);
    }
}
