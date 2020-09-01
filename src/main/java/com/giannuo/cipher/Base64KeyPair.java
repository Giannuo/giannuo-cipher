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


import java.security.KeyPair;
import java.util.Base64;

/**
 * Base64秘钥对，提供非对称加密秘钥对封装
 *
 * @since 1.0
 */
public final class Base64KeyPair {
    /**
     * Base64公钥
     *
     * @since 1.0
     */
    private final String base64PublicKey;
    /**
     * Base64私钥
     *
     * @since 1.0
     */
    private final String base64PrivateKey;

    /**
     * Base64秘钥对（构造）
     *
     * @param base64PublicKey  Base64公钥
     * @param base64PrivateKey Base64私钥
     * @since 1.0
     */
    public Base64KeyPair(String base64PublicKey, String base64PrivateKey) {
        this.base64PublicKey = base64PublicKey;
        this.base64PrivateKey = base64PrivateKey;
    }

    /**
     * Base64秘钥对（构造）
     *
     * @param keyPair 秘钥对{@code java.security.KeyPair}
     * @since 1.0
     */
    public Base64KeyPair(KeyPair keyPair) {
        this(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()), Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
    }

    /**
     * @return Base64公钥 String
     * @since 1.0
     */
    public String getBase64PublicKey() {
        return base64PublicKey;
    }

    /**
     * @return Base64私钥 String
     * @since 1.0
     */
    public String getBase64PrivateKey() {
        return base64PrivateKey;
    }

    /**
     * 转换秘钥对
     *
     * @param keyPair KeyPair秘钥对
     * @return Base64KeyPair Base64秘钥对
     * @since 1.0
     */
    public static Base64KeyPair of(KeyPair keyPair) {
        return new Base64KeyPair(keyPair);
    }
}
