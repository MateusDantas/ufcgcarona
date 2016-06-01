import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import models.Ride;
import models.User;
import repository.Repository;
import repository.RideEbeanRepository;
import repository.UserEbeanRepository;
import services.RealUserService;
import services.RideService;
import services.RealRideService;
import services.UserService;
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
    		.to(UserEbeanRepository.class)
    		.asEagerSingleton();
    	
    	bind(new TypeLiteral<Repository<Ride>>() {})
		.to(RideEbeanRepository.class)
		.asEagerSingleton();
    		
    	bind (UserService.class)
    		.to(RealUserService.class)
    		.asEagerSingleton();
    	
    	bind (RideService.class)
		.to(RealRideService.class)
		.asEagerSingleton();
    }

}
