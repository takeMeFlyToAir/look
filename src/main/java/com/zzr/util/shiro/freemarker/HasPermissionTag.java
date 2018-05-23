package com.zzr.util.shiro.freemarker;

/**
 *
 * @since 0.1
 */
public class HasPermissionTag extends PermissionTag {
    @Override
    protected boolean showTagBody(String p) {
        return isPermitted(p);
    }
}
