package cache;

import io.quarkus.redis.client.RedisClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class VeiculoCache {

    @Inject
    RedisClient redis;

    public void salvar(String key, String value) {
        redis.set(List.of(key, value));
    }

    public String buscar(String key) {
        var result = redis.get(key);
        return result != null ? result.toString() : null;
    }
}