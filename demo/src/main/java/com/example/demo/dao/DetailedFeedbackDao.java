package com.example.demo.dao;

import com.example.demo.bean.DetailedFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Component(value = "detailedFeedbackDao")
public interface DetailedFeedbackDao extends JpaRepository<DetailedFeedback,Long> {

    @Query(value = "SELECT * FROM faqs_detailed_feedback" +
            " WHERE DATEDIFF(df_createdate,NOW())=0" +
            " AND df_type = :type" +
            " AND df_dl_id = :dlid" +
            " AND df_ip = :ip", nativeQuery = true)
    List<DetailedFeedback> findAllByTypeAndDlIdAndIp(@Param("type") long type, @Param("dlid") long dlid, @Param("ip") String ip);

    @Query(value = "SELECT COUNT(*) FROM faqs_detailed_feedback" +
            " WHERE df_type = 1 AND df_dl_id = :dlId ", nativeQuery = true)
    long getByTepeCount(@Param("dlId") long dlId);

    @Query(value = "select * from faqs_detailed_feedback where df_id = :id", nativeQuery = true)
    DetailedFeedback getById(@Param("id")long id);

    @Modifying
    @Query("update DetailedFeedback  d set d.contentStatus = :df_nay_status where d.id = :id")
    void updateFeedbackStatus(@Param("id")long id,@Param("df_nay_status")long df_nay_status);
}
