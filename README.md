# 密码工具库（giannuo-cipher）
## 1. 它是一个密码工具库，包含了常用的AES、DES、DESede、RSA等算法
# 密码工具库（giannuo-cipher）
## 2. 它基于JDK8
## 3. 它封装了AES加密/解密（采用默认`AES/CBC/PKCS5Padding`的算法/工作模式/填充方式）
你可以这么用
```
--生成AES秘钥
AESCipherUtil.SINGLETON.generateBase64Key()
AESCipherUtil.SINGLETON.generateBase64Key(int)
AESCipherUtil.SINGLETON.generateBase64Key(byte[])
AESCipherUtil.SINGLETON.generateBase64Key(String, Charset)
--加密
AESCipherUtil.SINGLETON.encrypt(String, AESEncrypt)
AESCipherUtil.SINGLETON.encryptFile(String, String, AESEncrypt)
--解密
AESCipherUtil.SINGLETON.decrypt(String, AESDecrypt)
AESCipherUtil.SINGLETON.decryptFile(String, String, AESDecrypt)
```
## 4. 它封装了DES加密/解密（采用默认`DES/CBC/PKCS5Padding`的算法/工作模式/填充方式）
你可以这么用
```
--生成DES秘钥
DESCipherUtil.SINGLETON.generateBase64Key()
DESCipherUtil.SINGLETON.generateBase64Key(int)
DESCipherUtil.SINGLETON.generateBase64Key(byte[])
DESCipherUtil.SINGLETON.generateBase64Key(String, Charset)
--加密
DESCipherUtil.SINGLETON.encrypt(String, DESEncrypt)
DESCipherUtil.SINGLETON.encryptFile(String, String, DESEncrypt)
--解密
DESCipherUtil.SINGLETON.decrypt(String, DESDecrypt)
DESCipherUtil.SINGLETON.decryptFile(String, String, DESDecrypt)
```
## 5. 它封装了DESede(也可以叫3DES)加密/解密（采用默认`DESede/CBC/PKCS5Padding`的算法/工作模式/填充方式）
你可以这么用
```
--生成DESede秘钥
DESedeCipherUtil.SINGLETON.generateBase64Key()
DESedeCipherUtil.SINGLETON.generateBase64Key(int)
DESedeCipherUtil.SINGLETON.generateBase64Key(byte[])
DESedeCipherUtil.SINGLETON.generateBase64Key(String, Charset)
--加密
DESedeCipherUtil.SINGLETON.encrypt(String, DESedeEncrypt)
DESedeCipherUtil.SINGLETON.encryptFile(String, String, DESedeEncrypt)
--解密
DESedeCipherUtil.SINGLETON.decrypt(String, DESedeDecrypt)
DESedeCipherUtil.SINGLETON.decryptFile(String, String, DESedeDecrypt)
```
## 6. 它封装了RSA加密/解密
你可以这么用
```
--生成RSA秘钥
RSACipherUtil.SINGLETON.generateBase64Key()
RSACipherUtil.SINGLETON.generateBase64Key(int)
RSACipherUtil.SINGLETON.generateBase64Key(int, byte[])
RSACipherUtil.SINGLETON.generateBase64Key(int, String, Charset)
RSACipherUtil.SINGLETON.generateBase64Key(AlgorithmParameterSpec)
RSACipherUtil.SINGLETON.generateBase64Key(AlgorithmParameterSpec, byte[])
RSACipherUtil.SINGLETON.generateBase64Key(AlgorithmParameterSpec, String, Charset)
--加密
RSACipherUtil.SINGLETON.encrypt(String, RSAEncrypt)
--解密
RSACipherUtil.SINGLETON.decrypt(String, RSADecrypt)
```
## 7. 它封装了RSA签名/验签（采用默认SHA512withRSA算法）
你可以这么用
```
--签名
RSASignUtil.SINGLETON.sign(String, String, Charset)
--解密
RSASignUtil.SINGLETON.verify(String, String, String, Charset)
```
## 8. 它封装了MD5和SHA-1的摘要算法
你可以这么用
```
--MD5文件摘要
MD5Util.SINGLETON.fileDigest(File)
MD5Util.SINGLETON.fileDigest(String)
--解密
SHAUtil.SINGLETON.fileDigest(File)
SHAUtil.SINGLETON.fileDigest(String)
```
