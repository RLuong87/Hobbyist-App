package com.hooked.app.specification;

import com.hooked.app.models.angler.Angler;
import org.springframework.data.jpa.domain.Specification;

public class AnglerSpecifications {

    public static Specification<Angler> withCity(String city) {
        if (city == null) {
            return null;
        } else {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("city"), city));
        }
    }

    public static Specification<Angler> withName(String name) {
        if (name == null) {
            return null;
        } else {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name));
        }
    }

    public static Specification<Angler> withLocation(String location) {
        if (location == null) {
            return null;
        } else {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("location"), location));
        }
    }
}
