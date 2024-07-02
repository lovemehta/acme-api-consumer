package com.acme.trigger;

import com.acme.consumer.IApiConsumer;
import com.acme.exceptions.AuthenticationException;
import com.acme.exceptions.DataFetchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Component that triggers parcel shop data fetching on application startup.
 */
@Component
public class ParcelShopsApiRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ParcelShopsApiRunner.class);

    private final IApiConsumer parcelShopsApiConsumer;

    /**
     * Constructs a new ParcelShopsApiRunner.
     *
     * @param parcelShopsApiConsumer the consumer service for fetching parcel shop data
     */
    @Autowired
    public ParcelShopsApiRunner(IApiConsumer parcelShopsApiConsumer) {
        this.parcelShopsApiConsumer = parcelShopsApiConsumer;
    }

    /**
     * Runs the parcel shop data fetching process on application startup.
     *
     * @param args the application arguments
     */
    @Override
    public void run(ApplicationArguments args) {
        try {
            parcelShopsApiConsumer.consume();
            logger.info("Data Fetched Successfully!");
        } catch (AuthenticationException | DataFetchException e) {
            logger.error("Failed to fetch data: {}", e.getMessage(), e);
        }
    }
}
