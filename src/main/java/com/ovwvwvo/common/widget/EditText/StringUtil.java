/**
 * Copyright © 2016 by ovwvwvo
 */

package com.ovwvwvo.common.widget.EditText;

import android.support.annotation.Nullable;

class StringUtil {

    static boolean isEmpty(@Nullable CharSequence str) {
        return (str == null || str.length() == 0);
    }

    static boolean isBlank(String str) {
        return isEmpty(str) || isEmpty(str.trim());
    }

    static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
