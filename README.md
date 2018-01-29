# Notes-Prashul-Shrestha-Mobile-Application
An Android Mobile Application Developed by Prashul Shrestha


: I rebuilt this application as a part of our final project in Saving File in Internal Storage in 
  Software Engineering Course - Intoduction to Mobile Applications SE 275.
 
: We were assgined our final porject on Encryption and Decryption. We were given the code of encytping a String and Decypting it which uses Key of String in encryption and uses the same key in Descyption.

: I thought I would reuse the code of Encyption to built a NOte Saving application in the Internal Storage. So, therefor in this application
  the text files are taken from the user and stored in a serilizable objects of Note Java Class and send it to the Encrypt Class and then send it a SaveLoad Class
  to save it in the Internal Storage in a Encrypted form. 
  
: The main activity uses List Adapter to Display out the Notes saved in the Storage.


EncryptDecryupt Code:

Skip to content
This repository
Search
Pull requests
Issues
Marketplace
Explore
 @pradhulstha
 Sign out
 Watch 0
  Star 0  Fork 0 pradhulstha/Notes-Prashul-Shrestha-Mobile-Application
 Code  Issues 0  Pull requests 0  Projects 0  Wiki  Insights  Settings
Branch: master Find file Copy pathNotes-Prashul-Shrestha-Mobile-Application/app/src/main/java/com/example/prash/notes/EncyprDecrypt.java
d3ec79f  15 days ago
@pradhulstha pradhulstha Notes Mobile Application
1 contributor
RawBlameHistory     
64 lines (53 sloc)  2.23 KB
package com.example.prash.notes;

import android.util.Log;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// Encrypt and decrypt strings.
public class EncyprDecrypt {
    // note: key should be 16 ASCII characters which will create 128 bit key

    public String encrypt (String unencryptedText, String key) {
        String encryptedText = "";
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(unencryptedText.getBytes());

            // Convert from byte array to hex StringBuilder
            StringBuilder sb = new StringBuilder();
            for (byte b : encrypted) {
                sb.append(String.format("%02X", b));
            }

            encryptedText = sb.toString();
            Log.d("Message", "Encrypted text: " + encryptedText);
        } catch (Exception e) {
            Log.d("Message", "In exception" + e);
        }
        return encryptedText;
    } // end method encrypt

    public String decrypt(String encryptedText, String key) {
        String decryptedText = "";

        // call helper function included in this class
        byte[] encrypted = hexStringToByteArray(encryptedText);

        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decryptedText = new String(cipher.doFinal(encrypted));
            Log.d("Message", "Decrypted message: " + decryptedText);
        } catch (Exception e) {
            Log.d("Message", "In exception" + e);
        }
        return decryptedText;
    } // end method decrypt

    // Helper function to convert hex string to a byte array.
    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    } // end helper function

} // end class EncryptDecrypt
