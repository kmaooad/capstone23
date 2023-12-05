
@ApplicationScoped
public class NotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification save(Notification notification) {
        persist(notification);
        return notification;
    }

    public Notification findByUserIdAndType(String userId, String type){
        return find("userId = :userId and type =:type",
                Parameters.with("userId", userId).and("notificationType", type))
                .firstResult();
    }
}