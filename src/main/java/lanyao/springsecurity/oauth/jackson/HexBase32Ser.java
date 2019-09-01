package lanyao.springsecurity.oauth.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lanyao.springsecurity.oauth.SystemConstant;
import lanyao.springsecurity.oauth.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

public class HexBase32Ser extends JsonSerializer<byte[]> {

    public HexBase32Ser(){
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Autowired
    CommonService commonService;

    @Override
    public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(commonService.encodeBase32(commonService.hexToStr(value).getBytes(SystemConstant.encode_UTF_8.getValue())));
    }















}
