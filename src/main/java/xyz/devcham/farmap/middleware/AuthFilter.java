package xyz.devcham.farmap.middleware;

import java.io.IOException;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xyz.devcham.farmap.utils.CacheService;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private static final Set<String> PUBLIC = Set.of(
            "POST /api/user",
            "POST /api/user/session");

    private final CacheService cache;

    public AuthFilter(CacheService cache) {
        this.cache = cache;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws ServletException, IOException {

        String path = req.getRequestURI();
        String method = req.getMethod();

        if (PUBLIC.contains(method + " " + path)) {
            chain.doFilter(req, res);
            return;
        }

        String sid = null;
        if (req.getCookies() != null) {
            for (var c : req.getCookies()) {
                if (c.getName().equals("SESSION_ID")) {
                    sid = c.getValue();
                    break;
                }
            }
        }

        if (sid == null || cache.get("session:" + sid) == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(req, res);
    }
}
