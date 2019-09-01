package lanyao.springsecurity.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lanyao.springsecurity.oauth.SystemConstant;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommonService {



    @Autowired
    Base32 base32;

    public  byte[] encodeBase32Byte(byte[] str){
        return base32.encode(str);
    }

    public  String encodeBase32(byte[] str){
        return base32.encodeAsString(str);
    }

    public  byte[] decodeBase32(String encodeStr){
        return base32.decode(encodeStr);
    }

    public  String decodeBase32Str(String encodeStr) throws UnsupportedEncodingException {
        return new String(base32.decode(encodeStr),SystemConstant.encode_UTF_8.getValue());
    }

    public byte[] strToHex(String str) throws DecoderException {
        return Hex.decodeHex(str);
    }
    public String hexToStr(byte[] byteHex){
        return String.valueOf(Hex.encodeHex(byteHex));
    }

    public byte[] hexToByte(byte[] byteHex) throws UnsupportedEncodingException {
        return String.valueOf(Hex.encodeHex(byteHex)).getBytes(SystemConstant.encode_UTF_8.getValue());
    }

    public String urlEncode(String encodeStr) throws UnsupportedEncodingException {
        return URLEncoder.encode(encodeStr, SystemConstant.encode_UTF_8.getValue());
    }

    public String urlDecode(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str, SystemConstant.encode_UTF_8.getValue());
    }





    public void printJson(Object object) {
        String formattedData= null;
        try {
            formattedData = new ObjectMapper().writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("------");
        System.out.println(formattedData);
        System.out.println("------");
    }






    public String convertDate(LocalDateTime localDateTime){

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String format1 = localDateTime.format(format);


        System.out.println("format1format1:" + format1);

        return format1;
    }







}
