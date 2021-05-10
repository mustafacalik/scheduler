package tr.com.app.cpa.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Random;

@Component
public class IdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String currentTime = Long.toString(System.currentTimeMillis());
        String randomNumber = Integer.toString(new Random().nextInt(1000));
        return Long.valueOf(currentTime + randomNumber);
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return false;
    }
}
