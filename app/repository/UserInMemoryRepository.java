package repository;

import javax.inject.Singleton;

import models.User;

@Singleton
public class UserInMemoryRepository extends InMemoryRepository<User> {
}
