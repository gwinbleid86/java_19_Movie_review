package kg.attractor.movie_review.util;

import jakarta.servlet.http.HttpServletRequest;

public class Utility {

    private Utility() {
    }

    public static String getSiteUrl(HttpServletRequest request) {
        String siteUrl = request.getRequestURL().toString();
        return siteUrl.replace(request.getServletPath(), "");
    }
}
