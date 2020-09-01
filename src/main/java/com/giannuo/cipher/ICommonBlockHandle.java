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

import com.giannuo.util.Assert;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;

/**
 * 块处理公共接口
 *
 * @since 1.0
 */
public interface ICommonBlockHandle {
    /**
     * 【(RSA)加密|解密】分块处理
     *
     * @param cipher  密码处理器
     * @param opmode  cipher处理类型 {@code Cipher.DECRYPT_MODE}|{@code Cipher.ENCRYPT_MODE}
     * @param datas   待处理数据
     * @param keySize 密码长度
     * @return 【(RSA)加密|解密】分块处理过的数据
     * @since 1.0
     */
    default byte[] rsaBlockCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        final int MAX_BLOCK;
        if (Cipher.DECRYPT_MODE == opmode) {
            MAX_BLOCK = keySize / 8;
        } else if (Cipher.ENCRYPT_MODE == opmode) {
            /*
             参考：https://tools.ietf.org/html/rfc2313#section-8
             其中k指：length of modulus in octets，即此处的kesize/8
             */
            MAX_BLOCK = keySize / 8 - 11;
        } else {
            throw new RuntimeException("不支持的Cipher处理类型，expected >> Cipher.DECRYPT_MODE | Cipher.ENCRYPT_MODE <<");
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int offSet = 0;
            byte[] buff;
            int i = 0;
            while (datas.length > offSet) {
                if (datas.length - offSet > MAX_BLOCK) {
                    buff = cipher.doFinal(datas, offSet, MAX_BLOCK);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * MAX_BLOCK;
            }
            return out.toByteArray();
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "块处理阈值为[" + MAX_BLOCK + "]，加解密数据时发生异常");
        }
    }
}
