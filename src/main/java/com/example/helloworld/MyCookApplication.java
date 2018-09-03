package com.example.helloworld;

import com.example.helloworld.core.Category;
import com.example.helloworld.core.Recipe;
import com.example.helloworld.db.CategoryDAO;
import com.example.helloworld.db.RecipeDAO;
import com.example.helloworld.resources.CategoryResource;
import com.example.helloworld.resources.RecipeResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class MyCookApplication extends Application<MyCookConfiguration> {
    public static void main(String[] args) throws Exception {
        new MyCookApplication().run(args);
    }

    private final HibernateBundle<MyCookConfiguration> hibernateBundle =
        new HibernateBundle<MyCookConfiguration>(Recipe.class, Category.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(MyCookConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<MyCookConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)
            )
        );

        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<MyCookConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MyCookConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(MyCookConfiguration configuration, Environment environment) {
        final RecipeDAO recipeDao = new RecipeDAO(hibernateBundle.getSessionFactory());
        final CategoryDAO categoryDao = new CategoryDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new RecipeResource(recipeDao));
        environment.jersey().register(new CategoryResource(categoryDao));
    }
}
