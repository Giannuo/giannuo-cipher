/*
 * Copyright 2020 姚嘉乐（Giannuo）
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.giannuo.util;

/**
 * 断言工具
 * <ul>
 *     <li>参考：{@code org.springframework.util.Assert}</li>
 *     <li>目的：去Jar包依赖</li>
 * </ul>
 *
 * @since 1.0
 */
public class Assert {

    /**
     * 表达式`expression`如果为false，抛出异常消息
     *
     * @param expression 表达式
     * @param message    异常消息
     * @since 1.0
     */
    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new RuntimeException(message);
        }
    }

    /**
     * @param exception 异常
     * @return 运行时异常
     * @since 1.0
     */
    public static RuntimeException toRuntimeException(Exception exception) {
        return new RuntimeException(exception);
    }

    /**
     * @param message 异常消息
     * @return 运行时异常
     * @since 1.0
     */
    public static RuntimeException toRuntimeException(String message) {
        return new RuntimeException(message);
    }

    /**
     * 转换成运行时异常
     *
     * @param exception 异常
     * @param message   异常消息
     * @return 运行时异常
     * @since 1.0
     */
    public static RuntimeException toRuntimeException(Exception exception, String message) {
        return new RuntimeException(message + "[" + exception.getClass().getSimpleName() + "]", exception);
    }

}
