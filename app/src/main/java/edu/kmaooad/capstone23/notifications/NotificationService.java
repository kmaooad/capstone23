


public interface NotificationService {
    Notification save(Notification notification);
    Notification findByUserIdAndType(String userId, String type);
}