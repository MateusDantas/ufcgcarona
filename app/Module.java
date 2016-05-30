import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import models.User;
import repository.Repository;
import repository.UserInMemoryRepository;
import services.RealUserService;
import services.UserService;
import validators.UserValidator;
import validators.Validator;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

    @Override
    public void configure() {
    	
    	bind(new TypeLiteral<Repository<User>>() {})
    		.annotatedWith(Names.named("UserRepository"))
    		.to(UserInMemoryRepository.class)
    		.asEagerSingleton();
        
    	bind(new TypeLiteral<Validator<User>>() {})
    		.annotatedWith(Names.named("UserValidator"))
    		.to(UserValidator.class);
    		
    	bind (UserService.class)
    		.to(RealUserService.class)
    		.asEagerSingleton();
    }

}
