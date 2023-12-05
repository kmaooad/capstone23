package edu.kmaooad.capstone23.notifications.events;

public class NotificationSubscribed {
        private String id;
        private String userId;
        private String notificationStatus;
        private String subscriptionMethod;

        public NotificationSubscribed(
                String id,
                String userId,
                String notificationStatus
        ) {
            this.id = id;
            this.userId = userId;
            this.notificationStatus = notificationStatus;
            this.subscriptionMethod = subscriptionMethod;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNotificationStatus() {
            return notificationStatus;
        }

        public void setNotificationStatus(String notificationStatus) {
            this.notificationStatus = notificationStatus;
        }

        public String getSubscriptionMethod() {
            return subscriptionMethod;
        }

        public void setSubscriptionMethod(String subscriptionMethod) {
            this.subscriptionMethod = subscriptionMethod;
        }
}
