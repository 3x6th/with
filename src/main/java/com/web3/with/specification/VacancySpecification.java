package com.web3.with.specification;

import com.web3.with.entity.VacancyEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * Предоставляет методы для создания спецификаций для поиска вакансий по ключевым словам.
 */

public class VacancySpecification {

    /**
     * Создает {@link Specification} для поиска вакансий по ключевым слов.
     * Поиск ведется по полям {@code title} и {@code description} из {@link VacancyEntity}.
     * Если ключевые слова не предоставлены, спецификация вернет все вакансии.
     * </p>
     *
     * @param keywords
     *         Ключевые слова для поиска, разделенные пробелами.
     *
     * @return {@link Specification}  Spring Data JPA для фильтрации
     * вакансий по ключевым словам.
     */
    public static Specification<VacancyEntity> searchByKeyword(String keywords) {
        return (Root<VacancyEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (keywords == null || keywords.trim().isEmpty()) {
                return builder.conjunction(); // returns all the vacancies if keyword is null
            }

            String[] words = keywords.split("\\s+"); // split the keywords by spaces
            Predicate[] predicates = new Predicate[words.length];
            for (int i = 0; i < words.length; i++) {
                String keyword = words[i];
                predicates[i] = builder.or(
                        builder.like(
                                builder.lower(root.get("title")),
                                "%" + keyword.toLowerCase() + "%"
                        ),
                        builder.like(
                                builder.lower(root.get("description")),
                                "%" + keyword.toLowerCase() + "%"
                        )
                );
            }
            return builder.and(predicates); // join all the predicates
        };
    }

}
