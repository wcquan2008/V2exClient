package com.github.wcquan.library.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by WCQUAN on 2017-06-01.
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
