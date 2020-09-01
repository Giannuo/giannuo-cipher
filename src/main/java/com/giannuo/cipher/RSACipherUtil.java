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
import java.security.spec.AlgorithmParameterSpec;

/**
 * RSA密码器工具类
 *
 * @since 1.0
 */
public enum RSACipherUtil implements IAsymmetricCipherUtil {
    // （枚举模式）单例
    SINGLETON;

    /**
     * @return KeyGenerator秘钥生成的实际算法
     * @see IAsymmetricCipherUtil#generateBase64Key()
     * @see IAsymmetricCipherUtil#generateBase64Key(int)
     * @see IAsymmetricCipherUtil#generateBase64Key(int, byte[])
     * @see IAsymmetricCipherUtil#generateBase64Key(int, String, Charset)
     * @see IAsymmetricCipherUtil#generateBase64Key(AlgorithmParameterSpec)
     * @see IAsymmetricCipherUtil#generateBase64Key(AlgorithmParameterSpec, byte[])
     * @see IAsymmetricCipherUtil#generateBase64Key(AlgorithmParameterSpec, String, Charset)
     * @since 1.0
     */
    @Override
    final public String getAlgorithm() {
        return CipherConsts.ALGORITHM_RSA;
    }

}
