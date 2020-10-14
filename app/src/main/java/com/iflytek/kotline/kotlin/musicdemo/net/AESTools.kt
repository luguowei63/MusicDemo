package com.iflytek.kotline.kotlin.musicdemo.net

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESTools {

    companion object {
        private const val INPUT = "2012171402992850"
        private const val IV = "2012061402992850"
        private val CHARS = charArrayOf(
            48.toChar(),
            49.toChar(),
            50.toChar(),
            51.toChar(),
            52.toChar(),
            53.toChar(),
            54.toChar(),
            55.toChar(),
            56.toChar(),
            57.toChar(),
            65.toChar(),
            66.toChar(),
            67.toChar(),
            68.toChar(),
            69.toChar(),
            70.toChar()
        )

        fun encrpty(paramString: String): String{

            var  messageDigest : MessageDigest? =null
            try {
                messageDigest= MessageDigest.getInstance("MD5")
            }catch (e: NoSuchAlgorithmException){
                e.printStackTrace()
            }
            messageDigest?.update(INPUT.toByteArray())
            val stringBytes = messageDigest?.digest()
            val stringBuilder = StringBuilder(stringBytes!!.size * 2)
            for (i in stringBytes!!.indices) {
                stringBuilder.append(CHARS[stringBytes!![i] and 0xF0 ushr 4])
                stringBuilder.append(CHARS[stringBytes!![i] and 0xF])
            }
            val str = stringBuilder.toString()
            val localSecretKeySpec = SecretKeySpec(
                str.substring(str.length / 2)
                    .toByteArray(), "AES"
            )
            val localCipher: Cipher
            try {
                localCipher = Cipher
                    .getInstance("AES/CBC/PKCS5Padding")
                localCipher.init(
                    1, localSecretKeySpec,
                    IvParameterSpec(IV.toByteArray())
                )
                return URLEncoder.encode(
                    String(
                        BytesHandler.getChars(
                            localCipher
                                .doFinal(paramString.toByteArray())
                        )
                    ),
                    "utf-8"
                )
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: NoSuchPaddingException) {
                e.printStackTrace()
            } catch (e: InvalidKeyException) {
                e.printStackTrace()
            } catch (e: InvalidAlgorithmParameterException) {
                e.printStackTrace()
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            } catch (e: IllegalBlockSizeException) {
                e.printStackTrace()
            } catch (e: BadPaddingException) {
                e.printStackTrace()
            }
            return ""

        }










    }


}