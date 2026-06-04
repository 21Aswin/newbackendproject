package jobportal.demo.Specfication;

import jobportal.demo.Entity.Users;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification{

        public static Specification<Users> hasExperience(Integer experience) {
            return (root, query, cb) ->
                    cb.greaterThanOrEqualTo(
                            root.get("experience"),
                            experience
                    );
        }

        public static Specification<Users> hasIncome(Double income) {
            return (root, query, cb) ->
                    cb.greaterThanOrEqualTo(
                            root.get("income"),
                            income
                    );
        }

        public static Specification<Users> hasDepartment(String department) {
            return (root, query, cb) ->
                    cb.equal(
                            root.get("department"),
                            department
                    );
        }
    }
