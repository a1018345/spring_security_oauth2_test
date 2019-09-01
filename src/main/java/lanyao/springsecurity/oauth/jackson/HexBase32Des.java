package lanyao.springsecurity.oauth.jackson;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lanyao.springsecurity.oauth.service.CommonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


@Log4j2
public class HexBase32Des extends JsonDeserializer<byte[]> {

//    public HexBase32Des(){
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//    }


    @Autowired
    CommonService commonService;

    @Override
    public byte[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        String base32Uuid=node.get("uuid").asText();
        return commonService.decodeBase32(base32Uuid);
    }


}
