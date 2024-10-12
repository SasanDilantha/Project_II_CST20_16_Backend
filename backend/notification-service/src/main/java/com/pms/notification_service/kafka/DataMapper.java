package com.pms.notification_service.kafka;

import com.pms.monitoring_service.kafka.MonitoringNotifications;
import com.pms.notification_service.model.Notification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DataMapper {
    public Notification mapToNotification(MonitoringNotifications monitoringNotifications) {
        LocalDate date = LocalDate.now();
        return Notification.builder()
                .notification_type(monitoringNotifications.notificationType())
                .content(monitoringNotifications.notificationMessage())
                .date(date)
                .build();
    }
}
