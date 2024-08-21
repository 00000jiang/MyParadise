package com.paradise.gateway.header;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.paradise.gateway.utils.JWTUitls;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * @author jrf
 * @date 2023-3-30 13:13
 */
@Component
public class JWTInterceptor implements GlobalFilter{

    private final static List<String> path = new ArrayList<>(20);

    static {
        path.add("/login");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求对象和响应对象
        ServerHttpRequest request = exchange.getRequest();
        HashMap<String,Object> map = new HashMap<>();
        List<PathContainer.Element> elements = request.getPath().elements();
        String url = elements.get(0).value() + elements.get(1).value();
        //验证白名单
        if(path.contains(url)){
            return chain.filter(exchange);
        }
        try {
            HttpHeaders headers = request.getHeaders();
            String token = headers.getFirst("paradise-token");
            // 予以放行
            boolean verify  = JWTUitls.verifyToken(token);// 验证令牌
            if (verify) {
                return chain.filter(exchange);
            }
            map.put("code","T0001");// 设置状态
            map.put("message","token验证失败!");
        } catch (SignatureVerificationException e) {// 签名匹配异常
            map.put("code","T0002");
            map.put("message","无效签名!");
            e.printStackTrace();
        } catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("code","T0000");
            map.put("message","token已经过期!");
        } catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("code","T0003");
            map.put("message","算法异常!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code","T0002");
            map.put("message","无效签名!");
        }
        map.put("data",null);
        map.put("requestId", UUID.randomUUID().toString().replace("-",""));
        return denyAccess(exchange,map);
    }
    /**
     * 拦截并返回自定义的json字符串
     */
    private Mono<Void> denyAccess(ServerWebExchange exchange, Map map) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        //这里在返回头添加编码，否则中文会乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] bytes = JSON.toJSONBytes(map, SerializerFeature.WriteMapNullValue);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }
}
