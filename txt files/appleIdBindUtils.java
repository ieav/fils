
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwk.InvalidPublicKeyException;
import com.auth0.jwk.Jwk;
import com.msyd.wireless.base.util.HttpUtils;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

public class appleIdBindUtils {

    private static Logger log = LoggerFactory.getLogger(appleIdBindUtils.class);

    /**
     * 长整型ip转换为string
     *
     * @param long型ip
     * @return
     */
    public static boolean checkVerCode(HttpServletRequest request, String verCode) {
        Object rand = request.getSession().getAttribute("rand");
        if (rand != null && verCode != null
                && verCode.toLowerCase().equals(request.getSession().getAttribute("rand").toString().toLowerCase())) {
            return true;
        }
        return false;
    }


    /**
     * 验证苹果公钥
     * @param key
     * @param jwt
     * @param audience
     * @param subject
     * @return
     */
    public static Boolean verify(PublicKey key, String jwt, String audience, String subject) {
        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        jwtParser.requireIssuer("https://appleid.apple.com");
        jwtParser.requireAudience(audience);
        jwtParser.requireSubject(subject);
        // 生成苹果公钥
        try {
            Jws<Claims> claim = jwtParser.parseClaimsJws(jwt);
            log.info(" 验证苹果公钥 结果 ::"+claim.toString());
            if (claim != null && claim.getBody().containsKey("auth_time")) {
                log.info(" =============== 校验 苹果 identityToken  成功！");
                return true; //"SUCCESS"
            }
            return false;//"FAIL"
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("苹果token过期", e);
        } catch (Exception e) {
            throw new RuntimeException("苹果token非法", e);
        }
    }

    /**
     * 校验 苹果  tokens
     * @param identityToken
     * @return
     */
    public static Boolean appleAuth(String jwt) {
        log.info(" =============== 校验 苹果 identityToken 开始");
//
//        String httpClientResult = null;
//        try {
//            httpClientResult = HttpUtils.URLGet("https://appleid.apple.com/auth/keys",null,"utf-8"); // 获取公钥
//        } catch (Exception e) {
//            throw new RuntimeException("向苹果发送请求获取公钥失败", e);
//        }
//        JSONObject jsonObject = JSONObject.parseObject(httpClientResult);
//        String keys = jsonObject.getString("keys");
//        JSONArray arr = JSONObject.parseArray(keys);
//        JSONObject jsonObject1 = JSONObject.parseObject(arr.getString(0));
//        Jwk jwa = Jwk.fromValues(jsonObject1);
        // 生成苹果公钥

//            String hearder = new String(Base64.decodeBase64(jwt.split("\\.")[0]));
            if (jwt.split("\\.").length > 1) {

                String kids = new String(Base64.decodeBase64(jwt.split("\\.")[0]));
                String claim = new String(Base64.decodeBase64(jwt.split("\\.")[1]));
                log.info("kids:"+kids);
                log.info("claim:"+claim);
                JSONObject ko=JSONObject.parseObject(kids);
                JSONObject jo=JSONObject.parseObject(claim);

                String kid  = ko.get("kid").toString();
                String aud = jo.get("aud").toString();
                String sub = jo.get("sub").toString();

                PublicKey publicKey = getPublicKey(kid);
                return verify(publicKey, jwt, aud, sub);
            }
            log.info(" =============== 校验 苹果 identityToken 失败！");
            return false;//"FAIL";
    }


    /**
     * 根据kid生成公钥
     *
     * @param kid
     * @return 构造好的公钥
     */
    private static PublicKey getPublicKey(String kid) {
        try {
            String str =  HttpUtils.URLGet("https://appleid.apple.com/auth/keys",null,"utf-8");
            JSONObject data = JSONObject.parseObject(str);
            JSONArray keysJsonArray = data.getJSONArray("keys");
            String n = "";
            String e = "";
            for (int i = 0; i < keysJsonArray.size(); i++) {
                JSONObject jsonObject = keysJsonArray.getJSONObject(i);
                if (StringUtils.equals(jsonObject.getString("kid"), kid)) {
                    n = jsonObject.getString("n");
                    e = jsonObject.getString("e");
                }
            }
            final BigInteger modulus = new BigInteger(1, Base64.decodeBase64(n));
            final BigInteger publicExponent = new BigInteger(1, Base64.decodeBase64(e));

            final RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
            final KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(spec);
        } catch (final Exception e) {
            log.error("AppleController.getPublicKey error:{}", e);
        }
        return null;
    }

}

