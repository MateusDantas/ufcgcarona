package validators;

import exceptions.ValidateError;
import models.Entity;

public interface Validator<T extends Entity> {

	void validate(T entity) throws ValidateError;
}
