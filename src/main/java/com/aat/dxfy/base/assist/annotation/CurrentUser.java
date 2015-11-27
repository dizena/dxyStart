package com.aat.dxfy.base.assist.annotation;
import java.lang.annotation.*;

import com.aat.dxfy.base.CommonConstant;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
	String value() default CommonConstant.CURRENT_USER;

}