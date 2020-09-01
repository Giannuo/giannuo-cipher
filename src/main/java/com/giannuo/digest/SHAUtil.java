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
package com.giannuo.digest;

/**
 * SHA-1算法工具类单例
 *
 * @see sun.security.provider.SHA
 * @since 1.0
 */
public enum SHAUtil implements IDigest {
    SINGLETON;
    final String ALGORITHM = "SHA-1";

    /**
     * @return 摘要计算算法
     * @since 1.0
     */
    @Override
    public String getAlgorithm() {
        return ALGORITHM;
    }
}
