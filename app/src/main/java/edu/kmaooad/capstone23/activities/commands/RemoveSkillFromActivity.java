package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

    public class RemoveSkillFromActivity {
        @NotNull
        private ObjectId skillId;

        @NotNull
        private ObjectId activityId;

        public ObjectId getActivityId() {
            return activityId;
        }

        public void setActivityId(ObjectId activityId) {
            this.activityId = activityId;
        }

        public ObjectId getSkillSetId() {
            return activityId;
        }

        public void setSkillSetId(ObjectId activityId) {
            this.activityId = activityId;
        }

        public ObjectId getSkillId() {
            return skillId;
        }

        public void setSkillId(ObjectId skillId) {
            this.skillId = skillId;
        }
    }
