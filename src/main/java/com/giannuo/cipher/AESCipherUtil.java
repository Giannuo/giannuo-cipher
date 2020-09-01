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
package com.giannuo.cipher;

import java.nio.charset.Charset;

/**
 * AES密码器工具类
 *
 * @since 1.0
 */
public enum AESCipherUtil implements ISymmetricCipherUtil {
    // （枚举模式）单例
    SINGLETON;

    /**
     * @return KeyGenerator秘钥生成的实际算法
     * @see ISymmetricCipherUtil#generateBase64Key()
     * @see ISymmetricCipherUtil#generateBase64Key(int)
     * @see ISymmetricCipherUtil#generateBase64Key(byte[])
     * @see ISymmetricCipherUtil#generateBase64Key(String, Charset)
     * @since 1.0
     */
    @Override
    final public String getKeyGeneratorAlgorithm() {
        return CipherConsts.ALGORITHM_AES;
    }
}
