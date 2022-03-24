package com.Test.Server.Repository;

import com.Test.Server.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message,String> {

    @Query("select x from Message as x where x.timestamp>=:startDate and x.timestamp<=:endDate")
    List<Message> getMessage(Instant startDate, Instant endDate);

}
