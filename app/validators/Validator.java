package validators;

import models.Entity;

public interface Validator<T extends Entity> {

	boolean validate(T entity);
}
