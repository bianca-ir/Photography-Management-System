package project.core.model.validators;

public interface Validator<T> {
    /**
     * Validate an entity
     *
     * @param entity
     *          Entity of generic type
     * @throws ValidatorException
     *          if invalid entity provided
     */
    void validate(T entity) throws ValidatorException;
}
